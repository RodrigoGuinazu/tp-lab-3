public class Main {
    public static void main(String[] args) {


        // Registros Diarios
//        Stack<RegistroDiario> registrosDiarios = new Stack<>();
//        registrosDiarios.add(new RegistroDiario());
//        registrosDiarios.peek().agregarRegistro(new Registro("AccionGenerica", true));

        // Enfermedades
        // ArrayList<Enfermedad> enfermedades = new ArrayList<>();
//        enfermedades.add(new Enfermedad("Estres"));
//        enfermedades.add(new Enfermedad("Gripe"));
//        enfermedades.add(new Enfermedad("Alergia"));
//        enfermedades.add(new Enfermedad("Amiloidosis"));
//        enfermedades.add(new Enfermedad("Encondroma"));
//        Persistencia.serializacion(enfermedades, "enfermedades.json");


        // Acciones
//        ArrayList<Accion> acciones = new ArrayList<>();
//        acciones.add(new AccionBooleana(1, 1, "Pastilla", "Tomar Pastilla"));
//        acciones.add(new AccionBooleana(1, 2, "Presion", "Tomar Presion"));
//        acciones.add(new AccionDouble(1, 3, "Temperatura", "Tomar Temperatura"));
//        acciones.add(new AccionDouble(2, 1, "Densiometria", "Tomar Densidad"));
//        Persistencia.serializacionAcciones(acciones);


        // Tratamientos
//        ArrayList<Tratamiento> tratamientos = new ArrayList<>();
//        tratamientos.add(new Tratamiento(enfermedades.get(0), 7, acciones));
//        tratamientos.add(new Tratamiento(enfermedades.get(3),14,acciones));
//        tratamientos.add(new Tratamiento(new Enfermedad("Gripe"), 1, acciones));
//        tratamientos.get(0).setFinalizado(true);
//        tratamientos.get(0).setListaRegistrosDiarios(registrosDiarios);
//        tratamientos.get(0).setInicioDate(LocalDate.now().minusDays(2));
//        tratamientos.get(0).setFinDate(LocalDate.now().minusDays(1));
//        Persistencia.serializacionTratamientos(tratamientos);


        // Pacientes
//        ArrayList<Paciente> pacientes = new ArrayList<>();
//        pacientes.add(new Paciente("Juan", "Ros", "11247824", "paciente@gmail.com", "Casa"));//sin medico asignado
//        pacientes.add(new Paciente("Elena", "Vargas", "40347821", "paciente@hotmail.com", "Coco", 1));//con medico asignado


        // Medicos
//        ArrayList<Medico> medicos = new ArrayList<>();
//        medicos.add(new Medico("Sandra", "Gonzales", "44963081", "medico@outlook.com", "Cielo"));   //sin lista de pacientes
//        ArrayList<Integer> listaDePacientesDelMedico = new ArrayList<>();
//        listaDePacientesDelMedico.add(1);
//        listaDePacientesDelMedico.add(2);
//        listaDePacientesDelMedico.add(3);
//        medicos.add(new Medico("Cintia", "Laiz", "56912074", "medico@gmail.com", "Cosa", pacientesMedico));   //con lista de pacientes
//        Persistencia.serializacion(medicos, "medicos.json");
//

        // admins
//        ArrayList<Admin> admins = new ArrayList<>();
//        admins.add(new Admin("Rene", "Favaloro", "16703723", "admin@hotmail.com", "Cusco"));
//        admins.add(new Admin("Roberto", "Estévez", "19594105", "admin@gmail.com", "Caña"));
//        Persistencia.serializacion(admins, "admins.json");


        //Paciente con : historial(unico tratamiento con unica accion) y tratamiento actual(unica accion)
//        ArrayList<Paciente> pacientes1 = new ArrayList<>();
//
//        acciones.get(0).setUltimaNoti(LocalDate.now().minusDays(1));
//        Tratamiento tratamiento1 = new Tratamiento(new Enfermedad("Caida de pelo"),1,acciones);
//        Paciente paciente1 = new Paciente("Julian", "Gallo", "93746352", "paciente@outlook.com", "Chicha");
//
//        Persistencia.serializacionPacientes(pacientes1);

        Sistema sistema = new Sistema();
        sistema.menu();
    }
}
