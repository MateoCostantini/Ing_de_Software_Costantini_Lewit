package org.udesa.unoback.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.udesa.unoback.service.UnoService;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UnoServiceTest {
    @Autowired
    private UnoService unoService;

    @Test public void newMatchTest(){
        UUID id = unoService.newMatch(List.of("Lolo", "Pepe"));
        assertNotNull(id);

    }

    @Test public void multipleMatches(){
        UUID id1 = unoService.newMatch(List.of("Lolo", "Pepe"));
        UUID id2 = unoService.newMatch(List.of("Pepe", "Tata"));
        UUID id3 = unoService.newMatch(List.of("Tata", "Lolo"));

        unoService.
    }
}
