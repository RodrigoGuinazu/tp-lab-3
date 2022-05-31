import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Stack;

public class Tratamiento {
    private Enfermedad enfermedad;
    private LocalDate incioDate;
    private LocalDate finDate;
    private ArrayList<Accion> listaAcciones;
    private Stack<RegistroDiario> listaRegistrosDiarios;
    private boolean finalizado;

    public Tratamiento(Enfermedad enfermedad, LocalDate inicio, LocalDate fin, ArrayList<Accion> acciones){
        this.enfermedad = enfermedad;
        this.incioDate = inicio;
        this.finDate = fin;
        this.listaAcciones  = acciones;
        this.listaRegistrosDiarios = new Stack<RegistroDiario>();
        finalizado = false;
    }

    public void realizarAcciones (){
        if (listaRegistrosDiarios.peek().getFecha() == LocalDate.now()){
            RegistroDiario aux = listaRegistrosDiarios.peek();
            for (Accion a : listaAcciones){
                aux.agregarRegistro(a.accionar());
            }
        }else{
            RegistroDiario aux = new RegistroDiario();
            for (Accion a : listaAcciones){
                aux.agregarRegistro(a.accionar());
            }
            listaRegistrosDiarios.push(aux);
        }
    }

    public String modificarRegistros (){

        if (listaRegistrosDiarios.peek().getFecha() == LocalDate.now()){
            listaRegistrosDiarios.peek().modificarRegistro();
            return "Se a modificado el registro diario con exito";
        }else{
            return "No hay geistro diario que Modificar";
            // la logica del menu deberia indicarle que realice las acciones
        }

    }
}


