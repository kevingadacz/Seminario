package Modelo;

import Modelo.Cliente.Cliente;
import Modelo.Peluqueria.Peluqueria;
import Modelo.Servicio.Servicio;
import Modelo.Turno.Turno;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class PenalizadorUnitTest {
    @Test
    public void SolicitarTurno_ClienteCon0PenalizacionesSolicitaTurno_SeGeneraUnTurno(){
        Cliente cliente = new Cliente("Juan", "Paso", "JPaso@fi.uba.ar", "11330404");
        LocalDateTime fecha = LocalDateTime.now();
        Peluqueria peluqueria = new Peluqueria("Una peluqueria","Calle falsa 123","1234567","asd@a.com");
        Servicio servicio = new Servicio(5,"Corte de pelo",700);
        peluqueria.agregarServicio(servicio);
        try {
            cliente.solicitarTurno(fecha, peluqueria, servicio);
            Assert.assertEquals(cliente.getTurnos().get(0), peluqueria.getTurnos().get(0));
        }
        catch(Exception ex){
            Assert.assertEquals(ex.getMessage(),"El cliente no puede solicitar un turno porque ya tiene mas de 2 penalizacione");
        }
    }

    @Test
    public void SolicitarTurno_ClienteCon1PenalizacionSolicitaTurno_SeGeneraUnTurno(){
        Cliente cliente = new Cliente("Juan", "Paso", "JPaso@fi.uba.ar", "11330404");
        Peluqueria peluqueria = new Peluqueria("Una peluqueria","Calle falsa 123","1234567","asd@a.com");
        Servicio servicio = new Servicio(5,"Corte de pelo",700);
        peluqueria.agregarServicio(servicio);
        try {
            LocalDateTime fechaPrimerTurno = LocalDateTime.now();
            LocalDateTime fechaSegundoTurno = LocalDateTime.now().plusHours(10);

            Turno turno = cliente.solicitarTurno(fechaPrimerTurno, peluqueria, servicio);
            peluqueria.informarInasistencia(turno);

            cliente.solicitarTurno(fechaSegundoTurno, peluqueria, servicio);
            Assert.assertEquals(cliente.getTurnos().get(1), peluqueria.getTurnos().get(1));
        }
        catch(Exception ex){
            Assert.assertEquals(ex.getMessage(),"El cliente no puede solicitar un turno porque ya tiene mas de 2 penalizacione");
        }
    }

    @Test
    public void SolicitarTurno_ClienteCon2PenalizacionesSolicitaTurno_SeGeneraUnTurno(){
        Cliente cliente = new Cliente("Juan", "Paso", "JPaso@fi.uba.ar", "11330404");
        Peluqueria peluqueria = new Peluqueria("Una peluqueria","Calle falsa 123","1234567","asd@a.com");
        Servicio servicio = new Servicio(5,"Corte de pelo",700);
        peluqueria.agregarServicio(servicio);
        try {
            LocalDateTime fechaPrimerTurno = LocalDateTime.now();
            LocalDateTime fechaSegundoTurno = LocalDateTime.now().plusHours(10);
            LocalDateTime fechaTercerTurno = LocalDateTime.now().plusDays(10);

            Turno turno = cliente.solicitarTurno(fechaPrimerTurno, peluqueria, servicio);
            peluqueria.informarInasistencia(turno);

            turno = cliente.solicitarTurno(fechaSegundoTurno, peluqueria, servicio);
            peluqueria.informarInasistencia(turno);

            cliente.solicitarTurno(fechaTercerTurno, peluqueria, servicio);
            Assert.assertEquals(cliente.getTurnos().get(1), peluqueria.getTurnos().get(1));
        }
        catch(Exception ex){
            Assert.assertEquals(ex.getMessage(),"El cliente no puede solicitar un turno porque ya tiene mas de 2 penalizacione");
        }
    }

    @Test
    public void SolicitarTurno_ClienteCon3PenalizacionesSolicitaTurno_NoSeGeneraUnTurno(){
        Cliente cliente = new Cliente("Juan", "Paso", "JPaso@fi.uba.ar", "11330404");
        Peluqueria peluqueria = new Peluqueria("Una peluqueria","Calle falsa 123","1234567","asd@a.com");
        Servicio servicio = new Servicio(5,"Corte de pelo",700);
        peluqueria.agregarServicio(servicio);
        try {
            LocalDateTime fechaPrimerTurno = LocalDateTime.now();
            LocalDateTime fechaSegundoTurno = LocalDateTime.now().plusHours(10);
            LocalDateTime fechaTercerTurno = LocalDateTime.now().plusDays(10);
            LocalDateTime fechaCuartoTurno = LocalDateTime.now().plusDays(11);

            Turno turno = cliente.solicitarTurno(fechaPrimerTurno, peluqueria, servicio);
            peluqueria.informarInasistencia(turno);

            turno = cliente.solicitarTurno(fechaSegundoTurno, peluqueria, servicio);
            peluqueria.informarInasistencia(turno);

            turno = cliente.solicitarTurno(fechaTercerTurno, peluqueria, servicio);
            peluqueria.informarInasistencia(turno);

            cliente.solicitarTurno(fechaCuartoTurno, peluqueria, servicio);
            Assert.fail();
        }
        catch(Exception ex){
            Assert.assertEquals(ex.getMessage(),"El cliente no puede solicitar un turno porque ya tiene mas de 2 penalizaciones");
        }
    }
}
