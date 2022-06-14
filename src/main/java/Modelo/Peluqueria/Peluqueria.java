package Modelo.Peluqueria;

import Modelo.Calificacion.Calificacion;
import Modelo.Notificable.IFormaDeNotificar;
import Modelo.Notificable.INotificable;
import Modelo.Notificable.Whatsapp;
import Modelo.Servicio.Servicio;
import Modelo.Turno.Turno;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;


public class Peluqueria implements INotificable {
    private int ID ;
    private String nombre;
    private String direccion;
    private String telefono;
    private String mail;
    private ArrayList<Turno> turnos;
    private ArrayList<Servicio> servicios;
    private ArrayList<Calificacion> calificaciones;
    private IFormaDeNotificar formaDeNotificar;


    public Peluqueria(String nombre, String direccion, String telefono, String mail) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.mail = mail;
        this.turnos = new ArrayList<Turno>();
        this.servicios = new ArrayList<Servicio>();
        this.calificaciones = new ArrayList<Calificacion>();
        this.formaDeNotificar = new Whatsapp(telefono);
    }

    public int getID() {
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
        //turno.getCliente().penalizar(turno);
    }

    public void calificar(Calificacion calificacion) {
        this.calificaciones.add(calificacion);
    }

    public long calcularCalificacion(){
        int sumadepuntuaciones= 0;
        for(Calificacion calificacion : calificaciones){sumadepuntuaciones= sumadepuntuaciones + calificacion.getPuntuacion();}
        long cantidadDeCalificaciones = calificaciones.size();
        return sumadepuntuaciones/cantidadDeCalificaciones;
    }


    public ArrayList<Turno> buscarTurnos(LocalDateTime dia){
        ArrayList<Turno> turnosbuscados = new ArrayList<Turno>();
        for (Turno turno: turnos) {if((turno.getDia().getDayOfYear()==dia.getDayOfYear())&&(turno.getDia().getYear() == dia.getYear()))turnosbuscados.add(turno);}
        return turnosbuscados;
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

    public void solicitarTurno(Turno turno) throws Exception {
        if (turnoDisponible(turno)) {
            formaDeNotificar.notificar("Se ha solicitado un turno");
            agregarTurno(turno);
            turno.getCliente().confirmarTurno(turno);
        }
        else{
            throw new Exception("Turno no disponible");
        }
        formaDeNotificar.notificar("Se ha confirmado un turno");
    }

    private boolean mismoDia(LocalDateTime dia1,LocalDateTime dia2, long duracion1, long duracion2) {
        return dia2.isBefore(dia1.plusMinutes(duracion1)) && dia2.plusMinutes(duracion2).isAfter(dia1);

    }

    private boolean turnoDisponible(Turno turno) {
        //Esto podria estar en un ValidadorDeTurnos
        for (Turno unTurno: turnos) {
            if(mismoDia(unTurno.getDia(),turno.getDia(),turno.getServicio().getDuracion(),unTurno.getServicio().getDuracion()))
                return false;
        }
        return true;
    }

    @Override
    public void notificar(String mensaje) {
        this.formaDeNotificar.notificar(mensaje);
    }

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
