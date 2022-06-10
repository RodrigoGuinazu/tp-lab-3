import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public abstract class Persistencia {
    // Metodos

    // lectura

    public static ArrayList<Paciente> deserializacionPacientes ()  {

        GsonBuilder gsonBilder = new GsonBuilder();
        gsonBilder.registerTypeAdapter(Accion.class, new AbstractAccionAdapter());
        Gson gson = gsonBilder.create();
        ArrayList<Paciente> listaPacientes = new ArrayList<>();

        File file = new File("pacientes.json");
        try {

            Type listType = new TypeToken<ArrayList<Paciente>>() {
            }.getType();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            listaPacientes = gson.fromJson(bufferedReader, listType);
            return listaPacientes;

        } catch (IOException e) {
            System.out.println("No se pudo leer/escribir el archivo: " + e.getMessage());
            e.printStackTrace();
            return null;
        }

    }


    // escritura
    public static <T> void serializacionPacientes(ArrayList<Paciente> arrayPaciente) {


        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Accion.class, new AbstractAccionAdapter());
        Gson gson = gsonBuilder.create();

        File file = new File("pacientes.json");
        try {

            Type listType = new TypeToken<ArrayList<Paciente>>() {
            }.getType();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            gson.toJson(arrayPaciente, listType, bufferedWriter);
            bufferedWriter.close();


        } catch (IOException e) {
            System.out.println("No se pudo leer/escribir el archivo: " + e.getMessage());
            e.printStackTrace();
        }

    }

    // lectura
    public static <T> ArrayList<T> deserializacion(String archivo, Class<T> type) {
        ArrayList<T> rta = new ArrayList<T>();
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(archivo));
            rta = gson.fromJson(reader, TypeToken.getParameterized(ArrayList.class, type).getType());
            reader.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        return rta;
    }

    // escritura
    public static <T> void serializacion(ArrayList<T> list, String archivo) {
        try {
            // el LocalDate de los atributos Tratamiento en Paciente generan los warning en consola
            Writer writer = new FileWriter(archivo);
            new Gson().toJson(list, writer);
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }


}