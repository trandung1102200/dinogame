
package DINO;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;
import javax.imageio.ImageIO;
import pkg2dgamesframework.Objects;
import pkg2dgamesframework.QueueList;


public class TreeGroup {
    public class Tree extends Objects{
    public Tree(int x,int y, int w,int h){
        super(x,y,w,h);
    }
    public void update(){
        this.setPosX(this.getPosX() - Dinosaur.gamespeed);
    }
}
    private static QueueList<Tree> trees ;
    private BufferedImage[] imgtree;
    Random generator = new Random();
    public Vector <Integer> rdimg ;
    public int ktimg[][] ;
    public int n,ntree,kc2vc,tmp;
    public TreeGroup (){
        
        n=7;
        ntree = 4;
        kc2vc = ObstaclesGroup.khoangcach2vatcan;
        imgtree = new BufferedImage[ntree]; 
        try{
            imgtree[0] = ImageIO.read(new File("images/tree.png"));
            imgtree[1] = ImageIO.read(new File("images/tree1.png"));
            imgtree[2] = ImageIO.read(new File("images/tree2.png"));
            imgtree[3] = ImageIO.read(new File("images/tree3.png"));     
        }catch (IOException ex) {}
        trees = new QueueList<Tree>();
        rdimg = new Vector <> ();
        ktimg = new int[ntree][2];
        ktimg[0][0] = 159; ktimg[0][1] =  125;          ktimg[1][0] = 100; ktimg[1][1] = 93 ;
        ktimg[2][0] = 100; ktimg[2][1] =  90;          ktimg[3][0] = 126; ktimg[3][1] = 117 ;
        for(int i = 0 ;i<n ;i++){  //// 3 khoang 0-> 260 ; 261 -> 521 ; 521 -> 780 
            tmp = generator.nextInt(ntree);            
            rdimg.add(tmp);       
            Tree tr;
            tr = new Tree( 260*i - 130 , 400 - ktimg[rdimg.get(i)][1]  ,ktimg[rdimg.get(i)][0],ktimg[rdimg.get(i)][1]);         
            trees.push(tr);
        }
        
    }
    public void update(){
        
        for(int i = 0 ;i<n;i++){
            gettr(i).update();
        }
        if(gettr(0).getPosX() + ktimg[rdimg.get(0)][0] < 0){
            Tree tr = trees.pop();
            rdimg.remove(0);
            tmp = generator.nextInt(ntree);
            rdimg.add(tmp);
            
            
            tr = new Tree((int)(gettr(n-2).getPosX() + 260 + ktimg[rdimg.get(n-2)][0]) , 400 - ktimg[rdimg.get(n-1)][1], ktimg[rdimg.get(n-1)][0],ktimg[rdimg.get(n-1)][1]);
            trees.push(tr);
        }
    }
    public void paint(Graphics2D g2){
        for( int i = 0 ; i < n ; i++ ){
                g2.drawImage(imgtree[rdimg.get(i)], (int)gettr(i).getPosX(),(int)gettr(i).getPosY()  , null);   
        }
    }
    public static Tree gettr(int i){
        return trees.get(i);
    }    
}
