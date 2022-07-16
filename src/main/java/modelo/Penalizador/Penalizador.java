package modelo.Penalizador;

import modelo.Cliente.Cliente;
import modelo.Penalizacion.Penalizacion;
import modelo.Turno.Turno;
import java.util.ArrayList;
import java.util.HashMap;

public class Penalizador {
    //La clave es el Id del cliente y el valor es un arreglo con las penalizaciones del cliente
    private HashMap<String, ArrayList<Penalizacion>> penalizacionesPorCliente = new HashMap<String, ArrayList<Penalizacion>>();
    public void autorizarTurno(Cliente cliente) throws Exception {
        if (penalizacionesPorCliente.getOrDefault(cliente.getId(),new ArrayList<>()).size() > 2)
            throw new Exception("El cliente no puede solicitar un turno porque ya tiene mas de 2 penalizaciones");
    }
    public void penalizar(Turno turno) {
        if (penalizacionesPorCliente.containsKey(turno.getCliente().getId()))
            penalizacionesPorCliente.get(turno.getCliente().getId()).add(new Penalizacion(turno));
        else{
            ArrayList<Penalizacion> penalizaciones = new ArrayList<Penalizacion>();
            penalizaciones.add(new Penalizacion(turno));
            penalizacionesPorCliente.put(turno.getCliente().getId(),penalizaciones);
        }

        turno.getCliente().notificar("Haz recibido una penalizacion");
        turno.getPeluqueria().notificar("Se ha penalizado correctamente al cliente");
    }
}
