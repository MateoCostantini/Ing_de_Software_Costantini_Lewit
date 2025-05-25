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
        mazo.add(new CartaNumerada(6, "azul"));
        mazo.add(new CartaNumerada(4, "amarillo"));
        mazo.add(new CartaNumerada(8, "azul"));
        mazo.add(new CartaNumerada(6, "verde"));
        mazo.add(new CartaNumerada(5, "azul"));
        mazo.add(new CartaNumerada(2, "verde"));
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

        assertThrows( RuntimeException.class, () -> new Uno(crearMazoConTomaDos(), crearTresJugadores(), 2)
                .jugar("Pepe", tomaDosAzul)
                .jugar("Lolo", a7));

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

        assertThrows( RuntimeException.class, () -> new Uno(crearMazo(), crearJugadores(), 2)
                .jugar("Pepe", r5)
                .jugar("Lolo", a1));

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

    @Test void JugadorApilaSumaDosSobreNumeradaNoHabilitada() {
        Carta tomaDos = new CartaTomaDos( "azul");
        Carta a4 = new CartaNumerada(4, "azul");

        assertThrows( RuntimeException.class, () -> new Uno(crearMazoConTomaDos(), crearTresJugadores(), 2)
                .jugar("Pepe", tomaDos)
                .jugar("Tata", a4));


    }

    // Testear para cada uno de los tipos de cartas (tipo a un +2 apilarle un numero del mismo color
    // O a un +2 apilarle un salta, etc.)

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

        assertThrows( RuntimeException.class, () -> new Uno(crearMazoConComodin(), crearJugadores(), 2)
                .jugar("Pepe", comodin.asignarColor("verde"))
                .jugar("Lolo", a1));

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

    @Test void prueba() {
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

    @Test void JugadorNoPuedeTirarDosVecesMismaCarta() {
        Carta a1 = new CartaNumerada(1, "azul");
        Carta reversaAzul = new CartaReversa("azul");

        assertThrows(RuntimeException.class, () -> new Uno(crearMazoConReversa(), crearTresJugadores(), 2)
                .jugar("Pepe", a1)
                .jugar("Lolo", reversaAzul)
                .jugar("Pepe", a1));

    }

    @Test void TomarCarta() {
        Carta r5 = new CartaNumerada(5, "rojo");
        Carta reversaAzul = new CartaReversa("azul");
        Carta am5 = new CartaNumerada(5, "amarillo");

        assertEquals( 2, new Uno(crearMazo(), crearJugadores(), 2)
                .jugar("Pepe", r5)
                .jugar("Lolo", am5)
                .tomarCarta("Pepe")
                .getJugadorActual()
                .cantidadCartas());
    }

    @Test void CantarUno() {
        Carta r5 = new CartaNumerada(5, "rojo");

        assertEquals( true, new Uno(crearMazo(), crearJugadores(), 2)
                .cantarUnoYJugar("Pepe", r5)
                .getJugadorActual().izquierda
                .getCantoUno());
    }

    @Test void TomarCartaAnulaCantarUno() {
        Carta r5 = new CartaNumerada(5, "rojo");
        Carta am5 = new CartaNumerada(5, "amarillo");

        assertEquals( false, new Uno(crearMazo(), crearJugadores(), 2)
                .cantarUnoYJugar("Pepe", r5)
                .cantarUnoYJugar("Lolo", am5)
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
                    .cantarUnoYJugar("Pepe", a1)
                    .cantarUnoYJugar("Lolo", a7)
                    .jugar("Pepe", reversaAzul))
                        .getMessage());
    }


    // agregar test salta para la izquierda
        // tomaDos testea un comportamiento casi identico, mepa q no haria falta

    // Testear si un jugador tiene dos cartas iguales (podrian ser dos comodines o dos +2 del mismo color. Y si tira una sigue con la otra en mano.

    // Testear las cosas que agregue (lo que esta abajo)
}

// agregue que el jugador cuando tira una carta no tiene que tener mas esa carta. #TESTEADO
// hice que pozo sea una carta y no un stack. #asumo que no lleva test
// cambie la funcion puedeApilarse para que devuelva un boolean. Sirve para poder hacer un stream en el caso de tomarCarta #no se si lleva test
// Agregue la funcion tomarCarta que se va a llamar en el caso de que un jugador no pueda tirar ninguna carta.
// Agregue que el jugador puede cantar uno. Lo tiene que hacer solo si le queda una carta. #TESTEADO
// Si despues de cantar uno agarra una carta (por +2 o xq no tiene para tirar) entonces se le va el UNO que habia cantado. #TESTEADO
// No se si se podria hacer con polimorfismo lo de cantar UNO, pero no me esta dando la cabeza. #emilio en un mail dijo que no es polimorfico el IF de cantar uno
// Agregue que si despues de tirar una carta ya no tiene mas y habia cantado 1, entonce gano el juego.

// TESTEAR y revisar Todas estas cosas nuevas.

// faltaria ver los casos donde si un jugador se queda sin cartas pero no canto UNO.
