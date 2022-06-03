import java.util.Scanner;

public class AccionBooleana extends Accion{


    public AccionBooleana(int duracion, int cadaCuanto, int vecesPorDia, String nombr, String textoPregunta) {
        super(duracion, cadaCuanto, vecesPorDia, nombr, textoPregunta);
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
            Registro registro = new Registro(super.getNombre(), 0 , true);
            return registro;
        }
    }


}
