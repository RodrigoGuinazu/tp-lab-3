import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Sistema {
    Scanner scan = new Scanner(System.in);  //no se puede scan.close(); pero en Main si

    // Atributos
    private ArrayList<Usuario> usuarios;
    private LocalDate fechaDelDia;      //No se esta usando
    private Usuario usuarioLogueado;

    //Constructor 1
    public Sistema() {
        this.usuarios = new ArrayList<>();
        this.fechaDelDia = LocalDate.now();
        this.usuarioLogueado = null;
    }

    // Metodos
    public void menu() {
        // escondo los warning
        System.err.close();
        System.setErr(System.out);
        // generamos la lista de usuarios para poder utilizarla en la logica del login
        ArrayList<Medico> medicosAux = Persistencia.deserializacion("medicos.json", Medico.class);
        ArrayList<Paciente> pacientesAux = Persistencia.deserializacionPacientes();
        ArrayList<Admin> adminsAux = Persistencia.deserializacion("admins.json", Admin.class);
        this.usuarios.addAll(medicosAux);
        this.usuarios.addAll(pacientesAux);
        this.usuarios.addAll(adminsAux);

        Scanner scan = new Scanner(System.in);
        int opcionMenu = 0;

        do {
            System.out.println("Bienvenido al TP LAB III\n");
            System.out.println("[1] Log In");
            System.out.println("[2] Salir del programa");
            System.out.println("Ingrese una opcion:");

            int flagSwitch = 0;
            while(flagSwitch == 0){
                try{
                    opcionMenu = scan.nextInt();
                    flagSwitch = 1;
                }catch (InputMismatchException i) {
                    System.out.println(Colores.rojo() + "Ingresaste un tipo de dato incorrecto, intentalo nuevamente" + Colores.blanco());
                    System.out.println("Ingrese una opcion:");
                    scan.nextLine();
                }
            }

            switch (opcionMenu) {

                case 1:
                    this.usuarioLogueado = this.login();
                    if (this.usuarioLogueado instanceof Paciente) {
                        this.menuPaciente();
                    } else if (this.usuarioLogueado instanceof Medico) {
                        this.menuMedico();
                    } else {
                        this.menuAdmin();
                    }
                    break;

                case 2:
                    System.out.println(Colores.rojo() + "Cerrando el programa..." + Colores.blanco());
                    break;

                default:
                    System.out.println(Colores.rojo() + "Opcion incorrecta, ingrese otra" + Colores.blanco());
            }
        } while (opcionMenu != 2);
    }


    public void menuPaciente() {
        int opcionPaciente = 0;
        ((Paciente) usuarioLogueado).notificarPaciente();
        do {

            System.out.println("[1] Realizar acciones del dia");
            System.out.println("[2] Modificar acciones del dia");
            System.out.println("[3] Mostrar acciones del dia");
            System.out.println("[4] Log Out");
            System.out.println("Ingrese una opcion:");

            int flagSwitch = 0;
            while(flagSwitch == 0){
                try{
                    opcionPaciente = scan.nextInt();
                    flagSwitch = 1;
                }catch (InputMismatchException i) {
                    System.out.println(Colores.rojo() + "Ingresaste un tipo de dato incorrecto, intentalo nuevamente" + Colores.blanco());
                    System.out.println("Ingrese una opcion:");
                    scan.nextLine();
                }
            }

            switch (opcionPaciente) {
                case 1:
                    try {
                        ((Paciente) usuarioLogueado).realizarAcciones();
                        System.out.println(((Paciente) usuarioLogueado).tratamientoActual.listaRegistrosDiarios);
                        persistirPacienteActual(((Paciente) usuarioLogueado));
                    }catch (NullPointerException e){
                        System.out.println(Colores.amarillo() + "No te encuentras realizando un tratamiento" + Colores.blanco());
                    }
                    break;
                case 2:
                    ((Paciente) usuarioLogueado).editarAccionesDelDia();
                    persistirPacienteActual(((Paciente) usuarioLogueado));
                    break;

                case 3:
                    ((Paciente) usuarioLogueado).notificarPaciente();
                    break;

                case 4:
                    this.logout();
                    break;

                default:
                    System.out.println(Colores.rojo() + "Opcion incorrecta, ingrese otra" + Colores.blanco());
            }
        } while (opcionPaciente != 4);
    }


    public void menuMedico() {
        System.out.println(((Medico) usuarioLogueado).notificarMedico());
        int opcionMedico = 0;
        do {
            //mostrar mejor y que cuando oprima una tecla siga con el menu
            System.out.println("[1] Asignar tratamiento");
            System.out.println("[2] Ver las notificaciones del dia");
            System.out.println("[3] Ver historiales de los pacientes");
            System.out.println("[4] Log Out");

            System.out.println("Ingrese una opcion:");

            int flagSwitch = 0;
            while(flagSwitch == 0){
                try{
                    opcionMedico = scan.nextInt();
                    flagSwitch = 1;
                }catch (InputMismatchException i) {
                    System.out.println(Colores.rojo() + "Ingresaste un tipo de dato incorrecto, intentalo nuevamente" + Colores.blanco());
                    System.out.println("Ingrese una opcion:");
                    scan.nextLine();
                }
            }

            switch (opcionMedico) {
                case 1:
                    try {
                        ((Medico) usuarioLogueado).diagnosticarPacientes();
                    } catch (DniInexistenteException e) {
                    }
                    break;

                case 2:
                    System.out.println(((Medico) usuarioLogueado).notificarMedico());
                    break;

                case 3:
                    try {
                        ((Medico) usuarioLogueado).verHistorialPaciente();
                    } catch (DniInexistenteException e) {
                    }
                    break;

                case 4:
                    this.logout();
                    break;

                default:
                    System.out.println(Colores.rojo() + "Opcion incorrecta, ingrese otra" + Colores.blanco());
            }
        } while (opcionMedico != 4);
    }


    public void menuAdmin() {
        int opcion = 0;
        do {
            System.out.println("[1] Registrar un nuevo paciente");
            System.out.println("[2] Registrar un nuevo medico");
            System.out.println("[3] Agregar una nueva enfermedad");
            System.out.println("[4] Crear un nuevo tratamiento");
            System.out.println("[5] Editar un tratamiento");
            System.out.println("[6] Log Out");

            System.out.println("Ingrese una opcion:");

            int flagSwitch = 0;
            while(flagSwitch == 0){
                try{
                    opcion = scan.nextInt();
                    flagSwitch = 1;
                }catch (InputMismatchException i) {
                    System.out.println(Colores.rojo() + "Ingresaste un tipo de dato incorrecto, intentalo nuevamente" + Colores.blanco());
                    System.out.println("Ingrese una opcion:");
                    scan.nextLine();
                }
            }

            switch (opcion) {
                case 1: {
                    ((Admin) usuarioLogueado).registrarPaciente();
                }
                break;

                case 2: {
                    ((Admin) usuarioLogueado).registrarMedico();
                }
                break;

                case 3: {
                    ((Admin) usuarioLogueado).agregarEnfermedad();
                }
                break;

                case 4: {
                    ((Admin) usuarioLogueado).crearTratamiento();
                }
                break;

                case 5: {
                    ((Admin) usuarioLogueado).editarTratamiento(new Tratamiento());
                }
                break;

                case 6: {
                    this.logout();
                }
                break;

                default:
                    System.out.println(Colores.rojo() + "Opcion incorrecta, ingrese otra" + Colores.blanco());
            }
        } while (opcion != 6);
    }

    public Usuario login() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Log In");
        Usuario rta = null;
        while (rta == null) {

            System.out.println("Mail: ");
            String mail = scan.nextLine();    // sin harcodeo
            //String mail = "paciente@outlook.com"; //Harcodeo paciente
            //String mail = "medico@gmail.com"; //Harcodeo medico
            //String mail = "admin@hotmail.com"; //Harcodeo admin


            System.out.println("Contraseña: ");
            String pass = scan.nextLine(); // sin harcodeo
            //String pass = "Chicha"; //Harcodeo paciente
            //String pass = "Cosa"; //Harcodeo medico
            //String pass = "Cusco"; //Harcodeo admin


            try {
                rta = validarCredenciales(mail, pass);
            } catch (CredencialesIncorrectasException e) {
            }
        }
        return rta;
    }

    public void persistirPacienteActual(Paciente logueado) {

        ArrayList<Paciente> pacientes = Persistencia.deserializacionPacientes();
        for (Paciente a : pacientes) {
            if (a.getId().equals(logueado.getId())) {

                pacientes.set(pacientes.indexOf(a), logueado);
            }
        }


        Persistencia.serializacionPacientes(pacientes);

    }

    public Usuario validarCredenciales(String mail, String pass) throws CredencialesIncorrectasException {
        for (Usuario u : this.usuarios) {
            if (u.getMail().equals(mail)) {
                if (u.getPassword().equals(pass)) {
                    return u;
                } else {
                    throw new CredencialesIncorrectasException(mail);
                }
            }
        }
        throw new CredencialesIncorrectasException();
    }

    public void logout() {
        this.usuarioLogueado = null;
    }
}