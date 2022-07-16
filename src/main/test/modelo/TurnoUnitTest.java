package modelo;

import modelo.Cliente.Cliente;
import modelo.EstadoTurno.Ausentado;
import modelo.EstadoTurno.Cancelado;
import modelo.EstadoTurno.Finalizado;
import modelo.EstadoTurno.Solicitado;
import modelo.Peluqueria.Peluqueria;
import modelo.Servicio.Servicio;
import modelo.Turno.Turno;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TurnoUnitTest {
    @Test
    public void Turno_ParametrosValidos_SeGeneraunTurnoValido() {
        Cliente cliente = new Cliente("Juan", "Paso", "JPaso@fi.uba.ar", "11330404");
        LocalDateTime fecha = LocalDateTime.now();
        Peluqueria peluqueria = new Peluqueria("Una peluqueria","Calle falsa 123","1234567","asd@a.com");
        Servicio servicio = new Servicio(30,"Corte de pelo", new BigDecimal(700));
        Turno turno = new Turno(cliente, fecha,peluqueria,servicio);
        Assert.assertEquals( turno.getCliente(),cliente);
        Assert.assertEquals(turno.getServicio(), servicio);
        Assert.assertEquals(turno.getPeluqueria(), peluqueria);
        Assert.assertEquals(turno.getDia(), fecha);
    }
    @Test
    public void Turno_SeCreaUnTurno_EseTurnoSeGeneraSolicitado() {
        Cliente cliente = new Cliente("Juan", "Paso", "JPaso@fi.uba.ar", "11330404");
        LocalDateTime fecha = LocalDateTime.now();
        Peluqueria peluqueria = new Peluqueria("Una peluqueria", "Calle falsa 123", "1234567", "asd@a.com");
        Servicio servicio = new Servicio(30, "Corte de pelo",new BigDecimal(700));
        Turno turno = new Turno(cliente, fecha, peluqueria, servicio);
        Assert.assertTrue(turno.getEstado() instanceof Solicitado);
    }
    @Test
    public void FinalizarTurno_HayUnTurnoSolicitado_EseTurnoCambiaAEstadoFinalizado() throws Exception {
        Cliente cliente = new Cliente("Juan", "Paso", "JPaso@fi.uba.ar", "11330404");
        LocalDateTime fecha = LocalDateTime.now();
        Peluqueria peluqueria = new Peluqueria("Una peluqueria", "Calle falsa 123", "1234567", "asd@a.com");
        Servicio servicio = new Servicio(30, "Corte de pelo",new BigDecimal(700));
        Turno turno = new Turno(cliente, fecha, peluqueria, servicio);
        turno.finalizarTurno();
        Assert.assertTrue(turno.getEstado() instanceof Finalizado);
    }
    @Test
    public void CancelarTurno_HayUnTurnoSolicitado_EseTurnoCambiaAEstadoCancelado() throws Exception {
        Cliente cliente = new Cliente("Juan", "Paso", "JPaso@fi.uba.ar", "11330404");
        LocalDateTime fecha = LocalDateTime.now();
        Peluqueria peluqueria = new Peluqueria("Una peluqueria", "Calle falsa 123", "1234567", "asd@a.com");
        Servicio servicio = new Servicio(30, "Corte de pelo",new BigDecimal(700));
        Turno turno = new Turno(cliente, fecha, peluqueria, servicio);
        turno.cancelarTurno();
        Assert.assertTrue(turno.getEstado() instanceof Cancelado);
    }
    @Test
    public void AusentarTurno_HayUnTurnoSolicitado_EseTurnoCambiaAEstadoFinalizado() throws Exception {
        Cliente cliente = new Cliente("Juan", "Paso", "JPaso@fi.uba.ar", "11330404");
        LocalDateTime fecha = LocalDateTime.now();
        Peluqueria peluqueria = new Peluqueria("Una peluqueria", "Calle falsa 123", "1234567", "asd@a.com");
        Servicio servicio = new Servicio(30, "Corte de pelo",new BigDecimal(700));
        Turno turno = new Turno(cliente, fecha, peluqueria, servicio);
        turno.ausentarTurno();
        Assert.assertTrue(turno.getEstado() instanceof Ausentado);
    }
    @Test
    public void AusentarTurno_HayUnTurnoFinalizado_EseTurnoCambiaAEstadoAusentado() throws Exception {
        Cliente cliente = new Cliente("Juan", "Paso", "JPaso@fi.uba.ar", "11330404");
        LocalDateTime fecha = LocalDateTime.now();
        Peluqueria peluqueria = new Peluqueria("Una peluqueria", "Calle falsa 123", "1234567", "asd@a.com");
        Servicio servicio = new Servicio(30, "Corte de pelo",new BigDecimal(700));
        Turno turno = new Turno(cliente, fecha, peluqueria, servicio);
        turno.finalizarTurno();
        turno.ausentarTurno();
        Assert.assertTrue(turno.getEstado() instanceof Ausentado);
    }
}

