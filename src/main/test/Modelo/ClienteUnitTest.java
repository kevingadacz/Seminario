package Modelo;

import Modelo.EstadoTurno.Finalizado;
import Modelo.Peluqueria.Peluqueria;
import Modelo.Servicio.Servicio;
import Modelo.Turno.Turno;
import org.junit.Assert;
import org.junit.Test;

import Modelo.Cliente.Cliente;

import java.time.LocalDateTime;

public class ClienteUnitTest {
    @Test
    public void Cliente_ParametrosValidos_SeGeneraunClienteValido() {
        Cliente cliente = new Cliente("Juan", "Paso", "JPaso@fi.uba.ar", "11330404");
        Assert.assertEquals("Juan", cliente.getNombre());
        Assert.assertEquals("Paso", cliente.getApellido());
        Assert.assertEquals("JPaso@fi.uba.ar", cliente.getMail());
        Assert.assertEquals("11330404", cliente.getTelefono());
    }

    @Test
    public void SolicitarTurno_SeSolicitaUnTurnoDisponible_SeGeneraElTurnoSolicitado() {
        Cliente cliente = new Cliente("Juan", "Paso", "JPaso@fi.uba.ar", "11330404");
        LocalDateTime fecha = LocalDateTime.now();
        Peluqueria peluqueria = new Peluqueria("Una peluqueria","Calle falsa 123","1234567","asd@a.com");
        Servicio servicio = new Servicio(5,"Corte de pelo");
        try {
            Turno turno = cliente.solicitarTurno(fecha, peluqueria, servicio);
            Assert.assertEquals(cliente.getTurnos().get(0), turno);
        }
        catch (Exception ex) {
            Assert.fail();
        }
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

    @Test
    public void SolicitarTurno_DosClientesSolicitanElMismoTurno_SeLanzaExceptionPorTurnoOcupado() {
        Cliente cliente = new Cliente("Juan", "Paso", "JPaso@fi.uba.ar", "11330404");
        Cliente cliente2 = new Cliente("Otro", "Paso", "otroPaso@fi.uba.ar", "1235342");
        LocalDateTime fecha = LocalDateTime.now();
        Peluqueria peluqueria = new Peluqueria("Una peluqueria","Calle falsa 123","1234567","asd@a.com");
        Servicio servicio = new Servicio(5,"Corte de pelo");
        try {
            Turno turno = cliente.solicitarTurno(fecha, peluqueria, servicio);
            Turno turno2 = cliente2.solicitarTurno(fecha, peluqueria, servicio);
            Assert.fail();
        }
        catch (Exception ex) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void AsistirAlTurno_UnClienteSolicitoUnTurnoEnUnaPeluqueria_ElTurnoCambiaDeEstadoAFinalizado() {
        Cliente cliente = new Cliente("Juan", "Paso", "JPaso@fi.uba.ar", "11330404");
        LocalDateTime fecha = LocalDateTime.now();
        Peluqueria peluqueria = new Peluqueria("Una peluqueria","Calle falsa 123","1234567","asd@a.com");
        Servicio servicio = new Servicio(5,"Corte de pelo");
        try {
            Turno turno = cliente.solicitarTurno(fecha, peluqueria, servicio);
            cliente.asistirAlTurno(turno);
            Assert.assertTrue(turno.getEstadoTurno() instanceof Finalizado);
        }
        catch (Exception ex) {
            Assert.fail();
        }
    }
}
