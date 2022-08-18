/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.impl;

import DAO.IProductDAO;
import DAO.impl.ProductDAO;
import Model.Product;
import Model.TypeOfProduct;
import Service.IProductService;
import Support.CheckSupport;
import Support.Support;
import java.util.ArrayList;

/**
 *
 * @author NVS
 */
@SuppressWarnings("ClassWithoutLogger")
public class ProductService implements IProductService {

    private final IProductDAO productDAO = new ProductDAO();

    @Override
    public ArrayList<Product> getList() {
        return productDAO.getList();
    }

    @Override
    public Product getProduct(String id) {
        return productDAO.getProduct(id);
    }

    @Override
    public boolean insert(Product product) {
        return productDAO.insert(product);
    }

    @Override
    public boolean update(Product product) {
        return productDAO.update(product);
    }

    @Override
    public boolean delete(Product product) {
        return productDAO.delete(product);
    }

    @Override
    public ArrayList<Product> findProductByIDKey(ArrayList<Product> products, String idKey) {
        ArrayList<Product> results = new ArrayList<>();
        for (Product product : products) {
            if (CheckSupport.constains(product.getId(), idKey)) {
                results.add(product);
            }
        }
        return results;
    }

    @Override
    public ArrayList<Product> findProductByNameKey(ArrayList<Product> products, String nameKey) {
        ArrayList<Product> results = new ArrayList<>();
        for (Product product : products) {
            if (CheckSupport.constains(product.getName(), nameKey)) {
                results.add(product);
            }
        }
        return results;
    }

    @Override
    public ArrayList<Product> findProductByTypeNameKey(ArrayList<Product> products, String typeOfProductName) {
        ArrayList<Product> results = new ArrayList<>();
        for (Product product : products) {
            if (product.getTypeOfProduct().getName().equals(typeOfProductName)) {
                results.add(product);
            }
        }
        return results;
    }

    @Override
    public ArrayList<Product> findProductByInformationKey(ArrayList<Product> products, String inforKey) {
        ArrayList<Product> results = new ArrayList<>();
        for (Product product : products) {
            if (CheckSupport.constains(product.getInfo(), inforKey)) {
                results.add(product);
            }
        }
        return results;
    }

    @Override
    public ArrayList<Product> findProductByStatusKey(ArrayList<Product> products, String statusKey) {
        ArrayList<Product> results = new ArrayList<>();
        for (Product product : products) {
            if (product.getStatus().equals(statusKey)) {
                results.add(product);
            }
        }
        return results;
    }

    @Override
    public ArrayList<Product> findProductByIDKey(String idKey) {
        return productDAO.findProductByIDKey(idKey);
    }

    @Override
    public ArrayList<Product> findProductByTypeOfProductKey(TypeOfProduct typeOfProductKey) {
        return productDAO.findProductByTypeOfProductKey(typeOfProductKey);
    }

    @Override
    public ArrayList<Product> findProductByNameKey(String nameKey) {
        return productDAO.findProductByNameKey(nameKey);
    }

    @Override
    public ArrayList<Product> findProductByInforKey(String inforKey) {
        return productDAO.findProductByInforKey(inforKey);
    }

    @Override
    public ArrayList<Product> findProductByStatusKey(String statusKey) {
        return productDAO.findProductByStatusKey(statusKey);
    }

    @Override
    public ArrayList<Product> findProductByKey(String idKey, TypeOfProduct typeOfProductKey
            , String nameKey, String inforKey, String statusKey) {
        return productDAO.findProductByKey(idKey, typeOfProductKey, nameKey, inforKey, statusKey);
    }

    @Override
    public String getNewID() {
        ArrayList<Product> products = productDAO.getList();
        if (products.isEmpty()) {
            return "HH0000000001";
        }
        return Support.getNewID(products.get(products.size() - 1).getId());
    }
}
