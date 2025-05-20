package Uno;

import java.util.HashSet;
import java.util.List;

public class Jugador {
    String nombre;
    HashSet<Carta> cartasEnMano = new HashSet<>();
    Jugador derecha;
    Jugador izquierda;

    public Jugador(String nombre, List<Carta> cartas){
        this.nombre = nombre;
        cartasEnMano.addAll(cartas);
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
}