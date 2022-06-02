import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Sistema { // deberia ser static/abstract/final?
    Scanner scan = new Scanner(System.in);
    // Atributos
    private ArrayList<Usuario> usuarios;
    private ArrayList<Tratamiento> tratamientos;
    private ArrayList<Accion> acciones;
    private ArrayList<Enfermedad> enfermedades;
    private LocalDate fechaDelDia;
    private Usuario usuarioLogueado;

    // Constructores

    public Sistema() {
    }

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
                    System.out.println("Ingrese el mail: ");
                    String mail = scan.nextLine();
                    System.out.println("Ingrese la password: ");
                    String pass = scan.nextLine();
                    login(mail, pass);
                    if(this.usuarioLogueado instanceof Paciente){
                        this.menuPaciente();
                    }else if(this.usuarioLogueado instanceof Medico){
                        this.menuMedico();
                    }else{
                        this.menuAdmin();
                    }
                }break;
            }
        }while(opcion != 0);
    }

    public void menuPaciente(){
        int opcion;
        do{
            ((Paciente)usuarioLogueado).notificarPaciente();
            System.out.println("[1] Realizar acciones del dia\"");
            System.out.println("[2] Modificar acciones del dia\"");
            System.out.println("[3] Log Out");
            System.out.println("Ingrese una opcion o 0 para salir: ");
            opcion = scan.nextInt();

            switch(opcion){
                case 1 :

                case 2 :
                    ((Paciente)usuarioLogueado).realizarAcciones();
                    break;//preguntar si asi enta con 1 o con 2  al mismo case

                case 3 :
                    logout();
                    opcion = 0;
                    break;
            }
        }while(opcion != 0);
    }

    public void menuMedico(){
        int opcion;
        do{
            ArrayList<Integer> listaIdPacientes = new ArrayList<>();
            listaIdPacientes = ((Medico)usuarioLogueado).obtenerIDsPacientes(); // el medico esta obligado a diagnoticar apenas abre?
            for(Integer a : listaIdPacientes){
            }





            System.out.println("[1] Asingar tratamiento");
            System.out.println("[2] Ver historiales de los pacientes");
            System.out.println("[3] Log Out");

            System.out.println("Ingrese una opcion o 0 para salir: ");
            opcion = scan.nextInt();

            switch(opcion){
                case 1 :
                    ((Medico)usuarioLogueado).diagnosticarPacientes();
                break;

                case 2 :
                    ((Medico)usuarioLogueado).verHistorialPaciente();
                break;

                case 3 :
                    logout();
                    opcion = 0;
                break;
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
                    logout();
                    opcion = 0;
                }break;
            }
        }while(opcion != 0);
    }


    public void login(String mail, String pass){
        // buscar mail y password
        // ver si coinciden
        this.usuarioLogueado = new Admin("Prueba", "Paciente", "prueba@hotmail.com", "123456");
    }

    public void logout(){
        this.usuarioLogueado = null;
    }

    public void notificarMedicos(){

    }

    public void notificarPacientes(){

    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }
}