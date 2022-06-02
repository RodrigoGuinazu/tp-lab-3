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

    }

    public void verHistorialPaciente() {

    }

    @Override
    public void crearTratamiento() {

    }

    @Override
    public void editarTratamiento() {

    }

    @Override
    public String toString() {
        return super.toString() +
                "Medico{" +
                "pacientesDelMedico=" + pacientesDelMedico +
                '}';
    }
}
