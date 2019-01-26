package ru.mai.pubstash.interactor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mai.pubstash.entity.Party;
import ru.mai.pubstash.repository.PartyRepository;
import ru.mai.pubstash.util.Result;

import java.util.Optional;

@Component
public class PartyInteractor {

    private PartyRepository partyRepository;

    @Autowired
    public PartyInteractor(PartyRepository partyRepository) {
        this.partyRepository = partyRepository;
    }

    public Result<Void> createParty(String name) {
        return Result.success(null);
    }

    public Result<Void> addParticipant(long participantId, long partyId) {
        return Result.success(null);
    }

    public Result<Void> removeParticipant(long participantId, long partyId) {
        return Result.success(null);
    }

    public Result<Party> getParty(long id) {
        Optional<Party> party = partyRepository.findById(id);
        return party.map(Result::success).orElseGet(Result::fail);
    }

}
