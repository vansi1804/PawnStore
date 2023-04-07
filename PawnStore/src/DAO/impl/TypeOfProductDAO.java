/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.impl;

import DAO.ITypeOfProductDAO;
import Mapper.impl.TypeOfProductMapper;
import Model.TypeOfProduct;
import Support.CheckSupport;
import java.util.List;

/**
 *
 * @author NVS
 */
@SuppressWarnings("ClassWithoutLogger")
public class TypeOfProductDAO extends ADAO<TypeOfProduct> implements ITypeOfProductDAO {

    private static final String SELECTQUERY = "Select id, name, delete_flag From type_of_product";
    private static final String INSERTQUERY = "Insert Into type_of_product(id, name, delete_flag) Values(?,?,?)";
    private static final String UPDATEQUERY = "Update type_of_product Set name = ?, delete_flag = ? Where id = ?";

    @Override
    public List<TypeOfProduct> findAllServing() {
        String query = SELECTQUERY + " Where delete_flag = 0";
        return findAll(query, new TypeOfProductMapper());
    }

    @Override
    public TypeOfProduct findOneById(String id) {
        String query = SELECTQUERY + " Where id = ?";
        return findOne(query, new TypeOfProductMapper(), id);
    }

    @Override
    public TypeOfProduct findOneByName(String name) {
        String query = SELECTQUERY + " Where name = ?";
        return findOne(query, new TypeOfProductMapper(), name);
    }

    @Override
    public boolean insert(TypeOfProduct typeOfProduct) {
        return insert(INSERTQUERY, typeOfProduct.getId(), typeOfProduct.getName(), typeOfProduct.getDeleteFlag());
    }

    @Override
    public boolean update(TypeOfProduct typeOfProduct) {
        return insert(UPDATEQUERY, typeOfProduct.getName(), typeOfProduct.getDeleteFlag(), typeOfProduct.getId());
    }

    @Override
    public List<TypeOfProduct> filterByKey(String idKey, String nameKey, Boolean deleteflagKey) {
        String query = SELECTQUERY
                + " Where 1 = 1"
                + (CheckSupport.isNullOrBlank(idKey) ? "" : " And id like '%" + idKey + "%'")
                + (CheckSupport.isNullOrBlank(nameKey) ? "" : " And id like N'%" + nameKey + "%'")
                + (deleteflagKey == null ? "" : " And delete_flag = " + deleteflagKey);
        return findAll(query, new TypeOfProductMapper());
    }

}
