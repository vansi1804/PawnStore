/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Account;
import Model.Customer;
import Model.InterestPayment;
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

    public static int interestPaymentCycle = 15;

    private ArrayList<PawnCoupon> pawnCoupons = null;
    private ProductController _productController = new ProductController();
    private CustomerController _customerController = new CustomerController();
    private AccountController _accountController = new AccountController();
    private InterestPaymentController _interestPaymentController = new InterestPaymentController();

    public ProductController getProductController() {
        return _productController;
    }

    public CustomerController getCustomerController() {
        return _customerController;
    }

    public AccountController getAccountController() {
        return _accountController;
    }

    public InterestPaymentController getInterestPaymentController() {
        return _interestPaymentController;
    }

    public ArrayList<PawnCoupon> getPawnCouponList() {
        Connection conn = null;
        PreparedStatement prestate = null;
        ResultSet rs = null;
        String query = "SELECT _id, _pawnDate, _customerID, _productID, _amount, _price, _interestRate, _redeemingDate, _status, _username FROM PawnCoupon";
        try {
            conn = DBConnectionSupport.getConnection();
            prestate = conn.prepareStatement(query);
            rs = prestate.executeQuery();
            pawnCoupons = new ArrayList<>();
            while (rs.next()) {
                try {
                    String id = rs.getString(1);
                    String pawnDate = rs.getString(2);
                    Customer customer = _customerController.findByID(rs.getString(3));
                    Product product = _productController.findProductByID(rs.getString(4));
                    int amount = Integer.parseInt(rs.getString(5));
                    float price = Float.parseFloat(rs.getString(6));
                    float interestRate = Float.parseFloat(rs.getString(7));
                    String redeemingDate = rs.getString(8);
                    String status = rs.getString(9);
                    Account acc = _accountController.findAccountByUsername(rs.getString(10));
                    User user = new User(acc.getUsername(), acc.getPassword(), acc.getFullname());
                    PawnCoupon pawnCoupon = new PawnCoupon(id, pawnDate, customer, product, amount, price, interestRate, redeemingDate, status, user);
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
        String query = "INSERT INTO PawnCoupon(_id, _pawnDate, _customerID, _productID, _amount, _price, _interestRate, _redeemingDate, _status, _username) VALUES (?,?,?,?,?,?,?,?,?,?)";

        try {
            conn = DBConnectionSupport.getConnection();
            prestate = conn.prepareStatement(query);
            prestate.setString(1, pawnCoupon.getId());
            prestate.setString(2, pawnCoupon.getPawnDate());
            prestate.setString(3, pawnCoupon.getCustomer().getId());
            prestate.setString(4, pawnCoupon.getProduct().getProductID());
            prestate.setString(5, String.valueOf(pawnCoupon.getAmount()));
            prestate.setString(6, String.valueOf(pawnCoupon.getPrice()));
            prestate.setString(7, String.valueOf(pawnCoupon.getInterestRate()));
            prestate.setString(8, null);
            prestate.setString(9, pawnCoupon.getStatus());
            prestate.setString(10, Encoding.encrypt(pawnCoupon.getUser().getUsername()));
            prestate.executeUpdate();
            pawnCoupon.getProduct().setStatus(pawnCoupon.getStatus());
            _productController.editProduct(pawnCoupon.getProduct());
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
        String query = "UPDATE PawnCoupon SET _pawnDate = ?, _customerID = ?, _productID = ?, _amount = ?, _price = ?, _interestRate = ?, _redeemingDate = ?, _status = ? WHERE _id = ?";

        try {
            conn = DBConnectionSupport.getConnection();
            prestate = conn.prepareStatement(query);
            prestate.setString(1, pawnCoupon.getPawnDate());
            prestate.setString(2, pawnCoupon.getCustomer().getId());
            prestate.setString(3, pawnCoupon.getProduct().getProductID());
            prestate.setString(4, String.valueOf(pawnCoupon.getAmount()));
            prestate.setString(5, String.valueOf(pawnCoupon.getPrice()));
            prestate.setString(6, String.valueOf(pawnCoupon.getInterestRate()));
            prestate.setString(7, pawnCoupon.getRedeemingDate());
            prestate.setString(8, pawnCoupon.getStatus());
            prestate.setString(9, pawnCoupon.getId());
            prestate.executeUpdate();
            pawnCoupon.getProduct().setStatus(pawnCoupon.getStatus());
            _productController.editProduct(pawnCoupon.getProduct());
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
        String queryCustomerID = " _customerID = '" + pawnCoupon.getCustomer().getId() + "'";
        String queryProductID = " _productID = '" + pawnCoupon.getProduct().getProductID() + "'";
        String queryAmount = " _amount = " + pawnCoupon.getAmount();
        String queryPrice = " _price = " + pawnCoupon.getPrice();
        String queryInterestRate = " _interestRate = " + pawnCoupon.getInterestRate();
        String queryPawnDate = " _pawnDate like '%" + pawnCoupon.getPawnDate() + "%'";
        String queryRedeemingDate = " _redeemingDate = '%" + pawnCoupon.getRedeemingDate() + "%'";
        String querystatus = " _status = N'" + pawnCoupon.getStatus() + "'";

        if (!CheckSupport.isEmpty(pawnCoupon.getId())) {
            query2 += " WHERE" + queryId;
            if (!CheckSupport.isEmpty(pawnCoupon.getCustomer().getId())) {
                query2 += " AND " + queryCustomerID;
                if (!CheckSupport.isEmpty(pawnCoupon.getProduct().getProductID())) {
                    query2 += " AND " + queryProductID;
                    if (pawnCoupon.getAmount() != -1) {
                        query2 += " AND " + queryAmount;
                        if (pawnCoupon.getPrice() != -1) {
                            query2 += " AND " + queryPrice;
                            if (pawnCoupon.getInterestRate() != -1) {
                                query2 += " AND " + queryInterestRate;
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            } else {
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            }
                        } else {
                            if (pawnCoupon.getInterestRate() != -1) {
                                query2 += " AND " + queryInterestRate;
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            } else {
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (pawnCoupon.getPrice() != -1) {
                            query2 += " AND " + queryPrice;
                            if (pawnCoupon.getInterestRate() != -1) {
                                query2 += " AND " + queryInterestRate;
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            } else {
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            }
                        } else {
                            if (pawnCoupon.getInterestRate() != -1) {
                                query2 += " AND " + queryInterestRate;
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            } else {
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    if (pawnCoupon.getAmount() != -1) {
                        query2 += " AND " + queryAmount;
                        if (pawnCoupon.getPrice() != -1) {
                            query2 += " AND " + queryPrice;
                            if (pawnCoupon.getInterestRate() != -1) {
                                query2 += " AND " + queryInterestRate;
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            } else {
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            }
                        } else {
                            if (pawnCoupon.getInterestRate() != -1) {
                                query2 += " AND " + queryInterestRate;
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            } else {
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (pawnCoupon.getPrice() != -1) {
                            query2 += " AND " + queryPrice;
                            if (pawnCoupon.getInterestRate() != -1) {
                                query2 += " AND " + queryInterestRate;
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            } else {
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            }
                        } else {
                            if (pawnCoupon.getInterestRate() != -1) {
                                query2 += " AND " + queryInterestRate;
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            } else {
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                if (!CheckSupport.isEmpty(pawnCoupon.getProduct().getProductID())) {
                    query2 += " AND " + queryProductID;
                    if (pawnCoupon.getAmount() != -1) {
                        query2 += " AND " + queryAmount;
                        if (pawnCoupon.getPrice() != -1) {
                            query2 += " AND " + queryPrice;
                            if (pawnCoupon.getInterestRate() != -1) {
                                query2 += " AND " + queryInterestRate;
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            } else {
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            }
                        } else {
                            if (pawnCoupon.getInterestRate() != -1) {
                                query2 += " AND " + queryInterestRate;
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            } else {
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (pawnCoupon.getPrice() != -1) {
                            query2 += " AND " + queryPrice;
                            if (pawnCoupon.getInterestRate() != -1) {
                                query2 += " AND " + queryInterestRate;
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            } else {
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            }
                        } else {
                            if (pawnCoupon.getInterestRate() != -1) {
                                query2 += " AND " + queryInterestRate;
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            } else {
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    if (pawnCoupon.getAmount() != -1) {
                        query2 += " AND " + queryAmount;
                        if (pawnCoupon.getPrice() != -1) {
                            query2 += " AND " + queryPrice;
                            if (pawnCoupon.getInterestRate() != -1) {
                                query2 += " AND " + queryInterestRate;
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            } else {
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            }
                        } else {
                            if (pawnCoupon.getInterestRate() != -1) {
                                query2 += " AND " + queryInterestRate;
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            } else {
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (pawnCoupon.getPrice() != -1) {
                            query2 += " AND " + queryPrice;
                            if (pawnCoupon.getInterestRate() != -1) {
                                query2 += " AND " + queryInterestRate;
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            } else {
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            }
                        } else {
                            if (pawnCoupon.getInterestRate() != -1) {
                                query2 += " AND " + queryInterestRate;
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            } else {
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {
            if (!CheckSupport.isEmpty(pawnCoupon.getCustomer().getId())) {
                query2 += " WHERE " + queryCustomerID;
                if (!CheckSupport.isEmpty(pawnCoupon.getProduct().getProductID())) {
                    query2 += " AND " + queryProductID;
                    if (pawnCoupon.getAmount() != -1) {
                        query2 += " AND " + queryAmount;
                        if (pawnCoupon.getPrice() != -1) {
                            query2 += " AND " + queryPrice;
                            if (pawnCoupon.getInterestRate() != -1) {
                                query2 += " AND " + queryInterestRate;
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            } else {
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            }
                        } else {
                            if (pawnCoupon.getInterestRate() != -1) {
                                query2 += " AND " + queryInterestRate;
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            } else {
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (pawnCoupon.getPrice() != -1) {
                            query2 += " AND " + queryPrice;
                            if (pawnCoupon.getInterestRate() != -1) {
                                query2 += " AND " + queryInterestRate;
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            } else {
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            }
                        } else {
                            if (pawnCoupon.getInterestRate() != -1) {
                                query2 += " AND " + queryInterestRate;
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            } else {
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    if (pawnCoupon.getAmount() != -1) {
                        query2 += " AND " + queryAmount;
                        if (pawnCoupon.getPrice() != -1) {
                            query2 += " AND " + queryPrice;
                            if (pawnCoupon.getInterestRate() != -1) {
                                query2 += " AND " + queryInterestRate;
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            } else {
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            }
                        } else {
                            if (pawnCoupon.getInterestRate() != -1) {
                                query2 += " AND " + queryInterestRate;
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            } else {
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (pawnCoupon.getPrice() != -1) {
                            query2 += " AND " + queryPrice;
                            if (pawnCoupon.getInterestRate() != -1) {
                                query2 += " AND " + queryInterestRate;
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            } else {
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            }
                        } else {
                            if (pawnCoupon.getInterestRate() != -1) {
                                query2 += " AND " + queryInterestRate;
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            } else {
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                if (!CheckSupport.isEmpty(pawnCoupon.getProduct().getProductID())) {
                    query2 += " WHERE " + queryProductID;
                    if (pawnCoupon.getAmount() != -1) {
                        query2 += " AND " + queryAmount;
                        if (pawnCoupon.getPrice() != -1) {
                            query2 += " AND " + queryPrice;
                            if (pawnCoupon.getInterestRate() != -1) {
                                query2 += " AND " + queryInterestRate;
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            } else {
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            }
                        } else {
                            if (pawnCoupon.getInterestRate() != -1) {
                                query2 += " AND " + queryInterestRate;
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            } else {
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (pawnCoupon.getPrice() != -1) {
                            query2 += " AND " + queryPrice;
                            if (pawnCoupon.getInterestRate() != -1) {
                                query2 += " AND " + queryInterestRate;
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            } else {
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            }
                        } else {
                            if (pawnCoupon.getInterestRate() != -1) {
                                query2 += " AND " + queryInterestRate;
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            } else {
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    if (pawnCoupon.getAmount() != -1) {
                        query2 += " WHERE " + queryAmount;
                        if (pawnCoupon.getPrice() != -1) {
                            query2 += " AND " + queryPrice;
                            if (pawnCoupon.getInterestRate() != -1) {
                                query2 += " AND " + queryInterestRate;
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            } else {
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            }
                        } else {
                            if (pawnCoupon.getInterestRate() != -1) {
                                query2 += " AND " + queryInterestRate;
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            } else {
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (pawnCoupon.getPrice() != -1) {
                            query2 += " WHERE " + queryPrice;
                            if (pawnCoupon.getInterestRate() != -1) {
                                query2 += " AND " + queryInterestRate;
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            } else {
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            }
                        } else {
                            if (pawnCoupon.getInterestRate() != -1) {
                                query2 += " WHERE " + queryInterestRate;
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " AND " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                }
                            } else {
                                if (!CheckSupport.isEmpty(pawnCoupon.getPawnDate())) {
                                    query2 += " WHERE " + queryPawnDate;
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " AND " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    }
                                } else {
                                    if (!CheckSupport.isEmpty(pawnCoupon.getRedeemingDate())) {
                                        query2 += " WHERE " + queryRedeemingDate;
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " AND " + querystatus;
                                        }
                                    } else {
                                        if (!CheckSupport.isEmpty(pawnCoupon.getStatus())) {
                                            query2 += " WHERE " + querystatus;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        String query = " SELECT * FROM PawnCoupon " + query2;

        try {
            conn = DBConnectionSupport.getConnection();
            prestate = conn.prepareStatement(query);
            rs = prestate.executeQuery();
            ArrayList<PawnCoupon> pawnCoupons = new ArrayList<>();
            while (rs.next()) {
                try {
                    String id = rs.getString(1);
                    String pawnDate = rs.getString(2);
                    Customer customer = _customerController.findByID(rs.getString(3));
                    Product product = _productController.findProductByID(rs.getString(4));
                    int amount = Integer.parseInt(rs.getString(5));
                    float price = Float.parseFloat(rs.getString(6));
                    float interestRate = Float.parseFloat(rs.getString(7));
                    String redeemingDate = rs.getString(8);
                    String status = rs.getString(9);
                    Account acc = _accountController.findAccountByUsername(rs.getString(10));
                    User user = new User(acc.getUsername(), acc.getPassword(), acc.getFullname());
                    pawnCoupons.add(new PawnCoupon(id, pawnDate, customer, product, amount, price, interestRate, redeemingDate, status, user));
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

    public PawnCoupon findPawnCouponByProperty(String property, String value) {
        Connection conn = null;
        PreparedStatement prestate = null;
        ResultSet rs = null;
        String query = "SELECT * FROM PawnCoupon WHERE " + property + " = ?";
        try {
            conn = DBConnectionSupport.getConnection();
            prestate = conn.prepareStatement(query);
            prestate.setString(1, value);
            rs = prestate.executeQuery();
            while (rs.next()) {
                try {
                    String id = rs.getString(1);
                    String pawnDate = rs.getString(2);
                    Customer customer = _customerController.findByID(rs.getString(3));
                    Product product = _productController.findProductByID(rs.getString(4));
                    int amount = Integer.parseInt(rs.getString(5));
                    float price = Float.parseFloat(rs.getString(6));
                    float interestRate = Float.parseFloat(rs.getString(7));
                    String redeemingDate = rs.getString(8);
                    String status = rs.getString(9);
                    Account acc = _accountController.findAccountByUsername(rs.getString(10));
                    User user = new User(acc.getUsername(), acc.getPassword(), acc.getFullname());
                    return new PawnCoupon(id, pawnDate, customer, product, amount, price, interestRate, redeemingDate, status, user);
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

    public String getTheNextInterestPaymentDate(PawnCoupon pawnCoupon) {
        ArrayList<InterestPayment> interestPayments = _interestPaymentController.getList(pawnCoupon);
        if (interestPayments.size() == 0) {
            return Support.addDate(pawnCoupon.getPawnDate(), 14);
        } else {
            return Support.addDate(interestPayments.get(interestPayments.size()-1).getPaymentDate(), interestPaymentCycle);
        }
    }

    public boolean checkForLate(PawnCoupon pawnCoupon) {
        String theNextInterestPaymentDate = getTheNextInterestPaymentDate(pawnCoupon);
        if ((new Date()).compareTo(Support.stringToDate(theNextInterestPaymentDate, Support.getDateFormat())) > 0) {
            return true;
        }
        return false;
    }

    public String CreateNewPawnCouponID() {
        pawnCoupons = getPawnCouponList();
        if (pawnCoupons.size() == 0) {
            return "HĐ00000001";
        }
        return Support.FormatStringID(pawnCoupons.get(pawnCoupons.size() - 1).getId());
    }
}
