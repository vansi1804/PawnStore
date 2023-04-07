/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mapper.impl;

import Mapper.IObjectMapper;
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
public class TypeOfProductMapper implements IObjectMapper<TypeOfProduct> {

    @Override
    public TypeOfProduct mapObject(ResultSet rs) {
        try {
            String id = rs.getString(1);
            String name = rs.getString(2);
            boolean deleteflag = rs.getBoolean(3);
            return new TypeOfProduct(id, name, deleteflag);
        } catch (SQLException ex) {
            Logger.getLogger(AccountMapper.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
