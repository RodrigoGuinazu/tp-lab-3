import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Sistema {
    Scanner scan = new Scanner(System.in);  //no se puede scan.close(); pero en Main si

    // Atributos
    private ArrayList<Usuario> usuarios;
    private LocalDate fechaDelDia;      //No se esta usando
    private Usuario usuarioLogueado; //solo uno a la vez

    // Constructores

    //Constructor 1
    public Sistema() {
        this.usuarios = new ArrayList<>();
        this.fechaDelDia = LocalDate.now();
        this.usuarioLogueado = null;
    }


    //Constructor 2 cargar con metodos
//    public Sistema(ArrayList<Usuario> usuarios) {
//        this.usuarios = usuarios;
//        this.fechaDelDia = LocalDate.now();
//        this.usuarioLogueado = null;
//    }

    // Metodos
    public void menu() {
        ArrayList<Medico> medicosAux = Persistencia.deserializacion("medicos.json", Medico.class);
        ArrayList<Paciente> pacientesAux = Persistencia.deserializacion("pacientes.json", Paciente.class);
        this.usuarios.addAll(medicosAux);
        this.usuarios.addAll(pacientesAux);
        Scanner scan = new Scanner(System.in);

        int opcionMenu = 0;
        do {
            System.out.println("[1] Log In");
            System.out.println("[2] Salir del programa");
            System.out.println("Ingrese una opcion");
//            scan.nextInt();
            opcionMenu = scan.nextInt();

            switch (opcionMenu) {
                case 1: //log in
                    this.usuarioLogueado = this.login();
                    if (this.usuarioLogueado instanceof Paciente) {
                        this.menuPaciente();
                    } else if (this.usuarioLogueado instanceof Medico) {
                        this.menuMedico();
                    } else {
                        this.menuAdmin();
                    }
                    break;
                case 2: //salir del programa
                    System.out.println("Cerrando...Adios");
                    break;

                default:
                    System.out.println("Opcion incorrecta, ingrese otra");
            }

        } while (opcionMenu != 2);


//        int salir = 1;
//        while(salir != 0){
//            this.usuarioLogueado = this.login();
//            if (this.usuarioLogueado instanceof Paciente) {
//                this.menuPaciente();
//            } else if (this.usuarioLogueado instanceof Medico) {
//                this.menuMedico();
//            } else {
//                this.menuAdmin();
//            }
//
//            System.out.println("Volviendo al login.Ingresa 0 para salir del programa, cualquier otro numero para volver a loguearse");  //cambiar a s/n (desea salir) algo asi
//            salir = scan.nextInt();
//        }

    }


    public void menuPaciente() {
        int opcion;
        do {
            ((Paciente) usuarioLogueado).notificarPaciente();
            System.out.println("[1] Realizar acciones del dia\"");
            System.out.println("[2] Modificar acciones del dia\"");
            //mostrar devuelta las acciones del dia para hacer
            System.out.println("[3] Log Out");
            System.out.println("Ingrese una opcion o 0 para salir: ");
            opcion = scan.nextInt();

            switch (opcion) {
                case 1:

                case 2:
                    ((Paciente) usuarioLogueado).realizarAcciones();
                    break;

                case 3:
                    logout();
                    opcion = 0;
                    break;

//                default:
//                    System.out.println("Opcion incorrecta vuelva a intentar");
//                    break;
            }

        } while (opcion != 0);
    }

    public void menuMedico() {
        int opcion;
        do {
            System.out.println("hola1");
            System.out.println(((Medico) usuarioLogueado).notificarMedico());
            System.out.println("hola2");
            System.out.println("[1] Asingar tratamiento");
            System.out.println("[2] Ver historiales de los pacientes");
            System.out.println("[3] Log Out");

            System.out.println("Ingrese una opcion o 0 para salir: ");
            opcion = scan.nextInt();

            switch (opcion) {
                case 1:
                    ((Medico) usuarioLogueado).diagnosticarPacientes();
                    break;

                case 2:
                    ((Medico) usuarioLogueado).verHistorialPaciente();
                    break;

                case 3:
                    logout();
                    opcion = 0;
                    break;
            }
        } while (opcion != 0);
    }

    public void menuAdmin() {
        int opcion;
        do {
            System.out.println("[1] Registrar un nuevo paciente");
            System.out.println("[2] Registrar un nuevo medico");
            System.out.println("[3] Agregar una nueva enfermedad");
            System.out.println("[4] Crear un nuevo tratamiento");
            System.out.println("[5] Editar un tratamiento");
            System.out.println("[6] Log Out");

            System.out.println("Ingrese una opcion o 0 para salir: ");
            opcion = scan.nextInt();

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
                    ((Admin) usuarioLogueado).editarTratamiento();
                }
                break;

                case 6: {
                    logout();
                    opcion = 0;
                }
                break;
            }
        } while (opcion != 0);
    }

    public Usuario login() { // Si entra mal pass mantener el mail, no volver a pedirlo
        Scanner scan = new Scanner(System.in);
        System.out.println("Bienvenido al TP LAB III\n");
        System.out.println("Log In\n");
        Usuario rta = null;
        while (rta == null) {

            System.out.println("Mail: ");
            String mail = "lorem@hotmail.com";
//            String mail = scan.nextLine();


            System.out.println("Contraseña: ");
//            String pass = scan.nextLine();
            String pass = "Cocodrilo";


            try {
                rta = validarCredenciales(mail, pass);
            } catch (CredencialesIncorrectasException e) {
                System.out.println(e);
            }
        }
        return rta;
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


    //metodos sistema para cada menu y l oqeu tenga qeu hacer (cargar, mostrar)
    public void notificarMedicos() {

    }

    public void notificarPacientes() {

    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

}