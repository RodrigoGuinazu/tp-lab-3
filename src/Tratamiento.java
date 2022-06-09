import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Stack;

public class Tratamiento implements Cloneable {
    private Enfermedad enfermedad;
    private Integer duracion;
    private LocalDate inicioDate;
    private LocalDate finDate;
    protected ArrayList<Accion> listaAcciones;
    private Stack<RegistroDiario> listaRegistrosDiarios;
    private boolean finalizado;


    // Constructores

    public Tratamiento() {
        listaAcciones = new ArrayList<>();
        listaRegistrosDiarios  = new Stack<>();

    }

    // Tratamiento "Vacio"
    public Tratamiento(Enfermedad enfermedad) {
        this.enfermedad = enfermedad;
        this.finalizado = false;
    }


    public Tratamiento(Enfermedad enfermedad,Integer duracion, ArrayList<Accion> acciones) {
        this.duracion = duracion;
        this.enfermedad = enfermedad;
        this.listaAcciones = acciones;
        this.listaRegistrosDiarios = new Stack<RegistroDiario>();
        finalizado = false;
    }

    public Tratamiento clonarTratamiento() {
        Tratamiento aux = new Tratamiento(this.enfermedad,this.duracion, this.listaAcciones);
        return aux;
    }

    public LocalDate getInicioDate() {
        return inicioDate;
    }

    public LocalDate getFinDate() {
        return finDate;
    }

    public boolean isFinalizado() {
        return finalizado;
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

    public boolean existeRegistroDiario(LocalDate fecha) {
        for (RegistroDiario a : listaRegistrosDiarios) {
            if (a.getFecha().equals(fecha)) {
                return true;
            }
        }
        return false;
    }

    public void realizarAcciones() {        //CAMBIAR (tiene que poder elegir que accion cambiar)
        RegistroDiario aux;
        if (listaRegistrosDiarios.empty() || listaRegistrosDiarios.pop().getFecha().isBefore(LocalDate.now())) {
            aux = new RegistroDiario();

            for (Accion a : listaAcciones) {
                try {

                    if (a instanceof AccionBooleana){
                        Registro registroAux = ((AccionBooleana)a).accionar();
                        aux.agregarRegistro(registroAux);
                    }
                    if (a instanceof AccionDouble){
                        Registro registroAux = ((AccionDouble)a).accionar();
                        aux.agregarRegistro(registroAux);
                    }

                } catch (AccionFallidaException e) {
                    System.out.println(e);
                }
                listaRegistrosDiarios.push(aux);
            }

            return;

        } else {
            System.out.println("Ya se ingresaron registros para la fecha de hoy, si desea modificarlos, hagalo desde el menu");
        }




    }

    public String modificarRegistros() {

        if (listaRegistrosDiarios.peek().getFecha() == LocalDate.now()) {
            listaRegistrosDiarios.peek().modificarRegistro();
            return "Se a modificado el registro diario con exito";
        } else {
            return "No hay geistro diario que Modificar";
            // la logica del menu deberia indicarle que realice las acciones
        }

    }

    public String toStringListaAcciones() {
        String rta = "";
        try {
            for (Accion a : listaAcciones) {
                rta += a.toString();
            }

            return rta;
        } catch (NullPointerException e) {
            System.out.println("Error de tipo : " + e);
        }
        return rta;
    }

    public Enfermedad getEnfermedad() {
        return enfermedad;
    }


    public String toStringMedico(){     //enfermedad, duracion y lista de acciones
        String rta = "";
        for(Accion a : listaAcciones){
            rta += "\n     ";
            rta+=a.mostrarAccion();
        }
        return "Enfermedad : " +this.getEnfermedad().mostrarEnfermedad() + " , " + "Duracion : " +this.duracion + " " +rta;
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
