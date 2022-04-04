/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

public class BillOfPayment extends Information {

    private Date _setupDate;
    private PawnCoupon _paCoupon;
    private float _interest;
    private float _total;

    public Date getSetupDate() {
        return _setupDate;
    }

    public void setSetupDate(Date _setupDate) {
        this._setupDate = _setupDate;
    }

    public PawnCoupon getPaCoupon() {
        return _paCoupon;
    }

    public void setPaCoupon(PawnCoupon _paCoupon) {
        this._paCoupon = _paCoupon;
    }

    public float getInterest() {
        return _interest;
    }

    public void setInterest(float _interest) {
        this._interest = _interest;
    }

    public float getTotal() {
        return _total;
    }

    public void setTotal(float _total) {
        this._total = _total;
    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public BillOfPayment() {
    }

    public BillOfPayment(String _id, PawnCoupon _paCoupon, Date _setupDate, float _interest, float _total) {
        super(_id, null);
        this._setupDate = _setupDate;
        this._paCoupon = _paCoupon;
        this._interest = _interest;
        this._total = _total;
    }

}
