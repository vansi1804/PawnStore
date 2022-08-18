/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.impl;

import DAO.IActivityHistoryDAO;
import Mapper.impl.ActivityHistoryMapper;
import Model.ActivityHistory;
import Support.CheckSupport;
import Support.EncodingSupport;
import java.util.ArrayList;

/**
 *
 * @author NVS
 */
@SuppressWarnings("ClassWithoutLogger")
public class ActivityHistoryDAO extends ADAO<ActivityHistory> implements IActivityHistoryDAO {

    private static final String SELECTQUERY = " Select ActivityHistory._time"
            + ", Account._username, Account._password, Account._fullname, Account._deleteflag"
            + ", ActivityHistory._activity, ActivityHistory._objectname, ActivityHistory._infor"
            + " From ActivityHistory Inner Join Account On ActivityHistory._username = Account._username";
    private static final String INSERTQUERY = "Insert Into ActivityHistory(_time, _username, _activity, _objectname, _infor) Values(?,?,?,?,?)";
    private static final String UPDATEQUERY = "Update ActivityHistory Set _username = ?, _activity = ? , _objectname = ?, _infor = ? Where _time = ?";
    private static final String DELETEQUERY = "Delete From ActivityHistory Where _time = ?";

    @Override
    public ArrayList<ActivityHistory> getList() {
        String query = SELECTQUERY + " Order By Convert(datetime,ActivityHistory._time,105)";
        return getList(query, new ActivityHistoryMapper());
    }

    @Override
    public ActivityHistory getActivityHistory(String time) {
        String query = SELECTQUERY
                + " Where Convert(datetime,ActivityHistory._time,105) = Convert(datetime,?,105)"
                + " Order By Convert(datetime,ActivityHistory._time,105)";
        return getObject(query, new ActivityHistoryMapper(), time);
    }

    @Override
    public boolean insert(ActivityHistory activityHistory) {
        return insert(INSERTQUERY, activityHistory.getTime(), activityHistory.getAccount().getUsername(),
                activityHistory.getActivity(), activityHistory.getObjectname(), activityHistory.getInfor());
    }

    @Override
    public boolean update(ActivityHistory activityHistory) {
        return update(UPDATEQUERY, EncodingSupport.encrypt(activityHistory.getAccount().getUsername()), activityHistory.getActivity(),
                activityHistory.getObjectname(), activityHistory.getInfor(), activityHistory.getTime());
    }

    @Override
    public boolean delete(ActivityHistory activityHistory) {
        return delete(DELETEQUERY, activityHistory.getTime());
    }

    @Override
    @SuppressWarnings("AssignmentToMethodParameter")
    public ArrayList<ActivityHistory> findActivityHistoryByTimeKey(String fromTime, String toTime) {
        String query = SELECTQUERY
                + " Where Convert(datetime,ActivityHistory._time,105) Between Convert(datetime,? 00:00:00,105) And Convert(datetime,? 23:59:59,105)"
                + " Order By Convert(datetime,ActivityHistory._time,105)";
        return getList(query, new ActivityHistoryMapper(), fromTime, toTime);
    }

    @Override
    public ArrayList<ActivityHistory> findActivityHistoryByUsernameKey(String usenameKey) {
        String query = SELECTQUERY
                + " Where ActivityHistory._username like N'%" + usenameKey + "%' "
                + " Order By Convert(datetime,ActivityHistory._time,105)";
        return getList(query, new ActivityHistoryMapper());
    }

    @Override
    public ArrayList<ActivityHistory> findActivityHistoryByActivityKey(String activityKey) {
        String query = SELECTQUERY
                + " Where ActivityHistory._activity like N'%" + activityKey + "%' "
                + " Order By Convert(datetime,ActivityHistory._time,105)";
        return getList(query, new ActivityHistoryMapper());
    }

    @Override
    public ArrayList<ActivityHistory> findActivityHistoryByObjectnameKey(String objectnameKey) {
        String query = SELECTQUERY
                + " Where ActivityHistory._objectname like N'%" + objectnameKey + "%' "
                + " Order By Convert(datetime,ActivityHistory._time,105)";
        return getList(query, new ActivityHistoryMapper());
    }

    @Override
    public ArrayList<ActivityHistory> findActivityHistoryByInforKey(String inforKey) {
        String query = SELECTQUERY
                + " Where ActivityHistory._infor like N'%" + inforKey + "%' "
                + " Order By Convert(datetime,ActivityHistory._time,105)";
        return getList(query, new ActivityHistoryMapper());
    }

    @Override
    @SuppressWarnings({"AssignmentToMethodParameter", "UnusedAssignment"})
    public ArrayList<ActivityHistory> findActivityHistoryByKey(String fromTime, String toTime,
            String usenameKey, String activityKey, String objectnameKey, String inforKey) {
        String query = SELECTQUERY;

        boolean isTimeKeyEmpty = CheckSupport.isBlank(fromTime) || CheckSupport.isBlank(toTime);
        boolean isUsernameKeyEmpty = CheckSupport.isBlank(usenameKey);
        boolean isActivityKeyEmpty = CheckSupport.isBlank(activityKey);
        boolean isObjectnameKeyEmpty = CheckSupport.isBlank(objectnameKey);
        boolean isInforKeyEmpty = CheckSupport.isBlank(inforKey);

        if (!isTimeKeyEmpty || !isUsernameKeyEmpty || !isActivityKeyEmpty || !isObjectnameKeyEmpty || !isInforKeyEmpty) {
            query += " Where ";
        }
        if (!isTimeKeyEmpty) {
            query += " Convert(datetime,ActivityHistory._time,105) "
                    + " Between Convert(datetime,'" + fromTime + " 00:00:00',105) And Convert(datetime,'" + toTime + " 23:59:59',105) ";
        }
        if (!isTimeKeyEmpty && (!isUsernameKeyEmpty || !isActivityKeyEmpty || !isObjectnameKeyEmpty || !isInforKeyEmpty)) {
            query += " And ";
        }
        if (!isUsernameKeyEmpty) {
            query += " ActivityHistory._username like N'%" + usenameKey + "%' ";
        }
        if (!isUsernameKeyEmpty && (!isActivityKeyEmpty || !isObjectnameKeyEmpty || !isInforKeyEmpty)) {
            query += " And ";
        }
        if (!isActivityKeyEmpty) {
            query += " ActivityHistory._activity like N'%" + activityKey + "%' ";
        }
        if (!isActivityKeyEmpty && (!isObjectnameKeyEmpty || !isInforKeyEmpty)) {
            query += " And ";
        }
        if (!isObjectnameKeyEmpty) {
            query += " ActivityHistory._objectname like N'%" + objectnameKey + "%' ";
        }
        if (!isObjectnameKeyEmpty && !isInforKeyEmpty) {
            query += " And ";
        }
        if (!isInforKeyEmpty) {
            query += " ActivityHistory._infor like N'%" + inforKey + "%' ";
        }
        query += " Order By Convert(datetime,ActivityHistory._time,105)";
        return getList(query, new ActivityHistoryMapper());
    }
}
