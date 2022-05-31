import java.time.LocalDate;

public abstract class Accion {
    // Atributos
    private int duracion; // en dias
    private LocalDate ultimaNoti; // ultima vez que se le notifico la accion
    private int cadaCuanto; // cada cuanto dias tiene que hacer la accion
    private int vecesPorDia; // cuantas veces por dia tiene que hacer la accion
    private String nombre;

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
