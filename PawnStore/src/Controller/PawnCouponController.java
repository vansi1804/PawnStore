/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Customer;
import Model.PawnCoupon;
import Model.Product;
import Service.IPawnCouponService;
import Service.impl.PawnCouponService;
import java.util.ArrayList;

/**
 *
 * @author NVS
 */
@SuppressWarnings({"ClassMayBeInterface", "ClassWithoutLogger"})
public class PawnCouponController {

    private static PawnCouponController instance;

    public static PawnCouponController getCurrentInstance() {
        if (instance == null) {
            instance = new PawnCouponController();
        }
        return instance;
    }

    private final IPawnCouponService pawnCouponService = new PawnCouponService();

    public ArrayList<PawnCoupon> getList() {
        return pawnCouponService.getList();
    }

    public PawnCoupon getPawnCoupon(String id) {
        return pawnCouponService.getPawnCoupon(id);
    }

    public boolean insert(PawnCoupon pawnCoupon) {
        return pawnCouponService.insert(pawnCoupon);
    }

    public boolean update(PawnCoupon pawnCoupon) {
        return pawnCouponService.update(pawnCoupon);
    }

    public boolean delete(PawnCoupon pawnCoupon) {
        return pawnCouponService.delete(pawnCoupon);
    }

    public ArrayList<PawnCoupon> findPawnCouponByCustomerKey(Customer customer) {
        return pawnCouponService.findPawnCouponByCustomerKey(customer);
    }
    
    public ArrayList<PawnCoupon> findPawnCouponByStatusKey(String statusKey) {
        return pawnCouponService.findPawnCouponByStatusKey(statusKey);
    }

    public ArrayList<PawnCoupon> findPawnCouponByTime(String dateFrom, String dateTo){
        return pawnCouponService.findPawnCouponByTime(dateFrom, dateTo);
    }
    
    
    public ArrayList<PawnCoupon> findPawnCouponByKey(String idKey, Customer customer, Product productKey,
            int amountKey, int priceKey, float interestRateKey, String pawnDate, String redeemDate, String status) {
        return pawnCouponService.findPawnCouponByKey(idKey, customer, productKey,
                amountKey, priceKey, interestRateKey, pawnDate, redeemDate, status);
    }

    public String getNewID() {
        return pawnCouponService.getNewID();
    }

    public String getTheNextPaymentDate(PawnCoupon pawnCoupon) {
        return pawnCouponService.getTheNextPaymentDate(pawnCoupon);
    }
}
