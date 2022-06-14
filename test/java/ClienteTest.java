package java;



import org.junit.Assert;
import org.junit.Test;

import java.Cliente.Cliente;

class ClienteTest {

    @Test
    void testCrearCliente(){
        Cliente cliente = new Cliente("Juan","Paso","JPaso@fi.uba.ar","11330404");
        Assert.assertEquals("Juan", cliente.getNombre());
        Assert.assertEquals("Paso", cliente.getApellido());
        Assert.assertEquals("JPaso@fi.uba.ar", cliente.getMail());
        Assert.assertEquals("11330404", cliente.getTelefono());
    }
}