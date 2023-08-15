/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Support;

import Common.Default;
import com.toedter.calendar.JDateChooser;
import java.awt.Image;
import java.math.BigDecimal;
import java.net.URL;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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

    public static Date stringToDate(String str, String dateFormat) {
        if (CheckSupport.isNullOrBlank(str)) {
            return null;
        }
        try {
            return new SimpleDateFormat(dateFormat).parse(str);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String dateToString(Date date, String dateFormat) {
        return date == null ? "" : new SimpleDateFormat(dateFormat).format(date);
    }

    public static String dateToString(LocalDateTime now, String dateFormat) {
        return now == null ? "" : now.format(DateTimeFormatter.ofPattern(dateFormat));
    }

    public static String LocalDateTimeToString(LocalDateTime dateTime) {
        return dateTime == null ? null : dateTime.format(DateTimeFormatter.ofPattern(Default.DATE_TIME_FORMAT));
    }

    public static Long subtractDate(String strDate1, String strDate2) {
        Date date1 = stringToDate(strDate1, Default.DATE_FORMAT);
        Date date2 = stringToDate(strDate2, Default.DATE_FORMAT);
        return subtractDate(date1, date2);
    }

    public static Long subtractDate(Date date1, String strDate2) {
        Date date2 = stringToDate(strDate2, Default.DATE_FORMAT);
        return subtractDate(date1, date2);
    }

    public static Long subtractDate(String strDate1, Date date2) {
        Date date1 = stringToDate(strDate1, Default.DATE_FORMAT);
        return subtractDate(date1, date2);
    }

    public static Long subtractDate(Date date1, Date date2) {
        return (date1 == null || date2 == null) ? null : TimeUnit.MILLISECONDS.toDays(date1.getTime() - date2.getTime());
    }

    public static String addDate(String strDate, int days) {
        Date date = Support.stringToDate(strDate, Default.DATE_FORMAT);
        return date == null ? "" : dateToString(addDate(date, days), Default.DATE_FORMAT);
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
                    jLabel.setText(dateToString(new Date(), Default.DATE_TIME_FORMAT));
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

    public static void setSlideImage(JLabel jLabel, List<URL> imagesList) {
        Thread t = new Thread() {
            @SuppressWarnings({"SleepWhileInLoop", "UseSpecificCatch", "BroadCatchBlock", "TooBroadCatch", "CallToPrintStackTrace"})
            public void run() {
                while (true) {
                    try {
                        for (int i = 0; i < imagesList.size(); i++) {
                            Thread.sleep(10000);
                            ScaleImage(jLabel, imagesList.get(i));
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
                            jDateChooser.setDate(stringToDate(dateToString(new Date(), Default.DATE_TIME_FORMAT), Default.DATE_TIME_FORMAT));
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

    public static String createNewId(String lastID) {
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

        int index = 0;
        if (strNum.length() % 3 == 1) {
            int firstValue = Integer.parseInt(String.valueOf(strNum.charAt(0)));
            String textValue = convertNumToText[firstValue];
            pronounce += textValue;
            index = 1;
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
            index = 2;
            pronounce += devidefor3[strNum.length() / 3];
        }

        for (; index < strNum.length(); index += 3) {
            String getpronounce = getPronounce(strNum.charAt(index), strNum.charAt(index + 1), strNum.charAt(index + 2));
            if (!getpronounce.isEmpty()) {
                pronounce += " " + getpronounce;
                pronounce += devidefor3[((strNum.length() - index) / 3) - 1];
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

    public static void setRowTableSelection(JTable jTable, int col, String value) {
        for (int row = 0; row < jTable.getRowCount(); row++) {
            if (jTable.getValueAt(row, col).toString().equals(value)) {
                jTable.setRowSelectionInterval(row, row);
            }
        }
    }

    public static Date getFirstDateInCurrentMonth() {
        return Date.from(
                LocalDate.now()
                        .withDayOfMonth(1)
                        .atStartOfDay(ZoneId.systemDefault())
                        .toInstant());
    }

    public static Date getLastDateInCurrentMonth() {
        return Date.from(
                LocalDate.now()
                        .withDayOfMonth(LocalDate.now().lengthOfMonth())
                        .atStartOfDay(ZoneId.systemDefault())
                        .toInstant());
    }

}
