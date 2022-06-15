package Modelo;

import Modelo.Cliente.Cliente;
import Modelo.EstadoTurno.*;
import Modelo.Peluqueria.Peluqueria;
import Modelo.Servicio.Servicio;
import Modelo.Turno.Turno;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class EstadoTurnoUnitTest {
    @Test
    public void CancelarTurno_SeTieneUnEstadoTurnoSolicitado_ElEstadoTurnoCambiaAEstadoCancelado() throws Exception {
        Cliente cliente = new Cliente("Juan", "Paso", "JPaso@fi.uba.ar", "11330404");
        LocalDateTime fecha = LocalDateTime.now();
        Peluqueria peluqueria = new Peluqueria("Una peluqueria","Calle falsa 123","1234567","asd@a.com");
        Servicio servicio = new Servicio(5,"Corte de pelo");
        Turno turno = new Turno(cliente, fecha,peluqueria,servicio);
        IEstadoTurno solicitado = new Solicitado(turno);
        solicitado.cancelarTurno();
        Assert.assertTrue(turno.getEstadoTurno() instanceof Cancelado);
    }

    @Test
    public void CancelarTurno_SeTieneUnEstadoTurnoCancelado_SeLanzaExceptionNoSePuedeCancelarUnTurnoCancelado() {
        Cliente cliente = new Cliente("Juan", "Paso", "JPaso@fi.uba.ar", "11330404");
        LocalDateTime fecha = LocalDateTime.now();
        Peluqueria peluqueria = new Peluqueria("Una peluqueria","Calle falsa 123","1234567","asd@a.com");
        Servicio servicio = new Servicio(5,"Corte de pelo");
        peluqueria.agregarServicio(servicio);
        Turno turno = new Turno(cliente, fecha,peluqueria,servicio);
        IEstadoTurno cancelado = new Cancelado(turno);
        try{
            cancelado.cancelarTurno();
            Assert.fail();
        }
        catch (Exception ex){
            Assert.assertEquals(ex.getMessage(),"El turno ya esta cancelado");
        }
    }
    @Test
    public void CancelarTurno_SeTieneUnEstadoTurnoFinalizado_SeLanzaExceptionNoSePuedeCancelarUnTurnoFinalizado() {
        Cliente cliente = new Cliente("Juan", "Paso", "JPaso@fi.uba.ar", "11330404");
        LocalDateTime fecha = LocalDateTime.now();
        Peluqueria peluqueria = new Peluqueria("Una peluqueria","Calle falsa 123","1234567","asd@a.com");
        Servicio servicio = new Servicio(5,"Corte de pelo");
        peluqueria.agregarServicio(servicio);
        Turno turno = new Turno(cliente, fecha,peluqueria,servicio);
        IEstadoTurno finalizado = new Finalizado(turno);
        try{
            finalizado.cancelarTurno();
            Assert.fail();
        }
        catch (Exception ex){
            Assert.assertEquals(ex.getMessage(),"El turno no se puede cancelar si ya esta finalizado");
        }
    }
    @Test
    public void CancelarTurno_SeTieneUnEstadoTurnoAusentado_SeLanzaExceptionNoSePuedeCancelarUnTurnoAusentado() {
        Cliente cliente = new Cliente("Juan", "Paso", "JPaso@fi.uba.ar", "11330404");
        LocalDateTime fecha = LocalDateTime.now();
        Peluqueria peluqueria = new Peluqueria("Una peluqueria","Calle falsa 123","1234567","asd@a.com");
        Servicio servicio = new Servicio(5,"Corte de pelo");
        peluqueria.agregarServicio(servicio);
        Turno turno = new Turno(cliente, fecha,peluqueria,servicio);
        IEstadoTurno ausentado = new Ausentado(turno);
        try{
            ausentado.cancelarTurno();
            Assert.fail();
        }
        catch (Exception ex){
            Assert.assertEquals(ex.getMessage(),"No se puede cancelar un turno ausentado");
        }
    }
    @Test
    public void FinalizarTurno_SeTieneUnEstadoTurnoSolicitado_ElEstadoTurnoCambiaAEstadoFinalizado() throws Exception {
        Cliente cliente = new Cliente("Juan", "Paso", "JPaso@fi.uba.ar", "11330404");
        LocalDateTime fecha = LocalDateTime.now();
        Peluqueria peluqueria = new Peluqueria("Una peluqueria","Calle falsa 123","1234567","asd@a.com");
        Servicio servicio = new Servicio(5,"Corte de pelo");
        Turno turno = new Turno(cliente, fecha,peluqueria,servicio);
        IEstadoTurno solicitado = new Solicitado(turno);
        solicitado.finalizarTurno();
        Assert.assertTrue(turno.getEstadoTurno() instanceof  Finalizado);
    }
    @Test
    public void FinalizarTurno_SeTieneUnEstadoTurnoFinalizado_SeLanzaExceptionNoSePuedeFinalizarUnEstadoTurnoFinalizado() throws Exception {
        Cliente cliente = new Cliente("Juan", "Paso", "JPaso@fi.uba.ar", "11330404");
        LocalDateTime fecha = LocalDateTime.now();
        Peluqueria peluqueria = new Peluqueria("Una peluqueria", "Calle falsa 123", "1234567", "asd@a.com");
        Servicio servicio = new Servicio(5, "Corte de pelo");
        peluqueria.agregarServicio(servicio);
        Turno turno = new Turno(cliente, fecha, peluqueria, servicio);
        IEstadoTurno finalizado = new Finalizado(turno);
        try {
            finalizado.finalizarTurno();
            Assert.fail();
        } catch (Exception ex) {
            Assert.assertEquals(ex.getMessage(),"El turno no se puede finalizar si ya esta finalizado");
        }
    }
    @Test
    public void FinalizarTurno_SeTieneUnEstadoTurnoCancelado_SeLanzaExceptionNoSePuedeFinalizarUnEstadoTurnoCancelado() throws Exception {
        Cliente cliente = new Cliente("Juan", "Paso", "JPaso@fi.uba.ar", "11330404");
        LocalDateTime fecha = LocalDateTime.now();
        Peluqueria peluqueria = new Peluqueria("Una peluqueria", "Calle falsa 123", "1234567", "asd@a.com");
        Servicio servicio = new Servicio(5, "Corte de pelo");
        peluqueria.agregarServicio(servicio);
        Turno turno = new Turno(cliente, fecha, peluqueria, servicio);
        IEstadoTurno cancelado = new Cancelado(turno);
        try {
            cancelado.finalizarTurno();
            Assert.fail();
        } catch (Exception ex) {
            Assert.assertEquals(ex.getMessage(),"El turno no se puede finalizar si ya esta cancelado");
        }
    }
    @Test
    public void FinalizarTurno_SeTieneUnEstadoTurnoAusentado_SeLanzaExceptionNoSePuedeFinalizarUnEstadoTurnoAusentado() throws Exception {
        Cliente cliente = new Cliente("Juan", "Paso", "JPaso@fi.uba.ar", "11330404");
        LocalDateTime fecha = LocalDateTime.now();
        Peluqueria peluqueria = new Peluqueria("Una peluqueria", "Calle falsa 123", "1234567", "asd@a.com");
        Servicio servicio = new Servicio(5, "Corte de pelo");
        peluqueria.agregarServicio(servicio);
        Turno turno = new Turno(cliente, fecha, peluqueria, servicio);
        IEstadoTurno ausentado = new Ausentado(turno);
        try {
            ausentado.finalizarTurno();
            Assert.fail();
        } catch (Exception ex) {
            Assert.assertEquals(ex.getMessage(),"No se puede finalizar un turno ausentado");
        }
    }
    @Test
    public void AusentarTurno_SeTieneUnEstadoTurnoAusentado_SeLanzaExceptionNoSePuedeAusentarUnEstadoTurnoAusentado() throws Exception {
        Cliente cliente = new Cliente("Juan", "Paso", "JPaso@fi.uba.ar", "11330404");
        LocalDateTime fecha = LocalDateTime.now();
        Peluqueria peluqueria = new Peluqueria("Una peluqueria", "Calle falsa 123", "1234567", "asd@a.com");
        Servicio servicio = new Servicio(5, "Corte de pelo");
        peluqueria.agregarServicio(servicio);
        Turno turno = new Turno(cliente, fecha, peluqueria, servicio);
        IEstadoTurno ausentado = new Ausentado(turno);
        try {
            ausentado.ausentarTurno();
            Assert.fail();
        } catch (Exception ex) {
            Assert.assertEquals(ex.getMessage(),"El turno ya esta ausentado");
        }
    }
    @Test
    public void AusentarTurno_SeTieneUnEstadoTurnoSolicitado_EstadoTurnoCambiaAEstadoTurnoAusentado() throws Exception {
        Cliente cliente = new Cliente("Juan", "Paso", "JPaso@fi.uba.ar", "11330404");
        LocalDateTime fecha = LocalDateTime.now();
        Peluqueria peluqueria = new Peluqueria("Una peluqueria", "Calle falsa 123", "1234567", "asd@a.com");
        Servicio servicio = new Servicio(5, "Corte de pelo");
        Turno turno = new Turno(cliente, fecha, peluqueria, servicio);
        IEstadoTurno solicitado = new Solicitado(turno);
        solicitado.ausentarTurno();
        Assert.assertTrue(turno.getEstadoTurno()instanceof  Ausentado);
    }
    @Test
    public void AusentarTurno_SeTieneUnEstadoTurnoFinalizado_EstadoTurnoCambiaAEstadoTurnoAusentado() throws Exception {
        Cliente cliente = new Cliente("Juan", "Paso", "JPaso@fi.uba.ar", "11330404");
        LocalDateTime fecha = LocalDateTime.now();
        Peluqueria peluqueria = new Peluqueria("Una peluqueria", "Calle falsa 123", "1234567", "asd@a.com");
        Servicio servicio = new Servicio(5, "Corte de pelo");
        Turno turno = new Turno(cliente, fecha, peluqueria, servicio);
        IEstadoTurno finalizado = new Finalizado(turno);
        finalizado.ausentarTurno();
        Assert.assertTrue(turno.getEstadoTurno() instanceof  Ausentado);
    }
    @Test
    public void AusentarTurno_SeTieneUnEstadoTurnoCancelado_SeLanzaExceptionNoSePuedeAusentarUnEstadoTurnoCancelado() throws Exception {
        Cliente cliente = new Cliente("Juan", "Paso", "JPaso@fi.uba.ar", "11330404");
        LocalDateTime fecha = LocalDateTime.now();
        Peluqueria peluqueria = new Peluqueria("Una peluqueria", "Calle falsa 123", "1234567", "asd@a.com");
        Servicio servicio = new Servicio(5, "Corte de pelo");
        peluqueria.agregarServicio(servicio);
        Turno turno = new Turno(cliente, fecha, peluqueria, servicio);
        IEstadoTurno cancelado = new Cancelado(turno);
        try {
            cancelado.ausentarTurno();
            Assert.fail();
        } catch (Exception ex) {
            Assert.assertEquals(ex.getMessage(),"El turno no se puede ausentar si ya esta cancelado");
        }
    }
}
