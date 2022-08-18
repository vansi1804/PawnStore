/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.impl;

import DAO.IPawnCouponDAO;
import Mapper.impl.PawnCouponMapper;
import Model.Customer;
import Model.PawnCoupon;
import Model.Product;
import Support.CheckSupport;
import java.util.ArrayList;

/**
 *
 * @author NVS
 */
@SuppressWarnings({"ClassMayBeInterface", "ClassWithoutLogger"})
public class PawnCouponDAO extends ADAO<PawnCoupon> implements IPawnCouponDAO {

    private static final String SELECTQUERY = "Select PawnCoupon._id"
            + ", Customer._id, Customer._fullname, Customer._gender, Customer._phonenumber, Customer._address, Customer._deleteflag"
            + ", Product._id, TypeOfProduct._id, TypeOfProduct._name, TypeOfProduct._deleteflag, Product._name, Product._information, Product._status"
            + ", PawnCoupon._amount, PawnCoupon._price, PawnCoupon._interestRate, PawnCoupon._pawnDate, PawnCoupon._redeemOrLiquidationDate, PawnCoupon._liquidationPrice, PawnCoupon._status"
            + " From PawnCoupon Inner Join Customer On PawnCoupon._customerID = Customer._id"
            + " Inner Join Product On PawnCoupon._productID = Product._id"
            + " Inner Join TypeOfProduct On Product._typeID = TypeOfProduct._id";
    private static final String INSERTQUERY = "Insert Into PawnCoupon(_id, _customerID, _productID, _amount, _price, _interestRate, _pawnDate, _redeemOrLiquidationDate,_liquidationPrice, _status) Values(?,?,?,?,?,?,?,?,?,?)";
    private static final String UPDATEQUERY = "Update PawnCoupon Set _customerID = ?, _productID = ?, _amount = ?, _price = ?, _interestRate = ?"
            + ", _pawnDate = ?, _redeemOrLiquidationDate = ?, _liquidationPrice = ?, _status = ? Where _id = ?";
    private static final String DELETEQUERY = "Delete from PawnCoupon Where _id = ?";

    @Override
    public ArrayList<PawnCoupon> getList() {
        return getList(SELECTQUERY, new PawnCouponMapper());
    }

    @Override
    public PawnCoupon getPawnCoupon(String id) {
        String query = SELECTQUERY + " Where PawnCoupon._id = ?";
        return getObject(query, new PawnCouponMapper(), id);
    }

    @Override
    public boolean insert(PawnCoupon pawnCoupon) {
        return insert(INSERTQUERY,
                pawnCoupon.getId(), pawnCoupon.getCustomer().getId(), pawnCoupon.getProduct().getId(), pawnCoupon.getAmount(),
                pawnCoupon.getPrice(), pawnCoupon.getInterestRate(), pawnCoupon.getPawnDate(), pawnCoupon.getRedeem0rLiquidationDate(), pawnCoupon.getLiquidationPrice(), pawnCoupon.getStatus());
    }

    @Override
    public boolean update(PawnCoupon pawnCoupon) {
        return update(UPDATEQUERY,
                pawnCoupon.getCustomer().getId(), pawnCoupon.getProduct().getId(), pawnCoupon.getAmount(), pawnCoupon.getPrice(),
                pawnCoupon.getInterestRate(), pawnCoupon.getPawnDate(), pawnCoupon.getRedeem0rLiquidationDate(), pawnCoupon.getLiquidationPrice(), pawnCoupon.getStatus(), pawnCoupon.getId());
    }

    @Override
    public boolean delete(PawnCoupon pawnCoupon) {
        return update(DELETEQUERY, pawnCoupon.getId());
    }

    @Override
    public ArrayList<PawnCoupon> findPawnCouponByCustomerKey(Customer customer) {
        String query = SELECTQUERY + " Where PawnCoupon._customerID = ?";
        return getList(query, new PawnCouponMapper(), customer.getId());
    }

    @Override
    public ArrayList<PawnCoupon> findPawnCouponByStatusKey(String statusKey) {
        String query = SELECTQUERY + " Where PawnCoupon._status  = N'" + statusKey + "'";
        return getList(query, new PawnCouponMapper());
    }

    @Override
    @SuppressWarnings("null")
    public ArrayList<PawnCoupon> findPawnCouponByKey(String idKey, Customer customerKey, Product productKey,
            int amountKey, int priceKey, float interestRateKey, String pawnDateKey, String redeemDateKey, String statusKey) {
        String query = SELECTQUERY;

        boolean isIDKeyEmpty = CheckSupport.isBlank(idKey);
        boolean isCustomerKeyEmpty = customerKey == null;
        boolean isProductKeyEmpty = productKey == null;
        boolean isAmountKeyEmpty = amountKey == -1;
        boolean isPriceKeyEmpty = priceKey == -1;
        boolean isInterestKeyEmpty = interestRateKey == -1;
        boolean isPawnDateKeyEmpty = CheckSupport.isBlank(pawnDateKey);
        boolean isRedeemDateKeyEmpty = CheckSupport.isBlank(redeemDateKey);
        boolean isStatusKeyEmpty = CheckSupport.isBlank(statusKey);

        if (!isIDKeyEmpty || !isCustomerKeyEmpty || !isProductKeyEmpty || !isAmountKeyEmpty || !isPriceKeyEmpty
                || !isInterestKeyEmpty || !isPawnDateKeyEmpty || !isRedeemDateKeyEmpty || !isStatusKeyEmpty) {
            query += " Where ";
        }
        if (!isIDKeyEmpty) {
            query += " PawnCoupon._id like N'%" + idKey + "%'";
        }
        if (!isIDKeyEmpty && (!isCustomerKeyEmpty || !isProductKeyEmpty || !isAmountKeyEmpty || !isPriceKeyEmpty
                || !isInterestKeyEmpty || !isPawnDateKeyEmpty || !isRedeemDateKeyEmpty || !isStatusKeyEmpty)) {
            query += " And ";
        }
        if (!isCustomerKeyEmpty) {
            query += " PawnCoupon._customerID = '" + customerKey.getId() + "'";
        }
        if (!isCustomerKeyEmpty && (!isProductKeyEmpty || !isAmountKeyEmpty || !isPriceKeyEmpty
                || !isInterestKeyEmpty || !isPawnDateKeyEmpty || !isRedeemDateKeyEmpty || !isStatusKeyEmpty)) {
            query += " And ";
        }
        if (!isProductKeyEmpty) {
            query += " PawnCoupon._productID = '" + productKey.getId() + "'";
        }
        if (!isProductKeyEmpty && (!isAmountKeyEmpty || !isPriceKeyEmpty
                || !isInterestKeyEmpty || !isPawnDateKeyEmpty || !isRedeemDateKeyEmpty || !isStatusKeyEmpty)) {
            query += " And ";
        }
        if (!isAmountKeyEmpty) {
            query += " PawnCoupon._amount = " + amountKey;
        }
        if (!isAmountKeyEmpty && (!isPriceKeyEmpty || !isInterestKeyEmpty || !isPawnDateKeyEmpty
                || !isRedeemDateKeyEmpty || !isStatusKeyEmpty)) {
            query += " And ";
        }
        if (!isPriceKeyEmpty) {
            query += " PawnCoupon._price = " + priceKey;
        }
        if (!isPriceKeyEmpty && (!isInterestKeyEmpty || !isPawnDateKeyEmpty || !isRedeemDateKeyEmpty || !isStatusKeyEmpty)) {
            query += " And ";
        }
        if (!isInterestKeyEmpty) {
            query += " PawnCoupon._interestRate = " + interestRateKey;
        }
        if (!isInterestKeyEmpty && (!isPawnDateKeyEmpty || !isRedeemDateKeyEmpty || !isStatusKeyEmpty)) {
            query += " And ";
        }
        if (!isPawnDateKeyEmpty) {
            query += " Convert(datetime,PawnCoupon._pawnDate,105) "
                    + " Between Convert(datetime,'" + pawnDateKey + "',105) And Convert(datetime,'" + pawnDateKey + "',105) ";
        }
        if (!isPawnDateKeyEmpty && (!isRedeemDateKeyEmpty || !isStatusKeyEmpty)) {
            query += " And ";
        }
        if (!isRedeemDateKeyEmpty) {
            query += " Convert(datetime,PawnCoupon._redeemDate,105) "
                    + " Between Convert(datetime,'" + redeemDateKey + "',105) And Convert(datetime,'" + redeemDateKey + "',105) ";
        }
        if (!isRedeemDateKeyEmpty && !isStatusKeyEmpty) {
            query += " And ";
        }
        if (!isStatusKeyEmpty) {
            query += " PawnCoupon._status = N'" + statusKey + "'";
        }
        //MessageSupport.Message("query", query);
        return getList(query, new PawnCouponMapper());
    }

    @Override
    public ArrayList<PawnCoupon> findPawnCouponByTime(String dateFrom, String dateTo) {
        String query = SELECTQUERY
                + " Where Convert(datetime,PawnCoupon._pawnDate,105) Between Convert(datetime,?,105) And Convert(datetime,?,105)"
                + " Or Convert(datetime,PawnCoupon._redeemOrLiquidationDate,105) Between Convert(datetime,?,105) And Convert(datetime,?,105)";
        return getList(query, new PawnCouponMapper(), dateFrom, dateTo, dateFrom, dateTo);
    }

}
