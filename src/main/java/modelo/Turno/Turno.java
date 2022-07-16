package modelo.Turno;

import modelo.Cliente.Cliente;
import modelo.EstadoTurno.*;
import modelo.Peluqueria.Peluqueria;
import modelo.Servicio.Servicio;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Turno {
    private String id;
    private LocalDateTime dia;
    private Servicio servicio;
    private IEstadoTurno estado;
    private Peluqueria peluqueria;
    private Cliente cliente;

    public Turno(Cliente cliente, LocalDateTime dia,Peluqueria peluqueria, Servicio servicio ) {
        this.id = UUID.randomUUID().toString();
        this.dia = dia;
        this.servicio = servicio;
        this.estado = new Solicitado(this);
        this.peluqueria = peluqueria;
        this.cliente = cliente;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getDia() {
        return dia;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public IEstadoTurno getEstado() {
        return estado;
    }

    public Peluqueria getPeluqueria() {
        return peluqueria;
    }

    public Cliente getCliente() {
        return cliente;
    }

    private void cambiarEstado(IEstadoTurno estadoTurno){
        this.estado = estadoTurno;
    }

    public void cancelarTurno() throws Exception {
        //Los turnos pueden ser cancelados hasta 1hs antes de que suceda, luego ya no

        //Si tenemos un turno un miercoles a las 16 entonces:
        //Podes cancelarlo hasta el miercoles a las 15

        //Agregar logica aca que valide esa regla de negocio qe vale para todos los turnos
        this.estado.cancelarTurno();
        cambiarEstado(new Cancelado(this));
    }

    public void finalizarTurno() throws Exception {
        this.estado.finalizarTurno();
        cambiarEstado(new Finalizado(this));
    }

    public void ausentarTurno() throws Exception {
        this.estado.ausentarTurno();
        cambiarEstado(new Ausentado(this));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turno turno = (Turno) o;
        return id.equals(turno.id) && dia.equals(turno.dia) && servicio.equals(turno.servicio) && estado.equals(turno.estado) && peluqueria.equals(turno.peluqueria) && cliente.equals(turno.cliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dia, servicio, estado, peluqueria, cliente);
    }
}
