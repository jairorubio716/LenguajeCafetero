package co.edu.uniquindio.lenguajecafetero.exception;

public class AsignacionInvalidaException extends Exception {
    public AsignacionInvalidaException(String mensaje) {
        super(mensaje);
    }

    public AsignacionInvalidaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
