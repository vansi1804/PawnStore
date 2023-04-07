/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mapper.impl;

import Mapper.IObjectMapper;
import Model.Customer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NVS
 */
@SuppressWarnings("ClassWithoutLogger")
public class CustomerMapper implements IObjectMapper<Customer> {

    @Override
    public Customer mapObject(ResultSet rs) {
        try {
            String id = rs.getString(1);
            String fullname = rs.getString(2);
            String gender = rs.getString(3);
            String phonenumber = rs.getString(4);
            String address = rs.getString(5);
            boolean deleteflag = rs.getBoolean(6);
            return new Customer(id, fullname, gender, phonenumber, address, deleteflag);
        } catch (SQLException ex) {
            Logger.getLogger(AccountMapper.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
