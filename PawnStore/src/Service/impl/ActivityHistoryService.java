/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.impl;

import DAO.IActivityHistoryDAO;
import DAO.impl.ActivityHistoryDAO;
import Model.ActivityHistory;
import Service.IActivityHistoryService;
import java.util.List;

/**
 *
 * @author NVS
 */
@SuppressWarnings("ClassWithoutLogger")
public class ActivityHistoryService implements IActivityHistoryService {
    
    private final IActivityHistoryDAO activityHistoryDAO = new ActivityHistoryDAO();
    
    @Override
    public List<ActivityHistory> getList() {
        return activityHistoryDAO.getList();
    }
    
    @Override
    public ActivityHistory getActivityHistory(String time) {
        return activityHistoryDAO.getActivityHistory(time);
    }
    
    @Override
    public boolean insert(ActivityHistory activityHistory) {
        return activityHistoryDAO.insert(activityHistory);
    }
     
    @Override
    public List<ActivityHistory> filterByKey(String fromTime, String toTime,
             String usernameKey, String activityKey, String objectNameKey, String infoKey) {
        return activityHistoryDAO.filterByKey(fromTime, toTime, usernameKey, activityKey, objectNameKey, infoKey);
    }
    
}
