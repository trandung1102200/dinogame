
package DINO;

import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author user
 */
public class WriteFile {
    public void write(String data){
        try {
        FileWriter myWriter = new FileWriter("images/maxp.txt");
        myWriter.write(data);
        myWriter.close();
        System.out.println("Successfully");
            } catch (IOException e) {
        System.out.println("An error occurred");
        e.printStackTrace();
    }
}
}