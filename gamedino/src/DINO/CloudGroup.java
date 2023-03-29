
package DINO;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;
import javax.imageio.ImageIO;
import pkg2dgamesframework.QueueList;


public class CloudGroup {
    private final QueueList<Cloud> clouds ;
    private final BufferedImage[] imgmay = new BufferedImage[5]; 
    Random generator = new Random();
    public Vector <Integer> rdimg ;
    public int ktimg[][] = new int[5][2];
    public int tmp,vitri;
    public int n;
    
    public CloudGroup() {
        try{
            imgmay[0] = ImageIO.read(new File("images/cloud.png")); // 100:100
            imgmay[1] = ImageIO.read(new File("images/cloud1.png")); // 100:100
            imgmay[2] = ImageIO.read(new File("images/cloud2.png")); //100:100
            imgmay[3] = ImageIO.read(new File("images/cloud3.png")); // 100:100
            imgmay[4] = ImageIO.read(new File("images/cloud4.png")); //100:44  
        }catch (IOException ex) {}
        clouds = new QueueList<Cloud>();
        rdimg = new Vector <> ();
        ktimg[0][0] = 100 ; ktimg[0][1] = 100 ; ktimg[1][0] = 100 ; ktimg[1][1] = 100 ; ktimg[2][0] = 100 ; ktimg[2][1] = 100 ; 
        ktimg[3][0] = 100 ; ktimg[3][1] = 100 ; ktimg[4][0] = 100 ; ktimg[4][1] = 44 ; 
        vitri = 0;
        n=30;
        for(int i = 0 ;i<n ;i++){  //// 3 khoang 0-> 260 ; 261 -> 521 ; 521 -> 780 
            tmp = generator.nextInt(5);
            
            rdimg.add(tmp);
            
            Cloud cl;
            tmp =  generator.nextInt(30) + 10;
            cl = new Cloud( i*78 , tmp  ,ktimg[rdimg.get(i)][0],ktimg[rdimg.get(i)][1]);
            clouds.push(cl);
            
            
        }
    }
    public void update(){
        
        for(int i = 0 ;i<n;i++){
            getcl(i).update();
        }
        if(getcl(0).getPosX()<-78){
            Cloud cl;Cloud temp = clouds.pop();
            rdimg.remove(0);
            tmp = generator.nextInt(5);
            rdimg.add(tmp);
            
            vitri = generator.nextInt(30) + 10;
            cl = new Cloud((int)(getcl(n-2).getPosX() + 70) , vitri, ktimg[rdimg.get(n-1)][0],ktimg[rdimg.get(n-1)][1]);
            clouds.push(cl);
        }
    }
    public void paint(Graphics2D g2){
        for( int i = 0 ; i < n ; i++ ){
                g2.drawImage(imgmay[rdimg.get(i)], (int)getcl(i).getPosX(),(int)getcl(i).getPosY()  , null);   
        }
    }
    public Cloud getcl(int i){
        return clouds.get(i);
    }
    
}
