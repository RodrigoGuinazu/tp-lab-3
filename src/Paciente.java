import java.util.ArrayList;

public class Paciente extends Usuario{

    //Atributos

    private ArrayList<Tratamiento> historialClinico = new ArrayList<Tratamiento>();
    private Tratamiento tratamientoActual;
    private Integer idMedicoAsignado;
    private Boolean debeSerAtendido;

    //Constructores



    //con medico asignado
    public Paciente(String nombre, String apellido, String mail, String password, Integer idMedicoAsignado) {
        super(nombre, apellido, mail, password);
        this.idMedicoAsignado = idMedicoAsignado;
        this.debeSerAtendido = true;
    }

    //Metodos

    public void notificarPaciente(){
        //recorrer lista de acciones y llamar funcion notificar en accion
        // mostrar solamente las accioens que no esten hechas
    }


    public void realizarAcciones(){

        //cheqeuar fecha finalizacion menor o igual a actual
        // si es mayor pasar el tratamiento al historial clinico y setear tratamiento como null
        // recibir lista de acciones y modificar aca dentro y qeu get acciones devuelva una lista de acciones
        this.tratamientoActual.realizarAcciones();

    }

    public void getId(){

        this.tratamientoActual.realizarAcciones();

    }





    @Override
    public String toString() {
        return super.toString() +
                "Paciente{" +
                ", idMedicoAsignado=" + idMedicoAsignado +
                '}';
    }
}
