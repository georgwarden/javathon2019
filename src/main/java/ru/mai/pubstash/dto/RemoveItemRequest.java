package ru.mai.pubstash.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RemoveItemRequest {

    @JsonProperty("party_id")
    private long partyId;

    @JsonProperty("item_id")
    private long itemId;

    public long getPartyId() {
        return partyId;
    }

    public void setPartyId(long partyId) {
        this.partyId = partyId;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }
}
