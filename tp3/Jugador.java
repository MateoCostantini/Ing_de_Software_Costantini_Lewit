package Uno;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Jugador {
    String nombre;
    List<Carta> cartasEnMano = new ArrayList<>();
    Jugador derecha;
    Jugador izquierda;
    boolean cantoUno = false;

    public Jugador(String nombre){
        this.nombre = nombre;
        //cartasEnMano.addAll(cartas);
    }

    public String getNombre(){
        return this.nombre;
    }

    public boolean tieneCarta(Carta carta){
        return cartasEnMano.contains(carta);
    }

    public int cantidadCartas(){
        return cartasEnMano.size();
    }

    public Jugador agarrarCarta(int cantidad, List<Carta> mazo){
        List<Carta> cartasNuevas = new ArrayList<>(mazo.subList(0, cantidad));
        mazo.subList(0, cantidad).clear();
        this.cartasEnMano.addAll(cartasNuevas);

        if (this.cantidadCartas() > 1){
            this.cantoUno = false;
        }
        return this;
    }

    public boolean getCantoUno(){
        return cantoUno;
    }

}

