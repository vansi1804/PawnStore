/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.impl;

import DAO.IInterestPaymentDAO;
import DAO.IPawnCouponDAO;
import DAO.impl.InterestPaymentDAO;
import DAO.impl.PawnCouponDAO;
import Model.Customer;
import Model.InterestPayment;
import Model.PawnCoupon;
import Model.Product;
import Service.IPawnCouponService;
import Support.Support;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author NVS
 */
@SuppressWarnings({"ClassMayBeInterface", "ClassWithoutLogger"})
public class PawnCouponService implements IPawnCouponService {

    private final IPawnCouponDAO pawnCouponDAO = new PawnCouponDAO();
    private final IInterestPaymentDAO interestPaymentDAO = new InterestPaymentDAO();

    @Override
    public ArrayList<PawnCoupon> getList() {
        ArrayList<PawnCoupon> pawnCoupons = pawnCouponDAO.getList();
        for (PawnCoupon pawnCoupon : pawnCoupons) {
            if (checkForLate(pawnCoupon)) {
                pawnCoupon.setStatus("Trễ");
                pawnCouponDAO.update(pawnCoupon);
            }
        }
        return pawnCouponDAO.getList();
    }

    @Override
    public PawnCoupon getPawnCoupon(String id) {
        return pawnCouponDAO.getPawnCoupon(id);
    }

    @Override
    public boolean insert(PawnCoupon pawnCoupon) {
        return pawnCouponDAO.insert(pawnCoupon);
    }

    @Override
    public boolean update(PawnCoupon pawnCoupon) {
        return pawnCouponDAO.update(pawnCoupon);
    }

    @Override
    public boolean delete(PawnCoupon pawnCoupon) {
        return pawnCouponDAO.delete(pawnCoupon);
    }

    @Override
    public ArrayList<PawnCoupon> findPawnCouponByCustomerKey(Customer customer) {
        return pawnCouponDAO.findPawnCouponByCustomerKey(customer);
    }

    @Override
    public ArrayList<PawnCoupon> findPawnCouponByStatusKey(String statusKey) {
        return pawnCouponDAO.findPawnCouponByStatusKey(statusKey);
    }

    @Override
    public ArrayList<PawnCoupon> findPawnCouponByKey(String idKey, Customer customer, Product productKey,
            int amountKey, int priceKey, float interestRateKey, String pawnDate, String redeemDate, String status) {
        return pawnCouponDAO.findPawnCouponByKey(idKey, customer, productKey,
                amountKey, priceKey, interestRateKey, pawnDate, redeemDate, status);
    }

    @Override
    public String getNewID() {
        ArrayList<PawnCoupon> pawnCoupons = pawnCouponDAO.getList();
        if (pawnCoupons.isEmpty()) {
            return "HĐ0000000001";
        }
        return Support.getNewID(pawnCoupons.get(pawnCoupons.size() - 1).getId());
    }

    @Override
    public String getTheNextPaymentDate(PawnCoupon pawnCoupon) {
        ArrayList<InterestPayment> interestPayments = interestPaymentDAO.getList(pawnCoupon);
        if (interestPayments.isEmpty()) {
            return Support.addDate(pawnCoupon.getPawnDate(), 14);
        } else {
            return Support.addDate(interestPayments.get(interestPayments.size() - 1).getPaymentDate(), 15);
        }
    }

    @Override
    public boolean checkForLate(PawnCoupon pawnCoupon) {
        return Support.stringToDate(getTheNextPaymentDate(pawnCoupon), Support.getDateFormat()).compareTo(new Date()) < 0;
    }

    @Override
    public ArrayList<PawnCoupon> findPawnCouponByTime(String dateFrom, String dateTo) {
        return pawnCouponDAO.findPawnCouponByTime(dateFrom, dateTo);
    }


}
