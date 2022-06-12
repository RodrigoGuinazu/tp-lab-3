import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Admin extends Usuario implements Tratamientos {

    //Sin atributos

    //Constructor
    public Admin(String nombre, String apellido, String dni, String mail, String password) {
        super(nombre, apellido, dni, mail, password);
    }


    //Metodos
    public void registrarPaciente() {
        Scanner scan = new Scanner(System.in);
        Paciente rta;
        // levantar archivo usuarios

        ArrayList<Paciente>pacientes = Persistencia.deserializacionPacientes();
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
        Persistencia.serializacionPacientes(pacientes);
    }

    public void registrarMedico() {
        Scanner scan = new Scanner(System.in);
        Medico rta;
        // levantar archivo usuarios
        ArrayList<Medico> medicos = Persistencia.deserializacion("medicos.json", Medico.class);
        ArrayList<Usuario> aux = new ArrayList<Usuario>();
        aux.addAll(medicos);
        System.out.println("Registrando un Medico:");
        System.out.println("Ingrese el dni del medico");
        String dni = scan.nextLine();
        //fijarse que exista el usuario
        try {
            this.buscarUsuario(aux, dni);
            System.out.println("El Medico ya existe, no hace falta registrarlo de vuelta");
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
                    flag = 1;
                    rta = new Medico(nombre, apellido, dni, mail, password);
                    medicos.add(rta);
                } catch (InputMismatchException f) {
                    System.out.println(f);
                }
            }
        }
        //persistir
        Persistencia.serializacion(medicos, "medicos.json");
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
        mostrarEnfermedadesArchivo();

        System.out.println("Ingrese nueva enfermedad : ");
        Scanner scan = new Scanner(System.in);
        String nombreValido = nombreRepetidoEnfermedad(scan.nextLine());

        enfermedades.add(new Enfermedad(nombreValido));
        Persistencia.serializacion(enfermedades, "enfermedades.json");
    }

    @Override
    public Tratamiento crearTratamiento() { //falta ver si el tratamiento ya existe

        Scanner scan = new Scanner(System.in);
        ArrayList<Enfermedad> enfermedades = Persistencia.deserializacion("enfermedades.json", Enfermedad.class);


        // nombre enfermedad para tratamiento (2 opciones : la crea o la elige)
        Enfermedad enfermedad = null;
        int opcion;
        do{
            System.out.println("[1] Elegir enfermedad existente");
            System.out.println("[2] Crear nueva enfermedad");
            System.out.println("Ingrese una opcion:");


            opcion = scan.nextInt();
            switch (opcion){
                case 1: {

                    mostrarEnfermedadesArchivo();
                    System.out.println("Ingrese el numero de la enfermedad");
                    int indice = scan.nextInt();
                    enfermedad = enfermedades.get(indice - 1);  // falta validar indice
                    System.out.println("Se eligio la enfermedad : "+enfermedad.getNombre());
                    opcion = 0;
                    break;
                }
                case 2:
                    System.out.println("Creando nueva enfermedad, ingrese nombre");
                    scan.nextLine();
                    String nombreValido = nombreRepetidoEnfermedad(scan.nextLine());
                    Enfermedad nuevaEnfermedad = new Enfermedad(nombreValido);
                    enfermedades.add(nuevaEnfermedad);
                    System.out.println("Se agrego nueva enfermedad a enfermedades");
                    Persistencia.serializacion(enfermedades, "enfermedades.json");
                    enfermedad = nuevaEnfermedad;
                    opcion =0;
                    break;
            }
        }while (opcion !=0);

        // duracion
        System.out.println("Ingrese duracion del tratamiento");
        Integer duracion = scan.nextInt();


        //crear lista de acciones o elegir uno existente --> falta ver como son las acciones

        ArrayList<Accion> acciones = new ArrayList<>();
        //acciones.add(new Accion(1,2,"hola","aqdios"));
        Tratamiento nuevoTratamiento = new Tratamiento(enfermedad,duracion,acciones);
        System.out.println("Nuevo tratamiento a crear" + nuevoTratamiento);

        return null;
    }

    @Override
    public Tratamiento editarTratamiento(Tratamiento aux){
        Scanner scan = new Scanner(System.in);
        // levantar archivo Tratamientos
        ArrayList<Tratamiento> listaTratamientosGenericos = Persistencia.deserializacionTratamientos();

        // seleccionar el tratamiento a editar
        int x = 0;
        for (Tratamiento a : listaTratamientosGenericos) {
            System.out.println("[" + x + "] " + a.toStringMedico());
            x++;
        }
        System.out.println("Ingrese el numero del tratamiento que desea modificar: ");
        aux = listaTratamientosGenericos.get(scan.nextInt()).clonarTratamiento();

        //editar tratamiento
        Medico medicoAux = new Medico();
        medicoAux.editarTratamiento(aux);

        //persistir
        Persistencia.serializacionTratamientos(listaTratamientosGenericos);
        return aux;
    }


    public String nombreRepetidoEnfermedad(String nuevoNombre){
        ArrayList<Enfermedad> enfermedades = Persistencia.deserializacion("enfermedades.json", Enfermedad.class);
        Scanner scan = new Scanner(System.in);
        for (Enfermedad e : enfermedades) {
            while (nuevoNombre.equalsIgnoreCase(e.getNombre())) {
                System.out.println("Enfermedad ya cargada, ingrese otra");
                nuevoNombre = scan.nextLine();
            }
        }
        return nuevoNombre;
    }

    public void mostrarEnfermedadesArchivo(){
        Integer contador=1;
        ArrayList<Enfermedad> enfermedades = Persistencia.deserializacion("enfermedades.json", Enfermedad.class);
        System.out.println("Enfermedades persistidas : ");
        for (Enfermedad e : enfermedades) {
            System.out.print(contador + " ");
            System.out.println(e.mostrarEnfermedad());
            contador++;
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
