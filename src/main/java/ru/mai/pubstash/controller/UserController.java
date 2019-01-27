package ru.mai.pubstash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.mai.pubstash.dto.*;
import ru.mai.pubstash.dto.converters.UserConverter;
import ru.mai.pubstash.entity.Member;
import ru.mai.pubstash.entity.User;
import ru.mai.pubstash.interactor.UserInteractor;

import java.util.stream.Collectors;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserInteractor userInteractor;
    private static double BALANCE_DEFAULT = 10000;

    @Autowired
    public UserController(final UserInteractor userInteractor) {
        this.userInteractor = userInteractor;
    }

    @PostMapping("/create")
    public Mono<ResponseEntity> createUser(@RequestBody CreateUserRequest request) {
        if (request.getNickname().isEmpty() || request.getCardNumber() == null) {
            return Mono.fromCallable(() -> ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(ErrorDto.create("Незаполнены обязательные поля!")));
        }
        return Mono.fromCallable(() -> {
            User user = new User();
            user.setNickname(request.getNickname());
            user.setCardNumber(request.getCardNumber());
            user.setBalance(BALANCE_DEFAULT);
            return userInteractor.createUser(user)
                    .fold(
                            (v) -> ResponseEntity.ok().build(),
                            () -> ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build()
                    );
        });
    }
    @GetMapping("/getById")
    public Mono<ResponseEntity<UserDto>> getUserById(@RequestParam(value = "Id") long  id) {
        return Mono.fromCallable(() -> userInteractor.findUserById(id)
                .fold(
                        (user) -> {
                            return ResponseEntity.ok(UserConverter.convertUserDto(user));
                        },
                        () -> ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build()
                )
        );
    }
    @GetMapping("/getByNickname")
    public Mono<ResponseEntity<?>> getUserByNickname(@RequestParam(value = "nickname") String nickname) {
        if (nickname.isEmpty()) {
            return Mono.fromCallable(() -> ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(ErrorDto.create("Незаполнено поле nickname")));
        }
        return Mono.fromCallable(() -> userInteractor.findUserByNickname(nickname)
                .fold(
                        (user) -> {
                            return ResponseEntity.ok(UserConverter.convertUserDto(user));
                        },
                        () -> ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build()
                )
        );
    }
}
