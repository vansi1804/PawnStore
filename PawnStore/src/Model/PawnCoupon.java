/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;


/**
 *
 * @author Bau Kien
 */
public class PawnCoupon {

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public Date getPawnDate() {
        return _pawnDate;
    }

    public void setPawnDate(Date _pawnDate) {
        this._pawnDate = _pawnDate;
    }

    public Customer getCustomer() {
        return _customer;
    }

    public void setCustomer(Customer _customer) {
        this._customer = _customer;
    }

    public Product getProuct() {
        return _prouct;
    }

    public void setProuct(Product _prouct) {
        this._prouct = _prouct;
    }

    public int getAmount() {
        return _amount;
    }

    public void setAmount(int _amount) {
        this._amount = _amount;
    }

    public float getPrice() {
        return _price;
    }

    public void setPrice(float _price) {
        this._price = _price;
    }

    public float getInterestRate() {
        return _interestRate;
    }

    public void setInterestRate(float _interestRate) {
        this._interestRate = _interestRate;
    }

    public Date getRansomDate() {
        return _ransomDate;
    }

    public void setRansomDate(Date _ransomDate) {
        this._ransomDate = _ransomDate;
    }

    public User getUser() {
        return _user;
    }

    public void setUser(User _user) {
        this._user = _user;
    }

    public PawnCoupon() {
    }

    public PawnCoupon(String _id, Date _pawnDate, Customer _customer, Product _prouct, int _amount, float _price, float _interestRate, Date _ransomDate, User _user) {
        this._id = _id;
        this._pawnDate = _pawnDate;
        this._customer = _customer;
        this._prouct = _prouct;
        this._amount = _amount;
        this._price = _price;
        this._interestRate = _interestRate;
        this._ransomDate = _ransomDate;
        this._user = _user;
    }

    private String _id;
    private Date _pawnDate;
    private Customer _customer;
    private Product _prouct;
    private int _amount;
    private float _price;
    private float _interestRate;
    private Date _ransomDate;
    private User _user;
}
