/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Model.Customer;
import Model.PawnCoupon;
import Model.Product;
import java.util.List;

/**
 *
 * @author NVS
 */
@SuppressWarnings("MarkerInterface")
public interface IPawnCouponDAO extends IGenericDAO<PawnCoupon> {

    List<PawnCoupon> findAll();

    public List<PawnCoupon> findAllByStatus(String status);

    PawnCoupon getPawnCoupon(String id);

    boolean insert(PawnCoupon pawnCoupon);

    boolean update(PawnCoupon pawnCoupon);

}
