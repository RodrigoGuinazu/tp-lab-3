public class DniInexistenteException extends Exception{
    public DniInexistenteException(){
        System.out.println("EL dni que ingresaste no pertenece a ningun usuario registrado");
    }

}
