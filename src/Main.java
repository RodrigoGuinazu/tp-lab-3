import java.util.ArrayList;
import java.util.List;

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
//
//        //Crear 1 paciente
        List<Paciente> pacientes = new ArrayList<>();
//        Paciente paciente1 = new Paciente("Elena","Vargas","lorem@hotmail.com","Cocodrilo",14);
//        System.out.println(paciente1);
//        Paciente paciente2 = new Paciente("pedrito","Vargas","loremipsum@hotmail.com","abc123",14);
//        Paciente paciente3 = new Paciente("probando","agregar","nuevo@hotmail.com","hola",9);
//        System.out.println(paciente2);
//        pacientes.add(paciente1);
//        pacientes.add(paciente2);
//        pacientes.add(paciente3);

        pacientes = Persistencia.leerPacientes(pacientes);
        System.out.println(pacientes);
        //Sistema sistema = new Sistema(pacientes);
        //sistema.menu();


    }
}
