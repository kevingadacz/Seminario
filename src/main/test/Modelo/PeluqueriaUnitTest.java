package Modelo;

import Modelo.Cliente.Cliente;
import Modelo.Peluqueria.Peluqueria;
import Modelo.Servicio.Servicio;
import Modelo.Turno.Turno;
import org.junit.Assert;
import org.junit.Test;
import java.time.LocalDateTime;

public class PeluqueriaUnitTest {
    @Test
    public void Peluqueria_ParametrosValidos_SeGeneraUnaPeluqueriaValida() {
        Peluqueria peluqueria = new Peluqueria("" +
                "Una Peluqueria","Calle falsa 123","123456789","peluqueria@gmail.com");
        Assert.assertEquals("Una Peluqueria", peluqueria.getNombre());
        Assert.assertEquals("Calle falsa 123", peluqueria.getDireccion());
        Assert.assertEquals("123456789", peluqueria.getTelefono());
        Assert.assertEquals("peluqueria@gmail.com", peluqueria.getMail());
    }

    @Test
    public void SolicitarTurno_ElMismoClienteSolicitaElMismoTurnoDosVeces_SeLanzaExceptionPorTurnoOcupado() {
        Cliente cliente = new Cliente("Juan", "Paso", "JPaso@fi.uba.ar", "11330404");
        LocalDateTime fecha = LocalDateTime.now();
        Peluqueria peluqueria = new Peluqueria("Una peluqueria","Calle falsa 123","1234567","asd@a.com");
        Servicio servicio = new Servicio(5,"Corte de pelo");
        try {
            Turno turno = cliente.solicitarTurno(fecha, peluqueria, servicio);
            Turno turno2 = cliente.solicitarTurno(fecha, peluqueria, servicio);
            Assert.fail();
        }
        catch (Exception ex) {
            Assert.assertTrue(true);
        }
    }
}