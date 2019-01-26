package ru.mai.pubstash.interactor;

import org.springframework.stereotype.Component;
import ru.mai.pubstash.util.Result;

@Component
public class PartyInteractor {

    public Result<Void> createParty(String name) {
        return Result.success(null);
    }

    public Result<Void> addParticipant(long participantId, long partyId) {
        return Result.success(null);
    }

    public Result<Void> removeParticipant(long participantId, long partyId) {
        return Result.success(null);
    }

}
