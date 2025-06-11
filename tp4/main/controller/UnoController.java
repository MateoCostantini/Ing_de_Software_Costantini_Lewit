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

//    @GetMapping("/")
//    public String saludo(){
//        //return " Estos son las cartas";
//        return "index";

    @GetMapping( "/hola")
    public ResponseEntity<String> holaMundo(){
        return new ResponseEntity<>("Respuesta a hola mundo", HttpStatus.OK);
    }

    @PostMapping("newmatch") public ResponseEntity newMatch(@RequestParam List<String> players){
        return ResponseEntity.ok(unoService.newMatch(players));
    }

    @PostMapping("play/{matchId}/{player}") public ResponseEntity play(@PathVariable UUID matchId, @PathVariable String player, @RequestBody JsonCard card) {
        unoService.play(matchId, player, card.asCard());
        return ResponseEntity.ok().build();
    }


    //@PostMapping("draw/{matchId}/{player}") public ResponseEntity drawCard( @PathVariable UUID matchId, @RequestParam String player ) {}

    @GetMapping("activecard/{matchId}") public ResponseEntity activeCard( @PathVariable UUID matchId ) {
        return ResponseEntity.ok(unoService.activeCard(matchId).asJson());
    }

    @GetMapping("playerhand/{matchId}") public ResponseEntity playerHand( @PathVariable UUID matchId ) {
        return ResponseEntity.ok(unoService.playerHand(matchId).stream().map(each -> each.asJson()));
    }

    // Como hay que manejar los errores?

}
