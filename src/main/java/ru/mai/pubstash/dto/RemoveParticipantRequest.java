package ru.mai.pubstash.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RemoveParticipantRequest {

    @JsonProperty("id")
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
