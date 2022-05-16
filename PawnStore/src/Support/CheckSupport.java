/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Support;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author NVS
 */
public class CheckSupport {

    public static boolean isEmpty(String str) {
        if (str == null) {
            str = "";
        }
        return (str.isBlank());
    }

    public static boolean containsWhiteSpace(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                return true;
            }
        }
        return false;
    }

    public static boolean containsSpescialChar(String str) {
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(str);
        return m.find();
    }

    public static boolean equals(String str1, String str2) {
        return str1.equals(str2);
    }

    public static boolean isValidPhonenumber(String str) {
        String reg = "(((\\+|)84)|0)(3|5|7|8|9)+([0-9]{8})\\b";
        return str.matches(reg);
    }

    public static boolean isNumber(String str) {
        String reg = "[0-9]+[\\.]?[0-9]*";
        return str.matches(reg);
    }

    public static boolean isValidCustomerID(String customerID) {
        if (customerID.length() == 9 || customerID.length() == 12) {
            return isNumber(customerID);
        }
        return false;
    }
}
