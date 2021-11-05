package programaordenamiento;

/**
 *
 * @author Ricardo Ruelas, Yoav Farid
 */

public class Persona {
    protected String firstName;
    protected String lastName;
    protected int numCuenta;

    public Persona(String firstName, String lastName, int numCuenta){
        this.firstName=firstName;
        this.lastName=lastName;
        this.numCuenta=numCuenta;
    }
    
    public void setFirstName(String firstName){
        this.firstName=firstName;
    }

    public void setLastName(String lastName){
        this.lastName=lastName;
    }

    public void setNumCuenta(int numCuenta){
        this.numCuenta=numCuenta;
    }
    
    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public int getNumCuenta(){
        return this.numCuenta;
    }
    
    @Override
    public String toString(){
        return "Nombre: "+firstName+" Apellido: "+lastName+" NumCuenta: "+numCuenta;
    }
    
    public String saveToString(){
        return firstName+","+lastName+","+numCuenta+"\r\n";
    }
}
