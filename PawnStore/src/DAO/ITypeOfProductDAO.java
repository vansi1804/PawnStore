/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Model.TypeOfProduct;
import java.util.ArrayList;

/**
 *
 * @author NVS
 */
public interface ITypeOfProductDAO extends IGenericDAO<TypeOfProduct> {

    ArrayList<TypeOfProduct> getList();

    TypeOfProduct getTypeOfProductByID(String id);

    TypeOfProduct getTypeOfProductByName(String name);

    boolean insert(TypeOfProduct typeOfProduct);

    boolean update(TypeOfProduct typeOfProduct);

    boolean delete(TypeOfProduct typeOfProduct);

    ArrayList<TypeOfProduct> findTypeOfProductByIDKey(String idKey);

    ArrayList<TypeOfProduct> findTypeOfProductByNameKey(String nameKey);

    ArrayList<TypeOfProduct> findTypeOfProductByDeleteflagKey(String deleteflagKey);

    ArrayList<TypeOfProduct> findTypeOfProductByKey(String idKey, String nameKey, String deleteflagKey);

}
