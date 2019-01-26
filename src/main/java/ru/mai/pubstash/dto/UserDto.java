package ru.mai.pubstash.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public class UserDto {
    @JsonProperty("id")
    private long id;

    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("balance")
    private double balance;

    @JsonProperty("cardNumber")
    private String cardNumber;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

}
