package org.udesa.unoback.model;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.udesa.unoback.controller.UnoController;
import org.udesa.unoback.service.Dealer;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class UnoControllerTest {
//    @Autowired
//    UnoController unoController;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    Dealer dealer;


    @BeforeEach
    void beforeEach(){
        when(dealer.fullDeck()).thenReturn(UnoServiceTest.fullDeck());
    }

    private List<JsonCard> activeHand(String uuid) throws Exception {
        String resp = mockMvc.perform(get("/playerhand/" + uuid))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        return new ObjectMapper().readValue(resp, new TypeReference<List<JsonCard>>() {});
    }

    private String newGame() throws Exception {
        // Simular la creacion con newMatch
        String resp = mockMvc.perform(post("/newmatch?players=Lolo&players=Pepe"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        return new ObjectMapper().readTree(resp).asText();
    }


    @Test void newMatchTest() throws Exception{
        String player1 = "Lolo";
        String player2 = "Pepe";

        String resp = mockMvc.perform(
                        post("/newmatch")
                                .param("players", player1)
                                .param("players", player2)
                )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String uuidString = new ObjectMapper().readTree(resp).asText();

        UUID matchId = UUID.fromString(uuidString);
        assertNotNull(matchId);

    }

    @Test void matchWithoutPlayersTest() throws Exception{
        mockMvc.perform(post("/newmatch"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test void matchWithInvalidPlayerTest() throws Exception{
        String player1 = "Lolo";
        String player2 = "";

        String resp = mockMvc.perform(
                        post("/newmatch")
                                .param("players", player1)
                                .param("players", player2)
                )
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals(Match.NotValidPlayer, resp);
    }

    @Test void activeCardTest() throws Exception{
        String uuid = newGame();
        assertNotNull(UUID.fromString(uuid));

        mockMvc.perform(get("/activecard/" + uuid))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test void activeCardInvalidMatchTest() throws Exception {
        UUID invalidUuid = UUID.randomUUID();

        mockMvc.perform(get("/activecard/" + invalidUuid))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }


    @Test void playerHandTest() throws Exception {
        String uuid = newGame();
        assertNotNull(UUID.fromString(uuid));

        mockMvc.perform(get("/playerhand/" + uuid))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test void playerHandInvalidMatchTest() throws Exception {
        UUID invalidUuid = UUID.randomUUID();

        mockMvc.perform(get("/playerhand/" + invalidUuid))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }


    @Test void playWrongTurnTest() throws Throwable{
        String uuid = newGame();
        assertNotNull(UUID.fromString(uuid));

        List<JsonCard> cards = activeHand(uuid);

        String resp = mockMvc.perform(post("/play/" + uuid + "/Pepe")
                .contentType(MediaType.APPLICATION_JSON)
                .content(cards.getFirst().toString()))
                .andDo(print())
                .andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

        assertEquals(Player.NotPlayersTurn + "Pepe", resp);
    }


    @Test void playFakeColorCardTest() throws Throwable{
        String uuid = newGame();
        assertNotNull(UUID.fromString(uuid));

        String fakeColor = "Orange";
        JsonCard wrongCard = new JsonCard(fakeColor, 2, "NumberCard", false);

        String resp = mockMvc.perform(post("/play/" + uuid + "/Lolo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(wrongCard)))
                .andDo(print())
                .andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

        assertEquals("El color " + fakeColor + " no es valido", resp);
    }

    @Test void playFakeNumberCardTest() throws Exception{
        String uuid = newGame();
        assertNotNull(UUID.fromString(uuid));

        int fakenumber = 999;
        JsonCard wrongCard = new JsonCard("Red", fakenumber, "NumberCard", false);

        String resp = mockMvc.perform(post("/play/" + uuid + "/Lolo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(wrongCard)))
                .andDo(print())
                .andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

        assertEquals("El numero " + fakenumber + " no es valido", resp);
    }

    @Test void playWithoutCardTest() throws Exception {
        String uuid = newGame();
        assertNotNull(UUID.fromString(uuid));

        mockMvc.perform(post("/play/" + uuid + "/Lolo")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test void drawCardTest() throws Exception {
        String uuid = newGame();
        assertNotNull(UUID.fromString(uuid));

        mockMvc.perform(post("/draw/" + uuid + "/Lolo"))
                .andExpect(status().isOk());


    }

    @Test void drawCardInvalidPlayerTest() throws Exception {
        String uuid = newGame();
        assertNotNull(UUID.fromString(uuid));

        String resp = mockMvc.perform(post("/draw/" + uuid + "/Pepe"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn().getResponse().getContentAsString();

        assertEquals(Player.NotPlayersTurn + "Pepe", resp);
    }
}
