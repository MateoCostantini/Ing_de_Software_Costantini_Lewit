package Uno;

public class CartaComodin extends Carta{
    String color;

    // Que el aplicar carta le agregue el color que se decide.


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
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}