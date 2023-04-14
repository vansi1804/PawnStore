/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Model.PawnCoupon;
import java.util.List;

/**
 *
 * @author NVS
 */
@SuppressWarnings("MarkerInterface")
public interface IPawnCouponDAO extends IGenericDAO<PawnCoupon> {

    List<PawnCoupon> findAll();

    public List<PawnCoupon> findAllByStatus(String status);

    PawnCoupon findOneById(String id);

    boolean insert(PawnCoupon pawnCoupon);

    boolean update(PawnCoupon pawnCoupon);

    PawnCoupon findLastest();

    List<PawnCoupon> filter(String id, String customerId, String productId, 
            Long pawnPrice, Float interestRate, Integer amount, 
            String pawnDate, String theNextInterestPaymentDate, String redemptionOrLiquidationDate, 
            Long liquidationPrice, String status);

    List<PawnCoupon> findAllByCustomerId(String customerId);
}
