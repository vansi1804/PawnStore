/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Model.InterestPayment;
import Model.PawnCoupon;
import java.util.List;

/**
 *
 * @author NVS
 */
@SuppressWarnings("MarkerInterface")
public interface IInterestPaymentDAO extends IGenericDAO<InterestPayment> {

    List<InterestPayment> getList(PawnCoupon pawnCoupon);

    InterestPayment getInterestPayment(PawnCoupon pawnCoupon, String times);

    boolean insert(InterestPayment interestPayment);

    boolean update(InterestPayment interestPayment);

    boolean delete(InterestPayment interestPayment);

}
