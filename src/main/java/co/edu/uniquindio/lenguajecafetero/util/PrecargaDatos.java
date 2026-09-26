package co.edu.uniquindio.lenguajecafetero.util;

import co.edu.uniquindio.lenguajecafetero.model.Academia;
import co.edu.uniquindio.lenguajecafetero.model.Curso;
import co.edu.uniquindio.lenguajecafetero.model.Estudiante;
import co.edu.uniquindio.lenguajecafetero.model.Idioma;
import co.edu.uniquindio.lenguajecafetero.model.Matricula;
import co.edu.uniquindio.lenguajecafetero.model.NivelIdioma;
import co.edu.uniquindio.lenguajecafetero.model.Profesor;
import co.edu.uniquindio.lenguajecafetero.model.ServicioAdicional;
import co.edu.uniquindio.lenguajecafetero.model.TipoServicio;
import co.edu.uniquindio.lenguajecafetero.model.patrones.abstractfactory.FabricaBeneficiosRegular;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PrecargaDatos {
    private static boolean cargada = false;

    private PrecargaDatos() {
    }

    public static void cargar() {
        if (cargada) {
            return;
        }
        cargada = true;

        Academia academia = Academia.getInstance();
        try {
            ServicioAdicional simulacro = new ServicioAdicional(TipoServicio.EXAMEN_CERTIFICACION,
                    "S-EX-01", "Simulacro de examen de certificacion",
                    "Simulacro tipo examen oficial", 55000, true);
            ServicioAdicional tutoria = new ServicioAdicional(TipoServicio.TUTORIA_REFUERZO,
                    "S-TU-01", "Tutoria de refuerzo",
                    "Sesion uno a uno de refuerzo", 45000, true);
            ServicioAdicional taller = new FabricaBeneficiosRegular().crearServicio();
            academia.registrarServicio(simulacro);
            academia.registrarServicio(tutoria);
            academia.registrarServicio(taller);

            Curso cursoRegular = academia.registrarCursoRegular("C-REG-01", "Ingles Regular", Idioma.INGLES,
                    "Cuatro horas semanales en grupo", 120000, 4);
            academia.registrarCursoIntensivo("C-INT-01", "Frances Intensivo", Idioma.FRANCES,
                    "Seis horas semanales con enfasis oral", 160000, 3);
            Curso cursoPersonalizado = academia.registrarCursoPersonalizado("C-PER-01",
                    "Portugues Personalizado", Idioma.PORTUGUES,
                    "Clases privadas a la medida", 180000, 2, 4, NivelIdioma.B1,
                    "Alcanzar conversacion fluida");

            Estudiante ana = new Estudiante("Ana Maria Gomez", "Cra 8 Nro 10-45, Armenia", "300 111 2233",
                    "ana.gomez@mail.com", "1005400123", 22, LocalDate.of(2024, 2, 10));
            Estudiante luis = new Estudiante("Luis Carlos Perez", "Calle 25 Nro 3-12, Armenia", "312 555 6677",
                    "luis.perez@mail.com", "1012210456", 30, LocalDate.of(2024, 3, 5));
            academia.registrarEstudiante(ana);
            academia.registrarEstudiante(luis);

            Profesor profesor = new Profesor("Carla Torres", "Av. Bolivar 15-30, Armenia", "315 999 0011",
                    "carla.torres@mail.com", "P-001", Idioma.PORTUGUES, 60000);
            academia.registrarProfesor(profesor);

            Matricula matriculaRegular = academia.crearMatricula(ana, cursoRegular, 4, 0.10,
                    new ArrayList<>(List.of(taller)));
            academia.crearMatricula(luis, cursoPersonalizado, 2, 0,
                    new ArrayList<>(List.of(simulacro)));
            academia.asignarProfesor(luis, cursoPersonalizado, profesor);
            System.out.println("Precarga de datos lista: " + matriculaRegular.getNumero());
        } catch (Exception e) {
            System.out.println("Precarga de datos: " + e.getMessage());
        }
    }
}