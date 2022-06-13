public class MailRepetidoException extends Exception{
    public MailRepetidoException() {
        System.out.println(Colores.amarillo() + "Ese mail ya se encuentra en uso, intenta con otra direccion de email" + Colores.blanco());
    }
}
