package ru.mai.pubstash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.mai.pubstash.dto.*;
import ru.mai.pubstash.entity.Member;
import ru.mai.pubstash.entity.Party;
import ru.mai.pubstash.entity.User;
import ru.mai.pubstash.interactor.PartyInteractor;
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
    public Mono<ResponseEntity> createUser(CreateUserRequest request) {
        if(request.getNickname().isEmpty() || request.getCardNumber().bitLength() > 0){
            return Mono.create((e) -> ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(ErrorDto.create("Незаполнены обязательные поля!")));
        }
        User user = new User();
        user.setNickname(request.getNickname());
        user.setCardNumber(request.getCardNumber());
        user.setBalance(BALANCE_DEFAULT);
        return Mono.fromCallable(() ->
                userInteractor.createUser(user)
                        .fold(
                                (v) -> ResponseEntity.ok().build(),
                                () -> ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build()
                        ));
    }
}
