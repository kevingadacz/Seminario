package Modelo.EstadoTurno;

import Modelo.Turno.Turno;

public class Cancelado implements IEstadoTurno {
    private Turno turno;

    public Cancelado(Turno turno) {
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

    }
}
