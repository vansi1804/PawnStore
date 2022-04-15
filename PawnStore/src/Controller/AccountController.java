package Controller;

import Model.Account;
import Support.DBConnectionSupport;
import View.JLoginForm;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountController {

    private ArrayList<Account> _accountList = new ArrayList<Account>();
    
    public ArrayList<Account> getList(){
        Connection conn = null;
        PreparedStatement prestate = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Account";
        try {
            conn = DBConnectionSupport.getConnection();
            prestate = conn.prepareStatement(query);
            rs = prestate.executeQuery();
            while (rs.next()) {
                try {
                    _accountList.add(new Account(rs.getString("_username"), rs.getString("_password"), rs.getString("_fullname")));
                } catch (Exception e) {e.printStackTrace();}
            }
            return _accountList;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
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
