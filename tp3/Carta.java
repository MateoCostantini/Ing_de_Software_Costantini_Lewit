package Uno;

public class Carta {
    //Boolean abstract validar();

    public String getColor(){
        throw new RuntimeException("Esta carta no tiene color");
    }

    public int getNumero(){
        throw new RuntimeException("Esta carta no tiene numero");
    }
}

class CartaNumerada extends Carta{
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

}

class CartaTomaDos extends Carta{
    String color;

    public CartaTomaDos(String unColor){
        color = unColor;
    }

    public String getColor(){
        return color;
    }
}

class CartaReversa extends Carta{
    String color;

    public CartaReversa(String unColor){
        color = unColor;
    }

    public String getColor(){
        return color;
    }
}

class CartaSalta extends Carta{
    String color;

    public CartaSalta(String unColor){
        color = unColor;
    }

    public String getColor(){
        return color;
    }
}

class CartaComodin extends Carta{

}