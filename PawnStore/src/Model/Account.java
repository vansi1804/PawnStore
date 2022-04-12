/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author NVS
 */
public class Account extends Information {

    private String _password;
    
    private static Account _instance;
    
    public static Account getInstance(){
        if (_instance == null) {
            return null;
        }
        return _instance;
    }
    public static void SetInstance(String _id, String _password, String _name){
        _instance = new Account(_id,_password, _name);
    }
    public Account(String _id, String _password, String _name) {
        super(_id, _name);
        this._password = _password;
    }
    
    public String getPassword() {
        return _instance._password;
    }

    public void setPassword(String _password) {
        this._instance._password = _password;
    }

    public Account() {
        super();
    }

}
