/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Support;

import View.JLoginForm;
import java.awt.Component;
import java.awt.Image;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

/**
 *
 * @author NVS
 */
public class Support {

    public static void ScaleImage(JLabel label, URL url) {
        ImageIcon imageIcon = new ImageIcon(url);
        Image imageScale = imageIcon.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(imageScale));
    }

    public static void ScaleImage(JButton button, URL url) {
        ImageIcon imageIcon = new ImageIcon(url);
        Image imageScale = imageIcon.getImage().getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(imageScale));
    }

    public static void CloseJFrame(Component parent, String title, String content, JFrame jrame) {
        if (MessageSupport.Confirm(parent, title, content) == 0) {
            jrame.dispose();
        }
    }

    public static void ShowHirePassword(JCheckBox checkBox, JPasswordField jpassword) {
        if (checkBox.isSelected()) {
            jpassword.setEchoChar((char) 0);
        } else {
            jpassword.setEchoChar('*');
        }
    }

    public static boolean removeData(String objectName, String propertie, String values) {
        Connection conn = null;
        PreparedStatement prestate = null;
        ResultSet rs = null;
        String query = "Delete from " + objectName + " Where " + propertie + " = ?";
        try {
            conn = DBConnectionSupport.getConnection();
            prestate = conn.prepareStatement(query);
            prestate.setString(1, values);
            prestate.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (prestate != null) {
                try {
                    prestate.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JLoginForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JLoginForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    public static void loadCombobox(ArrayList<String> arrayList, JComboBox jComboBox) {
        jComboBox.removeAllItems();
        for (int i = 0; i < arrayList.size(); i++) {
            jComboBox.addItem(arrayList.get(i).toString());
        }
    }

    public static Date stringToDate(String str) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        try {
            return formatter.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String dateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        return formatter.format(date);
    }

    public static void setDateTimeField(JFormattedTextField jfmtf, Date date) {
        jfmtf.setText(dateToString(date));
    }

    public static Date getDateTimeField(JFormattedTextField jfmtf) {
        return stringToDate(jfmtf.getText());
    }

    public static ResultSet getObject(String objectName, String propertie, String values) {
        Connection conn = null;
        PreparedStatement prestate = null;
        ResultSet rs = null;
        String query = "Select * from " + objectName + " Where " + propertie + " = ?";
        ArrayList<Object> list = new ArrayList<>();
        try {
            conn = DBConnectionSupport.getConnection();
            prestate = conn.prepareStatement(query);
            prestate.setString(1, values);
            rs = prestate.executeQuery();
            if (rs.next()) {
                return rs;
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (prestate != null) {
                try {
                    prestate.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JLoginForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JLoginForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }
}
