
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
        for(int i = 0 ;i<10 ;i++){  //// 3 khoang 0-> 260 ; 261 -> 521 ; 521 -> 780 
            tmp = generator.nextInt(5);
            
            rdimg.add(tmp);
            tmp = generator.nextInt(260) + i*260; // v2 tu 261 + 
            Cloud cl;
            cl = new Cloud( tmp , 30  ,ktimg[rdimg.get(i)][0],ktimg[rdimg.get(i)][1]);
            clouds.push(cl);
            
            
        }
    }
    public void update(){
        for(int i = 0 ;i<10;i++){
            getcl(i).update();
        }
        if(getcl(0).getPosX()<-100){
            Cloud cl;Cloud temp = clouds.pop();
            rdimg.remove(0);
            tmp = generator.nextInt(5);
            rdimg.add(tmp);
            tmp = generator.nextInt(260);
            vitri = generator.nextInt(20) + 15;
            cl = new Cloud((int)(tmp+ getcl(8).getPosX()) , vitri, ktimg[rdimg.get(9)][0],ktimg[rdimg.get(9)][1]);
            clouds.push(cl);
        }
    }
    public void paint(Graphics2D g2){
        for( int i = 0 ; i < 5 ; i++ ){
                g2.drawImage(imgmay[rdimg.get(i)], (int)getcl(i).getPosX(),(int)getcl(i).getPosY()  , null);   
        }
    }
    public Cloud getcl(int i){
        return clouds.get(i);
    }
    
}
