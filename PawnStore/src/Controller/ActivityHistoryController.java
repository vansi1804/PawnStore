/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Account;
import Model.ActivityHistory;
import Support.DBConnectionSupport;
import Support.Encoding;
import View.JLoginForm;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Support.*;

/**
 *
 * @author NVS
 */
public class ActivityHistoryController {
    
    private AccountController _accountController = new AccountController();
    
     private ArrayList<ActivityHistory> _activityHistorys = null;

    public ArrayList<ActivityHistory> getList() {
        Connection conn = null;
        PreparedStatement prestate = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Account";
        try {
            conn = DBConnectionSupport.getConnection();
            prestate = conn.prepareStatement(query);
            rs = prestate.executeQuery();
            _activityHistorys = new ArrayList<>();
            while (rs.next()) {
                try {
                    _activityHistorys.add(new ActivityHistory(rs.getString("_activeTime")
                            , new Account(_accountController.findByUsername(Encoding.decrypt(rs.getString("_username"))))
                            , rs.getString("_activity")));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return _activityHistorys;
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
