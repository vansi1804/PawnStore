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
public class User extends Account{

    private static User _currentInstance;
    
    public static User getCurrentInstance(){
        if (_currentInstance == null) {
            _currentInstance = new User();
        }
        return _currentInstance;
    }
    
    public static void setCurrentInstance(String _username, String _password, String _fullname){
        _currentInstance = new User(_username, _password, _fullname);
    }
    
    public static void setCurrentInstance(User _user){
        _currentInstance.setUsername(_user.getUsername());
        _currentInstance.setPassword(_user.getPassword());
        _currentInstance.setFullname(_user.getFullname());
    }
    
    public User() {
    }

    public User(String _username, String _password, String _fullname) {
        this.setUsername(_username);
        this.setPassword(_password);
        this.setFullname(_fullname);
    }
}
