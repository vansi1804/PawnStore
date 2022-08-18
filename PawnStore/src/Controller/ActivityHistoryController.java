/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.ActivityHistory;
import Service.IActivityHistoryService;
import Service.impl.ActivityHistoryService;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author NVS
 */
@SuppressWarnings("ClassWithoutLogger")
public class ActivityHistoryController {

    private static ActivityHistoryController instance;

    public static ActivityHistoryController getCurrentInstance() {
        if (instance == null) {
            instance = new ActivityHistoryController();
        }
        return instance;
    }

    private final IActivityHistoryService activityHistoryService = new ActivityHistoryService();

    public ArrayList<ActivityHistory> getList() {
        return activityHistoryService.getList();
    }

    public ActivityHistory getActivityHistory(String time) {
        return activityHistoryService.getActivityHistory(time);
    }

    public boolean insert(ActivityHistory activityHistory) {
        return activityHistoryService.insert(activityHistory);
    }

    public boolean update(ActivityHistory activityHistory) {
        return activityHistoryService.update(activityHistory);
    }

    public boolean delete(ActivityHistory activityHistory) {
        return activityHistoryService.delete(activityHistory);
    }

    public ArrayList<ActivityHistory> findActivityHistoryByTimeKey(ArrayList<ActivityHistory> activityHistorys,
            Date fromTime, Date toTime) {
        return activityHistoryService.findActivityHistoryByTimeKey(activityHistorys, fromTime, toTime);
    }

    public ArrayList<ActivityHistory> findActivityHistoryByUsernameKey(ArrayList<ActivityHistory> activityHistorys, String usenameKey) {
        return activityHistoryService.findActivityHistoryByUsernameKey(activityHistorys, usenameKey);
    }

    public ArrayList<ActivityHistory> findActivityHistoryByActivityKey(ArrayList<ActivityHistory> activityHistorys, String activityKey) {
        return activityHistoryService.findActivityHistoryByActivityKey(activityHistorys, activityKey);
    }

    public ArrayList<ActivityHistory> findActivityHistoryByObjectnameKey(ArrayList<ActivityHistory> activityHistorys, String objectnameKey) {
        return activityHistoryService.findActivityHistoryByObjectnameKey(activityHistorys, objectnameKey);
    }

    public ArrayList<ActivityHistory> findActivityHistoryByInforKey(ArrayList<ActivityHistory> activityHistorys, String inforKey) {
        return activityHistoryService.findActivityHistoryByInforKey(activityHistorys, inforKey);

    }

    public ArrayList<ActivityHistory> findActivityHistoryByKey(Date fromTime, Date toTime, String usenameKey, String activityKey, String objectnameKey, String inforKey) {
        return activityHistoryService.findActivityHistoryByKey(fromTime, toTime, usenameKey, activityKey, objectnameKey, inforKey);
    }
    
    public ArrayList<ActivityHistory> findActivityHistoryByTimeKey(String fromTime, String toTime) {
        return activityHistoryService.findActivityHistoryByTimeKey(fromTime, toTime);
    }

    public ArrayList<ActivityHistory> findActivityHistoryByUsernameKey(String usenameKey) {
        return activityHistoryService.findActivityHistoryByUsernameKey(usenameKey);
    }

    public ArrayList<ActivityHistory> findActivityHistoryByActivityKey(String activityKey) {
        return activityHistoryService.findActivityHistoryByActivityKey(activityKey);
    }

    public ArrayList<ActivityHistory> findActivityHistoryByObjectnameKey(String objectnameKey) {
        return activityHistoryService.findActivityHistoryByObjectnameKey(objectnameKey);
    }

    public ArrayList<ActivityHistory> findActivityHistoryByInforKey(String inforKey) {
        return activityHistoryService.findActivityHistoryByInforKey(inforKey);
    }

    public ArrayList<ActivityHistory> findActivityHistoryByKey(String fromTime, String toTime,
             String usenameKey, String activityKey, String objectnameKey, String inforKey) {
        return activityHistoryService.findActivityHistoryByKey(fromTime, toTime, usenameKey, activityKey, objectnameKey, inforKey);
    }
}
