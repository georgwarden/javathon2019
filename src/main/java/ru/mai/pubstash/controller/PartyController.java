package ru.mai.pubstash.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.mai.pubstash.dto.AddParticipantRequest;
import ru.mai.pubstash.dto.CreatePartyRequest;
import ru.mai.pubstash.dto.RemoveParticipantRequest;
import ru.mai.pubstash.interactor.PartyInteractor;

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

}
