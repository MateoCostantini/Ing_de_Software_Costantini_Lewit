package Uno;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Uno {
    Stack<Carta> pozo = new Stack<>();
    List<Jugador> jugadores = new ArrayList<>();
    Jugador jugadorActual;


    public Uno(List<Carta> mazo, List<String> jugadores, int cantidadCartas){
        if (jugadores.size() < 2){
            throw new RuntimeException("La partida debe tener 2 o mas jugadores");
        }
        for(String jugador:jugadores){
            List<Carta> cartasJugador = new ArrayList<>(mazo.subList(0, cantidadCartas));
            mazo.subList(0, cantidadCartas).clear();
            this.jugadores.add(new JugadorReal(jugador, cartasJugador));
        }
        this.jugadores.add(new JugadorVacio());

        pozo.push(mazo.removeFirst());
        jugadorActual = this.jugadores.getFirst();

    }

    public Carta getUltimaCarta(){
        return pozo.peek();
    }

}
