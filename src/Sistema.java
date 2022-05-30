import java.time.LocalDate;
import java.util.ArrayList;

public class Sistema { // deberia ser static/abstract/final?
    // Atributos
    protected ArrayList<Usuario> usuarios;
    protected ArrayList<Tratamiento> tratamientos;
    protected ArrayList<Accion> acciones;
    protected ArrayList<Enfremedad> enfermedades;
    protected LocalDate fechaDelDia;

    // Constructores
    public Sistema(ArrayList<Usuario> usuarios, ArrayList<Tratamiento> tratamientos, ArrayList<Accion> acciones, ArrayList<Enfremedad> enfermedades, LocalDate fechaDelDia) {
        this.usuarios = usuarios;
        this.tratamientos = tratamientos;
        this.acciones = acciones;
        this.enfermedades = enfermedades;
        this.fechaDelDia = fechaDelDia;
    }

    // Metodos
    public void menu(){

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
