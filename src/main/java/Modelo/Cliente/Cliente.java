package Modelo.Cliente;

import Modelo.Calificacion.Calificacion;
import Modelo.Notificable.IFormaDeNotificar;
import Modelo.Notificable.INotificable;
import Modelo.Notificable.Mail;
import Modelo.Peluqueria.Peluqueria;
import Modelo.Servicio.Servicio;
import Modelo.SistemaDeTurnosPeluqueria.SistemaDeTurnosPeluqueria;
import Modelo.Turno.Turno;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class Cliente implements INotificable {
    private String Id;
    private String nombre;
    private String apellido;
    private String mail;
    private String telefono;
    private ArrayList<Turno> turnos;
    private IFormaDeNotificar formaDeNotificar;


    public Cliente(String nombre, String apellido, String mail, String telefono) {
        this.Id =  UUID.randomUUID().toString();
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.telefono = telefono;
        this.turnos = new ArrayList<>();
        this.formaDeNotificar = new Mail(mail);
    }

    public String getId() {
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

    public void confirmarTurno(Turno turno){
        turnos.add(turno);
        formaDeNotificar.notificar("El turno fue confirmado ");
    }

    public Turno solicitarTurno(LocalDateTime dia , Peluqueria peluqueria, Servicio servicio ) throws Exception{
        SistemaDeTurnosPeluqueria.getSistema().getPenalizador().autorizarTurno(this);
        return peluqueria.solicitarTurno(this, dia, peluqueria, servicio);
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
