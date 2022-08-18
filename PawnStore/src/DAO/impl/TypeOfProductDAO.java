/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.impl;

import DAO.ITypeOfProductDAO;
import Mapper.impl.TypeOfProductMapper;
import Model.TypeOfProduct;
import Support.CheckSupport;
import java.util.ArrayList;

/**
 *
 * @author NVS
 */
@SuppressWarnings("ClassWithoutLogger")
public class TypeOfProductDAO extends ADAO<TypeOfProduct> implements ITypeOfProductDAO {

    private static final String SELECTQUERY = "Select _id, _name, _deleteflag From TypeOfProduct";
    private static final String INSERTQUERY = "Insert Into TypeOfProduct(_id, _name, _deleteflag) Values(?,?,?)";
    private static final String UPDATEQUERY = "Update TypeOfProduct Set _name = ?, _deleteflag = ? Where _id = ?";
    private static final String DELETEQUERY = "Delete from TypeOfProduct Where _id = ?"; 
    
    @Override
    public ArrayList<TypeOfProduct> getList() {
        return getList(SELECTQUERY, new TypeOfProductMapper());
    }

    @Override
    public TypeOfProduct getTypeOfProductByID(String id) {
        String query = SELECTQUERY + " Where _id = ?";
        return getObject(query, new TypeOfProductMapper(), id);
    }

    @Override
    public TypeOfProduct getTypeOfProductByName(String name) {
        String query = SELECTQUERY + " Where _name = ?";
        return getObject(query, new TypeOfProductMapper(), name);
    }

    @Override
    public boolean insert(TypeOfProduct typeOfProduct) {
        return insert(INSERTQUERY, typeOfProduct.getId(), typeOfProduct.getName(), typeOfProduct.getDeleteflag());
    }

    @Override
    public boolean update(TypeOfProduct typeOfProduct) {
        return insert(UPDATEQUERY, typeOfProduct.getName(), typeOfProduct.getDeleteflag(), typeOfProduct.getId());
    }

    @Override
    public boolean delete(TypeOfProduct typeOfProduct) {
        return delete(DELETEQUERY, typeOfProduct.getId());
    }

    @Override
    public ArrayList<TypeOfProduct> findTypeOfProductByIDKey(String idKey) {
        String query = SELECTQUERY + " Where _id like N'%" + idKey + "%' ";
        return getList(query, new TypeOfProductMapper());
    }

    @Override
    public ArrayList<TypeOfProduct> findTypeOfProductByNameKey(String nameKey) {
        String query = SELECTQUERY + " Where _name like N'%" + nameKey + "%' ";
        return getList(query, new TypeOfProductMapper());
    }

    @Override
    public ArrayList<TypeOfProduct> findTypeOfProductByDeleteflagKey(String deleteflagKey) {
        String query = SELECTQUERY + " Where _deleteflag like N'%" + deleteflagKey + "%' ";
        return getList(query, new TypeOfProductMapper());
    }

    @Override
    public ArrayList<TypeOfProduct> findTypeOfProductByKey(String idKey, String nameKey, String deleteflagKey) {
        String query = SELECTQUERY;
        boolean isIDKeyEmpty = CheckSupport.isBlank(idKey);
        boolean isNameKeyEmpty = CheckSupport.isBlank(nameKey);
        boolean isDeleteflagKeyEmpty = CheckSupport.isBlank(deleteflagKey);

        if (!isIDKeyEmpty || !isNameKeyEmpty || !isDeleteflagKeyEmpty) {
            query += " Where ";
        }
        if (!isIDKeyEmpty) {
            query += " _id like N'%" + idKey + "%' ";
        }
        if (!isIDKeyEmpty && (!isNameKeyEmpty || !isDeleteflagKeyEmpty)) {
            query += " And ";
        }
        if (!isNameKeyEmpty) {
            query += " _name like N'%" + nameKey + "%' ";
        }
        if (!isNameKeyEmpty && !isDeleteflagKeyEmpty) {
            query += " And ";
        }
        if (!isDeleteflagKeyEmpty) {
            query += " _deleteflag = " + deleteflagKey;
        }
        return getList(query, new TypeOfProductMapper());
    }

}
