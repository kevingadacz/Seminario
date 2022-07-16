package modelo.EstadoTurno;

public interface IEstadoTurno {
    public void cancelarTurno() throws Exception;
    public void finalizarTurno() throws Exception;
    public void ausentarTurno() throws Exception;
}
