/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Support;

import com.toedter.calendar.JDateChooser;
import java.awt.Image;
import java.math.BigDecimal;
import java.net.URL;
import java.text.NumberFormat;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

/**
 *
 * @author NVS
 */
@SuppressWarnings({"UtilityClassWithoutPrivateConstructor", "ClassWithoutLogger"})
public class Support {

    public static void ScaleImage(JLabel label, URL url) {
        ImageIcon imageIcon = new ImageIcon(url);
        Image imageScale = imageIcon.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(imageScale));
    }

    public static void CloseJFrame(String title, String content, JFrame jrame) {
        if (MessageSupport.MessageConfirm(title, content) == 0) {
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

    public static String getDateFormat() {
        return "dd/MM/yyyy";
    }

    public static String getDateTimeFormat() {
        return "dd/MM/yyyy HH:mm:ss";
    }

    public static Date stringToDate(String str, String dateFormat) {
        if (!CheckSupport.isBlank(str)) {
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

    public static long subtractDate(String strDate1, String strDate2) {
        Date date1 = stringToDate(strDate1, getDateFormat());
        Date date2 = stringToDate(strDate2, getDateFormat());
        return subtractDate(date1, date2);
    }

    public static long subtractDate(Date date1, String strDate2) {
        Date date2 = stringToDate(strDate2, getDateFormat());
        return subtractDate(date1, date2);
    }

    public static long subtractDate(String strDate1, Date date2) {
        Date date1 = stringToDate(strDate1, getDateFormat());
        return subtractDate(date1, date2);
    }

    public static long subtractDate(Date date1, Date date2) {
        return TimeUnit.MILLISECONDS.toDays(date1.getTime() - date2.getTime());
    }

    public static String addDate(String strDate, int days) {
        Date date = Support.stringToDate(strDate, Support.getDateFormat());
        return dateToString(addDate(date, days), getDateFormat());
    }

    public static Date addDate(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        return c.getTime();
    }

    public static String BigFloatToString(float f) {
        return new BigDecimal(String.format("%.0f", f)).stripTrailingZeros().toPlainString();
    }

    @SuppressWarnings("AssignmentToMethodParameter")
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
        Thread t = new Thread() {
            @SuppressWarnings({"SleepWhileInLoop", "UseSpecificCatch", "BroadCatchBlock", "TooBroadCatch", "CallToPrintStackTrace"})
            public void run() {
                while (status) {
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

    public static void setSlideImage(JLabel jLabel, ArrayList<URL> imagesList) {
        Thread t = new Thread() {
            @SuppressWarnings({"SleepWhileInLoop", "UseSpecificCatch", "BroadCatchBlock", "TooBroadCatch", "CallToPrintStackTrace"})
            public void run() {
                while (true) {
                    try {
                        for (int i = 0; i < imagesList.size(); i++) {
                            ScaleImage(jLabel, imagesList.get(i));
                            Thread.sleep(10000);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();
    }

    public static void getClock(JDateChooser jDateChooser, boolean status) {

        Thread t = new Thread() {
            @Override
            @SuppressWarnings({"SleepWhileInLoop", "UseSpecificCatch", "BroadCatchBlock", "TooBroadCatch", "CallToPrintStackTrace"})
            public void run() {
                if (!status) {
                    jDateChooser.setDate(null);
                } else {
                    while (status) {
                        try {
                            jDateChooser.setDate(stringToDate(dateToString(new Date(), getDateTimeFormat()), getDateTimeFormat()));
                            Thread.sleep(1);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Support.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        };
        if (status) {
            t.start();
        }
    }

    public static String getNewID(String lastID) {
        String newID = "";
        String pattern = "";
        String strNumber = "";
        for (int i = 0; i < lastID.length(); i++) {
            if ('0' <= lastID.charAt(i) && lastID.charAt(i) <= '9') {
                strNumber += lastID.charAt(i);
            } else {
                pattern += lastID.charAt(i);
            }
        }
        newID += pattern;
        int number = Integer.parseInt(strNumber) + 1;
        for (int i = 0; i < 12 - pattern.length() - String.valueOf(number).length(); i++) {
            newID += '0';
        }
        newID += String.valueOf(number);
        return newID;
    }

    public static String getFormatNumber(long num) {
        return String.valueOf(NumberFormat.getInstance().format(num));
    }
}
