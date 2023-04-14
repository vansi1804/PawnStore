/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Product;
import Model.TypeOfProduct;
import Service.IProductService;
import Service.impl.ProductService;
import java.util.List;

/**
 *
 * @author NVS
 */
@SuppressWarnings("ClassWithoutLogger")
public class ProductController {

    private static ProductController instance = null;

    public static ProductController getCurrentInstance() {
        if (instance == null) {
            instance = new ProductController();
        }
        return instance;
    }

    private final IProductService productService = new ProductService();

    public List<Product> findAll() {
        return productService.findAll();
    }

    public List<Product> findAllByStatus(String status) {
        return productService.findAllByStatus(status);
    }

    public Product findOneById(String id) {
        return productService.findOneById(id);
    }

    public boolean insert(Product product) {
        return productService.insert(product);
    }

    public boolean update(Product product) {
        return productService.update(product);
    }

    public List<Product> filterByKey(String idKey, TypeOfProduct typeOfProductKey,
             String nameKey, String inforKey, String statusKey) {
        return productService.filterByKey(idKey, typeOfProductKey, nameKey, inforKey, statusKey);
    }

    public String createNewId() {
        return productService.createNewId();
    }

}
