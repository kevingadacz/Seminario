package Modelo.SistemaDeTurnosPeluqueria;

import Modelo.Cliente.Cliente;
import Modelo.Peluqueria.Peluqueria;
import Modelo.Servicio.Servicio;

import java.util.ArrayList;

public final class SistemaDeTurnosPeluqueria {
    private ArrayList<Cliente> clientes;
    private ArrayList<Peluqueria> peluquerias;
    private static SistemaDeTurnosPeluqueria _sistema;

    public SistemaDeTurnosPeluqueria getSistema() {
        if (_sistema != null)
            return _sistema;
        else{
            _sistema = new SistemaDeTurnosPeluqueria();
            return _sistema;
        }
    }

    private SistemaDeTurnosPeluqueria() {
        this.clientes = new ArrayList<>();
        this.peluquerias = new ArrayList<>();
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public ArrayList<Peluqueria> getPeluquerias() {
        return peluquerias;
    }

    public ArrayList<Peluqueria> buscarPeluqueriasPorServicio(Servicio servicio){
        ArrayList<Peluqueria> peluqueriasbuscadas = new ArrayList<Peluqueria>();
        for (Peluqueria peluqueria : peluquerias) {if(peluqueria.getServicios().contains(servicio))peluqueriasbuscadas.add(peluqueria);}
        return peluqueriasbuscadas;
    }

    public void agregarCliente(String nombre, String apellido, String mail, String telefono){
        clientes.add(new Cliente(nombre, apellido, mail,telefono));
    }

    public void agregarPeluqueria(String nombre, String direccion, String telefono,String email){
        peluquerias.add(new Peluqueria(nombre, direccion,telefono, email));
    }
}

