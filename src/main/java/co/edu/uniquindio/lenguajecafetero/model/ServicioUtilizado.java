package co.edu.uniquindio.lenguajecafetero.model;

import java.time.LocalDate;

public class ServicioUtilizado {
    private LocalDate fecha;
    private Estudiante estudiante;
    private ServicioAdicional servicio;

    public ServicioUtilizado(LocalDate fecha, Estudiante estudiante, ServicioAdicional servicio) {
        this.fecha = fecha;
        this.estudiante = estudiante;
        this.servicio = servicio;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public ServicioAdicional getServicio() {
        return servicio;
    }

    public void setServicio(ServicioAdicional servicio) {
        this.servicio = servicio;
    }
}