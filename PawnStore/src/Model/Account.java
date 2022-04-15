/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Bau Kien
 */
public class Account {

    public Account() {
    }

    public Account(String _username, String _password, String _fullname) {
        this._username = _username;
        this._password = _password;
        this._fullname = _fullname;
    }
    public Account(Account _account) {
        this._username = _account.getUsername();
        this._password = _account.getPassword();
        this._fullname = _account.getFullname();
    }

    public String getUsername() {
        return _username;
    }

    public void setUsername(String _username) {
        this._username = _username;
    }

    public String getPassword() {
        return _password;
    }

    public void setPassword(String _password) {
        this._password = _password;
    }

    public String getFullname() {
        return _fullname;
    }

    public void setFullname(String _fullname) {
        this._fullname = _fullname;
    }
    private String _username;
    private String _password;
    private String _fullname;

    
}
