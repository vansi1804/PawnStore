/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Model.Product;
import Model.TypeOfProduct;
import java.util.List;

/**
 *
 * @author NVS
 */
public interface IProductDAO extends IGenericDAO<Product> {

    public List<Product> findAll();

    List<Product> findAllNotRedeemed();

    Product findOneById(String id);

    Product findOneByName(String name);

    boolean insert(Product product);

    boolean update(Product product);

    List<Product> filterByKey(String idKey, TypeOfProduct typeOfProductKey, String nameKey, String inforKey, String statusKey);

}
