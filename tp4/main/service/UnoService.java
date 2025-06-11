package org.udesa.unoback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.udesa.unoback.model.Card;
import org.udesa.unoback.model.JsonCard;
import org.udesa.unoback.model.Match;

import java.util.*;

@Service
public class UnoService {
    @Autowired
    private Dealer dealer;
    private Map<UUID, Match> sessions = new HashMap<UUID, Match>();

    public UUID newMatch(List<String> players) {
        UUID newKey = UUID.randomUUID();
        sessions.put(newKey, Match.fullMatch(dealer.fullDeck(), players));
        return newKey;
        // esta funcionando si le paso un unico jugador tambien. Deberia?
    }

    public List<Card> playerHand(UUID matchId) {
        return sessions.get(matchId).playerHand();
        // habria que ver el caso en que te pasan un matchId que no existe.
    }

    public Card activeCard(UUID matchId) {
        return sessions.get(matchId).activeCard();
        // habria que ver el caso en que te pasan un matchId que no existe.
    }

    public void play(UUID matchId, String player, Card card) {
        sessions.get(matchId).play(player, card);
        // Si termina el juego deberia eliminarlo del diccionario.
        // Si la primera carta es una wildcard no se puede tirar nada q no sea una wildcard. es problema del modelo.
    }


    // deberian estar chequeando en todo momento el estado del juego

}
