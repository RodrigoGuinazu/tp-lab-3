public class Usuario {

    //Atributos
    private String nombre;
    private String apellido;
    private String mail;
    private String password;
    private static Integer ID=0;
    private Integer id;

    //Constructores

    public Usuario(String nombre, String apellido, String mail, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.password = password;
        this.id = idAutoincremental();
    }

    //Metodos

    //id autoincremental
    private Integer idAutoincremental(){
        ID+=1;
        return ID;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                '}';
    }
}
