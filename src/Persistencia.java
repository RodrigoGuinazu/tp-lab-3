import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
    public static <T> ArrayList<T> deserializacion(String archivo, Class<T> type){ // tener arreglos de clases dentro de la clase va ser un problema?
        ArrayList<T> rta = new ArrayList<T>();
        try{
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(archivo));
            System.out.println(reader);
            rta = gson.fromJson(reader, TypeToken.getParameterized(ArrayList.class, type).getType()); //
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
