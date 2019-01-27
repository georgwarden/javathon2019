package ru.mai.pubstash.dto.converters;

import ru.mai.pubstash.dto.UserDto;
import ru.mai.pubstash.entity.User;

public class UserConverter {
    public static UserDto convertUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setNickname(user.getNickname());
        userDto.setBalance(user.getBalance());
        userDto.setCardNumber(user.getCardNumber().toString());
        return userDto;
    }
}
