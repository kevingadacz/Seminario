package Modelo.Servicio;

import java.util.Objects;

public class Servicio {
    private String ID;
    private int duracion;
    private String nombre;
    private int precio;

    public Servicio(int duracion, String nombre, int precio) {
        this.duracion = duracion;
        this.nombre = nombre;
    }

    public String getID() {
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
