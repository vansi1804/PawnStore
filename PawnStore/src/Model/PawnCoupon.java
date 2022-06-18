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

    public String getPawnDate() {
        return _pawnDate;
    }

    public void setPawnDate(String _pawnDate) {
        this._pawnDate = _pawnDate;
    }

    public Customer getCustomer() {
        return _customer;
    }

    public void setCustomer(Customer _customer) {
        this._customer = _customer;
    }

    public Product getProduct() {
        return _product;
    }

    public void setProduct(Product _prouct) {
        this._product = _prouct;
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

    public String getRedeemingDate() {
        return _redeemingDate;
    }

    public void setRedeemingDate(String _ransomDate) {
        this._redeemingDate = _ransomDate;
    }

    public User getUser() {
        return _user;
    }

    public void setUser(User _user) {
        this._user = _user;
    }
    
    public String getStatus() {
        return _status;
    }

    public void setStatus(String _status) {
        this._status = _status;
    }

    public PawnCoupon() {
    }

    public PawnCoupon(String _id, String _pawnDate, Customer _customer, Product _prouct, int _amount, float _price, float _interestRate, String _ransomDate, String _status, User _user) {
        this._id = _id;
        this._pawnDate = _pawnDate;
        this._customer = _customer;
        this._product = _prouct;
        this._amount = _amount;
        this._price = _price;
        this._interestRate = _interestRate;
        this._redeemingDate = _ransomDate;
        this._status = _status;
        this._user = _user;
    }

    private String _id;
    private String _pawnDate;
    private Customer _customer;
    private Product _product;
    private int _amount;
    private float _price;
    private float _interestRate;
    private String _redeemingDate;
    private String _status;
    private User _user;
}
