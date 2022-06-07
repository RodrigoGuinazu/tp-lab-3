public class Enfermedad {
    private String nombre;

    public Enfermedad(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Enfermedad{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
