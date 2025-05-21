package Uno;

public class CartaReversa extends Carta{
    String color;

    public CartaReversa(String unColor){
        color = unColor;
    }

    public String getColor(){
        return color;
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
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}