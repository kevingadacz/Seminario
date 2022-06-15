package Modelo.Turno;

import Modelo.Cliente.Cliente;
import Modelo.EstadoTurno.*;
import Modelo.Peluqueria.Peluqueria;
import Modelo.Servicio.Servicio;

import java.time.LocalDateTime;
import java.util.Objects;

public class Turno {
    private String ID;
    private LocalDateTime dia;
    private Servicio servicio;
    private IEstadoTurno estadoTurno;
    private Peluqueria peluqueria;
    private Cliente cliente;

    public Turno(Cliente cliente, LocalDateTime dia,Peluqueria peluqueria, Servicio servicio ) {
        this.dia = dia;
        this.servicio = servicio;
        this.estadoTurno = new Solicitado(this);
        this.peluqueria = peluqueria;
        this.cliente = cliente;
    }

    public String getID() {
        return ID;
    }

    public LocalDateTime getDia() {
        return dia;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public IEstadoTurno getEstadoTurno() {
        return estadoTurno;
    }

    public Peluqueria getPeluqueria() {
        return peluqueria;
    }

    public Cliente getCliente() {
        return cliente;
    }

    private void cambiarEstado(IEstadoTurno estadoTurno){
        this.estadoTurno = estadoTurno;
    }

    public void cancelarTurno() throws Exception {
        cambiarEstado(new Cancelado(this));
    }

    public void finalizarTurno() {
        cambiarEstado(new Finalizado(this));
    }

    public void ausentarTurno() {
        cambiarEstado(new Ausentado(this));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turno turno = (Turno) o;
        return dia.equals(turno.dia) && servicio.equals(turno.servicio)  && peluqueria.equals(turno.peluqueria) && cliente.equals(turno.cliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dia, servicio, estadoTurno, peluqueria, cliente);
    }
}
