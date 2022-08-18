/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import Model.Product;
import Model.TypeOfProduct;
import java.util.ArrayList;

/**
 *
 * @author NVS
 */
public interface IProductService {

    ArrayList<Product> getList();

    Product getProduct(String id);

    boolean insert(Product product);

    boolean update(Product product);

    boolean delete(Product product);

    ArrayList<Product> findProductByIDKey(ArrayList<Product> products, String idKey);

    ArrayList<Product> findProductByNameKey(ArrayList<Product> products, String nameKey);

    ArrayList<Product> findProductByTypeNameKey(ArrayList<Product> products, String typeOfProductName);

    ArrayList<Product> findProductByInformationKey(ArrayList<Product> products, String inforKey);

    ArrayList<Product> findProductByStatusKey(ArrayList<Product> products, String statusKey);

    ArrayList<Product> findProductByIDKey(String idKey);

    ArrayList<Product> findProductByTypeOfProductKey(TypeOfProduct typeOfProductKey);

    ArrayList<Product> findProductByNameKey(String nameKey);

    ArrayList<Product> findProductByInforKey(String inforKey);

    ArrayList<Product> findProductByStatusKey(String statusKey);

    ArrayList<Product> findProductByKey(String idKey, TypeOfProduct typeOfProductKey, String nameKey, String inforKey, String statusKey);

    String getNewID();
}
