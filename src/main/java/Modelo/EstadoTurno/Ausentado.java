package Modelo.EstadoTurno;

import Modelo.Turno.Turno;

public class Ausentado implements IEstadoTurno {
    private Turno turno;

    public Ausentado(Turno turno) {
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
