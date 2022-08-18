/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Product;
import Model.TypeOfProduct;
import Service.IProductService;
import Service.impl.ProductService;
import java.util.ArrayList;

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

    public ArrayList<Product> getList() {
        return productService.getList();
    }

    public Product getProduct(String id) {
        return productService.getProduct(id);
    }

    public boolean insert(Product product) {
        return productService.insert(product);
    }

    public boolean update(Product product) {
        return productService.update(product);
    }

    public boolean delete(Product product) {
        return productService.delete(product);
    }

    public ArrayList<Product> findProductByIDKey(ArrayList<Product> products, String idKey) {
        return productService.findProductByIDKey(products, idKey);
    }

    public ArrayList<Product> findProductByNameKey(ArrayList<Product> products, String nameKey) {
        return productService.findProductByNameKey(products, nameKey);
    }

    public ArrayList<Product> findProductByTypeNameKey(ArrayList<Product> products, String typeOfProductName) {
        return productService.findProductByTypeNameKey(products, typeOfProductName);
    }

    public ArrayList<Product> findProductByInformationKey(ArrayList<Product> products, String inforKey) {
        return productService.findProductByInformationKey(products, inforKey);
    }

    public ArrayList<Product> findProductByStatusKey(ArrayList<Product> products, String status) {
        return productService.findProductByStatusKey(products, status);
    }

    public ArrayList<Product> findProductByIDKey(String idKey){
        return productService.findProductByIDKey(idKey);
    }

    public ArrayList<Product> findProductByTypeOfProductKey(TypeOfProduct typeOfProductKey){
        return productService.findProductByTypeOfProductKey(typeOfProductKey);
    }

    public ArrayList<Product> findProductByNameKey(String nameKey){
        return productService.findProductByNameKey(nameKey);
    }

    public ArrayList<Product> findProductByInforKey(String inforKey){
        return productService.findProductByInforKey(inforKey);
    }

    public ArrayList<Product> findProductByStatusKey(String statusKey){
        return productService.findProductByStatusKey(statusKey);
    }

    public ArrayList<Product> findProductByKey(String idKey, TypeOfProduct typeOfProductKey
            , String nameKey, String inforKey, String statusKey){
        return productService.findProductByKey(idKey, typeOfProductKey, nameKey, inforKey, statusKey);
    }

    public String getNewID() {
        return productService.getNewID();
    }

}
