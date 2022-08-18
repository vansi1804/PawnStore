/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Account;
import Service.ILoginService;
import Service.impl.LoginService;

/**
 *
 * @author NVS
 */
@SuppressWarnings("ClassWithoutLogger")
public class LoginController {

    private static LoginController instance;
    
    public static LoginController getCurrentInstance(){
        if (instance == null) {
            instance = new LoginController();
        }
        return instance;
    }
    
    private final ILoginService loginService = new LoginService();
    
    public Account login(String username, String password) {
        return loginService.Login(username, password);
    }
}
