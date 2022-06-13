import java.util.Scanner;

public class AccionDouble extends Accion {


    public AccionDouble(int cadaCuanto, int vecesPorDia, String nombr, String textoPregunta) {
        super(cadaCuanto, vecesPorDia, nombr, textoPregunta);
    }


    public void mostrarAccionDouble() {
        System.out.println(super.mostrarAccion());
    }


    @Override
    public AccionDouble clonarAccion() {
        AccionDouble aux = new AccionDouble(this.cadaCuanto, this.vecesPorDia, this.nombre, this.textoPregunta);
        return aux;
    }

    @Override
    public Registro accionar() throws AccionFallidaException {
        Scanner scan = new Scanner(System.in);
        String rta;
        Character opcion;
        System.out.println("Quiere realizar la accion: " + super.getNombre() + ": (s/n)");
        opcion = scan.nextLine().charAt(0);
        if (opcion != 's') {
            throw new AccionFallidaException();
        } else {
            System.out.println("Ingrese el resultado: ");
            rta = scan.nextLine();


            Registro registro = new Registro(super.getNombre(), rta, true);
            return registro;
        }
    }

    public String toString() {
        return "AccionDouble{" + super.toString() +
                "seHizo="  +
                '}';
    }

}
