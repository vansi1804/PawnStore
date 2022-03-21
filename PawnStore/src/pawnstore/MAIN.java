/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pawnstore;

import View.jLoginForm;
import java.awt.EventQueue;

public class MAIN {

    public static void main(String[] args) {
         EventQueue.invokeLater(() -> {
             try {
                 jLoginForm jloginform = new jLoginForm();
                 jloginform.setVisible(true);
             } catch (Exception e) {
             }
         });
    }
    
}
