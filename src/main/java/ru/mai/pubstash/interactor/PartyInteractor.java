package ru.mai.pubstash.interactor;

import ru.mai.pubstash.entity.Party;
import ru.mai.pubstash.util.Result;


public interface PartyInteractor {

     Result<Party> createParty(String name) ;

     Result<Void> addParticipant(long participantId, long partyId);

     Result<Void> removeParticipant(long participantId, long partyId);
}
