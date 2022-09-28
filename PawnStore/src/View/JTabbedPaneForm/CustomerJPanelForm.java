/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.JTabbedPaneForm;

import Controller.ActivityHistoryController;
import Controller.CustomerController;
import Controller.InterestPaymentController;
import Controller.PawnCouponController;
import Model.ActivityHistory;
import Model.Customer;
import Model.InterestPayment;
import Model.PawnCoupon;
import Model.StaticUser;
import Support.CheckSupport;
import Support.ColorFormatSupport;
import Support.MessageSupport;
import Support.Support;
import View.HomePageJFrameForm;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author NVS
 */
@SuppressWarnings("ClassWithoutLogger")
public class CustomerJPanelForm extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;

    public CustomerJPanelForm() {
        initComponents();
        setCustomerDefault(null);
        setCustomerFindEvent();
    }

    public CustomerJPanelForm(Customer customer) {
        initComponents();
        setCustomerDefault(customer);
        Support.setRowTableSelection(jtblCustomer, 1, customer.getId());
        setCustomerFindEvent();
    }

    private void setCustomerDefault(Customer customer) {
        if (customer == null) {
            jbtnAdd.setEnabled(true);
            jbtnEdit.setEnabled(true);
            jtfID.setText("");
            jtfID.setEditable(true);
            jtfFullname.setText("");
            setGender("");
            jtfPhonenumber.setText("");
            jtaAddress.setText("");
            setCustomerStatus("");
            setCustomerTable(CustomerController.getCurrentInstance().findCustomerByDeleteflagKey(getCustomerStatus()));
            jbtnPawn.setEnabled(false);
            jbtnAdd.setEnabled(false);
            jbtnEdit.setEnabled(false);
        } else {
            jbtnPawn.setEnabled(true);
            jbtnAdd.setEnabled(false);
            jbtnEdit.setEnabled(true);
            jtfID.setText(customer.getId());
            jtfID.setEditable(false);
            jtfFullname.setText(customer.getFullname());
            setGender(customer.getGender());
            jtfPhonenumber.setText(customer.getPhonenumber());
            jtaAddress.setText(customer.getAddress());
            setCustomerStatus(customer.getDeleteflag() ? "1" : "0");
            setCustomerTable(CustomerController.getCurrentInstance().findCustomerByDeleteflagKey(getCustomerStatus()));
        }
        setPawnHistoryTable(customer);
    }

    private void setGender(String gender) {
        if (gender == null || CheckSupport.isBlank(gender)) {
            jrbMaleGender.setEnabled(true);
            jrbFemaleGender.setEnabled(true);
            jrbAllGender.setEnabled(true);
            jrbAllGender.setSelected(true);
        } else {
            switch (gender) {
                case "Nam" -> {
                    jrbMaleGender.setEnabled(true);
                    jrbMaleGender.setSelected(true);
                    jrbFemaleGender.setEnabled(true);
                    jrbAllGender.setEnabled(false);
                }
                case "Nữ" -> {
                    jrbMaleGender.setEnabled(true);
                    jrbFemaleGender.setEnabled(true);
                    jrbFemaleGender.setSelected(true);
                    jrbAllGender.setEnabled(false);
                }
            }
        }
    }

    private String getGender() {
        if (jrbMaleGender.isSelected()) {
            return "Nam";
        } else if (jrbFemaleGender.isSelected()) {
            return "Nữ";
        } else {
            return null;
        }
    }

    private void setCustomerStatus(String status) {
        if (CheckSupport.isBlank(status)) {
            jrbStopServingStatus.setEnabled(true);
            jrbServingStatus.setEnabled(true);
            jrbServingStatus.setSelected(true);
            jrbAllStatus.setEnabled(true);
        } else {
            switch (status) {
                case "0" -> {
                    jrbStopServingStatus.setEnabled(true);
                    jrbServingStatus.setEnabled(true);
                    jrbServingStatus.setSelected(true);
                    jrbAllStatus.setEnabled(false);
                }
                case "1" -> {
                    jrbStopServingStatus.setEnabled(true);
                    jrbStopServingStatus.setSelected(true);
                    jrbServingStatus.setEnabled(true);
                    jrbAllStatus.setEnabled(false);
                }
            }
        }
    }

    private String getCustomerStatus() {
        if (jrbStopServingStatus.isSelected()) {
            return "1";
        } else if (jrbServingStatus.isSelected()) {
            return "0";
        } else {
            return null;
        }
    }

    private void setCustomerTable(ArrayList<Customer> customers) {
        ColorFormatSupport.FormatTableHeader(jtblCustomer);
        ColorFormatSupport.setDataTableCenter(jtblCustomer);
        DefaultTableModel model = (DefaultTableModel) jtblCustomer.getModel();
        model.setRowCount(0);
        Object rowData[] = new Object[6];
        for (int i = 0; i < customers.size(); i++) {
            rowData[0] = String.valueOf(i + 1);
            rowData[1] = customers.get(i).getId();
            rowData[2] = customers.get(i).getFullname();
            rowData[3] = customers.get(i).getGender();
            rowData[4] = customers.get(i).getPhonenumber();
            rowData[5] = customers.get(i).getAddress();
            model.addRow(rowData);
        }
    }

    private void setPawnHistoryTable(Customer customer) {
        long totalPrice = 0;
        long totalPayed = 0;
        long totalDebt = 0;
        ColorFormatSupport.setDataTableCenter(jtblPawningHistory);
        DefaultTableModel model = (DefaultTableModel) jtblPawningHistory.getModel();
        model.setRowCount(0);
        if (customer == null) {
            jlbTotalPawnedHistory.setText("");
            jlbTotalPrice.setText("");
            jlTotalPayed.setText("");
            jlbTotalDebt.setText("");
        } else {
            ArrayList<PawnCoupon> pawnCoupons = PawnCouponController.getCurrentInstance().findPawnCouponByCustomerKey(customer);
            Object rowData[] = new Object[11];
            for (int i = 0; i < pawnCoupons.size(); i++) {
                rowData[0] = String.valueOf(i + 1);
                rowData[1] = pawnCoupons.get(i).getId();
                rowData[2] = pawnCoupons.get(i).getPawnDate();
                rowData[3] = Support.getFormatNumber(pawnCoupons.get(i).getPrice());
                totalPrice += pawnCoupons.get(i).getPrice();
                rowData[4] = pawnCoupons.get(i).getInterestRate();
                ArrayList<InterestPayment> interestPayments = InterestPaymentController.getCurrentInstance().getList(pawnCoupons.get(i));
                rowData[5] = interestPayments.size();
                long interestPayed = 0;
                for (InterestPayment interestPayment : interestPayments) {
                    interestPayed += interestPayment.getMoney();
                }
                rowData[6] = interestPayed;
                totalPayed += interestPayed;
                if (!interestPayments.isEmpty()) {
                    rowData[7] = interestPayments.get(interestPayments.size() - 1).getDebt();
                    totalDebt += interestPayments.get(interestPayments.size() - 1).getDebt();
                } else {
                    rowData[7] = 0;
                }
                rowData[8] = pawnCoupons.get(i).getRedeem0rLiquidationDate();
                rowData[9] = Support.getFormatNumber(pawnCoupons.get(i).getLiquidationPrice());
                rowData[10] = pawnCoupons.get(i).getStatus();
                model.addRow(rowData);
                jlbTotalPawnedHistory.setText("Tổng:");
                jlbTotalPrice.setText(Support.getFormatNumber(totalPrice));
                jlTotalPayed.setText(Support.getFormatNumber(totalPayed));
                jlbTotalDebt.setText(Support.getFormatNumber(totalDebt));
            }
        }
    }

    private Customer getCustomerFromForm() {
        String id = jtfID.getText();
        if (!CheckSupport.isValidCustomerID(id)) {
            MessageSupport.Message("Lỗi", "CMND/CCCD không hợp lệ.");
            return null;
        }
        String fullname = jtfFullname.getText();
        if (CheckSupport.isBlank(fullname)) {
            MessageSupport.ErrorMessage("Lỗi", "Họ và tên không được để trống.");
            return null;
        } else if (CheckSupport.doesContainsSpescialChar(fullname)) {
            MessageSupport.ErrorMessage("Lỗi", "Họ và tên không chứa ký tự đặc biệt.");
            return null;
        } else if (CheckSupport.doesContainsNumber(fullname)) {
            MessageSupport.ErrorMessage("Lỗi", "Họ và tên không chứa ký tự số.");
            return null;
        }
        String gender = getGender();
        String phonenumber = jtfPhonenumber.getText();
        if (!CheckSupport.isValidPhonenumber(phonenumber)) {
            MessageSupport.ErrorMessage("Lỗi", "Số điện thoại không hợp lệ.");
            return null;
        }
        String address = jtaAddress.getText();
        if (CheckSupport.isBlank(address)) {
            MessageSupport.ErrorMessage("Lỗi", "Địa chỉ không để trống.");
            return null;
        }
        boolean deleteflag = getCustomerStatus().equals("1");
        return new Customer(id, fullname, gender, phonenumber, address, deleteflag);
    }

    private void findCustomer() {
        if (!jbtnAdd.isEnabled() && !jbtnEdit.isEnabled()) {
            ArrayList<Customer> results = CustomerController.getCurrentInstance()
                    .findCustomerByKey(jtfID.getText(), jtfFullname.getText(), getGender(),
                            jtfPhonenumber.getText(), jtaAddress.getText(), getCustomerStatus());
            setCustomerTable(results);
        }
    }

    private void setCustomerFindEvent() {
        jtfID.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                findCustomer();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                findCustomer();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                findCustomer();
            }
        });
        jtfFullname.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                findCustomer();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                findCustomer();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                findCustomer();
            }
        });
        jtfPhonenumber.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                findCustomer();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                findCustomer();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                findCustomer();
            }
        });
        jtaAddress.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                findCustomer();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                findCustomer();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                findCustomer();
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jbtnDeleteTab = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jtfFullname = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jtfID = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtaAddress = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jrbMaleGender = new javax.swing.JRadioButton();
        jrbFemaleGender = new javax.swing.JRadioButton();
        jrbAllGender = new javax.swing.JRadioButton();
        jbtnAdd = new javax.swing.JButton();
        jbtnEdit = new javax.swing.JButton();
        jtfPhonenumber = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jrbStopServingStatus = new javax.swing.JRadioButton();
        jrbServingStatus = new javax.swing.JRadioButton();
        jrbAllStatus = new javax.swing.JRadioButton();
        jPanel10 = new javax.swing.JPanel();
        jbtnAddNewCustomer = new javax.swing.JButton();
        jbtnPawn = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblPawningHistory = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jlbTotalPrice = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jlTotalPayed = new javax.swing.JLabel();
        jlbTotalDebt = new javax.swing.JLabel();
        jlbTotalPawnedHistory = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtblCustomer = new javax.swing.JTable();
        jbtnReload = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(51, 255, 255));

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("KHÁCH HÀNG");

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

        jPanel3.setBackground(new java.awt.Color(51, 255, 255));

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("CMND/CCCD");

        jtfFullname.setBackground(new java.awt.Color(255, 255, 255));
        jtfFullname.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jtfFullname.setForeground(new java.awt.Color(0, 0, 0));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Tên khách hàng");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Địa chỉ");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Số điện thoại");

        jtfID.setBackground(new java.awt.Color(255, 255, 255));
        jtfID.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jtfID.setForeground(new java.awt.Color(0, 0, 0));

        jtaAddress.setBackground(new java.awt.Color(255, 255, 255));
        jtaAddress.setColumns(8);
        jtaAddress.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jtaAddress.setForeground(new java.awt.Color(0, 0, 0));
        jtaAddress.setRows(3);
        jtaAddress.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane3.setViewportView(jtaAddress);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Giới tính");

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));
        jPanel7.setForeground(new java.awt.Color(0, 0, 0));

        jrbMaleGender.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(jrbMaleGender);
        jrbMaleGender.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jrbMaleGender.setForeground(new java.awt.Color(0, 0, 0));
        jrbMaleGender.setText("Nam");
        jrbMaleGender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbGenderActionPerformed(evt);
            }
        });

        jrbFemaleGender.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(jrbFemaleGender);
        jrbFemaleGender.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jrbFemaleGender.setForeground(new java.awt.Color(0, 0, 0));
        jrbFemaleGender.setText("Nữ");
        jrbFemaleGender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbGenderActionPerformed(evt);
            }
        });

        jrbAllGender.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(jrbAllGender);
        jrbAllGender.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jrbAllGender.setForeground(new java.awt.Color(0, 0, 0));
        jrbAllGender.setText("Tất cả");
        jrbAllGender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbGenderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jrbMaleGender, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jrbFemaleGender, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jrbAllGender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrbMaleGender, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jrbFemaleGender, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jrbAllGender, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jbtnAdd.setBackground(new java.awt.Color(0, 255, 255));
        jbtnAdd.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jbtnAdd.setForeground(new java.awt.Color(0, 0, 0));
        jbtnAdd.setText("Thêm");
        jbtnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAddActionPerformed(evt);
            }
        });

        jbtnEdit.setBackground(new java.awt.Color(0, 255, 255));
        jbtnEdit.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jbtnEdit.setForeground(new java.awt.Color(0, 0, 0));
        jbtnEdit.setText("Sửa");
        jbtnEdit.setMaximumSize(new java.awt.Dimension(60, 25));
        jbtnEdit.setMinimumSize(new java.awt.Dimension(60, 25));
        jbtnEdit.setPreferredSize(new java.awt.Dimension(60, 25));
        jbtnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEditActionPerformed(evt);
            }
        });

        jtfPhonenumber.setBackground(new java.awt.Color(255, 255, 255));
        jtfPhonenumber.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jtfPhonenumber.setForeground(new java.awt.Color(0, 0, 0));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Trạng thái");

        jrbStopServingStatus.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup3.add(jrbStopServingStatus);
        jrbStopServingStatus.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jrbStopServingStatus.setForeground(new java.awt.Color(0, 0, 0));
        jrbStopServingStatus.setText("Ngưng phục vụ");
        jrbStopServingStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbtnStatusActionPerformed(evt);
            }
        });

        jrbServingStatus.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup3.add(jrbServingStatus);
        jrbServingStatus.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jrbServingStatus.setForeground(new java.awt.Color(0, 0, 0));
        jrbServingStatus.setSelected(true);
        jrbServingStatus.setText("Phục vụ");
        jrbServingStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbtnStatusActionPerformed(evt);
            }
        });

        jrbAllStatus.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup3.add(jrbAllStatus);
        jrbAllStatus.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jrbAllStatus.setForeground(new java.awt.Color(0, 0, 0));
        jrbAllStatus.setText("Tất cả");
        jrbAllStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbtnStatusActionPerformed(evt);
            }
        });

        jPanel10.setBackground(new java.awt.Color(0, 255, 255));

        jbtnAddNewCustomer.setBackground(new java.awt.Color(255, 255, 255));
        jbtnAddNewCustomer.setForeground(new java.awt.Color(0, 0, 0));
        jbtnAddNewCustomer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/addNew.png"))); // NOI18N
        jbtnAddNewCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbtnAddNewCustomerMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jbtnAddNewCustomer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jbtnAddNewCustomer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, Short.MAX_VALUE)
        );

        jbtnPawn.setBackground(new java.awt.Color(0, 255, 255));
        jbtnPawn.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jbtnPawn.setForeground(new java.awt.Color(0, 0, 0));
        jbtnPawn.setText("Cầm");
        jbtnPawn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnPawnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtnPawn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jbtnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jtfID, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfFullname, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtfPhonenumber, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jrbStopServingStatus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jrbServingStatus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jrbAllStatus)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jtfID, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfFullname, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfPhonenumber, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jrbStopServingStatus)
                    .addComponent(jrbServingStatus)
                    .addComponent(jrbAllStatus))
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnPawn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Lịch sử cầm đồ");

        jtblPawningHistory.setBackground(new java.awt.Color(255, 255, 255));
        jtblPawningHistory.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jtblPawningHistory.setForeground(new java.awt.Color(0, 0, 0));
        jtblPawningHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã Hợp đồng", "Ngày cầm", "Giá", "Lãi xuất", "Số kỳ đóng", "Lãi đã đóng", "Nợ", "Ngày chuộc/Thanh lý", "Giá thanh lý", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblPawningHistory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblPawningHistoryMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtblPawningHistory);
        if (jtblPawningHistory.getColumnModel().getColumnCount() > 0) {
            jtblPawningHistory.getColumnModel().getColumn(0).setMinWidth(30);
            jtblPawningHistory.getColumnModel().getColumn(0).setPreferredWidth(30);
            jtblPawningHistory.getColumnModel().getColumn(0).setMaxWidth(30);
            jtblPawningHistory.getColumnModel().getColumn(1).setMinWidth(120);
            jtblPawningHistory.getColumnModel().getColumn(1).setPreferredWidth(120);
            jtblPawningHistory.getColumnModel().getColumn(1).setMaxWidth(120);
            jtblPawningHistory.getColumnModel().getColumn(2).setMinWidth(100);
            jtblPawningHistory.getColumnModel().getColumn(2).setPreferredWidth(100);
            jtblPawningHistory.getColumnModel().getColumn(2).setMaxWidth(100);
            jtblPawningHistory.getColumnModel().getColumn(3).setMinWidth(120);
            jtblPawningHistory.getColumnModel().getColumn(3).setPreferredWidth(120);
            jtblPawningHistory.getColumnModel().getColumn(3).setMaxWidth(120);
            jtblPawningHistory.getColumnModel().getColumn(4).setMinWidth(70);
            jtblPawningHistory.getColumnModel().getColumn(4).setPreferredWidth(70);
            jtblPawningHistory.getColumnModel().getColumn(4).setMaxWidth(70);
            jtblPawningHistory.getColumnModel().getColumn(5).setMinWidth(70);
            jtblPawningHistory.getColumnModel().getColumn(5).setPreferredWidth(70);
            jtblPawningHistory.getColumnModel().getColumn(5).setMaxWidth(70);
            jtblPawningHistory.getColumnModel().getColumn(6).setMinWidth(120);
            jtblPawningHistory.getColumnModel().getColumn(6).setPreferredWidth(120);
            jtblPawningHistory.getColumnModel().getColumn(6).setMaxWidth(120);
            jtblPawningHistory.getColumnModel().getColumn(7).setMinWidth(100);
            jtblPawningHistory.getColumnModel().getColumn(7).setPreferredWidth(100);
            jtblPawningHistory.getColumnModel().getColumn(7).setMaxWidth(100);
            jtblPawningHistory.getColumnModel().getColumn(8).setMinWidth(200);
            jtblPawningHistory.getColumnModel().getColumn(8).setPreferredWidth(200);
            jtblPawningHistory.getColumnModel().getColumn(8).setMaxWidth(200);
            jtblPawningHistory.getColumnModel().getColumn(9).setMinWidth(120);
            jtblPawningHistory.getColumnModel().getColumn(9).setPreferredWidth(120);
            jtblPawningHistory.getColumnModel().getColumn(9).setMaxWidth(120);
        }

        jPanel8.setBackground(new java.awt.Color(204, 204, 204));

        jlbTotalPrice.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jlbTotalPrice.setForeground(new java.awt.Color(0, 0, 0));
        jlbTotalPrice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbTotalPrice.setText("0");

        jLabel16.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jlTotalPayed.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jlTotalPayed.setForeground(new java.awt.Color(0, 0, 0));
        jlTotalPayed.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlTotalPayed.setText("0");

        jlbTotalDebt.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jlbTotalDebt.setForeground(new java.awt.Color(0, 0, 0));
        jlbTotalDebt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbTotalDebt.setText("0");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jlbTotalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlTotalPayed, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbTotalDebt, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(505, 505, 505))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlbTotalPrice, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlTotalPayed)
                        .addComponent(jlbTotalDebt))))
        );

        jlbTotalPawnedHistory.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jlbTotalPawnedHistory.setForeground(new java.awt.Color(0, 0, 0));
        jlbTotalPawnedHistory.setText("Tổng  :");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jlbTotalPawnedHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbTotalPawnedHistory)))
        );

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));

        jtblCustomer.setBackground(new java.awt.Color(255, 255, 255));
        jtblCustomer.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jtblCustomer.setForeground(new java.awt.Color(0, 0, 0));
        jtblCustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "CMND/CCCD", "Tên khách hàng", "Giới tính", "SĐT", "Địa chỉ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblCustomer.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtblCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblCustomerMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jtblCustomer);
        if (jtblCustomer.getColumnModel().getColumnCount() > 0) {
            jtblCustomer.getColumnModel().getColumn(0).setMinWidth(35);
            jtblCustomer.getColumnModel().getColumn(0).setPreferredWidth(35);
            jtblCustomer.getColumnModel().getColumn(0).setMaxWidth(35);
            jtblCustomer.getColumnModel().getColumn(1).setMinWidth(150);
            jtblCustomer.getColumnModel().getColumn(1).setPreferredWidth(150);
            jtblCustomer.getColumnModel().getColumn(1).setMaxWidth(150);
            jtblCustomer.getColumnModel().getColumn(3).setMinWidth(70);
            jtblCustomer.getColumnModel().getColumn(3).setPreferredWidth(70);
            jtblCustomer.getColumnModel().getColumn(3).setMaxWidth(70);
            jtblCustomer.getColumnModel().getColumn(4).setMinWidth(100);
            jtblCustomer.getColumnModel().getColumn(4).setPreferredWidth(100);
            jtblCustomer.getColumnModel().getColumn(4).setMaxWidth(100);
        }

        jbtnReload.setBackground(new java.awt.Color(0, 255, 255));
        jbtnReload.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jbtnReload.setForeground(new java.awt.Color(0, 0, 0));
        jbtnReload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/reload.png"))); // NOI18N
        jbtnReload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnReloadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbtnReload, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnReload, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void jtblCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblCustomerMouseClicked
        int row = jtblCustomer.getSelectedRow();
        if (evt.getClickCount() == 2 && row != -1) {
            JTable table = (JTable) evt.getSource();
            String id = (table.getModel().getValueAt(row, 1)).toString();
            Customer customer = CustomerController.getCurrentInstance().getCustomer(id);
            setCustomerDefault(customer);
        }
    }//GEN-LAST:event_jtblCustomerMouseClicked

    private void jbtnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAddActionPerformed
        Customer customer = getCustomerFromForm();
        if (customer != null) {
            Customer existingCustomer = CustomerController.getCurrentInstance().getCustomer(customer.getId());
            if (existingCustomer == null) {
                if (CustomerController.getCurrentInstance().insert(customer)) {
                    MessageSupport.Message("Thông báo", "Thêm mới khách hàng thành công.");
                    ActivityHistoryController.getCurrentInstance().insert(
                            new ActivityHistory(Support.dateToString(new Date(), Support.getDateTimeFormat()),
                                    StaticUser.getCurrentInstanceUser(), "Thêm mới", "Khách hàng", customer.toString()));
                    setCustomerDefault(null);
                } else {
                    MessageSupport.ErrorMessage("Lỗi", "Thêm mới khách hàng thất bại.");
                }
            } else {
                MessageSupport.ErrorMessage("Lỗi", "CMND/CCCD tồn tại.");
            }
        }
    }//GEN-LAST:event_jbtnAddActionPerformed

    private void jbtnReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnReloadActionPerformed
        setCustomerDefault(null);
    }//GEN-LAST:event_jbtnReloadActionPerformed

    private void jbtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEditActionPerformed
        Customer customer = getCustomerFromForm();
        if (customer != null) {
            if (CustomerController.getCurrentInstance().update(customer)) {
                MessageSupport.Message("Thông báo", "Sửa thông tin khách hàng thành công.");
                ActivityHistoryController.getCurrentInstance().insert(
                        new ActivityHistory(Support.dateToString(new Date(), Support.getDateTimeFormat()),
                                StaticUser.getCurrentInstanceUser(), "Sửa", "Khách hàng", customer.toString()));
                setCustomerDefault(null);
            } else {
                MessageSupport.ErrorMessage("Lỗi", "Sửa thông tin khách hàng thất bại.");
            }
        }
    }//GEN-LAST:event_jbtnEditActionPerformed

    private void jrbGenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbGenderActionPerformed
        findCustomer();
    }//GEN-LAST:event_jrbGenderActionPerformed

    private void jrbtnStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbtnStatusActionPerformed
        findCustomer();
    }//GEN-LAST:event_jrbtnStatusActionPerformed

    private void jtblPawningHistoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblPawningHistoryMouseClicked
        int row = jtblPawningHistory.getSelectedRow();
        if (evt.getClickCount() == 2 && row != -1) {
            JTable table = (JTable) evt.getSource();
            String id = (table.getModel().getValueAt(row, 1)).toString();
            @SuppressWarnings("UnusedAssignment")
            JPanel jPanel = null;
            PawnCoupon pawnCoupon = PawnCouponController.getCurrentInstance().getPawnCoupon(id);
            String title = "Hợp đồng";
            if (HomePageJFrameForm.jHomePageTabbedPane.indexOfTab(title) != -1) {
                HomePageJFrameForm.jHomePageTabbedPane.remove(HomePageJFrameForm.jHomePageTabbedPane.indexOfTab(title));
            }
            jPanel = new PawnCouponJPanelForm(pawnCoupon);
            HomePageJFrameForm.jHomePageTabbedPane.addTab(title, jPanel);
            HomePageJFrameForm.jHomePageTabbedPane.setSelectedComponent(jPanel);
        }
    }//GEN-LAST:event_jtblPawningHistoryMouseClicked

    private void jbtnDeleteTabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDeleteTabActionPerformed
        HomePageJFrameForm.jHomePageTabbedPane.remove(HomePageJFrameForm.jHomePageTabbedPane.indexOfTab("Khách hàng"));
    }//GEN-LAST:event_jbtnDeleteTabActionPerformed

    private void jbtnAddNewCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnAddNewCustomerMouseClicked
        if (evt.getClickCount() == 2) {
            setCustomerDefault(null);
            setGender("Nam");
            setCustomerStatus("0");
            jbtnAdd.setEnabled(true);
            jbtnEdit.setEnabled(false);
        }
    }//GEN-LAST:event_jbtnAddNewCustomerMouseClicked

    private void jbtnPawnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnPawnActionPerformed
        Customer customer = getCustomerFromForm();
        if (customer.getDeleteflag()) {
            MessageSupport.Message("Thông báo!", "Khách hàng đã ngưng phục vụ, không thể tiếp tục cầm");
        } else {
            @SuppressWarnings("UnusedAssignment")
            JPanel jPanel = null;
            String title = "Hợp đồng";
            if (HomePageJFrameForm.jHomePageTabbedPane.indexOfTab(title) != -1) {
                HomePageJFrameForm.jHomePageTabbedPane.remove(HomePageJFrameForm.jHomePageTabbedPane.indexOfTab(title));
            }
            jPanel = new PawnCouponJPanelForm(customer);
            HomePageJFrameForm.jHomePageTabbedPane.addTab(title, jPanel);
            HomePageJFrameForm.jHomePageTabbedPane.setSelectedComponent(jPanel);
        }
    }//GEN-LAST:event_jbtnPawnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton jbtnAdd;
    private javax.swing.JButton jbtnAddNewCustomer;
    private javax.swing.JButton jbtnDeleteTab;
    private javax.swing.JButton jbtnEdit;
    private javax.swing.JButton jbtnPawn;
    private javax.swing.JButton jbtnReload;
    private javax.swing.JLabel jlTotalPayed;
    private javax.swing.JLabel jlbTotalDebt;
    private javax.swing.JLabel jlbTotalPawnedHistory;
    private javax.swing.JLabel jlbTotalPrice;
    private javax.swing.JRadioButton jrbAllGender;
    private javax.swing.JRadioButton jrbAllStatus;
    private javax.swing.JRadioButton jrbFemaleGender;
    private javax.swing.JRadioButton jrbMaleGender;
    private javax.swing.JRadioButton jrbServingStatus;
    private javax.swing.JRadioButton jrbStopServingStatus;
    private javax.swing.JTextArea jtaAddress;
    private javax.swing.JTable jtblCustomer;
    private javax.swing.JTable jtblPawningHistory;
    private javax.swing.JTextField jtfFullname;
    private javax.swing.JTextField jtfID;
    private javax.swing.JTextField jtfPhonenumber;
    // End of variables declaration//GEN-END:variables
}
