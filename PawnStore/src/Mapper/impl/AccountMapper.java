/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mapper.impl;

import Mapper.IObjectMapper;
import Model.Account;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NVS
 */
@SuppressWarnings("ClassWithoutLogger")
public class AccountMapper implements IObjectMapper<Account> {
    
    @Override
    public Account mapObject(ResultSet rs) {
        try {
            String username = rs.getString("_username");
            String password = rs.getString("_password");
            String fullname = rs.getString("_fullname");
            boolean deleteflag = rs.getBoolean("_deleteflag");
            return new Account(username, password, fullname, deleteflag);
        } catch (SQLException ex) {
            Logger.getLogger(AccountMapper.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
