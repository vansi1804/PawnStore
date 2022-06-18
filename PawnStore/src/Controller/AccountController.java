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

    public ArrayList<Account> getList() {
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

    public boolean add(Account _account) {
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

    public boolean remove(String _username) {
        return Support.removeData("Account", "_username", Encoding.encrypt(_username));
    }

    public boolean resetPassword(String _username) {
        Connection conn = null;
        PreparedStatement prestate = null;
        String query = "Update Account set _password = ? Where _username = ?";
        try {
            conn = DBConnectionSupport.getConnection();
            prestate = conn.prepareStatement(query);
            prestate.setString(1, Encoding.encrypt("1"));
            prestate.setString(2, Encoding.encrypt(_username));
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

    public Account findByUsername(String id) {
        Connection conn = null;
        PreparedStatement prestate = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Account where _username = ?";
        try {
            conn = DBConnectionSupport.getConnection();
            prestate = conn.prepareStatement(query);
            prestate.setString(1, id);
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

    public boolean checkExistingAccount(String _username) {
        return findByUsername(_username) != null;
    }
}
