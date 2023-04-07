/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.ActivityHistory;
import Service.IActivityHistoryService;
import Service.impl.ActivityHistoryService;
import java.util.List;

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

    public List<ActivityHistory> getList() {
        return activityHistoryService.getList();
    }

    public ActivityHistory getActivityHistory(String time) {
        return activityHistoryService.getActivityHistory(time);
    }

    public boolean insert(ActivityHistory activityHistory) {
        return activityHistoryService.insert(activityHistory);
    }

    public List<ActivityHistory> filterByKey(String fromTime, String toTime,
             String usernameKey, String activityKey, String objectNameKey, String infoKey) {
        return activityHistoryService.filterByKey(fromTime, toTime, usernameKey, activityKey, objectNameKey, infoKey);
    }

}
