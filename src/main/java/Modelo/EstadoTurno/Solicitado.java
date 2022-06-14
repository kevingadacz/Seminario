package Modelo.EstadoTurno;

import Modelo.Turno.Turno;

public class Solicitado implements IEstadoTurno {
    private Turno turno;

    public Solicitado(Turno turno) {
        this.turno = turno;
    }

    @Override
    public void cancelarTurno() throws Exception {
        turno.cancelarTurno();
    }

    @Override
    public void finalizarTurno() {
        turno.finalizarTurno();
    }

    @Override
    public void ausentarTurno() {
        turno.ausentarTurno();
    }
}
