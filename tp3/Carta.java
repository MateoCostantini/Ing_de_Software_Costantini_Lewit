package Uno;

public abstract class Carta {
    //Boolean abstract validar();

    public String getColor(){
        throw new RuntimeException("Esta carta no tiene color");
    }

    public int getNumero(){
        throw new RuntimeException("Esta carta no tiene numero");
    }

    public Uno aplicarCarta(Uno uno) { return uno; };

    public Carta puedeApilarse(Carta nuevaCarta){
        throw new RuntimeException("Esta carta no puede ser apilada al mazo");
    }

    @Override
    public abstract boolean equals(Object o);

    @Override
    public abstract int hashCode();

    protected boolean teGustaColor(String color) {
        return false;
    }

    protected boolean teGustaNumero(int numero) {
        return false;
    }

    protected boolean teGustaTipo(Class<?> tipo) {
        return false;
    }
}