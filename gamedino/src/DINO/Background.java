
package DINO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public abstract class Background {
    public BufferedImage imgbgr;
    public int x1, y1,x2,y2;
    
    public void Update(){
        x1-=Main.gamespeed;
        x2-=Main.gamespeed;
        if(x2<0) x1 = x2 + 780;
        if(x1<0) x2 = x1 + 780;
    }
    public void Paint(Graphics2D g2){
        g2.drawImage(imgbgr, x1, y1, null);
        g2.drawImage(imgbgr, x2, y2, null);
    }
    public int getY(){
        return y1;
    }
}
