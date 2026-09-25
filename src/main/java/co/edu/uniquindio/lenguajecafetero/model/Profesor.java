package co.edu.uniquindio.lenguajecafetero.model;

public class Profesor extends Persona {
    private String especialidad;

    public Profesor(String nombre, String direccion, String telefono, String correo, String especialidad) {
        super(nombre, direccion, telefono, correo);
        this.especialidad = especialidad;
    }

    @Override
    public String mostrarInfo() {
        return "Profesor: " + getNombre() + " (" + especialidad + ")";
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}