import java.util.Scanner;

public class AccionBooleana extends Accion {

    private boolean seHizo = false;

    public AccionBooleana(int cadaCuanto, int vecesPorDia, String nombre, String textoPregunta) {
        super(cadaCuanto, vecesPorDia, nombre, textoPregunta);
    }

    @Override
    public AccionBooleana clonarAccion() {
        AccionBooleana aux = new AccionBooleana(this.cadaCuanto, this.vecesPorDia, this.nombre, this.textoPregunta);
        return aux;
    }

    @Override
    public Registro accionar() throws AccionFallidaException {
        Scanner scan = new Scanner(System.in);
        Character opcion;
        System.out.println("Quiere realizar la accion: " + super.getNombre());
        opcion = scan.nextLine().charAt(0);
        if (opcion != 's') {
            throw new AccionFallidaException();
        } else {
            Registro registro = new Registro(super.getNombre(), "no Aplica", true);
            return registro;
        }
    }

    @Override
    public String toString() {
        return "AccionBooleana{" + super.toString()+
                "seHizo=" + seHizo +
                '}';
    }
}
