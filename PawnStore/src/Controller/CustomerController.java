/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Account;
import java.util.ArrayList;
import Model.Customer;
import Support.*;
import Support.DBConnectionSupport;
import View.JLoginForm;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerController {

    public static boolean remove(String id) {
        return Support.removeData("Customer", "_id", id);
    }

    public ArrayList<Customer> _customersList = null;

    public ArrayList<Customer> getList() {
        Connection conn = null;
        PreparedStatement prestate = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Customer";
        try {
            conn = DBConnectionSupport.getConnection();
            prestate = conn.prepareStatement(query);
            rs = prestate.executeQuery();
            _customersList = new ArrayList<>();
            while (rs.next()) {
                try {
                    _customersList.add(new Customer(rs.getString("_id"), rs.getString("_fullname"), rs.getString("_gender"), rs.getString("_phonenumber"), rs.getString("_address")));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return _customersList;
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

    public boolean checkExistedCustomer(String customerID) {
        return CheckSupport.checkExistObject("Customer", "_id", customerID);
    }

    public boolean add(Customer customer) {
        Connection conn = null;
        PreparedStatement prestate = null;
        String query = "Insert into Customer(_id,_fullname,_gender,_phonenumber,_address) values (?,?,?,?,?)";
        try {
            conn = DBConnectionSupport.getConnection();
            prestate = conn.prepareStatement(query);
            prestate.setString(1, customer.getId());
            prestate.setString(2, customer.getFullname());
            prestate.setString(3, customer.getGender());
            prestate.setString(4, customer.getPhonenumber());
            prestate.setString(5, customer.getAddress());
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

    public boolean edit(Customer customer) {
        Connection conn = null;
        PreparedStatement prestate = null;
        String query = "Update Customer set _fullname = ?, _gender = ?, _phonenumber = ?, _address = ? Where _id = ?";
        try {
            conn = DBConnectionSupport.getConnection();
            prestate = conn.prepareStatement(query);
            prestate.setString(1, customer.getFullname());
            prestate.setString(2, customer.getGender());
            prestate.setString(3, customer.getPhonenumber());
            prestate.setString(4, customer.getAddress());
            prestate.setString(5, customer.getId());
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

    public ArrayList<Customer> find(Customer customer) {
        Connection conn = null;
        PreparedStatement prestate = null;
        ResultSet rs = null;

        String query2 = "";
        String queryId = " _id like '%" + customer.getId() +"%'";
        String queryFullname = " _fullname like N'%" + customer.getFullname()+"%'";
        String queryGender = " _gender like N'%" + customer.getGender()+"%'";
        String queryPhonenumber = " _phonenumber like '%" + customer.getPhonenumber()+"%'";
        String queryAddress = " _address like N'%" + customer.getAddress()+"%'";
        if (!CheckSupport.isEmpty(customer.getId())) {
                query2 += " WHERE " + queryId;
            if (!CheckSupport.isEmpty(customer.getFullname())) {
                query2 += " and " + queryFullname;
                if (!CheckSupport.isEmpty(customer.getGender())) {
                    query2 += " and " + queryGender;
                    if (!CheckSupport.isEmpty(customer.getPhonenumber())) {
                        query2 += " and " + queryPhonenumber;
                        if (!CheckSupport.isEmpty(customer.getAddress())) {
                            query2 += " and " + queryAddress;
                        }
                    } else {
                        if (!CheckSupport.isEmpty(customer.getAddress())) {
                            query2 += " and " + queryAddress;
                        }
                    }
                } else {
                    if (!CheckSupport.isEmpty(customer.getPhonenumber())) {
                        query2 += " and " + queryPhonenumber;
                        if (!CheckSupport.isEmpty(customer.getAddress())) {
                            query2 += " and " + queryAddress;
                        }
                    } else {
                        if (!CheckSupport.isEmpty(customer.getAddress())) {
                            query2 += " and " + queryAddress;
                        }
                    }
                }
            }else{
                if (!CheckSupport.isEmpty(customer.getGender())) {
                    query2 += " and " + queryGender;
                    if (!CheckSupport.isEmpty(customer.getPhonenumber())) {
                        query2 += " and " + queryPhonenumber;
                        if (!CheckSupport.isEmpty(customer.getAddress())) {
                            query2 += " and " + queryAddress;
                        }
                    } else {
                        if (!CheckSupport.isEmpty(customer.getAddress())) {
                            query2 += " and " + queryAddress;
                        }
                    }
                } else {
                    if (!CheckSupport.isEmpty(customer.getPhonenumber())) {
                        query2 += " and " + queryPhonenumber;
                        if (!CheckSupport.isEmpty(customer.getAddress())) {
                            query2 += " and " + queryAddress;
                        }
                    } else {
                        if (!CheckSupport.isEmpty(customer.getAddress())) {
                            query2 += " and " + queryAddress;
                        }
                    }
                }
            }
        }else{
            if (!CheckSupport.isEmpty(customer.getFullname())) {
                query2 += " WHERE " + queryFullname;
                if (!CheckSupport.isEmpty(customer.getGender())) {
                    query2 += " and " + queryGender;
                    if (!CheckSupport.isEmpty(customer.getPhonenumber())) {
                        query2 += " and " + queryPhonenumber;
                        if (!CheckSupport.isEmpty(customer.getAddress())) {
                            query2 += " and " + queryAddress;
                        }
                    } else {
                        if (!CheckSupport.isEmpty(customer.getAddress())) {
                            query2 += " and " + queryAddress;
                        }
                    }
                } else {
                    if (!CheckSupport.isEmpty(customer.getPhonenumber())) {
                        query2 += " and " + queryPhonenumber;
                        if (!CheckSupport.isEmpty(customer.getAddress())) {
                            query2 += " and " + queryAddress;
                        }
                    } else {
                        if (!CheckSupport.isEmpty(customer.getAddress())) {
                            query2 += " and " + queryAddress;
                        }
                    }
                }
            }else{
                if (!CheckSupport.isEmpty(customer.getGender())) {
                    query2 += " WHERE " + queryGender;
                    if (!CheckSupport.isEmpty(customer.getPhonenumber())) {
                        query2 += " and " + queryPhonenumber;
                        if (!CheckSupport.isEmpty(customer.getAddress())) {
                            query2 += " and " + queryAddress;
                        }
                    } else {
                        if (!CheckSupport.isEmpty(customer.getAddress())) {
                            query2 += " and " + queryAddress;
                        }
                    }
                } else {
                    if (!CheckSupport.isEmpty(customer.getPhonenumber())) {
                        query2 += " WHERE " + queryPhonenumber;
                        if (!CheckSupport.isEmpty(customer.getAddress())) {
                            query2 += " and " + queryAddress;
                        }
                    } else {
                        if (!CheckSupport.isEmpty(customer.getAddress())) {
                            query2 += " WHERE " + queryAddress;
                        }
                    }
                }
            }
        }
        String query = " SELECT * FROM Customer " + query2;

        try {
            conn = DBConnectionSupport.getConnection();
            prestate = conn.prepareStatement(query);
            rs = prestate.executeQuery();
            _customersList = new ArrayList<>();
            while (rs.next()) {
                try {
                    _customersList.add(new Customer(rs.getString("_id"), rs.getString("_fullname"), rs.getString("_gender"), rs.getString("_phonenumber"), rs.getString("_address")));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return _customersList;
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
