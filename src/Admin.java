public class Admin extends Usuario implements Tratamientos{

    //Atributos


    //Constructor
    public Admin(String nombre, String apellido, String dni, String mail, String password) {
        super(nombre, apellido, dni, mail, password);
    }


    //Metodos

    public void registrarPaciente(){
        // levantar archivo usuarios
        //fijarse que exista el usuario y si existe setear debe ser atendido en true
        //sino lo crea
        // agregar usuario
        //persistir
    }

    public void registrarMedico(){
        // levantar archivo medico
        // agregar medico
        // persistir
    }

    public void agregarEnfermedad(){
        // levantar archivo enfermedad
        // agregar enfermedad
        // persistir
    }

    @Override
    public Tratamiento crearTratamiento() {
        // levantar archivo Tratameintos
        // agregar tratemiento
        //persistir
        return null;
    }

    @Override
    public void editarTratamiento() {
        // levantar archivo Tratameintos
        // editar tratamiento
        //persistir
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
