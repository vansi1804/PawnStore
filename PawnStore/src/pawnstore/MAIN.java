/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pawnstore;

import View.JLoginForm;
import java.awt.EventQueue;

@SuppressWarnings("ClassWithoutLogger")
public class Main {

    public static void main(String[] args) {
         EventQueue.invokeLater(() -> {
             try {
                 JLoginForm jloginform = new JLoginForm();
                 jloginform.setVisible(true);
             } catch (Exception e) {
             }
         });
    } 
}
