package Uno;

import java.util.Objects;

public class CartaNumerada extends Carta{
    int numero;
    String color;

    public CartaNumerada(int unNumero, String unColor){
        numero = unNumero;
        color = unColor;
    }

    public String getColor(){
        return color;
    }

    public int getNumero(){
        return numero;
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
