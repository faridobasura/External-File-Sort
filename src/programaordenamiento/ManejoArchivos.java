package programaordenamiento;
//Declaración de las clases a usar, vitales para el funcionamiento de los métodos
import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Ricardo Ruelas, Yoav Farid
 */

//La clase es usada para guardar la información dentro de un archivo
public class ManejoArchivos {
    
    //Se devuelve una lista de personas y se recibe como parámetro el nombre del archivo
    //Los comentarios quedan de más al no usar este método pero se mantiene para que se pueda observar la evolución del desarrollo
    public List<Persona> leerArchivo(String nombreArchivo) throws IOException {
        //------------------------------------------------------------------------------------------------------//
                                                            //Lee archivos y guarda objetos.
    	List<Persona> estudiantes = new LinkedList<>();

        StringBuilder input= new StringBuilder("");
        int ch; 
        FileReader fr = null; 
        try
        { 
            fr = new FileReader(nombreArchivo); 
        } 
        catch (FileNotFoundException fe) 
        { 
            System.out.println("El archivo no se encontro."); 
        } //El try/catch obligatorio para leer archivos
        while ((ch=fr.read())!=-1) 
            input.append((char)ch);

        String information = input.toString();
        String[] people = information.split("\r\n"); // SE MODIFICO EL FORMATO DEL ARCHIVO A SALTO DE LINEA POR PERSONA --FUNCIONA--
        

        for (int i=0; i<people.length; i++) {
            String[] person = people[i].split(","); 
            estudiantes.add(new Persona(person[0],person[1],Integer.parseInt(person[2])));
        }
        
        fr.close(); 
        
        return estudiantes;
    }
//La utilidad de este método radica en su utilidad para registrar las personas ordenadas en un archivo, recibe la lista, el nombre del archivo y un contador
    public void guardarInfo(List<Persona> estudiantes, String nombreArchivo, int contEscrituras) throws IOException {
        //------------------------------------------------------------------------------------------------------//
                                                            //Guarda la lista con los estudiantes 
        
        StringBuilder newInformationSB = new StringBuilder("");
        FileWriter fw = null;

        for(int i=0; i<estudiantes.size(); i++)
            newInformationSB.append(estudiantes.get(i).saveToString());//Guarda todo lo de la lista en el StringBuilder

        String newInformation = newInformationSB.toString();
        
        if(contEscrituras == 0)//El contador nos dice si se sobreescribirá sobre el archivo o se guardará la información anterior
            fw = new FileWriter(nombreArchivo, false);//Cuando es false se sobreescribe
        else
            fw = new FileWriter(nombreArchivo, true);//Aquí mantiene la información anteriormente escrita en un archvio

        for (int i = 0; i < newInformation.length(); i++) 
            fw.write(newInformation.charAt(i)); //Escribe todos los caracteres del StringBuilder 

        fw.close(); //Obligatoriamente se tiene que cerrar el archvio
    }

}
