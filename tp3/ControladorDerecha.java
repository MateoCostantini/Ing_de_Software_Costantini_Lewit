
package Uno;

public class ControladorDerecha extends Controlador{
    public Jugador siguiente(Jugador actual){
        return actual.derecha;
    }

    public Controlador cambiarControlador(){
        return new ControladorIzquierda();
    }
}
