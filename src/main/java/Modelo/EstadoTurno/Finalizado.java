package Modelo.EstadoTurno;

import Modelo.Turno.Turno;

public class Finalizado implements IEstadoTurno {
    private Turno turno;

    public Finalizado(Turno turno) {
        this.turno = turno;
    }

    @Override
    public void cancelarTurno() {
    }

    @Override
    public void finalizarTurno() {
    }

    @Override
    public void ausentarTurno() {
        turno.cambiarEstado(new Ausentado(turno));
    }
}
