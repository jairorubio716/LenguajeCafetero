package co.edu.uniquindio.lenguajecafetero.model;

public class Profesor extends Persona {
    private String identificacion;
    private Idioma idiomaEnsenado;
    private double tarifaPorSesion;

    public Profesor(String nombre, String direccion, String telefono, String correo,
                    String identificacion, Idioma idiomaEnsenado, double tarifaPorSesion) {
        super(nombre, direccion, telefono, correo);
        this.identificacion = identificacion;
        this.idiomaEnsenado = idiomaEnsenado;
        this.tarifaPorSesion = tarifaPorSesion;
    }

    @Override
    public String mostrarInfo() {
        return "Profesor: " + getNombre() + " (idioma: " + idiomaEnsenado + ")";
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public Idioma getIdiomaEnsenado() {
        return idiomaEnsenado;
    }

    public void setIdiomaEnsenado(Idioma idiomaEnsenado) {
        this.idiomaEnsenado = idiomaEnsenado;
    }

    public double getTarifaPorSesion() {
        return tarifaPorSesion;
    }

    public void setTarifaPorSesion(double tarifaPorSesion) {
        this.tarifaPorSesion = tarifaPorSesion;
    }
}