package Modelo.Turno;

import java.Cliente.Cliente;
import Modelo.EstadoTurno.IEstadoTurno;
import Modelo.EstadoTurno.Solicitado;
import Modelo.Peluqueria.Peluqueria;
import Modelo.Servicio.Servicio;

import java.time.LocalDateTime;

public class Turno {
    private int ID;
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
        peluqueria.agregarTurno(this);
    }

    public int getID() {
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

    public void cambiarEstado(IEstadoTurno estadoTurno){
        this.estadoTurno = estadoTurno;
    }

    public void cancelar(){
        estadoTurno.cancelarTurno();
    }


}
