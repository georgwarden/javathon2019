package ru.mai.pubstash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public Mono<ResponseEntity> createParty(@RequestBody CreatePartyRequest request) {
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
    public Mono<ResponseEntity> addParticipant(@RequestBody AddParticipantRequest request) {
        return Mono.fromCallable(() ->
                partyInteractor.addParticipant(request.getParticipantId(), request.getPartyId())
                        .fold(
                                (v) -> ResponseEntity.ok().build(),
                                () -> ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build()
                        ));
    }

    @PostMapping("/remove_participant")
    public Mono<ResponseEntity> removeParticipant(@RequestBody RemoveParticipantRequest request) {
        return Mono.fromCallable(() ->
                partyInteractor.removeParticipant(request.getParticipantId(), request.getPartyId())
                        .fold(
                                (v) -> ResponseEntity.ok().build(),
                                () -> ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build()
                        ));
    }

    @GetMapping("/get_party")
    public Mono<ResponseEntity<GetPartyResponse>> getParty(@RequestParam(value = "id")  long partyId) {
        return Mono.fromCallable(() -> partyInteractor.getParty(partyId)
                .fold(
                        (party) -> {
                            GetPartyResponse response = new GetPartyResponse();
                            response.setId(party.getId());
                            response.setName(party.getName());
                            response.setDescription(party.getDescription());
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

    @PostMapping("/add_item")
    public Mono<ResponseEntity> addItem(@RequestBody AddItemRequest request) {
        return Mono.fromCallable(() ->
                partyInteractor.addItem(
                        request.getPartyId(),
                        request.getName(),
                        request.getCost()
                ).fold(
                        (v) -> ResponseEntity.ok().build(),
                        () -> ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build()
                )
        );
    }

    @PostMapping("/remove_item")
    public Mono<ResponseEntity> removeItem(@RequestBody RemoveItemRequest request) {
        return Mono.fromCallable(() ->
                partyInteractor.removeItem(request.getPartyId(), request.getItemId())
                        .fold(
                                (v) -> ResponseEntity.ok().build(),
                                () -> ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build()
                        )
        );
    }

}
