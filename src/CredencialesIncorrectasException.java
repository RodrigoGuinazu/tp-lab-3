public class CredencialesIncorrectasException extends Exception{
    public CredencialesIncorrectasException() {
        System.out.println("Credenciales Incorrectas");
    }

    public CredencialesIncorrectasException(String mail) {
        System.out.println("Contraseña Incorrecta");
    }
}
