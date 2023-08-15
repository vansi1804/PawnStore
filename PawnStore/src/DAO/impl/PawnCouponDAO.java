/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.impl;

import DAO.IPawnCouponDAO;
import Mapper.impl.PawnCouponMapper;
import Model.PawnCoupon;
import Support.CheckSupport;
import java.util.List;

/**
 *
 * @author NVS
 */
@SuppressWarnings({"ClassMayBeInterface", "ClassWithoutLogger"})
public class PawnCouponDAO extends ADAO<PawnCoupon> implements IPawnCouponDAO {

    private static final String SELECTQUERY = "Select pawn_coupon.id"
            + ", customer.id, customer.fullname, customer.gender, customer.phone_number, customer.address, customer.delete_flag"
            + ", product.id"
            + ", type_of_product.id, type_of_product.name, type_of_product.delete_flag"
            + ", product.name, product.info, product.status"
            + ", pawn_coupon.amount, pawn_coupon.price, pawn_coupon.interest_rate"
            + ", pawn_coupon.pawn_date, pawn_coupon.the_next_interest_payment_date"
            + ", pawn_coupon.redemption_or_liquidation_date, pawn_coupon.liquidation_price, pawn_coupon.status"
            + " From pawn_coupon Inner Join customer On pawn_coupon.customer_id = customer.id"
            + " Inner Join product On pawn_coupon.product_id = product.id"
            + " Inner Join type_of_product On product.type_id = type_of_product.id";
    private static final String INSERTQUERY = "Insert Into pawn_coupon("
            + " id, customer_id, product_id, amount, price, interest_rate, pawn_date, the_next_interest_payment_date, status)"
            + " Values(?,?,?,?,?,?,?,?,?)";
    private static final String UPDATEQUERY = "Update pawn_coupon"
            + " Set customer_id = ?, product_id = ?"
            + ", amount = ?, price = ?, interest_rate = ?"
            + ", the_next_interest_payment_date = ?, redemption_or_liquidation_date = ?"
            + ", liquidation_price = ?, status = ?"
            + " Where id = ?";
    private static final String ORDER_BY = " Order By pawn_coupon.id DESC";

    @Override
    public List<PawnCoupon> findAll() {
        String query = SELECTQUERY + ORDER_BY;
        return findAll(query, new PawnCouponMapper());
    }

    @Override
    public List<PawnCoupon> findAllByStatus(String status) {
        String query = SELECTQUERY
                + (status == null ? "" : " Where pawn_coupon.status = ?" + ORDER_BY);
        return findAll(query, new PawnCouponMapper(), status);
    }

    @Override
    public PawnCoupon findOneById(String id) {
        String query = SELECTQUERY + " Where pawn_coupon.id = ?";
        return findOne(query, new PawnCouponMapper(), id);
    }

    @Override
    public boolean insert(PawnCoupon pawnCoupon) {
        return insert(INSERTQUERY,
                pawnCoupon.getId(), pawnCoupon.getCustomer().getId(), pawnCoupon.getProduct().getId(),
                pawnCoupon.getAmount(), pawnCoupon.getPrice(), pawnCoupon.getInterestRate(),
                pawnCoupon.getPawnDate(), pawnCoupon.getTheNextInterestPaymentDate(), pawnCoupon.getStatus());
    }

    @Override
    public boolean update(PawnCoupon pawnCoupon) {
        return update(UPDATEQUERY,
                pawnCoupon.getCustomer().getId(), pawnCoupon.getProduct().getId(),
                pawnCoupon.getAmount(), pawnCoupon.getPrice(), pawnCoupon.getInterestRate(),
                pawnCoupon.getTheNextInterestPaymentDate(), pawnCoupon.getRedemption0rLiquidationDate(),
                pawnCoupon.getLiquidationPrice(), pawnCoupon.getStatus(), pawnCoupon.getId());
    }

    @Override
    public PawnCoupon findLastest() {
        String query = SELECTQUERY + " Where pawn_coupon.id = (Select Max(id) from pawn_coupon)";
        return findOne(query, new PawnCouponMapper());
    }

    @Override
    public List<PawnCoupon> filter(String id, String customerId, String productId,
            Long pawnPrice, Float interestRate, Integer amount,
            String pawnDate, String theNextInterestPaymentDate, String redemptionOrLiquidationDate,
            Long liquidationPrice, String status) {
        String query = SELECTQUERY
                + " Where 1 = 1"
                + (CheckSupport.isNullOrBlank(id) ? "" : " And pawn_coupon.id Like N'" + id + "'")
                + (CheckSupport.isNullOrBlank(customerId) ? "" : " And pawn_coupon.customer_id = N'" + customerId + "'")
                + (CheckSupport.isNullOrBlank(productId) ? "" : " And pawn_coupon.product_id = N'" + productId + "'")
                + (pawnPrice == null ? "" : " And pawn_coupon.price = " + pawnPrice)
                + (interestRate == null ? "" : " And pawn_coupon.interest_rate = " + interestRate)
                + (amount == null ? "" : " And pawn_coupon.amount = " + amount)
                + (CheckSupport.isNullOrBlank(pawnDate) ? ""
                : " And pawn_coupon.pawn_date = '" + pawnDate + "'")
                + (CheckSupport.isNullOrBlank(theNextInterestPaymentDate) ? ""
                : " And pawn_coupon.the_next_interest_payment_date = '" + theNextInterestPaymentDate + "'")
                + (CheckSupport.isNullOrBlank(redemptionOrLiquidationDate) ? ""
                : " And pawn_coupon.redemption_or_liquidation_date = '" + redemptionOrLiquidationDate + "'")
                + (liquidationPrice == null ? "" : " And pawn_coupon.liquidation_price = " + liquidationPrice)
                + (status == null ? "" : " And pawn_coupon.status = N'" + status + "'")
                + ORDER_BY;
        return findAll(query, new PawnCouponMapper());
    }

    @Override
    public List<PawnCoupon> findAllByCustomerId(String customerId) {
        String query = SELECTQUERY + " Where pawn_coupon.customer_id = ?" + ORDER_BY;
        return findAll(query, new PawnCouponMapper(), customerId);
    }

}
