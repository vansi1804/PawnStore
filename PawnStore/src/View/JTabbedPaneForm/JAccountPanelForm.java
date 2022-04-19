/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.JTabbedPaneForm;

import Controller.AccountController;
import Model.Account;
import Model.User;
import Support.CheckSupport;
import Support.MessageSupport;
import Support.Support;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author NVS
 */
public class JAccountPanelForm extends javax.swing.JPanel {

    AccountController _accountController = new AccountController();

    public JAccountPanelForm() {
        initComponents();
        setDefault();
        loadData();
    }

    public void setDefault() {
        jpfPassword.setEchoChar('*');
        jpfConfirmPassword.setEchoChar('*');
        jtfUsername.setText(null);
        jtfFullname.setText(null);
        jpfPassword.setText(null);
        jpfConfirmPassword.setText(null);
        setEditable(false);
    }

    public void setEditable(boolean bool) {
        jtfFullname.setEditable(bool);
        jtfUsername.setEditable(bool);
    }

    public void loadData() {
        DefaultTableModel model = (DefaultTableModel) jtblAccountList.getModel();
        model.setRowCount(0);
        ArrayList<Account> list = new ArrayList<>(_accountController.getList());
        Object rowData[] = new Object[3];
        int STT = 1;
        for (int i = 0; i < list.size(); i++) {
            rowData[0] = String.valueOf(STT++);
            rowData[1] = list.get(i).getUsername();
            rowData[2] = list.get(i).getFullname();
            model.addRow(rowData);
        }
    }

    public Account getDataFromForm() {
        String username = jtfUsername.getText();
        String fullname = jtfFullname.getText();
        if (CheckSupport.isEmpty(fullname) || CheckSupport.isEmpty(username)) {
            MessageSupport.ShowError(null, "Lỗi", "Họ và tên hoặc tên đăng nhập không được để trống.");
            return null;
        } else {
            if (CheckSupport.containsWhiteSpace(username)) {
                MessageSupport.ShowError(null, "Lỗi", "Tên đăng nhập không được chứa khoảng trống.");
                return null;
            } else {
                if (CheckSupport.containsSpescialChar(username)) {
                    MessageSupport.ShowError(null, "Lỗi", "Tên đăng nhập không được chứa ký tự đặc biệt.");
                    return null;
                }
            }
        }
        return new Account(username, "1", fullname);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jtfUserFullName = new javax.swing.JTextField();
        jtfUserName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jtfFullname = new javax.swing.JTextField();
        jtfUsername = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jchbShowHirePassword = new javax.swing.JCheckBox();
        jbtnRemove = new javax.swing.JButton();
        jbtnAdd = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblAccountList = new javax.swing.JTable();
        jlbResetPassword = new javax.swing.JLabel();
        jpfPassword = new javax.swing.JPasswordField();
        jpfConfirmPassword = new javax.swing.JPasswordField();
        btnAddNew = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();

        jLabel1.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel1.setText("Họ và tên");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel2.setText("Tên đăng nhập");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("TÀI KHOẢN");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
        );

        jLabel3.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel3.setText("Họ và tên");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel4.setText("Tên đăng nhập");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel5.setText("Mật khẩu");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel7.setText("Xác nhận mật khẩu");

        jchbShowHirePassword.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        jchbShowHirePassword.setText("Hiện mật khẩu");
        jchbShowHirePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jchbShowHirePasswordActionPerformed(evt);
            }
        });

        jbtnRemove.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jbtnRemove.setText("Xóa");
        jbtnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnRemoveActionPerformed(evt);
            }
        });

        jbtnAdd.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jbtnAdd.setText("Thêm");
        jbtnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAddActionPerformed(evt);
            }
        });

        jtblAccountList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên đăng nhập", "Họ và tên"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblAccountList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblAccountListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtblAccountList);
        if (jtblAccountList.getColumnModel().getColumnCount() > 0) {
            jtblAccountList.getColumnModel().getColumn(0).setPreferredWidth(30);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jlbResetPassword.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        jlbResetPassword.setForeground(new java.awt.Color(255, 0, 0));
        jlbResetPassword.setText("Đặt lại mật khẩu");
        jlbResetPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlbResetPasswordMouseClicked(evt);
            }
        });

        btnAddNew.setText("Thêm mới");
        btnAddNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jbtnAdd)
                                .addGap(98, 98, 98)
                                .addComponent(jbtnRemove))
                            .addComponent(jpfConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(97, 97, 97))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jchbShowHirePassword)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jpfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jtfUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jtfFullname, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jlbResetPassword))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAddNew)
                        .addGap(18, 18, 18)))
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfFullname, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAddNew))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jpfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jchbShowHirePassword)
                        .addGap(6, 6, 6)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jpfConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addComponent(jlbResetPassword)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbtnRemove)
                            .addComponent(jbtnAdd))))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 103, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jchbShowHirePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jchbShowHirePasswordActionPerformed
        Support.ShowHirePassword(jchbShowHirePassword, jpfPassword);
    }//GEN-LAST:event_jchbShowHirePasswordActionPerformed

    private void jbtnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAddActionPerformed
        Account account = getDataFromForm();
        if (account != null) {
            if (!CheckSupport.equals(String.valueOf(jpfPassword.getPassword()), User.getCurrentInstance().getPassword())) {
                MessageSupport.ShowError(null, "Lỗi", "Sai mật khẩu của admin");
            } else {
                if (!CheckSupport.equals(String.valueOf(jpfPassword.getPassword()), String.valueOf(jpfConfirmPassword.getPassword()))) {
                    MessageSupport.ShowError(null, "Lỗi", "Xác nhận mật khẩu không khớp");
                } else {
                    if (_accountController.checkExistingAccount(account.getUsername())) {
                        MessageSupport.ShowError(null, "Lỗi", "Tên đăng nhập đã tồn tại.");
                    } else {
                        if (_accountController.add(account)) {
                            MessageSupport.Show(null, "Thông báo", "Thêm mới thành công. Mật khẩu mặc định là 1.");
                            setDefault();
                            loadData();
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_jbtnAddActionPerformed

    private void jtblAccountListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblAccountListMouseClicked
        setEditable(false);
        int row = jtblAccountList.getSelectedRow();
        if (evt.getClickCount() == 2 && row != -1) {
            JTable table = (JTable) evt.getSource();
            jtfUsername.setText((table.getModel().getValueAt(row, 1)).toString());
            jtfFullname.setText((table.getModel().getValueAt(row, 2)).toString());
        }
    }//GEN-LAST:event_jtblAccountListMouseClicked

    private void btnAddNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewActionPerformed
        setEditable(true);
        setDefault();
        jpfPassword.setEchoChar('*');
        jpfConfirmPassword.setEchoChar('*');
        jtfUsername.setEditable(true);
        jtfFullname.setEditable(true);

    }//GEN-LAST:event_btnAddNewActionPerformed

    private void jlbResetPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbResetPasswordMouseClicked
        if (!CheckSupport.isEmpty(jtfUsername.getText())) {
            if (MessageSupport.Confirm(null, "Xác nhận", "Xác nhận cài đặt mật khẩu mặc định?") == JOptionPane.YES_OPTION) {
                if (_accountController.resetPassword(jtfUsername.getText())) {
                    MessageSupport.Show(null, "Thông báo", "Mật khẩu mặc định của tài khoản là: 1!");
                }
            }
        }
    }//GEN-LAST:event_jlbResetPasswordMouseClicked

    private void jbtnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnRemoveActionPerformed
        Account account = getDataFromForm();
        if (account != null) {
            if (CheckSupport.isEmpty(String.valueOf(jpfPassword.getPassword()))) {
                MessageSupport.ShowError(null, "Lỗi", "Nhập mật khẩu của admin để xác nhận.");
            } else {
                if (!CheckSupport.equals(String.valueOf(jpfPassword.getPassword()), User.getCurrentInstance().getPassword())) {
                    MessageSupport.ShowError(null, "Lỗi", "Sai mật khẩu của admin");
                } else {
                    if (!CheckSupport.equals(String.valueOf(jpfPassword.getPassword()), String.valueOf(jpfConfirmPassword.getPassword()))) {
                        MessageSupport.ShowError(null, "Lỗi", "Xác nhận mật khẩu không khớp");
                    } else {
                        if (MessageSupport.Confirm(null, "Xác nhận", "Bạn có muốn xóa không?") == JOptionPane.YES_OPTION) {
                            if (_accountController.remove(account.getUsername())) {
                                MessageSupport.Show(null, "Thông báo", "Đã xóa thành công!");
                                setDefault();
                                loadData();
                            }
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_jbtnRemoveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddNew;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnAdd;
    private javax.swing.JButton jbtnRemove;
    private javax.swing.JCheckBox jchbShowHirePassword;
    private javax.swing.JLabel jlbResetPassword;
    private javax.swing.JPasswordField jpfConfirmPassword;
    private javax.swing.JPasswordField jpfPassword;
    private javax.swing.JTable jtblAccountList;
    private javax.swing.JTextField jtfFullname;
    private javax.swing.JTextField jtfUserFullName;
    private javax.swing.JTextField jtfUserName;
    private javax.swing.JTextField jtfUsername;
    // End of variables declaration//GEN-END:variables
}
