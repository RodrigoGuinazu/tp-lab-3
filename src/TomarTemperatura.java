public class TomarTemperatura extends Accion{
    // Atributos

    // Constructores
    public TomarTemperatura(int duracion, int cadaCuanto, int vecesPorDia) {
        super(duracion, cadaCuanto, vecesPorDia,"Tomar Temperatura");
    }

    // Metodos
    @Override
    public Registro accionar() {
        return null;
    }
}
