/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.impl;

import DAO.ITypeOfProductDAO;
import DAO.impl.TypeOfProductDAO;
import Model.TypeOfProduct;
import Service.ITypeOfProductService;
import Support.MessageSupport;
import Support.Support;
import java.util.List;

/**
 *
 * @author NVS
 */
@SuppressWarnings("ClassWithoutLogger")
public class TypeOfProductService implements ITypeOfProductService {

    private final ITypeOfProductDAO typeOfProductDAO = new TypeOfProductDAO();

    @Override
    public List<TypeOfProduct> findAll() {
        return typeOfProductDAO.findAll();
    }

    @Override
    public List<TypeOfProduct> findAllByStatus(Boolean deleteFlag) {
        return typeOfProductDAO.findAllByStatus(deleteFlag);
    }

    @Override
    public TypeOfProduct findOneById(String id) {
        return typeOfProductDAO.findOneById(id);
    }

    @Override
    public TypeOfProduct findOneByName(String name) {
        return typeOfProductDAO.findOneByName(name);
    }

    @Override
    public boolean insert(TypeOfProduct typeOfProduct) {
        if (findOneByName(typeOfProduct.getName()) != null) {
            MessageSupport.ErrorMessage("Lỗi", "Tên loại hàng hóa đã tồn tại");
            return false;
        }
        return typeOfProductDAO.insert(typeOfProduct);
    }

    @Override
    public boolean update(TypeOfProduct typeOfProduct) {
        TypeOfProduct typeOfProductWithName = this.findOneByName(typeOfProduct.getName());
        if (typeOfProductWithName != null && !typeOfProductWithName.getId().equals(typeOfProduct.getId())) {
            MessageSupport.ErrorMessage("Lỗi", "Tên loại hàng hóa đã tồn tại");
            return false;
        }
        return typeOfProductDAO.update(typeOfProduct);
    }

    @Override
    public String createNewId() {
        TypeOfProduct typeOfProduct = typeOfProductDAO.findLastest();
        if (typeOfProduct == null) {
            return "LHH000000001";
        }
        return Support.createNewId(typeOfProduct.getId());
    }

    @Override
    public List<TypeOfProduct> filterByKey(String idKey, String nameKey, Boolean deleteflagKey) {
        return typeOfProductDAO.filterByKey(idKey, nameKey, deleteflagKey);
    }
}
