package co.edu.uniquindio.lenguajecafetero.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Matricula {
    private String numero;
    private LocalDate fecha;
    private int duracionContratada;
    private double descuentoAplicado;
    private double valorFinal;
    private Estudiante estudiante;
    private Curso curso;
    private List<ServicioAdicional> serviciosIncluidos;

    public Matricula(String numero, LocalDate fecha, Estudiante estudiante, Curso curso,
                     int duracionContratada, double descuentoAplicado,
                     List<ServicioAdicional> serviciosIncluidos) {
        this.numero = numero;
        this.fecha = fecha;
        this.estudiante = estudiante;
        this.curso = curso;
        this.duracionContratada = duracionContratada;
        this.descuentoAplicado = descuentoAplicado;
        this.serviciosIncluidos = new ArrayList<>();
        if (serviciosIncluidos != null) {
            this.serviciosIncluidos.addAll(serviciosIncluidos);
        }
        calcularValorFinal();
    }

    public void calcularValorFinal() {
        double valorCurso = curso.calcularValor() / (double) curso.getDuracionMeses() * duracionContratada;
        double valorServicios = 0;
        for (ServicioAdicional servicio : serviciosIncluidos) {
            valorServicios += servicio.getPrecio();
        }
        double subtotal = valorCurso + valorServicios;
        this.valorFinal = subtotal - (subtotal * descuentoAplicado);
    }

    public void agregarServicioIncluido(ServicioAdicional servicio) {
        serviciosIncluidos.add(servicio);
        calcularValorFinal();
    }

    @Override
    public String toString() {
        return numero + " - " + estudiante.getNombre() + " - " + curso.getNombre() + " - $" + valorFinal;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getDuracionContratada() {
        return duracionContratada;
    }

    public void setDuracionContratada(int duracionContratada) {
        this.duracionContratada = duracionContratada;
        calcularValorFinal();
    }

    public double getDescuentoAplicado() {
        return descuentoAplicado;
    }

    public void setDescuentoAplicado(double descuentoAplicado) {
        this.descuentoAplicado = descuentoAplicado;
        calcularValorFinal();
    }

    public double getValorFinal() {
        return valorFinal;
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
        calcularValorFinal();
    }

    public List<ServicioAdicional> getServiciosIncluidos() {
        return serviciosIncluidos;
    }
}