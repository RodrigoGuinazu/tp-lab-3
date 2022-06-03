public class Registro {
    private String nombreAccion;
    private double rta;
    private Boolean seHizo;

    public Registro(String nombre,double rta, Boolean seHizo){
        this.nombreAccion  = nombre;
        this.rta = rta;
        this.seHizo = seHizo;
    }

    public Registro(String nombreAccion, Boolean seHizo) {
        this.nombreAccion = nombreAccion;
        this.seHizo = seHizo;
    }

    public String mostrarNombresRegistros(){
        return this.nombreAccion;
    }

    public void setearValores(){
        // setea los nuevos valores que ingresa el usuario
    }
}
