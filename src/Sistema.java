import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Sistema {
    Scanner scan = new Scanner(System.in);

    // Atributos
    private ArrayList<Usuario> usuarios;
    private static LocalDate fechaDelDia = LocalDate.now();
    private Usuario usuarioLogueado;

    //Constructor
    public Sistema() {
        this.usuarios = new ArrayList<>();
        this.usuarioLogueado = null;
    }

    public static LocalDate getFechaDelDia() {
        return fechaDelDia;
    }

    public static void agregarDias(long x) {
        if (x < 0) {
            System.out.println(Colores.amarillo() + "Solo se puede ir a fechas futuras" + Colores.blanco());
        } else {
            fechaDelDia = fechaDelDia.plusDays(x);
            System.out.println(Colores.amarillo() + "Cambiando la fecha del dia..." + Colores.blanco());
        }
    }

    // Metodos
    public void menu() {
        // escondo los warning
        System.err.close();
        System.setErr(System.out);

        Scanner scan = new Scanner(System.in);
        int opcionMenu = 0;

        do {
            // generamos la lista de usuarios para poder utilizarla en la logica del login
            this.usuarios = new ArrayList<Usuario>();

            ArrayList<Medico> medicosAux = Persistencia.deserializacion("medicos.json", Medico.class);
            ArrayList<Paciente> pacientesAux = Persistencia.deserializacionPacientes();
            ArrayList<Admin> adminsAux = Persistencia.deserializacion("admins.json", Admin.class);

            this.usuarios.addAll(medicosAux);
            this.usuarios.addAll(pacientesAux);
            this.usuarios.addAll(adminsAux);

            System.out.println(Colores.violeta() + "Menu Principal" + Colores.blanco());
            System.out.println("Fecha del dia: " + fechaDelDia + "(Año/Mes/Dia)");
            System.out.println("[1] Log In");
            System.out.println("[2] Simular dias");
            System.out.println("[3] Resetear fecha");
            System.out.println("[4] Salir del programa");
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
                    this.usuarioLogueado = this.login();
                    if (this.usuarioLogueado != null) {
                        if (this.usuarioLogueado instanceof Paciente) {
                            this.menuPaciente();
                        } else if (this.usuarioLogueado instanceof Medico) {
                            this.menuMedico();
                        } else {
                            this.menuAdmin();
                        }
                    }
                    break;

                case 2:
                    int flagDias = 0;
                    while (flagDias == 0) {
                        try {
                            System.out.println("Cuantos dias desea adelantarse?");
                            Sistema.agregarDias(scan.nextLong());
                            flagDias = 1;
                        } catch (InputMismatchException e) {
                            System.out.println(Colores.rojo() + "Ingresaste un tipo de dato incorrecto, intentalo nuevamente" + Colores.blanco());
                            scan.nextLine();
                        }
                    }
                    break;

                case 3:
                    fechaDelDia = LocalDate.now();
                    System.out.println(Colores.amarillo() + "Resetenado la fecha del dia..." + Colores.blanco());
                    break;

                case 4:
                    System.out.println(Colores.rojo() + "Cerrando el programa..." + Colores.blanco());
                    break;

                default:
                    System.out.println(Colores.rojo() + "Opcion incorrecta, ingrese otra" + Colores.blanco());
            }
        } while (opcionMenu != 4);
    }


    public void menuPaciente() {
        int opcionPaciente = 0;
        ((Paciente) usuarioLogueado).notificarPaciente();
        do {
            System.out.println(Colores.violeta() + "Menu Paciente" + Colores.blanco());
            System.out.println("[1] Realizar acciones del dia");
            System.out.println("[2] Modificar acciones del dia");
            System.out.println("[3] Mostrar acciones del dia");
            System.out.println("[4] Log Out");
            System.out.println("Ingrese una opcion:");

            int flagSwitch = 0;
            while (flagSwitch == 0) {
                try {
                    opcionPaciente = scan.nextInt();
                    flagSwitch = 1;
                } catch (InputMismatchException i) {
                    System.out.println(Colores.rojo() + "Ingresaste un tipo de dato incorrecto, intentalo nuevamente" + Colores.blanco());
                    System.out.println("Ingrese una opcion:");
                    scan.nextLine();
                }
            }

            switch (opcionPaciente) {
                case 1:
                    try {
                        ((Paciente) usuarioLogueado).realizarAcciones();
                        persistirPacienteActual(((Paciente) usuarioLogueado));
                    } catch (NullPointerException e) {
                        System.out.println(Colores.amarillo() + "No te encuentras realizando un tratamiento" + Colores.blanco());
                    }
                    break;
                case 2:
                    ((Paciente) usuarioLogueado).editarAccionesDelDia();
                    persistirPacienteActual(((Paciente) usuarioLogueado));
                    break;

                case 3:

                    ((Paciente) usuarioLogueado).notificarPaciente();
                    break;

                case 4:
                    persistirPacienteActual(((Paciente) usuarioLogueado));
                    this.logout();
                    break;

                default:
                    System.out.println(Colores.rojo() + "Opcion incorrecta, ingrese otra" + Colores.blanco());
            }
        } while (opcionPaciente != 4);
    }


    public void menuMedico() {
        System.out.println(((Medico) usuarioLogueado).notificarMedico());
        int opcionMedico = 0;
        do {
            System.out.println(Colores.violeta() + "Menu Medico" + Colores.blanco());
            System.out.println("[1] Asignar tratamiento");
            System.out.println("[2] Ver las notificaciones del dia");
            System.out.println("[3] Ver historiales de los pacientes");
            System.out.println("[4] Ver historial del tratamiento actual de los pacientes");
            System.out.println("[5] Log Out");
            System.out.println("Ingrese una opcion:");

            int flagSwitch = 0;
            while (flagSwitch == 0) {
                try {
                    opcionMedico = scan.nextInt();
                    flagSwitch = 1;
                } catch (InputMismatchException i) {
                    System.out.println(Colores.rojo() + "Ingresaste un tipo de dato incorrecto, intentalo nuevamente" + Colores.blanco());
                    System.out.println("Ingrese una opcion:");
                    scan.nextLine();
                }
            }

            switch (opcionMedico) {
                case 1:
                    try {
                        ((Medico) usuarioLogueado).diagnosticarPacientes();
                    } catch (DniInexistenteException e) {
                    }
                    break;

                case 2:
                    System.out.println(((Medico) usuarioLogueado).notificarMedico());
                    break;

                case 3:
                    try {
                        ((Medico) usuarioLogueado).verHistorialPaciente();
                    } catch (DniInexistenteException e) {
                    }
                    break;

                case 4:
                    try {
                        ((Medico) usuarioLogueado).verHistorialTratamientoActual();
                    } catch (DniInexistenteException e) {
                    }
                    break;

                case 5:
                    this.logout();

                    break;

                default:
                    System.out.println(Colores.rojo() + "Opcion incorrecta, ingrese otra" + Colores.blanco());
            }
        } while (opcionMedico != 5);
    }


    public void menuAdmin() {
        int opcion = 0;
        do {
            System.out.println(Colores.violeta() + "Menu Admin" + Colores.blanco());
            System.out.println("[1] Registrar un nuevo paciente");
            System.out.println("[2] Registrar un nuevo medico");
            System.out.println("[3] Agregar una nueva enfermedad");
            System.out.println("[4] Crear un nuevo tratamiento");
            System.out.println("[5] Editar un tratamiento");
            System.out.println("[6] Crear una nueva accion");
            System.out.println("[7] Log Out");
            System.out.println("Ingrese una opcion:");

            int flagSwitch = 0;
            while (flagSwitch == 0) {
                try {
                    opcion = scan.nextInt();
                    flagSwitch = 1;
                } catch (InputMismatchException i) {
                    System.out.println(Colores.rojo() + "Ingresaste un tipo de dato incorrecto, intentalo nuevamente" + Colores.blanco());
                    System.out.println("Ingrese una opcion:");
                    scan.nextLine();
                }
            }

            switch (opcion) {
                case 1: {
                    ((Admin) usuarioLogueado).registrarPaciente();
                }
                break;

                case 2: {
                    ((Admin) usuarioLogueado).registrarMedico();
                }
                break;

                case 3: {
                    ((Admin) usuarioLogueado).agregarEnfermedad();
                }
                break;

                case 4: {
                    ((Admin) usuarioLogueado).crearTratamiento();
                }
                break;

                case 5: {

                    ((Admin) usuarioLogueado).editarTratamiento(new Tratamiento());
                }
                break;

                case 6: {
                    ((Admin) usuarioLogueado).crearAccion();
                }
                break;

                case 7: {
                    this.logout();
                }
                break;

                default:
                    System.out.println(Colores.rojo() + "Opcion incorrecta, ingrese otra" + Colores.blanco());
            }
        } while (opcion != 7);
    }

    public Usuario login() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Log In");
        Usuario rta = null;
        Character opcion = 's';
        while (rta == null && opcion == 's') {

            System.out.println("Mail: ");
            String mail = scan.nextLine();

            System.out.println("Contraseña: ");
            String pass = scan.nextLine();

            try {
                rta = validarCredenciales(mail, pass);
                opcion = 'n';
            } catch (CredencialesIncorrectasException e) {
                System.out.println("Desea intentarlo de vuelta? (s/n):");
                opcion = scan.nextLine().charAt(0);
            }
        }
        return rta;
    }

    public void persistirPacienteActual(Paciente logueado) {
        ArrayList<Paciente> pacientes = Persistencia.deserializacionPacientes();
        for (Paciente a : pacientes) {
            if (a.getId().equals(logueado.getId())) {
                pacientes.set(pacientes.indexOf(a), logueado);
            }
        }
        Persistencia.serializacionPacientes(pacientes);
    }

    public Usuario validarCredenciales(String mail, String pass) throws CredencialesIncorrectasException {
        for (Usuario u : this.usuarios) {
            if (u.getMail().equalsIgnoreCase(mail)) {
                if (u.getPassword().equals(pass)) {
                    return u;
                } else {
                    throw new CredencialesIncorrectasException(mail);
                }
            }
        }
        throw new CredencialesIncorrectasException();
    }

    public void logout() {
        this.usuarioLogueado = null;

    }

    public static boolean comprobarCorrespodenAccion(LocalDate fecha, int cadaCuanto) {
        while (fecha.isBefore(fechaDelDia)) {
            fecha = fecha.plusDays(cadaCuanto);
        }

        if (fecha.isEqual(Sistema.getFechaDelDia())) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean comprobarCorrespodiaAccionPasado(LocalDate inicioTratamiento, LocalDate fechaRegistro, int cadaCuanto) {
        while (inicioTratamiento.isBefore(fechaRegistro)) {
            inicioTratamiento = inicioTratamiento.plusDays(cadaCuanto);
        }

        if (inicioTratamiento.isEqual(fechaRegistro)) {
            return true;
        } else {
            return false;
        }
    }
}

