package DINO;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.FileReader;
import java.util.Scanner; // Import the Scanner class to read text files



public class ReadFile {
  public static void main(String[] args) {
      File fi = new File('images/maxp.txt');
            BufferedReader br = new BufferedReader(new FileReader(file));
 
        // Declaring a string variable
        String st;
        // Condition holds true till
        // there is character in a string
        while ((st = br.readLine()) != null){
            System.out.println(st);
            
        }
       
  }
}