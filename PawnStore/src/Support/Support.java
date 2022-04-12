/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Support;

import View.JTabbedPaneForm.JAccountPanelForm;
import java.awt.Button;
import java.awt.Component;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;

/**
 *
 * @author NVS
 */
public class Support {
    public static void ScaleImage(JLabel label,URL url){
        ImageIcon imageIcon = new ImageIcon(url);
        Image imageScale = imageIcon.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(imageScale));
    }
 
    
    public static void CloseJFrame(Component parent, String title, String content,JFrame jrame){
        if (MessageSupport.Confirm(parent, title, content) == 0) {
            jrame.dispose();
        }
    }
    
    public static void ShowHirePassword(JCheckBox checkBox,JPasswordField jpassword){
        if (checkBox.isSelected()) {
            jpassword.setEchoChar((char) 0); //password = JPasswordField
        } else {
            jpassword.setEchoChar('*');
        }
    }
    
//    public static void AddTabbedPanelForm(JTabbedPane jparent, Object jchild, String title){
//        if (jchild == null) {
//            jchild = new Object();
//            jparent.add(title, (Component) jchild);
//        }
//        jparent.setSelectedComponent((Component) jchild);
//    }
    
}
