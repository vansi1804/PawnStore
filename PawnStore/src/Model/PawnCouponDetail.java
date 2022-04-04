/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

public class PawnCouponDetail {

    public PawnCoupon getCoupon() {
        return _coupon;
    }

    public void setCoupon(PawnCoupon _coupon) {
        this._coupon = _coupon;
    }

    public Product getProduct() {
        return _product;
    }

    public void setProduct(Product _product) {
        this._product = _product;
    }

    public int getAmount() {
        return _amount;
    }

    public void setAmount(int _amount) {
        this._amount = _amount;
    }

    public float getMoney() {
        return _money;
    }

    public void setMoney(float _money) {
        this._money = _money;
    }

    public Date getInteresrPaymentDate() {
        return _interesrPaymentDate;
    }

    public void setInteresrPaymentDate(Date _interesrPaymentDate) {
        this._interesrPaymentDate = _interesrPaymentDate;
    }

    public PawnCouponDetail(PawnCoupon _coupon, Product _product, int _amount, float _money, Date _interesrPaymentDate) {
        this._coupon = _coupon;
        this._product = _product;
        this._amount = _amount;
        this._money = _money;
        this._interesrPaymentDate = _interesrPaymentDate;
    }

    public PawnCouponDetail() {
    }
    
    private PawnCoupon _coupon;
    private Product _product;
    private int _amount;
    private float _money;
    private Date _interesrPaymentDate;
    
}
