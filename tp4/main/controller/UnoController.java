package org.udesa.unoback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.udesa.unoback.model.JsonCard;
import org.udesa.unoback.service.UnoService;

import java.util.List;
import java.util.UUID;

@Controller
public class UnoController {
    @Autowired
    UnoService unoService;


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegal(IllegalArgumentException exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntime(RuntimeException exception){
        return ResponseEntity.internalServerError().body(exception.getMessage() );
    }


    @PostMapping("newmatch")
    public ResponseEntity newMatch(@RequestParam List<String> players){
        return ResponseEntity.ok(unoService.newMatch(players));
    }
    // curl -X POST "http://localhost:8080/newmatch?players=Lolo&players=Pepe" -H "Accept: application/json"

    @PostMapping("play/{matchId}/{player}")
    public ResponseEntity play(@PathVariable UUID matchId, @PathVariable String player, @RequestBody JsonCard card) {
        unoService.play(matchId, player, card.asCard());
        return ResponseEntity.ok().build();
    }
    // curl -X POST "http://localhost:8080/play/35655cdd-83cf-4b72-a02e-a4b713c03291/Lolo" -H "Content-Type: Application/json" -d '{"color":"Blue","number":8,"type":"NumberCard","shout":false}'


    @PostMapping("draw/{matchId}/{player}")
    public ResponseEntity drawCard( @PathVariable UUID matchId, @PathVariable String player ) {
        unoService.draw(matchId, player);
        return ResponseEntity.ok().build();
    }
    // POST draw/{matchId}/{player}

    @GetMapping("activecard/{matchId}")
    public ResponseEntity activeCard( @PathVariable UUID matchId ) {
        return ResponseEntity.ok(unoService.activeCard(matchId).asJson());
    }
    // curl -X GET "http://localhost:8080/activecard/35655cdd-83cf-4b72-a02e-a4b713c03291" -H "Accept: application/json"

    @GetMapping("playerhand/{matchId}")
    public ResponseEntity playerHand( @PathVariable UUID matchId ) {
        return ResponseEntity.ok(unoService.playerHand(matchId).stream().map(each -> each.asJson()));
    }
    // curl -X GET "http://localhost:8080/playerhand/35655cdd-83cf-4b72-a02e-a4b713c03291" -H "Accept: application/json"



}
