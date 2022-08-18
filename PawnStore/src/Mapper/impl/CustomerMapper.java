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
            String id = rs.getString("_id");
            String fullname = rs.getString("_fullname");
            String gender = rs.getString("_gender");
            String phonenumber = rs.getString("_phonenumber");
            String address = rs.getString("_address");
            boolean deleteflag = rs.getBoolean("_deleteflag");
            return new Customer(id, fullname, gender, phonenumber, address, deleteflag);
        } catch (SQLException ex) {
            Logger.getLogger(AccountMapper.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
