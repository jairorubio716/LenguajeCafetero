package co.edu.uniquindio.lenguajecafetero.model;

import java.time.LocalDate;

public class Estudiante extends Persona {
    private String documentoIdentidad;
    private int edad;
    private LocalDate fechaNacimiento;
    private NivelIdioma nivel;

    public Estudiante(String nombre, String direccion, String telefono, String correo,
                      String documentoIdentidad, int edad, LocalDate fechaNacimiento, NivelIdioma nivel) {
        super(nombre, direccion, telefono, correo);
        this.documentoIdentidad = documentoIdentidad;
        this.edad = edad;
        this.fechaNacimiento = fechaNacimiento;
        this.nivel = nivel;
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

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public NivelIdioma getNivel() {
        return nivel;
    }

    public void setNivel(NivelIdioma nivel) {
        this.nivel = nivel;
    }
}