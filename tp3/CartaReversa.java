package Uno;

import java.util.Objects;

public class CartaReversa extends Carta{

    public CartaReversa(String unColor){
        color = unColor;
    }


    public Uno aplicarCarta(Uno uno) {
        uno.controlador = uno.controlador.cambiarControlador();
        return uno;
    }


    public boolean puedeApilarse(Carta nuevaCarta){
        return (nuevaCarta.teGustaColor(this.color) || nuevaCarta.teGustaTipo(this.getClass()));
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
        if (!(o instanceof CartaReversa)) return false;
        CartaReversa otra = (CartaReversa) o;
        return color.equals(otra.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}