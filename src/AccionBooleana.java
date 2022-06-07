import java.util.Scanner;

public class AccionBooleana extends Accion{

    private boolean seHizo = false;

    public AccionBooleana(int cadaCuanto, int vecesPorDia, String nombre, String textoPregunta) {
        super(cadaCuanto, vecesPorDia, nombre, textoPregunta);
    }

    @Override
    public Registro accionar() throws AccionFallidaException{
        Scanner scan = new Scanner(System.in);
        Character opcion;
        System.out.println(super.getTextoPregunta());
        opcion = scan.nextLine().charAt(0);
        if(opcion != 's'){
            throw new AccionFallidaException();
        }else{
            Registro registro = new Registro(super.getNombre(), null , true);
            return registro;
        }
    }


}
