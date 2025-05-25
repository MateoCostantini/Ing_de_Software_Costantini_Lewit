package Uno;

import java.util.Objects;

public class CartaNumerada extends Carta{
    int numero;

    public CartaNumerada(int unNumero, String unColor){
        numero = unNumero;
        color = unColor;
    }



    public int getNumero(){
        return numero;
    }

    public boolean puedeApilarse(Carta nuevaCarta){
        return nuevaCarta.teGustaColor(this.color) || nuevaCarta.teGustaNumero(this.numero);
    }

    protected boolean teGustaColor(String unColor) {
        return unColor.equals(this.color);
    }

    protected boolean teGustaNumero(int unNumero) {
        return this.numero == unNumero;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartaNumerada)) return false;
        CartaNumerada otra = (CartaNumerada) o;
        return numero == otra.numero && color.equals(otra.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, color); // hace falta redefinir hash?
    }

}