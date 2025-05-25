package Uno;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Uno {
    Carta pozo;
    List<Jugador> jugadores = new ArrayList<>();
    Jugador jugadorActual;
    List<Carta> mazo;
    Controlador controlador;

    public Uno(List<Carta> mazo, List<String> jugadores, int cantidadCartas){
        if (jugadores.size() < 2){
            throw new RuntimeException("La partida debe tener 2 o mas jugadores");
        }

        //List<Carta> cartasPrimerJugador = new ArrayList<>(mazo.subList(0, cantidadCartas));
        //mazo.subList(0, cantidadCartas).clear();
        Jugador primerJugador = (new Jugador(jugadores.removeFirst())).agarrarCarta(cantidadCartas, mazo);
        this.jugadores.add(primerJugador);
        jugadorActual = primerJugador;

        for(String jugador:jugadores){
            //List<Carta> cartasJugador = new ArrayList<>(mazo.subList(0, cantidadCartas));
            //mazo.subList(0, cantidadCartas).clear();

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

    public Uno jugar(String jugador, Carta carta){
        if (!jugador.equals(jugadorActual.getNombre())) {
            throw new RuntimeException("No es el turno de este jugador");
        }

        if (!this.jugadorActual.tieneCarta(carta)) {
            throw new RuntimeException("El jugador no tiene esa carta");
        }

        if (!pozo.puedeApilarse(carta)) {
            throw new RuntimeException("Esta carta no puede ser apilada al mazo");
        }


        pozo = carta;
        jugadorActual.cartasEnMano.remove(carta);

        carta.aplicarCarta(this);

        if (jugadorActual.cartasEnMano.isEmpty() && jugadorActual.getCantoUno()){
            throw new RuntimeException("El Jugador gano el juego");
        }

        //carta.cantoUno = false;
        jugadorActual = controlador.siguiente(jugadorActual);

        return this;
    }

    public Uno cantarUnoYJugar(String jugador, Carta carta){
        // pregunto si tiene 2, porque ya al entrar esta funcion se asume que se puede tirar la carta que se pase como parametro.
        // si la carta fuese incorrecta, o por cualquier otra razon, tira exception. el comportamiento esperado es que tenga 2 cartas.
        if (jugadorActual.cantidadCartas() != 2){
            throw new RuntimeException("El jugador debe quedarse con una unica carta para cantar UNO.");
        }

        jugadorActual.cantoUno = true;

        jugar(jugador, carta);

//        if (!jugador.equals(jugadorActual.getNombre())) {
//            throw new RuntimeException("No es el turno de este jugador");
//        }
        // esto estaria testeandose en jugar



        return this;
    }

    public Uno tomarCarta(String jugador) {
        if (!jugador.equals(jugadorActual.getNombre())) {
            throw new RuntimeException("No es el turno de este jugador");
        }

        if (!jugadorActual.cartasEnMano.stream().anyMatch(carta -> pozo.puedeApilarse(carta))) {
            // Corrobora que no puede jugar ninguna carta.
            jugadorActual.agarrarCarta(1, mazo);
        }
        else {
            throw new RuntimeException("Se debia tirar la carta habilitada");
            // Esta bien tirar un runtimeException en este caso?
        }
        return this;
    }



    public Jugador getJugadorActual(){
        return jugadorActual;
    }


}


