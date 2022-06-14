package Modelo.EstadoTurno;

import Modelo.Turno.Turno;

public class Cancelado implements IEstadoTurno {
    private Turno turno;

    public Cancelado(Turno turno) {
        this.turno = turno;
    }

    @Override
    public void cancelarTurno() throws Exception {
        throw new Exception("El turno ya esta cancelado");
    }

    @Override
    public void finalizarTurno() {

    }

    @Override
    public void ausentarTurno() {

    }

    public boolean mismaClase(IEstadoTurno estadoTurno) {
        return estadoTurno.getClass() == Cancelado.class;
    }
}
