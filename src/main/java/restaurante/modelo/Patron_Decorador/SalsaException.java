package restaurante.modelo.Patron_Decorador;

public class SalsaException extends Exception{
    public SalsaException(){
        super("El plato ya tiene una salsa");
    }
}
