import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RegistroDiario {
    private LocalDate fecha;
    protected ArrayList<Registro> listaRegistros;


    public RegistroDiario() {
        this.fecha = LocalDate.now();
        this.listaRegistros = new ArrayList<Registro>();
    }


    public LocalDate getFecha() {
        return fecha;
    }

    public void agregarRegistro(Registro registro) {
        listaRegistros.add(registro);
    }

    public void modificarRegistro() {
        Scanner scan = new Scanner(System.in);
        int flag = 0;

        while (flag == 0){
            try{
                int i = 1;
                for(Registro r : listaRegistros){
                    System.out.println("["+i+"] " + r.mostrarNombresRegistros());
                    i++;
                }
                System.out.println("Ingrese el numero del Registro que desea modificar: ");
                int aux = scan.nextInt();
                listaRegistros.get(aux-1).modificar();
                flag = 1;
            }catch (IndexOutOfBoundsException e){
                System.out.println("Ingresaste una opcion incorrecta, intentalo nuevamente");
            }catch (InputMismatchException i) {
                System.out.println("Ingresaste un tipo de dato incorrecto, intentalo nuevamente");
                scan.nextLine();
            }catch (AccionFallidaException f){
                flag = 1;
            }
        }
    }

    public void mostrarRegistros() {
        Integer num = 0;

        for (Registro a : listaRegistros) {
            System.out.println(num.toString() + a.mostrarNombresRegistros());
        }
        // les mostramos todos los registros del dia y lo hacemos elegir cual quiere modificar, almacenamos el indice en una variable aux;

    }
    public String mostrarRegistrosParaToString() {
        String rta = "";

        for (Registro a : listaRegistros) {
            rta += a.toStringParaHistorial();
        }
        return rta;

    }

    @Override
    public String toString() {
        return "Registro de la fecha: " +
                 fecha +"\n" + mostrarRegistrosParaToString()
                ;
    }
}

