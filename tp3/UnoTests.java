package Uno;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UnoTests {

    private static List<Carta> crearMazo() {
        List<Carta> mazo = new ArrayList<>();
        mazo.add(new CartaNumerada(5, "verde"));
        mazo.add(new CartaNumerada(3, "azul"));
        mazo.add(new CartaNumerada(7, "Amarillo"));
        mazo.add(new CartaNumerada(1, "azul"));
        mazo.add(new CartaNumerada(6, "rojo"));
        mazo.add(new CartaNumerada(4, "amarillo"));
        return mazo;
    }

    @Test void TestUnSoloJugador(){
        List<Carta> mazo = new ArrayList<>();
        mazo.add(new CartaNumerada(5, "verde"));
        mazo.add(new CartaNumerada(3, "azul"));
        List<String> jugadores = new ArrayList<>();
        jugadores.add("Pepe");
        assertThrows( RuntimeException.class, () -> new Uno(mazo, jugadores, 2));
    }

    @Test void TestPrimeraCarta(){
        List<String> jugadores = new ArrayList<>();
        jugadores.add("Pepe");
        jugadores.add("Lolo");

        assertEquals("rojo", new Uno(crearMazo(), jugadores, 2).getUltimaCarta().getColor());
        assertEquals(6, new Uno(crearMazo(), jugadores, 2).getUltimaCarta().getNumero());
    }

    @Test void JugarActualizaPozo(){
        List<String> jugadores = new ArrayList<>();
        jugadores.add("Pepe");
        jugadores.add("Lolo");
        // de esto hay que hacer un refactor, quizas despues necesitemos mas jugadores y lo hacemos cuando tengamos mas

        Carta v5 = new CartaNumerada(5, "verde");

        assertEquals("amarillo", new Uno(crearMazo(), jugadores, 2)
                .jugar("Pepe", v5)
                .getUltimaCarta()
                .getColor());

        assertEquals(5, new Uno(crearMazo(), jugadores, 2)
                .jugar("Pepe", v5)
                .getUltimaCarta()
                .getNumero());

    }

}
