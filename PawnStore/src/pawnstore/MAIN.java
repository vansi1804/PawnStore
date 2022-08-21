/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pawnstore;

import View.JLoginJFrameForm;
import java.awt.EventQueue;

@SuppressWarnings("ClassWithoutLogger")
public class Main {

    public static void main(String[] args) {
         EventQueue.invokeLater(() -> {
             try {
                 JLoginJFrameForm jloginform = new JLoginJFrameForm();
                 jloginform.setVisible(true);
             } catch (Exception e) {
             }
         });
    } 
}
