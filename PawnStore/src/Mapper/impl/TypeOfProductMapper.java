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
            String id = rs.getString("_id");
            String name = rs.getString("_name");
            boolean deleteflag = rs.getBoolean("_deleteflag");
            return new TypeOfProduct(id, name, deleteflag);
        } catch (SQLException ex) {
            Logger.getLogger(AccountMapper.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
