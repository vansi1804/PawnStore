/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import Model.PawnCoupon;
import java.util.List;

/**
 *
 * @author NVS
 */
@SuppressWarnings("MarkerInterface")
public interface IPawnCouponService {

    List<PawnCoupon> findAll();

    List<PawnCoupon> findAllByStatus(String status);

    PawnCoupon getPawnCoupon(String id);

    boolean insert(PawnCoupon pawnCoupon);

    boolean update(PawnCoupon pawnCoupon);

    String getNewID();

    String getTheNextPaymentDate(PawnCoupon pawnCoupon);

}
