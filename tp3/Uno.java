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

    public Uno jugar(String jugador, Carta carta){
        if (!jugador.equals(jugadorActual.getNombre())) {
            throw new RuntimeException("No es el turno de este jugador");
        }

        // aplicar jugadorTieneCarta para verificar si el jugador tiene la carta
        if (!this.jugadorActual.tieneCarta(carta)) {
            throw new RuntimeException("El jugador no tiene esa carta");
        }
        // no esta andando bien tieneCarta. hablando con chat creo que es porque habria que hacer algo tipo get... para comparar, o sino reescribir el equals que no se bien a que se refiere

        pozo.push(carta);

        // actualizar siguiente jugador, ver bien como hacemos eso
//        jugadorActual = hay que crear esta func

    return this;
    }

}
