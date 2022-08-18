/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Support;


/**
 *
 * @author NVS
 */
@SuppressWarnings({"ClassWithoutLogger", "UtilityClassWithoutPrivateConstructor"})
public class CheckSupport {

    public static boolean isBlank(String str) {
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

    public static boolean doesContainsSpescialChar(String str) {
        for (int i = 0; i < str.length(); i++) {
            if ((('a' > str.indexOf(i) && str.indexOf(i) > 'z') || ('A' > str.indexOf(i) && str.indexOf(i) > 'Z')) 
                    && !doesContainsNumber(str)) {
                return true;
            }
        }
        return false;
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
    
    public static boolean constains(String s1, String s2){
        return s1.toLowerCase().contains(s2.toLowerCase());
    }
}
