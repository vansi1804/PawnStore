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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class JPawnCouponPanelForm extends javax.swing.JPanel {

    PawnCouponController _pawncouponController = new PawnCouponController();

    public JPawnCouponPanelForm() {
        initComponents();
        setPawnCouponDefault();
        loadDataPawnCoupon(_pawncouponController.getPawnCouponList());
        setInterestPaymentDefault(null);
    }

    public void setPawnCouponDefault() {
        jtfPawnCouponID.setEditable(true);
        jtfPawnCouponID.setText("");
        loadCustomerComboBox();
        loadProductComboBox();
        jcbCustomerID.setSelectedItem("Tất cả");
        jcbProductID.setSelectedItem("Tất cả");
        jtfAmount.setText("");
        jtfPrice.setText("");
        jtfInterestRate.setText("0.3");
        jDCTheNextInterestPaymentDate.setDate(null);
        jDCTheNextInterestPaymentDate.setEnabled(true);
        jDCPawnDate.setDate(Support.stringToDate(Support.dateToString(new Date())));
        jDCRedeemingDate.setDate(null);
        setStatus("");
    }

    public void setInterestPaymentDefault(PawnCoupon pawnCoupon) {
        if (pawnCoupon == null) {
            jtfTimes.setText("");
            jDCInterestPaymentDate.setDate(null);
            jtfAmountOfDays.setText("");
            jtfInterest.setText("");
            jtfFine.setText("");
            jtfAmountOfMoney.setText("");
            jtfDebt.setText("");
            jtanote.setText("");
            jtfTimes.setEditable(false);
            jDCInterestPaymentDate.setEnabled(false);
            jtfAmountOfDays.setEditable(false);
            jtfInterest.setEditable(false);
            jtfFine.setEditable(false);
            jtfAmountOfMoney.setEditable(false);
            jtfDebt.setEditable(false);
            jtanote.setEditable(false);
            jbtnAddInterestPaymentHistory.setEnabled(false);
            jbtnEditInterestPaymentHistory.setEnabled(false);
        } else {
            ArrayList<InterestPayment> interestPayments = _pawncouponController.getInterestPaymentController().getList(pawnCoupon);
            if (!isStoredProductStatus(pawnCoupon.getStatus())) {
                setInterestPaymentDefault(null);
            } else {
                jtfTimes.setText(String.valueOf(interestPayments.size() + 1));

                jDCInterestPaymentDate.setDate(Support.getToday());

                long amountOfDays = 0;
                if (interestPayments.size() == 0) {
                    amountOfDays = Support.subtractDate(pawnCoupon.getPawnDate(), jDCInterestPaymentDate.getDate()) + 1;
                } else {
                    InterestPayment lastTimes = interestPayments.get(interestPayments.size() - 1);
                    amountOfDays = Support.subtractDate(lastTimes.getPaymentDate(), jDCInterestPaymentDate.getDate());
                }
                jtfAmountOfDays.setText(String.valueOf(amountOfDays));

                float interest = pawnCoupon.getPrice() * pawnCoupon.getInterestRate() / 100 * amountOfDays;
                jtfInterest.setText(new BigDecimal(String.valueOf(interest)).stripTrailingZeros().toPlainString());

                if (jDCInterestPaymentDate.getDate().compareTo(jDCTheNextInterestPaymentDate.getDate()) > 0) {
                    long amountOfLateDays = Support.subtractDate(jDCInterestPaymentDate.getDate(), jDCTheNextInterestPaymentDate.getDate());
                    if (amountOfLateDays > 10) {
                        jtfFine.setText(new BigDecimal(String.valueOf(amountOfLateDays * interest * 1.5)).stripTrailingZeros().toPlainString());
                    } else {
                        jtfFine.setText("0");
                    }
                    String note = "Trễ " + amountOfLateDays + " ngày. ";
                    jtanote.setText(note);
                } else {
                    jtfFine.setText("0");
                }

                jtfAmountOfMoney.setText("");
                // event jtfAmountOfMoney change value:    jtfDebt = jtfInterest - jtfAmountOfMoney
                jtfAmountOfMoney.getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        jtfAmountOfMoneyChange();
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        jtfAmountOfMoneyChange();
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        jtfAmountOfMoneyChange();
                    }

                    public void jtfAmountOfMoneyChange() {
                        if (!CheckSupport.isEmpty(jtfInterest.getText()) && !CheckSupport.isEmpty(jtfAmountOfMoney.getText())) {
                            try {
                                jtfDebt.setText(new BigDecimal(Float.parseFloat(jtfInterest.getText()) - Float.parseFloat(jtfAmountOfMoney.getText())).stripTrailingZeros().toPlainString());
                            } catch (Exception e) {
                            }
                        } else {
                            jtfDebt.setText("");
                        }
                    }
                });
                jtfDebt.setText("");

                jDCInterestPaymentDate.setEnabled(true);
                jtfAmountOfDays.setEditable(true);
                jtfInterest.setEditable(true);
                jtfFine.setEditable(true);
                jtfAmountOfMoney.setEditable(true);
                jtfDebt.setEditable(true);
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
        if (CheckSupport.equals(Status, "Chưa chuộc") || CheckSupport.equals(Status, "Cần thanh lý") || CheckSupport.equals(Status, "Trễ")) {
            return true;
        }
        return false;
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
        float totalprice = .0f;
        float totalInterestRate = 0.0f;
        Object rowData[] = new Object[12];
        int STT = 1;
        for (int i = 0; i < _pawncouponList.size(); i++) {
            rowData[0] = String.valueOf(STT++);
            rowData[1] = _pawncouponList.get(i).getId();
            rowData[2] = _pawncouponList.get(i).getCustomer().getId();
            rowData[3] = _pawncouponList.get(i).getProduct().getProductID();
            rowData[4] = _pawncouponList.get(i).getAmount();
            rowData[5] = new BigDecimal(String.valueOf(_pawncouponList.get(i).getPrice())).stripTrailingZeros().toPlainString();
            totalprice += _pawncouponList.get(i).getPrice();
            rowData[6] = _pawncouponList.get(i).getInterestRate();
            totalInterestRate += _pawncouponList.get(i).getPrice() * _pawncouponList.get(i).getInterestRate() / 100;
            rowData[7] = _pawncouponList.get(i).getPrice() * _pawncouponList.get(i).getInterestRate() / 100;
            rowData[8] = Support.dateToString(_pawncouponList.get(i).getPawnDate());
            rowData[9] = Support.dateToString(_pawncouponList.get(i).getRedeemingDate());
            rowData[10] = _pawncouponList.get(i).getUser().getUsername();
            rowData[11] = _pawncouponList.get(i).getStatus();
            model.addRow(rowData);
        }
        jlbTotalPrice.setText(new BigDecimal(String.valueOf(totalprice)).stripTrailingZeros().toPlainString());
        jlbTotalInterestRate.setText(new BigDecimal(String.valueOf(totalInterestRate)).stripTrailingZeros().toPlainString());
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
            MessageSupport.ShowError(null, "Lỗi", "Lỗi nhập dữ liệu. Kiểm tra lại.");
        }
        return null;
    }

    public void loadInterestPaymentHistory(PawnCoupon pawnCoupon) {
        DefaultTableModel model = (DefaultTableModel) jtbInterestPaymentHistory.getModel();
        model.setRowCount(0);
        float totalInterestPayment = .0f;
        float totalDebt = 0.0f;
        Object rowData[] = new Object[5];
        ArrayList<InterestPayment> interestPayments = _pawncouponController.getInterestPaymentController().getList(pawnCoupon);
        for (int i = 0; i < interestPayments.size(); i++) {
            rowData[0] = interestPayments.get(i).getTimes();
            rowData[1] = interestPayments.get(i).getPaymentDate();
            rowData[2] = interestPayments.get(i).getMoney();
            totalInterestPayment += interestPayments.get(i).getMoney();
            rowData[3] = interestPayments.get(i).getDebt();
            totalDebt += interestPayments.get(i).getDebt();
            rowData[4] = interestPayments.get(i).getNote();
            model.addRow(rowData);
        }
        jlbTotalInterestPayment.setText(new BigDecimal(String.valueOf(totalInterestPayment)).stripTrailingZeros().toPlainString());
        jlbTotalDebt.setText(new BigDecimal(String.valueOf(totalDebt)).stripTrailingZeros().toPlainString());
        Support.setDataTableCenter(jtbInterestPaymentHistory);
    }

    public InterestPayment getInterestPaymentFromForm() {
        try {
            PawnCoupon pawnCoupon = _pawncouponController.findPawnCouponByProperty("_id", jtfPawnCouponID.getText());
            int times = Integer.parseInt(jtfTimes.getText());
            Date interestPaymentDate = jDCInterestPaymentDate.getDate();
            float amountOfMoney = Float.parseFloat(jtfAmountOfMoney.getText());
            float debt = Float.parseFloat(jtfDebt.getText());
            String note = jtanote.getText();
            return new InterestPayment(pawnCoupon, times, interestPaymentDate, amountOfMoney, debt, note);
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
        jLabel11 = new javax.swing.JLabel();
        jDCPawnDate = new com.toedter.calendar.JDateChooser();
        jDCRedeemingDate = new com.toedter.calendar.JDateChooser();
        jLabel24 = new javax.swing.JLabel();
        jDCTheNextInterestPaymentDate = new com.toedter.calendar.JDateChooser();
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
        jlbTotalDebt = new javax.swing.JLabel();
        jbtnReloadAll = new javax.swing.JButton();
        jDCInterestPaymentDate = new com.toedter.calendar.JDateChooser();
        jLabel20 = new javax.swing.JLabel();
        jtfInterest = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jtfAmountOfMoney = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jtfDebt = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jtfTimes = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jtfFine = new javax.swing.JTextField();
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

        jLabel25.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
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

        jLabel1.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel1.setText("Hợp đồng");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel2.setText("Mã hợp đồng");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel3.setText("Mã khách hàng");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel4.setText("Mã hàng cầm");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel7.setText("Số lượng");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel8.setText("Giá");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel9.setText("Lãi suất (%/ngày)");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel10.setText("Ngày cầm");

        jcbCustomerID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jcbProductID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jbtnCreateNew.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jbtnCreateNew.setText("Tạo mới");
        jbtnCreateNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCreateNewActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel12.setText("Trạng thái");

        buttonGroup1.add(jrbNotRedeemed);
        jrbNotRedeemed.setText("Chưa chuộc");
        jrbNotRedeemed.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                StatusMouseClicked(evt);
            }
        });

        buttonGroup1.add(jrbRedeemed);
        jrbRedeemed.setText("Đã chuộc");
        jrbRedeemed.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                StatusMouseClicked(evt);
            }
        });

        buttonGroup1.add(jrbLate);
        jrbLate.setText("Trễ hạn");
        jrbLate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                StatusMouseClicked(evt);
            }
        });

        buttonGroup1.add(jrbNeedToLiquidate);
        jrbNeedToLiquidate.setText("Cần thanh lý");
        jrbNeedToLiquidate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                StatusMouseClicked(evt);
            }
        });

        buttonGroup1.add(jrbLiquidated);
        jrbLiquidated.setText("Đã thanh lý");
        jrbLiquidated.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                StatusMouseClicked(evt);
            }
        });

        buttonGroup1.add(jrbAll);
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
                .addGap(26, 26, 26)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jrbAll)
                            .addComponent(jrbRedeemed)
                            .addComponent(jrbNeedToLiquidate)
                            .addComponent(jrbLate)
                            .addComponent(jrbLiquidated))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jrbNotRedeemed, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
        jbtnFindPawnCoupon.setText("Tìm");
        jbtnFindPawnCoupon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnFindPawnCouponActionPerformed(evt);
            }
        });

        jbtnAddPawnCoupon.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jbtnAddPawnCoupon.setText("Thêm");
        jbtnAddPawnCoupon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAddPawnCouponActionPerformed(evt);
            }
        });

        jbtnEditPawnCoupon.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jbtnEditPawnCoupon.setText("Sửa");
        jbtnEditPawnCoupon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEditPawnCouponActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel11.setText("Ngày chuộc/thanh lý");

        jDCPawnDate.setDateFormatString("yyyy-MM-dd");

        jDCRedeemingDate.setDateFormatString("yyyy-MM-dd");

        jLabel24.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel24.setText("Ngày đóng lãi tiếp theo");

        jDCTheNextInterestPaymentDate.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jtfPrice, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfAmount, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jcbProductID, javax.swing.GroupLayout.Alignment.LEADING, 0, 217, Short.MAX_VALUE)
                                    .addComponent(jcbCustomerID, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jtfPawnCouponID, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfInterestRate, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jDCPawnDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jDCTheNextInterestPaymentDate, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbtnCreateNew)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(85, 85, 85))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jbtnAddPawnCoupon)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtnEditPawnCoupon, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtnFindPawnCoupon, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jDCRedeemingDate, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jLabel1)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(jtfPawnCouponID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jbtnCreateNew, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(jcbCustomerID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(jcbProductID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(jtfAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(6, 6, 6)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(jtfPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(jtfInterestRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(7, 7, 7)
                                .addComponent(jLabel10))
                            .addComponent(jDCPawnDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel24)
                            .addComponent(jDCTheNextInterestPaymentDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jDCRedeemingDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnAddPawnCoupon)
                    .addComponent(jbtnEditPawnCoupon)
                    .addComponent(jbtnFindPawnCoupon))
                .addGap(24, 24, 24))
        );

        jLabel14.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel14.setText("Lịch sử đóng lãi");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel15.setText("Số ngày");

        jtaNote.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jtaNote.setText("Ghi chú");

        jtanote.setColumns(20);
        jtanote.setRows(5);
        jtanote.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane1.setViewportView(jtanote);

        jbtnEditInterestPaymentHistory.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jbtnEditInterestPaymentHistory.setText("Sửa");
        jbtnEditInterestPaymentHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEditInterestPaymentHistoryActionPerformed(evt);
            }
        });

        jbtnAddInterestPaymentHistory.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jbtnAddInterestPaymentHistory.setText("Thêm");
        jbtnAddInterestPaymentHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAddInterestPaymentHistoryActionPerformed(evt);
            }
        });

        jtbInterestPaymentHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kỳ đóng", "Ngày đóng", "Số tiền", "Nợ", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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
            jtbInterestPaymentHistory.getColumnModel().getColumn(0).setMinWidth(75);
            jtbInterestPaymentHistory.getColumnModel().getColumn(0).setPreferredWidth(75);
            jtbInterestPaymentHistory.getColumnModel().getColumn(0).setMaxWidth(75);
            jtbInterestPaymentHistory.getColumnModel().getColumn(1).setMinWidth(100);
            jtbInterestPaymentHistory.getColumnModel().getColumn(1).setPreferredWidth(100);
            jtbInterestPaymentHistory.getColumnModel().getColumn(1).setMaxWidth(100);
            jtbInterestPaymentHistory.getColumnModel().getColumn(2).setMinWidth(100);
            jtbInterestPaymentHistory.getColumnModel().getColumn(2).setPreferredWidth(100);
            jtbInterestPaymentHistory.getColumnModel().getColumn(2).setMaxWidth(100);
            jtbInterestPaymentHistory.getColumnModel().getColumn(3).setMinWidth(100);
            jtbInterestPaymentHistory.getColumnModel().getColumn(3).setPreferredWidth(100);
            jtbInterestPaymentHistory.getColumnModel().getColumn(3).setMaxWidth(100);
        }

        jLabel17.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel17.setText("Kỳ đóng");

        jLabel19.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel19.setText("Tổng      :");

        jlbTotalInterestPayment.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jlbTotalInterestPayment.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbTotalInterestPayment.setText("0");

        jlbTotalDebt.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jlbTotalDebt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbTotalDebt.setText("0");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbTotalInterestPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbTotalDebt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(190, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlbTotalInterestPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jlbTotalDebt, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jbtnReloadAll.setText("Tải lại");
        jbtnReloadAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnReloadAllActionPerformed(evt);
            }
        });

        jDCInterestPaymentDate.setDateFormatString("yyyy-MM-dd");

        jLabel20.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel20.setText("Lãi");

        jLabel21.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel21.setText("Đóng");

        jLabel22.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel22.setText("Nợ");

        jLabel23.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel23.setText("Ngày đóng");

        jLabel26.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel26.setText("Tiền phạt (trễ > 10 ngày)");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel14)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtaNote, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jbtnAddInterestPaymentHistory)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jbtnEditInterestPaymentHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jDCInterestPaymentDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtfTimes)
                            .addComponent(jtfAmountOfDays)
                            .addComponent(jtfInterest)
                            .addComponent(jtfFine)
                            .addComponent(jtfAmountOfMoney)
                            .addComponent(jtfDebt)
                            .addComponent(jScrollPane1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbtnReloadAll, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(jtfTimes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel23)
                            .addComponent(jDCInterestPaymentDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfAmountOfDays, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfInterest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfFine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfAmountOfMoney, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfDebt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jtaNote)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbtnEditInterestPaymentHistory)
                            .addComponent(jbtnAddInterestPaymentHistory))
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(jbtnReloadAll, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

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
            jtblPawnCoupon.getColumnModel().getColumn(1).setMinWidth(150);
            jtblPawnCoupon.getColumnModel().getColumn(1).setPreferredWidth(150);
            jtblPawnCoupon.getColumnModel().getColumn(1).setMaxWidth(150);
            jtblPawnCoupon.getColumnModel().getColumn(2).setMinWidth(150);
            jtblPawnCoupon.getColumnModel().getColumn(2).setPreferredWidth(150);
            jtblPawnCoupon.getColumnModel().getColumn(2).setMaxWidth(150);
            jtblPawnCoupon.getColumnModel().getColumn(3).setMinWidth(150);
            jtblPawnCoupon.getColumnModel().getColumn(3).setPreferredWidth(150);
            jtblPawnCoupon.getColumnModel().getColumn(3).setMaxWidth(150);
            jtblPawnCoupon.getColumnModel().getColumn(4).setMinWidth(75);
            jtblPawnCoupon.getColumnModel().getColumn(4).setPreferredWidth(75);
            jtblPawnCoupon.getColumnModel().getColumn(4).setMaxWidth(75);
            jtblPawnCoupon.getColumnModel().getColumn(5).setMinWidth(150);
            jtblPawnCoupon.getColumnModel().getColumn(5).setMaxWidth(150);
            jtblPawnCoupon.getColumnModel().getColumn(6).setMinWidth(100);
            jtblPawnCoupon.getColumnModel().getColumn(6).setMaxWidth(100);
            jtblPawnCoupon.getColumnModel().getColumn(7).setMinWidth(100);
            jtblPawnCoupon.getColumnModel().getColumn(7).setPreferredWidth(100);
            jtblPawnCoupon.getColumnModel().getColumn(7).setMaxWidth(100);
            jtblPawnCoupon.getColumnModel().getColumn(10).setMinWidth(100);
            jtblPawnCoupon.getColumnModel().getColumn(10).setPreferredWidth(100);
            jtblPawnCoupon.getColumnModel().getColumn(10).setMaxWidth(100);
        }

        jLabel18.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel18.setText("Tổng     :");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jlbTotalPrice.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jlbTotalPrice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbTotalPrice.setText("0");
        jlbTotalPrice.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbTotalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbTotalPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jlbTotalInterestRate.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jlbTotalInterestRate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbTotalInterestRate.setText("0");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbTotalInterestRate, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbTotalInterestRate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

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
            .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            if (!isStoredProductStatus(getStatus())) {
                jbtnAddInterestPaymentHistory.setEnabled(false);
                jbtnEditInterestPaymentHistory.setEnabled(false);
            } else {
                jbtnAddInterestPaymentHistory.setEnabled(true);
                jbtnEditInterestPaymentHistory.setEnabled(false);
            }
        }
    }//GEN-LAST:event_jtblPawnCouponMouseClicked

    private void jbtnCreateNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCreateNewActionPerformed
        setPawnCouponDefault();
        setInterestPaymentDefault(null);
    }//GEN-LAST:event_jbtnCreateNewActionPerformed

    private void jbtnAddPawnCouponActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAddPawnCouponActionPerformed
        PawnCoupon pawnCoupon = getPawnCouponFromForm();
        if (_pawncouponController.findPawnCouponByProperty("_id", pawnCoupon.getId()) != null) {
            MessageSupport.ShowError(null, "Lỗi", "Hợp đồng tồn tại.");
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
            setPawnCouponDefault();
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
            setPawnCouponDefault();
        }
    }//GEN-LAST:event_jbtnFindPawnCouponActionPerformed

    private void jbtnEditPawnCouponActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEditPawnCouponActionPerformed
        PawnCoupon pawnCoupon = getPawnCouponFromForm();
        if (_pawncouponController.findPawnCouponByProperty("_id", pawnCoupon.getId()) == null) {
            MessageSupport.ShowError(null, "Lỗi", "Hợp đồng không tồn tại.");
        } else if (pawnCoupon.getCustomer() == null) {
            MessageSupport.ShowError(null, "Lỗi", "Chọn mã khách hàng.");
        } else if (pawnCoupon.getProduct() == null) {
            MessageSupport.ShowError(null, "Lỗi", "Chọn mã hàng hóa.");
        } else if (!isStoredProductStatus(_pawncouponController.findPawnCouponByProperty("_id", pawnCoupon.getId()).getStatus())) {
            MessageSupport.ShowError(null, "Lỗi", "Hợp đồng đã hết hiệu lực (Đã chuộc/Đã thanh lý). Không thể thao tác.");
        } else if (pawnCoupon == null) {
            MessageSupport.ShowError(null, "Lỗi", "Lỗi nhập dữ liệu. Kiểm tra và thử lại!");
        } else if ((!CheckSupport.equals(_pawncouponController.findPawnCouponByProperty("_id", pawnCoupon.getId()).getProduct().getProductID(), pawnCoupon.getProduct().getProductID())
                && isStoredProductStatus(pawnCoupon.getProduct().getStatus()))) {
            MessageSupport.ShowError(null, "Lỗi", "Mã hàng hóa đang tồn tại ở một hợp đồng khác. Kiểm tra và chọn lại mã hàng hóa.");
            setStatus(_pawncouponController.findPawnCouponByProperty("_id", pawnCoupon.getId()).getStatus());
        } else if (_pawncouponController.edit(pawnCoupon)) {
            MessageSupport.Show(null, "Thông báo", "Sửa thành công.");
            setPawnCouponDefault();
            loadDataPawnCoupon(_pawncouponController.getPawnCouponList());
            setInterestPaymentDefault(null);
        }
    }//GEN-LAST:event_jbtnEditPawnCouponActionPerformed

    private void jbtnReloadAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnReloadAllActionPerformed
        setPawnCouponDefault();
        loadDataPawnCoupon(_pawncouponController.getPawnCouponList());
        setInterestPaymentDefault(null);
        jbtnFindPawnCoupon.setText("Tìm");
    }//GEN-LAST:event_jbtnReloadAllActionPerformed

    private void jbtnAddInterestPaymentHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAddInterestPaymentHistoryActionPerformed
        InterestPayment interestPayment = getInterestPaymentFromForm();
        PawnCoupon pawnCoupon = interestPayment.getPawnCoupon();
        if (interestPayment == null) {
            MessageSupport.ShowError(null, "Lỗi", "Lỗi chưa nhập hoặc nhập sai dữ liệu.");
        } else if (_pawncouponController.getInterestPaymentController().add(interestPayment)) {
            MessageSupport.Show(null, "Thông báo", "Thêm thành công.");
            setInterestPaymentDefault(pawnCoupon);
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

    private void StatusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StatusMouseClicked
        if (!CheckSupport.isEmpty(jtfPawnCouponID.getText())) {
            if ((jDCRedeemingDate.getDate() == null) && (jrbRedeemed.isSelected() || jrbLiquidated.isSelected())) {
                jDCRedeemingDate.setDate(Support.getToday());
            } else {
                jDCRedeemingDate.setDate(null);
            }
        }
    }//GEN-LAST:event_StatusMouseClicked

    private void jtbInterestPaymentHistoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbInterestPaymentHistoryMouseClicked
        int row = jtbInterestPaymentHistory.getSelectedRow();
        if (evt.getClickCount() == 2 && row != -1) {
            JTable table = (JTable) evt.getSource();
            jtfTimes.setText((table.getModel().getValueAt(row, 0)).toString());
            jDCInterestPaymentDate.setDate(Support.stringToDate((table.getModel().getValueAt(row, 1)).toString()));
            long amountOfDays = 0;
            if (row == 0) {
                amountOfDays = Support.subtractDate(jDCPawnDate.getDate(), jDCInterestPaymentDate.getDate()) + 1;
            } else {
                Date lastPaymentDate = Support.stringToDate((table.getModel().getValueAt(row - 1, 1)).toString());
                amountOfDays = Support.subtractDate(lastPaymentDate, jDCInterestPaymentDate.getDate());
            }
            jtfAmountOfDays.setText(String.valueOf(amountOfDays));
            float interest = Float.parseFloat(jtfPrice.getText()) * Float.parseFloat(jtfInterestRate.getText()) / 100 * amountOfDays;
            jtfInterest.setText(new BigDecimal(String.valueOf(interest)).stripTrailingZeros().toPlainString());
            jtfAmountOfMoney.setText((table.getModel().getValueAt(row, 2)).toString());
            jtfDebt.setText((table.getModel().getValueAt(row, 3)).toString());
            jtanote.setText(table.getModel().getValueAt(row, 4) == null ? "" : table.getModel().getValueAt(row, 4).toString());
            if (!isStoredProductStatus(getStatus())) {
                jbtnAddInterestPaymentHistory.setEnabled(false);
                jbtnEditInterestPaymentHistory.setEnabled(false);
            } else {
                jbtnAddInterestPaymentHistory.setEnabled(false);
                jbtnEditInterestPaymentHistory.setEnabled(true);
            }
        }
    }//GEN-LAST:event_jtbInterestPaymentHistoryMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private com.toedter.calendar.JDateChooser jDCInterestPaymentDate;
    private com.toedter.calendar.JDateChooser jDCPawnDate;
    private com.toedter.calendar.JDateChooser jDCRedeemingDate;
    private com.toedter.calendar.JDateChooser jDCTheNextInterestPaymentDate;
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
    private javax.swing.JLabel jlbTotalDebt;
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
    private javax.swing.JTextField jtfAmountOfMoney;
    private javax.swing.JTextField jtfDebt;
    private javax.swing.JTextField jtfFine;
    private javax.swing.JTextField jtfInterest;
    private javax.swing.JTextField jtfInterestRate;
    private javax.swing.JTextField jtfPawnCouponID;
    private javax.swing.JTextField jtfPrice;
    private javax.swing.JTextField jtfTimes;
    // End of variables declaration//GEN-END:variables
}
