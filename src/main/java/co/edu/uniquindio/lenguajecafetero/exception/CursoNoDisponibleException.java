package co.edu.uniquindio.lenguajecafetero.exception;

public class CursoNoDisponibleException extends Exception {
    public CursoNoDisponibleException(String mensaje) {
        super(mensaje);
    }

    public CursoNoDisponibleException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
