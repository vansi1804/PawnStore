/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Customer;
import Model.PawnCoupon;
import Service.IPawnCouponService;
import Service.impl.PawnCouponService;
import java.util.List;

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

    public List<PawnCoupon> findAll() {
        return pawnCouponService.findAll();
    }

    public List<PawnCoupon> findAllByStatus(String status) {
        return pawnCouponService.findAllByStatus(status);
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

    public String getNewID() {
        return pawnCouponService.getNewID();
    }

    public String getTheNextPaymentDate(PawnCoupon pawnCoupon) {
        return pawnCouponService.getTheNextPaymentDate(pawnCoupon);
    }

    public List<PawnCoupon> findPawnCouponByCustomerKey(Customer customer) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
