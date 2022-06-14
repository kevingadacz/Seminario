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
    public void finalizarTurno() throws Exception {
        throw new Exception("El turno no se puede finalizar si ya esta cancelado");


    }

    @Override
    public void ausentarTurno() throws Exception {
        throw new Exception("El turno no se puede ausentar si ya esta cancelado");
    }

    public boolean mismaClase(IEstadoTurno estadoTurno) {
        return estadoTurno.getClass() == Cancelado.class;
    }
}
