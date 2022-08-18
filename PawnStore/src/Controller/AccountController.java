/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Account;
import Service.IAccountService;
import Service.impl.AccountService;
import java.util.ArrayList;

/**
 *
 * @author NVS
 */
@SuppressWarnings("ClassWithoutLogger")
public class AccountController {

    private static AccountController instance;

    public static AccountController getCurrentInstance() {
        if (instance == null) {
            instance = new AccountController();
        }
        return instance;
    }

    private final IAccountService accountService = new AccountService();

    public ArrayList<Account> getList() {
        return accountService.getList();
    }

    public Account getAccount(String username) {
        return accountService.getAccount(username);
    }

    public boolean insert(Account account) {
        return accountService.insert(account);
    }

    public boolean update(Account account) {
        return accountService.update(account);
    }

    public boolean delete(Account account) {
        return accountService.delete(account);
    }
    
    public ArrayList<Account> findAccountByUsernameKey(String usernameKey) {
        return accountService.findAccountByUsernameKey(usernameKey);
    }

    public ArrayList<Account> findAccountByFullnameKey(String fullnameKey) {
        return accountService.findAccountByFullnameKey(fullnameKey);
    }

    public ArrayList<Account> findAccountByDeleteflagKey(boolean deleteflag) {
        return accountService.findAccountByDeleteflagKey(deleteflag);
    }

    public ArrayList<Account> findAccountByDeleteFlag(boolean deleteflag) {
        return accountService.findAccountByDeleteflagKey(deleteflag);
    }

}
