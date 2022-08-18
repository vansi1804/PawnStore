/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.impl;

import DAO.IActivityHistoryDAO;
import DAO.impl.ActivityHistoryDAO;
import Model.ActivityHistory;
import Service.IActivityHistoryService;
import Support.CheckSupport;
import Support.Support;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author NVS
 */
@SuppressWarnings("ClassWithoutLogger")
public class ActivityHistoryService implements IActivityHistoryService {
    
    private final IActivityHistoryDAO activityHistoryDAO = new ActivityHistoryDAO();
    
    @Override
    public ArrayList<ActivityHistory> getList() {
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
    public boolean update(ActivityHistory activityHistory) {
        return activityHistoryDAO.update(activityHistory);
    }
    
    @Override
    public boolean delete(ActivityHistory activityHistory) {
        return activityHistoryDAO.delete(activityHistory);
    }
    
    @Override
    public ArrayList<ActivityHistory> findActivityHistoryByTimeKey(ArrayList<ActivityHistory> activityHistorys,
            Date fromTimeKey, Date toTimeKey) {
        ArrayList<ActivityHistory> results = new ArrayList<>();
        for (ActivityHistory activityHistory : activityHistorys) {
            Date time = Support.stringToDate(activityHistory.getTime(), Support.getDateFormat());
            if (time.compareTo(fromTimeKey) >= 0 && time.compareTo(toTimeKey) <= 0) {
                results.add(activityHistory);
            }
        }
        return results;
    }
    
    @Override
    public ArrayList<ActivityHistory> findActivityHistoryByUsernameKey(ArrayList<ActivityHistory> activityHistorys, String usernameKey) {
        ArrayList<ActivityHistory> results = new ArrayList<>();
        for (ActivityHistory activityHistory : activityHistorys) {
            if (CheckSupport.constains(activityHistory.getAccount().getUsername(), usernameKey)) {
                results.add(activityHistory);
            }
        }
        return results;
    }
    
    @Override
    public ArrayList<ActivityHistory> findActivityHistoryByActivityKey(ArrayList<ActivityHistory> activityHistorys, String activityKey) {
        ArrayList<ActivityHistory> results = new ArrayList<>();
        for (ActivityHistory activityHistory : activityHistorys) {
            if (CheckSupport.constains(activityHistory.getActivity(), activityKey)) {
                results.add(activityHistory);
            }
        }
        return results;
    }
    
    @Override
    public ArrayList<ActivityHistory> findActivityHistoryByObjectnameKey(ArrayList<ActivityHistory> activityHistorys, String objectnameKey) {
        ArrayList<ActivityHistory> results = new ArrayList<>();
        for (ActivityHistory activityHistory : activityHistorys) {
            if (CheckSupport.constains(activityHistory.getObjectname(), objectnameKey)) {
                results.add(activityHistory);
            }
        }
        return results;
    }
    
    @Override
    public ArrayList<ActivityHistory> findActivityHistoryByInforKey(ArrayList<ActivityHistory> activityHistorys, String inforKey) {
        ArrayList<ActivityHistory> results = new ArrayList<>();
        for (ActivityHistory activityHistory : activityHistorys) {
            if (CheckSupport.constains(activityHistory.getInfor(), inforKey)) {
                results.add(activityHistory);
            }
        }
        return results;
    }
    
    @Override
    public ArrayList<ActivityHistory> findActivityHistoryByKey(Date fromTime, Date toTime, String usenameKey, String activityKey, String objectnameKey, String inforKey) {
        ArrayList<ActivityHistory> results = new ArrayList<>();
        for (ActivityHistory activityHistory : activityHistoryDAO.getList()) {
            if (fromTime != null && toTime != null) {
                Date date = Support.stringToDate(activityHistory.getTime(), Support.getDateFormat());
                if (fromTime.compareTo(date) > 0 || toTime.compareTo(date) < 0) {
                    break;
                }
            }
            if (!CheckSupport.isBlank(usenameKey) && !CheckSupport.constains(activityHistory.getAccount().getUsername(), usenameKey)) {
                break;
            }
            if (!CheckSupport.isBlank(activityKey) && !CheckSupport.constains(activityHistory.getActivity(), activityKey)) {
                break;
            }
            if (!CheckSupport.isBlank(objectnameKey) && !CheckSupport.constains(activityHistory.getObjectname(), objectnameKey)) {
                break;
            }
            if (!CheckSupport.isBlank(inforKey) && !CheckSupport.constains(activityHistory.getInfor(), inforKey)) {
                break;
            }
            results.add(activityHistory);
        }
        return results;
    }
    
    @Override
    public ArrayList<ActivityHistory> findActivityHistoryByTimeKey(String fromTime, String toTime) {
        return activityHistoryDAO.findActivityHistoryByTimeKey(fromTime, toTime);
    }
    
    @Override
    public ArrayList<ActivityHistory> findActivityHistoryByUsernameKey(String usenameKey) {
        return activityHistoryDAO.findActivityHistoryByUsernameKey(usenameKey);
    }
    
    @Override
    public ArrayList<ActivityHistory> findActivityHistoryByActivityKey(String activityKey) {
        return activityHistoryDAO.findActivityHistoryByActivityKey(activityKey);
    }
    
    @Override
    public ArrayList<ActivityHistory> findActivityHistoryByObjectnameKey(String objectnameKey) {
        return activityHistoryDAO.findActivityHistoryByObjectnameKey(objectnameKey);
    }
    
    @Override
    public ArrayList<ActivityHistory> findActivityHistoryByInforKey(String inforKey) {
        return activityHistoryDAO.findActivityHistoryByInforKey(inforKey);
    }
    
    @Override
    public ArrayList<ActivityHistory> findActivityHistoryByKey(String fromTime, String toTime,
            String usenameKey, String activityKey, String objectnameKey, String inforKey) {
        return activityHistoryDAO.findActivityHistoryByKey(fromTime, toTime, usenameKey, activityKey, objectnameKey, inforKey);
    }
    
}
