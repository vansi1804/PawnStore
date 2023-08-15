/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.impl;

import DAO.ICustomerDAO;
import DAO.IInterestPaymentDAO;
import DAO.IPawnCouponDAO;
import DAO.IProductDAO;
import DAO.impl.CustomerDAO;
import DAO.impl.InterestPaymentDAO;
import DAO.impl.PawnCouponDAO;
import DAO.impl.ProductDAO;
import Model.Customer;
import Model.InterestPayment;
import Model.PawnCoupon;
import Model.Product;
import Service.IPawnCouponService;
import Support.MessageSupport;
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
    private final ICustomerDAO customerDAO = new CustomerDAO();
    private final IProductDAO productDAO = new ProductDAO();
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
    public PawnCoupon findOneById(String id) {
        PawnCoupon pawnCoupon = pawnCouponDAO.findOneById(id);
        if (pawnCoupon != null) {
            updateLate(pawnCoupon);
        }
        return pawnCoupon;
    }

    @Override
    public boolean insert(PawnCoupon pawnCoupon) {
        if (customerDAO.findOneById(pawnCoupon.getCustomer().getId()).getDeleteFlag()) {
            MessageSupport.ErrorMessage("Lỗi", "Khách hàng ngưng phục vụ");
            return false;
        }
        Product product = productDAO.findOneById(pawnCoupon.getProduct().getId());
        if (product.getTypeOfProduct().getDeleteFlag()) {
            MessageSupport.ErrorMessage("Lỗi", "Hàng hóa thuộc loại mặt hàng ngưng phục vụ");
            return false;
        } else if (product.getStatus().equals("Chưa chuộc")
                || product.getStatus().equals("Cần thanh lý")) {
            MessageSupport.ErrorMessage("Lỗi", "Hàng hóa đang có hiệu lực ở một hợp đồng khác\n Kiểm tra và thử lại");
            return false;
        }
        return pawnCouponDAO.insert(pawnCoupon);
    }

    @Override
    public boolean update(PawnCoupon pawnCoupon) {
        PawnCoupon existingOne = this.findOneById(pawnCoupon.getId());
        Customer customer = customerDAO.findOneById(pawnCoupon.getCustomer().getId());
        if (!existingOne.getCustomer().getId().equals(customer.getId()) && customer.getDeleteFlag()) {
            MessageSupport.ErrorMessage("Lỗi", "Khách hàng ngưng phục vụ");
            return false;
        }

        Product product = productDAO.findOneById(pawnCoupon.getProduct().getId());
        if (!existingOne.getProduct().getId().equals(product.getId())
                && (product.getStatus().equals("Chưa chuộc") || product.getStatus().equals("Cần thanh lý"))) {
            MessageSupport.ErrorMessage("Lỗi", "Hàng hóa đang có hiệu lực ở một hợp đồng khác\n Kiểm tra và thử lại");
            return false;
        }

        InterestPayment lastInterestPayment = interestPaymentDAO.findLast(pawnCoupon.getId());
        if (pawnCoupon.getStatus().equals("Đã chuộc") && lastInterestPayment.getNewDebt() > 0) {
            MessageSupport.ErrorMessage("Lỗi", "Hợp đồng còn tồn nợ.");
            return false;
        }

        return pawnCouponDAO.update(pawnCoupon);
    }

    @Override
    public String createNewId() {
        PawnCoupon pawnCoupon = pawnCouponDAO.findLastest();
        if (pawnCoupon == null) {
            return "HĐ0000000001";
        }
        return Support.createNewId(pawnCoupon.getId());
    }

    private PawnCoupon updateLate(PawnCoupon pawnCoupon) {
        if (pawnCoupon.getStatus().equals("Chưa chuộc")) {
            if (Support.subtractDate(pawnCoupon.getTheNextInterestPaymentDate(), new Date()) <= 0) {
                pawnCoupon.setStatus("Trễ");
                this.update(pawnCoupon);
            }
        }
        return pawnCoupon;
    }

    @Override
    public List<PawnCoupon> filter(String id, String customerId, String productId,
            Long pawnPrice, Float interestRate, Integer amount,
            String pawnDate, String theNextInterestPaymentDate, String redemptionOrLiquidationDate,
            Long liquidationPrice, String status) {
        return pawnCouponDAO.filter(id, customerId, productId,
                pawnPrice, interestRate, amount,
                pawnDate, theNextInterestPaymentDate, redemptionOrLiquidationDate,
                liquidationPrice, status);
    }

    @Override
    public List<PawnCoupon> findAllByCustomerId(String customerId) {
        return pawnCouponDAO.findAllByCustomerId(customerId);
    }

}
