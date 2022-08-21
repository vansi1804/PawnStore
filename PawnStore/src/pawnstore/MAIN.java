/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pawnstore;

import View.JLoginFrame;
import java.awt.EventQueue;

@SuppressWarnings("ClassWithoutLogger")
public class Main {

    public static void main(String[] args) {
         EventQueue.invokeLater(() -> {
             try {
                 JLoginFrame jloginform = new JLoginFrame();
                 jloginform.setVisible(true);
             } catch (Exception e) {
             }
         });
    } 
}
