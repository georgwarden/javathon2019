package ru.mai.pubstash.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorDto {
    @JsonProperty("errorText")
    private String errorText;

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    public static ErrorDto create(String text){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setErrorText(text);
        return errorDto;
    }
}
