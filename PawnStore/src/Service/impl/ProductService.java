/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.impl;

import DAO.IProductDAO;
import DAO.ITypeOfProductDAO;
import DAO.impl.ProductDAO;
import DAO.impl.TypeOfProductDAO;
import Model.Product;
import Model.TypeOfProduct;
import Service.IProductService;
import Support.MessageSupport;
import Support.Support;
import java.util.List;

/**
 *
 * @author NVS
 */
@SuppressWarnings("ClassWithoutLogger")
public class ProductService implements IProductService {

    private final IProductDAO productDAO = new ProductDAO();
    private final ITypeOfProductDAO typeOfProductDAO = new TypeOfProductDAO();
 @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }

    @Override
    public List<Product> findAllNotRedeemed() {
        return productDAO.findAllNotRedeemed();
    }

    @Override
    public Product findOneById(String id) {
        return productDAO.findOneById(id);
    }

    @Override
    public boolean insert(Product product) {
        Product productWithName = productDAO.findOneByName(product.getName());
        if (productWithName != null
                && productWithName.getName().endsWith(product.getName())
                && productWithName.getInfo().equals(product.getInfo())) {
            MessageSupport.ErrorMessage("Lỗi", "Hàng hóa đã tồn tại");
            return false;
        }
        product.setTypeOfProduct(typeOfProductDAO.findOneByName(product.getTypeOfProduct().getName()));
        return productDAO.insert(product);
    }

    @Override
    public boolean update(Product product) {
        Product productWithName = productDAO.findOneByName(product.getName());
        if (productWithName != null
                && !productWithName.getId().equals(product.getId())
                && productWithName.getName().endsWith(product.getName())
                && productWithName.getInfo().equals(product.getInfo())) {
            MessageSupport.ErrorMessage("Lỗi", "Hàng hóa đã tồn tại");
            return false;
        }
        product.setTypeOfProduct(typeOfProductDAO.findOneByName(product.getTypeOfProduct().getName()));
        return productDAO.update(product);
    }

    @Override
    public List<Product> filterByKey(String idKey, TypeOfProduct typeOfProductKey,
            String nameKey, String inforKey, String statusKey) {
        return productDAO.filterByKey(idKey, typeOfProductKey, nameKey, inforKey, statusKey);
    }

    @Override
    public String createNewId() {
        List<Product> products = productDAO.findAllNotRedeemed();
        if (products.isEmpty()) {
            return "HH0000000001";
        }
        return Support.createNewId(products.get(products.size() - 1).getId());
    }
}
