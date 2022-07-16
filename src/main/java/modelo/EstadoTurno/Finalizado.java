package modelo.EstadoTurno;

import modelo.Turno.Turno;

public class Finalizado implements IEstadoTurno {
    private Turno turno;

    public Finalizado(Turno turno) {
        this.turno = turno;
    }

    @Override
    public void cancelarTurno() throws Exception {
        throw new Exception("El turno no se puede cancelar si ya esta finalizado");

    }

    @Override
    public void finalizarTurno() throws Exception {
        throw new Exception("El turno no se puede finalizar si ya esta finalizado");

    }

    @Override
    public void ausentarTurno() throws Exception {

    }
}
