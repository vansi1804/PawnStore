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
public class ActivityHistory {

    private String time;
    private Account account;
    private String activity;
    private String objectName;
    private String infor;

    public ActivityHistory() {
    }

    public ActivityHistory(String time, Account account, String activity, String objectname, String infor) {
        this.time = time;
        this.account = account;
        this.activity = activity;
        this.objectName = objectname;
        this.infor = infor;
    }

    public ActivityHistory(String time, String activity, String objectname, String infor) {
        this.time = time;
        this.account = StaticUser.getCurrentInstance();
        this.activity = activity;
        this.objectName = objectname;
        this.infor = infor;
    }

    public ActivityHistory(String time, String activity) {
        this.time = time;
        this.account = StaticUser.getCurrentInstance();
        this.activity = activity;
        this.objectName = "";
        this.infor = "";
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getInfor() {
        return infor;
    }

    public void setInfor(String infor) {
        this.infor = infor;
    }

}
