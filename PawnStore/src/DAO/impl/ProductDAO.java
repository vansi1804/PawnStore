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
import java.util.List;

/**
 *
 * @author NVS
 */
@SuppressWarnings("ClassWithoutLogger")
public class ProductDAO extends ADAO<Product> implements IProductDAO {

    private static final String SELECTQUERY = "Select product.id"
            + ", type_of_product.id, type_of_product.name, type_of_product.delete_flag"
            + ", product.name, product.info, product.status"
            + " From product Inner Join type_of_product On product.type_id = type_of_product.id";
    private static final String INSERTQUERY = "Insert Into product(id, type_id, name, info, status) Values (?,?,?,?,?)";
    private static final String UPDATEQUERY = "Update product Set type_id = ?, name = ?, info = ?, status = ? Where id = ?";

    @Override
    public List<Product> findAll() {
        return findAll(SELECTQUERY, new ProductMapper());
    }

    @Override
    public List<Product> findAllNotRedeemed() {
        String query = SELECTQUERY + " Where product.status = N'Chưa chuộc'";
        return findAll(query, new ProductMapper());
    }

    @Override
    public Product findOneById(String id) {
        String query = SELECTQUERY + " Where product.id = ?";
        return findOne(query, new ProductMapper(), id);
    }

    @Override
    public Product findOneByName(String name) {
        String query = SELECTQUERY + " Where product.name = ?";
        return findOne(query, new ProductMapper(), name);
    }

    @Override
    public boolean insert(Product product) {
        return insert(INSERTQUERY, product.getId(), product.getTypeOfProduct().getId(),
                product.getName(), product.getInfo(), product.getStatus());
    }

    @Override
    public boolean update(Product product) {
        return update(UPDATEQUERY, product.getTypeOfProduct().getId(),
                product.getName(), product.getInfo(), product.getStatus(), product.getId());
    }

    @Override
    @SuppressWarnings("null")
    public List<Product> filterByKey(String idKey, TypeOfProduct typeOfProductKey,
            String nameKey, String inforKey, String statusKey) {
        String query = SELECTQUERY
                + " Where 1 = 1"
                + (CheckSupport.isNullOrBlank(idKey) ? "" : " And product.id Like N'%" + idKey + "%'")
                + (typeOfProductKey == null ? "" : " And type_of_product.name Like N'" + typeOfProductKey.getName() + "'")
                + (CheckSupport.isNullOrBlank(nameKey) ? "" : " And product.name Like N'%" + nameKey + "%'")
                + (CheckSupport.isNullOrBlank(inforKey) ? "" : " And product.info Like N'%" + inforKey + "%'")
                + (CheckSupport.isNullOrBlank(statusKey) ? "" : " And product.status Like N'%" + statusKey + "%'");
        return findAll(query, new ProductMapper());
    }

}
