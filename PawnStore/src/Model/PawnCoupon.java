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
    private int price;
    private float interestRate;
    private String pawnDate;
    private String redeem0rLiquidationDate;
    private int liquidationPrice;
    private String status;

    public PawnCoupon() {
    }

    public PawnCoupon(String id, Customer customer, Product product, int amount, int price, float interestRate, String pawnDate, String redeem0rLiquidationDate, int liquidationPrice, String status) {
        this.id = id;
        this.customer = customer;
        this.product = product;
        this.amount = amount;
        this.price = price;
        this.interestRate = interestRate;
        this.pawnDate = pawnDate;
        this.redeem0rLiquidationDate = redeem0rLiquidationDate;
        this.liquidationPrice = liquidationPrice;
        this.status = status;
    }

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
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

    public String getRedeem0rLiquidationDate() {
        return redeem0rLiquidationDate;
    }

    public void setRedeem0rLiquidationDate(String redeem0rLiquidationDate) {
        this.redeem0rLiquidationDate = redeem0rLiquidationDate;
    }

    public int getLiquidationPrice() {
        return liquidationPrice;
    }

    public void setLiquidationPrice(int liquidationPrice) {
        this.liquidationPrice = liquidationPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
                + " - " + (status.equals("Đã chuộc") ? (redeem0rLiquidationDate + " - " + status) 
                : (status.equals("Đã thanh lý") ? (redeem0rLiquidationDate + " - " + liquidationPrice + " - " + status) : status));

    }

}
