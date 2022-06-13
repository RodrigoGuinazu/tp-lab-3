import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Medico extends Usuario implements Tratamientos {

    private ArrayList<Integer> pacientesDelMedico = new ArrayList<>();  //ids paciente

    // Constructores
    // Para medicos aux
    public Medico() {
    }

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

    public StringBuilder notificarMedico() {
        StringBuilder string1 = new StringBuilder();
        //levantar el archivo de pacientes
        //entra paciente por paciente por id y verifica qeu tratamiento
        ArrayList<Paciente> listaPacientes = Persistencia.deserializacionPacientes();
        string1.append("Pacientes que deben ser atendidos hoy : ");
        for (Paciente pacientegeneral : listaPacientes) {
            if (pacientegeneral.getDebeSerAtendido()) {
                for (Integer a : pacientesDelMedico) {
                    if (pacientegeneral.getId().equals(a)) {
                        string1.append(pacientegeneral.getinfoPaciente());
                    }
                }
            }
        }
        if (string1.toString().equals("Pacientes que deben ser atendidos hoy : ")) {
            string1.append(" No hay");
        }
        string1.append("\n");
        StringBuilder string2 = new StringBuilder();
        string2.append("Pacientes que no registraron informacion ayer : ");
        for (Paciente a : listaPacientes) {
            if (a.tratamientoActual != null) {
                if (!a.tratamientoActual.existeRegistroDiario(LocalDate.now().minusDays(1)) & a.tratamientoActual.getInicioDate().isBefore(LocalDate.now())) {
                    string2.append(a.getinfoPaciente());
                } else if (a.tratamientoActual.existeRegistroDiario(LocalDate.now().minusDays(1)) & a.tratamientoActual.getNumeroAccionesDelTratamiento() != a.tratamientoActual.getNumeroAccionesRegistroDiario(LocalDate.now().minusDays(1))) {
                    string2.append(a.getinfoPaciente());
                }
            }
        }
        if (string2.toString().equals("Pacientes que no registraron informacion ayer : ")) {
            string2.append("No hay");
        }
        return string1.append(string2);
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


    public void diagnosticarPacientes() throws DniInexistenteException {

        // revisar que haya alguien a quien diagnosticar

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
                    if (a.getDebeSerAtendido()) {
                        pacienteAux = a;
                        control = 1;
                    }
                }
            }

            if (pacienteAux == null) {
                System.out.println("Dni invalido, ¿Quiere ingresar otro dni? s/n");
                if (scan.nextLine().charAt(0) != 's') {
                    throw new DniInexistenteException();
                }
            }
        }


        // preguntamos al medico si queire elegir uno existente o crear uno nuevo
        Tratamiento tratamientoAux = null;
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
                    int flag1 = 0;
                    while (flag1 == 0){
                        try{
                            x = 1;
                            for (Tratamiento a : listaTratamientosGenericos) {
                                System.out.println("[" + x + "] " + a.toStringMedico());
                                x++;
                            }
                            System.out.println("Ingrese el numero del tratamiento que quiera asignarle al paciente: ");
                            tratamientoAux = listaTratamientosGenericos.get(scan.nextInt()-1).clonarTratamiento();
                            pacienteAux.tratamientoActual = tratamientoAux;
                            System.out.println("Tratamiento generico asignado");
                            flag1 = 1;
                        }catch (IndexOutOfBoundsException e){
                            System.out.println("Ingresaste una opcion incorrecta, intentalo nuevamente");
                        }
                    }
                    break;
                case 2:
                    int flag2 = 0;
                    while (flag2 == 0){
                        try{
                            x = 1;
                            for (Tratamiento a : listaTratamientosGenericos) {
                                System.out.println("[" + x + "] " + a.toStringMedico());
                                x++;
                            }
                            System.out.println("Ingrese el numero del tratamiento que desea modificar: ");
                            tratamientoAux = listaTratamientosGenericos.get(scan.nextInt()-1).clonarTratamiento();
                            pacienteAux.tratamientoActual = tratamientoAux;
                            pacienteAux.tratamientoActual = editarTratamiento(pacienteAux.tratamientoActual);
                            System.out.println("Tratamiento modificado asignado");
                            flag2= 1;
                        }catch (IndexOutOfBoundsException e){
                            System.out.println("Ingresaste una opcion incorrecta, intentalo nuevamente");
                        }
                    }
                    break;

                case 3:
                    pacienteAux.tratamientoActual = crearTratamiento();
                    tratamientoAux = pacienteAux.tratamientoActual;
                    System.out.println("Tratamiento nuevo asignado");
                    break;

                case 4:
                    System.out.println("Saliendo...");
                    opcionMenu = 0;
                    break;

                default:
                    System.out.println("Opcion incorrecta, ingrese otra");
            }
        } while (opcionMenu != 0);

        if (tratamientoAux == null) {
            System.out.println("No se cargo nada...");
        } else {
            // seteamos fecha de inicio y finde del tratamiento, y debeseratendido en false
            pacienteAux.tratamientoActual.setIncioDate(LocalDate.now());
            pacienteAux.tratamientoActual.setFinDate(LocalDate.now().plusDays(pacienteAux.tratamientoActual.getDuracion()));
            pacienteAux.setDebeSerAtendido(false);

            // finalmente persistimos el archivo de pacientes, para que este sufra modificaciones
            Persistencia.serializacionPacientes(listaPacientes);
        }


    }


    public void verHistorialPaciente() throws DniInexistenteException {
        Scanner scan = new Scanner(System.in);
        ArrayList<Paciente> listaPacientes = Persistencia.deserializacionPacientes();
        for (Paciente p : listaPacientes) {
            for (int a : pacientesDelMedico) {  //ve solo el historial de sus pacientes
                if (p.getId().equals(a)) {
                    System.out.println(p.toStringInfoNoSensible());
                }
            }
        }

        Paciente pacienteAux = null;
        int control = 0;
        while (control == 0) {
            System.out.println("Ingrese el dni su paciente para ver historial");

            String dni = scan.nextLine();
            pacienteAux = null;
            for (Paciente a : listaPacientes) {
                if (a.getDni().equals(dni)) {
                    pacienteAux = a;
                    control = 1;
                }
            }
            if (pacienteAux == null) {
                System.out.println("Dni invalido, ¿Quiere ingresar otro dni? s/n");
                if (scan.nextLine().charAt(0) != 's') {
                    throw new DniInexistenteException();
                }
            }
        }


        for (Tratamiento t : pacienteAux.getHistorialClinico()) {
            t.mostrarTratamiento();
        }
        System.out.println("Presione cualquier tecla para continuar");
        scan.nextLine();
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
        int flag;

        for (int i = 0; i < aux; i++) {
            flag = 0;
            while(flag == 0){
                try{
                    int index = 1;
                    for (Accion a : listaAcciones) {
                        System.out.println("[" + index + "] " + a.mostrarAccion()); // mostrar mejor el a
                        index++;

                    }

                    System.out.println("Elija el numero de de la accion que desea para el tratamiento:");
                    accionIndex = scan.nextInt()-1;
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
                    flag = 1;
                }catch (IndexOutOfBoundsException e){
                    System.out.println("Ingresaste una opcion incorrecta, intentalo nuevamente");
                }
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

            System.out.println("Editando el tratamiento para la enfermedad: " + aux.getEnfermedad().getNombre());
            System.out.println("[1] Agregar accion");
            System.out.println("[2] Quitar accion");
            System.out.println("[3] Modificar duracion");
            System.out.println("[4] Guardar tratamiendo");
            System.out.println("Ingrese una opcion:");
            opcionMenu = scan.nextInt();
            int index;

            switch (opcionMenu) {

                case 1:
                    int flag1 = 0;
                    int accionIndex;
                    while (flag1 == 0){
                        try{
                            index = 1;
                            for (Accion a : listaAcciones) {
                                System.out.println("[" + index + "] " + a.mostrarAccion());
                                index++;
                            }
                            System.out.println("Elija el numero de de la accion que escoja para el tratamiento:");
                            accionIndex = scan.nextInt();
                            if (listaAcciones.get(accionIndex-1) instanceof AccionDouble) {
                                AccionDouble accionAux = (AccionDouble) listaAcciones.get(accionIndex-1).clonarAccion();
                                System.out.println("Ingrese cada cuandos dias quiere que se realice la accion, encaso de ser todos los dias, ingrese 1:");
                                accionAux.setCadaCuanto(scan.nextInt());
                                aux.listaAcciones.add(accionAux);
                            } else {
                                AccionBooleana accionAux = (AccionBooleana) listaAcciones.get(accionIndex-1).clonarAccion();
                                System.out.println("Ingrese cada cuandos dias quiere que se realice la accion, encaso de ser todos los dias, ingrese 1:");
                                accionAux.setCadaCuanto(scan.nextInt());
                                aux.listaAcciones.add(accionAux);
                            }
                            flag1 = 1;
                        }catch (IndexOutOfBoundsException e){
                            System.out.println("Ingresaste una opcion incorrecta, intentalo nuevamente");
                        }
                    }
                    break;

                case 2:
                    int flag2 = 0;
                    while (flag2 == 0){
                        try{
                            index = 1;
                            for (Accion a : aux.listaAcciones) {
                                System.out.println("[" + index + "] " + a.mostrarAccion());
                                index++;
                            }

                            System.out.println("Elija el numero de la accion que escoja para eliminar del tratamiento:");
                            accionIndex = scan.nextInt();
                            aux.listaAcciones.remove(accionIndex-1);
                            flag2 = 1;
                        }catch (IndexOutOfBoundsException e){
                            System.out.println("Ingresaste una opcion incorrecta, intentalo nuevamente");
                        }
                    }
                    break;


                case 3:
                    System.out.println("Ingrese la duracion que desea para el tratamiento");
                    int x = scan.nextInt();
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
