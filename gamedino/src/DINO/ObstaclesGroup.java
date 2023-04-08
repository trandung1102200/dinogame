
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
    
    private static QueueList<Obstacles> xuongrongs;
    
    private BufferedImage[] xrimage = new BufferedImage[7];
    
    public static int khoangcach2vatcan = 500;
    
    public static int SIZE = 7;
    
    public static int posvatcanfirst = 780;
    
    Random generator = new Random();
        
    
    public Vector <Integer> rdimg ;  //random image   rdimg.get(i)
    
    public int ktanh[][] = new int[7][2];
        
    public int tmp;
    
    public ObstaclesGroup(){
        
        try {
            
            xrimage[0] = ImageIO.read(new File("images/xr.png")); // 64:53
            xrimage[1] = ImageIO.read(new File("images/xr1.png")); // 35:31
            xrimage[2] = ImageIO.read(new File("images/xr2.png")); // 18:35
            xrimage[3] = ImageIO.read(new File("images/xr3.png")); // 36:35
            xrimage[4] = ImageIO.read(new File("images/xr4.png")); // 35:35
            xrimage[5] = ImageIO.read(new File("images/xr5.png")); // 34:35
            xrimage[6] = ImageIO.read(new File("images/xr6.png")); // 18:36
        } catch (IOException ex) {}
        
        xuongrongs = new QueueList<Obstacles>();
        
        rdimg = new Vector <> ();
        
        
        ktanh[0][0] = 28;ktanh[0][1] = 36;ktanh[1][0]= 35;ktanh[1][1]=31;ktanh[2][0] = 18;ktanh[2][1] = 35;
        ktanh[3][0] = 36;ktanh[3][1] = 35;ktanh[4][0] = 35;ktanh[4][1]= 35;ktanh[5][0]= 34;ktanh[5][1] = 35;ktanh[6][0]=18;ktanh[6][1]=36;
        
        
        for(int i = 0 ;i<7 ;i++){
            tmp = generator.nextInt(7);
            
            rdimg.add(tmp);
            Obstacles xr;
            xr = new Obstacles(posvatcanfirst +i*khoangcach2vatcan ,400-ktanh[rdimg.get(i)][1],ktanh[rdimg.get(i)][0],ktanh[rdimg.get(i)][1]);
            xuongrongs.push(xr);
            
        }
        
        
        
        
            
        
    }
    
    
    public void update(){
        
        for( int i = 0 ; i < 7 ; i++ ){
            getxrs(i).update();
            
            
        }
        if(xuongrongs.get(0).getPosX() + ktanh[rdimg.get(0)][0] < 0  ){
            
            
            Obstacles xr; Obstacles temp = xuongrongs.pop();
            rdimg.remove(0);
            tmp = generator.nextInt(7);
            this.rdimg.add(tmp);
            
            
           
            xr = new Obstacles((int) (xuongrongs.get(5).getPosX() + khoangcach2vatcan),(int)(400-ktanh[rdimg.get(6)][1]),ktanh[rdimg.get(6)][0],ktanh[rdimg.get(6)][1]);
            
            xuongrongs.push(xr);
            xuongrongs.get(6).setisbehind(false);
            
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
