import java.util.ArrayList;

public class Paciente extends Usuario {

    //Atributos

    private ArrayList<Tratamiento> historialClinico = new ArrayList<>();
    protected Tratamiento tratamientoActual;
    private Integer idMedicoAsignado;
    private Boolean debeSerAtendido;

    // Vacio
    public Paciente() {
    }


    //para probar ver historial de de paciente
    public Paciente(String nombre, String apellido, String dni, String mail, String password, ArrayList<Tratamiento> historialClinico, Integer idMedicoAsignado) {
        super(nombre, apellido, dni, mail, password);
        this.historialClinico = historialClinico;
        this.idMedicoAsignado = idMedicoAsignado;
        this.debeSerAtendido = true;
    }

    // Sin medico asignado
    public Paciente(String nombre, String apellido, String dni, String mail, String password) {
        super(nombre, apellido, dni, mail, password);
        this.debeSerAtendido = true;
    }


    // Completo
    public Paciente(String nombre, String apellido, String dni, String mail, String password, Integer idMedicoAsignado) {
        super(nombre, apellido, dni, mail, password);
        this.idMedicoAsignado = idMedicoAsignado;
        this.debeSerAtendido = true;
    }

    //Metodos


    public ArrayList<Tratamiento> getHistorialClinico() {
        return historialClinico;
    }


    public void notificarPaciente() {
        //Chequear igualdades de LocalDate

        if (this.tratamientoActual.listaRegistrosDiarios.empty() || this.tratamientoActual.listaRegistrosDiarios.peek().getFecha().isBefore(Sistema.getFechaDelDia())) {
            try {


                for (Accion a : this.tratamientoActual.getListaAcciones()) {

                    if(a.ultimaNoti != null & a.ultimaNoti.plusDays(a.cadaCuanto).isEqual(Sistema.getFechaDelDia())){
                        System.out.println("Debe realizar la accion : " + a.getNombre());
                        a.setUltimaNoti(Sistema.getFechaDelDia());

                    }



                }


            } catch (NullPointerException e) {
                System.out.println(Colores.amarillo() + "El paciente no tiene tratamiento asignado" + Colores.blanco());

            }

        } else {
            try {
                int flag = 0;    //por si no hay accion para notificar


                for (Accion a : this.tratamientoActual.getListaAcciones()) {
                    flag = 0;

                    for (Registro x : this.tratamientoActual.listaRegistrosDiarios.peek().listaRegistros) {
                        if (x.mostrarNombresRegistros().equals(a.getNombre())) {
                            flag = 1;
                        }
                    }

                    if(flag == 0){

                        System.out.println("Debe realizar la accion : " + a.getNombre());
                        a.setUltimaNoti(Sistema.getFechaDelDia());
                    }else{
                        System.out.println("Ya se realizo la accion : " + a.getNombre());
                    }




                }


            } catch (NullPointerException e) {
                System.out.println(Colores.amarillo() + "El paciente no tiene tratamiento asignado" + Colores.blanco());

            }
        }


    }


    public void realizarAcciones() {
        if (tratamientoActual.getFinDate().equals(Sistema.getFechaDelDia()) || tratamientoActual.getFinDate().isBefore(Sistema.getFechaDelDia())) {
            tratamientoActual.setFinalizado(true);
            historialClinico.add(tratamientoActual);
            tratamientoActual = null;
            System.out.println(Colores.amarillo() + "Usted ha finalizado su tratamiento" + Colores.blanco());
            return;
        } else {
            this.tratamientoActual.realizarAcciones();
        }
        return;
    }

    public void editarAccionesDelDia() {
        if (tratamientoActual != null) {
            this.tratamientoActual.modificarAcciones();
        } else {
            System.out.println(Colores.amarillo() + "No te encuentras realizando un tratamiento" + Colores.blanco());
        }
    }

    public void setTratamientoActual(Tratamiento tratamientoActual) {
        this.tratamientoActual = tratamientoActual;
    }

    public void setDebeSerAtendido(Boolean debeSerAtendido) {
        this.debeSerAtendido = debeSerAtendido;
    }


    public String getinfoPaciente() {

        return super.toStringInfoNoSensible();
    }

    public Integer getId() {

        return super.getId();
    }

    public Integer getIdMedicoAsignado() {
        return idMedicoAsignado;
    }

    public Tratamiento getTratamientoActual() {
        return tratamientoActual;
    }

    public void setHistorialClinico(ArrayList<Tratamiento> historialClinico) {
        this.historialClinico = historialClinico;
    }

    public void setIdMedicoAsignado(Integer idMedicoAsignado) {
        this.idMedicoAsignado = idMedicoAsignado;
    }

    public Boolean getDebeSerAtendido() {
        return debeSerAtendido;
    }

    public String toStringHistorial() {
        StringBuilder string = new StringBuilder();
        for (Tratamiento t : this.historialClinico) {
            string.append(t.getEnfermedad().toString() + "\n");
        }
        return string.toString();
    }


    @Override
    public String toString() {
        return "Paciente{" +
                super.toString() + " " +
                ", idMedicoAsignado=" + idMedicoAsignado +
                ", tratamientoActual=" + tratamientoActual +
                ", debeSerAtendido=" + debeSerAtendido +
                ", historialClinico=" + historialClinico +
                '}';
    }
}
