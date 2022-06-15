package Modelo.SistemaDeTurnosPeluqueria;

import Modelo.Cliente.Cliente;
import Modelo.Peluqueria.Peluqueria;
import Modelo.Penalizador.Penalizador;

import java.util.ArrayList;

public final class SistemaDeTurnosPeluqueria {
    private ArrayList<Cliente> clientes;
    private ArrayList<Peluqueria> peluquerias;
    private static SistemaDeTurnosPeluqueria _sistema;
    private Penalizador penalizador;

    public static SistemaDeTurnosPeluqueria getSistema() {
        if (_sistema != null)
            return _sistema;
        else{
            _sistema = new SistemaDeTurnosPeluqueria();
            return _sistema;
        }
    }

    public Penalizador getPenalizador() {
        return this.penalizador;
    }

    private SistemaDeTurnosPeluqueria() {
        this.clientes = new ArrayList<>();
        this.peluquerias = new ArrayList<>();
        this.penalizador = new Penalizador();
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public ArrayList<Peluqueria> getPeluquerias() {
        return peluquerias;
    }

    public void agregarCliente(String nombre, String apellido, String mail, String telefono){
        clientes.add(new Cliente(nombre, apellido, mail,telefono));
    }

    public void agregarPeluqueria(String nombre, String direccion, String telefono,String email){
        peluquerias.add(new Peluqueria(nombre, direccion,telefono, email));
    }
}

