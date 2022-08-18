/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import Model.InterestPayment;
import Model.PawnCoupon;
import java.util.ArrayList;

/**
 *
 * @author NVS
 */
public interface IInterestPaymentService {
    ArrayList<InterestPayment> getList(PawnCoupon pawnCoupon);

    InterestPayment getInterestPayment(PawnCoupon pawnCoupon, String times);

    boolean insert(InterestPayment interestPayment);

    boolean update(InterestPayment interestPayment);

    boolean delete(InterestPayment interestPayment);
}
