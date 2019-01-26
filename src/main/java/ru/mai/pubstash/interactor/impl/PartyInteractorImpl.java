package ru.mai.pubstash.interactor.impl;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.util.annotation.NonNull;
import ru.mai.pubstash.entity.Party;
import ru.mai.pubstash.interactor.PartyInteractor;
import ru.mai.pubstash.repository.PartyRepository;
import ru.mai.pubstash.util.Result;

import java.util.Optional;

@Service
public class PartyInteractorImpl implements PartyInteractor {

    private static final Logger LOGGER = LoggerFactory.getLogger(PartyInteractorImpl.class);
    private final PartyRepository partyRepository;

    @Autowired
    public PartyInteractorImpl(PartyRepository partyRepository) {
        this.partyRepository = partyRepository;
    }

    @Override
    public Result<Party> createParty(final Party party) {
        return Result.retrieve(() -> {
            Preconditions.checkArgument(party != null && !party.getName().isEmpty());
            return partyRepository.saveAndFlush(party);
        });
    }


    @Override
    public Result<Void> addParticipant(long participantId, long partyId) {
        return null;
    }

    @Override
    public Result<Void> removeParticipant(long participantId, long partyId) {
        return null;
    }

    @Override
    public Result<Party> getParty(long id) {
        Optional<Party> party = partyRepository.findById(id);
        return party.map(Result::success).orElseGet(Result::fail);
    }

}
