/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.JTabbedPaneForm;

import Model.Customer;
import Controller.PawnCouponController;
import Model.PawnCoupon;
import Model.Product;
import Model.User;
import Support.*;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Bau Kien
 */
public class JPawnCouponPanelForm extends javax.swing.JPanel {

    PawnCouponController _pawncouponController = new PawnCouponController();

    public JPawnCouponPanelForm() {
        initComponents();
        setPawnCouponDefault();
        loadDataPawnCoupon(_pawncouponController.getPawnCouponList());
        setInterestPaymentDefaiult();
    }

    public void setPawnCouponDefault() {
        jtfPawnCouponID.setEditable(true);
        jtfPawnCouponID.setText("");
        jcbCustomerID.setSelectedIndex(0);
        jcbProductID.setSelectedIndex(0);
        loadCustomerComboBox();
        loadProductComboBox();
        jtfAmount.setText("");
        jtfPrice.setText("");
        jtfInterestRate.setText("");
        jfmtfPawnDate.setText(Support.getStringToday());
        setStatus("");
    }

    public void setInterestPaymentDefaiult() {
        jfmtfInterestPaymentDate.setText(Support.getStringToday());
        jtfMoney.setText("");
        jtanote.setText("");
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

    public void loadDataPawnCoupon(ArrayList<PawnCoupon> _pawncouponList) {
        DefaultTableModel model = (DefaultTableModel) jtblPawnCoupom.getModel();
        model.setRowCount(0);
        float totalprice = 0f;
        float totalInterestRate = 0.0f;
        Object rowData[] = new Object[11];
        int STT = 1;
        for (int i = 0; i < _pawncouponList.size(); i++) {
            rowData[0] = String.valueOf(STT++);
            rowData[1] = _pawncouponList.get(i).getId();
            rowData[2] = _pawncouponList.get(i).getCustomer().getId();
            rowData[3] = _pawncouponList.get(i).getProuct().getProductID();
            rowData[4] = _pawncouponList.get(i).getAmount();
            rowData[5] = _pawncouponList.get(i).getPrice();
            totalprice += _pawncouponList.get(i).getPrice();
            rowData[6] = _pawncouponList.get(i).getInterestRate();
            totalInterestRate += _pawncouponList.get(i).getPrice() * _pawncouponList.get(i).getInterestRate() / 100;
            rowData[7] = _pawncouponList.get(i).getPrice() * _pawncouponList.get(i).getInterestRate() / 100;
            rowData[8] = Support.dateToString(_pawncouponList.get(i).getPawnDate());
            rowData[9] = Support.dateToString(_pawncouponList.get(i).getRedeemingDate());
            rowData[10] = _pawncouponList.get(i).getUser().getUsername();
            model.addRow(rowData);
        }
        jlbTotalPrice.setText(String.valueOf(totalprice));
        jlbTotalInterestRate.setText(String.valueOf(totalInterestRate));
    }

    public void setStatus(String status) {
        if (CheckSupport.equals(status, "")) {
            jrbNotRedeemed.setSelected(true);
            jrbRedeemed.setSelected(false);
            jrbNeedToLiquidate.setSelected(false);
            jrbLiquidated.setSelected(false);
        } else if (CheckSupport.equals(status, "Chưa chuộc")) {
            jrbNotRedeemed.setSelected(true);
            jrbRedeemed.setSelected(false);
            jrbNeedToLiquidate.setSelected(false);
            jrbLiquidated.setSelected(false);
        } else if (CheckSupport.equals(status, "Đã chuộc")) {
            jrbNotRedeemed.setSelected(false);
            jrbRedeemed.setSelected(true);
            jrbNeedToLiquidate.setSelected(false);
            jrbLiquidated.setSelected(false);
        } else if (CheckSupport.equals(status, "Cần thanh lý")) {
            jrbNotRedeemed.setSelected(false);
            jrbRedeemed.setSelected(false);
            jrbNeedToLiquidate.setSelected(true);
            jrbLiquidated.setSelected(false);
        } else if (CheckSupport.equals(status, "Đã thanh lý")) {
            jrbNotRedeemed.setSelected(false);
            jrbRedeemed.setSelected(false);
            jrbNeedToLiquidate.setSelected(false);
            jrbLiquidated.setSelected(true);
        }
    }

    public PawnCoupon getPawnCouponFromForm() {
        try {
            String id = jtfPawnCouponID.getText();
            Date pawnDate = Support.stringToDate(jfmtfPawnDate.getText());
            Customer customer = _pawncouponController.getCustomerController().findCustomerByID(jcbCustomerID.getSelectedItem().toString());
            Product product = _pawncouponController.getProductController().findProductByID(jcbProductID.getSelectedItem().toString());
            int amount = Integer.parseInt(jtfAmount.getText());
            float price = Float.parseFloat(jtfPrice.getText());
            float interestRate = Float.parseFloat(jtfInterestRate.getText());
            Date redeemingDate = Support.stringToDate(jfmtRedeemingDate.getText());
            User user = User.getCurrentInstance();

            return new PawnCoupon(id, pawnDate, customer, product, amount, price, interestRate, null, user);
        } catch (Exception e) {
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
        jfmtfPawnDate = new javax.swing.JFormattedTextField();
        jbtnCreateNew = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jrbNotRedeemed = new javax.swing.JCheckBox();
        jrbRedeemed = new javax.swing.JCheckBox();
        jchbOutOfDate = new javax.swing.JCheckBox();
        jrbNeedToLiquidate = new javax.swing.JCheckBox();
        jrbLiquidated = new javax.swing.JCheckBox();
        jbtnFindPawnCoupon = new javax.swing.JButton();
        jbtnAddPawnCoupon = new javax.swing.JButton();
        jbtnEditPawnCoupon = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jfmtRedeemingDate = new javax.swing.JFormattedTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jtfMoney = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jtaNote = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtanote = new javax.swing.JTextArea();
        jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtbInterestPaymentHistory = new javax.swing.JTable();
        jfmtfInterestPaymentDate = new javax.swing.JFormattedTextField();
        jLabel17 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jlbTotalMoney = new javax.swing.JLabel();
        jbtnReloadAll = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtblPawnCoupom = new javax.swing.JTable();
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

        jLabel25.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
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

        jrbNotRedeemed.setText("Chưa chuộc");

        jrbRedeemed.setText("Đã cuộc");

        jchbOutOfDate.setText("Trễ lãi");

        jrbNeedToLiquidate.setText("Cần thanh lý");

        jrbLiquidated.setText("Đã thanh lý");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jrbNotRedeemed)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(98, 98, 98))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jrbLiquidated)
                            .addComponent(jrbNeedToLiquidate)
                            .addComponent(jchbOutOfDate)
                            .addComponent(jrbRedeemed))
                        .addGap(0, 0, Short.MAX_VALUE))))
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
                .addComponent(jchbOutOfDate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrbNeedToLiquidate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrbLiquidated)
                .addContainerGap(23, Short.MAX_VALUE))
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
        jLabel11.setText("Ngày chuộc");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfAmount)
                    .addComponent(jtfPrice)
                    .addComponent(jtfInterestRate)
                    .addComponent(jcbCustomerID, 0, 192, Short.MAX_VALUE)
                    .addComponent(jtfPawnCouponID, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jcbProductID, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jfmtfPawnDate)
                    .addComponent(jfmtRedeemingDate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jbtnAddPawnCoupon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbtnEditPawnCoupon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbtnFindPawnCoupon, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jbtnCreateNew))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jtfPawnCouponID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jfmtfPawnDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jfmtRedeemingDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jbtnCreateNew)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jbtnAddPawnCoupon)
                                .addGap(18, 18, 18)
                                .addComponent(jbtnEditPawnCoupon)
                                .addGap(18, 18, 18)
                                .addComponent(jbtnFindPawnCoupon))
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 22, Short.MAX_VALUE))
        );

        jLabel14.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel14.setText("Lịch sử đóng lãi");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel15.setText("Số tiền");

        jtaNote.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jtaNote.setText("Ghi chú");

        jtanote.setColumns(20);
        jtanote.setRows(5);
        jScrollPane1.setViewportView(jtanote);

        jButton6.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jButton6.setText("Sửa");

        jButton5.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jButton5.setText("Thêm");

        jtbInterestPaymentHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kỳ đóng", "Ngày đóng", "Số tiền", "Ghi chú"
            }
        ));
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
        }

        jLabel17.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel17.setText("Ngày đóng");

        jLabel19.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel19.setText("Tổng      :");

        jlbTotalMoney.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jlbTotalMoney.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlbTotalMoney.setText("0");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbTotalMoney, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlbTotalMoney, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

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
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtaNote, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jButton5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jtfMoney)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jfmtfInterestPaymentDate)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3)
                            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtnReloadAll)
                        .addContainerGap())))
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
                            .addComponent(jfmtfInterestPaymentDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfMoney, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jtaNote)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton5)
                            .addComponent(jButton6))
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtnReloadAll, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jtblPawnCoupom.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã hợp đồng", "Mã khách hàng", "Mã hàng cầm", "Số lượng", "Giá cầm", "Lãi suất (%/ngày)", "Lãi", "Ngày cầm", "Ngày chuộc", "Người lập"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblPawnCoupom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblPawnCoupomMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jtblPawnCoupom);
        if (jtblPawnCoupom.getColumnModel().getColumnCount() > 0) {
            jtblPawnCoupom.getColumnModel().getColumn(0).setMinWidth(50);
            jtblPawnCoupom.getColumnModel().getColumn(0).setPreferredWidth(50);
            jtblPawnCoupom.getColumnModel().getColumn(0).setMaxWidth(50);
            jtblPawnCoupom.getColumnModel().getColumn(1).setMinWidth(150);
            jtblPawnCoupom.getColumnModel().getColumn(1).setPreferredWidth(150);
            jtblPawnCoupom.getColumnModel().getColumn(1).setMaxWidth(150);
            jtblPawnCoupom.getColumnModel().getColumn(2).setMinWidth(150);
            jtblPawnCoupom.getColumnModel().getColumn(2).setPreferredWidth(150);
            jtblPawnCoupom.getColumnModel().getColumn(2).setMaxWidth(150);
            jtblPawnCoupom.getColumnModel().getColumn(3).setMinWidth(150);
            jtblPawnCoupom.getColumnModel().getColumn(3).setPreferredWidth(150);
            jtblPawnCoupom.getColumnModel().getColumn(3).setMaxWidth(150);
            jtblPawnCoupom.getColumnModel().getColumn(4).setMinWidth(75);
            jtblPawnCoupom.getColumnModel().getColumn(4).setPreferredWidth(75);
            jtblPawnCoupom.getColumnModel().getColumn(4).setMaxWidth(75);
            jtblPawnCoupom.getColumnModel().getColumn(5).setMinWidth(150);
            jtblPawnCoupom.getColumnModel().getColumn(5).setMaxWidth(150);
            jtblPawnCoupom.getColumnModel().getColumn(6).setMinWidth(100);
            jtblPawnCoupom.getColumnModel().getColumn(6).setMaxWidth(100);
            jtblPawnCoupom.getColumnModel().getColumn(7).setMinWidth(150);
            jtblPawnCoupom.getColumnModel().getColumn(7).setMaxWidth(150);
            jtblPawnCoupom.getColumnModel().getColumn(10).setMinWidth(100);
            jtblPawnCoupom.getColumnModel().getColumn(10).setPreferredWidth(100);
            jtblPawnCoupom.getColumnModel().getColumn(10).setMaxWidth(100);
        }

        jLabel18.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel18.setText("Tạm tổng     :");

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
        jlbTotalPrice.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlbTotalPrice.setText("0");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbTotalPrice, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbTotalPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jlbTotalInterestRate.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jlbTotalInterestRate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlbTotalInterestRate.setText("0");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbTotalInterestRate, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbTotalInterestRate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addGap(18, 18, 18)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void jtblPawnCoupomMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblPawnCoupomMouseClicked
        jtfPawnCouponID.setEditable(false);
        int row = jtblPawnCoupom.getSelectedRow();
        if (evt.getClickCount() == 2 && row != -1) {
            JTable table = (JTable) evt.getSource();
            jtfPawnCouponID.setText((table.getModel().getValueAt(row, 1)).toString());
            jcbCustomerID.setSelectedItem((table.getModel().getValueAt(row, 2)).toString());
            jcbProductID.setSelectedItem((table.getModel().getValueAt(row, 3)).toString());
            jtfAmount.setText((table.getModel().getValueAt(row, 4)).toString());
            jtfPrice.setText((table.getModel().getValueAt(row, 5)).toString());
            jtfInterestRate.setText((table.getModel().getValueAt(row, 6)).toString());
            jfmtfPawnDate.setText((table.getModel().getValueAt(row, 8)).toString());
            jfmtRedeemingDate.setText((table.getModel().getValueAt(row, 9)).toString());
            Product product = _pawncouponController.getProductController().findProductByID(jcbProductID.getSelectedItem().toString());
            setStatus(product.getStatus());
        }
    }//GEN-LAST:event_jtblPawnCoupomMouseClicked

    private void jbtnCreateNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCreateNewActionPerformed
        setPawnCouponDefault();
    }//GEN-LAST:event_jbtnCreateNewActionPerformed

    private void jbtnAddPawnCouponActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAddPawnCouponActionPerformed
        PawnCoupon pawnCoupon = getPawnCouponFromForm();
        if (pawnCoupon == null) {
            MessageSupport.ShowError(null, "Lỗi", "Lỗi nhập dữ liệu. Kiểm tra và thử lại.");
        } else {
            if (_pawncouponController.add(pawnCoupon)) {
                MessageSupport.Show(null, "Thông báo", "Thêm thành công.");
                loadDataPawnCoupon(_pawncouponController.getPawnCouponList());
                setPawnCouponDefault();
                loadDataPawnCoupon(_pawncouponController.getPawnCouponList());
            }
        }
    }//GEN-LAST:event_jbtnAddPawnCouponActionPerformed

    private void jbtnFindPawnCouponActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnFindPawnCouponActionPerformed
        if (jbtnEditPawnCoupon.getText() == "Tìm") {
            jbtnEditPawnCoupon.setText("Hủy");
            PawnCoupon pawnCoupon = getPawnCouponFromForm();
            loadDataPawnCoupon(_pawncouponController.findPawnCoupon(pawnCoupon));
        } else {
            jbtnEditPawnCoupon.setText("Tìm");
            loadDataPawnCoupon(_pawncouponController.getPawnCouponList());
            setPawnCouponDefault();
        }
    }//GEN-LAST:event_jbtnFindPawnCouponActionPerformed

    private void jbtnEditPawnCouponActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEditPawnCouponActionPerformed
        PawnCoupon pawnCoupon = getPawnCouponFromForm();
        if (pawnCoupon == null) {
            MessageSupport.ShowError(null, "Lỗi", "Lỗi nhập dữ liệu. Kiểm tra và thử lại.");
        } else {
            if (_pawncouponController.edit(pawnCoupon)) {
                MessageSupport.Show(null, "Thông báo", "Sửa thành công.");
                loadDataPawnCoupon(_pawncouponController.getPawnCouponList());
                setPawnCouponDefault();
            }
        }
    }//GEN-LAST:event_jbtnEditPawnCouponActionPerformed

    private void jbtnReloadAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnReloadAllActionPerformed
        setPawnCouponDefault();
        setInterestPaymentDefaiult();
    }//GEN-LAST:event_jbtnReloadAllActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
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
    private javax.swing.JLabel jLabel25;
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
    private javax.swing.JButton jbtnAddPawnCoupon;
    private javax.swing.JButton jbtnCreateNew;
    private javax.swing.JButton jbtnEditPawnCoupon;
    private javax.swing.JButton jbtnFindPawnCoupon;
    private javax.swing.JButton jbtnReloadAll;
    private javax.swing.JComboBox<String> jcbCustomerID;
    private javax.swing.JComboBox<String> jcbProductID;
    private javax.swing.JCheckBox jchbOutOfDate;
    private javax.swing.JFormattedTextField jfmtRedeemingDate;
    private javax.swing.JFormattedTextField jfmtfInterestPaymentDate;
    private javax.swing.JFormattedTextField jfmtfPawnDate;
    private javax.swing.JLabel jlbTotalInterestRate;
    private javax.swing.JLabel jlbTotalMoney;
    private javax.swing.JLabel jlbTotalPrice;
    private javax.swing.JCheckBox jrbLiquidated;
    private javax.swing.JCheckBox jrbNeedToLiquidate;
    private javax.swing.JCheckBox jrbNotRedeemed;
    private javax.swing.JCheckBox jrbRedeemed;
    private javax.swing.JLabel jtaNote;
    private javax.swing.JTextArea jtanote;
    private javax.swing.JTable jtbInterestPaymentHistory;
    private javax.swing.JTable jtblPawnCoupom;
    private javax.swing.JTextField jtfAmount;
    private javax.swing.JTextField jtfInterestRate;
    private javax.swing.JTextField jtfMoney;
    private javax.swing.JTextField jtfPawnCouponID;
    private javax.swing.JTextField jtfPrice;
    // End of variables declaration//GEN-END:variables
}
