package Uno;

public class CartaReversa extends Carta{
    String color;

    public CartaReversa(String unColor){
        color = unColor;
    }

    public String getColor(){
        return color;
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
