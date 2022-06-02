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
        RegistroDiario aux;
        if (listaRegistrosDiarios.pop().getFecha() == LocalDate.now()){
            aux = listaRegistrosDiarios.peek();
        }else{
            aux = new RegistroDiario();
        }

        for (Accion a : listaAcciones){
            try{
                aux.agregarRegistro(a.accionar());  // agregar catch de AccionFallidaException
            }
            catch (AccionFallidaException e){
                System.out.println(e);
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


