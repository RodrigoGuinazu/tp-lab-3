public class Main {
    public static void main(String[] args) {

        //falta crear sistema con listas ya cargadas


        // Enfermedades
//        ArrayList<Enfermedad> enfermedades = new ArrayList<>();
//        enfermedades.add(new Enfermedad("Estres"));
//        enfermedades.add(new Enfermedad("Gripe"));
//        enfermedades.add(new Enfermedad("Alergia"));
//        enfermedades.add(new Enfermedad("Amiloidosis"));
//        enfermedades.add(new Enfermedad("Encondroma"));
//        Persistencia.serializacion(enfermedades, "enfermedades.json");
//

        // Acciones
//        ArrayList<Accion> acciones = new ArrayList<>();
//        acciones.add(new AccionBooleana(1, 4, "Pastilla", "Tomar Pastilla"));
//        acciones.add(new AccionBooleana(1, 2, "Presion", "Tomar Presion"));
//        acciones.add(new AccionDouble(1, 3, "Temperatura", "Tomar Temperatura"));
//        acciones.add(new AccionDouble(2, 1, "Densiometria", "Tomar Densidad"));
//        Persistencia.serializacionAcciones(acciones);
//        AccionBooleana buli = (AccionBooleana) acciones.get(0);
//        buli.setSeHizo(true);

//

        // Tratamientos
//        ArrayList<Tratamiento> tratamientos = new ArrayList<>();
//        tratamientos.add(new Tratamiento(enfermedades.get(4), 7, acciones));
//        tratamientos.add(new Tratamiento(enfermedades.get(3),14,acciones));
//        Persistencia.serializacionTratamientos(tratamientos);
//        tratamientos.get(0).setFinalizado(true);


        // Pacientes
//        ArrayList<Paciente> pacientes = new ArrayList<>();
//        pacientes.add(new Paciente("Juan", "Ros", "11247824", "paciente@gmail.com", "Casa"));//sin medico asignado
//        pacientes.add(new Paciente("Elena", "Vargas", "40347821", "paciente@hotmail.com", "Coco", 14));//con medico asignado
//        pacientes.add(new Paciente("Julian", "Gallo", "93746352", "paciente@outlook.com", "Chicha", tratamientos, 3));
//        Persistencia.serializacionPacientes(pacientes);

//
        // Medicos
//        ArrayList<Medico> medicos = new ArrayList<>();
//        ArrayList<Integer> pacientesMedico = new ArrayList<>();
//        pacientesMedico.add(1);
//        pacientesMedico.add(3);
//        pacientesMedico.add(7);
//        medicos.add(new Medico("Sandra", "Gonzales", "44963081", "medico@outlook.com", "Cielo"));   //sin lista de pacientes
//        medicos.add(new Medico("Cintia", "Laiz", "56912074", "medico@gmail.com", "Cosa", pacientesMedico));   //con lista de pacientes
//        Persistencia.serializacion(medicos, "medicos.json");
//
//
        // admins
//        ArrayList<Admin> admins = new ArrayList<>();
//        admins.add(new Admin("Rene", "Favaloro", "16703723", "admin@hotmail.com", "Cusco"));
//        admins.add(new Admin("Roberto", "Estévez", "19594105", "admin@gmail.com", "Caña"));
//        Persistencia.serializacion(admins, "admins.json");
//        Persistencia.serializacion(admins, "acciones.json");
//


        Sistema sistema = new Sistema();

    }
}
