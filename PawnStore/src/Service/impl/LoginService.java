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
        if (account != null) {
            if (account.getPassword().equals(password)) {
                return account;
            } else {
                MessageSupport.Message("Lỗi", "Mật khẩu không đúng.");
                return null;
            }
        } else {
            MessageSupport.Message("Lỗi", "Tên đăng nhập không tồn tại.");
            return null;
        }
    }

}
