/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.impl;

import DAO.ITypeOfProductDAO;
import DAO.impl.TypeOfProductDAO;
import Model.TypeOfProduct;
import Service.ITypeOfProductService;
import Support.CheckSupport;
import Support.Support;
import java.util.ArrayList;

/**
 *
 * @author NVS
 */
@SuppressWarnings("ClassWithoutLogger")
public class TypeOfProductService implements ITypeOfProductService {

    private final ITypeOfProductDAO typeOfProductDAO = new TypeOfProductDAO();

    @Override
    public ArrayList<TypeOfProduct> getList() {
        return typeOfProductDAO.getList();
    }

    @Override
    public TypeOfProduct getTypeOfProductByID(String id) {
        return typeOfProductDAO.getTypeOfProductByID(id);
    }

    @Override
    public TypeOfProduct getTypeOfProductByName(String name) {
        return typeOfProductDAO.getTypeOfProductByName(name);
    }

    @Override
    public boolean insert(TypeOfProduct typeOfProduct) {
        return typeOfProductDAO.insert(typeOfProduct);
    }

    @Override
    public boolean update(TypeOfProduct typeOfProduct) {
        return typeOfProductDAO.update(typeOfProduct);
    }

    @Override
    public boolean delete(TypeOfProduct typeOfProduct) {
        return typeOfProductDAO.delete(typeOfProduct);
    }

    @Override
    public ArrayList<TypeOfProduct> findAccountByIDKey(ArrayList<TypeOfProduct> typeOfProducts, String idKey) {
        ArrayList<TypeOfProduct> results = new ArrayList<>();
        for (TypeOfProduct typeOfProduct : typeOfProducts) {
            if (CheckSupport.constains(typeOfProduct.getId(), idKey)) {
                results.add(typeOfProduct);
            }
        }
        return results;
    }

    @Override
    public ArrayList<TypeOfProduct> findAccountByNameKey(ArrayList<TypeOfProduct> typeOfProducts, String nameKey) {
        ArrayList<TypeOfProduct> results = new ArrayList<>();
        for (TypeOfProduct typeOfProduct : typeOfProducts) {
            if (CheckSupport.constains(typeOfProduct.getName(), nameKey)) {
                results.add(typeOfProduct);
            }
        }
        return results;
    }

    @Override
    public ArrayList<TypeOfProduct> findAccountByDeleteFlag(ArrayList<TypeOfProduct> typeOfProducts, boolean deleteflag) {
        ArrayList<TypeOfProduct> results = new ArrayList<>();
        for (TypeOfProduct typeOfProduct : typeOfProducts) {
            if (typeOfProduct.getDeleteflag() == deleteflag) {
                results.add(typeOfProduct);
            }
        }
        return results;
    }

    @Override
    public ArrayList<TypeOfProduct> findTypeOfProductByIDKey(String idKey) {
        return typeOfProductDAO.findTypeOfProductByIDKey(idKey);
    }

    @Override
    public ArrayList<TypeOfProduct> findTypeOfProductByNameKey(String nameKey) {
        return typeOfProductDAO.findTypeOfProductByNameKey(nameKey);
    }

    @Override
    public ArrayList<TypeOfProduct> findTypeOfProductByDeleteflagKey(String deleteflagKey) {
        return typeOfProductDAO.findTypeOfProductByDeleteflagKey(deleteflagKey);
    }

    @Override
    public ArrayList<TypeOfProduct> findTypeOfProductByKey(String idKey, String nameKey, String deleteflagKey) {
        return typeOfProductDAO.findTypeOfProductByKey(idKey, nameKey, deleteflagKey);
    }

    @Override
    public String getNewID() {
        ArrayList<TypeOfProduct> typeOfProducts = typeOfProductDAO.getList();
        if (typeOfProducts.isEmpty()) {
            return "LHH000000001";
        }
        return Support.getNewID(typeOfProducts.get(typeOfProducts.size() - 1).getId());
    }
}
