public class TomarPresion extends Accion{
    // Atributos

    // Constructores
    public TomarPresion(int duracion, int cadaCuanto, int vecesPorDia) {
        super(duracion, cadaCuanto, vecesPorDia,"Tomar Presion");
    }


    // Metodos
    @Override
    public Registro accionar()  throws AccionFallidaException{
        return null;
    }
}
