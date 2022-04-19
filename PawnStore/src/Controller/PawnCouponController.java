/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Customer;
import Model.PawnCoupon;
import Model.Product;
import Model.TypeOfProduct;
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


public class PawnCouponController {
    
    ArrayList<PawnCoupon> pawnCoupons = null;
    
    ProductController _productController = new ProductController();
    CustomerController _customerController = new CustomerController();

    public ArrayList<Customer> getCustomerList() {
        return _customerController.getList();
    }

    public ArrayList<Product> getProductList() {
       return _productController.getProductList();
    }
    
    public ArrayList<PawnCoupon> getPawnCouponList(){
        Connection conn = null;
        PreparedStatement prestate = null;
        ResultSet rs = null;
        Customer customer = null;
        ResultSet rsProduct = null;
        ResultSet rsUser = null;
        String query = "SELECT * FROM PawnCoupon";
        try {
            conn = DBConnectionSupport.getConnection();
            prestate = conn.prepareStatement(query);
            rs = prestate.executeQuery();
            pawnCoupons = new ArrayList<>();
            while (rs.next()) {
                try {
                ResultSet rsCustomer = Support.getObject("Customer", "_id", rs.getString(3));
                customer = new Customer(rsCustomer.getString(1), rsCustomer.getString(2), rsCustomer.getString(3), rsCustomer.getString(4), rsCustomer.getString(5));
                rsProduct = Support.getObject("Product", "_id", rs.getString(4));
                TypeOfProduct typeOfProduct = _productController.findTypeOfProduct("_id", rs.getString(4));
                Product product = new Product(rsProduct.getString(1), rsProduct.getString(2), rsProduct.getString(3), rsProduct.getString(4), typeOfProduct);
                rsUser = Support.getObject("User", "_username", rs.getString(9));
                User user = new User(rsUser.getString(1),rsUser.getString(2),rsUser.getString(3));
                
                PawnCoupon pawnCoupon = new PawnCoupon(rs.getString(1), Support.stringToDate(rs.getString(2)), customer, product, Integer.parseInt(rs.getString(3)), Float.parseFloat(rs.getString(4)), Float.parseFloat(rs.getString(5)), Support.stringToDate(rs.getString(6)), user);
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
    
    
    
}
