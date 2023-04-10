/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DINO;

/**
 *
 * @author user
 */
public class test {
    public static void main(String[] args){
        ReadFile rf = new ReadFile();
        System.out.println(rf.data);
        WriteFile wff = new WriteFile();
        wff.write("lan cute");
        rf = new ReadFile();
        System.out.println(rf.data);
    }
}
