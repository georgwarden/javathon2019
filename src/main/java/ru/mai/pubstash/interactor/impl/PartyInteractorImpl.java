package ru.mai.pubstash.interactor.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.util.annotation.NonNull;
import ru.mai.pubstash.entity.Party;
import ru.mai.pubstash.interactor.PartyInteractor;
import ru.mai.pubstash.repository.PartyRepository;
import ru.mai.pubstash.util.Result;

@Service
public class PartyInteractorImpl implements PartyInteractor {

    private final PartyRepository partyRepository;

    @Autowired
    public PartyInteractorImpl(PartyRepository partyRepository){
        this.partyRepository = partyRepository;
    }

    @Override
    public Result<Party> createParty(@NonNull final String name) {
        Party party = new Party();
        party.setName(name);
        try {
            partyRepository.save(party);
        }catch (RuntimeException e){
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
