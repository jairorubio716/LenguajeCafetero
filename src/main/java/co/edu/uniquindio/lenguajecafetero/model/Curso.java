package co.edu.uniquindio.lenguajecafetero.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Curso implements CursoPrototipo {
    private String codigo;
    private String nombre;
    private double precioBase;
    private int duracionMeses;
    private List<Beneficio> beneficios;

    public Curso(String codigo, String nombre, double precioBase, int duracionMeses) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.duracionMeses = duracionMeses;
        this.beneficios = new ArrayList<>();
    }

    public Curso(Curso curso) {
        this(curso.codigo, curso.nombre, curso.precioBase, curso.duracionMeses);
        this.beneficios = new ArrayList<>(curso.beneficios);
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

    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
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
}