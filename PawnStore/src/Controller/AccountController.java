package Controller;

import Model.Account;
import Support.*;
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

    private ArrayList<Account> _accountList = null;

    public ArrayList<Account> getAccountsList() {
        Connection conn = null;
        PreparedStatement prestate = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Account";
        try {
            conn = DBConnectionSupport.getConnection();
            prestate = conn.prepareStatement(query);
            rs = prestate.executeQuery();
            _accountList = new ArrayList<>();
            while (rs.next()) {
                try {
                    _accountList.add(new Account(Encoding.decrypt(rs.getString("_username")),
                             Encoding.decrypt(rs.getString("_password")), rs.getString("_fullname")));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return _accountList;
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

    public boolean addNewAccount(Account _account) {
        Connection conn = null;
        PreparedStatement prestate = null;
        String query = "Insert into Account(_username,_password,_fullname) values (?,?,?)";
        try {
            conn = DBConnectionSupport.getConnection();
            prestate = conn.prepareStatement(query);
            prestate.setString(1, Encoding.encrypt(_account.getUsername()));
            prestate.setString(2, Encoding.encrypt(_account.getPassword()));
            prestate.setString(3, _account.getFullname());
            prestate.executeUpdate();
            return true;
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
        return false;
    }

    public boolean removeAccount(Account account) {
        return Support.removeData("Account", "_username", Encoding.encrypt(account.getUsername()));
    }

    public boolean resetPassword(Account account ) {
        Connection conn = null;
        PreparedStatement prestate = null;
        String query = "Update Account set _password = ? Where _username = ?";
        try {
            conn = DBConnectionSupport.getConnection();
            prestate = conn.prepareStatement(query);
            prestate.setString(1, Encoding.encrypt("1"));
            prestate.setString(2, Encoding.encrypt(account.getUsername()));
            prestate.executeUpdate();
            return true;
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
        return false;
    }

    public Account findAccountByUsername(String username) {
        return findAccountByProperty("_username", username);
    }
    
    public Account findAccountByProperty(String property, String value) {
        Connection conn = null;
        PreparedStatement prestate = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Account where "+ property+" = ?";
        try {
            conn = DBConnectionSupport.getConnection();
            prestate = conn.prepareStatement(query);
            prestate.setString(1, Encoding.encrypt(value));
            rs = prestate.executeQuery();
            if (rs.next()) {
                try {
                    return new Account(Encoding.decrypt(rs.getString("_username")),
                             Encoding.decrypt(rs.getString("_password")), rs.getString("_fullname"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
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
