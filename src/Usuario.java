public class Usuario {

    //Atributos
    private String nombre;
    private String apellido;
    private String mail;
    private String password;
    private int dni;
    private static Integer ID=0;
    private Integer id;

    //Constructores

    public Usuario() {

    }

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

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public Integer getId() {
        return id;
    }
    public int getDni(){
        return dni;
    }

    public String toStringInfoNoSensible() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                '}';
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
