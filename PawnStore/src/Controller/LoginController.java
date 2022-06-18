/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.User;
import Support.DBConnectionSupport;
import Support.Encoding;
import View.JLoginForm;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NVS
 */
public class LoginController {
    public boolean login(String username,String password){
        Connection conn = null;
        PreparedStatement prestate = null;
        ResultSet rs = null;
        String query = "SELECT _username, _password, _fullname FROM Account WHERE _username = ? AND _password = ?";
        try {
            conn = DBConnectionSupport.getConnection();
            prestate = conn.prepareStatement(query);
            prestate.setString(1, Encoding.encrypt(username));
            prestate.setString(2, Encoding.encrypt(password));
            rs = prestate.executeQuery();
            if (rs.next()) {
                try {
                    User.setCurrentInstance(Encoding.decrypt(rs.getString("_username"))
                            , Encoding.decrypt(rs.getString("_password")), rs.getString("_fullname"));
                    return true;
                } catch (Exception e) {e.printStackTrace();}
            }
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
        return false;
    }
}
