package Modelo.EstadoTurno;

import Modelo.Turno.Turno;

public class Solicitado implements IEstadoTurno {
    private Turno turno;

    public Solicitado(Turno turno) {
        this.turno = turno;
    }

    @Override
    public void cancelarTurno() {
        turno.cambiarEstado(new Cancelado(turno));
    }

    @Override
    public void finalizarTurno() {
        turno.cambiarEstado(new Finalizado(turno));
    }

    @Override
    public void ausentarTurno() {
        turno.cambiarEstado(new Ausentado(turno));
    }
}
