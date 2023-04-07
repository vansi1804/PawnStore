/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.impl;

import DAO.IPawnCouponDAO;
import Mapper.impl.PawnCouponMapper;
import Model.PawnCoupon;
import java.util.List;

/**
 *
 * @author NVS
 */
@SuppressWarnings({"ClassMayBeInterface", "ClassWithoutLogger"})
public class PawnCouponDAO extends ADAO<PawnCoupon> implements IPawnCouponDAO {

    private static final String SELECTQUERY = "Select pawn_coupon.id"
            + ", customer.id, customer.fullname, customer.gender, customer.phone_number, customer.address, customer.delete_flag"
            + ", product.id, type_of_product.id, type_of_product.name, type_of_product.delete_flag, product.name, product.info, product.status"
            + ", pawn_coupon.amount, pawn_coupon.price, pawn_coupon.interest_rate, pawn_coupon.pawn_date"
            + ", pawn_coupon.redemption_or_liquidation_date, pawn_coupon.liquidation_price, pawn_coupon.status"
            + " From pawn_coupon Inner Join customer On pawn_coupon.customer_id =customer.id"
            + " Inner Join product On pawn_coupon.product_id = product.id"
            + " Inner Join type_of_product On product.type_id = type_of_product.id";
    private static final String INSERTQUERY = "Insert Into PawnCoupon(id, customer_id, product_id, amount, price, interest_rate, pawn_date, redemption_or_liquidation_date,liquidation_price, status) Values(?,?,?,?,?,?,?,?,?,?)";
    private static final String UPDATEQUERY = "Update PawnCoupon Set customer_id = ?, product_id = ?, amount = ?, price = ?, interest_rate = ?"
            + ", pawn_date = ?, redemption_or_liquidation_date = ?, liquidation_price = ?, status = ? Where id = ?";

    @Override
    public List<PawnCoupon> findAll() {
        return findAll(SELECTQUERY, new PawnCouponMapper());
    }

    @Override
    public List<PawnCoupon> findAllByStatus(String status) {
        String query = SELECTQUERY
                + (status == null ? "" : " Where pawn_coupon.id = N'" + status + "'");
        return findAll(query, new PawnCouponMapper());
    }

    @Override
    public PawnCoupon getPawnCoupon(String id) {
        String query = SELECTQUERY + " Where pawn_coupon.id = ?";
        return findOne(query, new PawnCouponMapper(), id);
    }

    @Override
    public boolean insert(PawnCoupon pawnCoupon) {
        return insert(INSERTQUERY,
                pawnCoupon.getId(), pawnCoupon.getCustomer().getId(), pawnCoupon.getProduct().getId(), pawnCoupon.getAmount(),
                pawnCoupon.getPrice(), pawnCoupon.getInterestRate(), pawnCoupon.getPawnDate(), pawnCoupon.getRedemption0rLiquidationDate(), pawnCoupon.getLiquidationPrice(), pawnCoupon.getStatus());
    }

    @Override
    public boolean update(PawnCoupon pawnCoupon) {
        return update(UPDATEQUERY,
                pawnCoupon.getCustomer().getId(), pawnCoupon.getProduct().getId(), pawnCoupon.getAmount(), pawnCoupon.getPrice(),
                pawnCoupon.getInterestRate(), pawnCoupon.getPawnDate(), pawnCoupon.getRedemption0rLiquidationDate(), pawnCoupon.getLiquidationPrice(), pawnCoupon.getStatus(), pawnCoupon.getId());
    }

}
