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

    @Override
    public abstract boolean equals(Object o);

    @Override
    public abstract int hashCode();
}


