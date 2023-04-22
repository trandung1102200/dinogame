
package DINO;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author user
 */
public class Sky {
    
    private BufferedImage groundImage;
    
    private int x1, y1,x2,y2;
    
    public Sky() {
        try {
            groundImage = ImageIO.read(new File("images/night.png"));
        }   catch (IOException ex){}
        
        x1 = 0;
        y1 = 0;
        x2 = x1 + 780;
        y2 = y1;
    }
    
    public void Update(){
        
        x1-=Dinosaur.gamespeed;
        x2-=Dinosaur.gamespeed;
        
        if(x2<0) x1 = x2 + 780;
        if(x1<0) x2 = x1 + 780;
    }
    
    public void Paint(Graphics2D g2){
        g2.drawImage(groundImage, x1, y1, null);
        g2.drawImage(groundImage, x2, y2, null);
    }
    
    public int getYSky(){
        return y1;
    }
    
    
    
    
}
