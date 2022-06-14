package Modelo.EstadoTurno;

import Modelo.Turno.Turno;

public class Ausentado implements IEstadoTurno {
    private Turno turno;

    public Ausentado(Turno turno) {
        this.turno = turno;
    }

    @Override
    public void cancelarTurno() throws Exception {
        throw new Exception("No se puede cancelar un turno ausentado");
    }

    @Override
    public void finalizarTurno() throws Exception {
        throw new Exception("No se puede finalizar un turno ausentado");

    }

    @Override
    public void ausentarTurno() throws Exception {
        throw new Exception("El turno ya esta ausentado");

    }
}
