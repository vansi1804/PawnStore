/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.impl;

import DAO.IInterestPaymentDAO;
import DAO.IPawnCouponDAO;
import DAO.impl.InterestPaymentDAO;
import DAO.impl.PawnCouponDAO;
import Model.InterestPayment;
import Model.PawnCoupon;
import Service.IPawnCouponService;
import Support.Support;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author NVS
 */
@SuppressWarnings({"ClassMayBeInterface", "ClassWithoutLogger"})
public class PawnCouponService implements IPawnCouponService {

    private final IPawnCouponDAO pawnCouponDAO = new PawnCouponDAO();
    private final IInterestPaymentDAO interestPaymentDAO = new InterestPaymentDAO();

    @Override
    public List<PawnCoupon> findAll() {
        return pawnCouponDAO.findAll().stream().map(p -> updateLate(p)).collect(Collectors.toList());
    }

    @Override
    public List<PawnCoupon> findAllByStatus(String status) {
        return pawnCouponDAO.findAllByStatus(status).stream().map(p -> updateLate(p)).collect(Collectors.toList());
    }

    @Override
    public PawnCoupon getPawnCoupon(String id) {
        return updateLate(pawnCouponDAO.getPawnCoupon(id));
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
    public String getNewID() {
        List<PawnCoupon> pawnCoupons = pawnCouponDAO.findAll();
        if (pawnCoupons.isEmpty()) {
            return "HĐ0000000001";
        }
        return Support.createNewId(pawnCoupons.get(pawnCoupons.size() - 1).getId());
    }

    @Override
    public String getTheNextPaymentDate(PawnCoupon pawnCoupon) {
        List<InterestPayment> interestPayments = interestPaymentDAO.getList(pawnCoupon);
        if (interestPayments.isEmpty()) {
            return Support.addDate(pawnCoupon.getPawnDate(), 14);
        } else {
            return Support.addDate(interestPayments.get(interestPayments.size() - 1).getPaymentDate(), 15);
        }
    }

    private boolean checkForLate(PawnCoupon pawnCoupon) {
        return pawnCoupon.getStatus().equals("Chưa chuộc") && Support.subtractDate(getTheNextPaymentDate(pawnCoupon), new Date()) < 0;
    }

    private PawnCoupon updateLate(PawnCoupon pawnCoupon) {
        if (pawnCoupon != null && checkForLate(pawnCoupon)) {
            pawnCoupon.setStatus("Trễ");
            pawnCouponDAO.update(pawnCoupon);
        }
        return pawnCoupon;
    }

}
