/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.impl;

import DAO.IProductDAO;
import Mapper.impl.ProductMapper;
import Model.Product;
import Model.TypeOfProduct;
import Support.CheckSupport;
import java.util.ArrayList;

/**
 *
 * @author NVS
 */
@SuppressWarnings("ClassWithoutLogger")
public class ProductDAO extends ADAO<Product> implements IProductDAO {

    private static final String SELECTQUERY = "Select Product._id"
            + ", TypeOfProduct._id, TypeOfProduct._name, TypeOfProduct._deleteflag"
            + ", Product._name, Product._information, Product._status"
            + " From Product Inner Join TypeOfProduct On Product._typeID = TypeOfProduct._id";
    private static final String INSERTQUERY = "Insert Into Product(_id, _typeID, _name, _information, _status) Values (?,?,?,?,?)";
    private static final String UPDATEQUERY = "Update Product Set _typeID = ?, _name = ?, _information = ?, _status = ? Where _id = ?";
    private static final String DELETEQUERY = "Delete from Product Where _id = ?"; 

    @Override
    public ArrayList<Product> getList() {
        return getList(SELECTQUERY, new ProductMapper());
    }

    @Override
    public Product getProduct(String id) {
        String query = SELECTQUERY + " Where Product._id = ?";
        return getObject(query, new ProductMapper(), id);
    }

    @Override
    public boolean insert(Product product) {
        return insert(INSERTQUERY, product.getId(), product.getTypeOfProduct().getId(), product.getName(), product.getInfo(), product.getStatus());
    }

    @Override
    public boolean update(Product product) {
        return update(UPDATEQUERY, product.getTypeOfProduct().getId(), product.getName(), product.getInfo(), product.getStatus(), product.getId());
    }

    @Override
    public boolean delete(Product product) {
        return delete(DELETEQUERY, product.getId());
    }

    @Override
    public ArrayList<Product> findProductByIDKey(String idKey) {
        String query = SELECTQUERY + " Where _id like N'%" + idKey + "%' ";
        return getList(query, new ProductMapper());
    }

    @Override
    public ArrayList<Product> findProductByTypeOfProductKey(TypeOfProduct typeOfProductKey) {
        String query = SELECTQUERY + " Where _typeID = N'" + typeOfProductKey.getId() + "' ";
        return getList(query, new ProductMapper());
    }

    @Override
    public ArrayList<Product> findProductByNameKey(String nameKey) {
        String query = SELECTQUERY + " Where _name like N'%" + nameKey + "%' ";
        return getList(query, new ProductMapper());
    }

    @Override
    public ArrayList<Product> findProductByInforKey(String inforKey) {
        String query = SELECTQUERY + " Where _information like N'%" + inforKey + "%' ";
        return getList(query, new ProductMapper());
    }

    @Override
    public ArrayList<Product> findProductByStatusKey(String statusKey) {
        String query = SELECTQUERY + " Where _status = N'" + statusKey + "' ";
        return getList(query, new ProductMapper());
    }

    @Override
    @SuppressWarnings("null")
    public ArrayList<Product> findProductByKey(String idKey, TypeOfProduct typeOfProductKey,
            String nameKey, String inforKey, String statusKey) {
        String query = SELECTQUERY;

        boolean isIDKeyEmpty = CheckSupport.isBlank(idKey);
        boolean isTypeOfProductEmpty = typeOfProductKey == null;
        boolean isNameKeyEmpty = CheckSupport.isBlank(nameKey);
        boolean isInforKeyEmpty = CheckSupport.isBlank(inforKey);
        boolean isStatusKeyEmpty = CheckSupport.isBlank(statusKey);

        if (!isIDKeyEmpty || !isTypeOfProductEmpty || !isNameKeyEmpty || !isInforKeyEmpty || !isStatusKeyEmpty) {
            query += " Where ";
        }
        if (!isIDKeyEmpty) {
            query += " Product._id like N'%" + idKey + "%' ";
        }
        if (!isIDKeyEmpty && (!isTypeOfProductEmpty || !isNameKeyEmpty || !isInforKeyEmpty || !isStatusKeyEmpty)) {
            query += " And ";
        }
        if (!isTypeOfProductEmpty) {
            query += " Product._typeID = N'" + typeOfProductKey.getId() + "'";
        }
        if (!isTypeOfProductEmpty && (!isNameKeyEmpty || !isInforKeyEmpty || !isStatusKeyEmpty)) {
            query += " And ";
        }
        if (!isNameKeyEmpty) {
            query += " Product._name like N'%" + nameKey + "%'";
        }
        if (!isNameKeyEmpty && (!isInforKeyEmpty || !isStatusKeyEmpty)) {
            query += " And ";
        }
        if (!isInforKeyEmpty) {
            query += " Product._information like N'%" + inforKey + "%'";
        }
        if (!isInforKeyEmpty && !isStatusKeyEmpty) {
            query += " And ";
        }
        if (!isStatusKeyEmpty) {
            query += " Product._status = N'" + statusKey + "'";
        }

        return getList(query, new ProductMapper());
    }

}
