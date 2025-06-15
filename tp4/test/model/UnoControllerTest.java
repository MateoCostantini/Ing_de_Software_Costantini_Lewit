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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
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


    @Autowired
    MockMvc mockMvc;

    @MockBean
    Dealer dealer;


    @BeforeEach
    void beforeEach(){
        when(dealer.fullDeck()).thenReturn(UnoServiceTest.fullDeck());
    }

    private ResultActions checkStatusIsOk(MockHttpServletRequestBuilder request) throws Exception {
        return mockMvc.perform(request)
                .andExpect(status().isOk());
    }

    private ResultActions checkStatusBadRequest(MockHttpServletRequestBuilder request) throws Exception {
        return mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    private String checkStatusBadRequestForPlay(MockHttpServletRequestBuilder request, JsonCard card) throws Exception {
        return mockMvc.perform(request
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(card)))
                .andDo(print())
                .andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
    }

    private List<JsonCard> activeHand(String uuid) throws Exception {
        String resp = mockMvc.perform(get("/playerhand/" + uuid))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        return new ObjectMapper().readValue(resp, new TypeReference<List<JsonCard>>() {});
    }

    private String newGame() throws Exception {
        String resp = mockMvc.perform(post("/newmatch?players=Lolo&players=Pepe"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        return new ObjectMapper().readTree(resp).asText();
    }


    @Test void newMatchTest() throws Exception{
        String player1 = "Lolo";
        String player2 = "Pepe";

        String resp = checkStatusIsOk(post("/newmatch")
                .param("players", player1)
                .param("players", player2))
                .andReturn()
                .getResponse()
                .getContentAsString();

        String uuidString = new ObjectMapper().readTree(resp).asText();

        UUID matchId = UUID.fromString(uuidString);
        assertNotNull(matchId);

    }



    @Test void matchWithoutPlayersTest() throws Exception{
        checkStatusBadRequest(post("/newmatch"));
    }

    @Test void matchWithInvalidPlayerTest() throws Exception{
        String player1 = "Lolo";
        String player2 = "";

        String resp = checkStatusBadRequest(post("/newmatch")
                .param("players", player1)
                .param("players", player2))
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals(Match.NotValidPlayer, resp);
    }

    @Test void activeCardTest() throws Exception{
        String uuid = newGame();
        assertNotNull(UUID.fromString(uuid));

        checkStatusIsOk(get("/activecard/" + uuid));
    }


    @Test void activeCardInvalidMatchTest() throws Exception {
        UUID invalidUuid = UUID.randomUUID();

        checkStatusBadRequest(get("/activecard/" + invalidUuid));
    }


    @Test void playerHandTest() throws Exception {
        String uuid = newGame();
        assertNotNull(UUID.fromString(uuid));
        checkStatusIsOk(get("/playerhand/" + uuid));
    }


    @Test void playerHandInvalidMatchTest() throws Exception {
        UUID invalidUuid = UUID.randomUUID();
        checkStatusBadRequest(get("/playerhand/" + invalidUuid));
    }




    @Test void playWrongTurnTest() throws Throwable{
        String uuid = newGame();
        assertNotNull(UUID.fromString(uuid));

        JsonCard card = activeHand(uuid).getFirst();
        String resp =  checkStatusBadRequestForPlay(post("/play/" + uuid + "/Pepe"), card);
        assertEquals(Player.NotPlayersTurn + "Pepe", resp);
    }


    @Test void playFakeColorCardTest() throws Throwable{
        String uuid = newGame();
        assertNotNull(UUID.fromString(uuid));

        String fakeColor = "Orange";
        JsonCard wrongCard = new JsonCard(fakeColor, 2, "NumberCard", false);
        String resp = checkStatusBadRequestForPlay(post("/play/" + uuid + "/Lolo"), wrongCard);
        assertEquals("El color " + fakeColor + " no es valido", resp);
    }


    @Test void playFakeNumberCardTest() throws Exception{
        String uuid = newGame();
        assertNotNull(UUID.fromString(uuid));

        int fakenumber = 999;
        JsonCard wrongCard = new JsonCard("Red", fakenumber, "NumberCard", false);
        String resp = checkStatusBadRequestForPlay(post("/play/" + uuid + "/Lolo"), wrongCard);
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

        checkStatusIsOk(post("/draw/" + uuid + "/Lolo"));
    }


    @Test void drawCardInvalidPlayerTest() throws Exception {
        String uuid = newGame();
        assertNotNull(UUID.fromString(uuid));

        String resp = checkStatusBadRequest(post("/draw/" + uuid + "/Pepe"))
                .andReturn().getResponse().getContentAsString();

        assertEquals(Player.NotPlayersTurn + "Pepe", resp);
    }
}
