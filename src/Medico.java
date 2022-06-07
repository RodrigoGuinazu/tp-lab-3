import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Medico extends Usuario implements Tratamientos{

    private ArrayList<Integer> pacientesDelMedico = new ArrayList<>();  //ids paciente

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

    public String notificarMedico() {
        //levantar el archivo de pacientes
        //entra paciente por paciente por id y verifica qeu tratamiento
        ArrayList<Paciente> aux = Persistencia.deserializacion("pacientes.json",Paciente.class);
        String rta = "";
        rta += "Pacientes que deben ser atendidos hoy:=" +   '\'' ;
        for (Paciente a : aux) {
            if (a.getDebeSerAtendido()){
                rta += a.getinfoPaciente();
            }
        }
        rta += "Pacientes que no registraron informacion ayer:='" +   '\'' ;

        for (Paciente a : aux) {
            if(a.tratamientoActual != null){
                if (! a.tratamientoActual.existeRegistroDiario(LocalDate.now().minusDays(1))){
                    rta += a.getinfoPaciente();
                }
            }
        }
        return rta;
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

    //metodo configuracion con ids y llenar su propai lista

    public void diagnosticarPacientes() {

        // levantamos de archivo lista pacientes en lista aux (levantar tratamientos,acciones,enfermedades)

        ArrayList<Paciente> listaPacientes = Persistencia.deserializacion("pacientes.json",Paciente.class);
        ArrayList<Tratamiento> listaTratamientosGenericos = Persistencia.deserializacion("tratamientos.json",Tratamiento.class);


        // mostramos al medico todos sus pacientes sin tratamiento

        String rta = "Pacientes que deben ser atendidos hoy:=" +   '\'' ;
        for (Paciente pacientegeneral : listaPacientes) {
            if (pacientegeneral.getDebeSerAtendido()){
                for(Integer a : pacientesDelMedico){
                    if (pacientegeneral.getId().equals(a));
                }
                rta += pacientegeneral.getinfoPaciente();
            }
        }
        System.out.println(rta);
        System.out.println("Ingrese el dni del paciente que desea diagnosticar");
        Scanner scan = new Scanner(System.in);
        int dni = scan.nextInt();
        Paciente pacienteAux = new Paciente();
        for (Paciente a : listaPacientes){
            if (a.getDni()==dni){
                pacienteAux = a;
            }
        }

        //mostramos al medico la lista de tratamientos

        int x = 0;
        for(Tratamiento a : listaTratamientosGenericos){
            System.out.println(x + a.toString());
            x++;
        }

        // preguntamos al medico si queire elegir uno existente o crear uno nuevo

        System.out.println("quiere legir uno de lso tratamientos ya existentes? s/n");
        scan.nextLine();
        String option = scan.nextLine();

        if (option.equals("s")){
            System.out.println("ingrese el numero del tratamiento elegido");
            pacienteAux.tratamientoActual = listaTratamientosGenericos.get(scan.nextInt());

        }
        else{
            pacienteAux.tratamientoActual = crearTratamiento();
        }

        // seteamos fecha de inicio y finde del tratamiento, y debeseratendido en false

        pacienteAux.tratamientoActual.setIncioDate(LocalDate.now());
        pacienteAux.tratamientoActual.setFinDate(LocalDate.now().plusDays(pacienteAux.tratamientoActual.getDuracion()));
        pacienteAux.setDebeSerAtendido(false);

        // finalmente persistimos el archivo de pacientes, para que este sufra modificaciones

        Persistencia.serializacion(listaPacientes,"pacientes.json");



    }

    public void verHistorialPaciente() {
        //mas o menos como diagnosticarPacientes() { sin modificar
    }

    @Override
    public Tratamiento crearTratamiento() {
        Scanner scan = new Scanner(System.in);
        ArrayList<Accion> listaAcciones = Persistencia.deserializacion("acciones.json",Accion.class);
        Tratamiento nuevoTratamiento = new Tratamiento();
        System.out.println("Ingrese la duracion del tratamiento");
        nuevoTratamiento.setDuracion(scan.nextInt());
        System.out.println("Ingrese el numero de acciones que tendra el tratamiento");
        int aux = scan.nextInt();

       for (int i = 0; i<aux; i++){
           for (Accion a :  listaAcciones){
               System.out.println(i + a.toString());
           }
           System.out.println("elija el numero de de la accion que escoja para el tratamiento:");
           nuevoTratamiento.listaAcciones.add(listaAcciones.get(scan.nextInt()));
           // tener en cuenta que sea cada tantos dias
       }

        return nuevoTratamiento;
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
