public class Admin extends Usuario implements Tratamientos{

    //Atributos

    //Constructor
    public Admin(String nombre, String apellido, String mail, String password) {
        super(nombre, apellido, mail, password);
    }

    //Metodos

    public void registrarPaciente(){

    }

    public void registrarMedico(){

    }

    public void agregarEnfermedad(){

    }
    @Override
    public void crearTratamiento() {

    }

    @Override
    public void editarTratamiento() {

    }

    @Override
    public String toString() {
        return super.toString();
    }
}
