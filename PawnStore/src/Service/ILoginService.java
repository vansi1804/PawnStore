/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import Model.Account;

/**
 *
 * @author NVS
 */
public interface ILoginService {
    Account Login(String username, String password);
}
