import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public abstract class Persistencia {
    // Metodos

    // lectura
    public static <T> ArrayList<T> deserializacion(String archivo){ // tener arreglos de clases dentro de la clase va ser un problema?
        ArrayList<T> rta = new ArrayList<T>();
        try{
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(archivo));
            // https://stackoverflow.com/questions/5554217/deserialize-a-listt-object-with-gson/5554296#5554296
            //rta = Arrays.asList(gson.fromJson(reader, T[].class));
            //rta.add((T)gson.fromJson(reader,Object.class));

            //Type userListType = new TypeToken<ArrayList<T>>(){}.getType(); // Due to type erasure, the TypeToken class is only able to capture types that are fully known at compile time. (That is, you can't do new TypeToken<List<T>>() {}.getType() for a type parameter T.)
            //rta = gson.fromJson(reader, userListType);

            reader.close();
        }catch(IOException e){
            System.out.println(e);
        }
        return rta;
    }

    // escritura
    public static <T> void serializacion(ArrayList<T> list, String archivo){
        try{
            // el LocalDate de los atributos Tratamiento en Paciente generan los warning en consola
            System.out.println(list.get(0).getClass());
            Writer writer = new FileWriter(archivo);
            new Gson().toJson(list, writer);
            writer.close();
        }catch(IOException e){
            System.out.println(e);
        }
    }

    /**
     // lectura
    public static void leerMedico(ArrayList<Medico> medicos){

    }

    public static void leerAdmins(ArrayList<Admin> admins){

    }

    public static void leerEnfermedades(ArrayList<Enfermedad> enfermedades){

    }

    public static void leerTratamientos(ArrayList<Tratamiento> tratamientos){

    }

    public static void leerAcciones(ArrayList<Accion> acciones){

    }

    // escritura
    public static void escribirMedico(ArrayList<Medico> medicos){

    }

    public static void escribirAdmins(ArrayList<Admin> admins){

    }

    public static void escribirEnfermedades(ArrayList<Enfermedad> enfermedades){

    }

    public static void escribirTratamientos(ArrayList<Tratamiento> tratamientos){

    }

    public static void escribirAcciones(ArrayList<Accion> acciones){

    }

     */
}
