package Uno;

import java.util.Objects;

public class CartaComodin extends Carta{

    public Carta asignarColor(String unColor){
        color = unColor;
        return this;
    }

    public Carta puedeApilarse(Carta nuevaCarta){
        if (nuevaCarta.teGustaColor(this.color) ){
            return nuevaCarta;
        }
        else {
            throw new RuntimeException("Esta carta no puede ser apilada al mazo");
        }
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