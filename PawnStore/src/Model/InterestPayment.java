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
    private long moneyPaid;
    private long newDebt;
    private String note;

    public InterestPayment() {
    }

    public InterestPayment(PawnCoupon pawnCoupon, int times, String paymentDate, long money, long debt, String note) {
        this.pawnCoupon = pawnCoupon;
        this.times = times;
        this.paymentDate = paymentDate;
        this.moneyPaid = money;
        this.newDebt = debt;
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

    public long getMoneyPaid() {
        return moneyPaid;
    }

    public void setMoneyPaid(long moneyPaid) {
        this.moneyPaid = moneyPaid;
    }

    public long getNewDebt() {
        return newDebt;
    }

    public void setNewDebt(long newDebt) {
        this.newDebt = newDebt;
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
                + " - " + moneyPaid
                + " - " + newDebt
                + (!CheckSupport.isNullOrBlank(note) ? (" - " + note) : "");

    }
}
