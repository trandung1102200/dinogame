package DINO;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Sky extends Background{  
    public Sky() {
        try {
            this.imgbgr = ImageIO.read(new File("images/night.png"));
        }   catch (IOException ex){}        
        x1 = 0;
        y1 = 0;
        x2 = x1 + 780;
        y2 = y1;
    }  
}
