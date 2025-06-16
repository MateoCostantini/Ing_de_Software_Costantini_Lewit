package org.udesa.unoback.service;

import org.springframework.stereotype.Component;
import org.udesa.unoback.model.*;

import java.util.*;

@Component
public class Dealer {
    public List<Card> fullDeck() {

        ArrayList<Card> deck = new ArrayList<>();
        deck.addAll( cardsOn("Red"));
        deck.addAll( cardsOn("Blue"));
        deck.addAll( cardsOn("Green"));
        deck.addAll( cardsOn("Yellow"));

        Collections.shuffle(deck);
        return deck;
    }

    private List<Card> cardsOn(String color) {
        List<Card> cards = new LinkedList<>();
        for (int i =1; i <= 9; i++){
            cards.add(new NumberCard(color, i));
            cards.add(new NumberCard(color, i));
        }

        cards.add(new NumberCard(color, 0));
        cards.add(new WildCard());

        for (int i=0; i<2; i++){
            cards.add(new SkipCard(color));
            cards.add(new Draw2Card(color));
            cards.add(new ReverseCard(color));

        }
        return cards;
    }
}
