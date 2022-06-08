import java.util.ArrayList;

public class Paciente extends Usuario {

    //Atributos

    private ArrayList<Tratamiento> historialClinico = new ArrayList<>();
    protected Tratamiento tratamientoActual;
    private Integer idMedicoAsignado;
    private Boolean debeSerAtendido;


    public Paciente() {
        super();
        this.historialClinico = null;
        this.tratamientoActual = null;
        this.idMedicoAsignado = null;
        this.debeSerAtendido = null;
    }

    // Vacio



    // Completo
    public Paciente(String nombre, String apellido, String dni, String mail, String password, Integer idMedicoAsignado) {
        super(nombre, apellido, dni, mail, password);
        this.idMedicoAsignado = idMedicoAsignado;
        this.debeSerAtendido = true;
    }

    //Metodos

    public void notificarPaciente() {
        //recorrer lista de acciones y llamar funcion notificar en accion
        // mostrar solamente las accioens que no esten hechas
    }


    public void realizarAcciones() {

        // cheqeuar fecha finalizacion menor o igual a actual
        // si es mayor pasar el tratamiento al historial clinico y setear tratamiento como null
        // recibir lista de acciones y modificar aca dentro y qeu get acciones devuelva una lista de acciones

        this.tratamientoActual.realizarAcciones();
    }

    public void setDebeSerAtendido(Boolean debeSerAtendido) {
        this.debeSerAtendido = debeSerAtendido;
    }


    public String getinfoPaciente() {

        return super.toStringInfoNoSensible();
    }

    public Integer getId() {

        return super.getId();
    }

    public Tratamiento getTratamientoActual() {
        return tratamientoActual;
    }

    public void setHistorialClinico(ArrayList<Tratamiento> historialClinico) {
        this.historialClinico = historialClinico;
    }

    public void asdasd() {

        this.tratamientoActual.realizarAcciones();

    }


    public Boolean getDebeSerAtendido() {
        return debeSerAtendido;
    }

    public String toStringHistorial() {
        StringBuilder string = new StringBuilder();
        for (Tratamiento t : this.historialClinico) {
            string.append(t.getEnfermedad().toString() + "\n");
        }
        return string.toString();
    }


    @Override
    public String toString() {
        return "Paciente{" +
                super.toString() + " " +
                ", idMedicoAsignado=" + idMedicoAsignado +
                ", tratamientoActual=" + tratamientoActual +
                ", debeSerAtendido=" + debeSerAtendido +
                ", historialClinico=" + historialClinico +
                '}';
    }
}
