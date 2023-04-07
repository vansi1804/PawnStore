/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.impl;

import DAO.IAccountDAO;
import Mapper.impl.AccountMapper;
import Model.Account;
import java.util.List;

/**
 *
 * @author NVS
 */
@SuppressWarnings("ClassWithoutLogger")
public class AccountDAO extends ADAO<Account> implements IAccountDAO {

    private static final String SELECTQUERY = "Select username, fullname, delete_flag From account";
    private static final String INSERTQUERY = "Insert Into account(username, password, fullname, delete_flag) Values(?,?,?,?)";
    private static final String UPDATEQUERY = "Update account";

    @Override
    public List<Account> findAll() {
        return findAll(SELECTQUERY, new AccountMapper());
    }

    @Override
    public Account findOneByUsername(String username) {
        String query = SELECTQUERY + " Where username = ?";
        return findOne(query, new AccountMapper(), username);
    }

    @Override
    public Account findOneByUsernameAndPassword(String username, String password) {
        String query = SELECTQUERY + " Where username = ? And password = ?";
        return findOne(query, new AccountMapper(), username, password);
    }

    @Override
    public boolean insert(Account account) {
        return insert(INSERTQUERY, account.getUsername(), account.getPassword(), account.getFullname(),
                account.getDeleteFlag());
    }

    @Override
    public boolean update(Account account) {
        String query = UPDATEQUERY
                + " Set password = ?, fullname = ? Where username = ?";
        return update(query, account.getPassword(), account.getFullname(), account.getUsername());
    }

    @Override
    public boolean lockOrUnlock(String username) {
        String query = UPDATEQUERY
                + " Set delete_flag = !delete_flag Where username = ?";
        return update(query, username);
    }

    @Override
    public List<Account> findAllByDeleteFlag(Boolean deleteflag) {
        String query = SELECTQUERY
                + " Where 1 = 1"
                + (deleteflag == null ? "" : " And delete_flag = ?");
        return findAll(query, new AccountMapper(), deleteflag);
    }

}
