/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.JTabbedPaneForm;

import Controller.CustomerController;
import Model.Customer;
import Support.CheckSupport;
import Support.MessageSupport;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Support.*;
/**
 *
 * @author NVS
 */
public class JCustomerPanelForm extends javax.swing.JPanel {

    CustomerController _customerController = new CustomerController();

    public JCustomerPanelForm() {
        initComponents();
        setDefault();
        loadData(_customerController.getList());
    }

    public void setDefault() {
        jtfCustomerID.setEditable(true);
        jtfCustomerID.setText("");
        jtfCustomerName.setText("");
        jrbAll.setSelected(true);
        jtfPhoneNumber.setText("");
        jtaAdress.setText("");
    }

    public void loadData(ArrayList<Customer> list) {
        DefaultTableModel model = (DefaultTableModel) jtblCustomersList.getModel();
        model.setRowCount(0);
        Object rowData[] = new Object[6];
        int STT = 1;
        for (int i = 0; i < list.size(); i++) {
            rowData[0] = String.valueOf(STT++);
            rowData[1] = list.get(i).getId();
            rowData[2] = list.get(i).getFullname();
            rowData[3] = list.get(i).getGender();
            rowData[4] = list.get(i).getPhonenumber();
            rowData[5] = list.get(i).getAddress();
            model.addRow(rowData);
        }
        Support.setDataTableCenter(jtblCustomersList);
    }

    public Customer getDataFromForm() {
        String id = jtfCustomerID.getText();
        String fullname = jtfCustomerName.getText();
        String gender;
        if (jrbMale.isSelected()) {
            gender = "Nam";
        } else if (jrbFemale.isSelected()) {
            gender = "Nữ";
        } else {
            gender = "";
        }
        String phonenumber = jtfPhoneNumber.getText();
        String address = jtaAdress.getText();
        return new Customer(id, fullname, gender, phonenumber, address);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jtfCustomerName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jtfPhoneNumber = new javax.swing.JTextField();
        jtfCustomerID = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtaAdress = new javax.swing.JTextArea();
        jbtnAddNew = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jrbMale = new javax.swing.JRadioButton();
        jrbFemale = new javax.swing.JRadioButton();
        jrbAll = new javax.swing.JRadioButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblHistory = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtblCustomersList = new javax.swing.JTable();
        jbtnAdd = new javax.swing.JButton();
        jbtnEdit = new javax.swing.JButton();
        jbtnRemove = new javax.swing.JButton();
        jbtnFind = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("KHÁCH HÀNG");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
        );

        jLabel2.setText("CMND/CCCD");

        jLabel3.setText("Tên khách hàng");

        jLabel4.setText("Địa chỉ");

        jLabel5.setText("Số điện thoại");

        jtaAdress.setColumns(20);
        jtaAdress.setRows(5);
        jScrollPane3.setViewportView(jtaAdress);

        jbtnAddNew.setText("Thêm mới");
        jbtnAddNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAddNewActionPerformed(evt);
            }
        });

        jLabel7.setText("Giới tính");

        buttonGroup1.add(jrbMale);
        jrbMale.setText("Nam");

        buttonGroup1.add(jrbFemale);
        jrbFemale.setText("Nữ");

        buttonGroup1.add(jrbAll);
        jrbAll.setText("Tất cả");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jrbMale)
                .addGap(18, 18, 18)
                .addComponent(jrbFemale)
                .addGap(18, 18, 18)
                .addComponent(jrbAll)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrbMale)
                    .addComponent(jrbFemale)
                    .addComponent(jrbAll))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfCustomerID, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnAddNew))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfCustomerName))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfPhoneNumber))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfCustomerID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jbtnAddNew, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtfCustomerName, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jLabel6.setText("Lịch sử cầm đồ");

        jtblHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Hợp đồng càm", "Mặt hàng ", "Số lượng", "Giá", "Lãi xuất", "Số kỳ đã đóng", "Lãi đã đóng", "Ngày cầm", "Ngày chuộc", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, false, false, true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblHistory.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(jtblHistory);
        jtblHistory.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jtblHistory.getColumnModel().getColumnCount() > 0) {
            jtblHistory.getColumnModel().getColumn(0).setMinWidth(50);
            jtblHistory.getColumnModel().getColumn(0).setPreferredWidth(50);
            jtblHistory.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1386, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
        );

        jtblCustomersList.setModel(new javax.swing.table.DefaultTableModel(
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
        jtblCustomersList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtblCustomersList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblCustomersListMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jtblCustomersList);
        jtblCustomersList.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jtblCustomersList.getColumnModel().getColumnCount() > 0) {
            jtblCustomersList.getColumnModel().getColumn(0).setMinWidth(50);
            jtblCustomersList.getColumnModel().getColumn(0).setPreferredWidth(50);
            jtblCustomersList.getColumnModel().getColumn(0).setMaxWidth(50);
            jtblCustomersList.getColumnModel().getColumn(1).setMinWidth(150);
            jtblCustomersList.getColumnModel().getColumn(1).setPreferredWidth(150);
            jtblCustomersList.getColumnModel().getColumn(1).setMaxWidth(150);
            jtblCustomersList.getColumnModel().getColumn(3).setMinWidth(70);
            jtblCustomersList.getColumnModel().getColumn(3).setPreferredWidth(70);
            jtblCustomersList.getColumnModel().getColumn(3).setMaxWidth(70);
            jtblCustomersList.getColumnModel().getColumn(4).setMinWidth(100);
            jtblCustomersList.getColumnModel().getColumn(4).setPreferredWidth(100);
            jtblCustomersList.getColumnModel().getColumn(4).setMaxWidth(100);
        }

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jbtnAdd.setText("Thêm");
        jbtnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAddActionPerformed(evt);
            }
        });

        jbtnEdit.setText("Sửa");
        jbtnEdit.setMaximumSize(new java.awt.Dimension(60, 25));
        jbtnEdit.setMinimumSize(new java.awt.Dimension(60, 25));
        jbtnEdit.setPreferredSize(new java.awt.Dimension(60, 25));
        jbtnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEditActionPerformed(evt);
            }
        });

        jbtnRemove.setText("Xóa");
        jbtnRemove.setMaximumSize(new java.awt.Dimension(60, 25));
        jbtnRemove.setMinimumSize(new java.awt.Dimension(60, 25));
        jbtnRemove.setPreferredSize(new java.awt.Dimension(60, 25));
        jbtnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnRemoveActionPerformed(evt);
            }
        });

        jbtnFind.setText("Tìm");
        jbtnFind.setMaximumSize(new java.awt.Dimension(60, 25));
        jbtnFind.setMinimumSize(new java.awt.Dimension(60, 25));
        jbtnFind.setPreferredSize(new java.awt.Dimension(60, 25));
        jbtnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnFindActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(jbtnAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnFind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbtnAdd)
                            .addComponent(jbtnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtnFind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void jbtnAddNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAddNewActionPerformed
        setDefault();
    }//GEN-LAST:event_jbtnAddNewActionPerformed

    private void jtblCustomersListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblCustomersListMouseClicked
        
        int row = jtblCustomersList.getSelectedRow();
        if (evt.getClickCount() == 2 && row != -1) {
            jtfCustomerID.setEditable(false);
            JTable table = (JTable) evt.getSource();
            jtfCustomerID.setText((table.getModel().getValueAt(row, 1)).toString());
            jtfCustomerName.setText((table.getModel().getValueAt(row, 2)).toString());
            String gender = (table.getModel().getValueAt(row, 3)).toString();
            if (CheckSupport.equals(gender, "Nam")) {
                jrbMale.setSelected(true);
            } else {
                jrbFemale.setSelected(true);
            }
            jtfPhoneNumber.setText((table.getModel().getValueAt(row, 4)).toString());
            jtaAdress.setText((table.getModel().getValueAt(row, 5)).toString());
        }
    }//GEN-LAST:event_jtblCustomersListMouseClicked

    private void jbtnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAddActionPerformed
        Customer customer = getDataFromForm();
        if (!CheckSupport.isValidCustomerID(customer.getId())) {
            MessageSupport.ShowError(null, "Lỗi", "CMND/CCCD phải có 9 hoặc 12 số. Kiêm tra lại.");
        } else {
            if (!CheckSupport.isValidPhonenumber(customer.getPhonenumber())) {
                MessageSupport.ShowError(null, "Lỗi", "Số điện thoại phải toàn số gồm 10 hoặc 11 chữ số.");
            } else {
                if (CheckSupport.isEmpty(customer.getGender())) {
                    MessageSupport.ShowError(null, "Lỗi", "Chọn một giới tính");
                } else {
                    if (_customerController.checkExistedCustomer(customer.getId())) {
                        MessageSupport.ShowError(null, "Lỗi", "Khách hàng tồn tại.");
                    } else {
                        if (_customerController.add(customer)) {
                            MessageSupport.Show(null, "Thông báo", "Thêm mới thành công");
                            setDefault();
                            loadData(_customerController.getList());
                            jbtnFind.setText("Tìm");
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_jbtnAddActionPerformed

    private void jbtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEditActionPerformed
        Customer customer = getDataFromForm();
        if (!CheckSupport.isValidCustomerID(customer.getId())) {
            MessageSupport.ShowError(null, "Lỗi", "CMND/CCCD phải có 9 hoặc 12 số. Kiêm tra lại.");
        } else {
            if (!CheckSupport.isValidPhonenumber(customer.getPhonenumber())) {
                MessageSupport.ShowError(null, "Lỗi", "Số điện thoại phải toàn số gồm 10 hoặc 11 chữ số.");
            } else {
                if (!_customerController.checkExistedCustomer(customer.getId())) {
                    MessageSupport.ShowError(null, "Lỗi", "Khách hàng không tồn tại.");
                } else {
                    if (_customerController.edit(customer)) {
                        MessageSupport.Show(null, "Thông báo", "Sửa thành công");
                        setDefault();
                        loadData(_customerController.getList());
                        jbtnFind.setText("Tìm");
                    }
                }
            }
        }
    }//GEN-LAST:event_jbtnEditActionPerformed

    private void jbtnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnRemoveActionPerformed
        Customer customer = getDataFromForm();
        if (customer != null) {
            if (!_customerController.checkExistedCustomer(customer.getId())) {
                MessageSupport.ShowError(null, "Lỗi", "Khách hàng không tồn tại.");
            } else {
                if (MessageSupport.Confirm(null, "Xác nhận", "Bạn có muốn xóa không?") == JOptionPane.YES_OPTION) {
                    if (_customerController.remove(customer.getId())) {
                        MessageSupport.Show(null, "Thông báo", "Đã xóa thành công!");
                        setDefault();
                        loadData(_customerController.getList());
                    }
                }
            }
        }
    }//GEN-LAST:event_jbtnRemoveActionPerformed

    private void jbtnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnFindActionPerformed
        if (jbtnFind.getText() == "Tìm") {
            jbtnFind.setText("Hủy");
            Customer customer = getDataFromForm();
            loadData(_customerController.find(customer));
        } else {
            jbtnFind.setText("Tìm");
            loadData(_customerController.getList());
            setDefault();
        }
    }//GEN-LAST:event_jbtnFindActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton jbtnAdd;
    private javax.swing.JButton jbtnAddNew;
    private javax.swing.JButton jbtnEdit;
    private javax.swing.JButton jbtnFind;
    private javax.swing.JButton jbtnRemove;
    private javax.swing.JRadioButton jrbAll;
    private javax.swing.JRadioButton jrbFemale;
    private javax.swing.JRadioButton jrbMale;
    private javax.swing.JTextArea jtaAdress;
    private javax.swing.JTable jtblCustomersList;
    private javax.swing.JTable jtblHistory;
    private javax.swing.JTextField jtfCustomerID;
    private javax.swing.JTextField jtfCustomerName;
    private javax.swing.JTextField jtfPhoneNumber;
    // End of variables declaration//GEN-END:variables
}
