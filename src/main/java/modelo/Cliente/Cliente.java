package modelo.Cliente;

import modelo.FormaDeNotificar.IFormaDeNotificar;
import modelo.FormaDeNotificar.Mail;
import modelo.Peluqueria.Peluqueria;
import modelo.Servicio.Servicio;
import modelo.SistemaDeTurnosPeluqueria.SistemaDeTurnosPeluqueria;
import modelo.Turno.Turno;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

public class Cliente{
    private String id;
    private String nombre;
    private String apellido;
    private String mail;
    private String telefono;
    private ArrayList<Turno> turnos;
    private IFormaDeNotificar formaDeNotificar;


    public Cliente(String nombre, String apellido, String mail, String telefono) {
        this.id =  UUID.randomUUID().toString();
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.telefono = telefono;
        this.turnos = new ArrayList<>();
        this.formaDeNotificar = new Mail(mail);
    }

    public String getId() {
        return id;
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
        List<Turno> turnoACancelar = turnos.stream().filter(turno1 -> turno1.equals(turno)).collect(Collectors.toList());
        if (turnoACancelar.size() == 0) {
            throw new Exception("El turno que se intenta cancelar ya esta cancelado");
        }

        turnoACancelar.get(0).cancelarTurno();
        formaDeNotificar.notificar("El turno fue cancelado");
    };

    public void asistirAlTurno(Turno turno) throws Exception {
        for(Turno unturno : turnos)if(turno == unturno){
            unturno.finalizarTurno();
            formaDeNotificar.notificar("El turno fue finalizado");
        }
    }

    public void notificar(String mensaje) {
        formaDeNotificar.notificar(mensaje);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id.equals(cliente.id) && nombre.equals(cliente.nombre) && apellido.equals(cliente.apellido) && mail.equals(cliente.mail) && telefono.equals(cliente.telefono) && turnos.equals(cliente.turnos) && formaDeNotificar.equals(cliente.formaDeNotificar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellido, mail, telefono, turnos, formaDeNotificar);
    }
}
