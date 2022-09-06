/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.impl;

import DAO.IActivityHistoryDAO;
import DAO.ICustomerDAO;
import DAO.IInterestPaymentDAO;
import DAO.IPawnCouponDAO;
import DAO.IProductDAO;
import DAO.ITypeOfProductDAO;
import DAO.impl.ActivityHistoryDAO;
import DAO.impl.CustomerDAO;
import DAO.impl.InterestPaymentDAO;
import DAO.impl.PawnCouponDAO;
import DAO.impl.ProductDAO;
import DAO.impl.TypeOfProductDAO;
import Model.Customer;
import Model.InterestPayment;
import Model.PawnCoupon;
import Model.TypeOfProduct;
import Service.IStatisticService;
import Support.Support;
import java.util.ArrayList;
import java.util.Date;
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
    private final IProductDAO productDAO = new ProductDAO();

    @Override
    public ArrayList<String> getPawnCouponStatistic(Date dateFrom, Date dateTo) {
        @SuppressWarnings("CollectionWithoutInitialCapacity")
        ArrayList<String> results = new ArrayList<>();
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

        for (PawnCoupon pawnCoupon : pawnCouponDAO.getList()) {
            if (dateFrom != null && dateTo != null) {
                Date pawnDate = Support.stringToDate(pawnCoupon.getPawnDate(), Support.getDateFormat());
                if (dateFrom.compareTo(pawnDate) <= dateTo.compareTo(pawnDate)) {
                    totalPawnCouponCount++;
                    totalPawnCouponPrice += pawnCoupon.getPrice();
                }
            } else {
                totalPawnCouponCount++;
                totalPawnCouponPrice += pawnCoupon.getPrice();
            }
            switch (pawnCoupon.getStatus()) {
                case "Đã chuộc" -> {
                    if (dateFrom != null && dateTo != null) {
                        Date redeemDate = Support.stringToDate(pawnCoupon.getRedeem0rLiquidationDate(), Support.getDateFormat());
                        if (dateFrom.compareTo(redeemDate) <= dateTo.compareTo(redeemDate)) {
                            totalRedeemedPawnCouponCount++;
                            totalRedeemedPawnCouponPrice += pawnCoupon.getPrice();
                        }
                    } else {
                        totalNotRedeemedPawnCouponCount++;
                        totalNotRedeemedPawnCouponPrice += pawnCoupon.getPrice();
                    }
                }
                case "Chưa chuộc" -> {
                    totalNotRedeemedPawnCouponCount++;
                    totalNotRedeemedPawnCouponPrice += pawnCoupon.getPrice();
                }
                case "Trễ" -> {
                    totalNotRedeemedPawnCouponCount++;
                    totalNotRedeemedPawnCouponPrice += pawnCoupon.getPrice();
                }
                case "Cần thanh lý" -> {
                    totalNotRedeemedPawnCouponCount++;
                    totalNotRedeemedPawnCouponPrice += pawnCoupon.getPrice();
                }
                case "Đã thanh lý" -> {
                    if (dateFrom != null && dateTo != null) {
                        Date liquidationDate = Support.stringToDate(pawnCoupon.getRedeem0rLiquidationDate(), Support.getDateFormat());
                        if (dateFrom.compareTo(liquidationDate) <= dateTo.compareTo(liquidationDate)) {
                            totalLiquidationPawnCouponCount++;
                            totalLiquidatedPawnCouponPawnPrice += pawnCoupon.getPrice();
                            totalLiquidatedPawnCouponLiquidationPrice += pawnCoupon.getLiquidationPrice();
                            totalInterest += pawnCoupon.getLiquidationPrice() - pawnCoupon.getPrice();
                        }
                    } else {
                        totalNotRedeemedPawnCouponCount++;
                        totalNotRedeemedPawnCouponPrice += pawnCoupon.getPrice();
                        totalInterest += pawnCoupon.getLiquidationPrice() - pawnCoupon.getPrice();
                    }
                }
            }

            for (InterestPayment interestPayment : interestPaymentDAO.getList(pawnCoupon)) {
                if (dateFrom != null && dateTo != null) {
                    Date paymentDate = Support.stringToDate(interestPayment.getPaymentDate(), Support.getDateFormat());
                    if (dateFrom.compareTo(paymentDate) <= paymentDate.compareTo(dateTo)) {
                        totalInterest += interestPayment.getMoney();
                    }
                } else {
                    totalInterest += interestPayment.getMoney();
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
    public ArrayList<String> getCustomerStatistic(Date dateFrom, Date dateTo) {
        int totalCustomer = 0;
        String bestCustomerName = "";
        int bestCustomerPawnedCount = 0;
        long bestCustomerPawnedPrice = 0;
        long bestCustomerInterestPayed = 0;
        int totalStopServiceCount = 0;

        @SuppressWarnings("CollectionWithoutInitialCapacity")
        ArrayList<String> results = new ArrayList<>();

        for (Customer customer : customerDAO.getList()) {
            if (dateFrom != null && dateTo != null) {
                String strDateFrom = Support.dateToString(dateFrom, Support.getDateFormat());
                String strDateTo = Support.dateToString(dateTo, Support.getDateFormat());
                if (!activityHistoryDAO.findActivityHistoryByKey(strDateFrom, strDateTo, null, "Thêm mới", "Khách hàng", null).isEmpty()) {
                    totalCustomer++;
                }
            } else {
                totalCustomer++;
            }

            int totalPawnedCount = 0;
            long totalPawnedPrice = 0;
            long totalInterestPayed = 0;
            for (PawnCoupon pawnCoupon : pawnCouponDAO.findPawnCouponByCustomerKey(customer)) {
                if (dateFrom != null && dateTo != null) {
                    Date pawnDate = Support.stringToDate(pawnCoupon.getPawnDate(), Support.getDateFormat());
                    if (dateFrom.compareTo(pawnDate) <= dateTo.compareTo(pawnDate)) {
                        totalPawnedCount++;
                        totalPawnedPrice += pawnCoupon.getPrice();
                    }
                } else {
                    totalPawnedCount++;
                    totalPawnedPrice += pawnCoupon.getPrice();
                }
                for (InterestPayment interestPayment : interestPaymentDAO.getList(pawnCoupon)) {
                    if (dateFrom != null && dateTo != null) {
                        Date pawnDate = Support.stringToDate(interestPayment.getPaymentDate(), Support.getDateFormat());
                        if (dateFrom.compareTo(pawnDate) <= dateTo.compareTo(pawnDate)) {
                            totalInterestPayed += interestPayment.getMoney();
                        }
                    } else {
                        totalInterestPayed += interestPayment.getMoney();
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

            if (dateFrom != null && dateTo != null) {
                String strDateFrom = Support.dateToString(dateFrom, Support.getDateFormat());
                String strDateTo = Support.dateToString(dateTo, Support.getDateFormat());
                if (!activityHistoryDAO.findActivityHistoryByKey(strDateFrom, strDateTo, null, "Sửa", "Khách hàng", "Ngưng phục vụ").isEmpty()
                        && activityHistoryDAO.findActivityHistoryByKey(strDateTo, strDateTo, null, "Sửa", "Khách hàng", "Phục vụ").isEmpty()) {
                    totalStopServiceCount++;
                }
            } else {
                if (customer.getDeleteflag()) {
                    totalStopServiceCount++;
                }
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
    public ArrayList<String> getTypeOfProductStatistic(Date dateFrom, Date dateTo) {

        int typeOfProductCount = 0;
        int productCount = 0;
        String bestTypeOfProductName = "";
        int bestTypeOfProductPawnedCount = 0;
        long bestTypeOfProductPawnedPrice = 0;
        long bestTypeOfProductInterestPayed = 0;
        int totalStopServiceCount = 0;

        @SuppressWarnings("CollectionWithoutInitialCapacity")
        ArrayList<String> results = new ArrayList<>();

        for (TypeOfProduct typeOfProduct : typeOfProductDAO.getList()) {
            typeOfProductCount++;
            if (dateFrom != null && dateTo != null) {
                String strDateFrom = Support.dateToString(dateFrom, Support.getDateFormat());
                String strDateTo = Support.dateToString(dateTo, Support.getDateFormat());
                if (!activityHistoryDAO.findActivityHistoryByKey(strDateFrom, strDateTo, null, "Thêm mới", "Hàng hóa", typeOfProduct.getId()).isEmpty()) {
                    productCount++;
                }
            } else {
                productCount += productDAO.findProductByTypeOfProductKey(typeOfProduct).size();
            }

            int totalPawnedCount = 0;
            long totalPawnedPrice = 0;
            long totalInterestPayed = 0;
            for (PawnCoupon pawnCoupon : pawnCouponDAO.getList()) {
                if (typeOfProduct.getId().equals(pawnCoupon.getProduct().getTypeOfProduct().getId())) {
                    if (dateFrom != null && dateTo != null) {
                        Date pawnDate = Support.stringToDate(pawnCoupon.getPawnDate(), Support.getDateFormat());
                        if (dateFrom.compareTo(pawnDate) <= dateTo.compareTo(pawnDate)) {
                            totalPawnedCount++;
                            totalPawnedPrice += pawnCoupon.getPrice();
                            for (InterestPayment interestPayment : interestPaymentDAO.getList(pawnCoupon)) {
                                Date paymentDate = Support.stringToDate(interestPayment.getPaymentDate(), Support.getDateFormat());
                                if (dateFrom.compareTo(paymentDate) <= dateTo.compareTo(paymentDate)) {
                                    totalInterestPayed += interestPayment.getMoney();
                                }
                            }
                        }
                    } else {
                        totalPawnedCount++;
                        totalPawnedPrice += pawnCoupon.getPrice();
                        for (InterestPayment interestPayment : interestPaymentDAO.getList(pawnCoupon)) {
                            totalInterestPayed += interestPayment.getMoney();
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

            if (dateFrom != null && dateTo != null) {
                String strDateFrom = Support.dateToString(dateFrom, Support.getDateFormat());
                String strDateTo = Support.dateToString(dateTo, Support.getDateFormat());
                if (!activityHistoryDAO.findActivityHistoryByKey(strDateFrom, strDateTo, null, "Sửa", "Loại hàng hóa", "Ngưng phục vụ").isEmpty()
                        && activityHistoryDAO.findActivityHistoryByKey(strDateTo, strDateTo, null, "Sửa", "Loại hàng hóa", "Phục vụ").isEmpty()) {
                    totalStopServiceCount++;
                }
            } else {
                if (typeOfProduct.getDeleteflag()) {
                    totalStopServiceCount++;
                }
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
