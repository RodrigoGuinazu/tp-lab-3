public class UsuarioInexistenteException extends Exception {
    public UsuarioInexistenteException() {
        System.out.println("No existe el usuario, creando uno nuevo...");
    }
}
