package programaordenamiento;

import java.io.*;
import java.util.*;


/**
 *
 * @author Ricardo Ruelas, Yoav Farid
 */

public class Polifase {
    File aux1;
    File aux2;
    File aux3;
    File iteraciones;
    List<Persona> personasOrdenar;
    MergeSort merge;
    ManejoArchivos files;
    int tamBloque;
    boolean bandera;
    
    public Polifase () throws IOException{
        personasOrdenar = new LinkedList<>();
        merge = new MergeSort();
        files = new ManejoArchivos();
        tamBloque = 5;
        bandera = true;
        aux1 = new File("Aux1.txt");
        aux2 = new File("Aux2.txt");    
        aux3 = new File("Aux3.txt");
        iteraciones = new File("Iteraciones.txt");
        
        if (!aux1.exists()) {
            aux1.createNewFile();
        }
        if (!aux2.exists()) {
            aux2.createNewFile();
        }
        if (!aux3.exists()) {
            aux3.createNewFile();
        }
        if(!iteraciones.exists()) {
            iteraciones.createNewFile();
        }

    }
    
    public void fase1 (String nombreArchivo, int campoOrdenar) throws IOException{
        int ch; 
        FileReader fr = null;
        FileWriter fwaux1 = new FileWriter("Aux1.txt", false);
        FileWriter fwaux2 = new FileWriter("Aux2.txt", false);
        this.tamBloque = 5; // Cada que se utilice nuevamente el algoritmo el tamano del bloque sera inicializado en 5
        try
        { 
            fr = new FileReader(nombreArchivo); 
        } 
        catch (FileNotFoundException fe) 
        { 
            System.out.println("El archivo no se encontro."); 
        }
        
        int contEnter = 0;
        boolean band = true;
        StringBuilder input = new StringBuilder("");
        List<Persona> listTemp = new LinkedList<>();
        
        while ((ch=fr.read())!=-1){
            input.append((char)ch);
            
            if((char)ch == '\n'){
                contEnter++; 
            }
            if(contEnter == tamBloque){
                String information = input.toString();
                String[] people = information.split("\r\n");
                for (int i=0; i<people.length; i++) {
                    String[] person = people[i].split(",");
                    listTemp.add(new Persona(person[0], person[1], Integer.parseInt(person[2])));
                }
                merge.Sort(listTemp, 0, listTemp.size()-1, campoOrdenar);
                String infoGuardada = "";
                for(Persona persona : listTemp){
                    infoGuardada = infoGuardada + persona.saveToString();
                }
                if(band){
                    files.guardarInfo(listTemp, "Aux1.txt", 1);
                    files.guardarInfo(listTemp, "Iteraciones.txt", 1);
                    try (FileWriter escrituraIte = new FileWriter("Iteraciones.txt", true)) {
                        String newInformation = "----------FIN BLOQUE----------\n";
                        
                        for (int i = 0; i < newInformation.length(); i++)
                            escrituraIte.write(newInformation.charAt(i));
                    }
                    band = !band;
                }
                else{
                    files.guardarInfo(listTemp, "Aux2.txt", 1);
                    files.guardarInfo(listTemp, "Iteraciones.txt", 1);
                    try (FileWriter escrituraIte = new FileWriter("Iteraciones.txt", true)) {
                        String newInformation = "----------FIN BLOQUE----------\n";
                        
                        for (int i = 0; i < newInformation.length(); i++)
                            escrituraIte.write(newInformation.charAt(i));
                    }
                    band = !band;
                }
                input.delete(0, input.length());
                listTemp.clear();
                contEnter = 0;
            }

        }
        String information = input.toString();
        String[] people = information.split("\r\n");

        for (int i=0; i<people.length; i++) {
            String[] person = people[i].split(",");
            listTemp.add(new Persona(person[0],person[1],Integer.parseInt(person[2])));
        }
        merge.Sort(listTemp, 0, listTemp.size()-1, campoOrdenar);
        String infoGuardada = "";
        for(Persona persona : listTemp){
            infoGuardada = infoGuardada + persona.saveToString();
        }
        if(band){
            files.guardarInfo(listTemp, "Aux1.txt", 1);
            files.guardarInfo(listTemp, "Iteraciones.txt", 1);
            try (FileWriter escrituraIte = new FileWriter("Iteraciones.txt", true)) {
                String newInformation = "----------FIN BLOQUE----------\n";
                for (int i = 0; i < newInformation.length(); i++)
                    escrituraIte.write(newInformation.charAt(i));
            }
            band = !band;
        }
        else{
            files.guardarInfo(listTemp, "Aux2.txt", 1);
            files.guardarInfo(listTemp, "Iteraciones.txt", 1);
            try (FileWriter escrituraIte = new FileWriter("Iteraciones.txt", true)) {
                String newInformation = "----------FIN BLOQUE----------\n";
                for (int i = 0; i < newInformation.length(); i++)
                    escrituraIte.write(newInformation.charAt(i));
            }
            band = !band;
        }
        input.delete(0, input.length());
        listTemp.clear();
        
        FileWriter escrituraIte = new FileWriter("Iteraciones.txt", true);
        String newInformation = "*******************FIN FASE 1*******************\n";

        for (int i = 0; i < newInformation.length(); i++) 
            escrituraIte.write(newInformation.charAt(i));
        
        escrituraIte.close();
        
        this.fase2(nombreArchivo, campoOrdenar);
    }
    
    public void fase2(String nombreArchivo, int campoOrdenar) throws IOException{
        int iter = 0;
        int ch, contEnter = 0;
        FileReader fr = null;
        
        try
        { 
            fr = new FileReader(nombreArchivo); 
        } 
        catch (FileNotFoundException fe) 
        { 
            System.out.println("El archivo no se encontro."); 
        } 

        while ((ch=fr.read())!=-1){
            if((char)ch == '\n')
                contEnter++;
        }
        
        while(this.tamBloque <= contEnter){
            if(iter % 2 == 0){
                this.mezclaExterna("Aux1.txt", "Aux2.txt", nombreArchivo, "Aux3.txt", campoOrdenar);
                iter++;
            }
            else{
                this.mezclaExterna(nombreArchivo, "Aux3.txt", "Aux1.txt", "Aux2.txt", campoOrdenar);
                iter++;
            }
        }
        if(iter % 2 != 0){
            this.mezclaExterna("Aux1.txt", "Aux2.txt", nombreArchivo, "Aux3.txt", campoOrdenar);
        }
        
        System.out.println("Archivo ordenado, informacion disponible en: " + nombreArchivo);
        FileWriter escrituraIte = new FileWriter("Iteraciones.txt", true);
        String newInformation = "*******************FIN FASE 2*******************\n";

        for (int i = 0; i < newInformation.length(); i++) 
            escrituraIte.write(newInformation.charAt(i));
        
        escrituraIte.close(); 

    }
    
    public void mezclaExterna(String archivoPrinc1, String archivoPrinc2, String aux1, String aux2, int campoOrdenar) throws IOException{
        this.tamBloque = this.tamBloque * 2;
        FileWriter fwaux1 = new FileWriter(aux1, false);
        FileWriter fwaux2 = new FileWriter(aux2, false);
        FileReader lector1 = null;
        FileReader lector2 = null;
        BufferedReader br1 = null;
        BufferedReader br2 = null;
        String persona1, persona2;
        Persona personaComp1, personaComp2;
        int contPersonas = 0, contEscrituras1 = 0, contEscrituras2 = 0;
        

        try
        { 
            lector1 = new FileReader(archivoPrinc1);
            lector2 = new FileReader(archivoPrinc2);
            br1 = new BufferedReader(lector1);
            br2 = new BufferedReader(lector2);
        } 
        catch (FileNotFoundException fe) 
        { 
            System.out.println("Uno de los archivos no se encontro."); 
        }
        
        while ((persona1=br1.readLine())!=null | (persona2=br2.readLine())!=null){
            if(persona1 != null){
                String[] persona1aux = persona1.split(",");
                personaComp1 = new Persona(persona1aux[0],persona1aux[1],Integer.parseInt(persona1aux[2]));
                personasOrdenar.add(personaComp1);
                contPersonas++;
            }
            if(persona2 != null){
                String[] persona2aux = persona2.split(",");
                personaComp2 = new Persona(persona2aux[0],persona2aux[1],Integer.parseInt(persona2aux[2]));
                personasOrdenar.add(personaComp2);
                contPersonas++;
            }
            if(contPersonas == tamBloque){
                merge.Sort(personasOrdenar, 0, personasOrdenar.size()-1, campoOrdenar);
                if(this.bandera){
                    files.guardarInfo(personasOrdenar, aux1, contEscrituras1);
                    files.guardarInfo(personasOrdenar, "Iteraciones.txt", 1);
                    try (FileWriter escrituraIte = new FileWriter("Iteraciones.txt", true)) {
                        String newInformation = "----------FIN BLOQUE----------\n";
                        for (int i = 0; i < newInformation.length(); i++)
                            escrituraIte.write(newInformation.charAt(i));
                    }
                    personasOrdenar.clear();
                    this.bandera = !this.bandera;
                    contEscrituras1++;
                }
                else{
                    files.guardarInfo(personasOrdenar, aux2, contEscrituras2);
                    files.guardarInfo(personasOrdenar, "Iteraciones.txt", 1);
                    try (FileWriter escrituraIte = new FileWriter("Iteraciones.txt", true)) {
                        String newInformation = "----------FIN BLOQUE----------\n";
                        for (int i = 0; i < newInformation.length(); i++)
                            escrituraIte.write(newInformation.charAt(i));
                    }
                    personasOrdenar.clear();
                    this.bandera = !this.bandera;
                    contEscrituras2++;
                }
                contPersonas = 0;
            }
        }
        if(!personasOrdenar.isEmpty()){
            merge.Sort(personasOrdenar, 0, personasOrdenar.size()-1, campoOrdenar);
            if(this.bandera){
                files.guardarInfo(personasOrdenar, aux1, contEscrituras1);
                files.guardarInfo(personasOrdenar, "Iteraciones.txt", 1);
                try (FileWriter escrituraIte = new FileWriter("Iteraciones.txt", true)) {
                    String newInformation = "----------FIN BLOQUE----------\n";
                    for (int i = 0; i < newInformation.length(); i++)
                        escrituraIte.write(newInformation.charAt(i));
                }
                personasOrdenar.clear();
                this.bandera = !this.bandera;
            }
            else{
                files.guardarInfo(personasOrdenar, aux2, contEscrituras2);
                files.guardarInfo(personasOrdenar, "Iteraciones.txt", 1);
                try (FileWriter escrituraIte = new FileWriter("Iteraciones.txt", true)) {
                    String newInformation = "----------FIN BLOQUE----------\n";
                    for (int i = 0; i < newInformation.length(); i++)
                        escrituraIte.write(newInformation.charAt(i));
                }
                personasOrdenar.clear();
                this.bandera = !this.bandera;
            }
        }
    }
}