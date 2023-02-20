/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.impl;

import DAO.IAccountDAO;
import DAO.impl.AccountDAO;
import Model.Account;
import Service.IAccountService;
import Support.EncodingSupport;
import java.util.ArrayList;

/**
 *
 * @author NVS
 */
@SuppressWarnings("ClassWithoutLogger")
public class AccountService implements IAccountService {

    private final IAccountDAO accountDAO = new AccountDAO();

    @Override
    public ArrayList<Account> getList() {
        return accountDAO.getList();
    }

    @Override
    public Account getAccount(String username) {
        return accountDAO.getAccount(username);
    }

    @Override
    public boolean insert(Account account) {
        account.setPassword(EncodingSupport.getMd5(account.getPassword()));
        return accountDAO.insert(account);
    }

    @Override
    public boolean update(Account account) {
        return accountDAO.update(account);
    }

    @Override
    public boolean delete(Account account) {
        return accountDAO.delete(account);
    }

    @Override
    public ArrayList<Account> findAccountByUsernameKey(String usernameKey) {
        return accountDAO.findAccountByUsernameKey(usernameKey);
    }

    @Override
    public ArrayList<Account> findAccountByFullnameKey(String fullnameKey) {
        return accountDAO.findAccountByFullnameKey(fullnameKey);
    }

    @Override
    public ArrayList<Account> findAccountByDeleteflagKey(boolean deleteflag) {
        return accountDAO.findAccountByDeleteflagKey(deleteflag);
    }

}
