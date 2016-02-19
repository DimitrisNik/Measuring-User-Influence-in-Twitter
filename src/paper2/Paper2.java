
package paper2;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Paper2 {

    public static void main(String[] args) {
        try {
            InfluenceMeasure influenceMeasure = new InfluenceMeasure();
            influenceMeasure.run();
        } catch (IOException ex) {
            System.err.println("Crashing...");
            System.exit(-1);
            Logger.getLogger(Paper2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
