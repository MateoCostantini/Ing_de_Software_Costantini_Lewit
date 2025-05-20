package Uno;

import java.util.Objects;

public class CartaSalta extends Carta{
    String color;

    public CartaSalta(String unColor){
        color = unColor;
    }

    public String getColor(){
        return color;
    }

    public Uno aplicarCarta(Uno uno) {
        uno.jugadorActual = uno.jugadorActual.derecha;
        return uno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartaSalta)) return false;
        CartaSalta otra = (CartaSalta) o;
        return color.equals(otra.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
