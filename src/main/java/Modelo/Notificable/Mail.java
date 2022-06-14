package Modelo.Notificable;

public class Mail implements IFormaDeNotificar{
    private String mail;

    public Mail(String mail) {
        this.mail = mail;
    }

    @Override
    public void notificar(String mensaje ) {
        System.out.println("Se envia un mensaje: "+mensaje+" al email de direccion:"+mail);
    }
}
