package co.edu.uniquindio.lenguajecafetero.model;

import java.time.LocalDate;

public class ServicioUtilizado {
    private LocalDate fecha;
    private Matricula matricula;
    private ServicioAdicional servicio;

    public ServicioUtilizado(LocalDate fecha, Matricula matricula, ServicioAdicional servicio) {
        this.fecha = fecha;
        this.matricula = matricula;
        this.servicio = servicio;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public ServicioAdicional getServicio() {
        return servicio;
    }

    public void setServicio(ServicioAdicional servicio) {
        this.servicio = servicio;
    }
}