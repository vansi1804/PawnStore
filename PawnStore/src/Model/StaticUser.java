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
public class StaticUser extends Account {

    private static Account currentUser;

    public static void setCurrentInstance(Account account) {
        currentUser = new Account(account.getUsername(), account.getPassword(), account.getFullname(), account.getDeleteflag());
    }

    public static Account getCurrentInstance() {
        if (currentUser == null) {
            return new StaticUser();
        }
        return currentUser;
    }

    public StaticUser() {
        super();
    }
}
