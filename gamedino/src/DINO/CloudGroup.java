
package DINO;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;
import javax.imageio.ImageIO;
import pkg2dgamesframework.QueueList;
import pkg2dgamesframework.Objects;




public class CloudGroup {
    public class Cloud extends Objects {
    
    
    
    public Cloud(int x, int y, int w, int h) {
        super(x, y, w, h);
    }
    public void update(){
        this.setPosX(this.getPosX() - Main.gamespeed);
    }
}
    private static QueueList<Cloud> clouds ;
    private final BufferedImage[] imgmay;
    Random generator = new Random();
    public Vector <Integer> rdimg ;
    public int ktimg[][] ;
    public int tmp,vitri;
    public int n,ncloud;
    
    public CloudGroup() {
        vitri = 0;
        n=30;
        ncloud = 12;
        imgmay = new BufferedImage[ncloud]; 
        try{
            imgmay[0] = ImageIO.read(new File("images/cloud.png")); // 100:100
            imgmay[1] = ImageIO.read(new File("images/cloud1.png")); // 100:100
            imgmay[2] = ImageIO.read(new File("images/cloud2.png")); //100:100
            imgmay[3] = ImageIO.read(new File("images/cloud3.png")); // 100:100
            imgmay[4] = ImageIO.read(new File("images/cloud4.png")); //100:44  
            imgmay[5] = ImageIO.read(new File("images/cloud5.png")); //100:100
            imgmay[6] = ImageIO.read(new File("images/cloud6.png")); //120 58
            imgmay[7] = ImageIO.read(new File("images/cloud7.png")); //150 63
            imgmay[8] = ImageIO.read(new File("images/cloud8.png")); // 100 58 
            imgmay[9] = ImageIO.read(new File("images/cloud9.png")); //100 77 
            imgmay[10] = ImageIO.read(new File("images/cloud10.png")); // 100 77 
            imgmay[11] = ImageIO.read(new File("images/cloud11.png")); //100 65
            
        }catch (IOException ex) {}
        clouds = new QueueList<Cloud>();
        rdimg = new Vector <> ();
        
        ktimg = new int[ncloud][2];
        ktimg[0][0] = 100 ; ktimg[0][1] = 100 ; ktimg[1][0] = 100 ; ktimg[1][1] = 100 ; ktimg[2][0] = 100 ; ktimg[2][1] = 100 ; 
        ktimg[3][0] = 100 ; ktimg[3][1] = 100 ; ktimg[4][0] = 100 ; ktimg[4][1] = 44 ; 
        ktimg[5][0] = 100; ktimg[5][1] =100 ;ktimg[6][0] =120 ; ktimg[6][1] = 58;ktimg[7][0] = 150; ktimg[7][1] =63 ;
        ktimg[8][0] = 100; ktimg[8][1] = 58;ktimg[9][0] = 100; ktimg[9][1] = 77;
        ktimg[10][0] = 100; ktimg[10][1] = 77;ktimg[11][0] = 100; ktimg[11][1] = 65;
        
        for(int i = 0 ;i<n ;i++){  //// 3 khoang 0-> 260 ; 261 -> 521 ; 521 -> 780 
            tmp = generator.nextInt(ncloud);
            
            rdimg.add(tmp);
            
            Cloud cl;
            tmp =  generator.nextInt(30);
            if(i==0){
                cl = new Cloud( 0 , tmp  ,ktimg[rdimg.get(i)][0],ktimg[rdimg.get(i)][1]);
            }
            else{
                cl = new Cloud( (int) (getcl(i-1).getPosX() + 65), tmp  ,ktimg[rdimg.get(i)][0],ktimg[rdimg.get(i)][1]);
            }
            
            clouds.push(cl);
            
            
        }
    }
    public void update(){
        
        for(int i = 0 ;i<n;i++){
            getcl(i).update();
        }
        if(getcl(0).getPosX() + ktimg[rdimg.get(0)][0]  < 0 ){
            Cloud cl;Cloud temp = clouds.pop();
            rdimg.remove(0);
            tmp = generator.nextInt(ncloud);
            rdimg.add(tmp);
            
            vitri = generator.nextInt(30);
            cl = new Cloud((int)(getcl(n-2).getPosX() + 65) , vitri, ktimg[rdimg.get(n-1)][0],ktimg[rdimg.get(n-1)][1]);
            clouds.push(cl);
        }
    }
    public void paint(Graphics2D g2){
        for( int i = 0 ; i < n ; i++ ){
                g2.drawImage(imgmay[rdimg.get(i)], (int)getcl(i).getPosX(),(int)getcl(i).getPosY()  , null);   
        }
    }
    public static Cloud getcl(int i){
        return clouds.get(i);
    }
    
}
