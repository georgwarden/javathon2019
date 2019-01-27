package ru.mai.pubstash.controller;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.mai.pubstash.dto.ErrorDto;
import ru.mai.pubstash.dto.SignInRequest;
import ru.mai.pubstash.dto.SignUpRequest;
import ru.mai.pubstash.dto.UserDto;
import ru.mai.pubstash.dto.converters.UserConverter;
import ru.mai.pubstash.entity.User;
import ru.mai.pubstash.interactor.UserInteractor;
import ru.mai.pubstash.util.UserUtils;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;

/**
 * Контроллер для авторизации и регистрации
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    private static double BALANCE_DEFAULT = 10000;

    @Autowired
    private UserInteractor userInteractor;


    @PostMapping("/signin")
    public Mono<ResponseEntity> authenticateUser(@RequestBody SignInRequest signInRequest, HttpSession session) {
        return Mono.fromCallable(() -> userInteractor.findUserByNickname(signInRequest.getLogin()).fold(
                (user) -> {
                    if (user.getPassword().equals(signInRequest.getPassword())) {
                        UserUtils.saveUserToSession(session, user);
                        return ResponseEntity.ok(UserConverter.convertUserDto(user));
                    } else {
                        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(ErrorDto.create("Неправильный пароль!"));
                    }
                },
                () -> ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(ErrorDto.create("Пользователь не найден!"))
        ));

    }

    @PostMapping("/signup")
    public Mono<ResponseEntity> registerUser(@RequestBody SignUpRequest signUpRequest) {
        return Mono.fromCallable(() -> userInteractor.findUserByNickname(signUpRequest.getLogin()).
                fold(
                        (us) -> {
                            if (us != null) {
                                return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(ErrorDto.create("Пользователь с таким логином уже существует!"));
                            }
                            User user = new User();
                            user.setNickname(signUpRequest.getLogin());
                            user.setPassword(signUpRequest.getPassword());
                            user.setCardNumber(BigInteger.valueOf(signUpRequest.getCardNumber()));
                            user.setBalance(BALANCE_DEFAULT);
                            return userInteractor.createUser(user)
                                    .fold((u) -> ResponseEntity.ok().body(UserConverter.convertUserDto(u)),
                                            () -> ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(ErrorDto.create("Ошибка регистрации!"))
                                    );
                        },
                        () -> {
                            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(ErrorDto.create("Ошибка регистрации!"));

                        }
                )
        );

    }

    @GetMapping("/get_current")
    public Mono<ResponseEntity> getCurrentUser(HttpSession session) {
        return Mono.fromCallable(() -> {
            User user = UserUtils.getUserFromSession(session);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(ErrorDto.create("Необходима авторизация!"));
            }
            return ResponseEntity.ok().body(UserConverter.convertUserDto(user));
        });
    }

    @GetMapping("/exit")
    public Mono<ResponseEntity> exit(HttpSession session) {
        return Mono.fromCallable(() -> {
            UserUtils.deleteUserFromSession(session);
            return ResponseEntity.ok().build();
        });
    }

}