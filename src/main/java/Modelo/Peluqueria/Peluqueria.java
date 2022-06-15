package Modelo.Peluqueria;

import Modelo.Cliente.Cliente;
import Modelo.FormaDeNotificar.IFormaDeNotificar;
import Modelo.FormaDeNotificar.Whatsapp;
import Modelo.Servicio.Servicio;
import Modelo.SistemaDeTurnosPeluqueria.SistemaDeTurnosPeluqueria;
import Modelo.Turno.Turno;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;


public class Peluqueria {
    private String ID ;
    private String nombre;
    private String direccion;
    private String telefono;
    private String mail;
    private ArrayList<Turno> turnos;
    private ArrayList<Servicio> servicios;
    private IFormaDeNotificar formaDeNotificar;


    public Peluqueria(String nombre, String direccion, String telefono, String mail) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.mail = mail;
        this.turnos = new ArrayList<Turno>();
        this.servicios = new ArrayList<Servicio>();
        this.formaDeNotificar = new Whatsapp(telefono);
    }

    public String getID() {
        return ID;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getMail() {
        return mail;
    }

    public String getTelefono() {return telefono;}

    public ArrayList<Turno> getTurnos() {
        return turnos;
    }

    public ArrayList<Servicio> getServicios() {
        return servicios;
    }

    public void agregarServicio(Servicio servicio){
        this.servicios.add(servicio);
    }
    public void informarInasistencia(Turno turno){
        SistemaDeTurnosPeluqueria.getSistema().getPenalizador().penalizar(turno);
    }

    public void cancelarTurno(Turno turno) throws Exception {
        for(Turno unturno : turnos)if(turno == unturno) {
            unturno.cancelarTurno();
            unturno.getCliente().cancelarTurnoPorPeluqueria(turno);
        }
        formaDeNotificar.notificar("El turno fue cancelado");
    }

    public void cancelarTurnoPedidoPorCliente(Turno turno) throws Exception {
        for(Turno unturno : turnos)if(turno == unturno) {
            unturno.cancelarTurno();
        }
        formaDeNotificar.notificar("El turno fue cancelado");
    }

    public void finalizarTurno(Turno turno) throws Exception {
        for(Turno unturno : turnos)if(turno == unturno) unturno.finalizarTurno();;
        formaDeNotificar.notificar("El turno fue finalizado");
    }

    public void agregarTurno(Turno turno) {
        turnos.add(turno);
    }

    private void validaServicioDisponible(Servicio servicio) throws Exception {
        if (!this.servicios.contains(servicio))
            throw new Exception("La peluqueria no ofrece ese servicio");
    }

    public Turno solicitarTurno(Cliente cliente, LocalDateTime dia, Peluqueria peluqueria, Servicio servicio) throws Exception {
        Turno turno = new Turno(cliente, dia, peluqueria, servicio);
        validaTurnoDisponible(turno);
        validaServicioDisponible(turno.getServicio());
        agregarTurno(turno);
        turno.getCliente().confirmarTurno(turno);
        formaDeNotificar.notificar("Se ha confirmado un turno");
        return turno;
    }

    private boolean mismoDia(LocalDateTime dia1,LocalDateTime dia2, long duracion1, long duracion2) {
        return dia2.isBefore(dia1.plusMinutes(duracion1)) && dia2.plusMinutes(duracion2).isAfter(dia1);

    }

    private void validaTurnoDisponible(Turno turno) throws Exception {
        //Esto podria estar en un ValidadorDeTurnos
        for (Turno unTurno: turnos) {
            if(mismoDia(unTurno.getDia(),turno.getDia(),turno.getServicio().getDuracion(),unTurno.getServicio().getDuracion()))
                throw new Exception("Turno no disponible");
        }
    }

    public void notificar(String mensaje) {this.formaDeNotificar.notificar(mensaje);}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Peluqueria that = (Peluqueria) o;
        return nombre.equals(that.nombre) && direccion.equals(that.direccion) && telefono.equals(that.telefono) && mail.equals(that.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, direccion, telefono, mail);
    }
}
