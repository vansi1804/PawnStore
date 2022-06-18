/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author NVS
 */
public class ActivityHistory {

    public ActivityHistory() {
    }

    public ActivityHistory(String _activeTime, Account _account, String _active) {
        this._activeTime = _activeTime;
        this._account = _account;
        this._active = _active;
    }

    public String getActiveTime() {
        return _activeTime;
    }

    public void setActiveTime(String _activeTime) {
        this._activeTime = _activeTime;
    }

    public Account getAccount() {
        return _account;
    }

    public void setAccount(Account _account) {
        this._account = _account;
    }

    public String getActive() {
        return _active;
    }

    public void setActive(String _active) {
        this._active = _active;
    }
    private String _activeTime;
    private Account _account;
    private String _active;
}
