/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Support.Support;

/**
 *
 * @author NVS
 */
@SuppressWarnings("ClassWithoutLogger")
public class PawnCoupon {
    private String id;
    private Customer customer;
    private Product product;
    private int amount;
    private long price;
    private float interestRate;
    private String pawnDate;
    private String theNextInterestPaymentDate;
    private String redemption0rLiquidationDate;
    private long liquidationPrice;
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public float getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(float interestRate) {
        this.interestRate = interestRate;
    }

    public String getPawnDate() {
        return pawnDate;
    }

    public void setPawnDate(String pawnDate) {
        this.pawnDate = pawnDate;
    }

    public String getTheNextInterestPaymentDate() {
        return theNextInterestPaymentDate;
    }

    public void setTheNextInterestPaymentDate(String theNextInterestPaymentDate) {
        this.theNextInterestPaymentDate = theNextInterestPaymentDate;
    }

    public String getRedemption0rLiquidationDate() {
        return redemption0rLiquidationDate;
    }

    public void setRedemption0rLiquidationDate(String redemption0rLiquidationDate) {
        this.redemption0rLiquidationDate = redemption0rLiquidationDate;
    }

    public long getLiquidationPrice() {
        return liquidationPrice;
    }

    public void setLiquidationPrice(long liquidationPrice) {
        this.liquidationPrice = liquidationPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PawnCoupon() {
    }

    public PawnCoupon(String id, String pawnDate) {
        this.id = id;
        this.pawnDate = pawnDate;
    }

    public PawnCoupon(String id, Customer customer, Product product, 
            int amount, long price, float interestRate, 
            String pawnDate, String theNextInterestPaymentDate, String redemption0rLiquidationDate,
            long liquidationPrice, String status) {
        this.id = id;
        this.customer = customer;
        this.product = product;
        this.amount = amount;
        this.price = price;
        this.interestRate = interestRate;
        this.pawnDate = pawnDate;
        this.theNextInterestPaymentDate = theNextInterestPaymentDate;
        this.redemption0rLiquidationDate = redemption0rLiquidationDate;
        this.liquidationPrice = liquidationPrice;
        this.status = status;
    }

    @Override
    public String toString() {
        return id
                + " - " + customer.getId()
                + " - " + product.getId()
                + " - " + Support.getFormatNumber(amount)
                + " - " + Support.getFormatNumber(price)
                + " - " + interestRate
                + " - " + pawnDate
                + " - " + (status.equals("Đã chuộc") ? (redemption0rLiquidationDate + " - " + status)
                : (status.equals("Đã thanh lý") ? (redemption0rLiquidationDate + " - " + liquidationPrice + " - " + status) : status));

    }

}
