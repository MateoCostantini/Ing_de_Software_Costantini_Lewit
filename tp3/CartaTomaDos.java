package Uno;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CartaTomaDos extends Carta{

    public CartaTomaDos(String unColor){
        color = unColor;
    }


    public Uno aplicarCarta(Uno uno) {
        uno.controlador.siguiente(uno.jugadorActual).agarrarCarta(2, uno.mazo);
        uno.jugadorActual = uno.controlador.siguiente(uno.jugadorActual);
        return uno;
    }

    public boolean puedeApilarse(Carta nuevaCarta){
        return nuevaCarta.teGustaColor(this.color) || nuevaCarta.teGustaTipo(this.getClass());
    }

    protected boolean teGustaColor(String unColor) {
        return unColor.equals(this.color);
    }

    protected boolean teGustaTipo(Class<?> clase) {
        return this.getClass() == clase;
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