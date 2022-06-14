package Modelo;

import Modelo.Cliente.Cliente;
import Modelo.EstadoTurno.Cancelado;
import Modelo.Peluqueria.Peluqueria;
import Modelo.Servicio.Servicio;
import Modelo.Turno.Turno;
import org.junit.Assert;
import org.junit.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class PeluqueriaUnitTest {

    private Turno buscarTurnoDelCliente(Turno turno, Cliente cliente) throws Exception {
        ArrayList<Turno> turnos = cliente.getTurnos();
        for (Turno unTurno : turnos) {
            if (turno == unTurno) return unTurno;
        }
        throw new Exception("El cliente no posee ese turno");
    }
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
            Turno turno = new Turno(cliente, fecha,peluqueria,servicio);
            peluqueria.solicitarTurno(turno);
            peluqueria.solicitarTurno(turno);
            Assert.fail();
        }
        catch (Exception ex) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void CancelarElTurno_LaPeluqueriaTieneUnTurnoQueQuiereCancelar_ElTurnoCambiaDeEstadoACancelado() {
        Cliente cliente = new Cliente("Juan", "Paso", "JPaso@fi.uba.ar", "11330404");
        LocalDateTime fecha = LocalDateTime.now();
        Peluqueria peluqueria = new Peluqueria("Una peluqueria","Calle falsa 123","1234567","asd@a.com");
        Servicio servicio = new Servicio(5,"Corte de pelo");
        try {
            Turno turno = cliente.solicitarTurno(fecha, peluqueria,servicio);
            peluqueria.cancelarTurno(turno);
            Assert.assertTrue(turno.getEstadoTurno() instanceof Cancelado);
            Assert.assertTrue(buscarTurnoDelCliente(turno, cliente).getEstadoTurno() instanceof Cancelado);
        }
        catch (Exception ex) {
            Assert.fail();
        }
    }

    @Test
    public void SolicitarTurno_PeluqueriaTieneUnTurnoYSeLeSolicitaOtroEnElMismoHorario_SeLanzaExceptionPorTurnoOcupado() {
        Cliente cliente = new Cliente("Juan", "Paso", "JPaso@fi.uba.ar", "11330404");
        Cliente cliente2 = new Cliente("Otro", "Paso", "otroPaso@fi.uba.ar", "1235342");
        LocalDateTime fecha1 = LocalDateTime.now();
        LocalDateTime fecha2 = LocalDateTime.now().plusMinutes(15);
        Peluqueria peluqueria = new Peluqueria("Una peluqueria","Calle falsa 123","1234567","asd@a.com");
        Servicio servicio = new Servicio(30,"Corte de pelo");
        try {
            Turno turno1 = new Turno(cliente, fecha1,peluqueria,servicio);
            peluqueria.solicitarTurno(turno1);
            Turno turno2 = new Turno(cliente2, fecha2,peluqueria,servicio);
            peluqueria.solicitarTurno(turno2);
            Assert.fail();
        }
        catch (Exception ex) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void SolicitarTurno_PeluqueriaTieneUnTurnoYSeLeSolicitaOtroEnUnHorarioDiferente_SeGeneraElTurnoSolicitado() {
        Cliente cliente = new Cliente("Juan", "Paso", "JPaso@fi.uba.ar", "11330404");
        Cliente cliente2 = new Cliente("Otro", "Paso", "otroPaso@fi.uba.ar", "1235342");
        LocalDateTime fecha1 = LocalDateTime.now();
        LocalDateTime fecha2 = LocalDateTime.now().plusMinutes(40);
        Peluqueria peluqueria = new Peluqueria("Una peluqueria","Calle falsa 123","1234567","asd@a.com");
        Servicio servicio = new Servicio(30,"Corte de pelo");
        try {
            Turno turno1 = new Turno(cliente, fecha1,peluqueria,servicio);
            peluqueria.solicitarTurno(turno1);
            Turno turno2 = new Turno(cliente2, fecha2,peluqueria,servicio);
            peluqueria.solicitarTurno(turno2);
        }
        catch (Exception ex) {
            Assert.fail();
        }
    }
}