/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Product;
import Model.TypeOfProduct;
import java.util.ArrayList;
import Support.*;
import View.JLoginForm;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductController {

    public ArrayList<TypeOfProduct> _typeOfProductsList = null;
    public ArrayList<Product> _productsList = null;

    public ArrayList<TypeOfProduct> getTypeOfProductList() {
        Connection conn = null;
        PreparedStatement prestate = null;
        ResultSet rs = null;
        String query = "SELECT * FROM TypeOfProduct";
        try {
            conn = DBConnectionSupport.getConnection();
            prestate = conn.prepareStatement(query);
            rs = prestate.executeQuery();
            _typeOfProductsList = new ArrayList<>();
            while (rs.next()) {
                try {
                    _typeOfProductsList.add(new TypeOfProduct(rs.getString(1), rs.getString(2)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return _typeOfProductsList;
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

    public ArrayList<Product> getProductList() {
        Connection conn = null;
        PreparedStatement prestate = null;
        ResultSet rs = null;
        String query = "SELECT Product._id,Product._name,Product._information,Product._status,TypeOfProduct._id,TypeOfProduct._name"
                + " FROM Product INNER JOIN TypeOfProduct ON Product._typeID = TypeOfProduct._id";
        try {
            conn = DBConnectionSupport.getConnection();
            prestate = conn.prepareStatement(query);
            rs = prestate.executeQuery();
            _productsList = new ArrayList<>();
            while (rs.next()) {
                try {
                    _productsList.add(new Product(rs.getString(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), new TypeOfProduct(rs.getString(5), rs.getString(6))));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return _productsList;
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

    public Product findProductByID(String value) {
        Connection conn = null;
        PreparedStatement prestate = null;
        ResultSet rs = null;
        String query = "SELECT Product._id,Product._name,Product._information,Product._status,TypeOfProduct._id,TypeOfProduct._name"
                + " FROM Product INNER JOIN TypeOfProduct ON Product._typeID = TypeOfProduct._id Where Product._id = ?";
        try {
            conn = DBConnectionSupport.getConnection();
            prestate = conn.prepareStatement(query);
            prestate.setString(1, value);
            rs = prestate.executeQuery();
            while (rs.next()) {
                try {
                    return new Product(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), new TypeOfProduct(rs.getString(5), rs.getString(6)));
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

    public TypeOfProduct findTypeOfProduct(String property, String value) {
        Connection conn = null;
        PreparedStatement prestate = null;
        ResultSet rs = null;
        String query = "SELECT * FROM TypeOfProduct WHERE " + property + " = ?";
        try {
            conn = DBConnectionSupport.getConnection();
            prestate = conn.prepareStatement(query);
            prestate.setString(1, value);
            rs = prestate.executeQuery();
            while (rs.next()) {
                try {
                    return new TypeOfProduct(rs.getString(1), rs.getString(2));
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

    public boolean addTypeOfProduct(TypeOfProduct _typeOfProduct) {
        Connection conn = null;
        PreparedStatement prestate = null;
        String query = "Insert into TypeOfProduct(_id,_name) values (?,?)";
        try {
            conn = DBConnectionSupport.getConnection();
            prestate = conn.prepareStatement(query);
            prestate.setString(1, _typeOfProduct.getTypeOfProductID());
            prestate.setString(2, _typeOfProduct.getTypeOfProductName());
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

    public boolean addProduct(Product _product) {
        Connection conn = null;
        PreparedStatement prestate = null;
        String query = "Insert into Product(_id,_name,_information,_status,_typeID) values (?,?,?,?,?)";
        try {
            conn = DBConnectionSupport.getConnection();
            prestate = conn.prepareStatement(query);
            prestate.setString(1, _product.getProductID());
            prestate.setString(2, _product.getProductName());
            prestate.setString(3, _product.getInformation());
            prestate.setString(4, null);
            prestate.setString(5, _product.getTypeOfProductID());
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

    public boolean editTypeOfProduct(TypeOfProduct _typeOfProduct, String id) {
        Connection conn = null;
        PreparedStatement prestate = null;
        String query = "Update TypeOfProduct set _id = ?, _name = ? Where _id = ?";
        try {
            conn = DBConnectionSupport.getConnection();
            prestate = conn.prepareStatement(query);
            prestate.setString(1, _typeOfProduct.getTypeOfProductID());
            prestate.setString(2, _typeOfProduct.getTypeOfProductName());
            prestate.setString(3, id);
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

    public boolean editProduct(Product _product) {
        Connection conn = null;
        PreparedStatement prestate = null;
        String query = "Update Product set _name = ?, _information = ?, _status = ?, _typeID = ? Where _id = ?";
        try {
            conn = DBConnectionSupport.getConnection();
            prestate = conn.prepareStatement(query);
            prestate.setString(1, _product.getProductName());
            prestate.setString(2, _product.getInformation());
            prestate.setString(3, _product.getStatus());
            prestate.setString(4, _product.getTypeOfProductID());
            prestate.setString(5, _product.getProductID());
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


    public ArrayList<Product> findProduct(Product product) {
        Connection conn = null;
        PreparedStatement prestate = null;
        ResultSet rs = null;

        String query2 = "";
        String queryId = " _id like '%" + product.getProductID() + "%'";
        String queryTypeID = " _typeID = '" + product.getTypeOfProductID() + "'";
        String queryName = " _name like N'%" + product.getProductName() + "%'";
        String queryInformation = " _information like N'%" + product.getInformation() + "%'";
        String queryStatus = " _status like N'%" + product.getStatus() + "%'"; 

        if (!CheckSupport.isEmpty(product.getProductID())) {
            query2 += " WHERE " + queryId;
            if (!CheckSupport.isEmpty(product.getProductName())) {
                query2 += " and " + queryName;
                if (!CheckSupport.isEmpty(product.getInformation())) {
                    query2 += " and " + queryInformation;
                    if (!CheckSupport.isEmpty(product.getStatus())) {
                        query2 += " and " + queryStatus;
                        if (!CheckSupport.isEmpty(product.getTypeOfProductID())) {
                            query2 += " and " + queryTypeID;
                        }
                    } else {
                        if (!CheckSupport.isEmpty(product.getTypeOfProductID())) {
                            query2 += " and " + queryTypeID;
                        }
                    }
                } else {
                    if (!CheckSupport.isEmpty(product.getStatus())) {
                        query2 += " and " + queryStatus;
                        if (!CheckSupport.isEmpty(product.getTypeOfProductID())) {
                            query2 += " and " + queryTypeID;
                        }
                    } else {
                        if (!CheckSupport.isEmpty(product.getTypeOfProductID())) {
                            query2 += " and " + queryTypeID;
                        }
                    }
                }
            } else {
                if (!CheckSupport.isEmpty(product.getInformation())) {
                    query2 += " and " + queryInformation;
                    if (!CheckSupport.isEmpty(product.getStatus())) {
                        query2 += " and " + queryStatus;
                        if (!CheckSupport.isEmpty(product.getTypeOfProductID())) {
                            query2 += " and " + queryTypeID;
                        }
                    } else {
                        if (!CheckSupport.isEmpty(product.getTypeOfProductID())) {
                            query2 += " and " + queryTypeID;
                        }
                    }
                } else {
                    if (!CheckSupport.isEmpty(product.getStatus())) {
                        query2 += " and " + queryStatus;
                        if (!CheckSupport.isEmpty(product.getTypeOfProductID())) {
                            query2 += " and " + queryTypeID;
                        }
                    } else {
                        if (!CheckSupport.isEmpty(product.getTypeOfProductID())) {
                            query2 += " and " + queryTypeID;
                        }
                    }
                }
            }
        } else {
            if (!CheckSupport.isEmpty(product.getProductName())) {
                query2 += " WHERE " + queryName;
                if (!CheckSupport.isEmpty(product.getInformation())) {
                    query2 += " and " + queryInformation;
                    if (!CheckSupport.isEmpty(product.getStatus())) {
                        query2 += " and " + queryStatus;
                        if (!CheckSupport.isEmpty(product.getTypeOfProductID())) {
                            query2 += " and " + queryTypeID;
                        }
                    } else {
                        if (!CheckSupport.isEmpty(product.getTypeOfProductID())) {
                            query2 += " and " + queryTypeID;
                        }
                    }
                } else {
                    if (!CheckSupport.isEmpty(product.getStatus())) {
                        query2 += " and " + queryStatus;
                        if (!CheckSupport.isEmpty(product.getTypeOfProductID())) {
                            query2 += " and " + queryTypeID;
                        }
                    } else {
                        if (!CheckSupport.isEmpty(product.getTypeOfProductID())) {
                            query2 += " and " + queryTypeID;
                        }
                    }
                }
            } else {
                if (!CheckSupport.isEmpty(product.getInformation())) {
                    query2 += " WHERE " + queryInformation;
                    if (!CheckSupport.isEmpty(product.getStatus())) {
                        query2 += " and " + queryStatus;
                        if (!CheckSupport.isEmpty(product.getTypeOfProductID())) {
                            query2 += " and " + queryTypeID;
                        }
                    } else {
                        if (!CheckSupport.isEmpty(product.getTypeOfProductID())) {
                            query2 += " and " + queryTypeID;
                        }
                    }
                } else {
                    if (!CheckSupport.isEmpty(product.getStatus())) {
                        query2 += " WHERE " + queryStatus;
                        if (!CheckSupport.isEmpty(product.getTypeOfProductID())) {
                            query2 += " and " + queryTypeID;
                        }
                    } else {
                        if (!CheckSupport.isEmpty(product.getTypeOfProductID())) {
                            query2 += " WHERE " + queryTypeID;
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
            _productsList = new ArrayList<>();
            while (rs.next()) {
                try {
                    _productsList.add(new Product(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), new TypeOfProduct(findTypeOfProduct("_id", rs.getString(5)))));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return _productsList;
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
