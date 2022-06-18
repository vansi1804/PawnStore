/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Support;

import View.JLoginForm;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.Label;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
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
    
    public static void ScaleImage(JPanel jpanel, URL url) {
        ImageIcon imageIcon = new ImageIcon(url);
        Image imageScale = imageIcon.getImage().getScaledInstance(jpanel.getWidth(), jpanel.getHeight(), Image.SCALE_SMOOTH);
        jpanel.add(new JLabel(new ImageIcon(imageScale)));
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

    public static String getDateFormat() {
        return "dd-MM-yyyy";
    }

    public static String getDateTimeFormat() {
        return "dd-MM-yyyy ~ HH:mm:ss";
    }

    public static Date stringToDate(String str, String dateFormat) {
        if (!CheckSupport.isEmpty(str)) {
            try {
                return new SimpleDateFormat(dateFormat).parse(str);
            } catch (ParseException ex) {
                Logger.getLogger(Support.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public static String dateToString(Date date, String dateFormat) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat(dateFormat).format(date);
    }

    public static void setDataTableCenter(JTable table) {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.CENTER);

        TableModel tableModel = table.getModel();

        for (int columnIndex = 0; columnIndex < tableModel.getColumnCount(); columnIndex++) {
            table.getColumnModel().getColumn(columnIndex).setCellRenderer(rightRenderer);
        }
    }

    public static long subtractDate(String strDate1, String strDate2) {
        Date d1 = stringToDate(strDate1, getDateFormat());
        Date d2 = stringToDate(strDate2, getDateFormat());
        return TimeUnit.MILLISECONDS.toDays(d2.getTime() - d1.getTime());
    }

    public static String addDate(String strDate, int days) {
        Calendar c = Calendar.getInstance();
        Date date = stringToDate(strDate, getDateFormat());
        c.setTime(date);
        c.add(Calendar.DATE, days);
        return dateToString(c.getTime(), getDateFormat());
    }

    public static void FormatTableHeader(JTable jTable) {
        JTableHeader jTableHeader = jTable.getTableHeader();
        jTableHeader.setBackground(Color.WHITE);
        jTableHeader.setForeground(Color.BLACK);
        jTableHeader.setFont(new Font("Times New Roman", Font.BOLD, 14));
    }

    public static String BigFloatToString(float f) {
        return new BigDecimal(String.format("%.0f", f)).stripTrailingZeros().toPlainString();
    }

    public static String FormatStringID(String lastID) {
        String pattern = "";
        for (int i = 0; i < lastID.length(); i++) {
            if (!('0' <= lastID.charAt(i) && lastID.charAt(i) <= '9')) {
                pattern += lastID.charAt(i);
            }
        }
        String id = pattern;
        int maxIDLength = 10;
        int patternLength = pattern.length();
        lastID = lastID.substring(patternLength, lastID.length());
        int lastNumericalOrder = Integer.parseInt(lastID);
        int newID = lastNumericalOrder + 1;
        int newIDLength = String.valueOf(newID).length();
        for (int i = 1; i <= maxIDLength - patternLength - newIDLength; i++) {
            id += "0";
        }
        id += String.valueOf(newID);
        return id;
    }

    public static void getClock(JLabel jLabel, boolean status) {
        if (status) {
            Thread t = new Thread() {
                public void run() {
                    while (true) {
                        jLabel.setText(dateToString(new Date(), getDateTimeFormat()));
                        try {
                            Thread.sleep(1);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            t.start();
        }
    }

    public static void getClock(JDateChooser jDateChooser, boolean status) {
        if (status) {
            Thread t = new Thread() {
                public void run() {
                    while (true) {
                        jDateChooser.setDate(stringToDate(dateToString(new Date(), getDateTimeFormat()), getDateTimeFormat()));
                        try {
                            Thread.sleep(1);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            t.start();
        }
    }
}
