package test;

import static org.junit.Assert.assertEquals;
import java.time.LocalDate;
import org.junit.Test;
import modelos.AdminDeposito;



public class DefinirFechaEntregaTest {

    @Test
    public void definirFechaEntregaNullFecha() {
        boolean resultado = AdminDeposito.definirFechaEntrega(1, null);
        assertEquals(false, resultado);
    }

    @Test
    public void definirFechaEntregaFechaAnterior() {
        boolean resultado = AdminDeposito.definirFechaEntrega(1, LocalDate.of(2024, 02, 03));
        assertEquals(false, resultado);
    }

    @Test
    public void definirFechaEntregaExitoso() {

        boolean resultado = AdminDeposito.definirFechaEntrega(1, LocalDate.of(2024, 07, 03));
        assertEquals(true, resultado);
    }

    @Test
    public void definirFechaidPedidoNoExiste() {

        boolean resultado = AdminDeposito.definirFechaEntrega(99, LocalDate.of(2024, 06, 03));
        assertEquals(false, resultado);
    }

}