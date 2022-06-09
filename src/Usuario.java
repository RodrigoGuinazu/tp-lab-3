public class Usuario {

    //Atributos
    private String nombre;
    private String apellido;
    private String dni;
    private String mail;
    private String password;
    private static Integer ID = 0;
    private Integer id;

    //Constructores


    // Vacio
    public Usuario() {
    }

    // Completo
    public Usuario(String nombre, String apellido, String dni, String mail, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.mail = mail;
        this.password = password;
        this.id = idAutoincremental();
    }

    //Metodos

    //id autoincremental
    private Integer idAutoincremental() {
        ID += 1;
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

    public String getDni() {
        return dni;
    }

    public String toStringInfoNoSensible() {
        return nombre + " " + apellido + " "+ dni + " , ";
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                '}';
    }
}
