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
@SuppressWarnings({"ClassWithoutLogger", "UtilityClassWithoutPrivateConstructor"})
public class CheckSupport {

    public static boolean isNullOrBlank(String str) {
        return str == null || str.isBlank();
    }

    public static boolean doesContainsWhiteSpace(String str) {
        return str.contains(" ");
    }

    public static boolean doesContainsNumber(String str) {
        for (int i = 0; i < 10; i++) {
            if (str.contains(String.valueOf(i))) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsSpecialCharacter(String str) {
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(str);
        return m.find();
    }

    public static boolean isValidName(String str) {
        // (hyphens are used for middle names)
        String regex = "^[\\p{L} '-]+$";
        // Compile the regular expression
        Pattern pattern = Pattern.compile(regex);
        // Check if the name matches the regular expression
        Matcher matcher = pattern.matcher(str);

        return matcher.matches();
    }

    public static boolean isValidFullname(String fullname) {
        return !CheckSupport.isNullOrBlank(fullname)
                && !CheckSupport.doesContainsNumber(fullname)
                && CheckSupport.isValidName(fullname);
    }

    public static boolean isValidUsername(String username) {
        return !CheckSupport.isNullOrBlank(username)
                && !CheckSupport.doesContainsWhiteSpace(username)
                && !CheckSupport.containsSpecialCharacter(username);
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

    public static boolean constains(String s1, String s2) {
        return s1.toLowerCase().contains(s2.toLowerCase());
    }
}
