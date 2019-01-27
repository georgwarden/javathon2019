package ru.mai.pubstash.interactor;

import ru.mai.pubstash.entity.Member;
import ru.mai.pubstash.entity.Party;
import ru.mai.pubstash.util.Result;


public interface PartyInteractor {

     Result<Party> createParty(Party party) ;

     Result<Member> addParticipant(long participantId, long partyId);

     Result<Void> removeParticipant(long participantId, long partyId);

     Result<Party> getParty(long id);

     Result<Void> addItem(long partyId, String name, float cost);

     Result<Void> removeItem(long partyId, long itemId);

}
