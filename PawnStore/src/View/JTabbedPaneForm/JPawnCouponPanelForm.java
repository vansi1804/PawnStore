/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.JTabbedPaneForm;

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
import Model.StaticUser;
import Support.CheckSupport;
import Support.ColorFormatSupport;
import Support.MessageSupport;
import Support.Support;
import View.JHomePageJFrameForm;
import View.PawnCouponPageBerJFrameForm;
import java.awt.event.ItemEvent;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("ClassWithoutLogger")
public class JPawnCouponPanelForm extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;

    public JPawnCouponPanelForm() {
        initComponents();
        setFindPawnCouponEvent();
        setInterestPaymentEvent();
        setPawnCouponDefault(null);
        setInterestPaymentDefault(null, null);
    }

    public JPawnCouponPanelForm(PawnCoupon pawnCoupon) {
        initComponents();
        setFindPawnCouponEvent();
        setInterestPaymentEvent();
        setPawnCouponDefault(null);
        setInterestPaymentDefault(null, null);
        setPawnCouponDefault(pawnCoupon);
        setInterestPaymentDefault(pawnCoupon, null);
    }

    private void setCBCustomer(ArrayList<Customer> customers) {
        jcbCustomerID.removeAllItems();
        jcbCustomerID.addItem("Tất cả");
        for (Customer customer : customers) {
            jcbCustomerID.addItem(customer.getId());
        }
        jcbCustomerID.setSelectedItem("Tất cả");
    }

    private Customer getCBCustomer() {
        return jcbCustomerID.getSelectedItem() == null
                ? null
                : CustomerController.getCurrentInstance().getCustomer(jcbCustomerID.getSelectedItem().toString());
    }

    private void setCBProduct(ArrayList<Product> products) {
        jcbProductID.removeAllItems();
        jcbProductID.addItem("Tất cả");
        for (Product product : products) {
            jcbProductID.addItem(product.getId());
        }
        jcbProductID.setSelectedItem("Tất cả");
    }

    private Product getCBProduct() {
        return jcbProductID.getSelectedItem() == null
                ? null
                : ProductController.getCurrentInstance().getProduct(jcbProductID.getSelectedItem().toString());
    }

    private void setPawnCouponStatus(String status) {
        if (CheckSupport.isBlank(status)) {
            jrbNotRedeemingStatus.setEnabled(true);
            jrbNotRedeemingStatus.setSelected(true);
            jrbRedeemedStatus.setEnabled(true);
            jrbNeedToLiquidateStatus.setEnabled(true);
            jrbLiquidatedStatus.setEnabled(true);
            jrbLateStatus.setEnabled(true);
            jrbAllStatus.setEnabled(true);
        } else {
            switch (status) {
                case "Chưa chuộc" -> {
                    jrbNotRedeemingStatus.setEnabled(true);
                    jrbNotRedeemingStatus.setSelected(true);
                    jrbRedeemedStatus.setEnabled(true);
                    jrbNeedToLiquidateStatus.setEnabled(true);
                    jrbLiquidatedStatus.setEnabled(true);
                    jrbLateStatus.setEnabled(true);
                    jrbAllStatus.setEnabled(false);
                }
                case "Đã chuộc" -> {
                    jrbNotRedeemingStatus.setEnabled(true);
                    jrbRedeemedStatus.setEnabled(true);
                    jrbRedeemedStatus.setSelected(true);
                    jrbNeedToLiquidateStatus.setEnabled(true);
                    jrbLiquidatedStatus.setEnabled(true);
                    jrbLateStatus.setEnabled(true);
                    jrbAllStatus.setEnabled(false);
                }
                case "Trễ" -> {
                    jrbNotRedeemingStatus.setEnabled(true);
                    jrbRedeemedStatus.setEnabled(true);
                    jrbNeedToLiquidateStatus.setEnabled(true);
                    jrbLiquidatedStatus.setEnabled(true);
                    jrbLateStatus.setEnabled(true);
                    jrbLateStatus.setSelected(true);
                    jrbAllStatus.setEnabled(false);
                }
                case "Cần thanh lý" -> {
                    jrbNotRedeemingStatus.setEnabled(true);
                    jrbRedeemedStatus.setEnabled(true);
                    jrbNeedToLiquidateStatus.setEnabled(true);
                    jrbNeedToLiquidateStatus.setSelected(true);
                    jrbLiquidatedStatus.setEnabled(true);
                    jrbLateStatus.setEnabled(true);
                    jrbAllStatus.setEnabled(false);
                }
                case "Đã thanh lý" -> {
                    jrbNotRedeemingStatus.setEnabled(true);
                    jrbRedeemedStatus.setEnabled(true);
                    jrbNeedToLiquidateStatus.setEnabled(true);
                    jrbLiquidatedStatus.setEnabled(true);
                    jrbLiquidatedStatus.setSelected(true);
                    jrbLateStatus.setEnabled(true);
                    jrbAllStatus.setEnabled(false);
                }
            }
        }
    }

    private String getPawnCouponStatus() {
        if (jrbNotRedeemingStatus.isSelected()) {
            return "Chưa chuộc";
        } else if (jrbRedeemedStatus.isSelected()) {
            return "Đã chuộc";
        } else if (jrbLateStatus.isSelected()) {
            return "Trễ";
        } else if (jrbNeedToLiquidateStatus.isSelected()) {
            return "Cần thanh lý";
        } else if (jrbLiquidatedStatus.isSelected()) {
            return "Đã thanh lý";
        } else {
            return null;
        }
    }

    @SuppressWarnings("MalformedFormatString")
    private void setPawnCouponTable(ArrayList<PawnCoupon> pawnCoupons) {
        ColorFormatSupport.FormatTableHeader(jtblPawnCoupon);
        ColorFormatSupport.setDataTableCenter(jtblPawnCoupon);
        DefaultTableModel model = (DefaultTableModel) jtblPawnCoupon.getModel();
        model.setRowCount(0);
        Object rowData[] = new Object[10];
        long totalPrice = 0;
        long totalInterest = 0;
        long totalLiquidationPrice = 0;
        for (int i = 0; i <= pawnCoupons.size() - 1; i++) {
            rowData[0] = String.valueOf(i + 1);
            rowData[1] = pawnCoupons.get(i).getId();
            rowData[2] = pawnCoupons.get(i).getCustomer().getId();
            rowData[3] = pawnCoupons.get(i).getProduct().getId();
            rowData[4] = Support.getFormatNumber(pawnCoupons.get(i).getAmount());
            rowData[5] = Support.getFormatNumber(pawnCoupons.get(i).getPrice());
            totalPrice += pawnCoupons.get(i).getPrice();
            rowData[6] = Support.getFormatNumber((long) (pawnCoupons.get(i).getPrice() * pawnCoupons.get(i).getInterestRate() / 100));
            totalInterest += (long) (pawnCoupons.get(i).getPrice() * pawnCoupons.get(i).getInterestRate() / 100);
            rowData[7] = pawnCoupons.get(i).getPawnDate();
            rowData[8] = pawnCoupons.get(i).getRedeem0rLiquidationDate();
            rowData[9] = Support.getFormatNumber(pawnCoupons.get(i).getLiquidationPrice());
            totalLiquidationPrice += pawnCoupons.get(i).getLiquidationPrice();
            model.addRow(rowData);
        }
        jlbInterestPaymentSum.setText("Tổng");
        jlbTotalPrice.setText(Support.getFormatNumber(totalPrice));
        jlbTotalInterest.setText(Support.getFormatNumber(totalInterest));
        jlbTotalLiquidationPrice.setText(Support.getFormatNumber(totalLiquidationPrice));
    }

    private void setPawnCouponDefault(PawnCoupon pawnCoupon) {
        if (pawnCoupon == null) {
            jbtnPrintPawnCoupon.setEnabled(false);
            jbtnAddPawnCoupon.setEnabled(true);
            jbtnEditPawnCoupon.setEnabled(true);
            jtfID.setText("");
            jtfID.setEditable(true);
            setCBCustomer(CustomerController.getCurrentInstance().getList());
            setCBProduct(ProductController.getCurrentInstance().getList());
            jtfAmount.setText("");
            jtfAmount.setEditable(true);
            jtfPrice.setText("");
            jtfPrice.setEditable(true);
            jtfInterestRate.setText("");
            jtfInterestRate.setEditable(true);
            jdcPawnDate.setDate(null);
            jdcPawnDate.setEnabled(true);
            jdcTheNextInterestPaymentDate.setDate(null);
            jdcTheNextInterestPaymentDate.setEnabled(true);
            jdcRedeemOrLiquidateDate.setDate(null);
            jdcRedeemOrLiquidateDate.setEnabled(true);
            jtfLiquidatePrice.setText("");
            jtfLiquidatePrice.setEditable(true);
            setPawnCouponStatus("");
            setPawnCouponTable(PawnCouponController.getCurrentInstance().findPawnCouponByStatusKey(getPawnCouponStatus()));
            jbtnAddPawnCoupon.setEnabled(false);
            jbtnEditPawnCoupon.setEnabled(false);
        } else {
            jbtnPrintPawnCoupon.setEnabled(true);
            jbtnAddPawnCoupon.setEnabled(false);
            jbtnEditPawnCoupon.setEnabled(true);
            jtfID.setText(pawnCoupon.getId());
            jtfID.setEditable(false);
            jcbCustomerID.setSelectedItem(pawnCoupon.getCustomer().getId());
            jcbProductID.setSelectedItem(pawnCoupon.getProduct().getId());
            jtfAmount.setText(String.valueOf(pawnCoupon.getAmount()));
            jtfPrice.setText(String.valueOf(pawnCoupon.getPrice()));
            jtfInterestRate.setText(String.valueOf(pawnCoupon.getInterestRate()));
            jdcPawnDate.setDate(Support.stringToDate(pawnCoupon.getPawnDate(), Support.getDateFormat()));
            jdcPawnDate.setEnabled(false);
            jdcTheNextInterestPaymentDate.setDate(
                    Support.stringToDate(PawnCouponController.getCurrentInstance().getTheNextPaymentDate(pawnCoupon), Support.getDateFormat()));
            jdcTheNextInterestPaymentDate.setEnabled(false);
            jdcRedeemOrLiquidateDate.setDate(Support.stringToDate(pawnCoupon.getRedeem0rLiquidationDate(), Support.getDateFormat()));
            jdcRedeemOrLiquidateDate.setEnabled(false);
            jtfLiquidatePrice.setText(String.valueOf(pawnCoupon.getLiquidationPrice()));
            jtfLiquidatePrice.setEditable(false);
            setPawnCouponStatus(pawnCoupon.getStatus());
            if (pawnCoupon.getStatus().equals("Đã chuộc") || pawnCoupon.getStatus().equals("Đã thanh lý")) {
                jcbCustomerID.setEnabled(false);
                jcbProductID.setEnabled(false);
                jtfAmount.setEditable(false);
                jtfPrice.setEditable(false);
                jtfInterestRate.setEditable(false);
                jdcPawnDate.setEnabled(false);
                jdcTheNextInterestPaymentDate.setEnabled(false);
                jdcTheNextInterestPaymentDate.setDate(null);
                jdcRedeemOrLiquidateDate.setEnabled(false);
                jtfLiquidatePrice.setEditable(false);
                jbtnAddPawnCoupon.setEnabled(false);
                jbtnEditPawnCoupon.setEnabled(false);
                jbtnPrintPawnCoupon.setEnabled(false);
                if (pawnCoupon.getStatus().equals("Đã chuộc")) {
                    jrbNotRedeemingStatus.setEnabled(false);
                    jrbLateStatus.setEnabled(false);
                    jrbNeedToLiquidateStatus.setEnabled(false);
                    jrbLiquidatedStatus.setEnabled(false);
                    jrbAllStatus.setEnabled(false);
                } else if (pawnCoupon.getStatus().equals("Đã thanh lý")) {
                    jrbNotRedeemingStatus.setEnabled(false);
                    jrbRedeemedStatus.setEnabled(false);
                    jrbLateStatus.setEnabled(false);
                    jrbNeedToLiquidateStatus.setEnabled(false);
                    jrbAllStatus.setEnabled(false);
                }
            }
        }
    }

    @SuppressWarnings({"UseSpecificCatch", "BroadCatchBlock", "TooBroadCatch", "UnusedAssignment"})
    private PawnCoupon getPawnCouponFromForm() {
        String id = jtfID.getText();
        Customer customer = getCBCustomer();
        if (customer == null) {
            MessageSupport.ErrorMessage("Lỗi", "Chọn mã khách hàng.");
            return null;
        }
        Product product = getCBProduct();
        if (product == null) {
            MessageSupport.ErrorMessage("Lỗi", "Chọn mã hàng hóa.");
            return null;
        }
        int amount;
        try {
            amount = Integer.parseInt(jtfAmount.getText());
            if (amount < 1) {
                MessageSupport.ErrorMessage("Lỗi", "Số lượng phải >= 1.");
                return null;
            }
        } catch (Exception e) {
            MessageSupport.ErrorMessage("Lỗi", "Số lượng định dạng không hợp lệ.");
            return null;
        }
        int price;
        try {
            price = Integer.parseInt(jtfPrice.getText());
            if (price < 0) {
                MessageSupport.ErrorMessage("Lỗi", "Giá cầm phải >= 0.");
                return null;
            }
        } catch (Exception e) {
            MessageSupport.ErrorMessage("Lỗi", "Giá cầm định dạng không hợp lệ.");
            return null;
        }
        float interestRate;
        try {
            interestRate = Float.parseFloat(jtfInterestRate.getText());
            if (interestRate < 0) {
                MessageSupport.ErrorMessage("Lỗi", "Lãi suất phải >= 0.");
                return null;
            }
        } catch (Exception e) {
            MessageSupport.ErrorMessage("Lỗi", "Lãi suất định dạng không hợp lệ.");
            return null;
        }
        String pawnDate = Support.dateToString(jdcPawnDate.getDate(), Support.getDateFormat());
        if (pawnDate == null) {
            MessageSupport.ErrorMessage("Lỗi", "Ngày cầm định dạng không hợp lệ.");
            return null;
        }
        String redeemOrLiquidationDate = Support.dateToString(jdcRedeemOrLiquidateDate.getDate(), Support.getDateFormat());
        if (redeemOrLiquidationDate == null) {
            MessageSupport.ErrorMessage("Lỗi", "Ngày chuộc/Thanh lý định dạng không hợp lệ.");
            return null;
        }
        int liquidationPrice;
        try {
            liquidationPrice = Integer.parseInt(jtfLiquidatePrice.getText());
            if (liquidationPrice < 0) {
                MessageSupport.ErrorMessage("Lỗi", "Giá thanh lý phải >= 0.");
                return null;
            }
        } catch (Exception e) {
            MessageSupport.ErrorMessage("Lỗi", "Giá thanh lý định dạng không hợp lệ.");
            return null;
        }
        String status = getPawnCouponStatus();
        if (!status.equals("Đã thanh lý") && liquidationPrice > 0) {
            MessageSupport.ErrorMessage("Lỗi", "Hợp đồng chưa thanh lý nên giá thanh lý = 0.");
            return null;
        }
        return new PawnCoupon(id, customer, product, amount, price, interestRate, pawnDate, redeemOrLiquidationDate, liquidationPrice, status);
    }

    @SuppressWarnings({"UnusedAssignment", "UseSpecificCatch", "BroadCatchBlock", "TooBroadCatch"})
    private void findPawnCoupon() {
        if (!jbtnAddPawnCoupon.isEnabled() && !jbtnEditPawnCoupon.isEnabled()) {
            String id = jtfID.getText();
            Customer customer = getCBCustomer();
            Product product = getCBProduct();
            int amount = 0;
            try {
                amount = Integer.parseInt(jtfAmount.getText());
            } catch (Exception e) {
                amount = -1;
            }
            int price = 0;
            try {
                price = Integer.parseInt(jtfAmount.getText());
            } catch (Exception e) {
                price = -1;
            }
            float interestRate = 0;
            try {
                interestRate = Integer.parseInt(jtfAmount.getText());
            } catch (Exception e) {
                interestRate = -1;
            }
            String pawnDate = Support.dateToString(jdcPawnDate.getDate(), Support.getDateFormat());
            String redeemOrLiquidationDate = Support.dateToString(jdcRedeemOrLiquidateDate.getDate(), Support.getDateFormat());
            String status = getPawnCouponStatus();

            ArrayList<PawnCoupon> results = PawnCouponController.getCurrentInstance()
                    .findPawnCouponByKey(id, customer, product, amount, price, interestRate, pawnDate, redeemOrLiquidationDate, status);
            @SuppressWarnings("CollectionWithoutInitialCapacity")
            ArrayList<PawnCoupon> results2 = new ArrayList<>();
            if (jdcTheNextInterestPaymentDate.getDate() != null) {
                for (PawnCoupon pawnCoupon : results) {
                    if (PawnCouponController.getCurrentInstance()
                            .getTheNextPaymentDate(pawnCoupon)
                            .equals(Support.dateToString(jdcTheNextInterestPaymentDate.getDate(), Support.getDateFormat()))) {
                        results2.add(pawnCoupon);
                    }
                }
                setPawnCouponTable(results2);
                return;
            }
            setPawnCouponTable(results);
        }
    }

    private void setFindPawnCouponEvent() {
        jtfID.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                findPawnCoupon();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                findPawnCoupon();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                findPawnCoupon();
            }
        });
        jcbCustomerID.addItemListener((ItemEvent e) -> {
            findPawnCoupon();
        });
        jcbProductID.addItemListener((ItemEvent e) -> {
            findPawnCoupon();
        });
        jtfAmount.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                findPawnCoupon();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                findPawnCoupon();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                findPawnCoupon();
            }
        });
        jtfPrice.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                findPawnCoupon();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                findPawnCoupon();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                findPawnCoupon();
            }
        });
        jtfInterestRate.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                findPawnCoupon();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                findPawnCoupon();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                findPawnCoupon();
            }
        });
        jdcPawnDate.addPropertyChangeListener((PropertyChangeEvent evt) -> {
            findPawnCoupon();
        });
        jdcTheNextInterestPaymentDate.addPropertyChangeListener((PropertyChangeEvent evt) -> {
            findPawnCoupon();
        });
        jdcRedeemOrLiquidateDate.addPropertyChangeListener((PropertyChangeEvent evt) -> {
            findPawnCoupon();
        });

    }

    private void setInterestPaymentTable(ArrayList<InterestPayment> interestPayments) {
        ColorFormatSupport.FormatTableHeader(jtblInterestPayment);
        ColorFormatSupport.setDataTableCenter(jtblInterestPayment);
        DefaultTableModel model = (DefaultTableModel) jtblInterestPayment.getModel();
        model.setRowCount(0);
        Object rowData[] = new Object[6];
        long totalInterest = 0;
        long totalTheMoneyPayed = 0;
        long lastDebt = 0;
        for (int i = 0; i <= interestPayments.size() - 1; i++) {
            rowData[0] = interestPayments.get(i).getTimes();
            rowData[1] = interestPayments.get(i).getPaymentDate();
            @SuppressWarnings("UnusedAssignment")
            int days = 0;
            if (interestPayments.get(i).getTimes() == 1) {
                days = (int) Support.subtractDate(interestPayments.get(i).getPaymentDate(), interestPayments.get(i).getPawnCoupon().getPawnDate()) + 1;
            } else {
                days = (int) Support.subtractDate(interestPayments.get(i).getPaymentDate(), interestPayments.get(i - 1).getPaymentDate());
            }
            rowData[2] = Support.getFormatNumber((long) (days * interestPayments.get(i).getPawnCoupon().getPrice()
                    * interestPayments.get(i).getPawnCoupon().getInterestRate() / 100));
            totalInterest += (long) (days * interestPayments.get(i).getPawnCoupon().getPrice()
                    * interestPayments.get(i).getPawnCoupon().getInterestRate() / 100);
            rowData[3] = Support.getFormatNumber(interestPayments.get(i).getMoney());
            totalTheMoneyPayed += interestPayments.get(i).getMoney();
            rowData[4] = interestPayments.get(i).getDebt();
            lastDebt = interestPayments.get(i).getDebt();
            rowData[5] = interestPayments.get(i).getNote();
            model.addRow(rowData);
        }
        jlbToTalInterest.setText(Support.getFormatNumber(totalInterest));
        jlbToTalTheMoneyPayed.setText(Support.getFormatNumber(totalTheMoneyPayed));
        jlbLastDebt.setText(Support.getFormatNumber(lastDebt));
    }

    private void setInterestPaymentDefault(PawnCoupon pawnCoupon, InterestPayment interestPayment) {
        if (pawnCoupon == null) {
            jbtnAddInterestPayment.setEnabled(false);
            jbtnEditInterestPayment.setEnabled(false);
            jbtnDeleteInterestPayment.setEnabled(false);
            jtfTimes.setEditable(false);
            jtfLastDebt.setEditable(false);
            jdcPaymentDate.setEnabled(false);
            jtfAmountOfDays.setEditable(false);
            jtfInterest.setEditable(false);
            jtfFine.setEditable(false);
            jtfTheMoneyHaveToPay.setEditable(false);
            jtfTheMoneyPayment.setEditable(false);
            jtfNewDebt.setEditable(false);
            jtaNote.setEditable(false);
            jtfTimes.setText("");
            jtfLastDebt.setText("");
            jdcPaymentDate.setDate(null);
            jtfAmountOfDays.setText("");
            jtfInterest.setText("");
            jtfFine.setText("");
            jtfTheMoneyHaveToPay.setText("");
            jtfTheMoneyPayment.setText("");
            jtfNewDebt.setText("");
            jtaNote.setText("");
            DefaultTableModel model = (DefaultTableModel) jtblInterestPayment.getModel();
            model.setRowCount(0);
            jlbInterestPaymentSum.setText("");
            jlbToTalInterest.setText("");
            jlbToTalTheMoneyPayed.setText("");
            jlbLastDebt.setText("");
        } else {
            if (interestPayment == null) {
                if (pawnCoupon.getStatus().equals("Đã chuộc") || pawnCoupon.getStatus().equals("Đã thanh lý")) {
                    setInterestPaymentDefault(null, null);
                    setInterestPaymentTable(InterestPaymentController.getCurrentInstance().getList(pawnCoupon));
                } else {
                    jbtnAddInterestPayment.setEnabled(true);
                    jbtnEditInterestPayment.setEnabled(false);
                    jbtnDeleteInterestPayment.setEnabled(false);
                    jtfTimes.setEditable(false);
                    jtfLastDebt.setEditable(false);
                    jdcPaymentDate.setEnabled(false);
                    jtfInterest.setEditable(false);
                    jtfFine.setEditable(true);
                    jtfTheMoneyHaveToPay.setEditable(false);
                    jtfTheMoneyPayment.setEditable(true);
                    jtfNewDebt.setEditable(true);
                    jtaNote.setEditable(true);
                    ArrayList<InterestPayment> interestPayments = InterestPaymentController.getCurrentInstance().getList(pawnCoupon);
                    int amountOfDays;
                    int paymentDaysCircle;
                    if (interestPayments.isEmpty()) { // first payment
                        jtfTimes.setText("1");
                        jtfLastDebt.setText("0");
                        jdcPaymentDate.setDate(new Date());
                        amountOfDays = (int) Support.subtractDate(jdcPaymentDate.getDate(), jdcPawnDate.getDate()) + 1;
                        paymentDaysCircle = 14;
                    } else { // next payment
                        InterestPayment lastInterestPayment = interestPayments.get(interestPayments.size() - 1);
                        jtfTimes.setText(String.valueOf(lastInterestPayment.getTimes() + 1));
                        jtfLastDebt.setText(String.valueOf(lastInterestPayment.getDebt()));
                        jdcPaymentDate.setDate(new Date());
                        amountOfDays = (int) Support.subtractDate(jdcPaymentDate.getDate(), lastInterestPayment.getPaymentDate());
                        paymentDaysCircle = 15;
                    }
                    jtfAmountOfDays.setText(String.valueOf(amountOfDays));
                    int interest = (int) (amountOfDays * Integer.parseInt(jtfPrice.getText()) * Float.parseFloat(jtfInterestRate.getText()) / 100);
                    jtfInterest.setText(String.valueOf(interest));
                    if (amountOfDays - paymentDaysCircle > 0) {
                        if (amountOfDays - paymentDaysCircle > 10) {
                            int fine = (int) (1.5 * (amountOfDays - paymentDaysCircle)
                                    * Integer.parseInt(jtfPrice.getText())
                                    * Float.parseFloat(jtfInterestRate.getText()) / 100);
                            jtfFine.setText(String.valueOf(fine));
                        } else {
                            jtfFine.setText("0");
                        }
                        jtaNote.setText("Trễ " + String.valueOf(amountOfDays - paymentDaysCircle) + " ngày");
                    } else {
                        jtfFine.setText("0");
                        jtaNote.setText("");
                    }
                    jtfTheMoneyPayment.setText("0");
                    setInterestPaymentTable(interestPayments);
                }
            } else {
                if (pawnCoupon.getStatus().equals("Đã chuộc") || pawnCoupon.getStatus().equals("Đã thanh lý")) {
                    jbtnAddInterestPayment.setEnabled(false);
                    jbtnEditInterestPayment.setEnabled(false);
                    jbtnDeleteInterestPayment.setEnabled(false);
                    jtfTimes.setEditable(false);
                    jtfLastDebt.setEditable(false);
                    jdcPaymentDate.setEnabled(false);
                    jtfAmountOfDays.setEditable(false);
                    jtfInterest.setEditable(false);
                    jtfFine.setEditable(false);
                    jtfTheMoneyHaveToPay.setEditable(false);
                    jtfTheMoneyPayment.setEditable(false);
                    jtfNewDebt.setEditable(false);
                    jtaNote.setEditable(false);
                } else {
                    jbtnAddInterestPayment.setEnabled(false);
                    if (InterestPaymentController.getCurrentInstance().getList(pawnCoupon).size() == interestPayment.getTimes()) {
                        jbtnDeleteInterestPayment.setEnabled(true);
                        jbtnEditInterestPayment.setEnabled(true);
                    } else {
                        jbtnDeleteInterestPayment.setEnabled(false);
                        jbtnEditInterestPayment.setEnabled(false);
                    }
                    jtfTimes.setEditable(false);
                    jtfLastDebt.setEditable(false);
                    jdcPaymentDate.setEnabled(false);
                    jtfInterest.setEditable(false);
                    jtfFine.setEditable(true);
                    jtfTheMoneyHaveToPay.setEditable(false);
                    jtfTheMoneyPayment.setEditable(true);
                    jtfNewDebt.setEditable(true);
                    jtaNote.setEditable(true);
                }
                jtfTimes.setText(String.valueOf(interestPayment.getTimes()));
                int lastDebt;
                int amountOfDays;
                int paymentDaysCircle;
                if (interestPayment.getTimes() == 1) {
                    lastDebt = 0;
                    jtfLastDebt.setText(String.valueOf(lastDebt));
                    amountOfDays = (int) Support.subtractDate(interestPayment.getPaymentDate(), interestPayment.getPawnCoupon().getPawnDate()) + 1;
                    paymentDaysCircle = 14;
                } else {
                    InterestPayment paymentBefor = InterestPaymentController.getCurrentInstance()
                            .getInterestPayment(pawnCoupon, String.valueOf(interestPayment.getTimes() - 1));
                    lastDebt = paymentBefor.getDebt();
                    jtfLastDebt.setText(String.valueOf(lastDebt));
                    amountOfDays = (int) Support.subtractDate(interestPayment.getPaymentDate(), paymentBefor.getPaymentDate());
                    paymentDaysCircle = 15;
                }
                jdcPaymentDate.setDate(Support.stringToDate(interestPayment.getPaymentDate(), Support.getDateFormat()));
                jtfAmountOfDays.setText(String.valueOf(amountOfDays));
                int interest = (int) (amountOfDays * interestPayment.getPawnCoupon().getPrice() * interestPayment.getPawnCoupon().getInterestRate() / 100);
                jtfInterest.setText(String.valueOf(interest));
                if (amountOfDays - paymentDaysCircle > 0) {
                    if (amountOfDays - paymentDaysCircle > 10) {
                        int fine = (int) (1.5 * (amountOfDays - paymentDaysCircle)
                                * Integer.parseInt(jtfPrice.getText())
                                * Float.parseFloat(jtfInterestRate.getText()) / 100);
                        jtfFine.setText(String.valueOf(fine));
                    } else {
                        jtfFine.setText("0");
                    }
                    jtaNote.setText("Trễ " + String.valueOf(amountOfDays - paymentDaysCircle) + " ngày");
                } else {
                    jtfFine.setText("0");
                    jtaNote.setText("");
                }
                jtfTheMoneyPayment.setText(String.valueOf(interestPayment.getMoney()));
            }
        }
    }

    @SuppressWarnings({"BroadCatchBlock", "TooBroadCatch", "UseSpecificCatch"})
    private InterestPayment getInterestPaymentFromForm() {
        PawnCoupon pawnCoupon = getPawnCouponFromForm();
        int times = Integer.parseInt(jtfTimes.getText());
        String paymentDate = Support.dateToString(new Date(), Support.getDateFormat());
        try {
            int fine = Integer.parseInt(jtfFine.getText());
            if (fine < 0) {
                MessageSupport.ErrorMessage("Lỗi", "Số tiền phạt >= 0.");
                return null;
            }
        } catch (Exception e) {
            MessageSupport.ErrorMessage("Lỗi", "Số tiền phạt không hợp lệ.");
            return null;
        }
        @SuppressWarnings("UnusedAssignment")
        int money = 0;
        try {
            money = Integer.parseInt(jtfTheMoneyPayment.getText());
            if (money < 0) {
                MessageSupport.ErrorMessage("Lỗi", "Số tiền đóng >= 0.");
                return null;
            }
        } catch (Exception e) {
            MessageSupport.ErrorMessage("Lỗi", "Số tiền đóng không hợp lệ.");
            return null;
        }
        @SuppressWarnings("UnusedAssignment")
        int debt = 0;
        try {
            debt = Integer.parseInt(jtfNewDebt.getText());
            if (debt < 0) {
                MessageSupport.ErrorMessage("Lỗi", "Nợ >= 0.");
                return null;
            }
        } catch (Exception e) {
            MessageSupport.ErrorMessage("Lỗi", "Nợ không hợp lệ.");
            return null;
        }
        String note = jtaNote.getText();
        return new InterestPayment(pawnCoupon, times, paymentDate, money, debt, note);
    }

    private void setInterestPaymentEvent() {
        jtfFine.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                valueChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                valueChange();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                valueChange();
            }

            @SuppressWarnings({"BroadCatchBlock", "TooBroadCatch", "UseSpecificCatch"})
            public void valueChange() {
                try {
                    int theMoneyHaveToPay = Integer.parseInt(jtfLastDebt.getText())
                            + Integer.parseInt(jtfInterest.getText())
                            + Integer.parseInt(jtfFine.getText());
                    jtfTheMoneyHaveToPay.setText(String.valueOf(theMoneyHaveToPay));
                } catch (Exception e) {
                    jtfTheMoneyHaveToPay.setText("");
                }
            }
        });
        jtfTheMoneyPayment.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                valueChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                valueChange();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                valueChange();
            }

            @SuppressWarnings({"BroadCatchBlock", "TooBroadCatch", "UseSpecificCatch"})
            public void valueChange() {
                try {
                    int newDebt = Integer.parseInt(jtfTheMoneyHaveToPay.getText()) - Integer.parseInt(jtfTheMoneyPayment.getText());
                    jtfNewDebt.setText(String.valueOf(newDebt));
                } catch (Exception e) {
                    jtfNewDebt.setText("");
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel6 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
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
        jtfID = new javax.swing.JTextField();
        jcbCustomerID = new javax.swing.JComboBox<>();
        jcbProductID = new javax.swing.JComboBox<>();
        jtfAmount = new javax.swing.JTextField();
        jtfPrice = new javax.swing.JTextField();
        jtfInterestRate = new javax.swing.JTextField();
        jdcPawnDate = new com.toedter.calendar.JDateChooser();
        jdcTheNextInterestPaymentDate = new com.toedter.calendar.JDateChooser();
        jdcRedeemOrLiquidateDate = new com.toedter.calendar.JDateChooser();
        jbtnAddPawnCoupon = new javax.swing.JButton();
        jbtnEditPawnCoupon = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jbtnAddNewPawnCoupon = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jrbNotRedeemingStatus = new javax.swing.JRadioButton();
        jrbRedeemedStatus = new javax.swing.JRadioButton();
        jrbNeedToLiquidateStatus = new javax.swing.JRadioButton();
        jrbLiquidatedStatus = new javax.swing.JRadioButton();
        jrbLateStatus = new javax.swing.JRadioButton();
        jrbAllStatus = new javax.swing.JRadioButton();
        jLabel19 = new javax.swing.JLabel();
        jtfLiquidatePrice = new javax.swing.JTextField();
        jbtnPrintPawnCoupon = new javax.swing.JButton();
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
        jtfLastDebt = new javax.swing.JTextField();
        jtfAmountOfDays = new javax.swing.JTextField();
        jtfInterest = new javax.swing.JTextField();
        jtfFine = new javax.swing.JTextField();
        jtfTheMoneyHaveToPay = new javax.swing.JTextField();
        jtfTheMoneyPayment = new javax.swing.JTextField();
        jtfNewDebt = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtaNote = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblInterestPayment = new javax.swing.JTable();
        jbtnAddInterestPayment = new javax.swing.JButton();
        jbtnEditInterestPayment = new javax.swing.JButton();
        jbtnDeleteInterestPayment = new javax.swing.JButton();
        jbtnReload = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jlbInterestPaymentSum = new javax.swing.JLabel();
        jlbToTalInterest = new javax.swing.JLabel();
        jlbToTalTheMoneyPayed = new javax.swing.JLabel();
        jlbLastDebt = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jdcPaymentDate = new com.toedter.calendar.JDateChooser();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtblPawnCoupon = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jlbTotalPrice = new javax.swing.JLabel();
        jlbTotalInterest = new javax.swing.JLabel();
        jlbTotalLiquidationPrice = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();

        jLabel5.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel5.setText("Mã hợp đồng");

        jLabel6.setText("jLabel6");

        jLabel13.setText("jLabel13");

        jLabel16.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel16.setText("Số tiền");

        jTextField6.setText("jTextField2");

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
        jLabel10.setText("Giá");

        jLabel11.setBackground(new java.awt.Color(0, 0, 0));
        jLabel11.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Lãi suất(%/ngày)");

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
        jLabel15.setText("Ngày chuộc/Thanh lý");

        jtfID.setBackground(new java.awt.Color(255, 255, 255));
        jtfID.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jtfID.setForeground(new java.awt.Color(0, 0, 0));

        jcbCustomerID.setBackground(new java.awt.Color(255, 255, 255));
        jcbCustomerID.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jcbCustomerID.setForeground(new java.awt.Color(0, 0, 0));

        jcbProductID.setBackground(new java.awt.Color(255, 255, 255));
        jcbProductID.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jcbProductID.setForeground(new java.awt.Color(0, 0, 0));
        jcbProductID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbProductIDActionPerformed(evt);
            }
        });

        jtfAmount.setBackground(new java.awt.Color(255, 255, 255));
        jtfAmount.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jtfAmount.setForeground(new java.awt.Color(0, 0, 0));

        jtfPrice.setBackground(new java.awt.Color(255, 255, 255));
        jtfPrice.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jtfPrice.setForeground(new java.awt.Color(0, 0, 0));

        jtfInterestRate.setBackground(new java.awt.Color(255, 255, 255));
        jtfInterestRate.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jtfInterestRate.setForeground(new java.awt.Color(0, 0, 0));

        jdcPawnDate.setBackground(new java.awt.Color(255, 255, 255));
        jdcPawnDate.setForeground(new java.awt.Color(0, 0, 0));
        jdcPawnDate.setDateFormatString("dd/MM/yyyy");
        jdcPawnDate.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N

        jdcTheNextInterestPaymentDate.setBackground(new java.awt.Color(255, 255, 255));
        jdcTheNextInterestPaymentDate.setForeground(new java.awt.Color(0, 0, 0));
        jdcTheNextInterestPaymentDate.setDateFormatString("dd/MM/yyyy");
        jdcTheNextInterestPaymentDate.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N

        jdcRedeemOrLiquidateDate.setBackground(new java.awt.Color(255, 255, 255));
        jdcRedeemOrLiquidateDate.setForeground(new java.awt.Color(0, 0, 0));
        jdcRedeemOrLiquidateDate.setDateFormatString("dd/MM/yyyy");
        jdcRedeemOrLiquidateDate.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N

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

        jbtnAddNewPawnCoupon.setBackground(new java.awt.Color(255, 255, 255));
        jbtnAddNewPawnCoupon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/addNew.png"))); // NOI18N
        jbtnAddNewPawnCoupon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbtnAddNewPawnCouponMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jbtnAddNewPawnCoupon, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jbtnAddNewPawnCoupon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, Short.MAX_VALUE)
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

        jtfLiquidatePrice.setBackground(new java.awt.Color(255, 255, 255));
        jtfLiquidatePrice.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jtfLiquidatePrice.setForeground(new java.awt.Color(0, 0, 0));

        jbtnPrintPawnCoupon.setBackground(new java.awt.Color(0, 255, 255));
        jbtnPrintPawnCoupon.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jbtnPrintPawnCoupon.setForeground(new java.awt.Color(0, 0, 0));
        jbtnPrintPawnCoupon.setText("Xem");
        jbtnPrintPawnCoupon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnPrintPawnCouponActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jcbCustomerID, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jcbProductID, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jtfAmount, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfPrice, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfInterestRate, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jdcPawnDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jdcTheNextInterestPaymentDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jdcRedeemOrLiquidateDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jtfLiquidatePrice, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jrbNotRedeemingStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jrbNeedToLiquidateStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jrbLateStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jrbAllStatus, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jrbRedeemedStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jrbLiquidatedStatus, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addComponent(jtfID, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jbtnAddPawnCoupon, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jbtnEditPawnCoupon, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jbtnPrintPawnCoupon, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(6, 6, 6))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jtfID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbCustomerID, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jdcRedeemOrLiquidateDate, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jdcPawnDate, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jcbProductID, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jtfAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jtfPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jtfInterestRate, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jdcTheNextInterestPaymentDate, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfLiquidatePrice, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jrbNotRedeemingStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jrbLateStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jrbRedeemedStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jrbLiquidatedStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jrbNeedToLiquidateStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jrbAllStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnEditPawnCoupon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnAddPawnCoupon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnPrintPawnCoupon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jtfLastDebt.setBackground(new java.awt.Color(255, 255, 255));
        jtfLastDebt.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jtfLastDebt.setForeground(new java.awt.Color(0, 0, 0));

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

        jtfTheMoneyPayment.setBackground(new java.awt.Color(255, 255, 255));
        jtfTheMoneyPayment.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jtfTheMoneyPayment.setForeground(new java.awt.Color(0, 0, 0));

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
                "Kỳ", "Ngày đóng", "Lãi", "Số tiền đóng", "Nợ", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblInterestPayment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblInterestPaymentMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtblInterestPayment);
        if (jtblInterestPayment.getColumnModel().getColumnCount() > 0) {
            jtblInterestPayment.getColumnModel().getColumn(0).setMinWidth(40);
            jtblInterestPayment.getColumnModel().getColumn(0).setPreferredWidth(40);
            jtblInterestPayment.getColumnModel().getColumn(0).setMaxWidth(40);
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
        jbtnReload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/reload (1).png"))); // NOI18N
        jbtnReload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnReloadActionPerformed(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));
        jPanel7.setForeground(new java.awt.Color(0, 0, 0));

        jlbInterestPaymentSum.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jlbInterestPaymentSum.setForeground(new java.awt.Color(0, 0, 0));
        jlbInterestPaymentSum.setText("Tổng  :");

        jlbToTalInterest.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jlbToTalInterest.setForeground(new java.awt.Color(0, 0, 0));
        jlbToTalInterest.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbToTalInterest.setText("0");

        jlbToTalTheMoneyPayed.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jlbToTalTheMoneyPayed.setForeground(new java.awt.Color(0, 0, 0));
        jlbToTalTheMoneyPayed.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbToTalTheMoneyPayed.setText("0");

        jlbLastDebt.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jlbLastDebt.setForeground(new java.awt.Color(0, 0, 0));
        jlbLastDebt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbLastDebt.setText("0");

        jLabel35.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jlbInterestPaymentSum, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbToTalInterest, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbToTalTheMoneyPayed, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbLastDebt, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jlbInterestPaymentSum)
                .addComponent(jlbToTalInterest, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jlbToTalTheMoneyPayed, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jlbLastDebt, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel34.setBackground(new java.awt.Color(0, 0, 0));
        jLabel34.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Ngày đóng");

        jdcPaymentDate.setBackground(new java.awt.Color(255, 255, 255));
        jdcPaymentDate.setForeground(new java.awt.Color(0, 0, 0));
        jdcPaymentDate.setDateFormatString("dd/MM/yyyy");
        jdcPaymentDate.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbtnAddInterestPayment, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jdcPaymentDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtfAmountOfDays, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfInterest, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfFine, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfTheMoneyHaveToPay, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfTheMoneyPayment, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfNewDebt, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtnEditInterestPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jtfTimes)
                            .addComponent(jtfLastDebt))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jbtnDeleteInterestPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jbtnReload, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfTimes, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfLastDebt, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jdcPaymentDate, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfAmountOfDays, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfInterest, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfFine, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfTheMoneyHaveToPay, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfTheMoneyPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfNewDebt, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane2)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbtnEditInterestPayment, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtnAddInterestPayment, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtnDeleteInterestPayment, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtnReload, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        jtblPawnCoupon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblPawnCouponMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jtblPawnCoupon);
        if (jtblPawnCoupon.getColumnModel().getColumnCount() > 0) {
            jtblPawnCoupon.getColumnModel().getColumn(0).setMinWidth(40);
            jtblPawnCoupon.getColumnModel().getColumn(0).setPreferredWidth(40);
            jtblPawnCoupon.getColumnModel().getColumn(0).setMaxWidth(40);
            jtblPawnCoupon.getColumnModel().getColumn(1).setMinWidth(150);
            jtblPawnCoupon.getColumnModel().getColumn(1).setPreferredWidth(150);
            jtblPawnCoupon.getColumnModel().getColumn(1).setMaxWidth(150);
            jtblPawnCoupon.getColumnModel().getColumn(2).setMinWidth(150);
            jtblPawnCoupon.getColumnModel().getColumn(2).setPreferredWidth(150);
            jtblPawnCoupon.getColumnModel().getColumn(2).setMaxWidth(150);
            jtblPawnCoupon.getColumnModel().getColumn(3).setMinWidth(150);
            jtblPawnCoupon.getColumnModel().getColumn(3).setPreferredWidth(150);
            jtblPawnCoupon.getColumnModel().getColumn(3).setMaxWidth(150);
            jtblPawnCoupon.getColumnModel().getColumn(4).setMinWidth(100);
            jtblPawnCoupon.getColumnModel().getColumn(4).setPreferredWidth(100);
            jtblPawnCoupon.getColumnModel().getColumn(4).setMaxWidth(100);
            jtblPawnCoupon.getColumnModel().getColumn(5).setMinWidth(120);
            jtblPawnCoupon.getColumnModel().getColumn(5).setPreferredWidth(120);
            jtblPawnCoupon.getColumnModel().getColumn(5).setMaxWidth(120);
            jtblPawnCoupon.getColumnModel().getColumn(6).setMinWidth(120);
            jtblPawnCoupon.getColumnModel().getColumn(6).setPreferredWidth(120);
            jtblPawnCoupon.getColumnModel().getColumn(6).setMaxWidth(120);
            jtblPawnCoupon.getColumnModel().getColumn(8).setMinWidth(120);
            jtblPawnCoupon.getColumnModel().getColumn(8).setPreferredWidth(120);
            jtblPawnCoupon.getColumnModel().getColumn(9).setMinWidth(120);
            jtblPawnCoupon.getColumnModel().getColumn(9).setPreferredWidth(120);
            jtblPawnCoupon.getColumnModel().getColumn(9).setMaxWidth(120);
        }

        jPanel9.setBackground(new java.awt.Color(204, 204, 204));

        jLabel28.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Tổng  :");

        jlbTotalPrice.setFont(new java.awt.Font("Times New Roman", 3, 16)); // NOI18N
        jlbTotalPrice.setForeground(new java.awt.Color(0, 0, 0));
        jlbTotalPrice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbTotalPrice.setText("0");
        jlbTotalPrice.setToolTipText("");
        jlbTotalPrice.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jlbTotalInterest.setFont(new java.awt.Font("Times New Roman", 3, 16)); // NOI18N
        jlbTotalInterest.setForeground(new java.awt.Color(0, 0, 0));
        jlbTotalInterest.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbTotalInterest.setText("0");
        jlbTotalInterest.setToolTipText("");
        jlbTotalInterest.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jlbTotalLiquidationPrice.setFont(new java.awt.Font("Times New Roman", 3, 16)); // NOI18N
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
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 591, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbTotalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbTotalInterest, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbTotalLiquidationPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbTotalLiquidationPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jlbTotalPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlbTotalInterest, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
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
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            if (pawnCoupon.getCustomer().getDeleteflag()) {
                MessageSupport.ErrorMessage("Lỗi", "Khách hàng ngưng phục vụ. Chọn khách hàng khác.");
            } else if (pawnCoupon.getProduct().getTypeOfProduct().getDeleteflag()) {
                MessageSupport.ErrorMessage("Lỗi", "Hàng hóa thuộc nhóm loại hàng hóa ngưng phục vụ. Chọn hàng hóa khác");
            } else if (pawnCoupon.getProduct().getStatus().equals("Chưa chuộc") || pawnCoupon.getProduct().getStatus().equals("Cần thanh lý")) {
                MessageSupport.ErrorMessage("Lỗi", "Mã hàng hóa đang tồn tại ở một hợp đồng có hiệu lực khác. Chọn mã hàng hóa khác.");
            } else if (PawnCouponController.getCurrentInstance().insert(pawnCoupon)) {
                MessageSupport.Message("Thông báo", "Thêm mới hợp đồng thành công.");
                ActivityHistoryController.getCurrentInstance()
                        .insert(new ActivityHistory(Support.dateToString(new Date(), Support.getDateTimeFormat()),
                                StaticUser.getCurrentInstanceUser(), "Thêm mới", "Hợp đồng", pawnCoupon.toString()));
                jbtnAddPawnCoupon.setEnabled(true);
                setPawnCouponDefault(null);
                setInterestPaymentDefault(null, null);
            }
        }
    }//GEN-LAST:event_jbtnAddPawnCouponActionPerformed

    private void jbtnEditPawnCouponActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEditPawnCouponActionPerformed
        PawnCoupon pawnCoupon = getPawnCouponFromForm();
        if (pawnCoupon != null) {
            if (pawnCoupon.getCustomer().getDeleteflag()) {
                MessageSupport.ErrorMessage("Lỗi", "Khách hàng ngưng phục vụ. Chọn khách hàng khác.");
            } else if (pawnCoupon.getProduct().getTypeOfProduct().getDeleteflag()) {
                MessageSupport.ErrorMessage("Lỗi", "Hàng hóa thuộc nhóm loại hàng hóa ngưng phục vụ. Chọn hàng hóa khác");
            } else if (pawnCoupon.getStatus().equals("Đã chuộc") && Integer.parseInt(jtfNewDebt.getText()) > 0) {
                MessageSupport.ErrorMessage("Lỗi", "Hợp đồng chưa đóng đủ lãi.");
            } else if (PawnCouponController.getCurrentInstance().update(pawnCoupon)) {
                MessageSupport.Message("Thông báo", "Sửa hợp đồng thành công.");
                ActivityHistoryController.getCurrentInstance()
                        .insert(new ActivityHistory(Support.dateToString(new Date(), Support.getDateTimeFormat()),
                                StaticUser.getCurrentInstanceUser(), "Sửa", "Hợp đồng", pawnCoupon.toString()));
                jbtnEditPawnCoupon.setEnabled(true);
                setPawnCouponDefault(null);
                setInterestPaymentDefault(null, null);
            } else {
                MessageSupport.Message("Thông báo", "Sửa hợp đồng thất bại.");
            }
        }
    }//GEN-LAST:event_jbtnEditPawnCouponActionPerformed

    private void jbtnReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnReloadActionPerformed
        setPawnCouponDefault(null);
        setInterestPaymentDefault(null, null);
    }//GEN-LAST:event_jbtnReloadActionPerformed

    private void jtblPawnCouponMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblPawnCouponMouseClicked
        int row = jtblPawnCoupon.getSelectedRow();
        if (evt.getClickCount() == 2 && row != -1) {
            JTable table = (JTable) evt.getSource();
            String pawnCouponId = (table.getModel().getValueAt(row, 1)).toString();
            PawnCoupon pawnCoupon = PawnCouponController.getCurrentInstance().getPawnCoupon(pawnCouponId);
            setPawnCouponDefault(pawnCoupon);
            setInterestPaymentDefault(pawnCoupon, null);
            if (jtblPawnCoupon.getSelectedColumn() == 2) {
                String customerId = (table.getModel().getValueAt(row, 2)).toString();
                Customer customer = CustomerController.getCurrentInstance().getCustomer(customerId);
                String title = "Khách hàng";
                if (JHomePageJFrameForm.jHomePageTabbedPane.indexOfTab(title) != -1) {
                    JHomePageJFrameForm.jHomePageTabbedPane.remove(JHomePageJFrameForm.jHomePageTabbedPane.indexOfTab(title));
                }
                JPanel jPanel = new JCustomerPanelForm(customer);
                JHomePageJFrameForm.jHomePageTabbedPane.addTab(title, jPanel);
                JHomePageJFrameForm.jHomePageTabbedPane.setSelectedComponent(jPanel);
            } else if (jtblPawnCoupon.getSelectedColumn() == 3) {
                String productId = (table.getModel().getValueAt(row, 3)).toString();
                Product product = ProductController.getCurrentInstance().getProduct(productId);
                String title = "Hàng hóa";
                if (JHomePageJFrameForm.jHomePageTabbedPane.indexOfTab(title) != -1) {
                    JHomePageJFrameForm.jHomePageTabbedPane.remove(JHomePageJFrameForm.jHomePageTabbedPane.indexOfTab(title));
                }
                JPanel jPanel = new JProductPanelForm(product);
                JHomePageJFrameForm.jHomePageTabbedPane.addTab(title, jPanel);
                JHomePageJFrameForm.jHomePageTabbedPane.setSelectedComponent(jPanel);
            }
        }
    }//GEN-LAST:event_jtblPawnCouponMouseClicked

    private void jrbPawnCouponStatusPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbPawnCouponStatusPerformed
        findPawnCoupon();
        if (jbtnEditPawnCoupon.isEnabled()) {
            if (getPawnCouponStatus().equals("Đã chuộc") || getPawnCouponStatus().equals("Đã thanh lý")) {
                jdcRedeemOrLiquidateDate.setDate(new Date());
                if (getPawnCouponStatus().equals("Đã thanh lý")) {
                    jtfLiquidatePrice.setEditable(true);
                }
            } else {
                jdcRedeemOrLiquidateDate.setDate(null);
                jtfLiquidatePrice.setEditable(false);
                jtfLiquidatePrice.setText("0");
            }
        }
    }//GEN-LAST:event_jrbPawnCouponStatusPerformed

    private void jtblInterestPaymentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblInterestPaymentMouseClicked
        int row = jtblInterestPayment.getSelectedRow();
        if (evt.getClickCount() == 2 && row != -1) {
            JTable table = (JTable) evt.getSource();
            PawnCoupon pawnCoupon = getPawnCouponFromForm();
            String times = table.getModel().getValueAt(row, 0).toString();
            InterestPayment interestPayment = InterestPaymentController.getCurrentInstance().getInterestPayment(pawnCoupon, times);
            setInterestPaymentDefault(pawnCoupon, interestPayment);
        }
    }//GEN-LAST:event_jtblInterestPaymentMouseClicked

    private void jbtnAddInterestPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAddInterestPaymentActionPerformed
        InterestPayment interestPayment = getInterestPaymentFromForm();
        if (interestPayment != null) {
            if (InterestPaymentController.getCurrentInstance().insert(interestPayment)) {
                MessageSupport.Message("Thông báo", "Thêm mới kỳ đóng lãi thành công.");
                ActivityHistoryController.getCurrentInstance()
                        .insert(new ActivityHistory(Support.dateToString(new Date(), Support.getDateTimeFormat()),
                                StaticUser.getCurrentInstanceUser(), "Thêm mới", "Kỳ đóng lãi", interestPayment.toString()));
                setInterestPaymentDefault(PawnCouponController.getCurrentInstance().getPawnCoupon(jtfID.getText()), null);
            }
        }
    }//GEN-LAST:event_jbtnAddInterestPaymentActionPerformed

    private void jbtnEditInterestPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEditInterestPaymentActionPerformed
        InterestPayment interestPayment = getInterestPaymentFromForm();
        if (InterestPaymentController.getCurrentInstance().update(interestPayment)) {
            MessageSupport.Message("Thông báo", "Sửa kỳ đóng lãi thành công.");
            ActivityHistoryController.getCurrentInstance()
                    .insert(new ActivityHistory(Support.dateToString(new Date(), Support.getDateTimeFormat()),
                            StaticUser.getCurrentInstanceUser(), "Sửa", "Kỳ đóng lãi", interestPayment.toString()));
            setInterestPaymentDefault(getPawnCouponFromForm(), null);
        }
    }//GEN-LAST:event_jbtnEditInterestPaymentActionPerformed

    private void jbtnDeleteInterestPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDeleteInterestPaymentActionPerformed
        InterestPayment interestPayment = InterestPaymentController.getCurrentInstance().getInterestPayment(getPawnCouponFromForm(), jtfTimes.getText());
        if (MessageSupport.MessageConfirm("Xác nhận xóa", "Bạn muốn xóa kỳ đóng lãi này?") == JOptionPane.YES_OPTION) {
            if (InterestPaymentController.getCurrentInstance().delete(interestPayment)) {
                MessageSupport.Message("Thông báo", "Xóa kỳ đóng lãi thành công.");
                ActivityHistoryController.getCurrentInstance()
                        .insert(new ActivityHistory(Support.dateToString(new Date(), Support.getDateTimeFormat()),
                                StaticUser.getCurrentInstanceUser(), "Xóa", "Kỳ đóng lãi", interestPayment.toString()));
                setInterestPaymentDefault(getPawnCouponFromForm(), null);
            }
        }
    }//GEN-LAST:event_jbtnDeleteInterestPaymentActionPerformed

    private void jbtnDeleteTabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDeleteTabActionPerformed
        JHomePageJFrameForm.jHomePageTabbedPane.remove(JHomePageJFrameForm.jHomePageTabbedPane.indexOfTab("Hợp đồng"));
    }//GEN-LAST:event_jbtnDeleteTabActionPerformed

    private void jbtnPrintPawnCouponActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnPrintPawnCouponActionPerformed
        PawnCoupon pawnCoupon = getPawnCouponFromForm();
        PawnCouponPageBerJFrameForm pawnCouponPageBerJFrameForm = new PawnCouponPageBerJFrameForm(pawnCoupon);
        pawnCouponPageBerJFrameForm.setVisible(true);
    }//GEN-LAST:event_jbtnPrintPawnCouponActionPerformed

    private void jcbProductIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbProductIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbProductIDActionPerformed

    private void jbtnAddNewPawnCouponMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnAddNewPawnCouponMouseClicked
        if (evt.getClickCount() == 2) {
            setPawnCouponDefault(null);
            jbtnAddPawnCoupon.setEnabled(true);
            jtfID.setText(PawnCouponController.getCurrentInstance().getNewID());
            jtfID.setEditable(false);
            jtfInterestRate.setText("0.3");
            jdcPawnDate.setDate(new Date());
            jdcTheNextInterestPaymentDate.setDate(Support.addDate(jdcPawnDate.getDate(), 14));
            jtfLiquidatePrice.setText("0");
            jtfLiquidatePrice.setEditable(false);
            setPawnCouponStatus("Chưa chuộc");
            jrbRedeemedStatus.setEnabled(false);
            jrbLateStatus.setEnabled(false);
            jrbNeedToLiquidateStatus.setEnabled(false);
            jrbLiquidatedStatus.setEnabled(false);
            jrbAllStatus.setEnabled(false);
        }
    }//GEN-LAST:event_jbtnAddNewPawnCouponMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
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
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JButton jbtnAddInterestPayment;
    private javax.swing.JButton jbtnAddNewPawnCoupon;
    private javax.swing.JButton jbtnAddPawnCoupon;
    private javax.swing.JButton jbtnDeleteInterestPayment;
    private javax.swing.JButton jbtnDeleteTab;
    private javax.swing.JButton jbtnEditInterestPayment;
    private javax.swing.JButton jbtnEditPawnCoupon;
    private javax.swing.JButton jbtnPrintPawnCoupon;
    private javax.swing.JButton jbtnReload;
    private javax.swing.JComboBox<String> jcbCustomerID;
    private javax.swing.JComboBox<String> jcbProductID;
    private com.toedter.calendar.JDateChooser jdcPawnDate;
    private com.toedter.calendar.JDateChooser jdcPaymentDate;
    private com.toedter.calendar.JDateChooser jdcRedeemOrLiquidateDate;
    private com.toedter.calendar.JDateChooser jdcTheNextInterestPaymentDate;
    private javax.swing.JLabel jlbInterestPaymentSum;
    private javax.swing.JLabel jlbLastDebt;
    private javax.swing.JLabel jlbToTalInterest;
    private javax.swing.JLabel jlbToTalTheMoneyPayed;
    private javax.swing.JLabel jlbTotalInterest;
    private javax.swing.JLabel jlbTotalLiquidationPrice;
    private javax.swing.JLabel jlbTotalPrice;
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
    private javax.swing.JTextField jtfID;
    private javax.swing.JTextField jtfInterest;
    private javax.swing.JTextField jtfInterestRate;
    private javax.swing.JTextField jtfLastDebt;
    private javax.swing.JTextField jtfLiquidatePrice;
    private javax.swing.JTextField jtfNewDebt;
    private javax.swing.JTextField jtfPrice;
    private javax.swing.JTextField jtfTheMoneyHaveToPay;
    private javax.swing.JTextField jtfTheMoneyPayment;
    private javax.swing.JTextField jtfTimes;
    // End of variables declaration//GEN-END:variables
}
