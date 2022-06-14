package Modelo.Peluqueria;

import Modelo.Calificacion.Calificacion;
import Modelo.Notificable.IFormaDeNotificar;
import Modelo.Notificable.INotificable;
import Modelo.Notificable.Whatsapp;
import Modelo.Servicio.Servicio;
import Modelo.Turno.Turno;

import java.time.LocalDateTime;
import java.util.ArrayList;


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
        turno.getCliente().penalizar(turno);
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
    public void cancelarTurno(Turno turno){
        turno.cancelar();
    }

    public void agregarTurno(Turno turno) {
        turnos.add(turno);
    }

    @Override
    public void notificar(String mensaje) {
        this.formaDeNotificar.notificar(mensaje);

    }
}
