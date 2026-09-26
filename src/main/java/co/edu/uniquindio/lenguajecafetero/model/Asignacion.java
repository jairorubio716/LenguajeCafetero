package co.edu.uniquindio.lenguajecafetero.model;

import java.time.LocalDate;

public class Asignacion {
    private LocalDate fechaAsignacion;
    private Estudiante estudiante;
    private Curso curso;
    private Profesor profesor;

    public Asignacion(LocalDate fechaAsignacion, Estudiante estudiante, Curso curso, Profesor profesor) {
        this.fechaAsignacion = fechaAsignacion;
        this.estudiante = estudiante;
        this.curso = curso;
        this.profesor = profesor;
    }

    @Override
    public String toString() {
        return fechaAsignacion + " - " + estudiante.getNombre() + " - " + curso.getNombre() + " - " + profesor.getNombre();
    }

    public LocalDate getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(LocalDate fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
}