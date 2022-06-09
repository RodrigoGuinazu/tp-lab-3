import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Stack;

public class Tratamiento implements Cloneable {
    private Enfermedad enfermedad;
    private int duracion ;
    private LocalDate incioDate;
    private LocalDate finDate;
    protected ArrayList<Accion> listaAcciones;
    private Stack<RegistroDiario> listaRegistrosDiarios;
    private boolean finalizado;

    public Tratamiento(){

    }

    public Tratamiento(int duracion , Enfermedad enfermedad, ArrayList<Accion> acciones){
        this.duracion = duracion;
        this.enfermedad = enfermedad;
        this.listaAcciones  = acciones;
        this.listaRegistrosDiarios = new Stack<RegistroDiario>();
        finalizado = false;
    }

    public Tratamiento clonarTratamiento (){
        Tratamiento aux = new Tratamiento(this.duracion,this.enfermedad,this.listaAcciones);
        return aux;
    }

    public void setEnfermedad(Enfermedad enfermedad) {
        this.enfermedad = enfermedad;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public void setIncioDate(LocalDate incioDate) {
        this.incioDate = incioDate;
    }

    public void setFinDate(LocalDate finDate) {
        this.finDate = finDate;
    }

    public void setListaAcciones(ArrayList<Accion> listaAcciones) {
        this.listaAcciones = listaAcciones;
    }

    public void setListaRegistrosDiarios(Stack<RegistroDiario> listaRegistrosDiarios) {
        this.listaRegistrosDiarios = listaRegistrosDiarios;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    public int getDuracion() {
        return duracion;
    }

    public boolean existeRegistroDiario(LocalDate fecha){
        for (RegistroDiario a : listaRegistrosDiarios){
            if (a.getFecha().equals(fecha)) {
                return true;
            }
        }
        return false;
    }

    public void realizarAcciones (){        //CAMBIAR (tiene que poder elegir que accion cambiar)
        RegistroDiario aux;
        if (listaRegistrosDiarios.pop().getFecha() == LocalDate.now()){
            aux = listaRegistrosDiarios.pop();
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

        }

        listaRegistrosDiarios.push(aux);
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

    public String toStringListaAcciones(){
        String rta = "";
        for (Accion a: listaAcciones){
            rta  += a.toString();
        }

        return rta;
    }

    public Enfermedad getEnfermedad() {
        return enfermedad;
    }

    @Override
    public String toString() {
        return "Tratamiento{" +
                "enfermedad=" + enfermedad +
                ", duracion=" + duracion + "Lista de acciones del tratamiento:" + toStringListaAcciones() +
                '}';
    }
}


