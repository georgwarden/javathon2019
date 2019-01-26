package ru.mai.pubstash.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;

public class CreateUserRequest {

    private String nickname;
    private BigInteger cardNumber;
    public CreateUserRequest(){}

    public CreateUserRequest(String nickname, BigInteger cardNumber) {
        this.nickname = nickname;
        this.cardNumber = cardNumber;
    }

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
