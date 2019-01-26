package ru.mai.pubstash.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreatePartyRequest {

    @JsonProperty("name")
    private String partyName;

    @JsonProperty("description")
    private String description;

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
