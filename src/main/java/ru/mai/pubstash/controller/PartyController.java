package ru.mai.pubstash.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.mai.pubstash.dto.AddParticipantRequest;
import ru.mai.pubstash.dto.CreatePartyRequest;
import ru.mai.pubstash.dto.RemoveParticipantRequest;

@RestController
public class PartyController {

    @PostMapping("/create_party")
    public Mono<ResponseEntity> createParty(CreatePartyRequest request) {
        return null;
    }

    @PostMapping("/add_participant")
    public Mono<ResponseEntity> addParticipant(AddParticipantRequest request) {
        return null;
    }

    @PostMapping("/remove_participant")
    public Mono<ResponseEntity> removeParticipant(RemoveParticipantRequest request) {
        return null;
    }

}
