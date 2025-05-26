package Uno;

import java.util.ArrayList;
import java.util.List;

public class Uno {
    Carta pozo;
    List<Jugador> jugadores = new ArrayList<>();
    Jugador jugadorActual;
    List<Carta> mazo;
    Controlador controlador;

    private void verificarTurno(String jugador) {
        if (!jugador.equals(jugadorActual.getNombre())) {
            throw new RuntimeException("No es el turno de este jugador");
        }
    }

    public Uno(List<Carta> mazo, List<String> jugadores, int cantidadCartas){
        if (jugadores.size() < 2){
            throw new RuntimeException("La partida debe tener 2 o mas jugadores");
        }

        Jugador primerJugador = (new Jugador(jugadores.removeFirst())).agarrarCarta(cantidadCartas, mazo);
        this.jugadores.add(primerJugador);
        jugadorActual = primerJugador;

        for(String jugador:jugadores){
            Jugador nuevoJugador = (new Jugador(jugador)).agarrarCarta(cantidadCartas, mazo);
            this.jugadores.add(nuevoJugador);

            jugadorActual.derecha = nuevoJugador;
            primerJugador.izquierda = nuevoJugador;
            nuevoJugador.derecha = primerJugador;
            nuevoJugador.izquierda = jugadorActual;

            jugadorActual = nuevoJugador;

        }

        this.controlador = new ControladorDerecha();

        pozo = mazo.removeFirst();
        jugadorActual = this.jugadores.getFirst();
        this.mazo = mazo;
    }

    public Carta getUltimaCarta(){
        return pozo;
    }

    public Jugador getJugadorActual(){
        return jugadorActual;
    }

    public Uno jugar(String jugador, Carta carta){
        verificarTurno(jugador);

        if (!this.jugadorActual.tieneCarta(carta)) {
            throw new RuntimeException("El jugador no tiene esa carta");
        }

        if (!pozo.puedeApilarse(carta)) {
            throw new RuntimeException("Esta carta no puede ser apilada al mazo");
        }

        pozo = carta;
        jugadorActual.cartasEnMano.remove(carta);

        // si el jugador no canto UNO tirando su penultima carta, se lo penaliza y se le dan dos cartas
        if (jugadorActual.cartasEnMano.size() == 1 && !(jugadorActual.getCantoUno()) ){
            jugadorActual.agarrarCarta(2, mazo);
        }

        if (jugadorActual.cartasEnMano.isEmpty()){
            throw new RuntimeException("El Jugador gano el juego");
        }

        carta.aplicarCarta(this);

        jugadorActual = controlador.siguiente(jugadorActual);

        return this;
    }



    public Uno jugarYCantarUno(String jugador, Carta carta){
        verificarTurno(jugador);

        if (jugadorActual.cantidadCartas() != 2){
            jugadorActual.agarrarCarta(2, mazo);
        }
        else{
            jugadorActual.cantoUno = true;
        }
        jugar(jugador, carta);
        return this;
    }


    public Uno tomarCarta(String jugador) {
        verificarTurno(jugador);

        if (!jugadorActual.cartasEnMano.stream().anyMatch(carta -> pozo.puedeApilarse(carta))) {
            // Corrobora que no puede jugar ninguna carta.
            jugadorActual.agarrarCarta(1, mazo);
        }
        else {
            throw new RuntimeException("Se debia tirar la carta habilitada");
        }
        return this;
    }
}