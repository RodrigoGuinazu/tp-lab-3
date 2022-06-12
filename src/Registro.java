import java.util.Scanner;

public class Registro {     //
    private String nombreAccion;
    private String rta;
    private Boolean seHizo;

    public Registro(String nombre, String rta, Boolean seHizo) {
        this.nombreAccion = nombre;
        this.rta = rta;
        this.seHizo = seHizo;
    }

    public Registro(String nombreAccion, Boolean seHizo) {
        this.nombreAccion = nombreAccion;
        this.seHizo = seHizo;
    }

    public String mostrarNombresRegistros() {
        return this.nombreAccion;
    }

    public void modificar() throws AccionFallidaException {
        Scanner scan = new Scanner(System.in);
        Character opcion;
        if(rta == null){
            System.out.println("Usted marco la accion " + this.nombreAccion + (this.seHizo ? " realizada " : " sin realizar "));
        }else{
            System.out.println("Usted ingreso en la accion " + this.nombreAccion + " el resultado: " + this.rta);
        }
        System.out.println("Desea modificar la accion? (s/n)");
        opcion = scan.nextLine().charAt(0);
        if (opcion != 's') {
            throw new AccionFallidaException();
        } else {
            if(rta == null){
                Boolean rta;
                if(this.seHizo == true){
                    System.out.println("Se marco la accion como sin realizar");
                    rta = false;
                }else{
                    System.out.println("Se marco la accion como realizada");
                    rta = true;
                }
                this.seHizo = rta;
            }else{
                System.out.println("Ingrese el nuevo resultado: ");
                String rta = scan.nextLine();
                this.rta = rta;
            }
        }
    }

    public String toStringParaHistorial() {

        String aux = "";
        aux += "La accion " + nombreAccion;
        if (this.seHizo){
            aux += " se realizo";
          if(rta!=null){
              aux += " y su resultado fue:"+ rta + ". \n";
          }else{
              aux += ". \n";
          }
        }else{
            aux += " no se realizo"+ ". \n";
        }



        return aux;


    }

    @Override
    public String toString() {
        return "Registro{" +
                "nombreAccion='" + nombreAccion + '\'' +
                ", rta='" + rta + '\'' +
                ", seHizo=" + seHizo +
                '}';
    }
}
