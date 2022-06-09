public class MedicoInexistenteException extends Exception{
    public MedicoInexistenteException() {
        System.out.println("Ese id no pertenece a ningun Medico registrado");
    }
}
