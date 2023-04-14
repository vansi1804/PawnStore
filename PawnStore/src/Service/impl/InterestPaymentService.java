/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.impl;

import Common.Default;
import DAO.IInterestPaymentDAO;
import DAO.IPawnCouponDAO;
import DAO.impl.InterestPaymentDAO;
import DAO.impl.PawnCouponDAO;
import Model.InterestPayment;
import Service.IInterestPaymentService;
import Support.Support;
import java.util.Date;
import java.util.List;

/**
 *
 * @author NVS
 */
@SuppressWarnings("ClassWithoutLogger")
public class InterestPaymentService implements IInterestPaymentService {

    private static final IInterestPaymentDAO interestPaymentDAO = new InterestPaymentDAO();
    private static final IPawnCouponDAO pawnCouponDAO = new PawnCouponDAO();

    @Override
    public List<InterestPayment> findAllByPawnCouponId(String pawnCouponId) {
        return interestPaymentDAO.findAllByPawnCouponId(pawnCouponId);
    }

    @Override
    public InterestPayment findOneByPawnCouponIdAndTimes(String pawnCouponId, Integer times) {
        return interestPaymentDAO.findOneByPawnCouponIdAndTimes(pawnCouponId, times);
    }

    @Override
    public boolean insert(InterestPayment interestPayment) {
        return interestPaymentDAO.insert(interestPayment);
    }

    @Override
    public boolean update(InterestPayment interestPayment) {
        return interestPaymentDAO.update(interestPayment);
    }

    @Override
    public boolean delete(InterestPayment interestPayment) {
        return interestPaymentDAO.delete(interestPayment);
    }

    @Override
    public int countAllByPawnCouponId(String pawnCouponId) {
        return interestPaymentDAO.countAllByPawnCouponId(pawnCouponId);
    }

}
