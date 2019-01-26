package ru.mai.pubstash.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.mai.pubstash.dto.*;
import ru.mai.pubstash.entity.Member;
import ru.mai.pubstash.interactor.PartyInteractor;

import java.util.stream.Collectors;

@RestController
public class PartyController {

    private PartyInteractor interactor;

    public PartyController(PartyInteractor interactor) {
        this.interactor = interactor;
    }

    @PostMapping("/create_party")
    public Mono<ResponseEntity> createParty(CreatePartyRequest request) {
        return Mono.fromCallable(() ->
                interactor.createParty(request.getPartyName())
                        .fold(
                                (v) -> ResponseEntity.ok().build(),
                                () -> ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build()
                        ));
    }

    @PostMapping("/add_participant")
    public Mono<ResponseEntity> addParticipant(AddParticipantRequest request) {
        return Mono.fromCallable(() ->
                interactor.addParticipant(request.getParticipantId(), request.getPartyId())
                        .fold(
                                (v) -> ResponseEntity.ok().build(),
                                () -> ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build()
                        ));
    }

    @PostMapping("/remove_participant")
    public Mono<ResponseEntity> removeParticipant(RemoveParticipantRequest request) {
        return Mono.fromCallable(() ->
                interactor.removeParticipant(request.getParticipantId(), request.getPartyId())
                        .fold(
                                (v) -> ResponseEntity.ok().build(),
                                () -> ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build()
                        ));
    }

    @GetMapping("/get_party")
    public Mono<ResponseEntity<GetPartyResponse>> getParty(GetPartyRequest request) {
        return Mono.fromCallable(() -> interactor.getParty(request.getId())
                .fold(
                        (party) -> {
                            GetPartyResponse response = new GetPartyResponse();
                            response.setId(party.getId());
                            response.setName(party.getName());
                            response.setMembers(
                                    party.getMembers().stream()
                                            .map(Member::getUser)
                                            .map((user) -> {
                                                UserDto userDto = new UserDto();
                                                userDto.setId(user.getId());
                                                userDto.setName(user.getNickname());
                                                return userDto;
                                            })
                                            .collect(Collectors.toList())
                            );
                            response.setItems(
                                    party.getItems().stream()
                                            .map((item) -> {
                                                ItemDto itemDto = new ItemDto();
                                                itemDto.setId(item.getId());
                                                itemDto.setName(item.getName());
                                                itemDto.setCost(item.getCost());
                                                return itemDto;
                                            })
                                            .collect(Collectors.toList())
                            );
                            return ResponseEntity.ok(response);
                        },
                        () -> ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build()
                )
        );
    }

}
