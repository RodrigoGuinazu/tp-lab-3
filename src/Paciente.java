import java.util.ArrayList;

public class Paciente extends Usuario{

    //Atributos

    private ArrayList<Tratamiento> historialClinico = new ArrayList<Tratamiento>();
    private Tratamiento tratamientoActual;
    private Integer idMedicoAsignado;

    //Constructores



    //con medico asignado
    public Paciente(String nombre, String apellido, String mail, String password, Integer idMedicoAsignado) {
        super(nombre, apellido, mail, password);
        this.idMedicoAsignado = idMedicoAsignado;
    }

    //Metodos

    public void notificarPaciente(){

    }

    public void realizarAcciones(){

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
