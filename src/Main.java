import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

//        Sistema sistema = new Sistema();
//        sistema.menu();

        // pacientes
        ArrayList<Paciente> pacientes = new ArrayList<>();

        Paciente paciente1 = new Paciente("Juan","Ros","11247824","paciente@gmail.com","Casa");
        pacientes.add(paciente1);   //sin medico asignado

        Paciente paciente2 = new Paciente("Elena","Vargas","40347821","paciente@hotmail.com","Coco",14);
        pacientes.add(paciente2);   //con medico asignado
//        System.out.println(pacientesMain);
        Persistencia.serializacion(pacientes, "pacientes.json");



        // medicos
        ArrayList<Medico> medicos = new ArrayList<>();
        ArrayList<Integer> pacientesMedico = new ArrayList<>();
        pacientesMedico.add(1);
        pacientesMedico.add(3);
        pacientesMedico.add(7);

        Medico medico1 = new Medico("Sandra","Gonzales","44963081","medico@outlook.com","Cielo");
        medicos.add(medico1);   //sin lista de pacientes

        Medico medico2 = new Medico("Cintia","Laiz","56912074","medico@gmail.com","Cosa",pacientesMedico);
        medicos.add(medico2);   //con lista de pacientes

        Persistencia.serializacion(medicos,"medicos.json");


        //acciones
//        ArrayList<Accion> acciones = new ArrayList<>();

        ////        AccionBooleana accion = new AccionBooleana(5, 1, 1, "tomar temperatura", "cual es la temperatura?");
////        acciones.add(accion);
//        ArrayList<Accion> listaAcciones = new ArrayList<Accion>();
////        listaAcciones.add(new AccionBooleana(1, 1, "Tomar Pastilla", "Debe tomar la pastilla"));
////        listaAcciones.add(new AccionDouble(1, 1, "Tomarse la fiebre", "Debe tomarse la fiebre"));
////        Persistencia.serializacion(listaAcciones, "acciones.json");
////





        // admins






//        //tratamientos
//        ArrayList<Tratamiento> tratamientos = new ArrayList<>();
//
////        ArrayList<Tratamiento> tratamientos = new ArrayList<>();
////        tratamientos.add(new Tratamiento(new Enfermedad("Fiebre"), LocalDate.now(), LocalDate.now().plusDays(5), acciones));

//        ArrayList<Tratamiento> tratamientos = new ArrayList<>();
////        tratamientos.add(new Tratamiento(enfermedad1, null, null, listaAcciones));
////        Persistencia.serializacion(tratamientos, "tratamientos.json");



//        //enfermedades
//        ArrayList<Enfermedad> enfermedades = new ArrayList<>();
//     Enfermedad enfermedad1 = new Enfermedad("Fiebre");
//


//        Sistema sistema = new Sistema();    //falta crear sistema con listas ya cargadas
//        sistema.menu();

    }
}
