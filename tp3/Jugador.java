package Uno;

import java.util.HashSet;
import java.util.List;

public abstract class Jugador {
    public abstract String getNombre();
    public abstract boolean tieneCarta(Carta carta);


    //void abstract jugar();

}

class JugadorVacio extends Jugador{
    public String getNombre() {
        return "Vacio"; // no se si iria asi
    }

    public boolean tieneCarta(Carta carta){
        return false;
    }
}

class JugadorReal extends Jugador{
    String nombre;
    HashSet<Carta> cartasEnMano = new HashSet<>();

    public JugadorReal(String nombre, List<Carta> cartas){
        this.nombre = nombre;
        cartasEnMano.addAll(cartas);
    }

    public String getNombre(){
        return this.nombre;
    }

    public boolean tieneCarta(Carta carta){
        return cartasEnMano.contains(carta);
    }


}
