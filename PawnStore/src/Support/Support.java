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
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

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
        jComboBox.addItem("Tất cả");
        for (int i = 0; i < arrayList.size(); i++) {
            jComboBox.addItem(arrayList.get(i).toString());
        }
    }

    public static Date stringToDate(String str) {
        if (!CheckSupport.isEmpty(str)) {
            try {
                return new SimpleDateFormat("yyyy-MM-dd").parse(str);
            } catch (ParseException ex) {
                Logger.getLogger(Support.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public static String dateToString(Date date) {
        if ((date == null) || (date.compareTo(stringToDate("1900-01-01")) == 0)) {
            return "";
        }
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }
    
    public static String dateToStringData(Date date) {
        if ((date == null) || (date.compareTo(stringToDate("1900-01-01")) == 0)) {
            return "";
        }
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public static Date getToday() {
        return new Date();
    }

    public static String getStringToday() {
        return new SimpleDateFormat("yyyy-MM-dd").format(getToday());
    }

    public static void setDataTableCenter(JTable table) {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.CENTER);

        TableModel tableModel = table.getModel();

        for (int columnIndex = 0; columnIndex < tableModel.getColumnCount(); columnIndex++) {
            table.getColumnModel().getColumn(columnIndex).setCellRenderer(rightRenderer);
        }
    }

    public static String format(float value) {
        DecimalFormat df = new DecimalFormat("###,###,###.00");
        return df.format(value);
    }
    
    public static long subtractDate(Date date1, Date date2){
        return TimeUnit.MILLISECONDS.toDays(date2.getTime() - date1.getTime());
    }
    
    public static Date addDate(Date date, int days){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        return c.getTime();
    }
}
