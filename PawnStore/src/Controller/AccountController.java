/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Account;
import Service.IAccountService;
import Service.impl.AccountService;
import java.util.List;

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

    public List<Account> findAll() {
        return accountService.findAll();
    }

    public Account findOneByUsername(String username) {
        return accountService.findOneByUsername(username);
    }

    public Account findOneByUsernameAndPassword(String username, String password) {
        return accountService.findOneByUsernameAndPassword(username, password);
    }

    public boolean insert(Account account, String adminPassword) {
        return accountService.insert(account, adminPassword);
    }

    public boolean updateProfile(Account account) {
        return accountService.updateProfile(account);
    }

    public boolean changePassword(Account account, String newPassword) {
        return accountService.changePassword(account, newPassword);
    }

    public boolean resetPassword(Account account, String adminPassword) {
        return accountService.resetPassword(account, adminPassword);
    }

    public boolean lockOrUnlock(Account account, String adminPassword) {
        return accountService.lockOrUnlock(account, adminPassword);
    }

    public List<Account> findAllByStatus(Boolean deleteflag) {
        return accountService.findAllByDeleteFlag(deleteflag);
    }

}
