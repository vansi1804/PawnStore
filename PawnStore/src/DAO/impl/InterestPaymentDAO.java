/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.impl;

import DAO.IInterestPaymentDAO;
import Mapper.impl.InterestPaymentMapper;
import Model.InterestPayment;
import Model.PawnCoupon;
import java.util.ArrayList;

/**
 *
 * @author NVS
 */
@SuppressWarnings("ClassWithoutLogger")
public class InterestPaymentDAO extends ADAO<InterestPayment> implements IInterestPaymentDAO {

    private static final String SELECTQUERY = "Select PawnCoupon._id"
            + ", Customer._id, Customer._fullname, Customer._gender, Customer._phonenumber, Customer._address, Customer._deleteflag"
            + ", Product._id"
            + ", TypeOfProduct._id, TypeOfProduct._name, TypeOfProduct._deleteflag"
            + ", Product._name, Product._information, Product._status"
            + ", PawnCoupon._amount, PawnCoupon._price, PawnCoupon._interestRate, PawnCoupon._pawnDate, PawnCoupon._redeemOrLiquidationDate, PawnCoupon._liquidationPrice, PawnCoupon._status"
            + ", InterestPayment._times, InterestPayment._paymentDate, InterestPayment._money, InterestPayment._debt, InterestPayment._note"
            + " From InterestPayment"
            + " Inner Join PawnCoupon On InterestPayment._pawnCouponID = PawnCoupon._id "
            + " Inner Join Customer On PawnCoupon._customerID = Customer._id"
            + " Inner Join Product On PawnCoupon._productID = Product._id"
            + " Inner Join TypeOfProduct On Product._typeID = TypeOfProduct._id";

    private static final String INSERTQUERY = "Insert Into InterestPayment(_pawnCouponID, _times, _paymentDate,  _money, _debt, _note) Values(?,?,?,?,?,?)";

    private static final String UPDATEQUERY = "Update InterestPayment Set _pawnCouponID = ?, _times = ?, _paymentDate = ?, _money = ?, _debt = ?, _note = ?";

    private static final String DELETEQUERY = "Delete from InterestPayment Where _pawnCouponID = ? And _times = ?";

    @Override
    public ArrayList<InterestPayment> getList(PawnCoupon pawnCoupon) {
        String query = SELECTQUERY + " Where InterestPayment._pawnCouponID = N'" + pawnCoupon.getId() + "'";
        return getList(query, new InterestPaymentMapper());
    }

    @Override
    public InterestPayment getInterestPayment(PawnCoupon pawnCoupon, String times) {
        String query = SELECTQUERY + " Where InterestPayment._pawnCouponID = N'" + pawnCoupon.getId() + "' And InterestPayment._times = ?";
        return getObject(query, new InterestPaymentMapper(), times);
    }

    @Override
    public boolean insert(InterestPayment interestPayment) {
        return insert(INSERTQUERY, interestPayment.getPawnCoupon().getId(), interestPayment.getTimes(),
                interestPayment.getPaymentDate(), interestPayment.getMoney(),
                interestPayment.getDebt(), interestPayment.getNote());
    }

    @Override
    public boolean update(InterestPayment interestPayment) {
        return update(UPDATEQUERY, interestPayment.getPawnCoupon().getId(), interestPayment.getTimes(),
                interestPayment.getPaymentDate(), interestPayment.getMoney(),
                interestPayment.getDebt(), interestPayment.getNote());
    }

    @Override
    public boolean delete(InterestPayment interestPayment) {
        return delete(DELETEQUERY, interestPayment.getPawnCoupon().getId(), interestPayment.getTimes());
    }

}
