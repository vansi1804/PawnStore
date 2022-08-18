/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Model.Product;
import Model.TypeOfProduct;
import java.util.ArrayList;

/**
 *
 * @author NVS
 */
public interface IProductDAO extends IGenericDAO<Product> {

    ArrayList<Product> getList();

    Product getProduct(String id);

    boolean insert(Product product);

    boolean update(Product product);

    boolean delete(Product product);

    ArrayList<Product> findProductByIDKey(String idKey);

    ArrayList<Product> findProductByTypeOfProductKey(TypeOfProduct typeOfProductKey);

    ArrayList<Product> findProductByNameKey(String nameKey);

    ArrayList<Product> findProductByInforKey(String inforKey);

    ArrayList<Product> findProductByStatusKey(String statusKey);

    ArrayList<Product> findProductByKey(String idKey, TypeOfProduct typeOfProductKey, String nameKey, String inforKey, String statusKey);

}
