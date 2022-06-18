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
        Support.FormatTableHeader(jtblAccountList);
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
        Support.setDataTableCenter(jtblAccountList);
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
        jtfFullname = new javax.swing.JTextField();
        jtfUsername = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jchbShowHirePassword = new javax.swing.JCheckBox();
        jbtnAdd = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblAccountList = new javax.swing.JTable();
        jlbResetPassword = new javax.swing.JLabel();
        jpfPassword = new javax.swing.JPasswordField();
        jpfConfirmPassword = new javax.swing.JPasswordField();
        jbtnCreateNew = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel1.setText("Họ và tên");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel2.setText("Tên đăng nhập");

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        jPanel5.setBackground(new java.awt.Color(153, 153, 153));

        jLabel6.setBackground(new java.awt.Color(51, 255, 255));
        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("TÀI KHOẢN");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 1192, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
        );

        jPanel9.setBackground(new java.awt.Color(204, 204, 204));
        jPanel9.setForeground(new java.awt.Color(51, 51, 51));

        jtfFullname.setBackground(new java.awt.Color(255, 255, 255));
        jtfFullname.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jtfFullname.setForeground(new java.awt.Color(0, 0, 0));

        jtfUsername.setBackground(new java.awt.Color(255, 255, 255));
        jtfUsername.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jtfUsername.setForeground(new java.awt.Color(0, 0, 0));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Họ và tên");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Mật khẩu quản trị viên");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Xác nhận mật khẩu");

        jchbShowHirePassword.setBackground(new java.awt.Color(204, 204, 204));
        jchbShowHirePassword.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jchbShowHirePassword.setForeground(new java.awt.Color(0, 0, 0));
        jchbShowHirePassword.setText("Hiện mật khẩu");
        jchbShowHirePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jchbShowHirePasswordActionPerformed(evt);
            }
        });

        jbtnAdd.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jbtnAdd.setForeground(new java.awt.Color(51, 51, 51));
        jbtnAdd.setText("Thêm");
        jbtnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAddActionPerformed(evt);
            }
        });

        jtblAccountList.setBackground(new java.awt.Color(255, 255, 255));
        jtblAccountList.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jtblAccountList.setForeground(new java.awt.Color(0, 0, 0));
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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jlbResetPassword.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jlbResetPassword.setForeground(new java.awt.Color(255, 0, 0));
        jlbResetPassword.setText("Đặt lại mật khẩu");
        jlbResetPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlbResetPasswordMouseClicked(evt);
            }
        });

        jpfPassword.setBackground(new java.awt.Color(255, 255, 255));
        jpfPassword.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jpfPassword.setForeground(new java.awt.Color(0, 0, 0));

        jpfConfirmPassword.setBackground(new java.awt.Color(255, 255, 255));
        jpfConfirmPassword.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jpfConfirmPassword.setForeground(new java.awt.Color(0, 0, 0));

        jbtnCreateNew.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jbtnCreateNew.setForeground(new java.awt.Color(51, 51, 51));
        jbtnCreateNew.setText("Tạo mới");
        jbtnCreateNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCreateNewActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Tên đăng nhập");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jpfConfirmPassword))
                        .addComponent(jchbShowHirePassword, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jtfFullname, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jlbResetPassword, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel9Layout.createSequentialGroup()
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(6, 6, 6))
                                .addGroup(jPanel9Layout.createSequentialGroup()
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jpfPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                                .addComponent(jtfUsername))))
                    .addComponent(jbtnAdd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnCreateNew)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfFullname, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtnCreateNew, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jpfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jchbShowHirePassword)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jpfConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlbResetPassword)
                        .addGap(12, 12, 12)
                        .addComponent(jbtnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(112, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void jbtnCreateNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCreateNewActionPerformed
        setEditable(true);
        setDefault();
        jpfPassword.setEchoChar('*');
        jpfConfirmPassword.setEchoChar('*');
        jtfUsername.setEditable(true);
        jtfFullname.setEditable(true);

    }//GEN-LAST:event_jbtnCreateNewActionPerformed

    private void jlbResetPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbResetPasswordMouseClicked
        Account account = getDataFromForm();
        if (account != null) {
            if (!CheckSupport.equals(String.valueOf(jpfPassword.getPassword()), User.getCurrentInstance().getPassword())) {
                MessageSupport.ShowError(null, "Lỗi", "Sai mật khẩu của admin");
            } else {
                if (!CheckSupport.equals(String.valueOf(jpfPassword.getPassword()), String.valueOf(jpfConfirmPassword.getPassword()))) {
                    MessageSupport.ShowError(null, "Lỗi", "Xác nhận mật khẩu không khớp");
                } else {
                    if (!_accountController.checkExistingAccount(account.getUsername())) {
                        MessageSupport.ShowError(null, "Lỗi", "Tên đăng nhập không tồn tại.");
                    } else {
                        if (MessageSupport.Confirm(null, "Xác nhận", "Xác nhận cài đặt mật khẩu mặc định?") == JOptionPane.YES_OPTION) {
                            if (_accountController.resetPassword(jtfUsername.getText())) {
                                MessageSupport.Show(null, "Thông báo", "Mật khẩu mặc định của tài khoản là: 1");
                                setDefault();
                                loadData();
                            }
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_jlbResetPasswordMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnAdd;
    private javax.swing.JButton jbtnCreateNew;
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
