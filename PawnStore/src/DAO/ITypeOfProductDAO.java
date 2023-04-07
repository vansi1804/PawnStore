/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Model.TypeOfProduct;
import java.util.List;

/**
 *
 * @author NVS
 */
public interface ITypeOfProductDAO extends IGenericDAO<TypeOfProduct> {

    List<TypeOfProduct> findAllServing();

    TypeOfProduct findOneById(String id);

    TypeOfProduct findOneByName(String name);
    
    boolean insert(TypeOfProduct typeOfProduct);

    boolean update(TypeOfProduct typeOfProduct);

    List<TypeOfProduct> filterByKey(String idKey, String nameKey, Boolean deleteflagKey);

}
