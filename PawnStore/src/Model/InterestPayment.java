/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Support.CheckSupport;

/**
 *
 * @author NVS
 */
@SuppressWarnings("ClassWithoutLogger")
public class InterestPayment {

    private PawnCoupon pawnCoupon;
    private int times;
    private String paymentDate;
    private int money;
    private int debt;
    private String note;

    public InterestPayment() {
    }

    public InterestPayment(PawnCoupon pawnCoupon, int times, String paymentDate, int money, int debt, String note) {
        this.pawnCoupon = pawnCoupon;
        this.times = times;
        this.paymentDate = paymentDate;
        this.money = money;
        this.debt = debt;
        this.note = note;
    }

    public PawnCoupon getPawnCoupon() {
        return pawnCoupon;
    }

    public void setPawnCoupon(PawnCoupon pawnCoupon) {
        this.pawnCoupon = pawnCoupon;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getDebt() {
        return debt;
    }

    public void setDebt(int debt) {
        this.debt = debt;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    
    @Override
    public String toString() {
        return pawnCoupon.getId()
                + " - " + times
                + " - " + paymentDate
                + " - " + money
                + " - " + debt
                + (!CheckSupport.isBlank(note) ? (" - " + note) : "");

    }
}
