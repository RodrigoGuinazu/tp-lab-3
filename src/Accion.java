import java.time.LocalDate;

public abstract class Accion {
    // Atributos
    protected int duracion; // en dias
    protected LocalDate ultimaNoti; // ultima vez que se le notifico la accion
    protected int cadaCuanto; // cada cuanto dias tiene que hacer la accion
    protected int vecesPorDia; // cuantas veces por dia tiene que hacer la accion

    // Constructores

    public Accion(int duracion, int cadaCuanto, int vecesPorDia) {
        this.duracion = duracion;
        this.cadaCuanto = cadaCuanto;
        this.vecesPorDia = vecesPorDia;
    }

    // Metodos
    public abstract void accionar();
}
