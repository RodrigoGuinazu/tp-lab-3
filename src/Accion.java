import java.time.LocalDate;

public abstract class Accion {
    // Atributos
    protected int duracion; // en dias
    protected LocalDate ultimaNoti; // ultima vez que se le notifico la accion
    protected int cadaCuanto; // cada cuanto dias tiene que hacer la accion
    protected int vecesPorDia; // cuantas veces por dia tiene que hacer la accion
    protected String nombre;

    // Constructores
    public Accion(int duracion, int cadaCuanto, int vecesPorDia, String nombre) {
        this.duracion = duracion;
        this.cadaCuanto = cadaCuanto;
        this.vecesPorDia = vecesPorDia;
        this.nombre = nombre;
    }

    // Metodos
    public abstract Registro accionar(); //accionar debe devolver el registro ya creado
}
