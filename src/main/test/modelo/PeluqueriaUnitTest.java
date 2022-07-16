package modelo;

import modelo.Cliente.Cliente;
import modelo.EstadoTurno.Cancelado;
import modelo.EstadoTurno.Solicitado;
import modelo.Peluqueria.Peluqueria;
import modelo.Servicio.Servicio;
import modelo.Turno.Turno;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
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
        Servicio servicio = new Servicio(5,"Corte de pelo",new BigDecimal(700));
        peluqueria.agregarServicio(servicio);
        try {
            peluqueria.solicitarTurno(cliente, fecha, peluqueria, servicio);
            peluqueria.solicitarTurno(cliente, fecha, peluqueria, servicio);
            Assert.fail();
        }
        catch (Exception ex) {
            Assert.assertEquals(ex.getMessage(),"Turno no disponible");
        }
    }

    @Test
    public void CancelarElTurno_LaPeluqueriaTieneUnTurnoQueQuiereCancelar_ElTurnoCambiaDeEstadoACancelado() {
        Cliente cliente = new Cliente("Juan", "Paso", "JPaso@fi.uba.ar", "11330404");
        LocalDateTime fecha = LocalDateTime.now();
        Peluqueria peluqueria = new Peluqueria("Una peluqueria","Calle falsa 123","1234567","asd@a.com");
        Servicio servicio = new Servicio(5,"Corte de pelo",new BigDecimal(700));
        peluqueria.agregarServicio(servicio);
        try {
            Turno turno = cliente.solicitarTurno(fecha, peluqueria,servicio);
            peluqueria.cancelarTurno(turno);
            Assert.assertTrue(turno.getEstado() instanceof Cancelado);
            Assert.assertTrue(buscarTurnoDelCliente(turno, cliente).getEstado() instanceof Cancelado);
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
        Servicio servicio = new Servicio(30,"Corte de pelo",new BigDecimal(700));
        peluqueria.agregarServicio(servicio);
        try {
            peluqueria.solicitarTurno(cliente, fecha1, peluqueria, servicio);
            peluqueria.solicitarTurno(cliente2, fecha2, peluqueria, servicio);
            Assert.fail();
        }
        catch (Exception ex) {
            Assert.assertEquals(ex.getMessage(),"Turno no disponible");
        }
    }

    @Test
    public void SolicitarTurno_PeluqueriaTieneUnTurnoYSeLeSolicitaOtroEnUnHorarioDiferente_SeGeneraElTurnoSolicitado() {
        Cliente cliente = new Cliente("Juan", "Paso", "JPaso@fi.uba.ar", "11330404");
        Cliente cliente2 = new Cliente("Otro", "Paso", "otroPaso@fi.uba.ar", "1235342");
        LocalDateTime fecha1 = LocalDateTime.now();
        LocalDateTime fecha2 = LocalDateTime.now().plusMinutes(40);
        Peluqueria peluqueria = new Peluqueria("Una peluqueria","Calle falsa 123","1234567","asd@a.com");
        Servicio servicio = new Servicio(30,"Corte de pelo",new BigDecimal(700));
        peluqueria.agregarServicio(servicio);
        try {
            peluqueria.solicitarTurno(cliente, fecha1, peluqueria, servicio);
            Turno segundoTurno = peluqueria.solicitarTurno(cliente2, fecha2, peluqueria, servicio);
            Assert.assertEquals(segundoTurno.getCliente(),cliente2);
            Assert.assertEquals(segundoTurno.getDia(),fecha2);
            Assert.assertEquals(segundoTurno.getServicio(),servicio);
            Assert.assertEquals(segundoTurno.getPeluqueria(),peluqueria);
        }
        catch (Exception ex) {
            Assert.fail();
        }
    }

    @Test
    public void SolicitarTurno_PeluqueriaTieneUnTurnoQueEstaCanceladoYSeLeSolicitaOtroEnElMismoHorario_SeGeneraElTurnoSolicitado() {
        Cliente cliente = new Cliente("Juan", "Paso", "JPaso@fi.uba.ar", "11330404");
        Cliente cliente2 = new Cliente("Otro", "Paso", "otroPaso@fi.uba.ar", "1235342");
        LocalDateTime fecha1 = LocalDateTime.now();
        Peluqueria peluqueria = new Peluqueria("Una peluqueria","Calle falsa 123","1234567","asd@a.com");
        Servicio servicio = new Servicio(30,"Corte de pelo",new BigDecimal(700));
        peluqueria.agregarServicio(servicio);
        try {
            Turno primerTurno = peluqueria.solicitarTurno(cliente, fecha1, peluqueria, servicio);
            cliente.cancelarTurno(primerTurno);
            Turno segundoTurno = peluqueria.solicitarTurno(cliente2, fecha1, peluqueria, servicio);
            Assert.assertTrue(segundoTurno.getEstado() instanceof Solicitado);
        }
        catch (Exception ex) {
            Assert.fail();
        }
    }
}