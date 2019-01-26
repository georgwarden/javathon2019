package ru.mai.pubstash.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreatePartyRequest {

    @JsonProperty("name")
    private String partyName;

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }
}
