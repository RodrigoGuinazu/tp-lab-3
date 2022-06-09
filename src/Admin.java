import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Admin extends Usuario implements Tratamientos {

    // Sin atributos


    //Constructor
    public Admin(String nombre, String apellido, String dni, String mail, String password) {
        super(nombre, apellido, dni, mail, password);
    }


    //Metodos
    public void registrarPaciente() {
        Scanner scan = new Scanner(System.in);
        Paciente rta;
        // levantar archivo usuarios
        ArrayList<Paciente> pacientes = Persistencia.deserializacion("pacientes.json", Paciente.class);
        ArrayList<Usuario> aux = new ArrayList<Usuario>();
        aux.addAll(pacientes);
        System.out.println("Registrando un paciente:");
        System.out.println("Ingrese el dni del paciente");
        String dni = scan.nextLine();
        //fijarse que exista el usuario
        try {
            // si existe le cambio el atributo debeSerAtendido a true
            rta = (Paciente) this.buscarUsuario(aux, dni);
            // verifico que no tenga un tratamiento en curso
            if (rta.getTratamientoActual() == null) {
                System.out.println("El paciente ya existe, asignarle un medico...");
                // asignar medico
                Integer id = null;
                while(id == null){
                    try{
                        id = asignarMedico(rta.getApellido(), rta.getNombre());
                    }catch(MedicoInexistenteException m){
                        System.out.println(m);
                    }catch(InputMismatchException e){
                        System.out.println(e);
                    }
                }
                rta.setIdMedicoAsignado(id);
                rta.setDebeSerAtendido(true);
            } else {
                System.out.println("El paciente ya se encuentra con un tratamiento vigente, debe terminar el mismo para generar una nueva visita");
            }
        } catch (UsuarioInexistenteException e) {
            // si no lo creo y lo agrego
            System.out.println(e);
            int flag = 0;
            while (flag == 0) {
                try {
                    System.out.println("Nombre: ");
                    String nombre = scan.nextLine();
                    System.out.println("Apellido: ");
                    String apellido = scan.nextLine();
                    System.out.println("Mail: ");
                    String mail = scan.nextLine();
                    System.out.println("Password: ");
                    String password = scan.nextLine();
                    //asignar medico
                    Integer id = null;
                    while(id == null){
                        try{
                            id = asignarMedico(apellido, nombre);
                        }catch(MedicoInexistenteException m){
                            System.out.println(m);
                        }
                    }
                    flag = 1;
                    rta = new Paciente(nombre, apellido, dni, mail, password, id);
                    pacientes.add(rta);
                } catch (InputMismatchException f) {
                    System.out.println(f);
                }
            }
        }
        //persistir
        Persistencia.serializacion(pacientes, "pacientes.json");
    }

    public void registrarMedico() {
        // levantar archivo medico
        // agregar medico
        // persistir
    }


    private Usuario buscarUsuario(ArrayList<Usuario> listado, String dni) throws UsuarioInexistenteException {
        for (Usuario u : listado) {
            if (u.getDni().equals(dni)) {
                return u;
            }
        }
        throw new UsuarioInexistenteException();
    }

    private Integer asignarMedico(String apellido, String nombre) throws MedicoInexistenteException{
        Scanner scan = new Scanner(System.in);
        System.out.println("Elegir un medico para " + nombre + " " + apellido);
        ArrayList<Medico> medicos = Persistencia.deserializacion("medicos.json", Medico.class);
        for(Medico m : medicos){
            System.out.println("[ ID: " + m.getId() + "]" + " Dr. " + m.getNombre() + " " + m.getApellido());
        }
        System.out.println("Ingrese el id del medico que desea asignar");
        Integer rta = scan.nextInt();
        for(Medico m : medicos){
            if(m.getId() == rta){
                return rta;
            }
        }
        throw new MedicoInexistenteException();
    }

    public void agregarEnfermedad() {
        ArrayList<Enfermedad> enfermedades = Persistencia.deserializacion("enfermedades.json", Enfermedad.class);
        System.out.println("Enfermedades actuales : ");
        for (Enfermedad e : enfermedades) {
            System.out.println(e.mostrarEnfermedad());
        }
        Scanner scan = new Scanner(System.in);
        System.out.println("Ingrese nueva enfermedad : ");
        String nuevoNombre = scan.nextLine();
        for (Enfermedad e : enfermedades) {
            while (nuevoNombre.equalsIgnoreCase(e.getNombre())) {
                System.out.println("Enfermedad ya cargada, ingrese otra");
                nuevoNombre = scan.nextLine();
            }
        }
        Enfermedad enfermedadNueva = new Enfermedad(nuevoNombre);
        enfermedades.add(enfermedadNueva);
        Persistencia.serializacion(enfermedades, "enfermedades.json");
    }

    @Override
    public Tratamiento crearTratamiento() {

        ArrayList<Tratamiento> tratamientos = Persistencia.deserializacion("tratamientos.json", Tratamiento.class);

        System.out.println(tratamientos);


        // levantar archivo Tratameintos
        // agregar tratemiento
        //persistir

        return null;
    }

    @Override
    public Tratamiento editarTratamiento(Tratamiento x) {
        // levantar archivo Tratameintos
        // editar tratamiento
        //persistir
        return null;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
