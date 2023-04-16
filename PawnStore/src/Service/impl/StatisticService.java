/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.impl;

import Common.Default;
import DAO.IActivityHistoryDAO;
import DAO.ICustomerDAO;
import DAO.IInterestPaymentDAO;
import DAO.IPawnCouponDAO;
import DAO.ITypeOfProductDAO;
import DAO.impl.ActivityHistoryDAO;
import DAO.impl.CustomerDAO;
import DAO.impl.InterestPaymentDAO;
import DAO.impl.PawnCouponDAO;
import DAO.impl.TypeOfProductDAO;
import Model.Customer;
import Model.InterestPayment;
import Model.PawnCoupon;
import Model.TypeOfProduct;
import Service.IStatisticService;
import Support.Support;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author NVS
 */
public class StatisticService implements IStatisticService {

    private static final Logger LOG = Logger.getLogger(StatisticService.class.getName());

    private final IPawnCouponDAO pawnCouponDAO = new PawnCouponDAO();
    private final IInterestPaymentDAO interestPaymentDAO = new InterestPaymentDAO();
    private final ICustomerDAO customerDAO = new CustomerDAO();
    private final ITypeOfProductDAO typeOfProductDAO = new TypeOfProductDAO();
    private final IActivityHistoryDAO activityHistoryDAO = new ActivityHistoryDAO();

    @Override
    @SuppressWarnings({"AssignmentToMethodParameter"})
    public List<String> getPawnCouponStatistic(Date dateFrom, Date dateTo) {
        if (dateFrom == null) {
            dateFrom = new Date(0);
        }
        if (dateTo == null) {
            dateTo = new Date(Long.MAX_VALUE);
        }
        @SuppressWarnings("CollectionWithoutInitialCapacity")
        List<String> results = new ArrayList<>();
        int totalPawnCouponCount = 0;
        long totalPawnCouponPrice = 0;
        int totalNotRedeemedPawnCouponCount = 0;
        long totalNotRedeemedPawnCouponPrice = 0;
        int totalRedeemedPawnCouponCount = 0;
        long totalRedeemedPawnCouponPrice = 0;
        int totalLiquidationPawnCouponCount = 0;
        long totalLiquidatedPawnCouponPawnPrice = 0;
        int totalLiquidatedPawnCouponLiquidationPrice = 0;
        long totalInterest = 0;

        for (PawnCoupon pawnCoupon : pawnCouponDAO.findAll()) {
            Date pawnDate = Support.stringToDate(pawnCoupon.getPawnDate(), Default.DATE_FORMAT);
            if (dateFrom.compareTo(pawnDate) <= dateTo.compareTo(pawnDate)) {
                totalPawnCouponCount++;
                totalPawnCouponPrice += pawnCoupon.getPrice();
            }
            if (pawnCoupon.getStatus().equals("Đã chuộc") || pawnCoupon.getStatus().equals("Đã thanh lý")) {
                Date redeemOrLiquidateDate = Support.stringToDate(pawnCoupon.getRedemption0rLiquidationDate(), Default.DATE_FORMAT);
                if (dateFrom.compareTo(redeemOrLiquidateDate) <= dateTo.compareTo(redeemOrLiquidateDate)) {
                    if (pawnCoupon.getStatus().equals("Đã chuộc")) {
                        totalRedeemedPawnCouponCount++;
                        totalRedeemedPawnCouponPrice += pawnCoupon.getPrice();
                    } else {
                        totalLiquidationPawnCouponCount++;
                        totalLiquidatedPawnCouponPawnPrice += pawnCoupon.getPrice();
                        totalLiquidatedPawnCouponLiquidationPrice += pawnCoupon.getLiquidationPrice();
                        totalInterest += pawnCoupon.getLiquidationPrice() - pawnCoupon.getPrice();
                    }
                }
            } else {
                totalNotRedeemedPawnCouponCount++;
                totalNotRedeemedPawnCouponPrice += pawnCoupon.getPrice();
            }
            for (InterestPayment interestPayment : interestPaymentDAO.findAllByPawnCouponId(pawnCoupon.getId())) {
                Date paymentDate = Support.stringToDate(interestPayment.getPaymentDate(), Default.DATE_FORMAT);
                if (dateFrom.compareTo(paymentDate) <= dateTo.compareTo(paymentDate)) {
                    totalInterest += interestPayment.getMoneyPaid();
                }
            }
        }
        results.add(Support.getFormatNumber(totalPawnCouponCount));
        results.add(Support.getFormatNumber(totalPawnCouponPrice));
        results.add(Support.getFormatNumber(totalNotRedeemedPawnCouponCount));
        results.add(Support.getFormatNumber(totalNotRedeemedPawnCouponPrice));
        results.add(Support.getFormatNumber(totalRedeemedPawnCouponCount));
        results.add(Support.getFormatNumber(totalRedeemedPawnCouponPrice));
        results.add(Support.getFormatNumber(totalLiquidationPawnCouponCount));
        results.add(Support.getFormatNumber(totalLiquidatedPawnCouponPawnPrice));
        results.add(Support.getFormatNumber(totalLiquidatedPawnCouponLiquidationPrice));
        results.add(Support.getFormatNumber(totalInterest));
        return results;
    }

    @Override
    @SuppressWarnings({"null", "AssignmentToMethodParameter"})
    public List<String> getCustomerStatistic(Date dateFrom, Date dateTo) {
        if (dateFrom == null) {
            dateFrom = new Date(0);
        }
        if (dateTo == null) {
            dateTo = new Date(Long.MAX_VALUE);
        }
        int totalCustomer = 0;
        String bestCustomerName = "";
        int bestCustomerPawnedCount = 0;
        long bestCustomerPawnedPrice = 0;
        long bestCustomerInterestPayed = 0;
        int totalStopServiceCount = 0;

        @SuppressWarnings("CollectionWithoutInitialCapacity")
        List<String> results = new ArrayList<>();
        for (Customer customer : customerDAO.findAll()) {
            String strDateFrom = Support.dateToString(dateFrom, Default.DATE_FORMAT);
            String strDateTo = Support.dateToString(dateTo, Default.DATE_FORMAT);
            if (!activityHistoryDAO.filterByKey(strDateFrom, strDateTo, null, "Thêm mới", "Khách hàng", null).isEmpty()) {
                totalCustomer++;
            }
            int totalPawnedCount = 0;
            long totalPawnedPrice = 0;
            long totalInterestPayed = 0;
            for (PawnCoupon pawnCoupon : pawnCouponDAO.findAllByCustomerId(customer.getId())) {
                Date pawnDate = Support.stringToDate(pawnCoupon.getPawnDate(), Default.DATE_FORMAT);
                if (dateFrom.compareTo(pawnDate) <= dateTo.compareTo(pawnDate)) {
                    totalPawnedCount++;
                    totalPawnedPrice += pawnCoupon.getPrice();
                }
                for (InterestPayment interestPayment : interestPaymentDAO.findAllByPawnCouponId(pawnCoupon.getId())) {
                    Date paymentDate = Support.stringToDate(interestPayment.getPaymentDate(), Default.DATE_FORMAT);
                    if (dateFrom.compareTo(paymentDate) <= dateTo.compareTo(paymentDate)) {
                        totalInterestPayed += interestPayment.getMoneyPaid();
                    }
                }
            }
            if (totalPawnedCount > bestCustomerPawnedCount) { 
                bestCustomerName = customer.getId() + " - " + customer.getFullname();
                bestCustomerPawnedCount = totalPawnedCount;
                bestCustomerPawnedPrice = totalPawnedPrice;
                bestCustomerInterestPayed = totalInterestPayed;
            } else if (totalPawnedCount == bestCustomerPawnedCount && totalPawnedPrice > bestCustomerPawnedPrice) {
                bestCustomerName = customer.getId() + " - " + customer.getFullname();
                bestCustomerPawnedPrice = totalPawnedPrice;
                bestCustomerInterestPayed = totalInterestPayed;
            } else if (totalPawnedPrice == bestCustomerPawnedPrice) {
                if (totalInterestPayed > bestCustomerInterestPayed) {
                    bestCustomerName = customer.getId() + " - " + customer.getFullname();
                    bestCustomerInterestPayed = totalInterestPayed;
                } else if (totalInterestPayed == bestCustomerInterestPayed) {
                    bestCustomerName += ", " + customer.getId() + " - " + customer.getFullname();
                }
            }
            if (!activityHistoryDAO.filterByKey(strDateFrom, strDateTo, null, "Sửa", "Khách hàng", "Ngưng phục vụ").isEmpty()
                    && activityHistoryDAO.filterByKey(strDateTo, strDateTo, null, "Sửa", "Khách hàng", "Phục vụ").isEmpty()) {
                totalStopServiceCount++;
            }
        }
        results.add(Support.getFormatNumber(totalCustomer));
        results.add(bestCustomerName);
        results.add(Support.getFormatNumber(bestCustomerPawnedCount));
        results.add(Support.getFormatNumber(bestCustomerPawnedPrice));
        results.add(Support.getFormatNumber(bestCustomerInterestPayed));
        results.add(Support.getFormatNumber(totalStopServiceCount));
        return results;
    }

    @Override
    @SuppressWarnings("AssignmentToMethodParameter")
    public List<String> getTypeOfProductStatistic(Date dateFrom, Date dateTo) {
        if (dateFrom == null) {
            dateFrom = new Date(0);
        }
        if (dateTo == null) {
            dateTo = new Date(Long.MAX_VALUE);
        }
        int typeOfProductCount = 0;
        int productCount = 0;
        String bestTypeOfProductName = "";
        int bestTypeOfProductPawnedCount = 0;
        long bestTypeOfProductPawnedPrice = 0;
        long bestTypeOfProductInterestPayed = 0;
        int totalStopServiceCount = 0;

        @SuppressWarnings("CollectionWithoutInitialCapacity")
        List<String> results = new ArrayList<>();
        for (TypeOfProduct typeOfProduct : typeOfProductDAO.findAll()) {
            typeOfProductCount++;
            String strDateFrom = Support.dateToString(dateFrom, Default.DATE_FORMAT);
            String strDateTo = Support.dateToString(dateTo, Default.DATE_FORMAT);
            if (!activityHistoryDAO.filterByKey(strDateFrom, strDateTo, null, "Thêm mới", "Hàng hóa", typeOfProduct.getId()).isEmpty()) {
                productCount++;
            }
            int totalPawnedCount = 0;
            long totalPawnedPrice = 0;
            long totalInterestPayed = 0;
            for (PawnCoupon pawnCoupon : pawnCouponDAO.findAll()) {
                if (typeOfProduct.getId().equals(pawnCoupon.getProduct().getTypeOfProduct().getId())) {
                    Date pawnDate = Support.stringToDate(pawnCoupon.getPawnDate(), Default.DATE_FORMAT);
                    if (dateFrom.compareTo(pawnDate) <= dateTo.compareTo(pawnDate)) {
                        totalPawnedCount++;
                        totalPawnedPrice += pawnCoupon.getPrice();
                        for (InterestPayment interestPayment : interestPaymentDAO.findAllByPawnCouponId(pawnCoupon.getId())) {
                            Date paymentDate = Support.stringToDate(interestPayment.getPaymentDate(), Default.DATE_FORMAT);
                            if (dateFrom.compareTo(paymentDate) <= dateTo.compareTo(paymentDate)) {
                                totalInterestPayed += interestPayment.getMoneyPaid();
                            }
                        }
                    }
                }
            }
            if (totalPawnedCount > bestTypeOfProductPawnedCount) {
                bestTypeOfProductName = typeOfProduct.getName();
                bestTypeOfProductPawnedCount = totalPawnedCount;
                bestTypeOfProductPawnedPrice = totalPawnedPrice;
                bestTypeOfProductInterestPayed = totalInterestPayed;
            } else if (bestTypeOfProductPawnedCount == bestTypeOfProductPawnedCount && totalPawnedPrice > bestTypeOfProductPawnedPrice) {
                bestTypeOfProductName = typeOfProduct.getName();
                bestTypeOfProductPawnedPrice = totalPawnedPrice;
                bestTypeOfProductInterestPayed = totalInterestPayed;
            } else if (totalPawnedCount == bestTypeOfProductPawnedPrice) {
                if (totalInterestPayed > bestTypeOfProductInterestPayed) {
                    bestTypeOfProductName = typeOfProduct.getName();
                    bestTypeOfProductInterestPayed = totalInterestPayed;
                } else if (totalInterestPayed == bestTypeOfProductInterestPayed) {
                    bestTypeOfProductName += ", " + typeOfProduct.getName();
                }
            }
            if (!activityHistoryDAO.filterByKey(strDateFrom, strDateTo, null, "Sửa", "Loại hàng hóa", "Ngưng phục vụ").isEmpty()
                    && activityHistoryDAO.filterByKey(strDateTo, strDateTo, null, "Sửa", "Loại hàng hóa", "Phục vụ").isEmpty()) {
                totalStopServiceCount++;
            }
        }
        results.add(Support.getFormatNumber(typeOfProductCount));
        results.add(Support.getFormatNumber(productCount));
        results.add(bestTypeOfProductName);
        results.add(Support.getFormatNumber(bestTypeOfProductPawnedCount));
        results.add(Support.getFormatNumber(bestTypeOfProductPawnedPrice));
        results.add(Support.getFormatNumber(bestTypeOfProductInterestPayed));
        results.add(Support.getFormatNumber(totalStopServiceCount));

        return results;
    }

}
