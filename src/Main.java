import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

//        Sistema sistema = new Sistema();
//        sistema.menu();


        // pacientes
        ArrayList<Paciente> pacientes = new ArrayList<>();
        pacientes.add(new Paciente("Juan", "Ros", "11247824", "paciente@gmail.com", "Casa"));//sin medico asignado
        pacientes.add(new Paciente("Elena", "Vargas", "40347821", "paciente@hotmail.com", "Coco", 14));//con medico asignado
        Persistencia.serializacion(pacientes, "pacientes.json");


        // medicos
        ArrayList<Medico> medicos = new ArrayList<>();
        ArrayList<Integer> pacientesMedico = new ArrayList<>();
        pacientesMedico.add(1);
        pacientesMedico.add(3);
        pacientesMedico.add(7);
        medicos.add(new Medico("Sandra", "Gonzales", "44963081", "medico@outlook.com", "Cielo"));   //sin lista de pacientes
        medicos.add(new Medico("Cintia", "Laiz", "56912074", "medico@gmail.com", "Cosa", pacientesMedico));   //con lista de pacientes
        Persistencia.serializacion(medicos, "medicos.json");


        //acciones
        ArrayList<Accion> acciones = new ArrayList<>();
        acciones.add(new AccionBooleana(1, 4, "Pastilla", "Tomar Pastilla"));
        acciones.add(new AccionBooleana(1, 2, "Presion", "Tomar Presion"));
        acciones.add(new AccionDouble(1,3,"Temperatura","Tomar Temperatura"));
        acciones.add(new AccionDouble(2,1,"Densiometria","Tomar Densidad"));
        Persistencia.serializacion(acciones, "acciones.json");



//        //Enfermedades
        ArrayList<Enfermedad> enfermedades = new ArrayList<>();
        enfermedades.add(new Enfermedad("Estres"));
        enfermedades.add(new Enfermedad("Gripe"));
        enfermedades.add(new Enfermedad("Alergia"));
        enfermedades.add(new Enfermedad("Amiloidosis"));
        enfermedades.add(new Enfermedad("Encondroma"));
        Persistencia.serializacion(enfermedades,"enfermedades.json");



        //tratamientos
        ArrayList<Tratamiento> tratamientos = new ArrayList<>();
        tratamientos.add(new Tratamiento(enfermedades.get(3))); // Vacio
        tratamientos.add(new Tratamiento(enfermedades.get(4), LocalDate.now(),acciones));   //con acciones
        Persistencia.serializacion(tratamientos, "tratamientos.json");



        // admins

//        Sistema sistema = new Sistema();    //falta crear sistema con listas ya cargadas
//        sistema.menu();

    }
}
