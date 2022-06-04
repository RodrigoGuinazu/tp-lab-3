import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Persistencia { // deberiamos hacerlo generico
    // Metodos

    // lectura
    public static List<Paciente> leerPacientes(List<Paciente> pacientes){
        try{
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get("pacientes.json"));
            pacientes = Arrays.asList(gson.fromJson(reader, Paciente[].class));
            reader.close();
        }catch(IOException e){
            System.out.println(e);
        }
        return pacientes;
    }

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
    public static void escribirPacientes(ArrayList<Paciente> pacientes){ // no deberia ser lista de usuario?
        try{
            // el LocalDate de los atributos Tratamiento en Paciente generan los warning en consola
            Writer writer = new FileWriter("pacientes.json");
            new Gson().toJson(pacientes, writer);
            writer.close();
        }catch(IOException e){
            System.out.println(e);
        }
    }

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
}
