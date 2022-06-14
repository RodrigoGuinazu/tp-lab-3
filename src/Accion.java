import java.time.LocalDate;

public abstract class Accion { // no puede ser abstract por el deserializador
    // Atributos
    protected LocalDate ultimaNoti = null; // ultima vez que se le notifico la accion
    protected String textoPregunta;
    protected Integer cadaCuanto; // cada cuanto dias tiene que hacer la accion
    protected Integer vecesPorDia; // cuantas veces por dia tiene que hacer la accion

    protected String nombre;

    // Constructores
    public Accion(Integer cadaCuanto, Integer vecesPorDia, String nombre, String textoPregunta) {
        this.textoPregunta = textoPregunta;
        this.cadaCuanto = cadaCuanto;
        this.vecesPorDia = vecesPorDia;
        this.nombre = nombre;
    }

    // Metodos



    public Integer getCadaCuanto() {
        return cadaCuanto;
    }

    public Integer getVecesPorDia() {
        return vecesPorDia;
    }

    public LocalDate getUltimaNoti() {
        return ultimaNoti;
    }


    public void setUltimaNoti(LocalDate ultimaNoti) {
        this.ultimaNoti = ultimaNoti;
    }

    public void setTextoPregunta(String textoPregunta) {
        this.textoPregunta = textoPregunta;
    }

    public void setCadaCuanto(Integer cadaCuanto) {
        this.cadaCuanto = cadaCuanto;
    }

    public void setVecesPorDia(Integer vecesPorDia) {
        this.vecesPorDia = vecesPorDia;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Metodos
    public abstract Accion clonarAccion();


    public abstract Registro accionar() throws AccionFallidaException;


    public String getNombre() {
        return nombre;
    }

    public String getTextoPregunta() {
        return textoPregunta;
    }

    public String mostrarAccion() {
        return "Accion : " + this.nombre + " , cada cuanto : " + this.cadaCuanto + " , veces por dia :" + this.vecesPorDia;
    }


    @Override
    public String toString() {
        return "Accion{" +
                "ultimaNoti=" + ultimaNoti +
                ", textoPregunta='" + textoPregunta + '\'' +
                ", cadaCuanto=" + cadaCuanto +
                ", vecesPorDia=" + vecesPorDia +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
