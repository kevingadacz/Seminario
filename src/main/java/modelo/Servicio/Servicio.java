package modelo.Servicio;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Objects;
import java.util.UUID;

public class Servicio {
    private String id;
    private Duration duracion;
    private String nombre;
    private BigDecimal precio;

    public Servicio(int duracion, String nombre, BigDecimal precio) {
        this.id = UUID.randomUUID().toString();
        this.precio = precio;
        this.duracion = Duration.ofHours(duracion);
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public Duration getDuracion() {
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
