package Uno;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UnoTests {

    private static List<Carta> crearMazo() {
        List<Carta> mazo = new ArrayList<>();
        mazo.add(new CartaNumerada(5, "rojo"));
        mazo.add(new CartaNumerada(3, "azul"));
        mazo.add(new CartaNumerada(5, "amarillo"));
        mazo.add(new CartaNumerada(1, "azul"));
        mazo.add(new CartaNumerada(6, "rojo"));
        mazo.add(new CartaNumerada(4, "amarillo"));
        mazo.add(new CartaNumerada(8, "rojo"));
        mazo.add(new CartaNumerada(6, "azul"));
        return mazo;
    }

    private static List<Carta> crearMazoConSalta() {
        List<Carta> mazo = new ArrayList<>();
        mazo.add(new CartaNumerada(5, "verde"));
        mazo.add(new CartaSalta("azul"));
        mazo.add(new CartaNumerada(1, "amarillo"));
        mazo.add(new CartaNumerada(7, "azul"));
        mazo.add(new CartaNumerada(6, "azul"));
        mazo.add(new CartaNumerada(4, "amarillo"));
        mazo.add(new CartaNumerada(8, "azul"));
        mazo.add(new CartaNumerada(6, "azul"));
        mazo.add(new CartaNumerada(5, "amarillo"));
        return mazo;
    }

    private static List<Carta> crearMazoConTomaDos() {
        List<Carta> mazo = new ArrayList<>();
        mazo.add(new CartaNumerada(5, "verde"));
        mazo.add(new CartaTomaDos("azul"));
        mazo.add(new CartaNumerada(7, "amarillo"));
        mazo.add(new CartaNumerada(1, "azul"));
        mazo.add(new CartaNumerada(6, "rojo"));
        mazo.add(new CartaNumerada(4, "amarillo"));
        mazo.add(new CartaNumerada(8, "azul"));
        mazo.add(new CartaNumerada(6, "azul"));
        mazo.add(new CartaNumerada(5, "azul"));
        mazo.add(new CartaNumerada(2, "verde"));
        return mazo;
    }

    private static List<String> crearJugadores() {
        List<String> jugadores = new ArrayList<>();
        jugadores.add("Pepe");
        jugadores.add("Lolo");
        return jugadores;
    }

    private static List<String> crearTresJugadores() {
        List<String> jugadores = new ArrayList<>();
        jugadores.add("Pepe");
        jugadores.add("Lolo");
        jugadores.add("Tata");
        return jugadores;
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
        assertEquals("rojo", new Uno(crearMazo(), crearJugadores(), 2).getUltimaCarta().getColor());
        assertEquals(6, new Uno(crearMazo(), crearJugadores(), 2).getUltimaCarta().getNumero());
    }

    @Test void JugarActualizaPozo(){
        Carta r5 = new CartaNumerada(5, "rojo");

        assertEquals("rojo", new Uno(crearMazo(), crearJugadores(), 2)
                .jugar("Pepe", r5)
                .getUltimaCarta()
                .getColor());

        assertEquals(5, new Uno(crearMazo(), crearJugadores(), 2)
                .jugar("Pepe", r5)
                .getUltimaCarta()
                .getNumero());
    }

    @Test void JugarActualizaJugadorActual(){
        Carta r5 = new CartaNumerada(5, "rojo");
        Carta a5 = new CartaNumerada(5, "amarillo");

        assertEquals("amarillo", new Uno(crearMazo(), crearJugadores(), 2)
                .jugar("Pepe", r5)
                .jugar("Lolo", a5)
                .getUltimaCarta()
                .getColor());

        assertEquals(5, new Uno(crearMazo(), crearJugadores(), 2)
                .jugar("Pepe", r5)
                .jugar("Lolo", a5)
                .getUltimaCarta()
                .getNumero());
    }

    @Test void tresJugadores(){
        Carta r5 = new CartaNumerada(5, "rojo");
        Carta a5 = new CartaNumerada(5, "amarillo");
        Carta a4 = new CartaNumerada(4, "amarillo");

        assertEquals("amarillo", new Uno(crearMazo(), crearTresJugadores(), 2)
                .jugar("Pepe", r5)
                .jugar("Lolo", a5)
                .jugar("Tata", a4)
                .getUltimaCarta()
                .getColor());

        assertEquals(4, new Uno(crearMazo(), crearTresJugadores(), 2)
                .jugar("Pepe", r5)
                .jugar("Lolo", a5)
                .jugar("Tata", a4)
                .getUltimaCarta()
                .getNumero());
    }

    @Test void tresJugadoresTurnoIncorrecto(){
        Carta v5 = new CartaNumerada(5, "verde");
        Carta a7 = new CartaNumerada(7, "amarillo");
        Carta a3 = new CartaNumerada(3, "azul");

        assertThrows( RuntimeException.class, () -> new Uno(crearMazo(), crearTresJugadores(), 2)
                .jugar("Pepe", v5)
                .jugar("Lolo", a7)
                .jugar("Pepe", a3));
    }

    @Test void cartaSalta() {
        Carta saltaAzul = new CartaSalta("azul");
        Carta a6 = new CartaNumerada(6, "azul");

        assertEquals("azul", new Uno(crearMazoConSalta(), crearTresJugadores(), 2)
                .jugar("Pepe", saltaAzul)
                .jugar("Tata", a6)
                .getUltimaCarta()
                .getColor());

        assertEquals(6, new Uno(crearMazoConSalta(), crearTresJugadores(), 2)
                .jugar("Pepe", saltaAzul)
                .jugar("Tata", a6)
                .getUltimaCarta()
                .getNumero());
    }

    @Test void cartaSaltaNoDejaJugar() {
        Carta saltaAzul = new CartaSalta("azul");
        Carta a7 = new CartaNumerada(7, "azul");

        assertThrows( RuntimeException.class, () -> new Uno(crearMazoConSalta(), crearTresJugadores(), 2)
                .jugar("Pepe", saltaAzul)
                .jugar("Lolo", a7));
    }

    @Test void cantidadCartas() {
        assertEquals(2, new Uno(crearMazoConTomaDos(), crearTresJugadores(), 2)
                .jugadorActual
                .cantidadCartas());
    }

    @Test void cartaTomaDos() {
        Carta tomaDosAzul = new CartaTomaDos("azul");

        assertEquals(4, new Uno(crearMazoConTomaDos(), crearTresJugadores(), 2)
                .jugar("Pepe", tomaDosAzul)
                .jugadorActual.izquierda
                .cantidadCartas());
    }

    @Test void cartaTomaDosSaltea() {
        Carta tomaDosAzul = new CartaTomaDos("azul");
        Carta a7 = new CartaNumerada(7, "azul");

        assertThrows( RuntimeException.class, () -> new Uno(crearMazoConTomaDos(), crearTresJugadores(), 2)
                .jugar("Pepe", tomaDosAzul)
                .jugar("Lolo", a7));

    }

}