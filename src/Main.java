import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {


        // pacientes
        ArrayList<Paciente> pacientesMain = new ArrayList<>();

        Paciente paciente3 = new Paciente("Elena","Vargas","40347821","paciente1@hotmail.com","Coco",14);
        pacientesMain.add(paciente3);   //con medico asignado

        System.out.println(pacientesMain);

        Persistencia.serializacion(pacientesMain, "pacientes2.0.json");




        // medicos


        // admins


        //acciones
//        ArrayList<Accion> acciones = new ArrayList<>();
//

//        //tratamientos
//        ArrayList<Tratamiento> tratamientos = new ArrayList<>();
//

//        //enfermedades
//        ArrayList<Enfermedad> enfermedades = new ArrayList<>();
//
//


//        //crear un sistema con listas ya cargadas
//        Sistema sistema = new Sistema();
//        sistema.menu();




//        ArrayList<Paciente> pacientes = new ArrayList<>();

////        AccionBooleana accion = new AccionBooleana(5, 1, 1, "tomar temperatura", "cual es la temperatura?");
////        acciones.add(accion);

////        ArrayList<Tratamiento> tratamientos = new ArrayList<>();
////        tratamientos.add(new Tratamiento(new Enfermedad("Fiebre"), LocalDate.now(), LocalDate.now().plusDays(5), acciones));
////        paciente1.setHistorialClinico(tratamientos);
////        pacientes.add(paciente1);
////        Paciente paciente2 = new Paciente("pedrito","Vargas","loremipsum@hotmail.com","abc123",14);
////        Paciente paciente3 = new Paciente("probando","agregar","nuevo@hotmail.com","hola",9);
////        pacientes.add(paciente2);
////        pacientes.add(paciente3);
////
//       ArrayList<Medico> medicos = new ArrayList<>();
//       ArrayList<Integer> pacienteDelMedico = new ArrayList<>();
//        pacienteDelMedico.add(1);
//        Medico medico1 = new Medico("Elena medico","Vargas","lorem@hotmailmedico.com","Cocodrilo", pacienteDelMedico);
////        Medico medico2 = new Medico("pedrito medico","Vargas","loremipsum@hotmailmedico.com","abc123",new ArrayList<Integer>());
////        Medico medico3 = new Medico("probando medico","agregar","nuevo@hotmailmedico.com","hola",new ArrayList<Integer>());
////        medicos.add(medico1);
////        medicos.add(medico2);
////        medicos.add(medico3);
//
////        ArrayList<Usuario> usuarios = new ArrayList<>();
////
//        pacientes = Persistencia.deserializacion("pacientes.json", Paciente.class);
////        //medicos = Persistencia.deserializacion("medicos.json", Medico.class);
////        for(Paciente p : pacientes){
////            System.out.println(p + "\n");
////        }
//
////        ArrayList<Accion> listaAcciones = new ArrayList<Accion>();
////        listaAcciones.add(new AccionBooleana(1, 1, "Tomar Pastilla", "Debe tomar la pastilla"));
////        listaAcciones.add(new AccionDouble(1, 1, "Tomarse la fiebre", "Debe tomarse la fiebre"));
////        Persistencia.serializacion(listaAcciones, "acciones.json");
////
////        Enfermedad enfermedad1 = new Enfermedad("Fiebre");
////
////        ArrayList<Tratamiento> tratamientos = new ArrayList<>();
////        tratamientos.add(new Tratamiento(enfermedad1, null, null, listaAcciones));
////        Persistencia.serializacion(tratamientos, "tratamientos.json");


    }
}
