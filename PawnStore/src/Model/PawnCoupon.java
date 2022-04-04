/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

public class PawnCoupon extends Information {

    public Date getSetupDate() {
        return _setupDate;
    }

    public void setSetupDate(Date _setupDate) {
        this._setupDate = _setupDate;
    }

    public float getMoney() {
        return _money;
    }

    public void setMoney(float _money) {
        this._money = _money;
    }

    public boolean isStatus() {
        return _status;
    }

    public void setStatus(boolean _status) {
        this._status = _status;
    }

    public Customer getCustomer() {
        return _customer;
    }

    public void setCustomer(Customer _customer) {
        this._customer = _customer;
    }

    public Account getAccount() {
        return _account;
    }

    public void setAccount(Account _account) {
        this._account = _account;
    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getName() {
        return _name;
    }

    private Date _setupDate;
    private float _money;
    private boolean _status;
    private Customer _customer;
    private Account _account;

    public PawnCoupon() {
        super();
    }

    public PawnCoupon(Date _setupDate, float _money, boolean _status, Customer _customer, Account _account, String _id, String _name) {
        super(_id, _name);
        this._setupDate = _setupDate;
        this._money = _money;
        this._status = _status;
        this._customer = _customer;
        this._account = _account;
    }
}
