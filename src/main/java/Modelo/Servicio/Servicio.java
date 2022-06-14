package Modelo.Servicio;

import java.util.Objects;

public class Servicio {
    private int ID;
    private int duracion;
    private String nombre;

    public Servicio(int duracion, String nombre) {
        this.duracion = duracion;
        this.nombre = nombre;
    }

    public int getID() {
        return ID;
    }

    public int getDuracion() {
        return duracion;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Servicio servicio = (Servicio) o;
        return duracion == servicio.duracion && nombre.equals(servicio.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(duracion, nombre);
    }
}
