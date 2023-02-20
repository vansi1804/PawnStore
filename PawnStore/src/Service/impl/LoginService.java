/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.impl;

import DAO.IAccountDAO;
import DAO.impl.AccountDAO;
import Model.Account;
import Service.ILoginService;
import Support.MessageSupport;

/**
 *
 * @author NVS
 */
@SuppressWarnings("ClassWithoutLogger")
public class LoginService implements ILoginService {

    private final IAccountDAO accountDAO = new AccountDAO();

    @Override
    public Account Login(String username, String password) {
        Account account = accountDAO.getAccount(username);
        if (account != null && account.getPassword().equals(password)) {
            return account;
        } else {
            return null;
        }

    }

}
