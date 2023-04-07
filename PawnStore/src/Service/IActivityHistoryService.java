/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import Model.ActivityHistory;
import java.util.List;

/**
 *
 * @author NVS
 */
public interface IActivityHistoryService {

    List<ActivityHistory> getList();

    ActivityHistory getActivityHistory(String time);

    boolean insert(ActivityHistory activityHistory);

    List<ActivityHistory> filterByKey(String fromTime, String toTime,
            String usernameKey, String activityKey, String objectNameKey, String infoKey);

}
