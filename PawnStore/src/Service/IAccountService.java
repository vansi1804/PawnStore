/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import Model.Account;
import java.util.List;

/**
 *
 * @author NVS
 */
public interface IAccountService {

    List<Account> findAll();

    Account findOneByUsername(String username);

    public Account findOneByUsernameAndPassword(String username, String password);

    boolean insert(Account account, String adminPassword);

    boolean updateProfile(Account account);

    public boolean changePassword(Account account, String newPassword);

    public boolean resetPassword(Account account, String adminPassword);

    boolean lockOrUnlock(Account account, String adminPassword);

    List<Account> findAllByDeleteFlag(Boolean deleteflag);

}
