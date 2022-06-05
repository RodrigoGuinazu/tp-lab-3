import java.util.ArrayList;

public class Main {
    public static void main(String[] args){

//        //Crear 1 admin
//        Admin admin1 = new Admin("admin","admin","lorem@gmail.com","Conejo");
//        System.out.println(admin1);
//
//        //Crear 1 admin
//        Admin admin2 = new Admin("admin","admin","lorem@yahoo.com","Caballo");
//        System.out.println(admin2);
//
//        //Crear 1 medico
//        ArrayList<Integer> listaIds = new ArrayList<>();
//        listaIds.add(7);
//        listaIds.add(3);
//        listaIds.add(1);
//        Medico medico1 = new Medico("Carlos","Maslaton","lorem@outlook","Cotorra",listaIds);
//        System.out.println(medico1);

        ArrayList<Paciente> pacientes = new ArrayList<>();
//        ArrayList<Accion> acciones = new ArrayList<Accion>();
//        AccionBooleana accion = new AccionBooleana(5, 1, 1, "tomar temperatura", "cual es la temperatura?");
//        acciones.add(accion);
//        Paciente paciente1 = new Paciente("Elena","Vargas","lorem@hotmail.com","Cocodrilo",14);
//        ArrayList<Tratamiento> tratamientos = new ArrayList<>();
//        tratamientos.add(new Tratamiento(new Enfermedad("Fiebre"), LocalDate.now(), LocalDate.now().plusDays(5), acciones));
//        paciente1.setHistorialClinico(tratamientos);
//        pacientes.add(paciente1);
//        Paciente paciente2 = new Paciente("pedrito","Vargas","loremipsum@hotmail.com","abc123",14);
//        Paciente paciente3 = new Paciente("probando","agregar","nuevo@hotmail.com","hola",9);
//        pacientes.add(paciente2);
//        pacientes.add(paciente3);
//
       ArrayList<Medico> medicos = new ArrayList<>();
//        Medico medico1 = new Medico("Elena medico","Vargas","lorem@hotmailmedico.com","Cocodrilo", new ArrayList<Integer>());
//        Medico medico2 = new Medico("pedrito medico","Vargas","loremipsum@hotmailmedico.com","abc123",new ArrayList<Integer>());
//        Medico medico3 = new Medico("probando medico","agregar","nuevo@hotmailmedico.com","hola",new ArrayList<Integer>());
//        medicos.add(medico1);
//        medicos.add(medico2);
//        medicos.add(medico3);

        ArrayList<Usuario> usuarios = new ArrayList<>();
        
        pacientes = Persistencia.deserializacion("pacientes.json", Paciente.class);
        //medicos = Persistencia.deserializacion("medicos.json", Medico.class);
        for(Paciente p : pacientes){
            System.out.println(p + "\n");
        }
    }
}
