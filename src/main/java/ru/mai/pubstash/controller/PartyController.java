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
import ru.mai.pubstash.interactor.PartyInteractor;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/party")
public class PartyController {

    private final PartyInteractor partyInteractor;

    @Autowired
    public PartyController(final PartyInteractor partyInteractor) {
        this.partyInteractor = partyInteractor;
    }

    @PostMapping("/create")
    public Mono<ResponseEntity> createParty(CreatePartyRequest request) {
        return Mono.fromCallable(() -> {
            Party party = new Party();
            party.setName(request.getPartyName());
            party.setDescription(request.getDescription());
            return partyInteractor.createParty(party)
                    .fold(
                            (v) -> ResponseEntity.ok().build(),
                            () -> ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build()
                    );
        });
    }

    @PostMapping("/add_participant")
    public Mono<ResponseEntity> addParticipant(AddParticipantRequest request) {
        return Mono.fromCallable(() ->
                partyInteractor.addParticipant(request.getParticipantId(), request.getPartyId())
                        .fold(
                                (v) -> ResponseEntity.ok().build(),
                                () -> ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build()
                        ));
    }

    @PostMapping("/remove_participant")
    public Mono<ResponseEntity> removeParticipant(RemoveParticipantRequest request) {
        return Mono.fromCallable(() ->
                partyInteractor.removeParticipant(request.getParticipantId(), request.getPartyId())
                        .fold(
                                (v) -> ResponseEntity.ok().build(),
                                () -> ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build()
                        ));
    }

    @GetMapping("/get_party")
    public Mono<ResponseEntity<GetPartyResponse>> getParty(GetPartyRequest request) {
        return Mono.fromCallable(() -> partyInteractor.getParty(request.getId())
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
                                                userDto.setNickname(user.getNickname());
                                                userDto.setCardNumber(user.getCardNumber().toString());
                                                userDto.setBalance(user.getBalance());
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
