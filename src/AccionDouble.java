import java.util.Scanner;

public class AccionDouble extends Accion{
    private float rta;

    public AccionDouble(int cadaCuanto, int vecesPorDia, String nombr, String textoPregunta) {
        super(cadaCuanto, vecesPorDia, nombr, textoPregunta);
    }

    @Override
    public Registro accionar() throws AccionFallidaException{
        Scanner scan = new Scanner(System.in);
        String rta;
        Character opcion;
        System.out.println(super.getTextoPregunta());
        opcion = scan.nextLine().charAt(0);
        if(opcion != 's'){
            throw new AccionFallidaException();
        }else{
            System.out.println("Ingrese el resultado: ");
            rta = scan.nextLine();
            Registro registro = new Registro(super.getNombre(), rta, true);
            return registro;
        }
    }

}
