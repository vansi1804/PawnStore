/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mapper.impl;

import Mapper.IObjectMapper;
import Model.Account;
import Model.ActivityHistory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NVS
 */
@SuppressWarnings("ClassWithoutLogger")
public class ActivityHistoryMapper implements IObjectMapper<ActivityHistory> {

    @Override
    public ActivityHistory mapObject(ResultSet rs) {
        try {
            String time = rs.getString(1);
            Account account = new Account(rs.getString(2), rs.getString(3), rs.getString(4), rs.getBoolean(5));
            String activity = rs.getString(6);
            String objectname = rs.getString(7);
            String infor = rs.getString(8);
            return new ActivityHistory(time, account, activity, objectname, infor);
        } catch (SQLException ex) {
            Logger.getLogger(AccountMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
