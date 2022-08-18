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
    private String objectname;
    private String infor;

    public ActivityHistory() {
    }

    public ActivityHistory(String time, Account account, String activity, String objectname, String infor) {
        this.time = time;
        this.account = account;
        this.activity = activity;
        this.objectname = objectname;
        this.infor = infor;
    }

    public void copy(ActivityHistory activityHistory){
        time = activityHistory.getTime();
        account = activityHistory.getAccount();
        activity = activityHistory.getActivity();
        objectname = activityHistory.getObjectname();
        infor = activityHistory.getInfor();
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

    public String getObjectname() {
        return objectname;
    }

    public void setObjectname(String objectname) {
        this.objectname = objectname;
    }

    public String getInfor() {
        return infor;
    }

    public void setInfor(String infor) {
        this.infor = infor;
    }

}
