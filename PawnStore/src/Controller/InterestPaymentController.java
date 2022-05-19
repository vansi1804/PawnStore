

package Controller;

import Model.InterestPayment;
import Model.PawnCoupon;
import Support.DBConnectionSupport;
import View.JLoginForm;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class InterestPaymentController {
    public static ArrayList<InterestPayment> interestPayments = null;
    
    public ArrayList<InterestPayment> getList(PawnCoupon pawnCoupon){
        Connection conn = null;
        PreparedStatement prestate = null;
        ResultSet rs = null;
        String query = "SELECT * FROM InterestPayment WHERE _pawCouponID = ?";
        try {
            conn = DBConnectionSupport.getConnection();
            prestate = conn.prepareStatement(query);
            prestate.setString(1, pawnCoupon.getId());
            rs = prestate.executeQuery();
            interestPayments = new ArrayList<>();
            while (rs.next()) {
                try {
                    int times = rs.getInt(2);
                    Date paymentDate = rs.getDate(3);
                    float money = rs.getFloat(4);
                    float debt = rs.getFloat(5);
                    String note = rs.getString(6);
                    interestPayments.add(new InterestPayment(pawnCoupon, times, paymentDate, money, debt, note));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return interestPayments;
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