import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Admin extends Usuario implements Tratamientos{
    //Constructor
    public Admin(String nombre, String apellido, String dni, String mail, String password) {
        super(nombre, apellido, dni, mail, password);
    }


    //Metodos
    public void registrarPaciente(){
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
        try{
            // si existe le cambio el atributo debeSerAtendido a true
            rta = (Paciente) this.buscarUsuario(aux, dni);
            // verifico que no tenga un tratamiento en curso
            if(rta.getTratamientoActual() == null){
                rta.setDebeSerAtendido(true);
            }else{
                System.out.println("El paciente ya se encuentra con un tratamiento vigente, debe terminar el mismo para generar una nueva visita");
            }
        }catch (UsuarioInexistenteException e){
            // si no lo creo y lo agrego
            System.out.println(e);
            int flag = 0;
            while(flag == 0){
                try{
                    rta = (Paciente) this.cargarDatosBasicos(dni);
                    flag = 1;
                    pacientes.add(rta);
                }catch (InputMismatchException f){
                    System.out.println(f);
                }
            }
        }
        //persistir
        Persistencia.serializacion(pacientes, "pacientes.json");
    }

    public void registrarMedico(){
        // levantar archivo medico
        // agregar medico
        // persistir
    }

    public void registrarAdmin(){
        // levantar archivo admin
        // agregar admin
        // persistir
    }

    private Usuario buscarUsuario(ArrayList<Usuario> listado, String dni) throws UsuarioInexistenteException{
        for(Usuario u : listado){
            if(u.getDni().equals(dni)){
                return u;
            }
        }
        throw new UsuarioInexistenteException();
    }

    private Usuario cargarDatosBasicos(String dni) throws InputMismatchException {
        Scanner scan = new Scanner(System.in);
        try{
            System.out.println("Nombre: ");
            String nombre = scan.nextLine();
            System.out.println("Apellido: ");
            String apellido = scan.nextLine();
            System.out.println("Mail: ");
            String mail = scan.nextLine();
            System.out.println("Password: ");
            String password = scan.nextLine();
            return new Usuario(nombre, apellido, dni, mail, password);
        }catch (InputMismatchException e){
            throw new InputMismatchException();
        }
    }

    public void agregarEnfermedad(){
        // levantar archivo enfermedad
        // agregar enfermedad
        // persistir
    }

    @Override
    public Tratamiento crearTratamiento() {
        // levantar archivo Tratameintos
        // agregar tratemiento
        //persistir
        return null;
    }

    @Override
    public void editarTratamiento() {
        // levantar archivo Tratameintos
        // editar tratamiento
        //persistir
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
