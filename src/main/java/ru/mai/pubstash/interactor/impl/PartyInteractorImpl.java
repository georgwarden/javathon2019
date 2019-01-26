package ru.mai.pubstash.interactor.impl;

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
    public Result<Party> createParty(@NonNull final String name) {
        Party party = new Party();
        party.setName(name);
        try {
            party = partyRepository.saveAndFlush(party);
        }catch (RuntimeException e){
            LOGGER.error("exception: ", e);
            return Result.fail();
        }
        return Result.success(party);
    }

    @Override
    public Result<Void> addParticipant(long participantId, long partyId) {
        return null;
    }

    @Override
    public Result<Void> removeParticipant(long participantId, long partyId) {
        return null;
    }
}
