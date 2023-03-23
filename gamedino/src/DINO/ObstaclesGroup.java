
package DINO;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import pkg2dgamesframework.QueueList;
import java.util.Random;
import java.util.*;

public class ObstaclesGroup {
    
    private final QueueList<Obstacles> xuongrongs;
    
    
    
    private BufferedImage[] xrimage = new BufferedImage[7];
    
    private int khoangcach2vatcan = 500;
    
    public static int SIZE = 6;
    
    public int posvatcanfirst = 780;
    
    Random generator = new Random();
        
    
    public Vector <Integer> rdimg = new Vector <> ();  //random image   rdimg.get(i)
    
    
    public int ktanh[][] = new int[7][2];
        
    public int tmp;
    
    public ObstaclesGroup(){
        
        try {
            
            xrimage[0] = ImageIO.read(new File("images/xr.png")); // 64:53
            xrimage[1] = ImageIO.read(new File("images/xr1.png")); // 51:55
            xrimage[2] = ImageIO.read(new File("images/xr2.png")); // 32: 53
            xrimage[3] = ImageIO.read(new File("images/xr3.png")); // 51:49
            xrimage[4] = ImageIO.read(new File("images/xr4.png")); // 49:49
            xrimage[5] = ImageIO.read(new File("images/xr5.png")); // 34:35
            xrimage[6] = ImageIO.read(new File("images/xr6.png")); // 17:35
        } catch (IOException ex) {}
        
        xuongrongs = new QueueList<Obstacles>();
        
        
        ktanh[0][0] = 64;ktanh[0][1] = 53;ktanh[1][0]= 51;ktanh[1][1]=55;ktanh[2][0] = 32;ktanh[2][1] = 53;
        ktanh[3][0] = 51;ktanh[3][1] = 49;ktanh[4][0] = 49;ktanh[4][1]= 49;ktanh[5][0]= 34;ktanh[5][1] = 35;ktanh[6][0]=17;ktanh[6][1]=35;
        
        
        for(int i = 0 ;i<7 ;i++){
            tmp = generator.nextInt(7);
            
            this.rdimg.add(tmp);
            
            Obstacles xr;
            xr = new Obstacles(posvatcanfirst +i*khoangcach2vatcan ,400-ktanh[rdimg.get(i)][1],ktanh[rdimg.get(i)][0],ktanh[rdimg.get(i)][1]);
            xuongrongs.push(xr);
            
            
            
        }
        
        
        
            
        
    }
    
    
    public void update(){
        
        for( int i = 0 ; i < 7 ; i++ ){
            getxrs(i).update();
            
            
        }
        if(xuongrongs.get(0).getPosX()<-78){
            
            
            Obstacles xr; Obstacles temp = xuongrongs.pop();
            rdimg.remove(0);
            tmp = generator.nextInt(7);
            this.rdimg.add(tmp);
            
            
           
            xr = new Obstacles((int) (xuongrongs.get(5).getPosX() + khoangcach2vatcan),(int)(400-ktanh[rdimg.get(6)][1]),ktanh[rdimg.get(6)][0],ktanh[rdimg.get(6)][1]);
            
            xuongrongs.push(xr);
            // 0 1 2 3 4 5 6 
            //   3 5 1 3 2 4 0 
            //     5 1 3 2 4 0 
            //   1 2 3 4 5 6 
            
            
        }
        
        
    }
    
        
    public void paint(Graphics2D g2){
        for( int i = 0 ; i < 7 ; i++ ){
            
                g2.drawImage(xrimage[rdimg.get(i)], (int)getxrs(i).getPosX(),(int)getxrs(i).getPosY()  , null);
            
            
        }
    }
    
    public Obstacles getxrs(int i){
        return xuongrongs.get(i);
    }
    
    
            
}
