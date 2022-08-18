/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.impl;

import DAO.IAccountDAO;
import Mapper.impl.AccountMapper;
import Model.Account;
import java.util.ArrayList;

/**
 *
 * @author NVS
 */
@SuppressWarnings("ClassWithoutLogger")
public class AccountDAO extends ADAO<Account> implements IAccountDAO {

    private static final String SELECTQUERY = "Select _username, _password, _fullname, _deleteflag From Account";
    private static final String INSERTQUERY = "Insert Into Account(_username, _password, _fullname, _deleteflag) Values(?,?,?,?)";
    private static final String UPDATEQUERY = "Update Account Set _password = ?, _fullname = ?, _deleteflag = ? Where _username = ?";
    private static final String DELETEQUERY = "Delete From Account Where _username = ?";

    @Override
    public ArrayList<Account> getList() {
        return getList(SELECTQUERY, new AccountMapper());
    }

    @Override
    public Account getAccount(String username) {
        String query = SELECTQUERY + " Where _username = ?";
        return getObject(query, new AccountMapper(), username);
    }

    @Override
    public boolean insert(Account account) {
        return insert(INSERTQUERY, account.getUsername(), account.getPassword(), account.getFullname(),
                account.getDeleteflag());
    }

    @Override
    public boolean update(Account account) {
        return update(UPDATEQUERY, account.getPassword(), account.getFullname(), account.getDeleteflag(),
                account.getUsername());
    }

    @Override
    public boolean delete(Account account) {
        return delete(DELETEQUERY, account.getUsername());
    }

    @Override
    public ArrayList<Account> findAccountByUsernameKey(String usernameKey) {
        String query = SELECTQUERY + " Where _username like N'%" + usernameKey + "%'";
        return getList(query, new AccountMapper());
    }

    @Override
    public ArrayList<Account> findAccountByFullnameKey(String fullnameKey) {
        String query = SELECTQUERY + " Where _fulname like N'%" + fullnameKey + "%'";
        return getList(query, new AccountMapper());
    }

    @Override
    public ArrayList<Account> findAccountByDeleteflagKey(boolean deleteflagKey) {
        String query = SELECTQUERY + " Where _deleteflag = ?";
        return getList(query, new AccountMapper(), deleteflagKey);
    }

}
