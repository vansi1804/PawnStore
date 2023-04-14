/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import Model.Product;
import Model.TypeOfProduct;
import java.util.List;

/**
 *
 * @author NVS
 */
public interface IProductService {

    List<Product> findAll();

    List<Product> findAllByStatus(String status);

    Product findOneById(String id);

    boolean insert(Product product);

    boolean update(Product product);

    List<Product> filterByKey(String idKey, TypeOfProduct typeOfProductKey, String nameKey, String inforKey, String statusKey);

    String createNewId();

}
