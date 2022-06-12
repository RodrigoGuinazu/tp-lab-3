import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Medico extends Usuario implements Tratamientos {

    private ArrayList<Integer> pacientesDelMedico = new ArrayList<>();  //ids paciente

    // Constructores
    // sin lista de pacientes
    public Medico(String nombre, String apellido, String dni, String mail, String password) {
        super(nombre, apellido, dni, mail, password);
    }

    // con lista de pacientes
    public Medico(String nombre, String apellido, String dni, String mail, String password, ArrayList<Integer> pacientesDelMedico) {
        super(nombre, apellido, dni, mail, password);
        this.pacientesDelMedico = pacientesDelMedico;
    }


    //Metodos

    public String notificarMedico() {
        //levantar el archivo de pacientes
        //entra paciente por paciente por id y verifica qeu tratamiento
        ArrayList<Paciente> listaPacientes = Persistencia.deserializacionPacientes();
        String rta = "";
        rta += "Pacientes que deben ser atendidos hoy : ";
        for (Paciente pacientegeneral : listaPacientes) {
            if (pacientegeneral.getDebeSerAtendido()) {
                for (Integer a : pacientesDelMedico) {
                    if (pacientegeneral.getId().equals(a)) {
                        rta += pacientegeneral.getinfoPaciente();
                    }
                }

            }
        }
        System.out.println();
        rta += " \nPacientes que no registraron informacion ayer : ";

        for (Paciente a : listaPacientes) {
            if (a.tratamientoActual != null) {
                if (!a.tratamientoActual.existeRegistroDiario(LocalDate.now().minusDays(1)) & a.tratamientoActual.getInicioDate().isBefore(LocalDate.now())) {
                    rta += a.getinfoPaciente();

                } else if (a.tratamientoActual.existeRegistroDiario(LocalDate.now().minusDays(1)) & a.tratamientoActual.getNumeroAccionesDelTratamiento() != a.tratamientoActual.getNumeroAccionesRegistroDiario(LocalDate.now().minusDays(1))) {
                    rta += a.getinfoPaciente();
                }
            }
        }
        return rta;
    }


    public void verHistoriales() {

    }

    public ArrayList<Integer> obtenerIDsPacientes() {
        ArrayList<Integer> aux = new ArrayList();
        for (Integer a : pacientesDelMedico) {
            aux.add(a);
        }
        return pacientesDelMedico;
    }


    public void diagnosticarPacientes() throws dniInexistenteException {

        // levantamos de archivo lista pacientes en lista aux (levantar tratamientos,acciones,enfermedades)
        ArrayList<Paciente> listaPacientes = Persistencia.deserializacionPacientes();
        ArrayList<Tratamiento> listaTratamientosGenericos = Persistencia.deserializacionTratamientos();

        Scanner scan = new Scanner(System.in);
        Paciente pacienteAux = null;
        int control = 0;
        while (control == 0) {

            // consultamos el paciente
            System.out.println("Ingrese el dni del paciente que desea diagnosticar");

            String dni = scan.nextLine();
            for (Paciente a : listaPacientes) {
                if (a.getDni().equals(dni)) {
                    pacienteAux = a;
                    control = 1;
                }
            }

            if (pacienteAux == null) {
                System.out.println("Dni inexistente, ¿Quiere ingresar otro dni? s/n");
                if (scan.nextLine().charAt(0) != 's') {
                    throw new dniInexistenteException();
                }
            }
        }


        // preguntamos al medico si queire elegir uno existente o crear uno nuevo
        Tratamiento tratamientoAux=null;
        int x;
        int opcionMenu;
        do {

            System.out.println("[1] Elegir un tratamiento ya existente");
            System.out.println("[2] Elegir un tratamiento ya existente y modificarlo");
            System.out.println("[3] Crear un nuevo tratamiento");
            System.out.println("[4] Salir");
            System.out.println("Ingrese una opcion:");
            opcionMenu = scan.nextInt();

            switch (opcionMenu) {
                case 1:
                    x = 0;
                    for (Tratamiento a : listaTratamientosGenericos) {
                        System.out.println("[" + x + "] " + a.toStringMedico());
                        x++;
                    }
                    System.out.println("ingrese el numero del tratamiento elegido");
                    tratamientoAux = listaTratamientosGenericos.get(scan.nextInt()).clonarTratamiento();
                    pacienteAux.tratamientoActual = tratamientoAux;
                    System.out.println("Tratamiento generico asignado");
                    break;
                case 2:

                    x = 0;
                    for (Tratamiento a : listaTratamientosGenericos) {
                        System.out.println("[" + x + "] " + a.toStringMedico());
                        x++;
                    }
                    System.out.println("ingrese el numero del tratamiento elegido");
                    tratamientoAux = listaTratamientosGenericos.get(scan.nextInt()).clonarTratamiento();
                    pacienteAux.tratamientoActual = tratamientoAux;
                    pacienteAux.tratamientoActual = editarTratamiento(pacienteAux.tratamientoActual);
                    System.out.println("Tratamiento modificado asignado");
                    break;

                case 3:
                    pacienteAux.tratamientoActual = crearTratamiento();
                    System.out.println("Tratamiento nuevo asignado");
                    break;

                case 4:
                    System.out.println("Saliendo...");
                    opcionMenu=0;
                    break;

                default:
                    System.out.println("Opcion incorrecta, ingrese otra");
            }
        } while (opcionMenu!=0);

        if(tratamientoAux==null){
            System.out.print("No se cargo nada...");
        }else {
            // seteamos fecha de inicio y finde del tratamiento, y debeseratendido en false
            pacienteAux.tratamientoActual.setIncioDate(LocalDate.now());
            pacienteAux.tratamientoActual.setFinDate(LocalDate.now().plusDays(pacienteAux.tratamientoActual.getDuracion()));
            pacienteAux.setDebeSerAtendido(false);

            // finalmente persistimos el archivo de pacientes, para que este sufra modificaciones
            Persistencia.serializacionPacientes(listaPacientes);
        }


    }


    public void verHistorialPaciente() {
        ArrayList<Paciente> listaPacientes = Persistencia.deserializacionPacientes();
        for (Paciente pacientegeneral : listaPacientes) {
            for (int a : pacientesDelMedico) {
                if (pacientegeneral.getId().equals(a)) {
                    System.out.println(pacientegeneral.toStringInfoNoSensible());
                }
            }
        }

        System.out.println("Ingrese el dni del paciente que desea cunsultar");
        Scanner scan = new Scanner(System.in);
        String dni = scan.nextLine();
        Paciente pacienteAux = new Paciente();
        for (Paciente a : listaPacientes) {
            if (a.getDni().equals(dni)) {
                pacienteAux = a;
            }
        }
        System.out.println(pacienteAux.tratamientoActual.toStringHistorialTratamientoActual());
    }

    @Override
    public Tratamiento crearTratamiento() {
        int accionIndex;
        Scanner scan = new Scanner(System.in);
        ArrayList<Accion> listaAcciones = Persistencia.deserializacionAcciones();
        Tratamiento nuevoTratamiento = new Tratamiento();
        System.out.println("Ingrese la duracion del tratamiento");
        nuevoTratamiento.setDuracion(scan.nextInt());
        System.out.println("Ingrese el numero de acciones que tendra el tratamiento");
        int aux = scan.nextInt();
        int index = 0;

        for (Accion a : listaAcciones) {
            System.out.println("[" + index + "] " + a.toString());
            index++;

        }

        for (int i = 0; i < aux; i++) {

            System.out.println("Elija el numero de de la accion que escoja para el tratamiento:");
            accionIndex = scan.nextInt();
            if (listaAcciones.get(accionIndex) instanceof AccionBooleana) {
                AccionBooleana accionAux = (AccionBooleana) listaAcciones.get(accionIndex).clonarAccion();
                System.out.println("Ingrese cada cuandos dias quiere que se realice la accion, encaso de ser todos los dias, ingrese 1:");
                accionAux.setCadaCuanto(scan.nextInt());
                nuevoTratamiento.listaAcciones.add(accionAux);
            } else {
                AccionDouble accionAux = (AccionDouble) listaAcciones.get(accionIndex).clonarAccion();
                System.out.println("Ingrese cada cuandos dias quiere que se realice la accion, encaso de ser todos los dias, ingrese 1:");
                accionAux.setCadaCuanto(scan.nextInt());
                nuevoTratamiento.listaAcciones.add(accionAux);
            }

        }

        return nuevoTratamiento;

    }

    @Override
    public Tratamiento editarTratamiento(Tratamiento aux) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Accion> listaAcciones = Persistencia.deserializacionAcciones();
        int opcionMenu = 0;


        do {


            System.out.println("[1] Agregar accion");
            System.out.println("[2] Quitar accion");
            System.out.println("[3] Modificar duracion");
            System.out.println("[4] Guardar tratamiendo");
            System.out.println("Ingrese una opcion:");
            opcionMenu = scan.nextInt();
            int x;
            int index;

            switch (opcionMenu) {

                case 1:
                    index = 0;
                    int accionIndex;
                    for (Accion a : listaAcciones) {
                        System.out.println("[" + index + "] " + a.mostrarAccion());
                        index++;

                    }

                    System.out.println("Elija el numero de de la accion que escoja para el tratamiento:");
                    accionIndex = scan.nextInt();
                    if (listaAcciones.get(accionIndex) instanceof AccionDouble) {
                        AccionDouble accionAux = (AccionDouble) listaAcciones.get(accionIndex).clonarAccion();
                        System.out.println("Ingrese cada cuandos dias quiere que se realice la accion, encaso de ser todos los dias, ingrese 1:");
                        accionAux.setCadaCuanto(scan.nextInt());
                        aux.listaAcciones.add(accionAux);
                    } else {
                        AccionBooleana accionAux = (AccionBooleana) listaAcciones.get(accionIndex).clonarAccion();
                        System.out.println("Ingrese cada cuandos dias quiere que se realice la accion, encaso de ser todos los dias, ingrese 1:");
                        accionAux.setCadaCuanto(scan.nextInt());
                        aux.listaAcciones.add(accionAux);
                    }

                    break;

                case 2:

                    String rta = "";
                    index = 0;
                    for (Accion a : aux.listaAcciones) {

                        System.out.println("[" + index + "] " + a.mostrarAccion());
                        index++;
                    }

                    System.out.println("Elija el numero de la accion que escoja para eliminar del tratamiento:");
                    accionIndex = scan.nextInt();
                    aux.listaAcciones.remove(accionIndex);

                    break;


                case 3:
                    System.out.println("Ingrese la duracion que desea para el tratamiento");
                    x = scan.nextInt();
                    aux.setDuracion(x);
                    break;

                case 4:
                    System.out.println("Guardando tratamieto...");
                    break;


                default:
                    System.out.println("Opcion incorrecta, ingrese otra");
            }
        } while (opcionMenu != 4);


        return aux;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Medico{" +
                "pacientesDelMedico=" + pacientesDelMedico +
                '}';
    }
}
