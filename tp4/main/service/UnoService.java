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

    private Match getMatchOrThrow(UUID matchId) {
        Match match = sessions.get(matchId);
        if (match == null) {
            throw new IllegalArgumentException("La partida con ID " + matchId + " no existe.");
        }
        return match;
    }

    public UUID newMatch(List<String> players) {
        UUID newKey = UUID.randomUUID();
        sessions.put(newKey, Match.fullMatch(dealer.fullDeck(), players));
        return newKey;
    }

    public List<Card> playerHand(UUID matchId) {
        return getMatchOrThrow(matchId).playerHand();
    }

    public Card activeCard(UUID matchId) {
        return getMatchOrThrow(matchId).activeCard();
    }

    public void play(UUID matchId, String player, Card card) {
        Match match = getMatchOrThrow(matchId);
        match.play(player, card);
        if (match.isOver()) {
            sessions.remove(matchId);
        }
        // Si termina el juego deberia eliminarlo del diccionario?
    }

    public void draw(UUID matchId, String player) {
        getMatchOrThrow(matchId).drawCard(player);
    }

    // Hice algo que esta chequeando el color de las cartaaas que se pasan dentor de ColoredCard. Pero que pasa con el wildCard??


}
