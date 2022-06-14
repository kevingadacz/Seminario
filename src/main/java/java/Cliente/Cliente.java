package java.Cliente;

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

public class Cliente implements INotificable {
    private int ID;
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
        return ID;
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

    public Turno solicitarTurno(LocalDateTime dia , Peluqueria peluqueria, Servicio servicio ) throws Exception{
        if(penalizaciones.size()>2) { new Exception("No se puede solicitar un turno con 3 penalizaciones");
        }
        Turno turno = new Turno(this, dia, peluqueria, servicio);
        turnos.add(turno);
        return turno;
     };

    public void cancelarTurno(Turno turno){
        turno.cancelar();
    };

    public void penalizar(Turno turno) {
        penalizaciones.add(new Penalizacion(turno));
        this.notificar("Haz recibido una penalizacion");
        turno.getPeluqueria().notificar("Se ha penalizado correctamente al cliente");
    }

    public void calificarPeluqueria(Peluqueria peluqueria, int puntuacion, String comentario){
        peluqueria.calificar(new Calificacion(puntuacion, comentario));
    }

    @Override
    public void notificar(String mensaje) {
        formaDeNotificar.notificar(mensaje);
    }
}
