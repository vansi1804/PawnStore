/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import Model.TypeOfProduct;
import java.util.ArrayList;

/**
 *
 * @author NVS
 */
public interface ITypeOfProductService {

    ArrayList<TypeOfProduct> getList();

    TypeOfProduct getTypeOfProductByID(String id);

    TypeOfProduct getTypeOfProductByName(String name);

    boolean insert(TypeOfProduct typeOfProduct);

    boolean update(TypeOfProduct typeOfProduct);

    boolean delete(TypeOfProduct typeOfProduct);

    ArrayList<TypeOfProduct> findAccountByIDKey(ArrayList<TypeOfProduct> typeOfProducts, String idKey);

    ArrayList<TypeOfProduct> findAccountByNameKey(ArrayList<TypeOfProduct> typeOfProducts, String nameKey);

    ArrayList<TypeOfProduct> findAccountByDeleteFlag(ArrayList<TypeOfProduct> typeOfProducts, boolean deleteflag);

    ArrayList<TypeOfProduct> findTypeOfProductByIDKey(String idKey);

    ArrayList<TypeOfProduct> findTypeOfProductByNameKey(String nameKey);

    ArrayList<TypeOfProduct> findTypeOfProductByDeleteflagKey(String deleteflagKey);

    ArrayList<TypeOfProduct> findTypeOfProductByKey(String idKey, String nameKey, String deleteflagKey);

    String getNewID();
}
