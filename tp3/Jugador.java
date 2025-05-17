package Uno;

import java.util.HashSet;
import java.util.List;

public abstract class Jugador {


    //void abstract jugar();

}

class JugadorVacio extends Jugador{

}

class JugadorReal extends Jugador{
    String nombre;
    HashSet<Carta> cartasEnMano = new HashSet<>();

    public JugadorReal(String nombre, List<Carta> cartas){
        this.nombre = nombre;
        cartasEnMano.addAll(cartas);
    }


}
