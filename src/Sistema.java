import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

// archivos qeu van a existir: admin,medico,paciente,tratamientos,acciones,enfermedades

public class Sistema { // deberia ser static/abstract/final?
    Scanner scan = new Scanner(System.in);
    // Atributos
    private ArrayList<Usuario> usuarios;
    private ArrayList<Tratamiento> tratamientos;
    private ArrayList<Accion> acciones;
    private ArrayList<Enfermedad> enfermedades;
    private LocalDate fechaDelDia;
    private Usuario usuarioLogueado;    //solo uno a la vez

    // Constructores

    public Sistema() {
    }

    //solo usuarios lo demas cuando entra admin o medico
    //cargar con metodos
    public Sistema(ArrayList<Usuario> usuarios, ArrayList<Tratamiento> tratamientos, ArrayList<Accion> acciones, ArrayList<Enfermedad> enfermedades, LocalDate fechaDelDia) {
        this.usuarios = usuarios;
        this.tratamientos = tratamientos;
        this.acciones = acciones;
        this.enfermedades = enfermedades;
        this.fechaDelDia = LocalDate.now();
        this.usuarioLogueado = null;
    }

    // Metodos
    public void menu() {    //ASIGNAR LOGIN (quitar menu y que lo haga de una)

        this.usuarioLogueado = login();

        if (this.usuarioLogueado instanceof Paciente) {
            this.menuPaciente();
        } else if (this.usuarioLogueado instanceof Medico) {
            this.menuMedico();
        } else {
            this.menuAdmin();
        }
    }


    public void menuPaciente() { //
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
            }
        } while (opcion != 0);
    }

    public void menuMedico() {
        int opcion;
        do {
//            ((Medico) usuarioLogueado).setup(); levanta info lista

            ((Medico) usuarioLogueado).notificarMedico();
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


    public Usuario login() {
        //pedir lso datos
        //recorrer la lsita y validar
        // ver si coinciden (try / catch) buscando por mail en lista de usuario y le decimos hola "nombre" y que ingrese la pass
        Usuario user = new Usuario("Carlos", "tikiti", "adsa", "asd");
        return user;
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