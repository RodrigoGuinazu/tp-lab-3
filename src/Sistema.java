import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Sistema { // deberia ser static/abstract/final?
    Scanner scan = new Scanner(System.in);
    // Atributos
    protected ArrayList<Usuario> usuarios;
    protected ArrayList<Tratamiento> tratamientos;
    protected ArrayList<Accion> acciones;
    protected ArrayList<Enfermedad> enfermedades;
    protected LocalDate fechaDelDia;
    protected Usuario usuarioLogueado;

    // Constructores
    public Sistema(ArrayList<Usuario> usuarios, ArrayList<Tratamiento> tratamientos, ArrayList<Accion> acciones, ArrayList<Enfermedad> enfermedades, LocalDate fechaDelDia) {
        this.usuarios = usuarios;
        this.tratamientos = tratamientos;
        this.acciones = acciones;
        this.enfermedades = enfermedades;
        this.fechaDelDia = fechaDelDia;
        this.usuarioLogueado = null;
    }

    // Metodos
    public void menu() {
        int opcion;
        do{
            System.out.println("[1] Log In");

            System.out.println("Ingrese una opcion o 0 para salir: ");
            opcion = scan.nextInt();

            switch(opcion){
                case 1 : {
                    //login();
                    if(this.usuarioLogueado instanceof Paciente){
                        menuPaciente();
                    }else if(this.usuarioLogueado instanceof Medico){
                        menuMedico();
                    }else{
                        menuAdmin();
                    }
                }break;
            }
        }while(opcion != 0);
    }

    public void menuPaciente(){
        int opcion;
        do{
            System.out.println("[1] Ver notificaciones");
            System.out.println("[1] Log Out");

            System.out.println("Ingrese una opcion o 0 para salir: ");
            opcion = scan.nextInt();

            switch(opcion){
                case 1 : {
                    //notificarPacientes();
                }break;

                case 2 : {
                    //logout();
                }break;
            }
        }while(opcion != 0);
    }

    public void menuMedico(){
        int opcion;
        do{
            System.out.println("[1] Asingar tratamiento");
            System.out.println("[2] Crear un nuevo tratamiento");
            System.out.println("[3] Editar un tratamiento");
            System.out.println("[4] Ver historiales de los pacientes");
            System.out.println("[5] Log Out");

            System.out.println("Ingrese una opcion o 0 para salir: ");
            opcion = scan.nextInt();

            switch(opcion){
                case 1 : {
                    //this.usuarioLogueado.asignarTratamiento();
                }break;

                case 2 : {
                    //this.usuarioLogueado.crearTratamiento();
                }break;

                case 3 : {
                    //this.usuarioLogueado.editarTratamiento();
                }break;

                case 4 : {
                    //this.usuarioLogueado.verHistoriales();
                }break;

                case 5 : {
                    //logout();
                }break;
            }
        }while(opcion != 0);
    }

    public void menuAdmin(){
        int opcion;
        do{
            System.out.println("[1] Registrar un nuevo paciente");
            System.out.println("[2] Registrar un nuevo medico");
            System.out.println("[3] Agregar una nueva enfermedad");
            System.out.println("[4] Crear un nuevo tratamiento");
            System.out.println("[5] Editar un tratamiento");
            System.out.println("[6] Log Out");

            System.out.println("Ingrese una opcion o 0 para salir: ");
            opcion = scan.nextInt();

            switch(opcion){
                case 1 : {
                    //this.usuarioLogueado.registrarPaciente();
                }break;

                case 2 : {
                    //this.usuarioLogueado.registrarMedico();
                }break;

                case 3 : {
                    //this.usuarioLogueado.agregarEnfermedad();
                }break;

                case 4 : {
                    //this.usuarioLogueado.crearTratamiento();
                }break;

                case 5 : {
                    //this.usuarioLogueado.editarTratamiento();
                }break;

                case 6 : {
                    //logout();
                }break;
            }
        }while(opcion != 0);
    }


    public void login(String mail, String pass){

    }

    public void logout(){

    }

    public void notificarMedicos(){

    }

    public void notificarPacientes(){

    }
}