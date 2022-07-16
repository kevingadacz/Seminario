package modelo.EstadoTurno;

import modelo.Turno.Turno;

public class Solicitado implements IEstadoTurno {
    private Turno turno;

    public Solicitado(Turno turno) {
        this.turno = turno;
    }

    @Override
    public void cancelarTurno() throws Exception {

    }

    @Override
    public void finalizarTurno() throws Exception {

    }

    @Override
    public void ausentarTurno() throws Exception {

    }
}
