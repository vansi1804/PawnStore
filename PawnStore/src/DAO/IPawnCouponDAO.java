/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Model.Customer;
import Model.PawnCoupon;
import Model.Product;
import java.util.ArrayList;

/**
 *
 * @author NVS
 */
@SuppressWarnings("MarkerInterface")
public interface IPawnCouponDAO extends IGenericDAO<PawnCoupon> {

    ArrayList<PawnCoupon> getList();

    PawnCoupon getPawnCoupon(String id);

    boolean insert(PawnCoupon pawnCoupon);

    boolean update(PawnCoupon pawnCoupon);

    boolean delete(PawnCoupon pawnCoupon);

    ArrayList<PawnCoupon> findPawnCouponByCustomerKey(Customer customer);
    
    ArrayList<PawnCoupon> findPawnCouponByStatusKey(String statusID);

    ArrayList<PawnCoupon> findPawnCouponByTime(String dateFrom, String dateTo);
    
    ArrayList<PawnCoupon> findPawnCouponByKey(String idKey, Customer customer, Product productKey, int amountKey, int priceKey,
             float interestRateKey, String pawnDate, String redeemDate, String status);

}
