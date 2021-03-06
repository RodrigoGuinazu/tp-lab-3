import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Medico extends Usuario implements Tratamientos {

    private ArrayList<Integer> pacientesDelMedico = new ArrayList<>();  //ids paciente

    // Constructores
    public Medico() {
    }

    // sin lista de pacientes
    public Medico(String nombre, String apellido, String dni, String mail, String password) {
        super(nombre, apellido, dni, mail, password);
    }

    //Metodos
    public StringBuilder notificarMedico() {
        StringBuilder string1 = new StringBuilder();
        //levantar el archivo de pacientes
        //entra paciente por paciente por id y verifica qeu tratamiento
        ArrayList<Paciente> listaPacientes = Persistencia.deserializacionPacientes();
        string1.append("Pacientes que deben ser atendidos hoy: \n");
        for (Paciente pacientegeneral : listaPacientes) {
            if (pacientegeneral.getDebeSerAtendido()) {
                for (Integer a : pacientesDelMedico) {
                    if (pacientegeneral.getId().equals(a) && pacientegeneral.getIdMedicoAsignado() != null && pacientegeneral.getIdMedicoAsignado().equals(this.getId())) {
                        string1.append(pacientegeneral.getinfoPaciente());
                    }
                }
            }
        }
        if (string1.toString().equals("Pacientes que deben ser atendidos hoy: \n")) {
            string1.append(Colores.amarillo() + "No hay" + Colores.blanco());
        }
        string1.append("\n");
        StringBuilder string2 = new StringBuilder();
        string2.append("Pacientes que no registraron toda la informacion ayer: \n");
        for (Paciente pacientegeneral : listaPacientes) {
            if (pacientegeneral.tratamientoActual != null) {
                for (Integer a : pacientesDelMedico) {
                    if (pacientegeneral.getId().equals(a) && pacientegeneral.getIdMedicoAsignado() != null && pacientegeneral.getIdMedicoAsignado().equals(this.getId())) {
                        if (!pacientegeneral.tratamientoActual.existeRegistroDiario(Sistema.getFechaDelDia().minusDays(1)) & pacientegeneral.tratamientoActual.getInicioDate().isBefore(Sistema.getFechaDelDia())) {
                            string2.append(pacientegeneral.getinfoPaciente());
                        } else if (pacientegeneral.tratamientoActual.existeRegistroDiario(Sistema.getFechaDelDia().minusDays(1)) & pacientegeneral.tratamientoActual.getNumeroAccionesNotificables() != pacientegeneral.tratamientoActual.getNumeroAccionesRegistroDiario(Sistema.getFechaDelDia().minusDays(1))) {
                            string2.append(pacientegeneral.getinfoPaciente());
                        }
                    }
                }

            }
        }
        if (string2.toString().equals("Pacientes que no registraron toda la informacion ayer: \n")) {
            string2.append(Colores.amarillo() + "No hay" + Colores.blanco());
        }
        return string1.append(string2);
    }

    public ArrayList<Integer> obtenerIDsPacientes() {
        ArrayList<Integer> aux = new ArrayList();
        for (Integer a : pacientesDelMedico) {
            aux.add(a);
        }
        return pacientesDelMedico;
    }

    public void agregarPaciente(Integer id) {
        pacientesDelMedico.add(id);
    }


    public void diagnosticarPacientes() throws DniInexistenteException {

        try {
            // levantamos de archivo lista pacientes en lista aux (levantar tratamientos,acciones,enfermedades)
            ArrayList<Paciente> listaPacientes = Persistencia.deserializacionPacientes();
            ArrayList<Tratamiento> listaTratamientosGenericos = Persistencia.deserializacionTratamientos();

            // muestro los pacientes que deben ser atendidos de vuelta
            int flagSinPacientes = 0;
            StringBuilder string = new StringBuilder();
            string.append("Pacientes que deben ser atendidos hoy: \n");
            for (Paciente pacientegeneral : listaPacientes) {
                if (pacientegeneral.getDebeSerAtendido()) {
                    for (Integer a : pacientesDelMedico) {
                        if (pacientegeneral.getId().equals(a) && pacientegeneral.getIdMedicoAsignado() != null && pacientegeneral.getIdMedicoAsignado().equals(this.getId())) {
                            string.append(pacientegeneral.getinfoPaciente());
                        }
                    }
                }
            }
            if (string.toString().equals("Pacientes que deben ser atendidos hoy: \n")) {
                string.append(Colores.amarillo() + "No hay" + Colores.blanco());
                flagSinPacientes = 1;
            }

            System.out.println(string);

            if (flagSinPacientes != 1) {
                Scanner scan = new Scanner(System.in);
                Paciente pacienteAux = null;
                int control = 0;
                while (control == 0) {

                    // consultamos el paciente
                    System.out.println("Ingrese el dni del paciente que desea diagnosticar: ");

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
                        System.out.println(Colores.amarillo() + "Dni invalido, ??Quiere ingresar otro dni? (s/n)" + Colores.blanco());
                        if (scan.nextLine().charAt(0) != 's') {
                            throw new DniInexistenteException();
                        }
                    }
                }


                // preguntamos al medico si queire elegir uno existente o crear uno nuevo
                Tratamiento tratamientoAux = null;
                int x;
                int opcionMenu = 0;
                do {

                    System.out.println("[1] Elegir un tratamiento ya existente");
                    System.out.println("[2] Elegir un tratamiento ya existente y modificarlo");
                    System.out.println("[3] Crear un nuevo tratamiento");
                    System.out.println("[4] Salir");
                    System.out.println("Ingrese una opcion:");

                    int flagSwitch = 0;
                    while (flagSwitch == 0) {
                        try {
                            opcionMenu = scan.nextInt();
                            flagSwitch = 1;
                        } catch (InputMismatchException i) {
                            System.out.println(Colores.rojo() + "Ingresaste un tipo de dato incorrecto, intentalo nuevamente" + Colores.blanco());
                            System.out.println("Ingrese una opcion:");
                            scan.nextLine();
                        }
                    }

                    switch (opcionMenu) {
                        case 1:
                            int flag1 = 0;
                            while (flag1 == 0) {
                                try {
                                    x = 1;
                                    for (Tratamiento a : listaTratamientosGenericos) {
                                        System.out.println("[" + x + "] " + a.mostrarTratamientoString());
                                        x++;
                                    }
                                    System.out.println("Ingrese el numero del tratamiento que quiera asignarle al paciente: ");
                                    tratamientoAux = listaTratamientosGenericos.get(scan.nextInt() - 1).clonarTratamiento();
                                    pacienteAux.tratamientoActual = tratamientoAux;
                                    System.out.println(Colores.verde() + "Tratamiento generico asignado" + Colores.blanco());
                                    flag1 = 1;
                                } catch (IndexOutOfBoundsException e) {
                                    System.out.println(Colores.rojo() + "Ingresaste una opcion incorrecta, intentalo nuevamente" + Colores.blanco());
                                } catch (InputMismatchException i) {
                                    System.out.println(Colores.rojo() + "Ingresaste un tipo de dato incorrecto, intentalo nuevamente" + Colores.blanco());
                                    scan.nextLine();
                                }
                                opcionMenu = 0;
                            }

                            break;
                        case 2:
                            int flag2 = 0;
                            while (flag2 == 0) {
                                try {
                                    x = 1;
                                    for (Tratamiento a : listaTratamientosGenericos) {
                                        System.out.println("[" + x + "] " + a.mostrarTratamientoString());
                                        x++;
                                    }
                                    System.out.println("Ingrese el numero del tratamiento que desea modificar: ");
                                    tratamientoAux = listaTratamientosGenericos.get(scan.nextInt() - 1).clonarTratamiento();
                                    pacienteAux.tratamientoActual = tratamientoAux;
                                    pacienteAux.tratamientoActual = editarTratamiento(pacienteAux.tratamientoActual);
                                    System.out.println(Colores.verde() + "Tratamiento modificado asignado" + Colores.blanco());
                                    flag2 = 1;
                                } catch (IndexOutOfBoundsException e) {
                                    System.out.println(Colores.rojo() + "Ingresaste una opcion incorrecta, intentalo nuevamente" + Colores.blanco());
                                } catch (InputMismatchException i) {
                                    System.out.println(Colores.rojo() + "Ingresaste un tipo de dato incorrecto, intentalo nuevamente" + Colores.blanco());
                                    scan.nextLine();
                                }
                            }
                            opcionMenu = 0;
                            break;

                        case 3:
                            pacienteAux.tratamientoActual = crearTratamiento();
                            tratamientoAux = pacienteAux.tratamientoActual;
                            System.out.println(Colores.verde() + "Tratamiento nuevo asignado" + Colores.blanco());
                            opcionMenu = 0;
                            break;

                        case 4:
                            System.out.println("Saliendo...");
                            opcionMenu = 0;
                            break;

                        default:
                            System.out.println(Colores.rojo() + "Opcion incorrecta, ingrese otra" + Colores.blanco());
                    }
                } while (opcionMenu != 0);

                if (tratamientoAux == null) {
                    System.out.println(Colores.amarillo() + "No se cargo nada..." + Colores.blanco());

                } else {
                    // seteamos fecha de inicio y finde del tratamiento, y debeseratendido en false
                    pacienteAux.tratamientoActual.setIncioDate(Sistema.getFechaDelDia());
                    pacienteAux.tratamientoActual.setFinDate(Sistema.getFechaDelDia().plusDays(pacienteAux.tratamientoActual.getDuracion()));
                    pacienteAux.setDebeSerAtendido(false);

                    // finalmente persistimos el archivo de pacientes, para que este sufra modificaciones
                    Persistencia.serializacionPacientes(listaPacientes);
                }
            } else {
                System.out.println(Colores.amarillo() + "No tienes pacientes que atender en el dia de hoy" + Colores.blanco());
            }
        } catch (NullPointerException e) {
            System.out.println(Colores.amarillo() + "No tienes pacientes asignados por el momento" + Colores.blanco());
        }
    }


    public void verHistorialPaciente() throws DniInexistenteException {
        Scanner scan = new Scanner(System.in);

        if (pacientesDelMedico.isEmpty()) {
            System.out.println(Colores.amarillo() + "No hay pacientes asignados a este medico" + Colores.blanco());
        } else {

            ArrayList<Paciente> listaPacientes = Persistencia.deserializacionPacientes();
            for (Paciente p : listaPacientes) {
                for (int a : pacientesDelMedico) {  //ve solo el historial de sus pacientes
                    if (p.getId().equals(a)) {
                        System.out.println(p.toStringInfoNoSensible()); //muestra nombre, apellido y DNI de los pacientes del medico
                    }
                }
            }

            // valida dni
            Paciente pacienteAux = null;
            int control = 0;
            while (control == 0) {
                System.out.println("Ingrese el dni del paciente para ver su historial: ");

                String dni = scan.nextLine();
                pacienteAux = null;
                for (Paciente a : listaPacientes) {
                    if (a.getDni().equals(dni)) {
                        pacienteAux = a;
                        control = 1;
                    }
                }
                if (pacienteAux == null) {
                    System.out.println(Colores.amarillo() + "Dni invalido, ??Quiere ingresar otro dni? s/n" + Colores.blanco());
                    if (scan.nextLine().charAt(0) != 's') {
                        throw new DniInexistenteException();
                    }
                }
            }


            //muestra el historial clinico
            try {
                if (pacienteAux.getHistorialClinico().isEmpty()) {
                    System.out.println(Colores.amarillo() + "No hay historial clinico para mostrar" + Colores.blanco());
                } else {
                    for (Tratamiento t : pacienteAux.getHistorialClinico()) {
                        t.mostrarTratamiento();
                    }
                }
            } catch (NullPointerException e) {
                System.out.println(Colores.amarillo() + "No hay historial clinico para mostrar" + Colores.blanco());
            }
            System.out.println("Presione cualquier tecla para continuar");
            scan.nextLine();
        }
    }


    public void verHistorialTratamientoActual() throws DniInexistenteException {

        ArrayList<Paciente> listaPacientes = Persistencia.deserializacionPacientes();


        for (Paciente pacientegeneral : listaPacientes) {
            for (int a : pacientesDelMedico) {
                if (pacientegeneral.getId().equals(a)) {
                    System.out.println(pacientegeneral.toStringInfoNoSensible()); //muestra pacientes del medico
                }
            }
        }

        System.out.println("Ingrese el dni del paciente para ver el seguimiento de su tratamiento: ");
        Scanner scan = new Scanner(System.in);
        String dni = scan.nextLine();
        Paciente pacienteAux = new Paciente();
        for (Paciente a : listaPacientes) {
            if (a.getDni().equals(dni)) {
                pacienteAux = a;
            }
        }

        try {
            if (pacienteAux.getTratamientoActual() != null) {
                if (!pacienteAux.tratamientoActual.listaRegistrosDiarios.isEmpty()) {
                    System.out.println(pacienteAux.tratamientoActual.toStringHistorialTratamientoActual());
                } else {
                    System.out.println(Colores.amarillo() + "El paciente que ingresaste no realizo ninguna accion del tratamiento por el momento!" + Colores.blanco());
                }
            } else {
                System.out.println(Colores.amarillo() + "El paciente que ingresaste no se encuentra realizando ningun tratamiento por el momento!" + Colores.blanco());
            }
        } catch (NullPointerException e) {
        }
    }

    @Override
    public Tratamiento crearTratamiento() {
        int accionIndex;
        Scanner scan = new Scanner(System.in);
        ArrayList<Accion> listaAcciones = Persistencia.deserializacionAcciones();
        Tratamiento nuevoTratamiento = new Tratamiento();
        System.out.println("Ingrese la duracion del tratamiento:");
        nuevoTratamiento.setDuracion(scan.nextInt());
        System.out.println("Ingrese el numero de acciones que tendra el tratamiento:");
        int aux = scan.nextInt();
        int flag;

        for (int i = 0; i < aux; i++) {
            flag = 0;
            while (flag == 0) {
                try {
                    int index = 1;
                    for (Accion a : listaAcciones) {
                        System.out.println("[" + index + "] " + a.mostrarAccion()); // mostrar mejor el a
                        index++;

                    }

                    System.out.println("Elija el numero de de la accion que desea para el tratamiento:");
                    accionIndex = scan.nextInt() - 1;
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
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(Colores.rojo() + "Ingresaste una opcion incorrecta, intentalo nuevamente" + Colores.blanco());
                } catch (InputMismatchException l) {
                    System.out.println(Colores.rojo() + "Ingresaste un tipo de dato incorrecto, intentalo nuevamente" + Colores.blanco());
                    scan.nextLine();
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

            int flagSwitch = 0;
            while (flagSwitch == 0) {
                try {
                    opcionMenu = scan.nextInt();
                    flagSwitch = 1;
                } catch (InputMismatchException i) {
                    System.out.println(Colores.rojo() + "Ingresaste un tipo de dato incorrecto, intentalo nuevamente" + Colores.blanco());
                    System.out.println("Ingrese una opcion:");
                    scan.nextLine();
                }
            }

            int index;

            switch (opcionMenu) {

                case 1:
                    int flag1 = 0;
                    int accionIndex;
                    while (flag1 == 0) {
                        try {
                            index = 1;
                            for (Accion a : listaAcciones) {
                                System.out.println("[" + index + "] " + a.mostrarAccion());
                                index++;
                            }
                            System.out.println("Elija el numero de de la accion que escoja para el tratamiento:");
                            accionIndex = scan.nextInt();
                            if (listaAcciones.get(accionIndex - 1) instanceof AccionDouble) {
                                AccionDouble accionAux = (AccionDouble) listaAcciones.get(accionIndex - 1).clonarAccion();
                                System.out.println("Ingrese cada cuandos dias quiere que se realice la accion, encaso de ser todos los dias, ingrese 1:");
                                accionAux.setCadaCuanto(scan.nextInt());
                                aux.listaAcciones.add(accionAux);
                            } else {
                                AccionBooleana accionAux = (AccionBooleana) listaAcciones.get(accionIndex - 1).clonarAccion();
                                System.out.println("Ingrese cada cuandos dias quiere que se realice la accion, encaso de ser todos los dias, ingrese 1:");
                                accionAux.setCadaCuanto(scan.nextInt());
                                aux.listaAcciones.add(accionAux);
                            }
                            flag1 = 1;
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println(Colores.rojo() + "Ingresaste una opcion incorrecta, intentalo nuevamente" + Colores.blanco());
                        } catch (InputMismatchException i) {
                            System.out.println(Colores.rojo() + "Ingresaste un tipo de dato incorrecto, intentalo nuevamente" + Colores.blanco());
                            scan.nextLine();
                        }
                    }
                    break;

                case 2:
                    int flag2 = 0;


                    if (aux.listaAcciones.size() == 1) {
                        System.out.println(Colores.amarillo() + "No se puede tener un tratamiento vacio" + Colores.blanco());
                    } else {
                        while (flag2 == 0) {
                            try {
                                index = 1;
                                for (Accion a : aux.listaAcciones) {
                                    System.out.println("[" + index + "] " + a.mostrarAccion());
                                    index++;
                                }

                                System.out.println("Elija el numero de la accion que escoja para eliminar del tratamiento:");
                                accionIndex = scan.nextInt();
                                aux.listaAcciones.remove(accionIndex - 1);
                                flag2 = 1;
                            } catch (IndexOutOfBoundsException e) {
                                System.out.println(Colores.rojo() + "Ingresaste una opcion incorrecta, intentalo nuevamente" + Colores.blanco());
                            } catch (InputMismatchException i) {
                                System.out.println(Colores.rojo() + "Ingresaste un tipo de dato incorrecto, intentalo nuevamente" + Colores.blanco());
                                scan.nextLine();
                            }
                        }
                    }

                    break;


                case 3:
                    System.out.println("Ingrese la duracion que desea para el tratamiento");
                    int x = scan.nextInt();
                    aux.setDuracion(x);
                    break;

                case 4:
                    System.out.println(Colores.verde() + "Guardando tratamieto..." + Colores.blanco());
                    break;


                default:
                    System.out.println(Colores.rojo() + "Opcion incorrecta, ingrese otra" + Colores.blanco());
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
