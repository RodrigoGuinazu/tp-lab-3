public class Paciente extends Usuario{

    //Atributos
    private Boolean conTratamiento = false;
//    ArrayList<Tratamientos> historialClinico = new ArrayList<Tratamientos>();
//    private Tratamiento tratamientoActual;
    private Integer idMedicoAsignado;

    //Constructores

    //con medico asignado
    public Paciente(String nombre, String apellido, String mail, String password, Integer idMedicoAsignado) {
        super(nombre, apellido, mail, password);
        this.idMedicoAsignado = idMedicoAsignado;
    }

    //Metodos

    @Override
    public String toString() {
        return super.toString() +
                "Paciente{" +
                "conTratamiento=" + conTratamiento +
                ", idMedicoAsignado=" + idMedicoAsignado +
                '}';
    }
}
