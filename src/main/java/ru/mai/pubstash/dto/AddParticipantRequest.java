package ru.mai.pubstash.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddParticipantRequest {

    @JsonProperty("party_id")
    private long partyId;

    @JsonProperty("participant_id")
    private long participantId;

    public long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(long participantId) {
        this.participantId = participantId;
    }

    public long getPartyId() {
        return partyId;
    }

    public void setPartyId(long partyId) {
        this.partyId = partyId;
    }
}
