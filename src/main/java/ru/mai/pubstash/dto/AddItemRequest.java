package ru.mai.pubstash.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddItemRequest {

    @JsonProperty("party_id")
    private long partyId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("cost")
    private float cost;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public long getPartyId() {
        return partyId;
    }

    public void setPartyId(long partyId) {
        this.partyId = partyId;
    }
}
