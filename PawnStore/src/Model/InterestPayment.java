/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import org.eclipse.persistence.jpa.jpql.parser.DateTime;

/**
 *
 * @author Bau Kien
 */
public class InterestPayment {

    public PawnCoupon getPawnCoupon() {
        return _pawnCoupon;
    }

    public void setPawnCoupon(PawnCoupon _pawnCoupon) {
        this._pawnCoupon = _pawnCoupon;
    }

    public int getTimes() {
        return _times;
    }

    public void setTimes(int _times) {
        this._times = _times;
    }

    public DateTime getPaymentDate() {
        return _paymentDate;
    }

    public void setPaymentDate(DateTime _paymentDate) {
        this._paymentDate = _paymentDate;
    }

    public float getMoney() {
        return _money;
    }

    public void setMoney(float _money) {
        this._money = _money;
    }

    public String getNote() {
        return _note;
    }

    public void setNote(String _note) {
        this._note = _note;
    }

    public InterestPayment(PawnCoupon _pawnCoupon, int _times, DateTime _paymentDate, float _money, String _note) {
        this._pawnCoupon = _pawnCoupon;
        this._times = _times;
        this._paymentDate = _paymentDate;
        this._money = _money;
        this._note = _note;
    }

    public InterestPayment() {
    }

    private PawnCoupon _pawnCoupon;
    private int _times;
    private DateTime _paymentDate;
    private float _money;
    private String _note;
}
