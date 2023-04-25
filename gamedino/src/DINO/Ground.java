
package DINO;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Ground extends Background{

    public Ground() {
        try {
            this.imgbgr = ImageIO.read(new File("images/nen.png"));
        }   catch (IOException ex){}
        
        x1 = 0;
        y1 = 400;
        x2 = x1 + 780;
        y2 = y1;
    }
}
