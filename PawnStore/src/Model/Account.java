/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author NVS
 */
@SuppressWarnings("ClassWithoutLogger")
public class Account {

    private String username;
    private String password;
    private String fullname;
    private boolean deleteFlag;

    public Account() {
    }

    public Account(String username, String password, String fullname, boolean deleteFlag) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.deleteFlag = deleteFlag;
    }

    public Account(Account account) {
        this.username = account.getUsername();
        this.password = account.getPassword();
        this.fullname = account.getFullname();
        this.deleteFlag = account.getDeleteFlag();
    }

    public boolean equal(Account account) {
        return this.username.equals(account.getUsername())
                && this.password.equals(account.getPassword())
                && this.fullname.equals(account.getFullname());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Override
    public String toString() {
        return username
                + " - " + fullname
                + (deleteFlag ? " - Khóa" : " - Mở khóa");
    }
}
