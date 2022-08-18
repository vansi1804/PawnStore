/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Model.ActivityHistory;
import java.util.ArrayList;

/**
 *
 * @author NVS
 */
public interface IActivityHistoryDAO extends IGenericDAO<ActivityHistory> {

    ArrayList<ActivityHistory> getList();

    ActivityHistory getActivityHistory(String time);

    boolean insert(ActivityHistory activityHistory);

    boolean update(ActivityHistory activityHistory);

    boolean delete(ActivityHistory activityHistory);

    ArrayList<ActivityHistory> findActivityHistoryByTimeKey(String fromTime, String toTime);

    ArrayList<ActivityHistory> findActivityHistoryByUsernameKey(String usenameKey);

    ArrayList<ActivityHistory> findActivityHistoryByActivityKey(String activityKey);

    ArrayList<ActivityHistory> findActivityHistoryByObjectnameKey(String objectnameKey);

    ArrayList<ActivityHistory> findActivityHistoryByInforKey(String inforKey);
    
    ArrayList<ActivityHistory> findActivityHistoryByKey(String fromTime, String toTime, String usenameKey, String activityKey, String objectnameKey, String inforKey);
    
    
}
