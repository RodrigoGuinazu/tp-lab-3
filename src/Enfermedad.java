public class Enfermedad {
    private String nombre;

    public Enfermedad(String nombre) {
        this.nombre = nombre;
    }


    public String mostrarEnfermedad(){
        return this.nombre;
    }

    @Override
    public String toString() {
        return "Enfermedad{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
