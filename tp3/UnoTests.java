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
        mazo.add(new CartaNumerada(2, "verde"));
        mazo.add(new CartaNumerada(9, "amarillo"));
        mazo.add(new CartaNumerada(8, "amarillo"));
        mazo.add(new CartaNumerada(2, "amarillo"));
        mazo.add(new CartaNumerada(3, "amarillo"));

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
        mazo.add(new CartaNumerada(2, "amarillo"));
        mazo.add(new CartaNumerada(3, "amarillo"));
        return mazo;
    }

    private static List<Carta> crearMazoConTomaDos() {
        List<Carta> mazo = new ArrayList<>();
        mazo.add(new CartaNumerada(5, "verde"));
        mazo.add(new CartaTomaDos("azul"));
        mazo.add(new CartaNumerada(7, "amarillo"));
        mazo.add(new CartaNumerada(1, "azul"));
        mazo.add(new CartaNumerada(6, "azul"));
        mazo.add(new CartaNumerada(4, "amarillo"));
        mazo.add(new CartaNumerada(8, "azul"));
        mazo.add(new CartaNumerada(6, "verde"));
        mazo.add(new CartaNumerada(5, "azul"));
        mazo.add(new CartaNumerada(2, "verde"));
        mazo.add(new CartaNumerada(2, "amarillo"));
        mazo.add(new CartaNumerada(3, "amarillo"));
        mazo.add(new CartaNumerada(9, "amarillo"));
        mazo.add(new CartaNumerada(8, "amarillo"));
        return mazo;
    }

    private static List<Carta> crearMazoConComodin() {
        List<Carta> mazo = new ArrayList<>();
        mazo.add(new CartaNumerada(5, "verde"));
        mazo.add(new CartaComodin());
        mazo.add(new CartaComodin());
        mazo.add(new CartaNumerada(1, "azul"));
        mazo.add(new CartaNumerada(6, "verde"));
        mazo.add(new CartaNumerada(4, "amarillo"));
        mazo.add(new CartaNumerada(8, "verde"));
        mazo.add(new CartaNumerada(2, "amarillo"));
        mazo.add(new CartaNumerada(3, "amarillo"));
        return mazo;
    }

    private static List<Carta> crearMazoConReversa() {
        List<Carta> mazo = new ArrayList<>();
        mazo.add(new CartaReversa("azul"));
        mazo.add(new CartaNumerada(1, "azul"));
        mazo.add(new CartaNumerada(7, "azul"));
        mazo.add(new CartaReversa("azul"));
        mazo.add(new CartaNumerada(6, "azul"));
        mazo.add(new CartaNumerada(4, "amarillo"));
        mazo.add(new CartaNumerada(8, "azul"));
        mazo.add(new CartaNumerada(6, "verde"));
        mazo.add(new CartaNumerada(5, "verde"));
        mazo.add(new CartaNumerada(2, "verde"));
        mazo.add(new CartaNumerada(2, "amarillo"));
        mazo.add(new CartaNumerada(3, "amarillo"));
        return mazo;
    }

    private static List<Carta> crearMazoConReversaTomaDos() {
        List<Carta> mazo = new ArrayList<>();
        mazo.add(new CartaTomaDos("azul"));
        mazo.add(new CartaNumerada(1, "azul"));
        mazo.add(new CartaNumerada(7, "amarillo"));
        mazo.add(new CartaReversa("azul"));
        mazo.add(new CartaNumerada(6, "azul"));
        mazo.add(new CartaNumerada(4, "amarillo"));
        mazo.add(new CartaNumerada(8, "azul"));
        mazo.add(new CartaNumerada(6, "verde"));
        mazo.add(new CartaNumerada(5, "verde"));
        mazo.add(new CartaNumerada(2, "verde"));
        mazo.add(new CartaNumerada(2, "amarillo"));
        mazo.add(new CartaNumerada(3, "amarillo"));
        mazo.add(new CartaNumerada(9, "amarillo"));
        mazo.add(new CartaNumerada(8, "amarillo"));

        return mazo;
    }

    private static List<Carta> crearMazoConReversaSalta() {
        List<Carta> mazo = new ArrayList<>();
        mazo.add(new CartaSalta("azul"));
        mazo.add(new CartaNumerada(1, "azul"));
        mazo.add(new CartaNumerada(7, "amarillo"));
        mazo.add(new CartaReversa("azul"));
        mazo.add(new CartaNumerada(6, "azul"));
        mazo.add(new CartaNumerada(4, "amarillo"));
        mazo.add(new CartaNumerada(8, "azul"));
        mazo.add(new CartaNumerada(6, "verde"));
        mazo.add(new CartaNumerada(5, "verde"));
        mazo.add(new CartaNumerada(2, "verde"));
        return mazo;
    }

    private static List<Carta> crearMazoRepetidas() {
        List<Carta> mazo = new ArrayList<>();
        mazo.add(new CartaNumerada(5, "rojo"));
        mazo.add(new CartaNumerada(5, "rojo"));
        mazo.add(new CartaNumerada(1, "azul"));
        mazo.add(new CartaNumerada(6, "rojo"));
        mazo.add(new CartaNumerada(5, "amarillo"));
        mazo.add(new CartaNumerada(4, "amarillo"));
        mazo.add(new CartaNumerada(8, "rojo"));
        mazo.add(new CartaNumerada(6, "azul"));
        mazo.add(new CartaNumerada(2, "amarillo"));
        mazo.add(new CartaNumerada(3, "amarillo"));
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
        assertEquals("La partida debe tener 2 o mas jugadores", assertThrows( RuntimeException.class, () -> new Uno(mazo, jugadores, 2)).getMessage());
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
        Carta r5 = new CartaNumerada(5, "rojo");
        Carta am5 = new CartaNumerada(5, "amarillo");
        Carta a3 = new CartaNumerada(3, "azul");

        assertEquals("No es el turno de este jugador", assertThrows( RuntimeException.class, () -> new Uno(crearMazo(), crearTresJugadores(), 2)
                .jugar("Pepe", r5)
                .jugar("Lolo", am5)
                .jugar("Pepe", a3))
                .getMessage());
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

        assertEquals("No es el turno de este jugador", assertThrows( RuntimeException.class, () -> new Uno(crearMazoConSalta(), crearTresJugadores(), 2)
                .jugar("Pepe", saltaAzul)
                .jugar("Lolo", a7)).getMessage());
    }

    @Test void cantidadCartas() {
        assertEquals(2, new Uno(crearMazoConTomaDos(), crearTresJugadores(), 2)
                .getJugadorActual()
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

        assertEquals("No es el turno de este jugador", assertThrows( RuntimeException.class, () -> new Uno(crearMazoConTomaDos(), crearTresJugadores(), 2)
                .jugar("Pepe", tomaDosAzul)
                .jugar("Lolo", a7)).getMessage());

    }

    @Test void JugadorApilaCartaHabilitadaPrimerTurno() {
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

    @Test void JugadorApilaCartaHabilitadaTurnosPosteriores() {
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

    @Test void JugadorApilaCartaNoHabilitadaNumerada() {
        Carta r5 = new CartaNumerada(5, "rojo");
        Carta a1 = new CartaNumerada(1, "azul");

        assertEquals("Esta carta no puede ser apilada al mazo", assertThrows( RuntimeException.class, () -> new Uno(crearMazo(), crearJugadores(), 2)
                .jugar("Pepe", r5)
                .jugar("Lolo", a1)).getMessage());

    }

    @Test void JugadorApilaSumaDosSobreNumeradaHabilitada() {
        Carta tomaDos = new CartaTomaDos( "azul");
        Carta a6 = new CartaNumerada(6, "azul");

        assertEquals("azul", new Uno(crearMazoConTomaDos(), crearTresJugadores(), 2)
                .jugar("Pepe", tomaDos)
                .jugar("Tata", a6)
                .getUltimaCarta()
                .getColor());

        assertEquals(6, new Uno(crearMazoConTomaDos(), crearTresJugadores(), 2)
                .jugar("Pepe", tomaDos)
                .jugar("Tata", a6)
                .getUltimaCarta()
                .getNumero());

    }


    @Test void JugadorApilaComodinSobreNumerada() {
        Carta comodin = new CartaComodin();
        Carta v5 = new CartaNumerada(5, "verde");

        assertEquals("azul", new Uno(crearMazoConComodin(), crearJugadores(), 2)
                .jugar("Pepe", v5)
                .jugar("Lolo", comodin.asignarColor("azul"))
                .getUltimaCarta()
                .getColor());
    }

    @Test void JugadorApilaNumeradaSobeComodinHabilitada() {
        Carta comodin = new CartaComodin();
        Carta a1 = new CartaNumerada(1, "azul");

        assertEquals("azul", new Uno(crearMazoConComodin(), crearJugadores(), 2)
                .jugar("Pepe", comodin.asignarColor("azul"))
                .jugar("Lolo", a1)
                .getUltimaCarta()
                .getColor());

        assertEquals(1, new Uno(crearMazoConComodin(), crearJugadores(), 2)
                .jugar("Pepe", comodin.asignarColor("azul"))
                .jugar("Lolo", a1)
                .getUltimaCarta()
                .getNumero());

    }

    @Test void JugadorApilaNumeradaSobreComodinNoHabilitada() {
        Carta a1 = new CartaNumerada(1, "azul");
        Carta comodin = new CartaComodin();

        assertEquals("Esta carta no puede ser apilada al mazo", assertThrows( RuntimeException.class, () -> new Uno(crearMazoConComodin(), crearJugadores(), 2)
                .jugar("Pepe", comodin.asignarColor("verde"))
                .jugar("Lolo", a1)).getMessage());

    }

    @Test void ReversaCambiaOrden() {
        Carta a1 = new CartaNumerada(1, "azul");
        Carta reversaAzul = new CartaReversa("azul");

        assertEquals( "Pepe", new Uno(crearMazoConReversa(), crearTresJugadores(), 2)
                .jugar("Pepe", a1)
                .jugar("Lolo", reversaAzul)
                .getJugadorActual()
                .getNombre());
    }

    @Test void TestDobleReversa() {
        Carta a1 = new CartaNumerada(1, "azul");
        Carta reversaAzul = new CartaReversa("azul");
        Carta a7 = new CartaNumerada(7, "azul");
        assertEquals( "Tata", new Uno(crearMazoConReversa(), crearTresJugadores(), 2)
                .jugar("Pepe", a1)
                .jugar("Lolo", reversaAzul)
                .jugar("Pepe", reversaAzul)
                .jugar("Lolo", a7)
                .getJugadorActual()
                .getNombre());
    }

    @Test void TomaDosParaLaIzquierda() {
        Carta a1 = new CartaNumerada(1, "azul");
        Carta reversaAzul = new CartaReversa("azul");
        Carta tomaDosAzul = new CartaTomaDos("azul");

        assertEquals( 4, new Uno(crearMazoConReversaTomaDos(), crearTresJugadores(), 2)
                .jugar("Pepe", a1)
                .jugar("Lolo", reversaAzul)
                .jugar("Pepe", tomaDosAzul)
                .getJugadorActual().derecha
                .cantidadCartas());

        assertEquals( "Lolo", new Uno(crearMazoConReversaTomaDos(), crearTresJugadores(), 2)
                .jugar("Pepe", a1)
                .jugar("Lolo", reversaAzul)
                .jugar("Pepe", tomaDosAzul)
                .getJugadorActual()
                .getNombre());

    }


    @Test void JugadorNoPuedeTirarDosVecesMismaCarta() {
        Carta a1 = new CartaNumerada(1, "azul");
        Carta reversaAzul = new CartaReversa("azul");

        assertEquals("El jugador no tiene esa carta", assertThrows(RuntimeException.class, () -> new Uno(crearMazoConReversa(), crearTresJugadores(), 2)
                .jugar("Pepe", a1)
                .jugar("Lolo", reversaAzul)
                .jugar("Pepe", a1)).getMessage());

    }

    @Test void TomarCarta() {
        Carta r5 = new CartaNumerada(5, "rojo");
        Carta am5 = new CartaNumerada(5, "amarillo");

        assertEquals( 2, new Uno(crearMazo(), crearJugadores(), 2)
                .jugarYCantarUno("Pepe", r5)
                .jugar("Lolo", am5)
                .tomarCarta("Pepe")
                .getJugadorActual()
                .cantidadCartas());
    }

    @Test void CantarUno() {
        Carta r5 = new CartaNumerada(5, "rojo");

        assertEquals( true, new Uno(crearMazo(), crearJugadores(), 2)
                .jugarYCantarUno("Pepe", r5)
                .getJugadorActual().izquierda
                .getCantoUno());
    }

    @Test void CantarUnoIncorrectamenteAgarraCartas() {
        // El comportamiento esperado es que tira la carta (rojo5) pero que al cantar UNO agarra dos.
        Carta r5 = new CartaNumerada(5, "rojo");

        assertEquals( 4, new Uno(crearMazo(), crearJugadores(), 3)
                .jugarYCantarUno("Pepe", r5)
                .getJugadorActual().izquierda
                .cantidadCartas());
    }

    @Test void NoCantarUnoCuandoDeberia() {
        Carta r5 = new CartaNumerada(5, "rojo");

        assertEquals( 3, new Uno(crearMazo(), crearJugadores(), 2)
                .jugar("Pepe", r5)
                .getJugadorActual().izquierda
                .cantidadCartas());
    }

    @Test void TomarCartaAnulaCantarUno() {
        Carta r5 = new CartaNumerada(5, "rojo");
        Carta am5 = new CartaNumerada(5, "amarillo");

        assertEquals( false, new Uno(crearMazo(), crearJugadores(), 2)
                .jugarYCantarUno("Pepe", r5)
                .jugarYCantarUno("Lolo", am5)
                .tomarCarta("Pepe")
                .getJugadorActual()
                .getCantoUno());

    }

    @Test void GanaElJuego() {
        Carta a1 = new CartaNumerada(1, "azul");
        Carta a7 = new CartaNumerada(7, "azul");
        Carta reversaAzul = new CartaReversa("azul");

        assertEquals("El Jugador gano el juego",
                assertThrows(RuntimeException.class, () -> new Uno(crearMazoConReversa(), crearJugadores(), 2)
                        .jugarYCantarUno("Pepe", a1)
                        .jugarYCantarUno("Lolo", a7)
                        .jugar("Pepe", reversaAzul))
                        .getMessage());
    }

    @Test void CartasRepetidas() {
        Carta r5 = new CartaNumerada(5, "rojo");
        Carta am5 = new CartaNumerada(5, "amarillo");
        Carta reversaAzul = new CartaReversa("azul");

        assertEquals("rojo", new Uno(crearMazoRepetidas(), crearJugadores(), 3)
                .jugar("Pepe", r5)
                .jugar("Lolo", am5)
                .jugarYCantarUno("Pepe", r5)
                .getUltimaCarta()
                .getColor());
    }

}
