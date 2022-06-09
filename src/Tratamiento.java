import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Stack;

public class Tratamiento implements Cloneable {
    private Enfermedad enfermedad;
    private Integer duracion ;
    private LocalDate inicioDate;
    private LocalDate finDate;
    protected ArrayList<Accion> listaAcciones;
    private Stack<RegistroDiario> listaRegistrosDiarios;
    private boolean finalizado;


    // Constructores

    public Tratamiento(){

    }

    // Tratamiento "Vacio"
    public Tratamiento(Enfermedad enfermedad) {
        this.enfermedad = enfermedad;
        this.finalizado = false;
    }

    public Tratamiento(Enfermedad enfermedad,LocalDate inicioDate, ArrayList<Accion> listaAcciones) {
        this.enfermedad = enfermedad;
        // this.duracion = calcularDuracion();  //crear
        this.inicioDate = inicioDate;
        // this.finDate = calcularCierre(); //crear
        this.listaAcciones = listaAcciones;
        this.listaRegistrosDiarios = new Stack<RegistroDiario>();
        this.finalizado = false;
    }

    public Tratamiento(Integer duracion , Enfermedad enfermedad, ArrayList<Accion> acciones){
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

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public void setIncioDate(LocalDate incioDate) {
        this.inicioDate = incioDate;
    }

    public void setFinDate(LocalDate finDate) {
        this.finDate = finDate;
    }

    public void setListaAcciones(ArrayList<Accion> listaAcciones) {
        this.listaAcciones = listaAcciones;
    }

    public ArrayList<Accion> getListaAcciones() {
        return listaAcciones;
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
        try{
            for (Accion a: listaAcciones){
                rta  += a.toString();
            }

            return rta;
        } catch (NullPointerException e) {
            System.out.println("Error de tipo : "+e);
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
                ", duracion=" + duracion +
                ", incioDate=" + inicioDate +
                ", finDate=" + finDate +
                ", listaAcciones=" + listaAcciones +
                ", listaRegistrosDiarios=" + listaRegistrosDiarios +
                ", finalizado=" + finalizado +
                '}';
    }
}
