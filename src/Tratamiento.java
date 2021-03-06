import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

import static java.time.temporal.ChronoUnit.DAYS;

public class Tratamiento {
    // Atributos
    private Enfermedad enfermedad;
    private Integer duracion;
    private LocalDate inicioDate;
    private LocalDate finDate;
    protected ArrayList<Accion> listaAcciones;
    protected Stack<RegistroDiario> listaRegistrosDiarios;
    private boolean finalizado;


    // Constructores
    public Tratamiento() {
        listaAcciones = new ArrayList<>();
        listaRegistrosDiarios = new Stack<>();

    }

    public Tratamiento(Enfermedad enfermedad, Integer duracion, ArrayList<Accion> acciones) {
        this.duracion = duracion;
        this.enfermedad = enfermedad;
        this.listaAcciones = acciones;
        this.listaRegistrosDiarios = new Stack<RegistroDiario>();
        finalizado = false;
    }

    // Metodos

    public void setInicioDate(LocalDate inicioDate) {
        this.inicioDate = inicioDate;
    }

    public void setListaAcciones(ArrayList<Accion> listaAcciones) {
        this.listaAcciones = listaAcciones;
    }

    public Stack<RegistroDiario> getListaRegistrosDiarios() {
        return listaRegistrosDiarios;
    }

    public void setListaRegistrosDiarios(Stack<RegistroDiario> listaRegistrosDiarios) {
        this.listaRegistrosDiarios = listaRegistrosDiarios;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void mostrarTratamiento() {
        System.out.println("Enfermedad : " + this.enfermedad.getNombre());
        System.out.println("Duracion : " + this.duracion);
        System.out.println("Inicio : " + this.inicioDate);
        System.out.println("Fin : " + this.finDate);
        for (Accion a : listaAcciones) {
            if (a instanceof AccionBooleana) {
                ((AccionBooleana) a).mostrarAccionBooleana();
            } else {
                ((AccionDouble) a).mostrarAccionDouble();
            }
        }
    }

    public Tratamiento clonarTratamiento() {
        Tratamiento aux = new Tratamiento(this.enfermedad, this.duracion, this.listaAcciones);
        return aux;
    }

    public LocalDate getInicioDate() {
        return inicioDate;
    }

    public LocalDate getFinDate() {
        return finDate;
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

    public ArrayList<Accion> getListaAcciones() {
        return listaAcciones;
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

    public void realizarAcciones() {
        RegistroDiario aux;
        int flag = 0;
        int contador = 0;
        if (listaRegistrosDiarios.empty() || listaRegistrosDiarios.peek().getFecha().isBefore(Sistema.getFechaDelDia())) { // si no existe registro para hoy, le pide hacer todo
            aux = new RegistroDiario();
            listaRegistrosDiarios.push(aux);

            for (Accion a : listaAcciones) {
                flag = 0;
                if (a.ultimaNoti != null && !a.ultimaNoti.plusDays(a.cadaCuanto).isEqual(Sistema.getFechaDelDia())) {
                    flag = 1;
                }

                if (flag == 0) {
                    contador++;
                    if (a instanceof AccionDouble) {
                        try {
                            Registro registroAux = a.accionar();
                            a.setUltimaNoti(Sistema.getFechaDelDia());
                            listaRegistrosDiarios.peek().agregarRegistro(registroAux);
                        } catch (AccionFallidaException e) {
                        }
                    }
                    if (a instanceof AccionBooleana) {
                        try {
                            Registro registroAux = a.accionar();
                            a.setUltimaNoti(Sistema.getFechaDelDia());
                            listaRegistrosDiarios.peek().agregarRegistro(registroAux);
                        } catch (AccionFallidaException e) {
                        }
                    }
                }
            }

            if (contador == 0) {
                System.out.println(Colores.amarillo() + "Todas las acciones fueron realizadas, si quiere modificar sus valores, ingrese desde la opcion Modificar en el menu" + Colores.blanco());
            }
            return;

        } else if (listaRegistrosDiarios.peek().getFecha().isEqual(Sistema.getFechaDelDia())) { // cuando hay registro para hoy, pero no tiene nada adentro, pasa muy poco
            if (listaRegistrosDiarios.peek().listaRegistros.isEmpty()) {
                for (Accion a : listaAcciones) {
                    if (a.ultimaNoti != null && !Sistema.comprobarCorrespodenAccion(a.ultimaNoti, a.cadaCuanto)) {
                        break;
                    }

                    if (a instanceof AccionDouble) {
                        try {
                            Registro registroAux = a.accionar();
                            a.setUltimaNoti(Sistema.getFechaDelDia());
                            listaRegistrosDiarios.peek().agregarRegistro(registroAux);
                        } catch (AccionFallidaException e) {
                        }
                    } else if (a instanceof AccionBooleana) {
                        try {
                            Registro registroAux = a.accionar();
                            a.setUltimaNoti(Sistema.getFechaDelDia());
                            listaRegistrosDiarios.peek().agregarRegistro(registroAux);
                        } catch (AccionFallidaException e) {
                        }
                    }

                }
            } else {
                for (Accion a : listaAcciones) { // cuando hay registro del dia con algo adentro
                    flag = 0;
                    if (a.ultimaNoti != null && !Sistema.comprobarCorrespodenAccion(a.ultimaNoti, a.cadaCuanto)) {
                        flag = 1;
                    } else {
                        for (Registro x : listaRegistrosDiarios.peek().listaRegistros) {
                            if (x.mostrarNombresRegistros().equals(a.getNombre())) {
                                flag = 1;
                            }
                        }
                    }
                    if (flag == 0) {
                        contador++;
                        if (a instanceof AccionDouble) {
                            try {
                                Registro registroAux = a.accionar();
                                a.setUltimaNoti(Sistema.getFechaDelDia());
                                listaRegistrosDiarios.peek().agregarRegistro(registroAux);
                            } catch (AccionFallidaException e) {
                            }
                        }
                        if (a instanceof AccionBooleana) {
                            try {
                                Registro registroAux = a.accionar();
                                a.setUltimaNoti(Sistema.getFechaDelDia());
                                listaRegistrosDiarios.peek().agregarRegistro(registroAux);
                            } catch (AccionFallidaException e) {
                            }
                        }
                    }
                }
            }

        }

        if (contador == 0) {
            System.out.println(Colores.amarillo() + "Todas las acciones fueron realizadas, si quiere modificar sus valores, ingrese desde la opcion Modificar en el menu" + Colores.blanco());
        }

    }

    public void modificarAcciones() {
        try {
            if (Sistema.getFechaDelDia().equals(this.listaRegistrosDiarios.peek().getFecha()) && (this.listaRegistrosDiarios.peek().listaRegistros.size() > 0)) {
                this.listaRegistrosDiarios.peek().modificarRegistro();
            } else {
                System.out.println(Colores.amarillo() + "No hay registros que modificar en el dia de hoy" + Colores.blanco());
            }
        } catch (EmptyStackException e) {
            System.out.println(Colores.amarillo() + "No hay registros que modificar en el dia de hoy" + Colores.blanco());
        }
    }

    public Enfermedad getEnfermedad() {
        return enfermedad;
    }

    public String mostrarTratamientoString() {     //enfermedad, duracion y lista de acciones
        String rta = "";
        for (Accion a : listaAcciones) {
            rta += "\n     ";
            rta += a.mostrarAccion();
        }
        return "Enfermedad : " + this.getEnfermedad().mostrarEnfermedad() + " , " + "Duracion : " + this.duracion + " " + rta;
    }

    public String toStringHistorialTratamientoActual() {
        String rta = "";

        for (RegistroDiario registroDiario : listaRegistrosDiarios) {
            rta += registroDiario.getFecha().toString() + "\n";
            for (Accion accion : listaAcciones) {
                int flag = 0;
                String aux = "";
                if (!Sistema.comprobarCorrespodiaAccionPasado(this.inicioDate, registroDiario.getFecha(), accion.cadaCuanto)) {
                    rta += "La accion " + accion.getNombre() + " no debia realizarce.\n";
                } else {
                    for (Registro registroPart : registroDiario.listaRegistros) {
                        if (registroPart.mostrarNombresRegistros().equals(accion.getNombre())) {
                            flag = 1;
                            aux = registroPart.toStringParaHistorial();
                        }
                    }
                    if (flag == 1) {
                        rta += aux;
                    } else {
                        rta += "La accion " + accion.getNombre() + " debia realizarce y no se hizo.\n";
                    }
                }
            }
        }

        return rta;
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

    public int getNumeroAccionesNotificables() {
        int contador = 0;
        long dias = DAYS.between(this.inicioDate, Sistema.getFechaDelDia().minusDays(1));
        for (Accion a : listaAcciones) {
            if (dias % a.cadaCuanto == 0) {
                contador++;
            }
        }

        return contador;
    }

    public int getNumeroAccionesRegistroDiario(LocalDate fecha) {
        for (RegistroDiario a : listaRegistrosDiarios) {
            if (a.getFecha().equals(fecha)) {
                int x = a.listaRegistros.size();
                return x;
            }
        }
        return -1;
    }
}
