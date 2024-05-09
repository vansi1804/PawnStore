/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.JTabbedPaneForm;

import Common.Default;
import Controller.ActivityHistoryController;
import Controller.CustomerController;
import Controller.InterestPaymentController;
import Controller.PawnCouponController;
import Controller.ProductController;
import Model.ActivityHistory;
import Model.Customer;
import Model.InterestPayment;
import Model.PawnCoupon;
import Model.Product;
import Support.CheckSupport;
import Support.ColorFormatSupport;
import Support.MessageSupport;
import Support.Support;
import View.HomePageJFrameForm;
import View.PawnCouponInPaperJFrameForm;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("ClassWithoutLogger")
public class PawnCouponJPanelForm extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;

    public PawnCouponJPanelForm() {
        initComponents();
        setPawnCouponDefault(null);
        setTableFormat();
    }

    PawnCouponJPanelForm(Customer customer) {
        initComponents();
        PawnCoupon pawnCoupon = new PawnCoupon();
        pawnCoupon.setCustomer(customer);
        createNewPawnCoupon(pawnCoupon, false);
        setTableFormat();
    }

    PawnCouponJPanelForm(Product product) {
        initComponents();
        PawnCoupon pawnCoupon = new PawnCoupon();
        pawnCoupon.setProduct(product);
        createNewPawnCoupon(pawnCoupon, false);
        setTableFormat();
    }

    PawnCouponJPanelForm(PawnCoupon pawnCoupon) {
        initComponents();
        setPawnCouponDefault(pawnCoupon);
        setTableFormat();
    }

    private void setTableFormat() {
        ColorFormatSupport.FormatTableHeader(jtblPawnCoupon);
        ColorFormatSupport.setDataTableCenter(jtblPawnCoupon);
        ColorFormatSupport.FormatTableHeader(jtblInterestPayment);
        ColorFormatSupport.setDataTableCenter(jtblInterestPayment);
    }

    //======================================================================\\
    private void setPawnCouponDefault(PawnCoupon pawnCoupon) {
        jbtnRepawn.setEnabled(true);
        jbtnAddPawnCoupon.setEnabled(false);
        setCBCustomer(CustomerController.getCurrentInstance().findAll());
        setCBProduct(ProductController.getCurrentInstance().findAll());
        if (pawnCoupon == null) {
            jtfPawnCouponId.setText(null);
            jtfPawnCouponId.setEditable(true);
            jtfAmount.setText(null);
            jtfAmount.setEditable(true);
            jtfPawnPrice.setText(null);
            jtfPawnPrice.setEditable(true);
            jtfInterestRate.setText(null);
            jtfInterestRate.setEditable(true);
            jdcPawnDate.setDate(null);
            jdcPawnDate.setEnabled(true);
            jdcTheNextInterestPaymentDate.setDate(null);
            jdcTheNextInterestPaymentDate.setEnabled(true);
            jdcRedemptionOrLiquidationDate.setDate(null);
            jdcRedemptionOrLiquidationDate.setEnabled(true);
            jdcRedemptionOrLiquidationDate.setDate(null);
            jdcRedemptionOrLiquidationDate.setEnabled(true);
            jtfLiquidationPrice.setText(null);
            jtfLiquidationPrice.setEditable(true);
            setPawnCouponStatus(null, false);
            jbtnRepawn.setEnabled(false);
            jbtnEditPawnCoupon.setEnabled(false);
            jbtnViewPawnCouponInPaper.setEnabled(false);
            setPawnCouponTable(PawnCouponController.getCurrentInstance().findAllByStatus(getPawnCouponStatus()));
        } else {
            jtfPawnCouponId.setText(pawnCoupon.getId());
            jtfPawnCouponId.setEditable(false);
            jcbCustomerId.setSelectedItem(pawnCoupon.getCustomer().getId());
            jcbCustomerId.setEnabled(!pawnCoupon.getStatus().equals("Đã chuộc") && !pawnCoupon.getStatus().equals("Đã thanh lý"));
            jcbProductId.setSelectedItem(pawnCoupon.getProduct().getId());
            jcbProductId.setEnabled(!pawnCoupon.getStatus().equals("Đã chuộc") && !pawnCoupon.getStatus().equals("Đã thanh lý"));
            jtfAmount.setText(String.valueOf(pawnCoupon.getAmount()));
            jtfAmount.setEditable(!pawnCoupon.getStatus().equals("Đã chuộc") && !pawnCoupon.getStatus().equals("Đã thanh lý"));
            jtfPawnPrice.setText(String.valueOf(pawnCoupon.getPrice()));
            jtfPawnPrice.setEditable(!pawnCoupon.getStatus().equals("Đã chuộc") && !pawnCoupon.getStatus().equals("Đã thanh lý"));
            jtfInterestRate.setText(String.valueOf(pawnCoupon.getInterestRate()));
            jtfInterestRate.setEditable(!pawnCoupon.getStatus().equals("Đã chuộc") && !pawnCoupon.getStatus().equals("Đã thanh lý"));
            jdcPawnDate.setDate(Support.stringToDate(pawnCoupon.getPawnDate(), Default.DATE_FORMAT));
            jdcPawnDate.setEnabled(false);
            jdcTheNextInterestPaymentDate.setDate(Support.stringToDate(pawnCoupon.getTheNextInterestPaymentDate(), Default.DATE_FORMAT));
            jdcTheNextInterestPaymentDate.setEnabled(false);
            jdcRedemptionOrLiquidationDate.setDate(Support.stringToDate(pawnCoupon.getRedemption0rLiquidationDate(), Default.DATE_FORMAT));
            jdcRedemptionOrLiquidationDate.setEnabled(false);
            jtfLiquidationPrice.setText(String.valueOf(pawnCoupon.getLiquidationPrice()));
            jtfLiquidationPrice.setEditable(false);
            setPawnCouponStatus(pawnCoupon.getStatus(), false);
            jbtnRepawn.setEnabled((pawnCoupon.getStatus().equals("Đã chuộc") || pawnCoupon.getStatus().equals("Đã thanh lý"))
                    && !pawnCoupon.getCustomer().getDeleteFlag() && !pawnCoupon.getProduct().getTypeOfProduct().getDeleteFlag());
            jbtnEditPawnCoupon.setEnabled(pawnCoupon.getStatus().equals("Chưa chuộc")
                    || pawnCoupon.getStatus().equals("Trễ")
                    || pawnCoupon.getStatus().equals("Cần thanh lý"));
            jbtnViewPawnCouponInPaper.setEnabled(true);
            setPawnCouponTable(PawnCouponController.getCurrentInstance().findAllByStatus(getPawnCouponStatus()));
            Support.setRowTableSelection(jtblPawnCoupon, 1, pawnCoupon.getId());
        }
        setInterestPaymentDefault(pawnCoupon, null);
    }

    private void setCBCustomer(List<Customer> customers) {
        jcbCustomerId.removeAllItems();
        jcbCustomerId.addItem("Tất cả");
        for (Customer customer : customers) {
            jcbCustomerId.addItem(customer.getId());
        }
        jcbCustomerId.setSelectedItem("Tất cả");
    }

    private void setCBProduct(List<Product> products) {
        jcbProductId.removeAllItems();
        jcbProductId.addItem("Tất cả");
        for (Product product : products) {
            jcbProductId.addItem(product.getId());
        }
        jcbProductId.setSelectedItem("Tất cả");
    }

    private void setPawnCouponStatus(String status, boolean isCreatingNew) {
        jrbLateStatus.setEnabled(!isCreatingNew);
        jrbRedeemedStatus.setEnabled(!isCreatingNew);
        jrbNeedToLiquidateStatus.setEnabled(!isCreatingNew);
        jrbLiquidatedStatus.setEnabled(!isCreatingNew);
        if (status == null) {
            jrbAllStatus.setEnabled(true);
            jrbNotRedeemingStatus.setSelected(true);
            jrbNotRedeemingStatus.setEnabled(true);
        } else {
            jrbAllStatus.setEnabled(false);
            switch (status) {
                case "Chưa chuộc" -> {
                    jrbNotRedeemingStatus.setSelected(true);
                }
                case "Trễ" -> {
                    jrbLateStatus.setSelected(true);
                }
                case "Cần thanh lý" -> {
                    jrbNeedToLiquidateStatus.setSelected(true);
                }
                case "Đã chuộc" -> {
                    jrbRedeemedStatus.setSelected(true);
                    jrbRedeemedStatus.setEnabled(true);
                    jrbNotRedeemingStatus.setEnabled(false);
                    jrbLateStatus.setEnabled(false);
                    jrbNeedToLiquidateStatus.setEnabled(false);
                    jrbLiquidatedStatus.setEnabled(false);
                }
                case "Đã thanh lý" -> {
                    jrbRedeemedStatus.setEnabled(false);
                    jrbLiquidatedStatus.setSelected(true);
                    jrbLiquidatedStatus.setEnabled(true);
                    jrbNotRedeemingStatus.setEnabled(false);
                    jrbLateStatus.setEnabled(false);
                    jrbNeedToLiquidateStatus.setEnabled(false);
                }
            }
        }
    }

    private String getPawnCouponStatus() {
        return jrbNotRedeemingStatus.isSelected() ? "Chưa chuộc"
                : jrbLateStatus.isSelected() ? "Trễ"
                : jrbNeedToLiquidateStatus.isSelected() ? "Cần thanh lý"
                : jrbRedeemedStatus.isSelected() ? "Đã chuộc"
                : jrbLiquidatedStatus.isSelected() ? "Đã thanh lý"
                : null;
    }

    private void setPawnCouponTable(List<PawnCoupon> pawnCoupons) {
        DefaultTableModel model = (DefaultTableModel) jtblPawnCoupon.getModel();
        model.setRowCount(0);
        if (pawnCoupons == null) {
            return;
        }
        long totalPawnPrice = 0;
        long totolInterest = 0;
        long totalLiquidationPrice = 0;
        Object rowData[] = new Object[10];
        for (int i = 0; i < pawnCoupons.size(); i++) {
            rowData[0] = String.valueOf(i + 1);
            rowData[1] = pawnCoupons.get(i).getId();
            rowData[2] = pawnCoupons.get(i).getCustomer().getId();
            rowData[3] = pawnCoupons.get(i).getProduct().getId();
            rowData[4] = Support.getFormatNumber(pawnCoupons.get(i).getAmount());
            rowData[5] = Support.getFormatNumber(pawnCoupons.get(i).getPrice());
            totalPawnPrice += pawnCoupons.get(i).getPrice();
            long interest = (long) (pawnCoupons.get(i).getPrice() * pawnCoupons.get(i).getInterestRate() / 100);
            rowData[6] = Support.getFormatNumber(interest);
            totolInterest += interest;
            rowData[7] = pawnCoupons.get(i).getPawnDate();
            rowData[8] = pawnCoupons.get(i).getRedemption0rLiquidationDate();
            if (pawnCoupons.get(i).getStatus().equals("Đã thanh lý")) {
                rowData[9] = Support.getFormatNumber(pawnCoupons.get(i).getLiquidationPrice());
                totalLiquidationPrice += pawnCoupons.get(i).getLiquidationPrice();
            }
            model.addRow(rowData);
        }
        jlbTotalPawnPrice.setText(Support.getFormatNumber(totalPawnPrice));
        jlbTotalInterestPerDay.setText(Support.getFormatNumber(totolInterest));
        jlbTotalLiquidationPrice.setText(Support.getFormatNumber(totalLiquidationPrice));
        if (!CheckSupport.isNullOrBlank(jtfPawnCouponId.getText())) {
            Support.setRowTableSelection(jtblPawnCoupon, 1, jtfPawnCouponId.getText());
        }
    }

    private PawnCoupon getPawnCouponFromForm() {
        if (jcbCustomerId.getSelectedItem().equals("Tất cả")) {
            MessageSupport.ErrorMessage("Lỗi", "Chọn CMND/CCCC khách hàng");
            return null;
        }
        if (jcbProductId.getSelectedItem().equals("Tất cả")) {
            MessageSupport.ErrorMessage("Lỗi", "Chọn mã hàng hóa");
            return null;
        }
        long price;
        try {
            price = Long.parseLong(jtfPawnPrice.getText());
            if (price < 0) {
                MessageSupport.ErrorMessage("Lỗi", "Giá cầm >= 0");
            }
        } catch (NumberFormatException e) {
            MessageSupport.ErrorMessage("Lỗi", "Giá cầm không hợp lệ");
            return null;
        }
        float interestRate;
        try {
            interestRate = Float.parseFloat(jtfInterestRate.getText());
            if (interestRate < 0) {
                MessageSupport.ErrorMessage("Lỗi", "Lãi suất >= 0");
            }
        } catch (NumberFormatException e) {
            MessageSupport.ErrorMessage("Lỗi", "Lãi suất không hợp lệ");
            return null;
        }
        int amount;
        try {
            amount = Integer.parseInt(jtfAmount.getText());
            if (amount < 0) {
                MessageSupport.ErrorMessage("Lỗi", "Số lượng >= 0");
            }
        } catch (NumberFormatException e) {
            MessageSupport.ErrorMessage("Lỗi", "Số lượng không hợp lệ");
            return null;
        }
        long liquidationPrice;
        try {
            liquidationPrice = Integer.parseInt(jtfAmount.getText());
            if (liquidationPrice < 0) {
                MessageSupport.ErrorMessage("Lỗi", "Giá thanh lý >= 0");
            }
        } catch (NumberFormatException e) {
            MessageSupport.ErrorMessage("Lỗi", "Giá thanh lý không hợp lệ");
            return null;
        }
        return new PawnCoupon(jtfPawnCouponId.getText(),
                new Customer(jcbCustomerId.getSelectedItem().toString()),
                new Product(jcbProductId.getSelectedItem().toString()),
                amount, price, interestRate,
                Support.dateToString(jdcPawnDate.getDate(), Default.DATE_FORMAT),
                Support.dateToString(jdcTheNextInterestPaymentDate.getDate(), Default.DATE_FORMAT),
                Support.dateToString(jdcRedemptionOrLiquidationDate.getDate(), Default.DATE_FORMAT),
                liquidationPrice, getPawnCouponStatus());
    }

    private void filterPawnCoupon() {
        if (!jbtnRepawn.isEnabled()
                && !jbtnAddPawnCoupon.isEnabled()
                && !jbtnEditPawnCoupon.isEnabled()
                && !jbtnViewPawnCouponInPaper.isEnabled()) {
            String id = jtfPawnCouponId.getText();
            String customerId = jcbCustomerId.getSelectedItem().toString().equals("Tất cả") ? null
                    : jcbCustomerId.getSelectedItem().toString();
            String productId = jcbProductId.getSelectedItem().toString().equals("Tất cả") ? null
                    : jcbProductId.getSelectedItem().toString();
            Long pawnPrice;
            try {
                pawnPrice = Long.parseLong(jtfPawnPrice.getText());
            } catch (NumberFormatException e) {
                pawnPrice = null;
            }
            Float interestRate;
            try {
                interestRate = Float.parseFloat(jtfInterestRate.getText());
            } catch (NumberFormatException e) {
                interestRate = null;
            }
            Integer amount;
            try {
                amount = Integer.parseInt(jtfAmount.getText());
            } catch (NumberFormatException e) {
                amount = null;
            }
            String pawnDate = Support.dateToString(jdcPawnDate.getDate(), Default.DATE_FORMAT);
            String theNextInterestPaymentDate = Support.dateToString(jdcTheNextInterestPaymentDate.getDate(), Default.DATE_FORMAT);
            String redemptionOrLiquidationDate = Support.dateToString(jdcRedemptionOrLiquidationDate.getDate(), Default.DATE_FORMAT);
            Long liquidationPrice;
            try {
                liquidationPrice = Long.parseLong(jtfLiquidationPrice.getText());
            } catch (NumberFormatException e) {
                liquidationPrice = null;
            }
            setPawnCouponTable(
                    PawnCouponController.getCurrentInstance().filter(id, customerId, productId,
                            pawnPrice, interestRate, amount,
                            pawnDate, theNextInterestPaymentDate, redemptionOrLiquidationDate,
                            liquidationPrice, getPawnCouponStatus()));
        }
    }

    private void createNewPawnCoupon(PawnCoupon pawnCoupon, boolean isRepawning) {
        setPawnCouponDefault(null);
        jbtnAddPawnCoupon.setEnabled(true);
        jtfPawnCouponId.setText(PawnCouponController.getCurrentInstance().createNewId());
        jtfPawnCouponId.setEditable(false);
        jtfAmount.setText("1");
        jtfPawnPrice.setText("0");
        jtfInterestRate.setText("0.3");
        if (pawnCoupon != null) {
            if (pawnCoupon.getCustomer() != null) {
                jcbCustomerId.setSelectedItem(pawnCoupon.getCustomer().getId());
            }
            if (pawnCoupon.getProduct() != null) {
                jcbProductId.setSelectedItem(pawnCoupon.getProduct().getId());
            }
            if (isRepawning) {
                jtfAmount.setText(String.valueOf(pawnCoupon.getAmount()));
                jtfPawnPrice.setText(String.valueOf(pawnCoupon.getPrice()));
                jtfInterestRate.setText(String.valueOf(pawnCoupon.getInterestRate()));
            }
        }
        jdcPawnDate.setDate(new Date());
        jdcPawnDate.setEnabled(false);
        jdcTheNextInterestPaymentDate.setDate(Support.addDate(jdcPawnDate.getDate(), Default.PAYMENT_CIRCLE - 1));
        jdcTheNextInterestPaymentDate.setEnabled(false);
        jdcRedemptionOrLiquidationDate.setDate(null);
        jdcRedemptionOrLiquidationDate.setEnabled(false);
        jtfLiquidationPrice.setText("0");
        jtfLiquidationPrice.setEditable(false);
        setPawnCouponStatus("Chưa chuộc", true);

    }

    //========================================================================\\
    private void setInterestPaymentTable(List<InterestPayment> interestPayments) {
        DefaultTableModel model = (DefaultTableModel) jtblInterestPayment.getModel();
        model.setRowCount(0);
        if (interestPayments == null) {
            jPanelInterestPaymentStatistic.setVisible(false);
            return;
        }
        Object rowData[] = new Object[6];
        long totalInterest = 0;
        long totalTheMoneyPayed = 0;
        for (int i = 0; i <= interestPayments.size() - 1; i++) {
            rowData[0] = interestPayments.get(i).getTimes();
            rowData[1] = interestPayments.get(i).getPaymentDate();
            @SuppressWarnings("UnusedAssignment")
            long days = 0;
            if (interestPayments.get(i).getTimes() == 1) {
                days = Support.subtractDate(interestPayments.get(i).getPaymentDate(),
                        interestPayments.get(i).getPawnCoupon().getPawnDate())
                        + 1;
            } else {
                days = Support.subtractDate(interestPayments.get(i + 1).getPaymentDate(),
                        interestPayments.get(i).getPaymentDate());
            }
            Long interest = (long) (days
                    * interestPayments.get(i).getPawnCoupon().getPrice()
                    * interestPayments.get(i).getPawnCoupon().getInterestRate()
                    / 100);
            rowData[2] = Support.getFormatNumber(interest);
            totalInterest += interest;
            rowData[3] = Support.getFormatNumber(interestPayments.get(i).getMoneyPaid());
            totalTheMoneyPayed += interestPayments.get(i).getMoneyPaid();
            rowData[4] = interestPayments.get(i).getNewDebt();
            rowData[5] = interestPayments.get(i).getNote();
            model.addRow(rowData);
        }
        jPanelInterestPaymentStatistic.setVisible(true);
        jlbTotalInterest.setText(Support.getFormatNumber(totalInterest));
        jlbTotalPaid.setText(Support.getFormatNumber(totalTheMoneyPayed));
    }

    @SuppressWarnings("null")
    private void setInterestPaymentDefault(PawnCoupon pawnCoupon, InterestPayment interestPayment) {
        if (pawnCoupon == null
                || (pawnCoupon.getStatus().equals("Đã chuộc") || pawnCoupon.getStatus().equals("Đã thanh lý"))) {
            jbtnAddInterestPayment.setEnabled(false);
            jbtnEditInterestPayment.setEnabled(false);
            jbtnDeleteInterestPayment.setEnabled(false);
            jtfTimes.setText(null);
            jtfTimes.setEditable(false);
            jtfOldDebt.setText(null);
            jtfOldDebt.setEditable(false);
            jdcInterestPaymentDate.setDate(null);
            jdcInterestPaymentDate.setEnabled(false);
            jtfAmountOfDays.setText(null);
            jtfAmountOfDays.setEditable(false);
            jtfInterest.setText(null);
            jtfInterest.setEditable(false);
            jtfFine.setText(null);
            jtfFine.setEditable(false);
            jtfTheMoneyHaveToPay.setText(null);
            jtfTheMoneyHaveToPay.setEditable(false);
            jtfTheMoneyPaid.setText(null);
            jtfTheMoneyPaid.setEditable(false);
            jtfNewDebt.setText(null);
            jtfNewDebt.setEditable(false);
            jtaNote.setText(null);
            jtaNote.setEditable(false);
            if (pawnCoupon == null) {
                setInterestPaymentTable(null);
            } else if (pawnCoupon.getStatus().equals("Đã chuộc") || pawnCoupon.getStatus().equals("Đã thanh lý")) {
                setInterestPaymentTable(InterestPaymentController.getCurrentInstance().findAllByPawnCouponId(pawnCoupon.getId()));
            }
        } else {
            jtfTheMoneyPaid.setEditable(true);
            jtfNewDebt.setEditable(true);
            jtaNote.setEditable(true);
            setInterestPaymentTable(InterestPaymentController.getCurrentInstance().findAllByPawnCouponId(pawnCoupon.getId()));
            if (interestPayment == null) {
                jbtnAddInterestPayment.setEnabled(true);
                jbtnEditInterestPayment.setEnabled(false);
                jbtnDeleteInterestPayment.setEnabled(false);
            } else {
                jbtnAddInterestPayment.setEnabled(false);
                jbtnEditInterestPayment.setEnabled(true);
                jbtnDeleteInterestPayment.setEnabled(true);
            }
            // set Times
            int times;
            if (interestPayment == null) {
                // create new times
                times = InterestPaymentController.getCurrentInstance()
                        .countAllByPawnCouponId(pawnCoupon.getId()) + 1;
            } else {
                times = interestPayment.getTimes();
            }
            jtfTimes.setText(String.valueOf(times));

            // set payment date
            Date paymentDate;
            if (interestPayment == null) {
                paymentDate = new Date();
            } else {
                paymentDate = Support.stringToDate(interestPayment.getPaymentDate(), Default.DATE_FORMAT);
            }
            jdcInterestPaymentDate.setDate(paymentDate);

            // set amount of days
            long amountOfDays;
            if (times == 1) {
                amountOfDays = Support.subtractDate(jdcInterestPaymentDate.getDate(),
                        jdcPawnDate.getDate()) + 1;
            } else {
                InterestPayment lastPayment = InterestPaymentController.getCurrentInstance()
                        .findOneByPawnCouponIdAndTimes(pawnCoupon.getId(), times - 1);
                amountOfDays = Support.subtractDate(jdcInterestPaymentDate.getDate(),
                        lastPayment.getPaymentDate());
            }
            jtfAmountOfDays.setText(String.valueOf(amountOfDays));

            // set interest
            long interest = (long) (amountOfDays * pawnCoupon.getPrice() * pawnCoupon.getInterestRate() / 100);
            jtfInterest.setText(String.valueOf(interest));

            // set fine
            long fine = 0;
            long lateDays = Support.subtractDate(jdcInterestPaymentDate.getDate(), jdcTheNextInterestPaymentDate.getDate());
            if (lateDays > 0) {
                fine = (long) (0.5 * lateDays * pawnCoupon.getPrice() * pawnCoupon.getInterestRate() / 100);
            }
            jtfFine.setText(String.valueOf(fine));

            // set old debt
            long oldDebt;
            if (times == 1) {
                oldDebt = 0;
            } else {
                oldDebt = InterestPaymentController.getCurrentInstance()
                        .findOneByPawnCouponIdAndTimes(pawnCoupon.getId(), times - 1).getNewDebt();
            }
            jtfOldDebt.setText(String.valueOf(oldDebt));

            // set have to pay
            long theMoneyHaveToPay = interest + fine + oldDebt;
            jtfTheMoneyHaveToPay.setText(String.valueOf(theMoneyHaveToPay));

            // set default payed
            long theMoneyPaid;
            if (interestPayment == null) {
                theMoneyPaid = 0;
            } else {
                theMoneyPaid = interestPayment.getMoneyPaid();
            }
            jtfTheMoneyPaid.setText(String.valueOf(theMoneyPaid));

            // set default new debt
            jtfNewDebt.setText(String.valueOf(theMoneyHaveToPay - theMoneyPaid));
        }
    }

    @SuppressWarnings({"BroadCatchBlock", "TooBroadCatch", "UseSpecificCatch"})
    private InterestPayment getInterestPaymentFromForm() {
        PawnCoupon pawnCoupon = getPawnCouponFromForm();
        int times = Integer.parseInt(jtfTimes.getText());
        String paymentDate = Support.dateToString(jdcInterestPaymentDate.getDate(), Default.DATE_FORMAT);
        try {
            int fine = Integer.parseInt(jtfFine.getText());
            if (fine < 0) {
                MessageSupport.ErrorMessage("Lỗi", "Số tiền phạt >= 0");
                return null;
            }
        } catch (Exception e) {
            MessageSupport.ErrorMessage("Lỗi", "Số tiền phạt không hợp lệ");
            return null;
        }
        @SuppressWarnings("UnusedAssignment")
        long moneyPaid = 0;
        try {
            moneyPaid = Long.parseLong(jtfTheMoneyPaid.getText());
            if (moneyPaid < 0) {
                MessageSupport.ErrorMessage("Lỗi", "Số tiền đóng >= 0");
                return null;
            }
        } catch (Exception e) {
            MessageSupport.ErrorMessage("Lỗi", "Số tiền đóng không hợp lệ");
            return null;
        }
        @SuppressWarnings("UnusedAssignment")
        long newDebt = 0;
        try {
            newDebt = Long.parseLong(jtfNewDebt.getText());
            if (newDebt < 0) {
                MessageSupport.ErrorMessage("Lỗi", "Nợ >= 0");
                return null;
            }
        } catch (Exception e) {
            MessageSupport.ErrorMessage("Lỗi", "Nợ không hợp lệ");
            return null;
        }
        String note = jtaNote.getText();
        return new InterestPayment(pawnCoupon, times, paymentDate, moneyPaid, newDebt, note);
    }

    //========================================================================\\
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jbtnDeleteTab = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jtfPawnCouponId = new javax.swing.JTextField();
        jcbCustomerId = new javax.swing.JComboBox<>();
        jcbProductId = new javax.swing.JComboBox<>();
        jtfAmount = new javax.swing.JTextField();
        jtfPawnPrice = new javax.swing.JTextField();
        jtfInterestRate = new javax.swing.JTextField();
        jdcPawnDate = new com.toedter.calendar.JDateChooser();
        jdcTheNextInterestPaymentDate = new com.toedter.calendar.JDateChooser();
        jdcRedemptionOrLiquidationDate = new com.toedter.calendar.JDateChooser();
        jbtnAddPawnCoupon = new javax.swing.JButton();
        jbtnEditPawnCoupon = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jbtnCreateNewPawnCoupon = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jrbNotRedeemingStatus = new javax.swing.JRadioButton();
        jrbRedeemedStatus = new javax.swing.JRadioButton();
        jrbNeedToLiquidateStatus = new javax.swing.JRadioButton();
        jrbLiquidatedStatus = new javax.swing.JRadioButton();
        jrbLateStatus = new javax.swing.JRadioButton();
        jrbAllStatus = new javax.swing.JRadioButton();
        jLabel19 = new javax.swing.JLabel();
        jtfLiquidationPrice = new javax.swing.JTextField();
        jbtnViewPawnCouponInPaper = new javax.swing.JButton();
        jbtnRepawn = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jtfTimes = new javax.swing.JTextField();
        jtfOldDebt = new javax.swing.JTextField();
        jtfAmountOfDays = new javax.swing.JTextField();
        jtfInterest = new javax.swing.JTextField();
        jtfFine = new javax.swing.JTextField();
        jtfTheMoneyHaveToPay = new javax.swing.JTextField();
        jtfTheMoneyPaid = new javax.swing.JTextField();
        jtfNewDebt = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtaNote = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblInterestPayment = new javax.swing.JTable();
        jbtnAddInterestPayment = new javax.swing.JButton();
        jbtnEditInterestPayment = new javax.swing.JButton();
        jbtnDeleteInterestPayment = new javax.swing.JButton();
        jbtnReload = new javax.swing.JButton();
        jPanelInterestPaymentStatistic = new javax.swing.JPanel();
        jlbInterestPaymentSum = new javax.swing.JLabel();
        jlbTotalInterest = new javax.swing.JLabel();
        jlbTotalPaid = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jdcInterestPaymentDate = new com.toedter.calendar.JDateChooser();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtblPawnCoupon = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jlbTotalPawnPrice = new javax.swing.JLabel();
        jlbTotalInterestPerDay = new javax.swing.JLabel();
        jlbTotalLiquidationPrice = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 204, 204));

        jPanel1.setBackground(new java.awt.Color(0, 255, 255));

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("HỢP ĐỒNG");

        jbtnDeleteTab.setFont(new java.awt.Font("Dialog", 1, 35)); // NOI18N
        jbtnDeleteTab.setForeground(new java.awt.Color(255, 0, 0));
        jbtnDeleteTab.setText("X");
        jbtnDeleteTab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnDeleteTabActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnDeleteTab, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbtnDeleteTab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(0, 255, 255));

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setForeground(new java.awt.Color(0, 0, 0));

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Hợp đồng");

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Mã hợp đồng");

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Mã khách hàng");

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Mã hàng hóa");

        jLabel9.setBackground(new java.awt.Color(0, 0, 0));
        jLabel9.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Số lượng");

        jLabel10.setBackground(new java.awt.Color(0, 0, 0));
        jLabel10.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Giá cầm");

        jLabel11.setBackground(new java.awt.Color(0, 0, 0));
        jLabel11.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Lãi suất (%/ngày)");

        jLabel12.setBackground(new java.awt.Color(0, 0, 0));
        jLabel12.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Ngày cầm");

        jLabel14.setBackground(new java.awt.Color(0, 0, 0));
        jLabel14.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Ngày đóng lãi tiếp theo");

        jLabel15.setBackground(new java.awt.Color(0, 0, 0));
        jLabel15.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Ngày chuộc/thanh lý");

        jtfPawnCouponId.setBackground(new java.awt.Color(255, 255, 255));
        jtfPawnCouponId.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jtfPawnCouponId.setForeground(new java.awt.Color(0, 0, 0));
        jtfPawnCouponId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfPawnCouponInfoKeyRealeasedToFilter(evt);
            }
        });

        jcbCustomerId.setBackground(new java.awt.Color(255, 255, 255));
        jcbCustomerId.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jcbCustomerId.setForeground(new java.awt.Color(0, 0, 0));
        jcbCustomerId.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbItemStateChanged(evt);
            }
        });

        jcbProductId.setBackground(new java.awt.Color(255, 255, 255));
        jcbProductId.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jcbProductId.setForeground(new java.awt.Color(0, 0, 0));
        jcbProductId.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbItemStateChanged(evt);
            }
        });

        jtfAmount.setBackground(new java.awt.Color(255, 255, 255));
        jtfAmount.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jtfAmount.setForeground(new java.awt.Color(0, 0, 0));
        jtfAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfPawnCouponInfoKeyRealeasedToFilter(evt);
            }
        });

        jtfPawnPrice.setBackground(new java.awt.Color(255, 255, 255));
        jtfPawnPrice.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jtfPawnPrice.setForeground(new java.awt.Color(0, 0, 0));
        jtfPawnPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfPawnCouponInfoKeyRealeasedToFilter(evt);
            }
        });

        jtfInterestRate.setBackground(new java.awt.Color(255, 255, 255));
        jtfInterestRate.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jtfInterestRate.setForeground(new java.awt.Color(0, 0, 0));
        jtfInterestRate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfPawnCouponInfoKeyRealeasedToFilter(evt);
            }
        });

        jdcPawnDate.setBackground(new java.awt.Color(255, 255, 255));
        jdcPawnDate.setForeground(new java.awt.Color(0, 0, 0));
        jdcPawnDate.setDateFormatString("dd/MM/yyyy");
        jdcPawnDate.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jdcPawnDate.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdcValueChangeToFilter(evt);
            }
        });

        jdcTheNextInterestPaymentDate.setBackground(new java.awt.Color(255, 255, 255));
        jdcTheNextInterestPaymentDate.setForeground(new java.awt.Color(0, 0, 0));
        jdcTheNextInterestPaymentDate.setDateFormatString("dd/MM/yyyy");
        jdcTheNextInterestPaymentDate.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jdcTheNextInterestPaymentDate.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdcValueChangeToFilter(evt);
            }
        });

        jdcRedemptionOrLiquidationDate.setBackground(new java.awt.Color(255, 255, 255));
        jdcRedemptionOrLiquidationDate.setForeground(new java.awt.Color(0, 0, 0));
        jdcRedemptionOrLiquidationDate.setDateFormatString("dd/MM/yyyy");
        jdcRedemptionOrLiquidationDate.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jdcRedemptionOrLiquidationDate.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdcValueChangeToFilter(evt);
            }
        });

        jbtnAddPawnCoupon.setBackground(new java.awt.Color(0, 255, 255));
        jbtnAddPawnCoupon.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jbtnAddPawnCoupon.setForeground(new java.awt.Color(0, 0, 0));
        jbtnAddPawnCoupon.setText("Thêm");
        jbtnAddPawnCoupon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAddPawnCouponActionPerformed(evt);
            }
        });

        jbtnEditPawnCoupon.setBackground(new java.awt.Color(0, 255, 255));
        jbtnEditPawnCoupon.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jbtnEditPawnCoupon.setForeground(new java.awt.Color(0, 0, 0));
        jbtnEditPawnCoupon.setText("Sửa");
        jbtnEditPawnCoupon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEditPawnCouponActionPerformed(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(0, 255, 255));

        jbtnCreateNewPawnCoupon.setBackground(new java.awt.Color(255, 255, 255));
        jbtnCreateNewPawnCoupon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/addNew.png"))); // NOI18N
        jbtnCreateNewPawnCoupon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbtnCreateNewPawnCouponMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jbtnCreateNewPawnCoupon, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jbtnCreateNewPawnCoupon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jLabel27.setBackground(new java.awt.Color(0, 0, 0));
        jLabel27.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Trạng thái");

        jrbNotRedeemingStatus.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(jrbNotRedeemingStatus);
        jrbNotRedeemingStatus.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jrbNotRedeemingStatus.setForeground(new java.awt.Color(0, 0, 0));
        jrbNotRedeemingStatus.setText("Chưa chuộc");
        jrbNotRedeemingStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbPawnCouponStatusPerformed(evt);
            }
        });

        jrbRedeemedStatus.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(jrbRedeemedStatus);
        jrbRedeemedStatus.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jrbRedeemedStatus.setForeground(new java.awt.Color(0, 0, 0));
        jrbRedeemedStatus.setText("Đã chuộc");
        jrbRedeemedStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbPawnCouponStatusPerformed(evt);
            }
        });

        jrbNeedToLiquidateStatus.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(jrbNeedToLiquidateStatus);
        jrbNeedToLiquidateStatus.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jrbNeedToLiquidateStatus.setForeground(new java.awt.Color(0, 0, 0));
        jrbNeedToLiquidateStatus.setText("Cần thanh lý");
        jrbNeedToLiquidateStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbPawnCouponStatusPerformed(evt);
            }
        });

        jrbLiquidatedStatus.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(jrbLiquidatedStatus);
        jrbLiquidatedStatus.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jrbLiquidatedStatus.setForeground(new java.awt.Color(0, 0, 0));
        jrbLiquidatedStatus.setText("Đã thanh lý");
        jrbLiquidatedStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbPawnCouponStatusPerformed(evt);
            }
        });

        jrbLateStatus.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(jrbLateStatus);
        jrbLateStatus.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jrbLateStatus.setForeground(new java.awt.Color(0, 0, 0));
        jrbLateStatus.setText("Trễ");
        jrbLateStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbPawnCouponStatusPerformed(evt);
            }
        });

        jrbAllStatus.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(jrbAllStatus);
        jrbAllStatus.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jrbAllStatus.setForeground(new java.awt.Color(0, 0, 0));
        jrbAllStatus.setSelected(true);
        jrbAllStatus.setText("Tất cả");
        jrbAllStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbPawnCouponStatusPerformed(evt);
            }
        });

        jLabel19.setBackground(new java.awt.Color(0, 0, 0));
        jLabel19.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Giá thanh lý");

        jtfLiquidationPrice.setBackground(new java.awt.Color(255, 255, 255));
        jtfLiquidationPrice.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jtfLiquidationPrice.setForeground(new java.awt.Color(0, 0, 0));
        jtfLiquidationPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfPawnCouponInfoKeyRealeasedToFilter(evt);
            }
        });

        jbtnViewPawnCouponInPaper.setBackground(new java.awt.Color(0, 255, 255));
        jbtnViewPawnCouponInPaper.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jbtnViewPawnCouponInPaper.setForeground(new java.awt.Color(0, 0, 0));
        jbtnViewPawnCouponInPaper.setText("Xem ");
        jbtnViewPawnCouponInPaper.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnViewPawnCouponInPaperActionPerformed(evt);
            }
        });

        jbtnRepawn.setBackground(new java.awt.Color(0, 255, 255));
        jbtnRepawn.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jbtnRepawn.setForeground(new java.awt.Color(0, 0, 0));
        jbtnRepawn.setText("Cầm lại");
        jbtnRepawn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnRepawnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtnRepawn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jbtnAddPawnCoupon, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtnEditPawnCoupon, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jdcRedemptionOrLiquidationDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jdcTheNextInterestPaymentDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jdcPawnDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtfPawnCouponId, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcbCustomerId, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jcbProductId, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtfPawnPrice, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jtfInterestRate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addGap(6, 6, 6)
                        .addComponent(jtfAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jtfLiquidationPrice))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbtnViewPawnCouponInPaper, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jrbNotRedeemingStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jrbLateStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jrbNeedToLiquidateStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jrbLiquidatedStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jrbRedeemedStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jrbAllStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 16, Short.MAX_VALUE))))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(9, 9, 9))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtfPawnCouponId, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbCustomerId, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbProductId, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfPawnPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jrbNotRedeemingStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfInterestRate, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jrbLateStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jdcRedemptionOrLiquidationDate, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jdcPawnDate, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jrbNeedToLiquidateStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jdcTheNextInterestPaymentDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jrbRedeemedStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jrbLiquidatedStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfLiquidationPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jrbAllStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnEditPawnCoupon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnAddPawnCoupon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnViewPawnCouponInPaper, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnRepawn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Lịch sử đóng lãi");

        jLabel17.setBackground(new java.awt.Color(0, 0, 0));
        jLabel17.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Kỳ");

        jLabel18.setBackground(new java.awt.Color(0, 0, 0));
        jLabel18.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Nợ cũ");

        jLabel20.setBackground(new java.awt.Color(0, 0, 0));
        jLabel20.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Số ngày");

        jLabel21.setBackground(new java.awt.Color(0, 0, 0));
        jLabel21.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Lãi");

        jLabel22.setBackground(new java.awt.Color(0, 0, 0));
        jLabel22.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Phạt");

        jLabel23.setBackground(new java.awt.Color(0, 0, 0));
        jLabel23.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Đóng đủ");

        jLabel24.setBackground(new java.awt.Color(0, 0, 0));
        jLabel24.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Đóng");

        jLabel25.setBackground(new java.awt.Color(0, 0, 0));
        jLabel25.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Nợ mới");

        jLabel26.setBackground(new java.awt.Color(0, 0, 0));
        jLabel26.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Ghi chú");

        jtfTimes.setBackground(new java.awt.Color(255, 255, 255));
        jtfTimes.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jtfTimes.setForeground(new java.awt.Color(0, 0, 0));

        jtfOldDebt.setBackground(new java.awt.Color(255, 255, 255));
        jtfOldDebt.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jtfOldDebt.setForeground(new java.awt.Color(0, 0, 0));

        jtfAmountOfDays.setBackground(new java.awt.Color(255, 255, 255));
        jtfAmountOfDays.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jtfAmountOfDays.setForeground(new java.awt.Color(0, 0, 0));

        jtfInterest.setBackground(new java.awt.Color(255, 255, 255));
        jtfInterest.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jtfInterest.setForeground(new java.awt.Color(0, 0, 0));

        jtfFine.setBackground(new java.awt.Color(255, 255, 255));
        jtfFine.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jtfFine.setForeground(new java.awt.Color(0, 0, 0));

        jtfTheMoneyHaveToPay.setBackground(new java.awt.Color(255, 255, 255));
        jtfTheMoneyHaveToPay.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jtfTheMoneyHaveToPay.setForeground(new java.awt.Color(0, 0, 0));

        jtfTheMoneyPaid.setBackground(new java.awt.Color(255, 255, 255));
        jtfTheMoneyPaid.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jtfTheMoneyPaid.setForeground(new java.awt.Color(0, 0, 0));
        jtfTheMoneyPaid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfTheMoneyPaidKeyReleased(evt);
            }
        });

        jtfNewDebt.setBackground(new java.awt.Color(255, 255, 255));
        jtfNewDebt.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jtfNewDebt.setForeground(new java.awt.Color(0, 0, 0));

        jScrollPane2.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N

        jtaNote.setBackground(new java.awt.Color(255, 255, 255));
        jtaNote.setColumns(3);
        jtaNote.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jtaNote.setForeground(new java.awt.Color(0, 0, 0));
        jtaNote.setRows(3);
        jtaNote.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane2.setViewportView(jtaNote);

        jtblInterestPayment.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jtblInterestPayment.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kỳ", "Ngày đóng", "Lãi", "Đã đóng", "Nợ mới", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblInterestPayment.setRowHeight(20);
        jtblInterestPayment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblInterestPaymentMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtblInterestPayment);
        if (jtblInterestPayment.getColumnModel().getColumnCount() > 0) {
            jtblInterestPayment.getColumnModel().getColumn(0).setMinWidth(35);
            jtblInterestPayment.getColumnModel().getColumn(0).setPreferredWidth(35);
            jtblInterestPayment.getColumnModel().getColumn(0).setMaxWidth(35);
            jtblInterestPayment.getColumnModel().getColumn(1).setMinWidth(100);
            jtblInterestPayment.getColumnModel().getColumn(1).setPreferredWidth(100);
            jtblInterestPayment.getColumnModel().getColumn(1).setMaxWidth(100);
            jtblInterestPayment.getColumnModel().getColumn(2).setMinWidth(100);
            jtblInterestPayment.getColumnModel().getColumn(2).setPreferredWidth(100);
            jtblInterestPayment.getColumnModel().getColumn(2).setMaxWidth(100);
            jtblInterestPayment.getColumnModel().getColumn(3).setMinWidth(100);
            jtblInterestPayment.getColumnModel().getColumn(3).setPreferredWidth(100);
            jtblInterestPayment.getColumnModel().getColumn(3).setMaxWidth(100);
            jtblInterestPayment.getColumnModel().getColumn(4).setMinWidth(100);
            jtblInterestPayment.getColumnModel().getColumn(4).setPreferredWidth(100);
            jtblInterestPayment.getColumnModel().getColumn(4).setMaxWidth(100);
        }

        jbtnAddInterestPayment.setBackground(new java.awt.Color(0, 255, 255));
        jbtnAddInterestPayment.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jbtnAddInterestPayment.setForeground(new java.awt.Color(0, 0, 0));
        jbtnAddInterestPayment.setText("Thêm");
        jbtnAddInterestPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAddInterestPaymentActionPerformed(evt);
            }
        });

        jbtnEditInterestPayment.setBackground(new java.awt.Color(0, 255, 255));
        jbtnEditInterestPayment.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jbtnEditInterestPayment.setForeground(new java.awt.Color(0, 0, 0));
        jbtnEditInterestPayment.setText("Sửa");
        jbtnEditInterestPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEditInterestPaymentActionPerformed(evt);
            }
        });

        jbtnDeleteInterestPayment.setBackground(new java.awt.Color(0, 255, 255));
        jbtnDeleteInterestPayment.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jbtnDeleteInterestPayment.setForeground(new java.awt.Color(0, 0, 0));
        jbtnDeleteInterestPayment.setText("Xóa");
        jbtnDeleteInterestPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnDeleteInterestPaymentActionPerformed(evt);
            }
        });

        jbtnReload.setBackground(new java.awt.Color(0, 255, 255));
        jbtnReload.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jbtnReload.setForeground(new java.awt.Color(0, 0, 0));
        jbtnReload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/reload.png"))); // NOI18N
        jbtnReload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnReloadActionPerformed(evt);
            }
        });

        jPanelInterestPaymentStatistic.setBackground(new java.awt.Color(204, 204, 204));
        jPanelInterestPaymentStatistic.setForeground(new java.awt.Color(0, 0, 0));

        jlbInterestPaymentSum.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jlbInterestPaymentSum.setForeground(new java.awt.Color(0, 0, 0));
        jlbInterestPaymentSum.setText("Tổng  :");

        jlbTotalInterest.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jlbTotalInterest.setForeground(new java.awt.Color(0, 0, 0));
        jlbTotalInterest.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbTotalInterest.setText("0");

        jlbTotalPaid.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jlbTotalPaid.setForeground(new java.awt.Color(0, 0, 0));
        jlbTotalPaid.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbTotalPaid.setText("0");

        jLabel35.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanelInterestPaymentStatisticLayout = new javax.swing.GroupLayout(jPanelInterestPaymentStatistic);
        jPanelInterestPaymentStatistic.setLayout(jPanelInterestPaymentStatisticLayout);
        jPanelInterestPaymentStatisticLayout.setHorizontalGroup(
            jPanelInterestPaymentStatisticLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInterestPaymentStatisticLayout.createSequentialGroup()
                .addComponent(jlbInterestPaymentSum, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbTotalInterest, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbTotalPaid, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(110, 110, 110)
                .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelInterestPaymentStatisticLayout.setVerticalGroup(
            jPanelInterestPaymentStatisticLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInterestPaymentStatisticLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jlbTotalInterest, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jlbTotalPaid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jlbInterestPaymentSum, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel34.setBackground(new java.awt.Color(0, 0, 0));
        jLabel34.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Ngày đóng");

        jdcInterestPaymentDate.setBackground(new java.awt.Color(255, 255, 255));
        jdcInterestPaymentDate.setForeground(new java.awt.Color(0, 0, 0));
        jdcInterestPaymentDate.setDateFormatString("dd/MM/yyyy");
        jdcInterestPaymentDate.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jtfAmountOfDays, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jdcInterestPaymentDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(jtfTimes, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfInterest))
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jtfOldDebt, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                            .addComponent(jtfFine)))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jtfTheMoneyHaveToPay, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addGap(12, 12, 12)
                                                .addComponent(jtfTheMoneyPaid, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel26))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jtfNewDebt)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)))
                            .addComponent(jPanelInterestPaymentStatistic, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addGap(141, 141, 141)
                                .addComponent(jbtnAddInterestPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jbtnEditInterestPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jbtnDeleteInterestPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jbtnReload, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jtfNewDebt, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jtfTimes, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jdcInterestPaymentDate, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfTheMoneyPaid, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfInterest, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfFine, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfOldDebt, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfTheMoneyHaveToPay, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfAmountOfDays, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelInterestPaymentStatistic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jbtnAddInterestPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jbtnEditInterestPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jbtnDeleteInterestPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jbtnReload, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));

        jtblPawnCoupon.setBackground(new java.awt.Color(255, 255, 255));
        jtblPawnCoupon.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jtblPawnCoupon.setForeground(new java.awt.Color(0, 0, 0));
        jtblPawnCoupon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã hợp đồng", "Mã khách hàng", "Mã hàng hóa", "Số lượng", "Giá cầm", "Lãi/ngày", "Ngày cầm", "Ngày chuộc/thanh lý", "Giá thanh lý"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblPawnCoupon.setRowHeight(20);
        jtblPawnCoupon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblPawnCouponMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jtblPawnCoupon);
        if (jtblPawnCoupon.getColumnModel().getColumnCount() > 0) {
            jtblPawnCoupon.getColumnModel().getColumn(0).setMinWidth(35);
            jtblPawnCoupon.getColumnModel().getColumn(0).setPreferredWidth(35);
            jtblPawnCoupon.getColumnModel().getColumn(0).setMaxWidth(35);
            jtblPawnCoupon.getColumnModel().getColumn(1).setPreferredWidth(150);
            jtblPawnCoupon.getColumnModel().getColumn(2).setPreferredWidth(150);
            jtblPawnCoupon.getColumnModel().getColumn(3).setPreferredWidth(150);
            jtblPawnCoupon.getColumnModel().getColumn(4).setMinWidth(75);
            jtblPawnCoupon.getColumnModel().getColumn(4).setPreferredWidth(75);
            jtblPawnCoupon.getColumnModel().getColumn(4).setMaxWidth(75);
            jtblPawnCoupon.getColumnModel().getColumn(5).setPreferredWidth(120);
            jtblPawnCoupon.getColumnModel().getColumn(6).setPreferredWidth(120);
            jtblPawnCoupon.getColumnModel().getColumn(8).setPreferredWidth(120);
            jtblPawnCoupon.getColumnModel().getColumn(9).setPreferredWidth(120);
        }

        jPanel9.setBackground(new java.awt.Color(204, 204, 204));

        jLabel28.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Tổng  :");

        jlbTotalPawnPrice.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jlbTotalPawnPrice.setForeground(new java.awt.Color(0, 0, 0));
        jlbTotalPawnPrice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbTotalPawnPrice.setText("0");
        jlbTotalPawnPrice.setToolTipText("");
        jlbTotalPawnPrice.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jlbTotalInterestPerDay.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jlbTotalInterestPerDay.setForeground(new java.awt.Color(0, 0, 0));
        jlbTotalInterestPerDay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbTotalInterestPerDay.setText("0");
        jlbTotalInterestPerDay.setToolTipText("");
        jlbTotalInterestPerDay.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jlbTotalLiquidationPrice.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jlbTotalLiquidationPrice.setForeground(new java.awt.Color(0, 0, 0));
        jlbTotalLiquidationPrice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbTotalLiquidationPrice.setText("0");
        jlbTotalLiquidationPrice.setToolTipText("");
        jlbTotalLiquidationPrice.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel33.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setToolTipText("");
        jLabel33.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbTotalPawnPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbTotalInterestPerDay, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbTotalLiquidationPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbTotalLiquidationPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jlbTotalPawnPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlbTotalInterestPerDay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addContainerGap())))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnAddPawnCouponActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAddPawnCouponActionPerformed
        PawnCoupon pawnCoupon = getPawnCouponFromForm();
        if (pawnCoupon != null) {
            if (PawnCouponController.getCurrentInstance().insert(pawnCoupon)) {
                MessageSupport.Message("Thông báo", "Thêm mới hợp đồng thành công\n Kiểm tra và chỉnh sửa thông tin nếu sai sót");
                ActivityHistoryController.getCurrentInstance()
                        .insert(new ActivityHistory(Support.dateToString(LocalDateTime.now(), Default.DATE_TIME_FORMAT),
                                "Thêm mới", "Hợp đồng", pawnCoupon.toString()));

                PawnCouponInPaperJFrameForm pawnCouponInPaperJFrameForm
                        = new PawnCouponInPaperJFrameForm(
                                PawnCouponController.getCurrentInstance()
                                        .findOneById(pawnCoupon.getId()));
                pawnCouponInPaperJFrameForm.setVisible(true);
                setPawnCouponDefault(null);
            }
        }
    }//GEN-LAST:event_jbtnAddPawnCouponActionPerformed

    private void jbtnEditPawnCouponActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEditPawnCouponActionPerformed
        PawnCoupon pawnCoupon = getPawnCouponFromForm();
        if (pawnCoupon != null) {
            if (PawnCouponController.getCurrentInstance().update(pawnCoupon)) {
                MessageSupport.Message("Thông báo", "Cập nhật thông tin hợp đồng thành công");
                ActivityHistoryController.getCurrentInstance()
                        .insert(new ActivityHistory(Support.dateToString(LocalDateTime.now(), Default.DATE_TIME_FORMAT),
                                "Cập nhật", "Hợp đồng", pawnCoupon.toString()));
                setPawnCouponDefault(null);
            }
        }
    }//GEN-LAST:event_jbtnEditPawnCouponActionPerformed

    private void jbtnReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnReloadActionPerformed
        setPawnCouponDefault(null);
    }//GEN-LAST:event_jbtnReloadActionPerformed

    private void jtblPawnCouponMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblPawnCouponMouseClicked
        int row = jtblPawnCoupon.getSelectedRow();
        if (evt.getClickCount() == 2 && row != -1) {
            JTable table = (JTable) evt.getSource();
            String pawnCouponId = (table.getModel().getValueAt(row, 1)).toString();
            PawnCoupon pawnCoupon = PawnCouponController.getCurrentInstance().findOneById(pawnCouponId);
            setPawnCouponDefault(pawnCoupon);
            if (jtblPawnCoupon.getSelectedColumn() == 2) {
                String customerId = (table.getModel().getValueAt(row, 2)).toString();
                Customer customer = CustomerController.getCurrentInstance().findOneById(customerId);
                String title = "Khách hàng";
                if (HomePageJFrameForm.jtpHomePage.indexOfTab(title) != -1) {
                    HomePageJFrameForm.jtpHomePage.remove(HomePageJFrameForm.jtpHomePage.indexOfTab(title));
                }
                JPanel jPanel = new CustomerJPanelForm(customer);
                HomePageJFrameForm.jtpHomePage.addTab(title, jPanel);
                HomePageJFrameForm.jtpHomePage.setSelectedComponent(jPanel);
            } else if (jtblPawnCoupon.getSelectedColumn() == 3) {
                String productId = (table.getModel().getValueAt(row, 3)).toString();
                Product product = ProductController.getCurrentInstance().findOneById(productId);
                String title = "Hàng hóa";
                if (HomePageJFrameForm.jtpHomePage.indexOfTab(title) != -1) {
                    HomePageJFrameForm.jtpHomePage.remove(HomePageJFrameForm.jtpHomePage.indexOfTab(title));
                }
                JPanel jPanel = new ProductJPanelForm(product);
                HomePageJFrameForm.jtpHomePage.addTab(title, jPanel);
                HomePageJFrameForm.jtpHomePage.setSelectedComponent(jPanel);
            }
        }
    }//GEN-LAST:event_jtblPawnCouponMouseClicked

    private void jrbPawnCouponStatusPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbPawnCouponStatusPerformed
        if (jbtnEditPawnCoupon.isEnabled()) {
            if ((evt.getSource().equals(jrbRedeemedStatus) || evt.getSource().equals(jrbLiquidatedStatus))) {
                jdcTheNextInterestPaymentDate.setDate(null);
                jdcRedemptionOrLiquidationDate.setDate(new Date());
                if (evt.getSource().equals(jrbLiquidatedStatus)) {
                    jtfLiquidationPrice.setEditable(true);
                } else {
                    jtfLiquidationPrice.setText("0");
                    jtfLiquidationPrice.setEditable(false);
                }
            } else {
                jdcTheNextInterestPaymentDate.setDate(
                        Support.stringToDate(PawnCouponController.getCurrentInstance()
                                .findOneById(jtfPawnCouponId.getText()).getTheNextInterestPaymentDate(), Default.DATE_FORMAT));
                jdcRedemptionOrLiquidationDate.setDate(null);
                jtfLiquidationPrice.setText("0");
                jtfLiquidationPrice.setEditable(false);
            }

        }
        filterPawnCoupon();

    }//GEN-LAST:event_jrbPawnCouponStatusPerformed

    private void jtblInterestPaymentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblInterestPaymentMouseClicked
        int row = jtblInterestPayment.getSelectedRow();
        if (evt.getClickCount() == 2 && row != -1) {
            JTable table = (JTable) evt.getSource();
            int times = Integer.parseInt(table.getModel().getValueAt(row, 0).toString());
            InterestPayment interestPayment = InterestPaymentController.getCurrentInstance()
                    .findOneByPawnCouponIdAndTimes(jtfPawnCouponId.getText(), times);
            setInterestPaymentDefault(interestPayment.getPawnCoupon(), interestPayment);
        }
    }//GEN-LAST:event_jtblInterestPaymentMouseClicked

    private void jbtnAddInterestPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAddInterestPaymentActionPerformed
        InterestPayment interestPayment = getInterestPaymentFromForm();
        if (interestPayment != null) {
            float theMoneyHaveToPay = Float.parseFloat(jtfTheMoneyHaveToPay.getText());
            float newDebt = Float.parseFloat(jtfNewDebt.getText());
            float theMoneyPaid = Float.parseFloat(jtfTheMoneyPaid.getText());

            if ((theMoneyHaveToPay != (theMoneyPaid + newDebt))
                    && jtaNote.getText().isBlank()) {
                MessageSupport.ErrorMessage("Lỗi", "Tổng số tiền đóng và nợ mới không khớp với số tiền đóng đủ.\nHãy điền thêm ghi chú.");
                return;
            }
            if (InterestPaymentController.getCurrentInstance().insert(interestPayment)) {
                MessageSupport.Message("Thông báo", "Thêm mới kỳ đóng lãi thành công");
                ActivityHistoryController.getCurrentInstance()
                        .insert(new ActivityHistory(Support.dateToString(LocalDateTime.now(), Default.DATE_TIME_FORMAT),
                                "Thêm mới", "Kỳ đóng lãi", interestPayment.toString()));
                setInterestPaymentDefault(interestPayment.getPawnCoupon(), null);
            }
        }
    }//GEN-LAST:event_jbtnAddInterestPaymentActionPerformed

    private void jbtnEditInterestPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEditInterestPaymentActionPerformed
        InterestPayment interestPayment = getInterestPaymentFromForm();
        if (InterestPaymentController.getCurrentInstance().update(interestPayment)) {
            MessageSupport.Message("Thông báo", "Cập nhật thông tin kỳ đóng lãi thành công.");
            ActivityHistoryController.getCurrentInstance()
                    .insert(new ActivityHistory(Support.dateToString(LocalDateTime.now(), Default.DATE_TIME_FORMAT),
                            "Cập nhật", "Kỳ đóng lãi", interestPayment.toString()));
            setInterestPaymentDefault(getPawnCouponFromForm(), null);
        }
    }//GEN-LAST:event_jbtnEditInterestPaymentActionPerformed

    private void jbtnDeleteInterestPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDeleteInterestPaymentActionPerformed
        InterestPayment interestPayment = InterestPaymentController.getCurrentInstance()
                .findOneByPawnCouponIdAndTimes(jtfPawnCouponId.getText(), Integer.parseInt(jtfTimes.getText()));
        if (MessageSupport.MessageConfirm("Xác nhận xóa", "Bạn muốn xóa kỳ đóng lãi này?") == JOptionPane.YES_OPTION) {
            if (InterestPaymentController.getCurrentInstance().delete(interestPayment)) {
                MessageSupport.Message("Thông báo", "Xóa kỳ đóng lãi thành công.");
                ActivityHistoryController.getCurrentInstance()
                        .insert(new ActivityHistory(Support.dateToString(LocalDateTime.now(), Default.DATE_TIME_FORMAT),
                                "Xóa", "Kỳ đóng lãi", interestPayment.toString()));
                setInterestPaymentDefault(getPawnCouponFromForm(), null);
            }
        }
    }//GEN-LAST:event_jbtnDeleteInterestPaymentActionPerformed

    private void jbtnDeleteTabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDeleteTabActionPerformed
        HomePageJFrameForm.jtpHomePage.remove(HomePageJFrameForm.jtpHomePage.indexOfTab(this.getName()));
    }//GEN-LAST:event_jbtnDeleteTabActionPerformed

    private void jbtnViewPawnCouponInPaperActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnViewPawnCouponInPaperActionPerformed
        PawnCouponInPaperJFrameForm pawnCouponInPaperJFrameForm
                = new PawnCouponInPaperJFrameForm(
                        PawnCouponController.getCurrentInstance()
                                .findOneById(jtfPawnCouponId.getText()));
        pawnCouponInPaperJFrameForm.setVisible(true);
    }//GEN-LAST:event_jbtnViewPawnCouponInPaperActionPerformed

    private void jbtnCreateNewPawnCouponMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnCreateNewPawnCouponMouseClicked
        createNewPawnCoupon(null, false);
    }//GEN-LAST:event_jbtnCreateNewPawnCouponMouseClicked

    private void jbtnRepawnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnRepawnActionPerformed
        createNewPawnCoupon(getPawnCouponFromForm(), true);
    }//GEN-LAST:event_jbtnRepawnActionPerformed

    private void jcbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbItemStateChanged
        filterPawnCoupon();
    }//GEN-LAST:event_jcbItemStateChanged

    private void jtfPawnCouponInfoKeyRealeasedToFilter(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfPawnCouponInfoKeyRealeasedToFilter
        filterPawnCoupon();
    }//GEN-LAST:event_jtfPawnCouponInfoKeyRealeasedToFilter

    private void jdcValueChangeToFilter(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdcValueChangeToFilter
        if ("date".equals(evt.getPropertyName())) {
            filterPawnCoupon();
        }
    }//GEN-LAST:event_jdcValueChangeToFilter

    private void jtfTheMoneyPaidKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfTheMoneyPaidKeyReleased
        try {
            Long newDebt = Long.parseLong(jtfTheMoneyHaveToPay.getText())
                    - Long.parseLong(jtfTheMoneyPaid.getText());
            jtfNewDebt.setText(String.valueOf(newDebt));
        } catch (NumberFormatException e) {
            jtfNewDebt.setText(null);
        }
    }//GEN-LAST:event_jtfTheMoneyPaidKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelInterestPaymentStatistic;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton jbtnAddInterestPayment;
    private javax.swing.JButton jbtnAddPawnCoupon;
    private javax.swing.JButton jbtnCreateNewPawnCoupon;
    private javax.swing.JButton jbtnDeleteInterestPayment;
    private javax.swing.JButton jbtnDeleteTab;
    private javax.swing.JButton jbtnEditInterestPayment;
    private javax.swing.JButton jbtnEditPawnCoupon;
    private javax.swing.JButton jbtnReload;
    private javax.swing.JButton jbtnRepawn;
    private javax.swing.JButton jbtnViewPawnCouponInPaper;
    private javax.swing.JComboBox<String> jcbCustomerId;
    private javax.swing.JComboBox<String> jcbProductId;
    private com.toedter.calendar.JDateChooser jdcInterestPaymentDate;
    private com.toedter.calendar.JDateChooser jdcPawnDate;
    private com.toedter.calendar.JDateChooser jdcRedemptionOrLiquidationDate;
    private com.toedter.calendar.JDateChooser jdcTheNextInterestPaymentDate;
    private javax.swing.JLabel jlbInterestPaymentSum;
    private javax.swing.JLabel jlbTotalInterest;
    private javax.swing.JLabel jlbTotalInterestPerDay;
    private javax.swing.JLabel jlbTotalLiquidationPrice;
    private javax.swing.JLabel jlbTotalPaid;
    private javax.swing.JLabel jlbTotalPawnPrice;
    private javax.swing.JRadioButton jrbAllStatus;
    private javax.swing.JRadioButton jrbLateStatus;
    private javax.swing.JRadioButton jrbLiquidatedStatus;
    private javax.swing.JRadioButton jrbNeedToLiquidateStatus;
    private javax.swing.JRadioButton jrbNotRedeemingStatus;
    private javax.swing.JRadioButton jrbRedeemedStatus;
    private javax.swing.JTextArea jtaNote;
    private javax.swing.JTable jtblInterestPayment;
    private javax.swing.JTable jtblPawnCoupon;
    private javax.swing.JTextField jtfAmount;
    private javax.swing.JTextField jtfAmountOfDays;
    private javax.swing.JTextField jtfFine;
    private javax.swing.JTextField jtfInterest;
    private javax.swing.JTextField jtfInterestRate;
    private javax.swing.JTextField jtfLiquidationPrice;
    private javax.swing.JTextField jtfNewDebt;
    private javax.swing.JTextField jtfOldDebt;
    private javax.swing.JTextField jtfPawnCouponId;
    private javax.swing.JTextField jtfPawnPrice;
    private javax.swing.JTextField jtfTheMoneyHaveToPay;
    private javax.swing.JTextField jtfTheMoneyPaid;
    private javax.swing.JTextField jtfTimes;
    // End of variables declaration//GEN-END:variables

}
