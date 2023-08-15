/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Model.InterestPayment;
import java.util.List;

/**
 *
 * @author NVS
 */
@SuppressWarnings("MarkerInterface")
public interface IInterestPaymentDAO extends IGenericDAO<InterestPayment> {

    List<InterestPayment> findAllByPawnCouponId(String pawnCouponId);

    InterestPayment findOneByPawnCouponIdAndTimes(String pawnCouponId, Integer times);

    boolean insert(InterestPayment interestPayment);

    boolean update(InterestPayment interestPayment);

    boolean delete(InterestPayment interestPayment);

    int countAllByPawnCouponId(String pawnCouponId);

    public InterestPayment findLast(String id);

}
