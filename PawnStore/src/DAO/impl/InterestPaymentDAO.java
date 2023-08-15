/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.impl;

import DAO.IInterestPaymentDAO;
import Mapper.impl.InterestPaymentMapper;
import Model.InterestPayment;
import java.util.List;

/**
 *
 * @author NVS
 */
@SuppressWarnings("ClassWithoutLogger")
public class InterestPaymentDAO extends ADAO<InterestPayment> implements IInterestPaymentDAO {

    private static final String SELECTQUERY = "Select pawn_coupon.id"
            + ", customer.id, customer.fullname, customer.gender, customer.phone_number, customer.address, customer.delete_flag"
            + ", product.id"
            + ", type_of_product.id, type_of_product.name, type_of_product.delete_flag"
            + ", product.name, product.info, product.status"
            + ", pawn_coupon.amount, pawn_coupon.price, pawn_coupon.interest_rate"
            + ", pawn_coupon.pawn_date, pawn_coupon.the_next_interest_payment_date"
            + ", pawn_coupon.redemption_or_liquidation_date, pawn_coupon.liquidation_price, pawn_coupon.status"
            + ", interest_payment.times, interest_payment.payment_date, interest_payment.money_paid"
            + ", interest_payment.new_debt, interest_payment.note"
            + " From interest_payment"
            + " Inner Join pawn_coupon On interest_payment.pawn_coupon_id = pawn_coupon.id "
            + " Inner Join customer On pawn_coupon.customer_id = customer.id"
            + " Inner Join product On pawn_coupon.product_id = product.id"
            + " Inner Join type_of_product On product.type_id = type_of_product.id";

    private static final String INSERTQUERY = "Insert Into interest_payment(pawn_coupon_id, times, payment_date,  money_paid, new_debt, note)"
            + " Values(?,?,?,?,?,?)";

    private static final String UPDATEQUERY = "Update interest_payment Set pawn_coupon_id = ?, times = ?, payment_date = ?"
            + ", money_paid = ?, new_debt = ?, note = ?";

    private static final String DELETEQUERY = "Delete from interest_payment Where pawn_coupon_id = ? And times = ?";

    private static final String COUNTQUERY = "Select Count(times) from interest_payment Where pawn_coupon_id = ?";

    private static final String ORDER_BY = " Order By interest_payment.times DESC";

    @Override
    public List<InterestPayment> findAllByPawnCouponId(String pawnCouponId) {
        String query = SELECTQUERY + " Where interest_payment.pawn_coupon_id = ?" + ORDER_BY;
        return findAll(query, new InterestPaymentMapper(), pawnCouponId);
    }

    @Override
    public InterestPayment findOneByPawnCouponIdAndTimes(String pawnCouponId, Integer times) {
        String query = SELECTQUERY
                + " Where interest_payment.pawn_coupon_id = N'" + pawnCouponId + "'"
                + " And interest_payment.times = "
                + (times != null ? times
                        : " (Select Max(interest_payment.times)"
                        + "  From InterestPayment"
                        + "  Where interest_payment.pawn_coupon_id = N'" + pawnCouponId + "')");
        return findOne(query, new InterestPaymentMapper());
    }

    @Override
    public boolean insert(InterestPayment interestPayment) {
        return insert(INSERTQUERY, interestPayment.getPawnCoupon().getId(), interestPayment.getTimes(),
                interestPayment.getPaymentDate(), interestPayment.getMoneyPaid(),
                interestPayment.getNewDebt(), interestPayment.getNote());
    }

    @Override
    public boolean update(InterestPayment interestPayment) {
        return update(UPDATEQUERY, interestPayment.getPawnCoupon().getId(), interestPayment.getTimes(),
                interestPayment.getPaymentDate(), interestPayment.getMoneyPaid(),
                interestPayment.getNewDebt(), interestPayment.getNote());
    }

    @Override
    public boolean delete(InterestPayment interestPayment) {
        return delete(DELETEQUERY, interestPayment.getPawnCoupon().getId(), interestPayment.getTimes());
    }

    @Override
    public int countAllByPawnCouponId(String pawnCouponId) {
        return count(COUNTQUERY, pawnCouponId);
    }

    @Override
    public InterestPayment findLast(String id) {
        String query = SELECTQUERY + ORDER_BY + " Limit 1";
        return findOne(query, new InterestPaymentMapper());
    }

}
