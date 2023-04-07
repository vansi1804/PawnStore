/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.impl;

import DAO.IInterestPaymentDAO;
import DAO.impl.InterestPaymentDAO;
import Model.InterestPayment;
import Model.PawnCoupon;
import Service.IInterestPaymentService;
import java.util.List;

/**
 *
 * @author NVS
 */
@SuppressWarnings("ClassWithoutLogger")
public class InterestPaymentService implements IInterestPaymentService {

    @SuppressWarnings("FieldMayBeFinal")
    private static IInterestPaymentDAO interestPaymentDAO = new InterestPaymentDAO();

    @Override
    public List<InterestPayment> getList(PawnCoupon pawnCoupon) {
        return interestPaymentDAO.getList(pawnCoupon);
    }

    @Override
    public InterestPayment getInterestPayment(PawnCoupon pawnCoupon, String times) {
        return interestPaymentDAO.getInterestPayment(pawnCoupon, times);
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

}
