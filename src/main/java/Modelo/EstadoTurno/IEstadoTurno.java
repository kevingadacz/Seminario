package Modelo.EstadoTurno;

public interface IEstadoTurno {
    public void cancelarTurno() throws Exception;
    public void finalizarTurno();
    public void ausentarTurno();
}
