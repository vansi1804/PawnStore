/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.JTabbedPaneForm;

import Model.Customer;
import Controller.PawnCouponController;
import Model.InterestPayment;
import Model.PawnCoupon;
import Model.Product;
import Model.TypeOfProduct;
import Model.User;
import Support.*;
import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class JPawnCouponPanelForm extends javax.swing.JPanel {

    public static int interestPaymentCycle = 15;

    PawnCouponController _pawncouponController = new PawnCouponController();

    public JPawnCouponPanelForm() {
        initComponents();
        setPawnCouponDefault(true);
        loadDataPawnCoupon(_pawncouponController.getPawnCouponList());
        setInterestPaymentDefault(null);
        Support.FormatTableHeader(jtbInterestPaymentHistory);
        Support.FormatTableHeader(jtblPawnCoupon);
    }

    public void setPawnCouponDefault(boolean b) {
        jtfPawnCouponID.setEditable(true);
        jtfPawnCouponID.setText(null);
        loadCustomerComboBox();
        loadProductComboBox();
        jcbCustomerID.setSelectedItem("Tất cả");
        jcbProductID.setSelectedItem("Tất cả");
        jtfAmount.setText(null);
        jtfPrice.setText(null);
        jtfInterestRate.setText(b ? null : "0.3");
        jDCPawnDate.setDate(b ? null : Support.stringToDate(Support.dateToString(new Date())));
        jDCPawnDate.setForeground(b ? Color.black : Color.BLACK);
        jDCPawnDate.setEnabled(b);
        jDCTheNextInterestPaymentDate.setDate(null);
        jDCTheNextInterestPaymentDate.setEnabled(b);
        jDCRedeemingDate.setDate(null);
        jDCRedeemingDate.setEnabled(b);
        if (b) {
            setStatus("");
        } else {
            setStatus("Chưa chuộc");
        }
    }

    public void setInterestPaymentDefault(PawnCoupon pawnCoupon) {
        if (pawnCoupon == null) {
            jtfTimes.setText(null);
            jDCInterestPaymentUntilDate.setDate(null);
            jtfAmountOfDays.setText(null);
            jtfInterest.setText(null);
            jtfFine.setText(null);
            jtfLastDebt.setText(null);
            jtfTotalHaveToPay.setText(null);
            jtfMoney.setText(null);
            jtfNewDebt.setText(null);
            jtanote.setText(null);
            ////////////////////////////////////////////////////////////////////
            jtfTimes.setEditable(false);
            jtfLastDebt.setEditable(false);
            jDCInterestPaymentUntilDate.setEnabled(false);
            jtfAmountOfDays.setEditable(false);
            jtfInterest.setEditable(false);
            jtfFine.setEditable(false);
            jtfTotalHaveToPay.setEditable(false);
            jtfMoney.setEditable(false);
            jtfNewDebt.setEditable(false);
            jtanote.setEditable(false);
            jbtnAddInterestPaymentHistory.setEnabled(false);
            jbtnEditInterestPaymentHistory.setEnabled(false);
        } else {
            if (!isStoredProductStatus(pawnCoupon.getStatus())) {
                setInterestPaymentDefault(null);
            } else {
                ArrayList<InterestPayment> interestPayments = _pawncouponController.getInterestPaymentController().getList(pawnCoupon);
                jtfTimes.setText(String.valueOf(interestPayments.size() + 1));
                float lastDebt = interestPayments.size() == 0 ? 0 : interestPayments.get(interestPayments.size() - 1).getDebt();
                jtfLastDebt.setText(Support.BigFloatToString(lastDebt));
                jDCInterestPaymentUntilDate.setDate(Support.getToday());
                jDCInterestPaymentUntilDate.addPropertyChangeListener(new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        Date interestPaymentUntilDate = null;
                        boolean isValidValue = true;
                        try {
                            interestPaymentUntilDate = jDCInterestPaymentUntilDate.getDate();
                            isValidValue = (interestPaymentUntilDate != null);
                        } catch (Exception e) {
                            isValidValue = false;
                            e.printStackTrace();
                        }
                        if (isValidValue) {
                            ArrayList<InterestPayment> interestPayments = _pawncouponController.getInterestPaymentController().getList(pawnCoupon);
                            int amountOfDays;
                            if (interestPayments.size() == 0) {
                                amountOfDays = (int) Support.subtractDate(pawnCoupon.getPawnDate(), jDCInterestPaymentUntilDate.getDate()) + 1;
                            } else {
                                InterestPayment lastPayment = interestPayments.get(interestPayments.size() - 1);
                                amountOfDays = (int) Support.subtractDate(lastPayment.getPaymentDate(), jDCInterestPaymentUntilDate.getDate());
                            }
                            if (amountOfDays < 0) {
                                MessageSupport.ShowError(null, "Lỗi", "Ngày đóng lãi tới không được trước ngày cầm hoặc ngày đóng lãi tới gần nhất.");
                                jDCInterestPaymentUntilDate.setDate(Support.getToday());
                            } else {
                                jtfAmountOfDays.setText(String.valueOf(amountOfDays));
                                float interest = amountOfDays * pawnCoupon.getPrice() * pawnCoupon.getInterestRate() / 100;
                                jtfInterest.setText(Support.BigFloatToString(interest));
                                int lateDays = amountOfDays - interestPaymentCycle;
                                float fine = 0;
                                if (lateDays > 0) {
                                    if (lateDays > 10) {
                                        fine = (float) (interest * 0.5);
                                        jtfFine.setText(Support.BigFloatToString(fine));
                                    }
                                    jtanote.setText("Trễ " + lateDays + " ngày");
                                } else {
                                    jtfFine.setText("0");
                                    jtanote.setText(null);
                                }
                                if (!CheckSupport.isEmpty(jtfLastDebt.getText())) {
                                    float totalHaveToPay = Float.parseFloat(jtfLastDebt.getText()) + interest + fine;
                                    jtfTotalHaveToPay.setText(Support.BigFloatToString(totalHaveToPay));
                                }
                            }
                        }
                    }
                });

                jtfMoney.setText("0");
                jtfMoney.getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        jtfMoneyValueChange();
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        jtfMoneyValueChange();
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        jtfMoneyValueChange();
                    }

                    public void jtfMoneyValueChange() {
                        float money = 0;
                        boolean isValidValue = true;
                        try {
                            if (!CheckSupport.isEmpty(jtfMoney.getText())) {
                                money = Float.parseFloat(jtfMoney.getText());
                            }
                            isValidValue = money >= 0;
                        } catch (Exception e) {
                            isValidValue = false;
                            e.printStackTrace();
                        }
                        if (isValidValue) {
                            if (!CheckSupport.isEmpty(jtfTotalHaveToPay.getText())) {
                                float newDebt = Float.parseFloat(jtfTotalHaveToPay.getText()) - money;
                                jtfNewDebt.setText(Support.BigFloatToString(newDebt));
                            }
                        }
                    }
                });
                jtfNewDebt.setText("0");

                ////////////////////////////////////////////////////////////////
                jDCInterestPaymentUntilDate.setEnabled(true);
                jtfFine.setEditable(true);
                jtfMoney.setEditable(true);
                jtfNewDebt.setEditable(true);
                jtanote.setEditable(true);
                jbtnAddInterestPaymentHistory.setEnabled(true);
                jbtnEditInterestPaymentHistory.setEnabled(true);
            }
            loadInterestPaymentHistory(pawnCoupon);
        }
    }

    public void loadCustomerComboBox() {
        ArrayList<Customer> _customers = _pawncouponController.getCustomerController().getList();
        ArrayList<String> customerIDs = new ArrayList<>();
        for (Customer customer : _customers) {
            customerIDs.add(customer.getId());
        }
        Support.loadCombobox(customerIDs, jcbCustomerID);
    }

    public void loadProductComboBox() {
        ArrayList<Product> _products = _pawncouponController.getProductController().getProductList();
        ArrayList<String> productIDs = new ArrayList<>();
        for (Product product : _products) {
            productIDs.add(product.getProductID());
        }
        Support.loadCombobox(productIDs, jcbProductID);
    }

    public void setStatus(String status) {
        if (CheckSupport.equals(status, "Chưa chuộc")) {
            jrbNotRedeemed.setSelected(true);
        } else if (CheckSupport.equals(status, "Đã chuộc")) {
            jrbRedeemed.setSelected(true);
        } else if (CheckSupport.equals(status, "Trễ")) {
            jrbLate.setSelected(true);
        } else if (CheckSupport.equals(status, "Cần thanh lý")) {
            jrbNeedToLiquidate.setSelected(true);
        } else if (CheckSupport.equals(status, "Đã thanh lý")) {
            jrbLiquidated.setSelected(true);
        } else {
            jrbAll.setSelected(true);
        }
    }

    public String getStatus() {
        if (jrbNotRedeemed.isSelected()) {
            return "Chưa chuộc";
        } else if (jrbRedeemed.isSelected()) {
            return "Đã chuộc";
        } else if (jrbLate.isSelected()) {
            return "Trễ";
        } else if (jrbNeedToLiquidate.isSelected()) {
            return "Cần thanh lý";
        } else if (jrbLiquidated.isSelected()) {
            return "Đã thanh lý";
        } else {
            return "";
        }
    }

    public boolean isStoredProductStatus(String Status) {
        return CheckSupport.equals(Status, "Chưa chuộc") || CheckSupport.equals(Status, "Cần thanh lý") || CheckSupport.equals(Status, "Trễ");
    }

    public void refreshPawnCoupon(ArrayList<PawnCoupon> _pawncouponList) {
        for (PawnCoupon pawnCoupon : _pawncouponList) {
            if (_pawncouponController.checkForLate(pawnCoupon)) {
                pawnCoupon.setStatus("Trễ");
                _pawncouponController.edit(pawnCoupon);
            }
        }
    }

    public void loadDataPawnCoupon(ArrayList<PawnCoupon> _pawncouponList) {
        DefaultTableModel model = (DefaultTableModel) jtblPawnCoupon.getModel();
        model.setRowCount(0);
        float totalprice = 0;
        float totalInterest = 0;
        Object rowData[] = new Object[12];
        int STT = 1;
        for (int i = 0; i < _pawncouponList.size(); i++) {
            rowData[0] = String.valueOf(STT++);
            rowData[1] = _pawncouponList.get(i).getId();
            rowData[2] = _pawncouponList.get(i).getCustomer().getId();
            rowData[3] = _pawncouponList.get(i).getProduct().getProductID();
            rowData[4] = _pawncouponList.get(i).getAmount();
            rowData[5] = Support.BigFloatToString(_pawncouponList.get(i).getPrice());
            totalprice += _pawncouponList.get(i).getPrice();
            rowData[6] = _pawncouponList.get(i).getInterestRate();
            float interest = _pawncouponList.get(i).getPrice() * _pawncouponList.get(i).getInterestRate() / 100;
            rowData[7] = Support.BigFloatToString(interest);
            totalInterest += interest;
            rowData[8] = Support.dateToString(_pawncouponList.get(i).getPawnDate());
            rowData[9] = Support.dateToString(_pawncouponList.get(i).getRedeemingDate());
            rowData[10] = _pawncouponList.get(i).getUser().getUsername();
            rowData[11] = _pawncouponList.get(i).getStatus();
            model.addRow(rowData);
        }
        jlbTotalPrice.setText(Support.BigFloatToString(totalprice));
        jlbTotalInterestRate.setText(Support.BigFloatToString(totalInterest));
        Support.setDataTableCenter(jtblPawnCoupon);
    }

    public PawnCoupon getPawnCouponFromForm() {
        try {
            String id = jtfPawnCouponID.getText();
            Date pawnDate = jDCPawnDate.getDate();
            String customerID = jcbCustomerID.getSelectedItem().toString();

            Customer customer;
            if (CheckSupport.equals(customerID, "Tất cả")) {
                customer = new Customer(" ", " ", " ", " ", " ");
            } else {
                customer = _pawncouponController.getCustomerController().findByID(customerID);
            }

            String productID = jcbProductID.getSelectedItem().toString();
            Product product;
            if (CheckSupport.equals(productID, "Tất cả")) {
                product = new Product(" ", " ", " ", " ", new TypeOfProduct(" ", " "));
            } else {
                product = _pawncouponController.getProductController().findProductByID(jcbProductID.getSelectedItem().toString());
            }

            int amount = CheckSupport.isEmpty(jtfAmount.getText()) ? -1 : Integer.parseInt(jtfAmount.getText());
            float price = CheckSupport.isEmpty(jtfPrice.getText()) ? -1 : Float.parseFloat(jtfPrice.getText());
            float interestRate = CheckSupport.isEmpty(jtfInterestRate.getText()) ? -1 : Float.parseFloat(jtfInterestRate.getText());
            Date redeemingDate = jDCRedeemingDate.getDate();
            User user = User.getCurrentInstance();
            String status = getStatus();
            return new PawnCoupon(id, pawnDate, customer, product, amount, price, interestRate, redeemingDate, status, user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void loadInterestPaymentHistory(PawnCoupon pawnCoupon) {
        DefaultTableModel model = (DefaultTableModel) jtbInterestPaymentHistory.getModel();
        model.setRowCount(0);
        float totalInterestPayment = 0;
        Object rowData[] = new Object[6];
        ArrayList<InterestPayment> interestPayments = _pawncouponController.getInterestPaymentController().getList(pawnCoupon);
        for (int i = 0; i < interestPayments.size(); i++) {
            rowData[0] = interestPayments.get(i).getTimes();
            rowData[1] = interestPayments.get(i).getPaymentDate();
            rowData[2] = interestPayments.get(i).getPaymentUntilDate();
            rowData[3] = interestPayments.get(i).getMoney();
            totalInterestPayment += interestPayments.get(i).getMoney();
            rowData[4] = interestPayments.get(i).getDebt();
            rowData[5] = interestPayments.get(i).getNote();
            model.addRow(rowData);
        }
        jlbTotalInterestPayment.setText(Support.BigFloatToString(totalInterestPayment));
        Support.setDataTableCenter(jtbInterestPaymentHistory);
    }

    public InterestPayment getInterestPaymentFromForm() {
        try {
            PawnCoupon pawnCoupon = _pawncouponController.findPawnCouponByProperty("_id", jtfPawnCouponID.getText());
            int times = Integer.parseInt(jtfTimes.getText());
            Date interestPaymentDate = Support.getToday();
            Date interestPaymentUntilDate = jDCInterestPaymentUntilDate.getDate();
            if (interestPaymentUntilDate == null) {
                return null;
            }
            float amountOfMoney = Float.parseFloat(jtfMoney.getText());
            float newDebt = Float.parseFloat(jtfNewDebt.getText());
            String note = jtanote.getText();
            return new InterestPayment(pawnCoupon, times, interestPaymentDate, interestPaymentUntilDate, amountOfMoney, newDebt, note);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
        jPanel8 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jtfPawnCouponID = new javax.swing.JTextField();
        jcbCustomerID = new javax.swing.JComboBox<>();
        jcbProductID = new javax.swing.JComboBox<>();
        jtfAmount = new javax.swing.JTextField();
        jtfPrice = new javax.swing.JTextField();
        jtfInterestRate = new javax.swing.JTextField();
        jbtnCreateNew = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jrbNotRedeemed = new javax.swing.JRadioButton();
        jrbRedeemed = new javax.swing.JRadioButton();
        jrbLate = new javax.swing.JRadioButton();
        jrbNeedToLiquidate = new javax.swing.JRadioButton();
        jrbLiquidated = new javax.swing.JRadioButton();
        jrbAll = new javax.swing.JRadioButton();
        jbtnFindPawnCoupon = new javax.swing.JButton();
        jbtnAddPawnCoupon = new javax.swing.JButton();
        jbtnEditPawnCoupon = new javax.swing.JButton();
        jDCPawnDate = new com.toedter.calendar.JDateChooser();
        jDCRedeemingDate = new com.toedter.calendar.JDateChooser();
        jLabel24 = new javax.swing.JLabel();
        jDCTheNextInterestPaymentDate = new com.toedter.calendar.JDateChooser();
        jLabel29 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jtfAmountOfDays = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jtaNote = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtanote = new javax.swing.JTextArea();
        jbtnEditInterestPaymentHistory = new javax.swing.JButton();
        jbtnAddInterestPaymentHistory = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtbInterestPaymentHistory = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jlbTotalInterestPayment = new javax.swing.JLabel();
        jDCInterestPaymentUntilDate = new com.toedter.calendar.JDateChooser();
        jLabel20 = new javax.swing.JLabel();
        jtfInterest = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jtfMoney = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jtfNewDebt = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jtfTimes = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jtfFine = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jtfLastDebt = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jtfTotalHaveToPay = new javax.swing.JTextField();
        jbtnReloadAll = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtblPawnCoupon = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jlbTotalPrice = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jlbTotalInterestRate = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();

        jLabel5.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel5.setText("Mã hợp đồng");

        jLabel6.setText("jLabel6");

        jLabel13.setText("jLabel13");

        jLabel16.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel16.setText("Số tiền");

        jTextField6.setText("jTextField2");

        setBackground(new java.awt.Color(102, 102, 102));

        jPanel8.setBackground(new java.awt.Color(153, 153, 153));

        jLabel25.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("HỢP ĐỒNG CẦM");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Hợp đồng");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Mã hợp đồng");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Mã khách hàng");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Mã hàng cầm");
        jLabel4.setPreferredSize(new java.awt.Dimension(82, 30));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Số lượng");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Giá");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Lãi suất (%/ngày)");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Ngày cầm");

        jtfPawnCouponID.setBackground(new java.awt.Color(255, 255, 255));
        jtfPawnCouponID.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jtfPawnCouponID.setForeground(new java.awt.Color(0, 0, 0));

        jcbCustomerID.setBackground(new java.awt.Color(255, 255, 255));
        jcbCustomerID.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jcbCustomerID.setForeground(new java.awt.Color(0, 0, 0));
        jcbCustomerID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jcbProductID.setBackground(new java.awt.Color(255, 255, 255));
        jcbProductID.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jcbProductID.setForeground(new java.awt.Color(0, 0, 0));
        jcbProductID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jtfAmount.setBackground(new java.awt.Color(255, 255, 255));
        jtfAmount.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jtfAmount.setForeground(new java.awt.Color(0, 0, 0));

        jtfPrice.setBackground(new java.awt.Color(255, 255, 255));
        jtfPrice.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jtfPrice.setForeground(new java.awt.Color(0, 0, 0));

        jtfInterestRate.setBackground(new java.awt.Color(255, 255, 255));
        jtfInterestRate.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jtfInterestRate.setForeground(new java.awt.Color(0, 0, 0));

        jbtnCreateNew.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jbtnCreateNew.setForeground(new java.awt.Color(0, 0, 0));
        jbtnCreateNew.setText("Tạo mới");
        jbtnCreateNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCreateNewActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Trạng thái");

        jrbNotRedeemed.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(jrbNotRedeemed);
        jrbNotRedeemed.setForeground(new java.awt.Color(0, 0, 0));
        jrbNotRedeemed.setText("Chưa chuộc");
        jrbNotRedeemed.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                StatusMouseClicked(evt);
            }
        });

        jrbRedeemed.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(jrbRedeemed);
        jrbRedeemed.setForeground(new java.awt.Color(0, 0, 0));
        jrbRedeemed.setText("Đã chuộc");
        jrbRedeemed.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                StatusMouseClicked(evt);
            }
        });

        jrbLate.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(jrbLate);
        jrbLate.setForeground(new java.awt.Color(0, 0, 0));
        jrbLate.setText("Trễ hạn");
        jrbLate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                StatusMouseClicked(evt);
            }
        });

        jrbNeedToLiquidate.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(jrbNeedToLiquidate);
        jrbNeedToLiquidate.setForeground(new java.awt.Color(0, 0, 0));
        jrbNeedToLiquidate.setText("Cần thanh lý");
        jrbNeedToLiquidate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                StatusMouseClicked(evt);
            }
        });

        jrbLiquidated.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(jrbLiquidated);
        jrbLiquidated.setForeground(new java.awt.Color(0, 0, 0));
        jrbLiquidated.setText("Đã thanh lý");
        jrbLiquidated.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                StatusMouseClicked(evt);
            }
        });

        jrbAll.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(jrbAll);
        jrbAll.setForeground(new java.awt.Color(0, 0, 0));
        jrbAll.setText("Tất cả");
        jrbAll.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                StatusMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jrbNotRedeemed, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jrbRedeemed, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jrbNeedToLiquidate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jrbLiquidated, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jrbLate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jrbAll, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrbNotRedeemed)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrbRedeemed)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrbLate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrbNeedToLiquidate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrbLiquidated)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jrbAll))
        );

        jbtnFindPawnCoupon.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jbtnFindPawnCoupon.setForeground(new java.awt.Color(0, 0, 0));
        jbtnFindPawnCoupon.setText("Tìm");
        jbtnFindPawnCoupon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnFindPawnCouponActionPerformed(evt);
            }
        });

        jbtnAddPawnCoupon.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jbtnAddPawnCoupon.setForeground(new java.awt.Color(0, 0, 0));
        jbtnAddPawnCoupon.setText("Thêm");
        jbtnAddPawnCoupon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAddPawnCouponActionPerformed(evt);
            }
        });

        jbtnEditPawnCoupon.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jbtnEditPawnCoupon.setForeground(new java.awt.Color(0, 0, 0));
        jbtnEditPawnCoupon.setText("Sửa");
        jbtnEditPawnCoupon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEditPawnCouponActionPerformed(evt);
            }
        });

        jDCPawnDate.setBackground(new java.awt.Color(255, 255, 255));
        jDCPawnDate.setForeground(new java.awt.Color(0, 0, 0));
        jDCPawnDate.setDateFormatString("yyyy-MM-dd");

        jDCRedeemingDate.setBackground(new java.awt.Color(255, 255, 255));
        jDCRedeemingDate.setForeground(new java.awt.Color(0, 0, 0));
        jDCRedeemingDate.setDateFormatString("yyyy-MM-dd");

        jLabel24.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Ngày đóng tiếp theo");

        jDCTheNextInterestPaymentDate.setBackground(new java.awt.Color(255, 255, 255));
        jDCTheNextInterestPaymentDate.setForeground(new java.awt.Color(0, 0, 0));
        jDCTheNextInterestPaymentDate.setDateFormatString("yyyy-MM-dd");

        jLabel29.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Ngày chuộc/thanh lý");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcbCustomerID, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtfPawnCouponID)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jcbProductID, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtfAmount)
                            .addComponent(jtfPrice)
                            .addComponent(jtfInterestRate)
                            .addComponent(jDCPawnDate, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(jDCTheNextInterestPaymentDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDCRedeemingDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbtnAddPawnCoupon, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnEditPawnCoupon, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtnCreateNew)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnFindPawnCoupon, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(202, 202, 202))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfPawnCouponID, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnCreateNew))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jcbCustomerID, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jcbProductID, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfInterestRate, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDCPawnDate, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(226, 226, 226))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jDCTheNextInterestPaymentDate, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDCRedeemingDate, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbtnFindPawnCoupon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtnEditPawnCoupon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtnAddPawnCoupon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(162, 162, 162))))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Lịch sử đóng lãi");

        jtfAmountOfDays.setBackground(new java.awt.Color(255, 255, 255));
        jtfAmountOfDays.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jtfAmountOfDays.setForeground(new java.awt.Color(0, 0, 0));

        jLabel15.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Số ngày");

        jtaNote.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jtaNote.setForeground(new java.awt.Color(0, 0, 0));
        jtaNote.setText("Ghi chú");

        jtanote.setBackground(new java.awt.Color(255, 255, 255));
        jtanote.setColumns(10);
        jtanote.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jtanote.setForeground(new java.awt.Color(0, 0, 0));
        jtanote.setRows(3);
        jtanote.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane1.setViewportView(jtanote);

        jbtnEditInterestPaymentHistory.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jbtnEditInterestPaymentHistory.setForeground(new java.awt.Color(0, 0, 0));
        jbtnEditInterestPaymentHistory.setText("Sửa");
        jbtnEditInterestPaymentHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEditInterestPaymentHistoryActionPerformed(evt);
            }
        });

        jbtnAddInterestPaymentHistory.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jbtnAddInterestPaymentHistory.setForeground(new java.awt.Color(0, 0, 0));
        jbtnAddInterestPaymentHistory.setText("Thêm");
        jbtnAddInterestPaymentHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAddInterestPaymentHistoryActionPerformed(evt);
            }
        });

        jtbInterestPaymentHistory.setBackground(new java.awt.Color(255, 255, 255));
        jtbInterestPaymentHistory.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jtbInterestPaymentHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kỳ đóng", "Ngày đóng", "Đóng tới ngày", "Số tiền", "Nợ", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtbInterestPaymentHistory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtbInterestPaymentHistoryMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jtbInterestPaymentHistory);
        if (jtbInterestPaymentHistory.getColumnModel().getColumnCount() > 0) {
            jtbInterestPaymentHistory.getColumnModel().getColumn(0).setMinWidth(60);
            jtbInterestPaymentHistory.getColumnModel().getColumn(0).setPreferredWidth(60);
            jtbInterestPaymentHistory.getColumnModel().getColumn(0).setMaxWidth(60);
            jtbInterestPaymentHistory.getColumnModel().getColumn(1).setMinWidth(100);
            jtbInterestPaymentHistory.getColumnModel().getColumn(1).setPreferredWidth(100);
            jtbInterestPaymentHistory.getColumnModel().getColumn(1).setMaxWidth(100);
            jtbInterestPaymentHistory.getColumnModel().getColumn(2).setMinWidth(100);
            jtbInterestPaymentHistory.getColumnModel().getColumn(2).setPreferredWidth(100);
            jtbInterestPaymentHistory.getColumnModel().getColumn(2).setMaxWidth(100);
            jtbInterestPaymentHistory.getColumnModel().getColumn(3).setMinWidth(90);
            jtbInterestPaymentHistory.getColumnModel().getColumn(3).setPreferredWidth(90);
            jtbInterestPaymentHistory.getColumnModel().getColumn(3).setMaxWidth(90);
            jtbInterestPaymentHistory.getColumnModel().getColumn(4).setMinWidth(90);
            jtbInterestPaymentHistory.getColumnModel().getColumn(4).setPreferredWidth(90);
            jtbInterestPaymentHistory.getColumnModel().getColumn(4).setMaxWidth(90);
        }

        jLabel17.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Kỳ đóng");

        jPanel13.setBackground(new java.awt.Color(204, 204, 204));

        jLabel19.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel19.setText("Tổng      :");

        jlbTotalInterestPayment.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jlbTotalInterestPayment.setForeground(new java.awt.Color(0, 0, 0));
        jlbTotalInterestPayment.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbTotalInterestPayment.setText("0");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbTotalInterestPayment, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jlbTotalInterestPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jDCInterestPaymentUntilDate.setBackground(new java.awt.Color(255, 255, 255));
        jDCInterestPaymentUntilDate.setForeground(new java.awt.Color(0, 0, 0));
        jDCInterestPaymentUntilDate.setDateFormatString("yyyy-MM-dd");

        jLabel20.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Lãi");

        jtfInterest.setBackground(new java.awt.Color(255, 255, 255));
        jtfInterest.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jtfInterest.setForeground(new java.awt.Color(0, 0, 0));

        jLabel21.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Đóng");

        jtfMoney.setBackground(new java.awt.Color(255, 255, 255));
        jtfMoney.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jtfMoney.setForeground(new java.awt.Color(0, 0, 0));

        jLabel22.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Nợ mới");

        jtfNewDebt.setBackground(new java.awt.Color(255, 255, 255));
        jtfNewDebt.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jtfNewDebt.setForeground(new java.awt.Color(0, 0, 0));

        jLabel23.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Đóng tới ngày");

        jtfTimes.setBackground(new java.awt.Color(255, 255, 255));
        jtfTimes.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jtfTimes.setForeground(new java.awt.Color(0, 0, 0));

        jLabel26.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Phạt");

        jtfFine.setBackground(new java.awt.Color(255, 255, 255));
        jtfFine.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jtfFine.setForeground(new java.awt.Color(0, 0, 0));

        jLabel27.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Nợ cũ");

        jtfLastDebt.setBackground(new java.awt.Color(255, 255, 255));
        jtfLastDebt.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jtfLastDebt.setForeground(new java.awt.Color(0, 0, 0));

        jLabel28.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Phải đóng");

        jtfTotalHaveToPay.setBackground(new java.awt.Color(255, 255, 255));
        jtfTotalHaveToPay.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jtfTotalHaveToPay.setForeground(new java.awt.Color(0, 0, 0));

        jbtnReloadAll.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jbtnReloadAll.setForeground(new java.awt.Color(0, 0, 0));
        jbtnReloadAll.setText("Tải lại");
        jbtnReloadAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnReloadAllActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel14)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtaNote, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jDCInterestPaymentUntilDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtfAmountOfDays, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfInterest, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfFine, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfTotalHaveToPay, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfMoney, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfNewDebt, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jbtnAddInterestPaymentHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnEditInterestPaymentHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jtfLastDebt)
                    .addComponent(jtfTimes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnReloadAll, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfTimes, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfLastDebt, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDCInterestPaymentUntilDate, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfAmountOfDays, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfInterest, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfFine, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfTotalHaveToPay, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfMoney, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfNewDebt, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtaNote, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtnAddInterestPaymentHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnEditInterestPaymentHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jbtnReloadAll, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jPanel4.setBackground(new java.awt.Color(102, 102, 102));

        jtblPawnCoupon.setBackground(new java.awt.Color(255, 255, 255));
        jtblPawnCoupon.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jtblPawnCoupon.setForeground(new java.awt.Color(0, 0, 0));
        jtblPawnCoupon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã hợp đồng", "Mã khách hàng", "Mã hàng cầm", "Số lượng", "Giá cầm", "Lãi suất (%/ngày)", "Lãi (/ngày)", "Ngày cầm", "Ngày chuộc", "Người lập", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
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
        jScrollPane2.setViewportView(jtblPawnCoupon);
        if (jtblPawnCoupon.getColumnModel().getColumnCount() > 0) {
            jtblPawnCoupon.getColumnModel().getColumn(0).setMinWidth(50);
            jtblPawnCoupon.getColumnModel().getColumn(0).setPreferredWidth(50);
            jtblPawnCoupon.getColumnModel().getColumn(0).setMaxWidth(50);
            jtblPawnCoupon.getColumnModel().getColumn(1).setMinWidth(125);
            jtblPawnCoupon.getColumnModel().getColumn(1).setPreferredWidth(125);
            jtblPawnCoupon.getColumnModel().getColumn(1).setMaxWidth(125);
            jtblPawnCoupon.getColumnModel().getColumn(2).setMinWidth(125);
            jtblPawnCoupon.getColumnModel().getColumn(2).setPreferredWidth(125);
            jtblPawnCoupon.getColumnModel().getColumn(2).setMaxWidth(125);
            jtblPawnCoupon.getColumnModel().getColumn(3).setMinWidth(125);
            jtblPawnCoupon.getColumnModel().getColumn(3).setPreferredWidth(125);
            jtblPawnCoupon.getColumnModel().getColumn(3).setMaxWidth(125);
            jtblPawnCoupon.getColumnModel().getColumn(4).setMinWidth(75);
            jtblPawnCoupon.getColumnModel().getColumn(4).setPreferredWidth(75);
            jtblPawnCoupon.getColumnModel().getColumn(4).setMaxWidth(75);
            jtblPawnCoupon.getColumnModel().getColumn(5).setMinWidth(125);
            jtblPawnCoupon.getColumnModel().getColumn(5).setPreferredWidth(125);
            jtblPawnCoupon.getColumnModel().getColumn(5).setMaxWidth(125);
            jtblPawnCoupon.getColumnModel().getColumn(7).setMinWidth(125);
            jtblPawnCoupon.getColumnModel().getColumn(7).setPreferredWidth(125);
            jtblPawnCoupon.getColumnModel().getColumn(7).setMaxWidth(125);
            jtblPawnCoupon.getColumnModel().getColumn(8).setMinWidth(90);
            jtblPawnCoupon.getColumnModel().getColumn(8).setPreferredWidth(90);
            jtblPawnCoupon.getColumnModel().getColumn(8).setMaxWidth(90);
            jtblPawnCoupon.getColumnModel().getColumn(9).setMinWidth(90);
            jtblPawnCoupon.getColumnModel().getColumn(9).setPreferredWidth(90);
            jtblPawnCoupon.getColumnModel().getColumn(9).setMaxWidth(90);
            jtblPawnCoupon.getColumnModel().getColumn(10).setMinWidth(100);
            jtblPawnCoupon.getColumnModel().getColumn(10).setPreferredWidth(100);
            jtblPawnCoupon.getColumnModel().getColumn(10).setMaxWidth(100);
        }

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));
        jPanel6.setForeground(new java.awt.Color(0, 0, 0));

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));
        jPanel7.setForeground(new java.awt.Color(204, 204, 204));

        jLabel18.setBackground(new java.awt.Color(204, 204, 204));
        jLabel18.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Tổng     :");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel9.setBackground(new java.awt.Color(204, 204, 204));

        jlbTotalPrice.setBackground(new java.awt.Color(255, 255, 255));
        jlbTotalPrice.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jlbTotalPrice.setForeground(new java.awt.Color(0, 0, 0));
        jlbTotalPrice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbTotalPrice.setText("0");
        jlbTotalPrice.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbTotalPrice, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbTotalPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel10.setBackground(new java.awt.Color(204, 204, 204));

        jlbTotalInterestRate.setBackground(new java.awt.Color(204, 204, 204));
        jlbTotalInterestRate.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jlbTotalInterestRate.setForeground(new java.awt.Color(0, 0, 0));
        jlbTotalInterestRate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbTotalInterestRate.setText("0");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbTotalInterestRate, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jlbTotalInterestRate)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel12.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jtblPawnCouponMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblPawnCouponMouseClicked
        int row = jtblPawnCoupon.getSelectedRow();
        if (evt.getClickCount() == 2 && row != -1) {
            jtfPawnCouponID.setEditable(false);
            JTable table = (JTable) evt.getSource();
            jtfPawnCouponID.setText((table.getModel().getValueAt(row, 1)).toString());
            jcbCustomerID.setSelectedItem((table.getModel().getValueAt(row, 2)).toString());
            jcbProductID.setSelectedItem((table.getModel().getValueAt(row, 3)).toString());
            jtfAmount.setText((table.getModel().getValueAt(row, 4)).toString());
            jtfPrice.setText((table.getModel().getValueAt(row, 5)).toString());
            jtfInterestRate.setText((table.getModel().getValueAt(row, 6)).toString());
            jDCPawnDate.setDate(Support.stringToDate((table.getModel().getValueAt(row, 8)).toString()));
            jDCRedeemingDate.setDate(Support.stringToDate((table.getModel().getValueAt(row, 9)).toString()));
            setStatus((table.getModel().getValueAt(row, 11)).toString());
            PawnCoupon pawnCoupon = getPawnCouponFromForm();
            if (isStoredProductStatus(pawnCoupon.getStatus())) {
                jDCTheNextInterestPaymentDate.setEnabled(true);
                jDCTheNextInterestPaymentDate.setDate(_pawncouponController.getTheNextInterestPaymentDate(pawnCoupon));
            } else {
                jDCTheNextInterestPaymentDate.setDate(null);
                jDCTheNextInterestPaymentDate.setEnabled(false);
            }
            setInterestPaymentDefault(pawnCoupon);
            boolean isEnable = isStoredProductStatus(getStatus());
            jbtnAddInterestPaymentHistory.setEnabled(isEnable);
            jbtnEditInterestPaymentHistory.setEnabled(isEnable);
        }
    }//GEN-LAST:event_jtblPawnCouponMouseClicked

    private void jbtnReloadAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnReloadAllActionPerformed
        setPawnCouponDefault(true);
        loadDataPawnCoupon(_pawncouponController.getPawnCouponList());
        setInterestPaymentDefault(null);
        jbtnFindPawnCoupon.setText("Tìm");
    }//GEN-LAST:event_jbtnReloadAllActionPerformed

    private void jtbInterestPaymentHistoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbInterestPaymentHistoryMouseClicked
        int row = jtbInterestPaymentHistory.getSelectedRow();
        if (evt.getClickCount() == 2 && row != -1) {
            JTable table = (JTable) evt.getSource();
            jtfTimes.setText((table.getModel().getValueAt(row, 0)).toString());
            jDCInterestPaymentUntilDate.setDate(Support.stringToDate((table.getModel().getValueAt(row, 2)).toString()));
            long amountOfDays = 0;
            if (row == 0) {
                amountOfDays = Support.subtractDate(jDCPawnDate.getDate(), jDCInterestPaymentUntilDate.getDate()) + 1;
                jtfLastDebt.setText("0");
            } else {
                Date lastPaymentDate = Support.stringToDate((table.getModel().getValueAt(row - 1, 2)).toString());
                amountOfDays = Support.subtractDate(lastPaymentDate, jDCInterestPaymentUntilDate.getDate());
                jtfLastDebt.setText((table.getModel().getValueAt(row - 1, 4)).toString());
            }
            jtfAmountOfDays.setText(String.valueOf(amountOfDays));
            jtfMoney.setText((table.getModel().getValueAt(row, 3)).toString());
            jtanote.setText(table.getModel().getValueAt(row, 5) == null ? "" : table.getModel().getValueAt(row, 5).toString());
            if (!isStoredProductStatus(getStatus())) {
                jbtnAddInterestPaymentHistory.setEnabled(false);
                jbtnEditInterestPaymentHistory.setEnabled(false);
            } else {
                jbtnAddInterestPaymentHistory.setEnabled(false);
                jbtnEditInterestPaymentHistory.setEnabled(true);
            }
        }
    }//GEN-LAST:event_jtbInterestPaymentHistoryMouseClicked

    private void jbtnAddInterestPaymentHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAddInterestPaymentHistoryActionPerformed
        InterestPayment interestPayment = getInterestPaymentFromForm();
        PawnCoupon pawnCoupon = interestPayment.getPawnCoupon();
        if (interestPayment == null) {
            MessageSupport.ShowError(null, "Lỗi", "Lỗi chưa nhập hoặc nhập sai dữ liệu.");
        } else if (_pawncouponController.getInterestPaymentController().add(interestPayment)) {
            MessageSupport.Show(null, "Thông báo", "Thêm thành công.");
            setInterestPaymentDefault(pawnCoupon);
            jDCTheNextInterestPaymentDate.setDate(_pawncouponController.getTheNextInterestPaymentDate(pawnCoupon));
        }
    }//GEN-LAST:event_jbtnAddInterestPaymentHistoryActionPerformed

    private void jbtnEditInterestPaymentHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEditInterestPaymentHistoryActionPerformed
        InterestPayment interestPayment = getInterestPaymentFromForm();
        PawnCoupon pawnCoupon = interestPayment.getPawnCoupon();
        if (interestPayment == null) {
            MessageSupport.ShowError(null, "Lỗi", "Lỗi chưa nhập hoặc nhập sai dữ liệu.");
        }
        if (_pawncouponController.getInterestPaymentController().edit(interestPayment)) {
            MessageSupport.Show(null, "Thông báo", "Sửa thành công.");
            setInterestPaymentDefault(pawnCoupon);
        }
    }//GEN-LAST:event_jbtnEditInterestPaymentHistoryActionPerformed

    private void jbtnEditPawnCouponActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEditPawnCouponActionPerformed
        PawnCoupon pawnCoupon = getPawnCouponFromForm();
        if (_pawncouponController.findPawnCouponByProperty("_id", pawnCoupon.getId()) == null) {
            MessageSupport.ShowError(null, "Lỗi", "Hợp đồng không tồn tại.");
        } else if (!isStoredProductStatus(_pawncouponController.findPawnCouponByProperty("_id", pawnCoupon.getId()).getStatus())) {
            MessageSupport.ShowError(null, "Lỗi", "Hợp đồng đã hết hiệu lực (Đã chuộc/Đã thanh lý). Không thể thao tác.");
        } else if (pawnCoupon.getCustomer() == null) {
            MessageSupport.ShowError(null, "Lỗi", "Chọn mã khách hàng.");
        } else if (pawnCoupon.getProduct() == null) {
            MessageSupport.ShowError(null, "Lỗi", "Chọn mã hàng hóa.");
        } else if (pawnCoupon == null) {
            MessageSupport.ShowError(null, "Lỗi", "Lỗi nhập dữ liệu. Kiểm tra và thử lại!");
        } else if ((!CheckSupport.equals(_pawncouponController.findPawnCouponByProperty("_id", pawnCoupon.getId()).getProduct().getProductID(), pawnCoupon.getProduct().getProductID())
                && isStoredProductStatus(pawnCoupon.getProduct().getStatus()))) {
            MessageSupport.ShowError(null, "Lỗi", "Mã hàng hóa đang tồn tại ở một hợp đồng khác. Kiểm tra và chọn lại mã hàng hóa.");
            setStatus(_pawncouponController.findPawnCouponByProperty("_id", pawnCoupon.getId()).getStatus());
        } else if (_pawncouponController.edit(pawnCoupon)) {
            MessageSupport.Show(null, "Thông báo", "Sửa thành công.");
            setPawnCouponDefault(true);
            loadDataPawnCoupon(_pawncouponController.getPawnCouponList());
            setInterestPaymentDefault(null);
        }
    }//GEN-LAST:event_jbtnEditPawnCouponActionPerformed

    private void jbtnAddPawnCouponActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAddPawnCouponActionPerformed
        PawnCoupon pawnCoupon = getPawnCouponFromForm();
        if (_pawncouponController.findPawnCouponByProperty("_id", pawnCoupon.getId()) != null) {
            MessageSupport.ShowError(null, "Lỗi", "Hợp đồng tồn tại.");
            if (!isStoredProductStatus(_pawncouponController.findPawnCouponByProperty("_id", pawnCoupon.getId()).getStatus())) {
                MessageSupport.ShowError(null, "Lỗi", "Hợp đồng đã hết hiệu lực (Đã chuộc/Đã thanh lý). Không thể thao tác.");
            }
        } else if (pawnCoupon.getCustomer() == null) {
            MessageSupport.ShowError(null, "Lỗi", "Chọn mã khách hàng.");
        } else if (pawnCoupon.getProduct() == null) {
            MessageSupport.ShowError(null, "Lỗi", "Chọn mã hàng hóa.");
        } else if (isStoredProductStatus(pawnCoupon.getProduct().getStatus())) {
            MessageSupport.ShowError(null, "Lỗi", "Mã hàng hóa đang tồn tại ở một hợp đồng khác. Kiểm tra và chọn lại mã hàng hóa.");
            setStatus("");
        } else if (pawnCoupon == null) {
            MessageSupport.ShowError(null, "Lỗi", "Lỗi nhập dữ liệu. Kiểm tra và thử lại!");
        } else if (_pawncouponController.add(pawnCoupon)) {
            MessageSupport.Show(null, "Thông báo", "Thêm thành công.");
            setPawnCouponDefault(true);
            loadDataPawnCoupon(_pawncouponController.getPawnCouponList());
            setInterestPaymentDefault(null);
        }
    }//GEN-LAST:event_jbtnAddPawnCouponActionPerformed

    private void jbtnFindPawnCouponActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnFindPawnCouponActionPerformed
        if (jbtnFindPawnCoupon.getText() == "Tìm") {
            jbtnFindPawnCoupon.setText("Hủy");
            ArrayList<PawnCoupon> pawnCoupons = new ArrayList<>();
            if (jDCTheNextInterestPaymentDate.getDate() == null) {
                pawnCoupons = _pawncouponController.findPawnCoupon(getPawnCouponFromForm());
            } else {
                for (PawnCoupon pawnCoupon : _pawncouponController.findPawnCoupon(getPawnCouponFromForm())) {
                    if (isStoredProductStatus(pawnCoupon.getStatus()) && jDCTheNextInterestPaymentDate.getDate().compareTo(_pawncouponController.getTheNextInterestPaymentDate(pawnCoupon)) == 0) {
                        pawnCoupons.add(pawnCoupon);
                    }
                }
            }
            loadDataPawnCoupon(pawnCoupons);
        } else {
            jbtnFindPawnCoupon.setText("Tìm");
            loadDataPawnCoupon(_pawncouponController.getPawnCouponList());
            setPawnCouponDefault(true);
        }
    }//GEN-LAST:event_jbtnFindPawnCouponActionPerformed

    private void StatusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StatusMouseClicked
        if (!CheckSupport.isEmpty(jtfPawnCouponID.getText())) {
            if ((jDCRedeemingDate.getDate() == null) && (jrbRedeemed.isSelected() || jrbLiquidated.isSelected())) {
                jDCRedeemingDate.setDate(Support.getToday());
            } else {
                jDCRedeemingDate.setDate(null);
            }
        }
    }//GEN-LAST:event_StatusMouseClicked

    private void jbtnCreateNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCreateNewActionPerformed
        setPawnCouponDefault(false);
        setInterestPaymentDefault(null);
    }//GEN-LAST:event_jbtnCreateNewActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private com.toedter.calendar.JDateChooser jDCInterestPaymentUntilDate;
    private com.toedter.calendar.JDateChooser jDCPawnDate;
    private com.toedter.calendar.JDateChooser jDCRedeemingDate;
    private com.toedter.calendar.JDateChooser jDCTheNextInterestPaymentDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
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
    private javax.swing.JButton jbtnAddInterestPaymentHistory;
    private javax.swing.JButton jbtnAddPawnCoupon;
    private javax.swing.JButton jbtnCreateNew;
    private javax.swing.JButton jbtnEditInterestPaymentHistory;
    private javax.swing.JButton jbtnEditPawnCoupon;
    private javax.swing.JButton jbtnFindPawnCoupon;
    private javax.swing.JButton jbtnReloadAll;
    private javax.swing.JComboBox<String> jcbCustomerID;
    private javax.swing.JComboBox<String> jcbProductID;
    private javax.swing.JLabel jlbTotalInterestPayment;
    private javax.swing.JLabel jlbTotalInterestRate;
    private javax.swing.JLabel jlbTotalPrice;
    private javax.swing.JRadioButton jrbAll;
    private javax.swing.JRadioButton jrbLate;
    private javax.swing.JRadioButton jrbLiquidated;
    private javax.swing.JRadioButton jrbNeedToLiquidate;
    private javax.swing.JRadioButton jrbNotRedeemed;
    private javax.swing.JRadioButton jrbRedeemed;
    private javax.swing.JLabel jtaNote;
    private javax.swing.JTextArea jtanote;
    private javax.swing.JTable jtbInterestPaymentHistory;
    private javax.swing.JTable jtblPawnCoupon;
    private javax.swing.JTextField jtfAmount;
    private javax.swing.JTextField jtfAmountOfDays;
    private javax.swing.JTextField jtfFine;
    private javax.swing.JTextField jtfInterest;
    private javax.swing.JTextField jtfInterestRate;
    private javax.swing.JTextField jtfLastDebt;
    private javax.swing.JTextField jtfMoney;
    private javax.swing.JTextField jtfNewDebt;
    private javax.swing.JTextField jtfPawnCouponID;
    private javax.swing.JTextField jtfPrice;
    private javax.swing.JTextField jtfTimes;
    private javax.swing.JTextField jtfTotalHaveToPay;
    // End of variables declaration//GEN-END:variables
}
