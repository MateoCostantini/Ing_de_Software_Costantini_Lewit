package Uno;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CartaTomaDos extends Carta{

    public CartaTomaDos(String unColor){
        color = unColor;
    }


    public Uno aplicarCarta(Uno uno) {

        List<Carta> cartasNuevas = new ArrayList<>(uno.mazo.subList(0, 2));
        uno.mazo.subList(0, 2).clear();
        uno.controlador.siguiente(uno.jugadorActual) // siguiente jugador
                .cartasEnMano.addAll(cartasNuevas);
        uno.jugadorActual = uno.controlador.siguiente(uno.jugadorActual);
        return uno;
    }

    public Carta puedeApilarse(Carta nuevaCarta){
        if (nuevaCarta.teGustaColor(this.color) || nuevaCarta.teGustaTipo(this.getClass())){
            return nuevaCarta;
        }
        else {
            throw new RuntimeException("Esta carta no puede ser apilada al mazo");
        }
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