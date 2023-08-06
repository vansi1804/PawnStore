/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.impl;

import Common.Default;
import DAO.IAccountDAO;
import DAO.impl.AccountDAO;
import Model.Account;
import Support.EncodingSupport;

/**
 *
 * @author NVS
 */
@SuppressWarnings("ClassWithoutLogger")
public class AdminInitializerService implements Runnable {

    private final IAccountDAO accountDAO = new AccountDAO();

    @Override
    public void run() {
        if (accountDAO.findOneByUsername(Default.Admin.USERNAME) == null) {
            Account admin = new Account(
                    Default.Admin.USERNAME, 
                    EncodingSupport.getMd5(Default.Admin.PASSWORD), 
                    Default.Admin.FULL_NAME, 
                    false);
            accountDAO.insert(admin);
        }
        
    }

}
