/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Common;

/**
 *
 * @author NVS
 */
@SuppressWarnings({"UtilityClassWithoutPrivateConstructor", "ClassWithoutLogger", "ClassMayBeInterface"})
public class Default {

    public static final String DATE_FORMAT = "dd-MM-yyyy";

    public static final String DATE_TIME_FORMAT = DATE_FORMAT + " HH:mm:ss";

    public static final String MIN_TIME_OF_DATE = " 00:00:00";

    public static final String MAX_TIME_OF_DATE = " 23:59:59";

    public static final String DEFAULT_PASSWORD = "1";

    // Chu kỳ đóng lãi
    public static final int PAYMENT_CIRCLE = 15;

    @SuppressWarnings("PublicInnerClass")
    public static class Admin {

        public static final String USERNAME = "admin";
        
        public static final String PASSWORD = "admin";
        
        public static final String FULL_NAME = "Admin";
        
    }

}
