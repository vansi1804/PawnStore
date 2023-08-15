/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.impl;

import Common.Default;
import DAO.IActivityHistoryDAO;
import Mapper.impl.ActivityHistoryMapper;
import Model.ActivityHistory;
import Support.CheckSupport;
import java.util.List;

/**
 *
 * @author NVS
 */
@SuppressWarnings("ClassWithoutLogger")
public class ActivityHistoryDAO extends ADAO<ActivityHistory> implements IActivityHistoryDAO {

    private static final String SELECTQUERY = "Select time, username, activity, object_name, info From activity_history";
    private static final String INSERTQUERY = "Insert Into activity_history(time, username, activity, object_name, info) Values(?,?,?,?,?)";
    private static final String ORDER_BY = " Order By time";

    @Override
    public List<ActivityHistory> getList() {
        String query = SELECTQUERY + ORDER_BY;
        return findAll(query, new ActivityHistoryMapper());
    }

    @Override
    public ActivityHistory getActivityHistory(String time) {
        String query = SELECTQUERY
                + " Where Convert(datetime,time,105) = Convert(datetime,?,105)";
        return findOne(query, new ActivityHistoryMapper(), time);
    }

    @Override
    public boolean insert(ActivityHistory activityHistory) {
        return insert(INSERTQUERY, activityHistory.getTime(), activityHistory.getAccount().getUsername(),
                activityHistory.getActivity(), activityHistory.getObjectName(), activityHistory.getInfor());
    }

    @Override
    public List<ActivityHistory> filterByKey(String fromTime, String toTime, String usernameKey,
            String activityKey, String objectNameKey, String infoKey) {
        String query = SELECTQUERY
                + " Where 1 = 1" // for Where clause always true if all key is null
                + ((CheckSupport.isNullOrBlank(fromTime) && CheckSupport.isNullOrBlank(toTime))
                ? ""
                : (!CheckSupport.isNullOrBlank(fromTime) && CheckSupport.isNullOrBlank(toTime))
                ? " And (STR_TO_DATE(time, '%d-%m-%Y %H:%i:%s') "
                + " >= STR_TO_DATE('" + fromTime + Default.MIN_TIME_OF_DATE + "', '%d-%m-%Y %H:%i:%s'))"
                : (CheckSupport.isNullOrBlank(fromTime) && !CheckSupport.isNullOrBlank(toTime))
                ? " And (STR_TO_DATE(time, '%d-%m-%Y %H:%i:%s')"
                + " <= STR_TO_DATE('" + toTime + Default.MAX_TIME_OF_DATE + "', '%d-%m-%Y %H:%i:%s'))"
                : " And (STR_TO_DATE(time, '%d-%m-%Y %H:%i:%s')"
                + "      Between STR_TO_DATE('" + fromTime + Default.MIN_TIME_OF_DATE + "', '%d-%m-%Y %H:%i:%s')"
                + "      And STR_TO_DATE('" + toTime + Default.MAX_TIME_OF_DATE + "', '%d-%m-%Y %H:%i:%s'))")
                + (CheckSupport.isNullOrBlank(usernameKey) ? "" : " And (username Like N'%" + usernameKey + "%')")
                + (CheckSupport.isNullOrBlank(activityKey) ? "" : " And (activity Like N'%" + activityKey + "%')")
                + (CheckSupport.isNullOrBlank(objectNameKey) ? "" : " And (object_name Like N'%" + objectNameKey + "%')")
                + (CheckSupport.isNullOrBlank(infoKey) ? "" : " And (info Like N'%" + infoKey + "%')")
                + ORDER_BY;
        return findAll(query, new ActivityHistoryMapper());
    }
}
