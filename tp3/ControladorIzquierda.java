package Uno;

public class ControladorIzquierda extends Controlador{
    public Jugador siguiente(Jugador actual){
        return actual.izquierda;
    }

    public Controlador cambiarControlador(){
        return new ControladorDerecha();
    }
}
