public class Nebulizarce extends Accion{
    // Atributos

    // Constructores
    public Nebulizarce(int duracion, int cadaCuanto, int vecesPorDia) {
        super(duracion, cadaCuanto, vecesPorDia,"Nebulizarse");
    }

    // Metodos
    @Override
    public Registro accionar() {
        return null;
    }
}
