package co.edu.uniquindio.lenguajecafetero.exception;

public class ServicioNoDisponibleException extends Exception {
    public ServicioNoDisponibleException(String mensaje) {
        super(mensaje);
    }

    public ServicioNoDisponibleException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
