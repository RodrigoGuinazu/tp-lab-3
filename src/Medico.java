import java.util.ArrayList;

public class Medico extends Usuario implements Tratamientos{

    private ArrayList<Integer> pacientesDelMedico = new ArrayList<>();

    //Constructores
    //sin parametro lista de ids
    public Medico(String nombre, String apellido, String mail, String password) {
        super(nombre, apellido, mail, password);
    }

    //Recibe lista de ids
    public Medico(String nombre, String apellido, String mail, String password, ArrayList<Integer> pacientesDelMedico) {
        super(nombre, apellido, mail, password);
        this.pacientesDelMedico = pacientesDelMedico;
    }

    //Metodos

    public void notificarMedico() {
        //levantar el archivo de pacientes
        //entra paciente por paciente por id y verifica qeu tratamiento

    }


    public void verHistoriales() {

    }
    public ArrayList<Integer> obtenerIDsPacientes() {
        ArrayList <Integer> aux = new ArrayList();
        for (Integer a : pacientesDelMedico){
            aux.add(a);
        }
        return pacientesDelMedico;
    }

    public void diagnosticarPacientes() {
        // levantamos de archivo lista pacientes en lista aux (levantar tratamientos,acciones,enfermedades)
        // buscamos por id de paciente en la lista auxiliar
        // modificamos en paciente aca podria elegir modificarlo
        //setear debe ser atendido como false
        // persistimso en archivo
    }

    public void verHistorialPaciente() {
        //mas o menos como diagnosticarPacientes() { sin modificar
    }

    @Override
    public void crearTratamiento() {
        //llamar a cosntreuctor de tratamiento
        //no se persiste
    }

    @Override
    public void editarTratamiento() {
       // se modifica uno ya existente de la lista
        //no se persiste
    }

    @Override
    public String toString() {
        return super.toString() +
                "Medico{" +
                "pacientesDelMedico=" + pacientesDelMedico +
                '}';
    }
}
