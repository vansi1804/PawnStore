/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.JTabbedPaneForm;

import Controller.ActivityHistoryController;
import Controller.CustomerController;
import Controller.InterestPaymentController;
import Controller.PawnCouponController;
import Controller.TypeOfProductController;
import Model.Customer;
import Model.InterestPayment;
import Model.PawnCoupon;
import Model.TypeOfProduct;
import Support.MessageSupport;
import Support.Support;
import View.JHomePageForm;
import java.beans.PropertyChangeEvent;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author NVS
 */
@SuppressWarnings("ClassWithoutLogger")
public class JStatisticPanelForm extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;

    /**
     * Creates new form jStatisticPanelForm
     */
    public JStatisticPanelForm() {
        initComponents();
        jchbAll.setSelected(true);
        setPawnCouponStatistics();
        setCustomerStatistics();
        setTypeOfProductStatistics();
        setDateFindValueChage();
    }

    private void setPawnCouponStatistics() {
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

        for (PawnCoupon pawnCoupon : PawnCouponController.getCurrentInstance().getList()) {
            if (!jchbAll.isSelected()) {
                Date pawnDate = Support.stringToDate(pawnCoupon.getPawnDate(), Support.getDateFormat());
                if (pawnDate.compareTo(jdcDateFrom.getDate()) >= 0 && pawnDate.compareTo(jdcDateTo.getDate()) <= 0) {
                    totalPawnCouponCount++;
                    totalPawnCouponPrice += pawnCoupon.getPrice();
                }
            } else {
                totalPawnCouponCount++;
                totalPawnCouponPrice += pawnCoupon.getPrice();
            }
            switch (pawnCoupon.getStatus()) {
                case "Đã chuộc" -> {
                    if (!jchbAll.isSelected()) {
                        Date redeemDate = Support.stringToDate(pawnCoupon.getRedeem0rLiquidationDate(), Support.getDateFormat());
                        if (redeemDate.compareTo(jdcDateFrom.getDate()) >= 0 && redeemDate.compareTo(jdcDateTo.getDate()) <= 0) {
                            totalRedeemedPawnCouponCount++;
                            totalRedeemedPawnCouponPrice += pawnCoupon.getPrice();
                        }
                    } else {
                        totalNotRedeemedPawnCouponCount++;
                        totalNotRedeemedPawnCouponPrice += pawnCoupon.getPrice();
                        totalInterest += pawnCoupon.getLiquidationPrice() - pawnCoupon.getPrice();
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
                    if (!jchbAll.isSelected()) {
                        Date liquidationDate = Support.stringToDate(pawnCoupon.getRedeem0rLiquidationDate(), Support.getDateFormat());
                        if (liquidationDate.compareTo(jdcDateFrom.getDate()) >= 0 && liquidationDate.compareTo(jdcDateTo.getDate()) <= 0) {
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

            for (InterestPayment interestPayment : InterestPaymentController.getCurrentInstance().getList(pawnCoupon)) {
                if (!jchbAll.isSelected()) {
                    Date paymentDate = Support.stringToDate(interestPayment.getPaymentDate(), Support.getDateFormat());
                    if (paymentDate.compareTo(jdcDateFrom.getDate()) >= 0 && paymentDate.compareTo(jdcDateTo.getDate()) <= 0) {
                        totalInterest += interestPayment.getMoney();
                    }
                } else {
                    totalInterest += interestPayment.getMoney();
                }
            }
        }

        jlb1_2.setText(Support.getFormatNumber(totalPawnCouponCount));
        jlb1_3.setText(Support.getFormatNumber(totalPawnCouponPrice));
        jlb2_2.setText(Support.getFormatNumber(totalNotRedeemedPawnCouponCount));
        jlb2_3.setText(Support.getFormatNumber(totalNotRedeemedPawnCouponPrice));
        jlb3_2.setText(Support.getFormatNumber(totalRedeemedPawnCouponCount));
        jlb3_3.setText(Support.getFormatNumber(totalRedeemedPawnCouponPrice));
        jlb4_2.setText(Support.getFormatNumber(totalLiquidationPawnCouponCount));
        jlb4_3.setText(Support.getFormatNumber(totalLiquidatedPawnCouponPawnPrice));
        jlb4_4.setText(Support.getFormatNumber(totalLiquidatedPawnCouponLiquidationPrice));
        jlb5_2.setText(Support.getFormatNumber(totalInterest));

    }

    private void setCustomerStatistics() {
        int totalCustomerCount = 0;
        String bestPawnedCountCustomerName = "";
        long bestCustomerInterestPayed = 0;
        int bestCustomerPawnedCount = 0;
        long bestCustomerPawnedPrice = 0;
        int totalStopServingCustomerCount = 0;

        for (Customer customer : CustomerController.getCurrentInstance().getList()) {
            if (!jchbAll.isSelected()) {
                if (!ActivityHistoryController.getCurrentInstance()
                        .findActivityHistoryByKey(jdcDateFrom.getDate(), jdcDateTo.getDate(), null, "Thêm", "Khách hàng", customer.getId()).isEmpty()) {
                    totalCustomerCount++;
                }
            } else {
                totalCustomerCount++;
            }
            if (customer.getDeleteflag()) {
                totalStopServingCustomerCount++;
            }
            int pawnedCount = 0;
            long interestPayed = 0;
            long pawnedPrice = 0;

            for (PawnCoupon pawnCoupon : PawnCouponController.getCurrentInstance().findPawnCouponByCustomerKey(customer)) {
                if (!jchbAll.isSelected()) {
                    Date pawnDate = Support.stringToDate(pawnCoupon.getPawnDate(), Support.getDateFormat());
                    if (pawnDate.compareTo(jdcDateFrom.getDate()) >= 0 && pawnDate.compareTo(jdcDateTo.getDate()) <= 0) {
                        pawnedCount++;
                        pawnedPrice += pawnCoupon.getPrice();
                        for (InterestPayment interestPayment : InterestPaymentController.getCurrentInstance().getList(pawnCoupon)) {
                            interestPayed += interestPayment.getMoney();
                        }
                    }
                } else {
                    pawnedCount++;
                    pawnedPrice++;
                    for (InterestPayment interestPayment : InterestPaymentController.getCurrentInstance().getList(pawnCoupon)) {
                        interestPayed += interestPayment.getMoney();
                    }
                }
            }
            if (pawnedCount > bestCustomerPawnedCount) {
                bestCustomerPawnedCount = pawnedCount;
                bestCustomerPawnedPrice = pawnedPrice;
                bestCustomerInterestPayed = interestPayed;
                bestPawnedCountCustomerName = customer.getId() + "-" + customer.getFullname();
            } else if (pawnedCount == bestCustomerPawnedCount && pawnedPrice > bestCustomerPawnedPrice) {
                bestCustomerPawnedCount = pawnedCount;
                bestCustomerPawnedPrice = pawnedPrice;
                bestCustomerInterestPayed = interestPayed;
                bestPawnedCountCustomerName = customer.getId() + "-" + customer.getFullname();
            } else if (pawnedPrice == bestCustomerPawnedPrice && interestPayed > bestCustomerInterestPayed) {
                bestCustomerPawnedCount = pawnedCount;
                bestCustomerPawnedPrice = pawnedPrice;
                bestCustomerInterestPayed = interestPayed;
                bestPawnedCountCustomerName = customer.getId() + "-" + customer.getFullname();
            } else if (interestPayed == bestCustomerInterestPayed) {
                bestCustomerPawnedCount = pawnedCount;
                bestCustomerPawnedPrice = pawnedPrice;
                bestCustomerInterestPayed = interestPayed;
                bestPawnedCountCustomerName += ", " + customer.getId() + "-" + customer.getFullname();
            }
            if (bestCustomerPawnedCount == 0) {
                bestPawnedCountCustomerName = "";
            }
        }

        jlb6_2.setText(Support.getFormatNumber(totalCustomerCount));
        jlb7_2.setText(bestPawnedCountCustomerName);
        jlb7_3.setText(Support.getFormatNumber(bestCustomerPawnedCount));
        jlb7_4.setText(Support.getFormatNumber(bestCustomerPawnedPrice));
        jlb7_5.setText(Support.getFormatNumber(bestCustomerInterestPayed));
        jlb8_2.setText(Support.getFormatNumber(totalStopServingCustomerCount));
    }

    @SuppressWarnings("UnusedAssignment")
    private void setTypeOfProductStatistics() {
        int totalTypeOfProductCount = 0;
        int totalProductCount = 0;
        String bestTypeOfProductName = "";
        int bestPawnedTypeOfProductCount = 0;
        long bestTypeOfProductPawnedPrice = 0;
        long bestTypeOfProductInterestPayed = 0;
        int totalStopServingTypeOfProductCount = 0;

        for (TypeOfProduct typeOfProduct : TypeOfProductController.getCurrentInstance().getList()) {
            totalTypeOfProductCount++;
            if (typeOfProduct.getDeleteflag()) {
                totalStopServingTypeOfProductCount++;
            }
            if (!jchbAll.isSelected()) {
                totalProductCount += ActivityHistoryController.getCurrentInstance()
                        .findActivityHistoryByKey(jdcDateFrom.getDate(), jdcDateTo.getDate(),
                                null, "Thêm", "Hàng hóa", typeOfProduct.getId()).size();
            } else {
                totalProductCount = PawnCouponController.getCurrentInstance().getList().size();
            }

            int pawnedTypeOfProductCount = 0;
            long typeOfProductPawnedPrice = 0;
            long typeOfProductInterestPayed = 0;
            for (PawnCoupon pawnCoupon : PawnCouponController.getCurrentInstance().getList()) {
                if (!jchbAll.isSelected()) {
                    Date pawnDate = Support.stringToDate(pawnCoupon.getPawnDate(), Support.getDateFormat());
                    if (pawnDate.compareTo(jdcDateFrom.getDate()) >= 0 && pawnDate.compareTo(jdcDateTo.getDate()) <= 0) {
                        if (pawnCoupon.getProduct().getTypeOfProduct().getId().equals(typeOfProduct.getId())) {
                            pawnedTypeOfProductCount++;
                            typeOfProductPawnedPrice += pawnCoupon.getPrice();
                            for (InterestPayment interestPayment : InterestPaymentController.getCurrentInstance().getList(pawnCoupon)) {
                                typeOfProductInterestPayed += interestPayment.getMoney();
                            }
                        }
                    }
                } else {
                    if (pawnCoupon.getProduct().getTypeOfProduct().getId().equals(typeOfProduct.getId())) {
                        pawnedTypeOfProductCount++;
                        typeOfProductPawnedPrice += pawnCoupon.getPrice();
                        for (InterestPayment interestPayment : InterestPaymentController.getCurrentInstance().getList(pawnCoupon)) {
                            typeOfProductInterestPayed += interestPayment.getMoney();
                        }
                    }
                }
            }
            if (pawnedTypeOfProductCount > bestPawnedTypeOfProductCount) {
                bestPawnedTypeOfProductCount = pawnedTypeOfProductCount;
                bestTypeOfProductPawnedPrice = typeOfProductPawnedPrice;
                bestTypeOfProductInterestPayed = typeOfProductInterestPayed;
                bestTypeOfProductName = typeOfProduct.getName();
            } else if (pawnedTypeOfProductCount == bestPawnedTypeOfProductCount && typeOfProductPawnedPrice > bestTypeOfProductPawnedPrice) {
                bestPawnedTypeOfProductCount = pawnedTypeOfProductCount;
                bestTypeOfProductPawnedPrice = typeOfProductPawnedPrice;
                bestTypeOfProductInterestPayed = typeOfProductInterestPayed;
                bestTypeOfProductName = typeOfProduct.getName();
            }else if (typeOfProductPawnedPrice == bestTypeOfProductPawnedPrice && typeOfProductInterestPayed > bestTypeOfProductInterestPayed) {
                bestPawnedTypeOfProductCount = pawnedTypeOfProductCount;
                bestTypeOfProductPawnedPrice = typeOfProductPawnedPrice;
                bestTypeOfProductInterestPayed = typeOfProductInterestPayed;
                bestTypeOfProductName = typeOfProduct.getName();
            }else if (typeOfProductInterestPayed == bestTypeOfProductInterestPayed) {
                bestPawnedTypeOfProductCount = pawnedTypeOfProductCount;
                bestTypeOfProductPawnedPrice = typeOfProductPawnedPrice;
                bestTypeOfProductInterestPayed = typeOfProductInterestPayed;
                bestTypeOfProductName += ", "+ typeOfProduct.getName();
            }
        }

        jlb9_3.setText(Support.getFormatNumber(totalTypeOfProductCount));
        jlb9_2.setText(Support.getFormatNumber(totalProductCount));
        jlb10_2.setText(bestTypeOfProductName);
        jlb10_3.setText(Support.getFormatNumber(bestPawnedTypeOfProductCount));
        jlb10_4.setText(Support.getFormatNumber(bestTypeOfProductPawnedPrice));
        jlb10_5.setText(Support.getFormatNumber(bestTypeOfProductInterestPayed));
        jlb11_2.setText(Support.getFormatNumber(totalStopServingTypeOfProductCount));

    }

    private void setDateFindValueChage() {
        jdcDateFrom.addPropertyChangeListener((PropertyChangeEvent evt) -> {
            if (jdcDateFrom.getDate() != null && jdcDateTo.getDate() != null) {
                if (jdcDateFrom.getDate().compareTo(jdcDateTo.getDate()) > 0) {
                    MessageSupport.Message("Lỗi", "Ngày tìm kiếm 1 không được sau ngày tìm kiếm 2.");
                } else {
                    setPawnCouponStatistics();
                    setCustomerStatistics();
                    setTypeOfProductStatistics();
                }
            }
        });

        jdcDateTo.addPropertyChangeListener((PropertyChangeEvent evt) -> {
            if (jdcDateFrom.getDate() != null && jdcDateTo.getDate() != null) {
                if (jdcDateTo.getDate().compareTo(jdcDateFrom.getDate()) < 0) {
                    MessageSupport.Message("Lỗi", "Ngày tìm kiếm 2 không được sau ngày tìm kiếm 1.");
                } else {
                    setPawnCouponStatistics();
                    setCustomerStatistics();
                    setTypeOfProductStatistics();
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel7 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jbtnDelete = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jdcDateFrom = new com.toedter.calendar.JDateChooser();
        jdcDateTo = new com.toedter.calendar.JDateChooser();
        jchbAll = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jlb1_1 = new javax.swing.JLabel();
        jlb1_2 = new javax.swing.JLabel();
        jlb1_3 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jlb2_2 = new javax.swing.JLabel();
        jlb2_1 = new javax.swing.JLabel();
        jlb2_3 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jlb3_1 = new javax.swing.JLabel();
        jlb3_3 = new javax.swing.JLabel();
        jlb3_2 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jlb4_1 = new javax.swing.JLabel();
        jlb4_2 = new javax.swing.JLabel();
        jlb4_3 = new javax.swing.JLabel();
        jlb4_4 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jlb5_1 = new javax.swing.JLabel();
        jlb5_2 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jlb6_1 = new javax.swing.JLabel();
        jlb6_2 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jlb8_1 = new javax.swing.JLabel();
        jlb8_2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jlb7_1 = new javax.swing.JLabel();
        jlb7_2 = new javax.swing.JLabel();
        jlb7_3 = new javax.swing.JLabel();
        jlb7_4 = new javax.swing.JLabel();
        jlb7_5 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jlb9_1 = new javax.swing.JLabel();
        jlb9_3 = new javax.swing.JLabel();
        jlb9_2 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jlb11_1 = new javax.swing.JLabel();
        jlb11_2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jlb10_1 = new javax.swing.JLabel();
        jlb10_2 = new javax.swing.JLabel();
        jlb10_3 = new javax.swing.JLabel();
        jlb10_4 = new javax.swing.JLabel();
        jlb10_5 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel14.setText("jLabel14");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jButton1.setText("jButton1");

        setBackground(new java.awt.Color(0, 255, 255));

        jPanel1.setBackground(new java.awt.Color(0, 255, 255));

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("THỐNG KÊ");

        jbtnDelete.setFont(new java.awt.Font("Dialog", 1, 35)); // NOI18N
        jbtnDelete.setForeground(new java.awt.Color(255, 0, 0));
        jbtnDelete.setText("X");
        jbtnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnDelete))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jbtnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel9.setBackground(new java.awt.Color(204, 204, 204));

        jPanel8.setBackground(new java.awt.Color(204, 204, 204));

        jdcDateFrom.setBackground(new java.awt.Color(204, 204, 204));
        jdcDateFrom.setForeground(new java.awt.Color(0, 0, 0));
        jdcDateFrom.setDateFormatString("dd/MM/yyyy");
        jdcDateFrom.setEnabled(false);
        jdcDateFrom.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N

        jdcDateTo.setBackground(new java.awt.Color(204, 204, 204));
        jdcDateTo.setForeground(new java.awt.Color(0, 0, 0));
        jdcDateTo.setDateFormatString("dd/MM/yyyy");
        jdcDateTo.setEnabled(false);
        jdcDateTo.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N

        jchbAll.setBackground(new java.awt.Color(204, 204, 204));
        jchbAll.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jchbAll.setForeground(new java.awt.Color(0, 0, 0));
        jchbAll.setSelected(true);
        jchbAll.setText("Tất cả");
        jchbAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jchbAllActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Từ ngày");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Đến ngày");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jchbAll, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jdcDateFrom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jdcDateTo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jdcDateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jdcDateTo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jchbAll, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel11.setBackground(new java.awt.Color(204, 204, 204));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Khách hàng");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel12.setBackground(new java.awt.Color(204, 204, 204));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Loại hàng hóa");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel10.setBackground(new java.awt.Color(0, 255, 255));

        jPanel13.setBackground(new java.awt.Color(0, 0, 204));

        jlb1_1.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jlb1_1.setForeground(new java.awt.Color(255, 255, 255));
        jlb1_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb1_1.setText("Tổng");

        jlb1_2.setFont(new java.awt.Font("Times New Roman", 3, 20)); // NOI18N
        jlb1_2.setForeground(new java.awt.Color(255, 255, 255));
        jlb1_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb1_2.setText("Số lượng");

        jlb1_3.setFont(new java.awt.Font("Times New Roman", 3, 25)); // NOI18N
        jlb1_3.setForeground(new java.awt.Color(255, 255, 255));
        jlb1_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb1_3.setText("Giá cầm");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlb1_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jlb1_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jlb1_3, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addComponent(jlb1_1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlb1_2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlb1_3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel15.setBackground(new java.awt.Color(204, 0, 204));

        jlb2_2.setFont(new java.awt.Font("Times New Roman", 3, 20)); // NOI18N
        jlb2_2.setForeground(new java.awt.Color(255, 255, 255));
        jlb2_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb2_2.setText("Số lượng");

        jlb2_1.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jlb2_1.setForeground(new java.awt.Color(255, 255, 255));
        jlb2_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb2_1.setText("Chưa chuộc");

        jlb2_3.setFont(new java.awt.Font("Times New Roman", 3, 25)); // NOI18N
        jlb2_3.setForeground(new java.awt.Color(255, 255, 255));
        jlb2_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb2_3.setText("Giá cầm");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlb2_1, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
            .addComponent(jlb2_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jlb2_3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addComponent(jlb2_1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlb2_2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlb2_3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel16.setBackground(new java.awt.Color(255, 0, 0));

        jlb3_1.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jlb3_1.setForeground(new java.awt.Color(255, 255, 255));
        jlb3_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb3_1.setText("Đã chuộc");

        jlb3_3.setFont(new java.awt.Font("Times New Roman", 3, 25)); // NOI18N
        jlb3_3.setForeground(new java.awt.Color(255, 255, 255));
        jlb3_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb3_3.setText("Giá chuộc");

        jlb3_2.setFont(new java.awt.Font("Times New Roman", 3, 20)); // NOI18N
        jlb3_2.setForeground(new java.awt.Color(255, 255, 255));
        jlb3_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb3_2.setText("Số lượng");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlb3_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jlb3_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jlb3_3, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addComponent(jlb3_1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlb3_2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlb3_3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel17.setBackground(new java.awt.Color(204, 204, 0));

        jlb4_1.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jlb4_1.setForeground(new java.awt.Color(255, 255, 255));
        jlb4_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb4_1.setText("Đã thanh lý");

        jlb4_2.setFont(new java.awt.Font("Times New Roman", 3, 20)); // NOI18N
        jlb4_2.setForeground(new java.awt.Color(255, 255, 255));
        jlb4_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb4_2.setText("Số lượng");

        jlb4_3.setFont(new java.awt.Font("Times New Roman", 3, 25)); // NOI18N
        jlb4_3.setForeground(new java.awt.Color(255, 255, 255));
        jlb4_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb4_3.setText("Giá cầm");

        jlb4_4.setFont(new java.awt.Font("Times New Roman", 3, 25)); // NOI18N
        jlb4_4.setForeground(new java.awt.Color(255, 255, 255));
        jlb4_4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb4_4.setText("Giá TL");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlb4_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jlb4_3, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlb4_4, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jlb4_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addComponent(jlb4_1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlb4_2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlb4_3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlb4_4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel18.setBackground(new java.awt.Color(0, 204, 51));

        jlb5_1.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jlb5_1.setForeground(new java.awt.Color(255, 255, 255));
        jlb5_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb5_1.setText("Lãi");

        jlb5_2.setFont(new java.awt.Font("Times New Roman", 3, 30)); // NOI18N
        jlb5_2.setForeground(new java.awt.Color(255, 255, 255));
        jlb5_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb5_2.setText("Lãi");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlb5_2, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
            .addComponent(jlb5_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addComponent(jlb5_1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlb5_2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel19.setBackground(new java.awt.Color(0, 0, 204));

        jlb6_1.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jlb6_1.setForeground(new java.awt.Color(255, 255, 255));
        jlb6_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb6_1.setText("Tổng");

        jlb6_2.setFont(new java.awt.Font("Times New Roman", 3, 25)); // NOI18N
        jlb6_2.setForeground(new java.awt.Color(255, 255, 255));
        jlb6_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb6_2.setText("Số lượng");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlb6_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jlb6_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addComponent(jlb6_1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlb6_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel20.setBackground(new java.awt.Color(204, 0, 0));

        jlb8_1.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jlb8_1.setForeground(new java.awt.Color(255, 255, 255));
        jlb8_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb8_1.setText("Ngưng phục vụ");

        jlb8_2.setFont(new java.awt.Font("Times New Roman", 3, 25)); // NOI18N
        jlb8_2.setForeground(new java.awt.Color(255, 255, 255));
        jlb8_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb8_2.setText("Số lượng");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlb8_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jlb8_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addComponent(jlb8_1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlb8_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        jPanel6.setBackground(new java.awt.Color(0, 204, 204));

        jlb7_1.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jlb7_1.setForeground(new java.awt.Color(255, 255, 255));
        jlb7_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb7_1.setText("Cầm nhiều");

        jlb7_2.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jlb7_2.setForeground(new java.awt.Color(255, 255, 255));
        jlb7_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb7_2.setText("Tên khách hàng");

        jlb7_3.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jlb7_3.setForeground(new java.awt.Color(255, 255, 255));
        jlb7_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb7_3.setText("Số lần cầm");

        jlb7_4.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        jlb7_4.setForeground(new java.awt.Color(255, 255, 255));
        jlb7_4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb7_4.setText("Tổng giá cầm");

        jlb7_5.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        jlb7_5.setForeground(new java.awt.Color(255, 255, 255));
        jlb7_5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb7_5.setText("Tổng lãi");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlb7_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jlb7_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jlb7_3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jlb7_4, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlb7_5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jlb7_1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlb7_2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlb7_3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlb7_5, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(jlb7_4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel21.setBackground(new java.awt.Color(0, 0, 204));

        jlb9_1.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jlb9_1.setForeground(new java.awt.Color(255, 255, 255));
        jlb9_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb9_1.setText("Tổng");

        jlb9_3.setFont(new java.awt.Font("Times New Roman", 3, 30)); // NOI18N
        jlb9_3.setForeground(new java.awt.Color(255, 255, 255));
        jlb9_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb9_3.setText("Số lượng");

        jlb9_2.setFont(new java.awt.Font("Times New Roman", 3, 30)); // NOI18N
        jlb9_2.setForeground(new java.awt.Color(255, 255, 255));
        jlb9_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb9_2.setText("Số lượng");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlb9_3, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
            .addComponent(jlb9_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jlb9_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addComponent(jlb9_1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlb9_2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlb9_3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel22.setBackground(new java.awt.Color(204, 0, 0));

        jlb11_1.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jlb11_1.setForeground(new java.awt.Color(255, 255, 255));
        jlb11_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb11_1.setText("Ngưng phục vụ");

        jlb11_2.setFont(new java.awt.Font("Times New Roman", 3, 30)); // NOI18N
        jlb11_2.setForeground(new java.awt.Color(255, 255, 255));
        jlb11_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb11_2.setText("Số lượng");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlb11_1, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
            .addComponent(jlb11_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addComponent(jlb11_1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlb11_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(0, 204, 204));

        jlb10_1.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jlb10_1.setForeground(new java.awt.Color(255, 255, 255));
        jlb10_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb10_1.setText("Cầm nhiều");

        jlb10_2.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jlb10_2.setForeground(new java.awt.Color(255, 255, 255));
        jlb10_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb10_2.setText("Tên loại");

        jlb10_3.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jlb10_3.setForeground(new java.awt.Color(255, 255, 255));
        jlb10_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb10_3.setText("Số lần cầm");

        jlb10_4.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        jlb10_4.setForeground(new java.awt.Color(255, 255, 255));
        jlb10_4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb10_4.setText("Tổng giá cầm");

        jlb10_5.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        jlb10_5.setForeground(new java.awt.Color(255, 255, 255));
        jlb10_5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb10_5.setText("Tổng lãi");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlb10_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jlb10_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jlb10_3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jlb10_4, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlb10_5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jlb10_1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlb10_2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlb10_3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlb10_5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlb10_4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel23.setBackground(new java.awt.Color(0, 255, 255));

        jButton2.setBackground(new java.awt.Color(204, 204, 204));
        jButton2.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setText("Tải lại");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addComponent(jButton2)
                .addGap(0, 5, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    @SuppressWarnings({"deprecation"})
    private void jchbAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jchbAllActionPerformed
        if (!jchbAll.isSelected()) {
            jdcDateFrom.setEnabled(true);
            Date dateFrom = Support.stringToDate(
                    String.valueOf(Calendar.getInstance().getActualMinimum(Calendar.DATE))
                    + "/" + String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1)
                    + "/2022",
                    Support.getDateFormat());
            jdcDateFrom.setDate(dateFrom);
            jdcDateTo.setEnabled(true);
            Date dateTo = Support.stringToDate(
                    String.valueOf(Calendar.getInstance().getActualMaximum(Calendar.DATE))
                    + "/" + String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1)
                    + "/2022",
                    Support.getDateFormat());
            jdcDateTo.setDate(dateTo);
            jlb1_1.setText("Mới");
            jlb6_1.setText("Mới");
            jlb9_1.setText("Mới");
        } else {
            jdcDateFrom.setEnabled(false);
            jdcDateFrom.setDate(null);
            jdcDateTo.setEnabled(false);
            jdcDateTo.setDate(null);
            jlb1_1.setText("Tổng");
            jlb6_1.setText("Tổng");
            jlb9_1.setText("Tổng");
        }
    }//GEN-LAST:event_jchbAllActionPerformed

    private void jbtnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDeleteActionPerformed
        JHomePageForm.jHomePageTabbedPane.remove(JHomePageForm.jHomePageTabbedPane.indexOfTab("Thống kê"));
    }//GEN-LAST:event_jbtnDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JButton jbtnDelete;
    private javax.swing.JCheckBox jchbAll;
    private com.toedter.calendar.JDateChooser jdcDateFrom;
    private com.toedter.calendar.JDateChooser jdcDateTo;
    private javax.swing.JLabel jlb10_1;
    private javax.swing.JLabel jlb10_2;
    private javax.swing.JLabel jlb10_3;
    private javax.swing.JLabel jlb10_4;
    private javax.swing.JLabel jlb10_5;
    private javax.swing.JLabel jlb11_1;
    private javax.swing.JLabel jlb11_2;
    private javax.swing.JLabel jlb1_1;
    private javax.swing.JLabel jlb1_2;
    private javax.swing.JLabel jlb1_3;
    private javax.swing.JLabel jlb2_1;
    private javax.swing.JLabel jlb2_2;
    private javax.swing.JLabel jlb2_3;
    private javax.swing.JLabel jlb3_1;
    private javax.swing.JLabel jlb3_2;
    private javax.swing.JLabel jlb3_3;
    private javax.swing.JLabel jlb4_1;
    private javax.swing.JLabel jlb4_2;
    private javax.swing.JLabel jlb4_3;
    private javax.swing.JLabel jlb4_4;
    private javax.swing.JLabel jlb5_1;
    private javax.swing.JLabel jlb5_2;
    private javax.swing.JLabel jlb6_1;
    private javax.swing.JLabel jlb6_2;
    private javax.swing.JLabel jlb7_1;
    private javax.swing.JLabel jlb7_2;
    private javax.swing.JLabel jlb7_3;
    private javax.swing.JLabel jlb7_4;
    private javax.swing.JLabel jlb7_5;
    private javax.swing.JLabel jlb8_1;
    private javax.swing.JLabel jlb8_2;
    private javax.swing.JLabel jlb9_1;
    private javax.swing.JLabel jlb9_2;
    private javax.swing.JLabel jlb9_3;
    // End of variables declaration//GEN-END:variables
}
