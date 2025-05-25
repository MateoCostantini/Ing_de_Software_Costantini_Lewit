package Uno;

public abstract class Carta {
    //Boolean abstract validar();
    String color;

    public String getColor(){
        return color;
    }

    public int getNumero(){
        throw new RuntimeException("Esta carta no tiene numero");
    }

    public Carta asignarColor(String unColor){
        throw new RuntimeException("No se puede asignar color a esta carta");
    }

    public Uno aplicarCarta(Uno uno) { return uno; };

    public boolean puedeApilarse(Carta nuevaCarta){
        return false;
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