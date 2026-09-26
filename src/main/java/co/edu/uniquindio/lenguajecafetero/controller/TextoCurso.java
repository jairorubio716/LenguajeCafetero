package co.edu.uniquindio.lenguajecafetero.controller;

import co.edu.uniquindio.lenguajecafetero.model.Curso;

import java.util.stream.Collectors;

public final class TextoCurso {

    private TextoCurso() {
    }

    public static String conBeneficios(Curso curso) {
        String base = curso.getCodigo() + " - " + curso.getNombre() + " - " + curso.getIdioma()
                + " - $" + curso.calcularValor() + " - " + curso.getEstado();
        String beneficios = beneficiosDe(curso);
        return beneficios.isEmpty() ? base : base + "  |  Beneficios: " + beneficios;
    }

    public static String beneficiosDe(Curso curso) {
        if (curso.getBeneficios().isEmpty()) {
            return "";
        }
        return curso.getBeneficios().stream()
                .map(beneficio -> beneficio.getNombre())
                .collect(Collectors.joining(", "));
    }
}