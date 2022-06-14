package Modelo.Calificacion;

public class Calificacion {
    private int puntuacion;
    private String comentario;

    public Calificacion(int puntuacion, String comentario) {
        this.puntuacion = puntuacion;
        this.comentario = comentario;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public String getComentario() {
        return comentario;
    }
}

