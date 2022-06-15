package Modelo.FormaDeNotificar;

public class Whatsapp implements IFormaDeNotificar{
    private String telefono;

    public Whatsapp(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public void notificar(String mensaje) {
        System.out.println("Se envia un mensaje <"+mensaje+"> al whatsapp con numero <"+telefono+">");
    }
}
