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

    public PawnCoupon findOneById(String id) {
        return pawnCouponService.findOneById(id);
    }

    public boolean insert(PawnCoupon pawnCoupon) {
        return pawnCouponService.insert(pawnCoupon);
    }

    public boolean update(PawnCoupon pawnCoupon) {
        return pawnCouponService.update(pawnCoupon);
    }

    public String createNewId() {
        return pawnCouponService.createNewId();
    }

    public List<PawnCoupon> findAllByCustomerId(String customerId) {
        return pawnCouponService.findAllByCustomerId(customerId);
    }

    public List<PawnCoupon> filter(String id, String customerId, String productId,
            Long pawnPrice, Float interestRate, Integer amount,
            String pawnDate, String theNextInterestPaymentDate, String redemptionOrLiquidationDate,
            Long liquidationPrice, String status) {
        return pawnCouponService.filter(id, customerId, productId,
                pawnPrice, interestRate, amount, 
                pawnDate, theNextInterestPaymentDate, redemptionOrLiquidationDate,
                liquidationPrice, status);
    }
}
