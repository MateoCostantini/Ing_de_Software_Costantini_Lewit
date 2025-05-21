package Uno;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Uno {
    Stack<Carta> pozo = new Stack<>();
    List<Jugador> jugadores = new ArrayList<>();
    Jugador jugadorActual;
    List<Carta> mazo;

    public Uno(List<Carta> mazo, List<String> jugadores, int cantidadCartas){
        if (jugadores.size() < 2){
            throw new RuntimeException("La partida debe tener 2 o mas jugadores");
        }

        List<Carta> cartasPrimerJugador = new ArrayList<>(mazo.subList(0, cantidadCartas));
        mazo.subList(0, cantidadCartas).clear();
        Jugador primerJugador = new Jugador(jugadores.removeFirst(), cartasPrimerJugador);
        this.jugadores.add(primerJugador);
        jugadorActual = primerJugador;

        //List<Carta> cartasSegundoJugador = new ArrayList<>(mazo.subList(0, cantidadCartas));
        //mazo.subList(0, cantidadCartas).clear();
        //Jugador segundoJugador = new Jugador(jugadores.removeFirst(), cartasSegundoJugador);
        //this.jugadores.add(segundoJugador);
        //jugadorActual.derecha = segundoJugador;
        //jugadorActual.izquierda = segundoJugador;
        //segundoJugador.izquierda = jugadorActual;
        //segundoJugador.derecha = jugadorActual;
        //jugadorActual = segundoJugador;

        for(String jugador:jugadores){
            List<Carta> cartasJugador = new ArrayList<>(mazo.subList(0, cantidadCartas));
            mazo.subList(0, cantidadCartas).clear();

            Jugador nuevoJugador = new Jugador(jugador, cartasJugador);
            this.jugadores.add(nuevoJugador);

            jugadorActual.derecha = nuevoJugador;
            primerJugador.izquierda = nuevoJugador;
            nuevoJugador.derecha = primerJugador;
            nuevoJugador.izquierda = jugadorActual;

            jugadorActual = nuevoJugador;

        }

        pozo.push(mazo.removeFirst());
        jugadorActual = this.jugadores.getFirst();
        this.mazo = mazo;
    }

    public Carta getUltimaCarta(){
        return pozo.peek();
    }

    public Uno jugar(String jugador, Carta carta){
        if (!jugador.equals(jugadorActual.getNombre())) {
            throw new RuntimeException("No es el turno de este jugador");
        }

        if (!this.jugadorActual.tieneCarta(carta)) {
            throw new RuntimeException("El jugador no tiene esa carta");
        }


        pozo.push(pozo.peek().puedeApilarse(carta));

        carta.aplicarCarta(this);

        jugadorActual = jugadorActual.derecha;

        return this;
    }


}