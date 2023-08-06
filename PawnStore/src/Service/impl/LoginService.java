/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.impl;

import DAO.IAccountDAO;
import DAO.impl.AccountDAO;
import Model.Account;
import Service.ILoginService;
import Support.EncodingSupport;

/**
 *
 * @author NVS
 */
@SuppressWarnings("ClassWithoutLogger")
public class LoginService implements ILoginService {

    private final IAccountDAO accountDAO = new AccountDAO();

    @Override
    public Account Login(String username, String password) {
        return accountDAO.findOneByUsernameAndPassword(username, EncodingSupport.getMd5(password));
    }

}
