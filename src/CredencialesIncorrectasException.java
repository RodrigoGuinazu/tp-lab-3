public class CredencialesIncorrectasException extends Exception {
    public CredencialesIncorrectasException() {
        System.out.println("Por favor, vuelva a introducir sus credenciales...");
    }

    public CredencialesIncorrectasException(String mail) {
        System.out.println("Contraseña Incorrecta");
    }

}
