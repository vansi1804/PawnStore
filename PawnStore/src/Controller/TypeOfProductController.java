/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.TypeOfProduct;
import Service.ITypeOfProductService;
import Service.impl.TypeOfProductService;
import java.util.ArrayList;

/**
 *
 * @author NVS
 */
@SuppressWarnings("ClassWithoutLogger")
public class TypeOfProductController {

    private static TypeOfProductController instance;

    public static TypeOfProductController getCurrentInstance() {
        if (instance == null) {
            instance = new TypeOfProductController();
        }
        return instance;
    }

    private final ITypeOfProductService typeOfProductService = new TypeOfProductService();

    public ArrayList<TypeOfProduct> getList() {
        return typeOfProductService.getList();
    }

    public TypeOfProduct getTypeOfProductByID(String id) {
        return typeOfProductService.getTypeOfProductByID(id);
    }
    public TypeOfProduct getTypeOfProductByName(String name) {
        return typeOfProductService.getTypeOfProductByName(name);
    }
    public boolean insert(TypeOfProduct typeOfProduct) {
        return typeOfProductService.insert(typeOfProduct);
    }

    public boolean update(TypeOfProduct typeOfProduct) {
        return typeOfProductService.update(typeOfProduct);
    }

    public boolean delete(TypeOfProduct typeOfProduct) {
        return typeOfProductService.delete(typeOfProduct);
    }

    public ArrayList<TypeOfProduct> findTypeOfProductByIDKey(ArrayList<TypeOfProduct> typeOfProducts, String idKey) {
        return typeOfProductService.findAccountByIDKey(typeOfProducts, idKey);
    }

    public ArrayList<TypeOfProduct> findTypeOfProductByNameKey(ArrayList<TypeOfProduct> typeOfProducts, String nameKey) {
        return typeOfProductService.findAccountByNameKey(typeOfProducts, nameKey);
    }

    public ArrayList<TypeOfProduct> findTypeOfProductByDeleteFlag(ArrayList<TypeOfProduct> typeOfProducts, boolean deleteflag) {
        return typeOfProductService.findAccountByDeleteFlag(typeOfProducts, deleteflag);
    }

    public ArrayList<TypeOfProduct> findTypeOfProductByIDKey(String idKey) {
        return typeOfProductService.findTypeOfProductByIDKey(idKey);
    }

    public ArrayList<TypeOfProduct> findTypeOfProductByNameKey(String nameKey) {
        return typeOfProductService.findTypeOfProductByNameKey(nameKey);
    }

    public ArrayList<TypeOfProduct> findTypeOfProductByDeleteflagKey(String deleteflagKey) {
        return typeOfProductService.findTypeOfProductByDeleteflagKey(deleteflagKey);
    }

    public ArrayList<TypeOfProduct> findTypeOfProductByKey(String idKey, String nameKey, String deleteflagKey) {
        return typeOfProductService.findTypeOfProductByKey(idKey, nameKey, deleteflagKey);
    }

    public String getNewID() {
        return typeOfProductService.getNewID();
    }
}
