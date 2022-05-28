public class Admin extends Usuario implements Tratamientos{

    //Atributos

    //Constructor
    public Admin(String nombre, String apellido, String mail, String password) {
        super(nombre, apellido, mail, password);
    }

    //Metodos


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
