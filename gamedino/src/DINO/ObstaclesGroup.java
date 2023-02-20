
package DINO;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import pkg2dgamesframework.QueueList;

/**
 *
 * @author user
 */
public class ObstaclesGroup {
    
    private QueueList<Obstacles> xuongrongs;
    
    private BufferedImage xrimage,xrimage2;
    
    private int khoangcach2vatcan = 500;
    
    public static int SIZE = 6;
    
    public int posvatcanfirst = 780;
    
    
    public ObstaclesGroup(){
        
        try {
            xrimage = ImageIO.read(new File("images/xr1.png"));
            xrimage2 = ImageIO.read(new File("images/xr2.png"));
            
        
        } catch (IOException ex) {}
        
        xuongrongs = new QueueList<Obstacles>();
        
        Obstacles xr;
        
        for( int i = 0 ; i < 6 ; i++ ){
            if(i%2==0){
                xr = new Obstacles(posvatcanfirst + i*khoangcach2vatcan,400-53,74,53);
            }
            else{
                xr = new Obstacles(posvatcanfirst + i*khoangcach2vatcan,400-55,51,55);
            }
            
            xuongrongs.push(xr);
        }
    }
    
    public void update(){
        for( int i = 0 ; i < 6 ; i++ ){
            xuongrongs.get(i).update();
        }
        
        if(xuongrongs.get(1).getPosX()<-78){
            
            Obstacles xr;
            xr = xuongrongs.pop();
            xr.setPosX(xuongrongs.get(4).getPosX() + khoangcach2vatcan);
            xuongrongs.push(xr);
            
            xr = xuongrongs.pop();
            xr.setPosX(xuongrongs.get(4).getPosX() + khoangcach2vatcan);
            xuongrongs.push(xr);
            
        }
        
        
    }
    
        
    public void paint(Graphics2D g2){
        for( int i = 0 ; i < 6 ; i++ ){
            if(i%2==0){
                g2.drawImage(xrimage, (int)xuongrongs.get(i).getPosX(),(int)xuongrongs.get(i).getPosY() , null);
            }
            else{
                g2.drawImage(xrimage2, (int)xuongrongs.get(i).getPosX(),(int)xuongrongs.get(i).getPosY() , null);
            }
            
        }
    }
    
    public Obstacles getobstacles(int i){
        return xuongrongs.get(i);
    }
            
}
