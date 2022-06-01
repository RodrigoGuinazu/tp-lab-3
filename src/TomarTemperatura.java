import java.util.Scanner;

public class TomarTemperatura extends Accion{
    // Atributos

    // Constructores
    public TomarTemperatura(int duracion, int cadaCuanto, int vecesPorDia) {
        super(duracion, cadaCuanto, vecesPorDia,"Tomar Temperatura");
    }

    // Metodos
    @Override
    public Registro accionar() throws AccionFallidaException{
        Scanner scan = new Scanner(System.in);
        String rta;
        Character opcion;
        System.out.println("Tomar fiebre? (s/n)");
        opcion = scan.nextLine().charAt(0);
        if(opcion != 's'){
            throw new AccionFallidaException();
        }else{
            System.out.println("Ingrese su temperatura: ");
            rta = scan.nextLine();
            Registro registro = new Registro(super.getNombre(), rta, true);
            return registro;
        }
    }
}
