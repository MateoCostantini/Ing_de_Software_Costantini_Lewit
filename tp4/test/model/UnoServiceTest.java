package org.udesa.unoback.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.udesa.unoback.service.Dealer;
import org.udesa.unoback.service.UnoService;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UnoServiceTest {
    @Autowired
    private UnoService unoService;

    @MockBean
    private Dealer dealer;

    public static List<Card> fullDeck() {
        return List.of(
                new NumberCard("Blue", 1),
                new NumberCard("Green", 9),
                new NumberCard("Red", 3),
                new ReverseCard("Blue"),
                new NumberCard("Yellow", 9),
                new NumberCard("Blue", 7),
                new NumberCard("Red", 1),
                new WildCard(),
                new NumberCard("Red", 4),
                new Draw2Card("Green"),
                new SkipCard("Blue"),
                new NumberCard("Yellow", 1),
                new NumberCard("Yellow", 2),
                new Draw2Card("Blue"),
                new NumberCard("Green", 3),
                new NumberCard("Red", 0)
        );
    }

    @BeforeEach
    void buildDeck() {
        List<Card> fixedDeck = fullDeck();
        when(dealer.fullDeck()).thenReturn(fixedDeck);
    }


    @Test public void newMatchTest(){
        UUID id = unoService.newMatch(List.of("Lolo", "Pepe"));
        assertNotNull(id);

    }

    @Test public void multipleMatchesTest(){

        UUID id1 = unoService.newMatch(List.of("Lolo", "Pepe"));
        UUID id2 = unoService.newMatch(List.of("Pepe", "Tata"));
        UUID id3 = unoService.newMatch(List.of("Tata", "Lolo"));

        assertNotNull(id1);
        assertNotNull(id2);
        assertNotNull(id3);

    }

    @Test public void activeCardTest(){

        UUID matchId = unoService.newMatch(List.of("Lolo", "Pepe"));
        assertNotNull(unoService.activeCard(matchId));
    }



    @Test public void activeCardWithNotExistenceMatchTest(){

        UUID noExistenceMatchId = UUID.randomUUID();

        assertEquals("La partida con ID " + noExistenceMatchId + " no existe.",
                assertThrows( IllegalArgumentException.class, () -> unoService.activeCard(noExistenceMatchId) ).getMessage() );

    }


    @Test public void playerHandTest() {
        UUID matchId = unoService.newMatch(List.of("Lolo", "Pepe"));
        assertNotNull(unoService.playerHand(matchId));

    }


    @Test public void playerHandWithNotExistenceMatchTest() {

        UUID noExistenceMatchId = UUID.randomUUID();

        assertEquals("La partida con ID " + noExistenceMatchId + " no existe.",
                assertThrows( IllegalArgumentException.class, () -> unoService.playerHand(noExistenceMatchId) ).getMessage() );

    }


    @Test public void playersHave7CardsAtBeginningTest(){
        UUID matchId = unoService.newMatch(List.of("Lolo", "Pepe"));
        assertEquals(7, unoService.playerHand(matchId).size());

    }


    @Test void playCardTest(){
        UUID matchId = unoService.newMatch(List.of("Lolo", "Pepe"));
        assertDoesNotThrow(() -> unoService.play(matchId, "Lolo", new NumberCard("Blue", 7)));
    }


    @Test void playCardWithNotExistenceMatchTest(){
        UUID noExistenceMatchId = UUID.randomUUID();

        assertEquals("La partida con ID " + noExistenceMatchId + " no existe.",
                assertThrows( IllegalArgumentException.class, () -> unoService.play(noExistenceMatchId, "Lolo", new NumberCard("Blue", 7)) ).getMessage() );
    }

    @Test void drawCardTest(){
        UUID matchId = unoService.newMatch(List.of("Lolo", "Pepe"));
        assertDoesNotThrow(() -> unoService.draw(matchId, "Lolo"));
    }

    @Test void drawCardIncreasesAmountInHandTest(){
        UUID matchId = unoService.newMatch(List.of("Lolo", "Pepe"));

        List<Card> pastCardsInHand = unoService.playerHand(matchId);
        unoService.draw(matchId, "Lolo");
        List<Card> actualCardsInHand = unoService.playerHand(matchId);

        assertEquals(pastCardsInHand.size()+1, actualCardsInHand.size());
    }

    @Test void drawCardWithNotExistenceMatchTest(){
        UUID noExistenceMatchId = UUID.randomUUID();

        assertEquals("La partida con ID " + noExistenceMatchId + " no existe.",
                assertThrows( IllegalArgumentException.class, () -> unoService.draw(noExistenceMatchId, "Lolo") ).getMessage() );
    }

}
