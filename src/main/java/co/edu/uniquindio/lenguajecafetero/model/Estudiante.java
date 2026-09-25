package co.edu.uniquindio.lenguajecafetero.model;

public class Estudiante extends Persona {
    private String documentoIdentidad;
    private int edad;
    private String nivelActual;

    public Estudiante(String nombre, String direccion, String telefono, String correo,
                      String documentoIdentidad, int edad, String nivelActual) {
        super(nombre, direccion, telefono, correo);
        this.documentoIdentidad = documentoIdentidad;
        this.edad = edad;
        this.nivelActual = nivelActual;
    }

    @Override
    public String mostrarInfo() {
        return "Estudiante: " + getNombre() + " (CC " + documentoIdentidad + ")";
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNivelActual() {
        return nivelActual;
    }

    public void setNivelActual(String nivelActual) {
        this.nivelActual = nivelActual;
    }
}