package programaordenamiento;

//Se importan todas las utilerias
import java.util.*;

/**
 *
 * @author Ricardo Ruelas, Yoav Farid
 */
//Clase encargada de llevar a cabo el ordenamiento interno de ls bloques
public class MergeSort {

    //Método que orden a respecto al nombre, recibe la lista, el índice menor, la mitad y el índice mayor
    void mergeNombre(List<Persona> auxLista, int l, int m, int r) { 
        //Tamaño de las listas izquierdas y derechas
        int sizeLeftList = m - l + 1; 
        int sizeRightList = r - m; 

        //Crea dos listas auxiliares
        List<Persona> L = new LinkedList<>();
        List<Persona> R = new LinkedList<>();

        //Agrega a las listas los elementos de las dos mitades
        for (int i=0; i<sizeLeftList; ++i){ 
            L.add(auxLista.get(l + i));
        }
        
        for (int j=0; j<sizeRightList; ++j){
            R.add(auxLista.get(m + 1 + j)); 
        }
  
        int i = 0, j = 0; 

        int k = l; 
        //Se comparará hasta que las variables contadoras i y j sean mayores que los índices
        while (i < sizeLeftList && j < sizeRightList) 
        {   //Compara lexicográficamente cuál es mayor o menor para ordenarlo en la lista final
            if (L.get(i).firstName.compareTo(R.get(j).firstName) < 0) //L <= R
            {
                auxLista.set(k, L.get(i));
                i++; 
            } 
            else
            {
                auxLista.set(k, R.get(j));
                j++; 
            } 
            k++; 
        }
        //Mientras el índice i sea menor a el tamaño de la lista izquierda se agregan los elementos en la lista final
        while (i < sizeLeftList) {
            auxLista.set(k, L.get(i));
            i++; 
            k++; 
        } 
        //Mientras el indice j sea menor a el tamaño de la lista derecha se agregan sus elementos a la lista final
        while (j < sizeRightList) { 
            auxLista.set(k, R.get(j));
            j++; 
            k++; 
        }
    }
//Método que orden a respecto al apellido, recibe la lista, el índice menor, la mitad y el índice mayor
    void mergeApellido(List<Persona> auxLista, int l, int m, int r){ 
        //Tamaño de las listas izquieras y derechas
        int sizeLeftList = m - l + 1; 
        int sizeRightList = r - m; 
  
        //Crea dos listas auxiliares
        List<Persona> L = new LinkedList<>();
        List<Persona> R = new LinkedList<>();
        
        //Agrega a las listas los elementos de las dos mitades
        for (int i=0; i<sizeLeftList; ++i){ 
            L.add(auxLista.get(l + i));
        }
        for (int j=0; j<sizeRightList; ++j){
            R.add(auxLista.get(m + 1 + j)); 
        }
        
        int i = 0, j = 0; 
  
        int k = l; 
        //Se comparará hasta que las variables contadoras i y j sean mayores que los índices
        while (i < sizeLeftList && j < sizeRightList) 
        { 
            //Compara lexicográficamente cuál es mayor o menor para ordenarlo en la lista final
            if (L.get(i).lastName.compareTo(R.get(j).lastName) < 0) //L <= R
            {
                auxLista.set(k, L.get(i));
                i++; 
            } 
            else
            {
                auxLista.set(k, R.get(j));
                j++; 
            } 
            k++; 
        }
        //Mientras el índice i sea menor a el tamaño de la lista izquierda se agregan los elementos en la lista final
        while (i < sizeLeftList) {
            auxLista.set(k, L.get(i));
            i++; 
            k++; 
        } 
        //Mientras el indice j sea menor a el tamaño de la lista derecha se agregan sus elementos a la lista final
        while (j < sizeRightList) { 
            auxLista.set(k, R.get(j));
            j++; 
            k++; 
        }
    }
    //Método que orden a respecto al numero de cuenta, recibe la lista, el índice menor, la mitad y el índice mayor
    void mergeNumCuenta(List<Persona> auxLista, int l, int m, int r){ 
         //Tamaño de las listas izquieras y derechas
        int sizeLeftList = m - l + 1; 
        int sizeRightList = r - m; 
  
        //Crea dos listas auxiliares
        List<Persona> L = new LinkedList<>();
        List<Persona> R = new LinkedList<>();
        
        //Agrega a las listas los elementos de las dos mitades
        for (int i=0; i<sizeLeftList; ++i){ 
            L.add(auxLista.get(l + i));
        }
        for (int j=0; j<sizeRightList; ++j){
            R.add(auxLista.get(m + 1 + j));
        }
        
        int i = 0, j = 0; 
  
        int k = l; 
        //Se comparará hasta que las variables contadoras i y j sean mayores que los índices
        while (i < sizeLeftList && j < sizeRightList) 
        { 
            //Compara cuál es mayor o menor para ordenarlo en la lista final
            if (L.get(i).numCuenta <= R.get(j).numCuenta) //L <= R
            {
                auxLista.set(k, L.get(i));
                i++; 
            } 
            else
            {
                auxLista.set(k, R.get(j)); 
                j++; 
            } 
            k++; 
        }
        //Mientras el índice i sea menor a el tamaño de la lista izquierda se agregan los elementos en la lista final
        while (i < sizeLeftList) {
            auxLista.set(k, L.get(i));
            i++; 
            k++; 
        } 
        //Mientras el indice j sea menor a el tamaño de la lista derecha se agregan sus elementos a la lista final
        while (j < sizeRightList) { 
            auxLista.set(k, R.get(j));
            j++; 
            k++; 
        }
    }

    //Esta función simplemente recurre a dividir la lista para que posteriormente se ordenene cuando ya esté en listas muy pequeños
    //Recibe la lista a ordenar, el índice menor, el índice mayor y el campo a ordenar que por un siwtch determina el método que usaremos
    void Sort(List<Persona> auxLista, int l, int r, int campoOrdenar){ 
        if (l < r) { 
            int m = (l + r)/2; 
  
            Sort(auxLista, l, m, campoOrdenar); 
            Sort(auxLista , m + 1, r, campoOrdenar); 
            
            // Ordena respecto al campo que le sea indicado
            switch (campoOrdenar) {
                case 1:
                    mergeNombre(auxLista, l, m, r);
                    break;
                case 2:
                    mergeApellido(auxLista, l, m, r);
                    break;
                case 3:
                    mergeNumCuenta(auxLista, l, m, r);
                    break;
            }
        } 
    }
    
}
