/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Model.Account;
import java.util.List;

/**
 *
 * @author NVS
 */
public interface IAccountDAO extends IGenericDAO<Account> {

    List<Account> findAll();

    Account findOneByUsername(String username);

    Account findOneByUsernameAndPassword(String username, String password);

    boolean insert(Account account);

    boolean update(Account account);

    boolean lockOrUnlock(String username);

    public List<Account> findAllByDeleteFlag(Boolean deleteflag);

}
