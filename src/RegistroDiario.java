import java.time.LocalDate;
import java.util.ArrayList;

public class RegistroDiario {
    private ArrayList<Registro> listaRegistros;
    private LocalDate fecha;

    public RegistroDiario (){
        this.fecha  = LocalDate.now();
        this.listaRegistros = new ArrayList<Registro>();
    }


    public LocalDate getFecha() {
        return fecha;
    }

    public void agregarRegistro(Registro registro){
        listaRegistros.add(registro);
    }

    public void modificarRegistro(){
        mostrarRegistros();
        //preguntamos que registro y guardamos el index en una variable int
        int aux  = 0;
        listaRegistros.get(aux).setearValores();

    }

    public void mostrarRegistros(){
        Integer num = 0;

        for (Registro  a: listaRegistros) {
            System.out.println(num.toString() + a.mostrarNombresRegistros());
        }
        // les mostramos todos los registros del dia y lo hacemos elegir cual quiere modificar, almacenamos el indice en una variable aux;

    }

}

