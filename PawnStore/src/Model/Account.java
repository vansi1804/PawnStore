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

    public String getPassword() {
        return _password;
    }

    public void setPassword(String _password) {
        this._password = _password;
    }

    public Account() {
        super();
    }

    public Account(String _id, String _password, String _name) {
        super(_id, _name);
        this._password = _password;
    }

    private String _password;

    
}
