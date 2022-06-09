import java.time.LocalDate;

public class Accion { // no puede ser abstract por el deserializador
    // Atributos
    private LocalDate ultimaNoti = null; // ultima vez que se le notifico la accion
    private String textoPregunta;
    private Integer cadaCuanto; // cada cuanto dias tiene que hacer la accion
    private Integer vecesPorDia; // cuantas veces por dia tiene que hacer la accion
    private String nombre;

    // Constructores
    public Accion(Integer cadaCuanto, Integer vecesPorDia, String nombre, String textoPregunta) {
        this.textoPregunta = textoPregunta;
        this.cadaCuanto = cadaCuanto;
        this.vecesPorDia = vecesPorDia;
        this.nombre = nombre;
    }

    // Constructores
    public Accion() {

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
    public Accion clonarAccion() {
        Accion aux = new Accion(this.cadaCuanto, this.vecesPorDia, this.nombre, this.textoPregunta);
        return aux;
    }

    public Registro accionar() throws AccionFallidaException {
        return null; // por default
    }//accionar debe devolver el registro ya creado

    public String getNombre() {
        return nombre;
    }

    public String getTextoPregunta() {
        return textoPregunta;
    }

    public String mostrarAccion(){
        return  "Accion : "+this.nombre+ " , cada cuanto : "+this.cadaCuanto+" , veces por dia :"+this.vecesPorDia;
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
