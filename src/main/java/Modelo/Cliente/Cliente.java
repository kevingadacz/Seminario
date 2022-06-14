package Modelo.Cliente;

import Modelo.Calificacion.Calificacion;
import Modelo.Notificable.IFormaDeNotificar;
import Modelo.Notificable.INotificable;
import Modelo.Notificable.Mail;
import Modelo.Peluqueria.Peluqueria;
import Modelo.Penalizacion.Penalizacion;
import Modelo.Servicio.Servicio;
import Modelo.Turno.Turno;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class Cliente implements INotificable {
    private int Id;
    private String nombre;
    private String apellido;
    private String mail;
    private String telefono;
    private ArrayList<Turno> turnos;
    private ArrayList<Penalizacion> penalizaciones;
    private IFormaDeNotificar formaDeNotificar;


    public Cliente(String nombre, String apellido, String mail, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.telefono = telefono;
        this.turnos = new ArrayList<>();
        this.penalizaciones = new ArrayList<>();
        this.formaDeNotificar = new Mail(mail);
    }

    public int getID() {
        return Id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getMail() {
        return mail;
    }

    public String getTelefono() {
        return telefono;
    }

    public ArrayList<Turno> getTurnos() {
        return turnos;
    }

    public ArrayList<Penalizacion> getPenalizaciones() {
        return penalizaciones;
    }

    /*public void penalizarjjh(){
        if(penalizaciones.size()>2) { new Exception("No se puede solicitar un turno con 3 penalizaciones");
        }

    }*/

    public void confirmarTurno(Turno turno){
        turnos.add(turno);
        formaDeNotificar.notificar("El turno fue confirmado ");
    }

    public Turno solicitarTurno(LocalDateTime dia , Peluqueria peluqueria, Servicio servicio ) throws Exception{
        Turno turno = new Turno(this, dia, peluqueria, servicio);
        formaDeNotificar.notificar("Haz solicitado un turno");
        peluqueria.solicitarTurno(turno);
        return turno;
     }

    public void cancelarTurno(Turno turno) throws Exception {
        for(Turno unturno : turnos)if(turno == unturno) {
            unturno.cancelarTurno();
            unturno.getPeluqueria().cancelarTurnoPedidoPorCliente(unturno);
        }
        formaDeNotificar.notificar("El turno fue cancelado");
    };

    public void cancelarTurnoPorPeluqueria(Turno turno) throws Exception {
        for(Turno unturno : turnos)if(turno == unturno) {
            unturno.cancelarTurno();}
        formaDeNotificar.notificar("El turno fue cancelado");
    };

    /*public void penalizar(Turno turno) {
        penalizaciones.add(new Penalizacion(turno));
        this.notificar("Haz recibido una penalizacion");
        turno.getPeluqueria().notificar("Se ha penalizado correctamente al cliente");
    }*/

    public void calificarPeluqueria(Peluqueria peluqueria, int puntuacion, String comentario){
        peluqueria.calificar(new Calificacion(puntuacion, comentario));
    }

    public void asistirAlTurno(Turno turno) throws Exception {
        for(Turno unturno : turnos)if(turno == unturno){
            unturno.finalizarTurno();
            formaDeNotificar.notificar("El turno fue finalizado");
            unturno.getPeluqueria().finalizarTurno(unturno);
        }
    }

    @Override
    public void notificar(String mensaje) {
        formaDeNotificar.notificar(mensaje);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return nombre.equals(cliente.nombre) && apellido.equals(cliente.apellido) && mail.equals(cliente.mail) && telefono.equals(cliente.telefono);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, apellido, mail, telefono);
    }
}
