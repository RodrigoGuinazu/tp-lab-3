import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

/**
 * escribir:
 File file = new File(“mi_archivo.json”);
 BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
 Persona persona = new Persona("Juan", "Gson");
 Gson gson = new Gson();
 gson.toJson(persona, Persona.class, bufferedWriter);

 - Leer desde archivo:
 File file = new File(“mi_archivo.json”);
 BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
 Gson gson = new Gson();
 Persona persona = gson.fromJson(bufferedReader, Persona.class);
 System.out.println(persona);
 */

public abstract class Persistencia { // deberiamos hacerlo generico
    // Metodos

    // lectura
    public static void leerPacientes(ArrayList<Paciente> pacientes){

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
