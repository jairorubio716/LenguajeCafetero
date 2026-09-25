package co.edu.uniquindio.lenguajecafetero.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Curso implements CursoPrototipo {
    private String codigo;
    private String nombre;
    private Idioma idioma;
    private String descripcion;
    private double valorMensual;
    private int duracionMeses;
    private List<Beneficio> beneficios;
    private EstadoCurso estado;

    public Curso(String codigo, String nombre, Idioma idioma, String descripcion,
                 double valorMensual, int duracionMeses) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.idioma = idioma;
        this.descripcion = descripcion;
        this.valorMensual = valorMensual;
        this.duracionMeses = duracionMeses;
        this.beneficios = new ArrayList<>();
        this.estado = EstadoCurso.ACTIVO;
    }

    public Curso(Curso curso) {
        this(curso.codigo, curso.nombre, curso.idioma, curso.descripcion,
                curso.valorMensual, curso.duracionMeses);
        this.beneficios = new ArrayList<>(curso.beneficios);
        this.estado = curso.estado;
    }

    public abstract double calcularValor();

    public void agregarBeneficio(Beneficio beneficio) {
        beneficios.add(beneficio);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getValorMensual() {
        return valorMensual;
    }

    public void setValorMensual(double valorMensual) {
        this.valorMensual = valorMensual;
    }

    public int getDuracionMeses() {
        return duracionMeses;
    }

    public void setDuracionMeses(int duracionMeses) {
        this.duracionMeses = duracionMeses;
    }

    public List<Beneficio> getBeneficios() {
        return beneficios;
    }

    public EstadoCurso getEstado() {
        return estado;
    }

    public void setEstado(EstadoCurso estado) {
        this.estado = estado;
    }
}