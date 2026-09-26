package co.edu.uniquindio.lenguajecafetero.model;

import co.edu.uniquindio.lenguajecafetero.model.patrones.abstractfactory.FabricaBeneficiosIntensivo;
import co.edu.uniquindio.lenguajecafetero.model.patrones.abstractfactory.FabricaBeneficiosPersonalizado;
import co.edu.uniquindio.lenguajecafetero.model.patrones.abstractfactory.FabricaBeneficiosRegular;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FabricaBeneficiosTest {

    @Test
    void fabricaRegularFamiliaCorrecta() {
        FabricaBeneficiosRegular fabrica = new FabricaBeneficiosRegular();
        List<Beneficio> beneficios = fabrica.crearBeneficios();
        assertEquals(2, beneficios.size());
        assertEquals(TipoBeneficio.MATERIAL_DIDACTICO, beneficios.get(0).getTipo());
        assertEquals(TipoBeneficio.CLUBES_CONVERSACION, beneficios.get(1).getTipo());
        ServicioAdicional servicio = fabrica.crearServicio();
        assertEquals(TipoServicio.TALLERES_CONVERSACION, servicio.getTipo());
        assertEquals("S-REG-01", servicio.getCodigo());
        assertTrue(servicio.isDisponible());
    }

    @Test
    void fabricaIntensivoFamiliaCorrecta() {
        FabricaBeneficiosIntensivo fabrica = new FabricaBeneficiosIntensivo();
        List<Beneficio> beneficios = fabrica.crearBeneficios();
        assertEquals(2, beneficios.size());
        assertEquals(TipoBeneficio.PLATAFORMA_VIRTUAL, beneficios.get(0).getTipo());
        assertEquals(TipoBeneficio.MATERIAL_DIDACTICO, beneficios.get(1).getTipo());
        ServicioAdicional servicio = fabrica.crearServicio();
        assertEquals(TipoServicio.TUTORIA_REFUERZO, servicio.getTipo());
        assertEquals("S-INT-01", servicio.getCodigo());
    }

    @Test
    void fabricaPersonalizadoFamiliaCorrecta() {
        FabricaBeneficiosPersonalizado fabrica = new FabricaBeneficiosPersonalizado();
        List<Beneficio> beneficios = fabrica.crearBeneficios();
        assertEquals(2, beneficios.size());
        assertEquals(TipoBeneficio.PLATAFORMA_VIRTUAL, beneficios.get(0).getTipo());
        assertEquals(TipoBeneficio.CLUBES_CONVERSACION, beneficios.get(1).getTipo());
        ServicioAdicional servicio = fabrica.crearServicio();
        assertEquals(TipoServicio.EXAMEN_CERTIFICACION, servicio.getTipo());
        assertEquals("S-PER-01", servicio.getCodigo());
    }
}
