import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Sistema { // deberia ser static/abstract/final?
    Scanner scan = new Scanner(System.in);
    // Atributos
    protected ArrayList<Usuario> usuarios;
    protected ArrayList<Tratamiento> tratamientos;
    protected ArrayList<Accion> acciones;
    protected ArrayList<Enfermedad> enfermedades;
    protected LocalDate fechaDelDia;
    protected Usuario usuarioLogueado;

    // Constructores
    public Sistema(ArrayList<Usuario> usuarios, ArrayList<Tratamiento> tratamientos, ArrayList<Accion> acciones, ArrayList<Enfermedad> enfermedades, LocalDate fechaDelDia) {
        this.usuarios = usuarios;
        this.tratamientos = tratamientos;
        this.acciones = acciones;
        this.enfermedades = enfermedades;
        this.fechaDelDia = fechaDelDia;
        this.usuarioLogueado = null;
    }

    // Metodos
    public void menu() {
        int opcion;
        do{
            System.out.println("[1] Log In");

            System.out.println("Ingrese una opcion o 0 para salir: ");
            opcion = scan.nextInt();

            switch(opcion){
                case 1 : {
                    //login();
                    if(this.usuarioLogueado instanceof Paciente){
                        menuPaciente();
                    }else if(this.usuarioLogueado instanceof Medico){
                        menuMedico();
                    }else{
                        menuAdmin();
                    }
                }break;
            }
        }while(opcion != 0);
    }

    public void menuPaciente(){
        int opcion;
        do{
            System.out.println("[1] Ver notificaciones");
            System.out.println("[1] Log Out");

            System.out.println("Ingrese una opcion o 0 para salir: ");
            opcion = scan.nextInt();

            switch(opcion){
                case 1 : {
                    //notificarPacientes();
                }break;

                case 2 : {
                    //logout();
                }break;
            }
        }while(opcion != 0);
    }

    public void menuMedico(){
        int opcion;
        do{
            System.out.println("[1] Ver notificaciones");
            System.out.println("[1] Log Out");

            System.out.println("Ingrese una opcion o 0 para salir: ");
            opcion = scan.nextInt();

            switch(opcion){
                case 1 : {
                    //notificarMedicos();
                }break;

                case 2 : {
                    //logout();
                }break;
            }
        }while(opcion != 0);
    }

    public void login(String mail, String pass){

    }

    public void logout(){

    }

    public void notificarMedicos(){

    }

    public void notificarPacientes(){

    }
}

 do {
         System.out.println("[1] Buscar Peliculas");
         System.out.println("[2] Agregar Peliculas para alquilar");
         System.out.println("[3] Buscar Cliente");
         System.out.println("[4] Crear Cliente");
         System.out.println("[5] Generar Boleta");
         System.out.println("[6] Devolver Pelicula");
         System.out.println("[7] Ver alquileres vigentes");
         System.out.println("[8] Alquileres que se deben al dia de la fecha");
         System.out.println("[9] Ultimas peliculas alquiladas por el cliente");
         System.out.println("[10] Ordenar las peliculas por popularidad");
         System.out.println("[11] Ordenar las peliculas por popularidad y genero");
         System.out.println("[12] Ver detalle de todas las peliculas");

         System.out.println("Ingrese una opcion o 0 para salir: ");
         opcion = scan.nextInt();

         switch (opcion) {
         case 1 : {
         // verifica si la pelicula existe, si existe, hay copia?
         resultado = new ArrayList<Copia>();
        System.out.println("Buscar pelicula: ");
        scan.nextLine(); // como el fflush();
        String busqueda = scan.nextLine();
        resultado = Copia.buscarPelicula(peliculas, busqueda);
        System.out.println("Resultado de la busqueda: ");
        System.out.println(resultado.toString());
        }break;

        case 2 : {
        // se agregan las peliculas que el usuario se va llevar
        if(peliculasAAlquilar == null){
        peliculasAAlquilar = Copia.alquilarPeliculas(peliculas);
        }else{
        System.out.println("Ya alquilaste las peliculas, debes ir a generar la boleta para agregar mas peliculas");
        }
        }break;

        case 3 : {
        // verifica si el cliente ya esta en la base de datos
        System.out.println("Ingrese el dni del cliente: ");
        scan.nextLine(); // como el fflush();
        String dniABuscar = scan.nextLine();
        rtaCliente = Cliente.buscarCliente(dniABuscar, clientes);
        if(rtaCliente == null){  // si no existe crea un nuevo cliente
        System.out.println("El cliente que busca no existe");
        }
        }break;

        case 4 : {
        // crear un nuevo cliente
        System.out.println("Agregar un nuevo cliente");
        System.out.println("DNI: ");
        scan.nextLine(); // como el fflush();
        String nuevoDni = scan.nextLine();
        System.out.println("Nombre: ");
        String nuevoNombre = scan.nextLine();
        System.out.println("Telefono: ");
        String nuevoTelefono = scan.nextLine();
        System.out.println("Direccion: ");
        String nuevaDireccion = scan.nextLine();
        rtaCliente = new Cliente(nuevoDni, nuevoNombre, nuevoTelefono, nuevaDireccion);
        clientes.add(rtaCliente);
        }break;

        case 5 : {
        // generar boleta
        if(peliculasAAlquilar == null || rtaCliente == null){
        System.out.println("Debes ir al case 2 para agregar las peliculas y al case 3 o 4 (buscar o crear cliente)");
        }else{
        int dias;
        for(Copia a : peliculasAAlquilar){
        System.out.println("Ingrese la cantidad de dias que va alquilar " + a.getTitulo() + ":");
        dias = scan.nextInt();
        nuevoAlquiler = new Alquiler(rtaCliente, a, dias);
        rtaCliente.setAlquileres(nuevoAlquiler);
        rtaCliente.setAlquileresVigentes(nuevoAlquiler);
        alquileresUsuario.add(nuevoAlquiler); // guardo los alquileres para poder generar la boleta
        alquileres.add(nuevoAlquiler);
        }
        boleta = new Boleta(rtaCliente, alquileresUsuario);
        boletas.add(boleta);
        peliculasAAlquilar = null;
        }
        }break;

        case 6 : {
        // cliente devuelve las peliculas
        for(Alquiler a : rtaCliente.getAlquileresVigentes()){
        Devolucion devolucion = new Devolucion(a);
        devoluciones.add(devolucion);
        }
        rtaCliente.resetAlquileresVigentes();
        }break;

        case 7 : {
        // consultar alquileres vigentes
        for(Alquiler a : alquileres){
        if(a.getEstado() == true){
        System.out.println(a);
        }
        }
        }break;

        case 8 : {
        // consultar alquileres que se deben en la fecha de hoy
        for(Alquiler a : alquileres){
        if((DAYS.between(a.getFechaDevolucion(), LocalDate.now())) == 0 && a.getEstado() == true){
        System.out.println(a);
        }
        }
        }break;

        case 9 : {
        // arreglo de las ultimas peliculas alquiladas por el cliente (muestra los alquileres del cliente que se busco en el case 3 o 4)
        if(rtaCliente == null){
        System.out.println("Debes ir al case 3 o 4 (buscar o crear cliente)");
        }else{
        System.out.println(rtaCliente.getAlquileres());
        }
        }break;

        case 10 : {
        // consultar los titulos mas alquilados
        int flag;
        Copia nuevaCopia;
        for(Copia c : peliculas){
        flag = 0;
        for(Copia r : ordenadoPorPopularidad){
        if(c.getTitulo().equals(r.getTitulo())){
        flag = 1;
        r.aumentarVecesAlquilado(c.getVecesAlquilado());
        }
        }
        if(flag == 0){
        nuevaCopia = c;
        ordenadoPorPopularidad.add(nuevaCopia);
        }
        }

        // llamo al objecto collections y a su metodo sort
        Collections.sort(ordenadoPorPopularidad, new Comparator<Copia>() { // como 2do parametro creo una funcion comparativa
public int compare(Copia o1, Copia o2) {
        return Integer.valueOf(o2.getVecesAlquilado()).compareTo(o1.getVecesAlquilado()); // si quiero ordenar de forma ascendiente cambio el orden de 01 y 02
        }
        });
        System.out.println(ordenadoPorPopularidad);
        }break;

        case 11 : {
        // buscar titulo por genero y ordenarlos por popularidad
        do {
        System.out.println("[1] Accion");
        System.out.println("[2] Aventura");
        System.out.println("[3] Comedia");
        System.out.println("[4] Drama");
        System.out.println("[5] Horror");
        System.out.println("[6] Documental");

        System.out.println("Ingrese una opcion o 0 para salir: ");
        opcionGenero = scan.nextInt();

        switch (opcionGenero) {
        case 1 : {
        System.out.println("Accion\n");
        ordenadoPorGenero = Copia.ordenarPorGenero(ordenadoPorPopularidad, "Accion");
        System.out.println(ordenadoPorGenero);
        }break;

        case 2 : {
        System.out.println("Aventura\n");
        ordenadoPorGenero = Copia.ordenarPorGenero(ordenadoPorPopularidad, "Aventura");
        System.out.println(ordenadoPorGenero);
        }break;

        case 3 : {
        System.out.println("Comedia\n");
        ordenadoPorGenero = Copia.ordenarPorGenero(ordenadoPorPopularidad, "Comedia");
        System.out.println(ordenadoPorGenero);
        }break;

        case 4 : {
        System.out.println("Drama\n");
        ordenadoPorGenero = Copia.ordenarPorGenero(ordenadoPorPopularidad, "Drama");
        System.out.println(ordenadoPorGenero);
        }break;

        case 5 : {
        System.out.println("Horror\n");
        ordenadoPorGenero = Copia.ordenarPorGenero(ordenadoPorPopularidad, "Horror");
        System.out.println(ordenadoPorGenero);
        }break;

        case 6 : {
        System.out.println("Documental\n");
        ordenadoPorGenero = Copia.ordenarPorGenero(ordenadoPorPopularidad, "Documental");
        System.out.println(ordenadoPorGenero);
        }break;
        }

        }while (opcionGenero != 0);
        }break;

        case 12 : {
        // ver informacion detallada de cada titulo
        for (Copia p : peliculas){
        System.out.println(p.getInfo());
        }
        }break;
        }
        }while (opcion != 0);