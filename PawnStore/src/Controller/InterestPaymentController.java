/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.InterestPayment;
import Service.IInterestPaymentService;
import Service.impl.InterestPaymentService;
import java.util.List;

/**
 *
 * @author NVS
 */
@SuppressWarnings({"ClassMayBeInterface", "ClassWithoutLogger"})
public class InterestPaymentController {

    private static InterestPaymentController instance;

    public static InterestPaymentController getCurrentInstance() {
        if (instance == null) {
            instance = new InterestPaymentController();
        }
        return instance;
    }

    private final IInterestPaymentService interestPaymentService = new InterestPaymentService();

    public List<InterestPayment> findAllByPawnCouponId(String pawnCouponId) {
        return interestPaymentService.findAllByPawnCouponId(pawnCouponId);
    }

    public int countAllByPawnCouponId(String pawnCouponId) {
        return interestPaymentService.countAllByPawnCouponId(pawnCouponId);
    }

    public InterestPayment findOneByPawnCouponIdAndTimes(String pawnCouponId, Integer times) {
        return interestPaymentService.findOneByPawnCouponIdAndTimes(pawnCouponId, times);
    }

    public boolean insert(InterestPayment interestPayment) {
        return interestPaymentService.insert(interestPayment);
    }

    public boolean update(InterestPayment interestPayment) {
        return interestPaymentService.update(interestPayment);
    }

    public boolean delete(InterestPayment interestPayment) {
        return interestPaymentService.delete(interestPayment);
    }

}
