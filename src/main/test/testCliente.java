import org.junit.Assert;
import org.junit.Test;

import Modelo.Cliente.Cliente;

public class testCliente {

    @Test
    public void testCrearCliente() {
        Cliente cliente = new Cliente("Juan", "Paso", "JPaso@fi.uba.ar", "11330404");
        Assert.assertEquals("Juan", cliente.getNombre());
        Assert.assertEquals("Paso", cliente.getApellido());
        Assert.assertEquals("JPaso@fi.uba.ar", cliente.getMail());
        Assert.assertEquals("11330404", cliente.getTelefono());
    }
}
