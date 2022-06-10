public class Registro {     //
    private String nombreAccion;
    private String rta;
    private Boolean seHizo;

    public Registro(String nombre, String rta, Boolean seHizo) {
        this.nombreAccion = nombre;
        this.rta = rta;
        this.seHizo = seHizo;
    }

    public Registro(String nombreAccion, Boolean seHizo) {
        this.nombreAccion = nombreAccion;
        this.seHizo = seHizo;
    }

    public String mostrarNombresRegistros() {
        return this.nombreAccion;
    }

    public void setearValores() {
        // setea los nuevos valores que ingresa el usuario
    }

    public String toStringParaHistorial() {

        String aux = "";
        aux += "La accion " + nombreAccion;
        if (this.seHizo){
            aux += " se realizo";
          if(rta!=null){
              aux += " y su resultado fue:"+ rta + ". \n";
          }else{
              aux += ". \n";
          }
        }else{
            aux += " no se realizo"+ ". \n";
        }



        return aux;


    }

    @Override
    public String toString() {
        return "Registro{" +
                "nombreAccion='" + nombreAccion + '\'' +
                ", rta='" + rta + '\'' +
                ", seHizo=" + seHizo +
                '}';
    }
}
