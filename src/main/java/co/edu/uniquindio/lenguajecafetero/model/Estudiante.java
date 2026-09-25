package co.edu.uniquindio.lenguajecafetero.model;

import java.time.LocalDate;

public class Estudiante extends Persona {
    private String documentoIdentidad;
    private int edad;
    private LocalDate fechaRegistro;

    public Estudiante(String nombre, String direccion, String telefono, String correo,
                      String documentoIdentidad, int edad, LocalDate fechaRegistro) {
        super(nombre, direccion, telefono, correo);
        this.documentoIdentidad = documentoIdentidad;
        this.edad = edad;
        this.fechaRegistro = fechaRegistro;
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

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}