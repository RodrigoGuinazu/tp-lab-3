public class MailRepetidoException extends Exception{
    public MailRepetidoException() {
        System.out.println("Ese mail ya se encuentra en uso, intenta con otra direccion de email");
    }
}
