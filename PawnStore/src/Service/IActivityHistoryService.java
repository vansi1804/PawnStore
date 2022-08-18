/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import Model.ActivityHistory;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author NVS
 */
public interface IActivityHistoryService {

    ArrayList<ActivityHistory> getList();

    ActivityHistory getActivityHistory(String time);

    boolean insert(ActivityHistory activityHistory);

    boolean update(ActivityHistory activityHistory);

    boolean delete(ActivityHistory activityHistory);

    ArrayList<ActivityHistory> findActivityHistoryByTimeKey(ArrayList<ActivityHistory> activityHistorys, Date fromTime, Date toTime);

    ArrayList<ActivityHistory> findActivityHistoryByUsernameKey(ArrayList<ActivityHistory> activityHistorys, String usenameKey);

    ArrayList<ActivityHistory> findActivityHistoryByActivityKey(ArrayList<ActivityHistory> activityHistorys, String activityKey);

    ArrayList<ActivityHistory> findActivityHistoryByObjectnameKey(ArrayList<ActivityHistory> activityHistorys, String objectnameKey);

    ArrayList<ActivityHistory> findActivityHistoryByInforKey(ArrayList<ActivityHistory> activityHistorys, String inforKey);
    
    ArrayList<ActivityHistory> findActivityHistoryByKey(Date fromTime, Date toTime, String usenameKey, String activityKey, String objectnameKey, String inforKey);
    
    ArrayList<ActivityHistory> findActivityHistoryByTimeKey(String fromTime, String toTime);

    ArrayList<ActivityHistory> findActivityHistoryByUsernameKey(String usenameKey);

    ArrayList<ActivityHistory> findActivityHistoryByActivityKey(String activityKey);

    ArrayList<ActivityHistory> findActivityHistoryByObjectnameKey(String objectnameKey);

    ArrayList<ActivityHistory> findActivityHistoryByInforKey(String inforKey);
    
    ArrayList<ActivityHistory> findActivityHistoryByKey(String fromTime, String toTime, String usenameKey, String activityKey, String objectnameKey, String inforKey);
    
}
