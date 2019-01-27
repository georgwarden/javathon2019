package ru.mai.pubstash.interactor.impl;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mai.pubstash.entity.Item;
import ru.mai.pubstash.entity.Member;
import ru.mai.pubstash.entity.Party;
import ru.mai.pubstash.entity.User;
import ru.mai.pubstash.interactor.PartyInteractor;
import ru.mai.pubstash.repository.MemberRepository;
import ru.mai.pubstash.repository.PartyRepository;
import ru.mai.pubstash.repository.UserRepository;
import ru.mai.pubstash.util.Result;

import java.util.Optional;

@Service
public class PartyInteractorImpl implements PartyInteractor {

    private static final Logger LOGGER = LoggerFactory.getLogger(PartyInteractorImpl.class);

    private final PartyRepository partyRepository;
    private final UserRepository userRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public PartyInteractorImpl(
            PartyRepository partyRepository,
            UserRepository userRepository,
            MemberRepository memberRepository
    ) {
        this.partyRepository = partyRepository;
        this.userRepository = userRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public Result<Party> createParty(final Party party) {
        return Result.retrieve(() -> {
            Preconditions.checkArgument(party != null && !party.getName().isEmpty());
            return partyRepository.saveAndFlush(party);
        });
    }


    @Override
    public Result<Member> addParticipant(long participantId, long partyId) {
        Optional<User> user = userRepository.findById(participantId);
        Optional<Party> party = partyRepository.findById(partyId);
        if (user.isPresent() && party.isPresent()) {
            Member newMember = new Member();
            newMember.setCost(0);
            newMember.setUser(user.get());
            newMember.setParty(party.get());
            return Result.retrieve(() -> memberRepository.saveAndFlush(newMember));
        } else {
            return Result.fail();
        }
    }

    @Override
    public Result<Void> removeParticipant(long participantId, long partyId) {
        Optional<Member> member = memberRepository.findByUserIdAndPartyId(participantId, partyId);
        if (member.isPresent()) {
            memberRepository.delete(member.get());
            return Result.success(null);
        } else {
            return Result.fail();
        }
    }

    @Override
    public Result<Party> getParty(long id) {
        Optional<Party> party = partyRepository.findById(id);
        return party.map(Result::success).orElseGet(Result::fail);
    }

    @Override
    public Result<Void> addItem(long partyId, String name, float cost) {
        Optional<Party> party = partyRepository.findById(partyId);
        if (party.isPresent()) {
            Party actual = party.get();
            Item newItem = new Item();
            newItem.setName(name);
            newItem.setCost(cost);
            actual.getItems().add(newItem);
            partyRepository.saveAndFlush(actual);
            return Result.success(null);
        } else {
            return Result.fail();
        }
    }

}
