/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.TypeOfProduct;
import Service.ITypeOfProductService;
import Service.impl.TypeOfProductService;
import java.util.List;

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

    public List<TypeOfProduct> findAll() {
        return typeOfProductService.findAll();
    }

    public List<TypeOfProduct> findAllByStatus(Boolean deleteFlag) {
        return typeOfProductService.findAllByStatus(deleteFlag);
    }

    public TypeOfProduct findOneById(String id) {
        return typeOfProductService.findOneById(id);
    }

    public TypeOfProduct findOneByName(String name) {
        return typeOfProductService.findOneByName(name);
    }

    public boolean insert(TypeOfProduct typeOfProduct) {
        return typeOfProductService.insert(typeOfProduct);
    }

    public boolean update(TypeOfProduct typeOfProduct) {
        return typeOfProductService.update(typeOfProduct);
    }

    public String createNewId() {
        return typeOfProductService.createNewId();
    }

    public List<TypeOfProduct> filterByKey(String idKey, String nameKey, Boolean deleteflagKey) {
        return typeOfProductService.filterByKey(idKey, nameKey, deleteflagKey);
    }

}
