/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Account;
import Model.Customer;
import Model.PawnCoupon;
import Model.Product;
import Model.User;
import Support.DBConnectionSupport;
import View.JLoginForm;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Support.*;
import java.util.Date;

public class PawnCouponController {

    ArrayList<PawnCoupon> pawnCoupons = null;
    ProductController _productController = new ProductController();
    CustomerController _customerController = new CustomerController();
    AccountController _accountController = new AccountController();

    public ProductController getProductController() {
        return _productController;
    }

    public CustomerController getCustomerController() {
        return _customerController;
    }

    public AccountController getAccountController() {
        return _accountController;
    }

    public ArrayList<PawnCoupon> getPawnCouponList() {
        Connection conn = null;
        PreparedStatement prestate = null;
        ResultSet rs = null;
        String query = "SELECT * FROM PawnCoupon";
        try {
            conn = DBConnectionSupport.getConnection();
            prestate = conn.prepareStatement(query);
            rs = prestate.executeQuery();
            pawnCoupons = new ArrayList<>();
            while (rs.next()) {
                try {
                    String id = rs.getString(1);
                    Date pawnDate = Support.stringToDate(rs.getString(2));
                    Customer customer = _customerController.findByID(rs.getString(3));
                    Product product = _productController.findProductByID(rs.getString(4));
                    int amount = Integer.parseInt(rs.getString(5));
                    float price = Float.parseFloat(rs.getString(6));
                    float interestRate = Float.parseFloat(rs.getString(7));
                    Date ransomDate = Support.stringToDate(rs.getString(8));
                    Account acc = _accountController.findByUsername(rs.getString(9));
                    User user = new User(acc.getUsername(), acc.getPassword(), acc.getFullname());
                    PawnCoupon pawnCoupon = new PawnCoupon(id, pawnDate, customer, product, amount, price, interestRate, ransomDate, user);
                    pawnCoupons.add(pawnCoupon);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return pawnCoupons;
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

    public boolean add(PawnCoupon pawnCoupon) {
        Connection conn = null;
        PreparedStatement prestate = null;
        String query = "Insert into PawnCoupon(_id, _pawnDate, _customerID, _productID, _amount, _price, _interestRate, _redeemingDate, _username) values (?,?,?,?,?,?,?,?,?)";
        try {
            conn = DBConnectionSupport.getConnection();
            prestate = conn.prepareStatement(query);
            prestate.setString(1, pawnCoupon.getId());
            prestate.setString(2, Support.dateToString(pawnCoupon.getPawnDate()));
            prestate.setString(3, pawnCoupon.getCustomer().getId());
            prestate.setString(4, pawnCoupon.getProuct().getProductID());
            prestate.setString(5, String.valueOf(pawnCoupon.getAmount()));
            prestate.setString(6, String.valueOf(pawnCoupon.getPrice()));
            prestate.setString(7, Support.dateToString(pawnCoupon.getRedeemingDate()));
            prestate.setString(8, "null");
            prestate.setString(9, pawnCoupon.getUser().getUsername());
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

    public boolean edit(PawnCoupon pawnCoupon) {
        Connection conn = null;
        PreparedStatement prestate = null;
        String query = "Update PawnCoupon set _customerID = ?, _productID = ?, _amount = ?, _price = ?, _interestRate = ?, _pawnDate = ?, _ransomDate = ? where _id = ?";
        try {
            conn = DBConnectionSupport.getConnection();
            prestate = conn.prepareStatement(query);
            prestate.setString(1, pawnCoupon.getCustomer().getId());
            prestate.setString(2, pawnCoupon.getProuct().getProductID());
            prestate.setString(3, String.valueOf(pawnCoupon.getAmount()));
            prestate.setString(4, String.valueOf(pawnCoupon.getPrice()));
            prestate.setString(5, String.valueOf(pawnCoupon.getInterestRate()));
            prestate.setString(6, Support.dateToString(pawnCoupon.getPawnDate()));
            String ransomDate = Support.dateToString(pawnCoupon.getRedeemingDate());
            prestate.setString(8, CheckSupport.isEmpty(ransomDate) ? "null" : ransomDate);
            prestate.setString(8, pawnCoupon.getId());
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

    public ArrayList<PawnCoupon> findPawnCoupon(PawnCoupon pawnCoupon) {
        Connection conn = null;
        PreparedStatement prestate = null;
        ResultSet rs = null;

        String query2 = "";
        String queryId = " _id like '%" + pawnCoupon.getId() + "%'";
        String queryCustomerID = " _customerID like '%" + pawnCoupon.getCustomer().getId() + "%'";
        String queryProductID = " _productID like '%" + pawnCoupon.getProuct().getProductID() + "%'";
        String queryAmount = " _amount = " + pawnCoupon.getAmount() + "";
        String queryPrice = " _price like " + pawnCoupon.getPrice() + "";
        String queryInterestRate = " _interestRate like " + pawnCoupon.getInterestRate() + "";
        String queryPawnDate = " _id _pawnDate '%" + pawnCoupon.getPawnDate() + "%'";
        String queryRedeemingDate = " _redeemingDate like '%" + pawnCoupon.getRedeemingDate() + "%'";

        if (!CheckSupport.isEmpty(pawnCoupon.getId())) {
            if (!CheckSupport.isEmpty(pawnCoupon.getCustomer().getId())) {
                if (!CheckSupport.isEmpty(pawnCoupon.getId())) {
                    if (!CheckSupport.isEmpty(pawnCoupon.getId())) {
                        if (!CheckSupport.isEmpty(pawnCoupon.getId())) {
                            if (!CheckSupport.isEmpty(pawnCoupon.getId())) {
                                if (!CheckSupport.isEmpty(pawnCoupon.getId())) {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getId())) {

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        String query = " SELECT * FROM Product " + query2;

        try {
            conn = DBConnectionSupport.getConnection();
            prestate = conn.prepareStatement(query);
            rs = prestate.executeQuery();
            ArrayList<PawnCoupon> pawnCoupons = new ArrayList<>();
            while (rs.next()) {
                try {
                    pawnCoupons.add(new PawnCoupon());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return pawnCoupons;
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
