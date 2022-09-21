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
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTable;

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
    public static void ScaleImage(JButton button, URL url) {
        ImageIcon imageIcon = new ImageIcon(url);
        Image imageScale = imageIcon.getImage().getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_DEFAULT);
        button.setIcon(new ImageIcon(imageScale));
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

    @SuppressWarnings("UnusedAssignment")
    public static String getTextNumber(long num) {
        @SuppressWarnings("MismatchedReadAndWriteOfArray")
        String[] convertNumToText = {"KHÔNG", "MỘT", "HAI", "BA", "BỐN", "NĂM", "SÁU", "BẢY", "TÁM", "CHÍN"};
        @SuppressWarnings("MismatchedReadAndWriteOfArray")
        String[] devidefor3 = {"", " NGHÌN ", " TRIỆU ", " TỶ ", " TỶ TỶ ", " TỶ TỶ TỶ "};
        String strNum = String.valueOf(num);
        String pronounce = "";
//100,000,000
//10,000,000
//1,000,000

        int i = 0;
        if (strNum.length() % 3 == 1) {
            int firstValue = Integer.parseInt(String.valueOf(strNum.charAt(0)));
            String textValue = convertNumToText[firstValue];
            pronounce += textValue;
            i = 1;
            pronounce += devidefor3[strNum.length() / 3];
        } else if (strNum.length() % 3 == 2) {
            int firstValue = Integer.parseInt(String.valueOf(strNum.charAt(0)));
            int secondValue = Integer.parseInt(String.valueOf(strNum.charAt(1)));
            String textValue = "";
            if (firstValue == 1) {
                textValue = switch (secondValue) {
                    case 0 ->
                        " MƯỜI";
                    case 1 ->
                        " MƯỜI " + convertNumToText[secondValue];
                    default ->
                        convertNumToText[firstValue] + " MƯỜI " + convertNumToText[secondValue];
                };
            } else {
                textValue = switch (secondValue) {
                    case 0 ->
                        " MƯƠI";
                    case 1 ->
                        " MƯƠI " + convertNumToText[secondValue];
                    default ->
                        convertNumToText[firstValue] + " MƯƠI " + convertNumToText[secondValue];
                };
            }

            pronounce += textValue;
            i = 2;
            pronounce += devidefor3[strNum.length() / 3];
        }

        for (; i < strNum.length(); i += 3) {
            String getpronounce = getPronounce(strNum.charAt(i), strNum.charAt(i + 1), strNum.charAt(i + 2));
            if (!getpronounce.isEmpty()) {
                pronounce += " " + getpronounce;
                pronounce += devidefor3[((strNum.length() - i) / 3) -1];
            }
        }

        return pronounce;
    }

    @SuppressWarnings({"BroadCatchBlock", "TooBroadCatch", "UseSpecificCatch"})
    public static String getPronounce(char strHundreds, char strDozens, char strUnits) {
        @SuppressWarnings("MismatchedReadAndWriteOfArray")
        String[] convertNumToText = {"KHÔNG", "MỘT", "HAI", "BA", "BỐN", "NĂM", "SÁU", "BẢY", "TÁM", "CHÍN"};
        String pronounce = "";
        int hundreds;
        int dozens;
        int units;
        try {
            hundreds = Integer.parseInt(String.valueOf(strHundreds));
            dozens = Integer.parseInt(String.valueOf(strDozens));
            units = Integer.parseInt(String.valueOf(strUnits));
        } catch (Exception e) {
            return null;
        }
        if (hundreds > 0 || dozens > 0 || units > 0) {
            pronounce += convertNumToText[hundreds] + " TRĂM ";
            if (dozens == 0 && units == 0) {
                return pronounce;
            }
            switch (dozens) {
                case 0 ->
                    pronounce += " LẺ ";
                case 1 ->
                    pronounce += " MƯỜI ";
                default ->
                    pronounce += convertNumToText[dozens] + " MƯƠI ";
            }
            if (units > 0) {
                pronounce += convertNumToText[units];
            }
        }

        return pronounce;
    }

    public static void setRowTableSelection(JTable jTable, int col , String value){
        for (int row = 0; row < jTable.getRowCount(); row++) {
            if (jTable.getValueAt(row, col).toString().equals(value)) {
                jTable.setRowSelectionInterval(row, row);
            }
        }
    }
    
}
