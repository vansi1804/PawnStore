/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mapper.impl;

import Mapper.IObjectMapper;
import Model.Product;
import Model.TypeOfProduct;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NVS
 */
@SuppressWarnings("ClassWithoutLogger")
public class ProductMapper implements IObjectMapper<Product> {
    @Override
    public Product mapObject(ResultSet rs) {
        try {
            String id = rs.getString(1);
            TypeOfProduct typeOfProduct = new TypeOfProduct(rs.getString(2), rs.getString(3), rs.getBoolean(4));
            String name = rs.getString(5);            
            String infor = rs.getString(6);
            String status = rs.getString(7);
            return new Product(id, typeOfProduct, name, infor, status);
        } catch (SQLException ex) {
            Logger.getLogger(AccountMapper.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
