package Uno;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CartaTomaDos extends Carta{
    String color;

    public CartaTomaDos(String unColor){
        color = unColor;
    }

    public String getColor(){
        return color;
    }

    public Uno aplicarCarta(Uno uno) {

        List<Carta> cartasNuevas = new ArrayList<>(uno.mazo.subList(0, 2));
        uno.mazo.subList(0, 2).clear();
        uno.jugadorActual.derecha.cartasEnMano.addAll(cartasNuevas);
        uno.jugadorActual = uno.jugadorActual.derecha;
        return uno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartaTomaDos)) return false;
        CartaTomaDos otra = (CartaTomaDos) o;
        return color.equals(otra.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
