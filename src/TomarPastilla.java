public class TomarPastilla extends Accion{
    // Atributos

    // Constructores



    public TomarPastilla(int duracion, int cadaCuanto, int vecesPorDia) {
        super(duracion, cadaCuanto, vecesPorDia, "Tomar Pastilla");
    }

    // Metodos
    @Override
    public Registro accionar()  throws AccionFallidaException{
        return null;
    }
}
