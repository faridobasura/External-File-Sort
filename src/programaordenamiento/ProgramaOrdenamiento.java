package programaordenamiento;

import java.io.IOException;

/**
 *
 * @author Ricardo Ruelas
 */

public class ProgramaOrdenamiento {

    public static void main(String[] args) throws IOException {
        Polifase pf = new Polifase();
        pf.fase1("info.txt", 1);
    }
    
}
