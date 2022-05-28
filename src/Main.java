import java.util.ArrayList;

public class Main {
    public static void main(String[] args){

        //Crear 1 admin
        Admin admin1 = new Admin("admin","admin","lorem@gmail.com","Conejo");
        System.out.println(admin1);

        //Crear 1 admin
        Admin admin2 = new Admin("admin","admin","lorem@yahoo.com","Caballo");
        System.out.println(admin2);

        //Crear 1 medico
        ArrayList <Integer> listaIds = new ArrayList<>();
        listaIds.add(7);
        listaIds.add(3);
        listaIds.add(1);
        Medico medico1 = new Medico("Carlos","Maslaton","lorem@outlook","Cotorra",listaIds);
        System.out.println(medico1);

        //Crear 1 paciente
        Paciente paciente1 = new Paciente("Elena","Vargas","lorem@hotmail.com","Cocodrilo",14);
        System.out.println(paciente1);


    }
}
