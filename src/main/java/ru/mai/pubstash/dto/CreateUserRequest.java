package ru.mai.pubstash.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;

public class CreateUserRequest {

    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("cardNumber")
    private BigInteger cardNumber;


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public BigInteger getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(BigInteger cardNumber) {
        this.cardNumber = cardNumber;
    }
}
