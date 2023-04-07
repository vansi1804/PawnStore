/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.impl;

import Common.Default;
import DAO.IAccountDAO;
import DAO.impl.AccountDAO;
import Model.Account;
import Model.StaticUser;
import Service.IAccountService;
import Support.EncodingSupport;
import Support.MessageSupport;
import java.util.List;

/**
 *
 * @author NVS
 */
@SuppressWarnings("ClassWithoutLogger")
public class AccountService implements IAccountService {

    private final IAccountDAO accountDAO = new AccountDAO();

    @Override
    public List<Account> findAll() {
        return accountDAO.findAll();
    }

    @Override
    public Account findOneByUsername(String username) {
        return accountDAO.findOneByUsername(username);
    }

    @Override
    public Account findOneByUsernameAndPassword(String username, String password) {
        return accountDAO.findOneByUsernameAndPassword(username, EncodingSupport.getMd5(password));
    }

    @Override
    public boolean insert(Account account, String adminPassword) {
        if (this.findOneByUsernameAndPassword(StaticUser.getCurrentInstance().getUsername(), adminPassword) == null) { // check password of admin
            MessageSupport.ErrorMessage("Lỗi", "Mật khẩu quản trị viên không đúng");
            return false;
        }
        if (this.findOneByUsername(account.getUsername()) != null) {
            MessageSupport.ErrorMessage("Lỗi", "Tên đăng nhập đã tồn tại");
            return false;
        }
        account.setPassword(EncodingSupport.getMd5(Default.DEFAULT_PASSWORD));
        return accountDAO.insert(account);
    }

    @Override
    public boolean updateProfile(Account account) {
        if (this.findOneByUsernameAndPassword(account.getUsername(), account.getPassword()) == null) {
            MessageSupport.ErrorMessage("Lỗi", "Mật khẩu không đúng");
            return false;
        }
        account.setPassword(EncodingSupport.getMd5(account.getPassword()));
        return accountDAO.update(account);
    }

    @Override
    public boolean changePassword(Account account, String newPassword) {
        if (this.findOneByUsernameAndPassword(account.getUsername(), account.getPassword()) == null) {
            MessageSupport.ErrorMessage("Lỗi", "Mật khẩu không đúng");
            return false;
        }
        account.setPassword(EncodingSupport.getMd5(newPassword));
        return accountDAO.update(account);
    }

    @Override
    public boolean resetPassword(Account account, String adminPassword) {
        if (this.findOneByUsernameAndPassword(StaticUser.getCurrentInstance().getUsername(), adminPassword) == null) { // check password of admin
            MessageSupport.ErrorMessage("Lỗi", "Mật khẩu quản trị viên không đúng");
            return false;
        }
        account.setPassword(EncodingSupport.getMd5(Default.DEFAULT_PASSWORD));
        return accountDAO.update(account);
    }

    @Override
    public boolean lockOrUnlock(Account account, String adminPassword) {
       if (this.findOneByUsernameAndPassword(StaticUser.getCurrentInstance().getUsername(), adminPassword) == null) { // check password of admin
            MessageSupport.ErrorMessage("Lỗi", "Mật khẩu quản trị viên không đúng");
            return false;
        }
        return accountDAO.lockOrUnlock(account.getUsername());
    }

    @Override
    public List<Account> findAllByDeleteFlag(Boolean deleteflag) {
        return accountDAO.findAllByDeleteFlag(deleteflag);
    }

}
