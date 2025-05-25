package Uno;

import java.util.Objects;

public class CartaComodin extends Carta{

    public Carta asignarColor(String unColor){
        color = unColor;
        return this;
    }

    public boolean puedeApilarse(Carta nuevaCarta){
        return (nuevaCarta.teGustaColor(this.color) );
    }

    protected boolean teGustaColor(String unColor) {
        return true;
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CartaComodin)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash("Comodin");
    }
}