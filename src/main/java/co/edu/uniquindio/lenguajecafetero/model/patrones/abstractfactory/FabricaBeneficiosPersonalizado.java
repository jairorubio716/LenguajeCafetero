package co.edu.uniquindio.lenguajecafetero.model.patrones.abstractfactory;

import co.edu.uniquindio.lenguajecafetero.model.Beneficio;
import co.edu.uniquindio.lenguajecafetero.model.ServicioAdicional;
import co.edu.uniquindio.lenguajecafetero.model.TipoBeneficio;
import co.edu.uniquindio.lenguajecafetero.model.TipoServicio;

import java.util.ArrayList;
import java.util.List;

public class FabricaBeneficiosPersonalizado implements FabricaBeneficios {
    @Override
    public List<Beneficio> crearBeneficios() {
        List<Beneficio> beneficios = new ArrayList<>();
        beneficios.add(new Beneficio(TipoBeneficio.PLATAFORMA_VIRTUAL,
                "Acceso a plataforma virtual", "Material en linea disponible 24/7"));
        beneficios.add(new Beneficio(TipoBeneficio.CLUBES_CONVERSACION,
                "Club de conversacion premium", "Sesiones adicionales con nativos"));
        return beneficios;
    }

    @Override
    public ServicioAdicional crearServicio() {
        return new ServicioAdicional(TipoServicio.EXAMEN_CERTIFICACION, "S-PER-01",
                "Simulacro de examen de certificacion", "Simulacro IELTS/TOEFL con retroalimentacion", 90000, true);
    }
}