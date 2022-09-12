/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.JTabbedPaneForm;

import Controller.AccountController;
import Model.Account;
import Model.StaticUser;
import Support.CheckSupport;
import Support.MessageSupport;
import Support.Support;
import View.JHomePageJFrameForm;

/**
 *
 * @author Bau Kien
 */
@SuppressWarnings("ClassWithoutLogger")
public class JProfilePanelForm extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;

    private final AccountController accountController = new AccountController();

    public JProfilePanelForm() {
        initComponents();
        jtfUserName.setEditable(false);
        setProfileDefault();
        jpfOldPassword.setEchoChar('*');
    }

    private void setProfileDefault() {
        jtfFullName.setText(StaticUser.getCurrentInstanceUser().getFullname());
        jtfUserName.setText(StaticUser.getCurrentInstanceUser().getUsername());
        if (StaticUser.getCurrentInstanceUser().getUsername().equals("admin")) {
            jpfOldPassword.setText(StaticUser.getCurrentInstanceUser().getPassword());
        } else {
            jpfOldPassword.setText(null);
        }
        jpfNewPassword.setText(null);
        jpfNewPassword.setEditable(false);
        jpfConfirmPassword.setText(null);
    }

    private Account getAccountFromForm() {
        String fullname = jtfFullName.getText();
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
        String username = jtfUserName.getText();
        String oldPassword = String.valueOf(jpfOldPassword.getPassword());
        String newPassword = String.valueOf(jpfNewPassword.getPassword());
        String passwordConfirm = String.valueOf(jpfConfirmPassword.getPassword());
        boolean status = StaticUser.getCurrentInstanceUser().getDeleteflag();
        if (!jpfNewPassword.isEditable()) {
            if (oldPassword.equals(passwordConfirm)) {
                return new Account(username, oldPassword, fullname, status);
            } else {
                MessageSupport.ErrorMessage("Lỗi", "Xác nhận mật khẩu không đúng.");
                return null;
            }
        } else {
            if (oldPassword.equals(StaticUser.getCurrentInstanceUser().getPassword())) {
                if (CheckSupport.isBlank(newPassword)) {
                    MessageSupport.ErrorMessage("Lỗi", "Mật khẩu không được để trống hoặc toàn ký tự trắng.");
                    return null;
                } else if (newPassword.equals(passwordConfirm)) {
                    return new Account(username, newPassword, fullname, status);
                } else {
                    MessageSupport.ErrorMessage("Lỗi", "Xác nhận mật khẩu không đúng.");
                    return null;
                }
            } else {
                MessageSupport.ErrorMessage("Lỗi", "Mật khẩu cũ không đúng.");
                return null;
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jbtnDeleteTab = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jbtnCancel = new javax.swing.JButton();
        jbtnSave = new javax.swing.JButton();
        jpfConfirmPassword = new javax.swing.JPasswordField();
        jchbShowHirePassword = new javax.swing.JCheckBox();
        jpfOldPassword = new javax.swing.JPasswordField();
        jtfUserName = new javax.swing.JTextField();
        jtfFullName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jpfNewPassword = new javax.swing.JPasswordField();
        jlbChangePassword = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(51, 255, 255));

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setForeground(new java.awt.Color(0, 0, 0));

        jLabel1.setBackground(new java.awt.Color(153, 153, 153));
        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("THÔNG TIN TÀI KHOẢN");

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
                .addComponent(jbtnDeleteTab)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbtnDeleteTab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setForeground(new java.awt.Color(0, 0, 0));

        jbtnCancel.setBackground(new java.awt.Color(0, 255, 255));
        jbtnCancel.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jbtnCancel.setForeground(new java.awt.Color(0, 0, 0));
        jbtnCancel.setText("Hủy");
        jbtnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCancelActionPerformed(evt);
            }
        });

        jbtnSave.setBackground(new java.awt.Color(0, 255, 255));
        jbtnSave.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jbtnSave.setForeground(new java.awt.Color(0, 0, 0));
        jbtnSave.setText("Lưu");
        jbtnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSaveActionPerformed(evt);
            }
        });

        jpfConfirmPassword.setBackground(new java.awt.Color(255, 255, 255));
        jpfConfirmPassword.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jpfConfirmPassword.setForeground(new java.awt.Color(0, 0, 0));

        jchbShowHirePassword.setBackground(new java.awt.Color(204, 204, 204));
        jchbShowHirePassword.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jchbShowHirePassword.setForeground(new java.awt.Color(0, 0, 0));
        jchbShowHirePassword.setText("Hiện mật khẩu");
        jchbShowHirePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jchbShowHirePasswordActionPerformed(evt);
            }
        });

        jpfOldPassword.setBackground(new java.awt.Color(255, 255, 255));
        jpfOldPassword.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jpfOldPassword.setForeground(new java.awt.Color(0, 0, 0));

        jtfUserName.setBackground(new java.awt.Color(255, 255, 255));
        jtfUserName.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jtfUserName.setForeground(new java.awt.Color(0, 0, 0));

        jtfFullName.setBackground(new java.awt.Color(255, 255, 255));
        jtfFullName.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jtfFullName.setForeground(new java.awt.Color(0, 0, 0));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Họ và tên");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Tên đăng nhập");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Mật khẩu hiện tại");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Xác nhận mật khẩu");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Mật khẩu mới");

        jpfNewPassword.setBackground(new java.awt.Color(255, 255, 255));
        jpfNewPassword.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jpfNewPassword.setForeground(new java.awt.Color(0, 0, 0));

        jlbChangePassword.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jlbChangePassword.setForeground(new java.awt.Color(255, 0, 0));
        jlbChangePassword.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlbChangePassword.setText("Đổi mật khẩu");
        jlbChangePassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlbChangePasswordMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(375, 375, 375)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jchbShowHirePassword)
                            .addComponent(jpfConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jpfNewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                    .addComponent(jbtnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jbtnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jtfFullName, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jtfUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jpfOldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jlbChangePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(286, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfFullName, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpfOldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpfNewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jchbShowHirePassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpfConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jlbChangePassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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

    private void jchbShowHirePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jchbShowHirePasswordActionPerformed
        Support.ShowHirePassword(jchbShowHirePassword, jpfOldPassword);
        Support.ShowHirePassword(jchbShowHirePassword, jpfNewPassword);
    }//GEN-LAST:event_jchbShowHirePasswordActionPerformed

    private void jbtnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCancelActionPerformed
        setProfileDefault();
    }//GEN-LAST:event_jbtnCancelActionPerformed

    private void jbtnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSaveActionPerformed
        Account account = getAccountFromForm();
        if (account != null) {
            if (accountController.update(account)) {
                MessageSupport.Message("Thông báo", "Sửa thành công.");
                setProfileDefault();
                StaticUser.setCurrentInstanceUser(account);
                JHomePageJFrameForm.setProfileName(StaticUser.getCurrentInstanceUser().getFullname());
            } else {
                MessageSupport.Message("Thông báo", "Sửa thất bại.");
            }
        }
    }//GEN-LAST:event_jbtnSaveActionPerformed

    private void jlbChangePasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbChangePasswordMouseClicked
        if (evt.getClickCount() == 2) {
            setProfileDefault();
            jpfNewPassword.setEditable(true);
        }
    }//GEN-LAST:event_jlbChangePasswordMouseClicked

    private void jbtnDeleteTabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDeleteTabActionPerformed
        JHomePageJFrameForm.jHomePageTabbedPane.remove(JHomePageJFrameForm.jHomePageTabbedPane.indexOfTab("Thông tin tài khoản"));
    }//GEN-LAST:event_jbtnDeleteTabActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JButton jbtnCancel;
    private javax.swing.JButton jbtnDeleteTab;
    private javax.swing.JButton jbtnSave;
    private javax.swing.JCheckBox jchbShowHirePassword;
    private javax.swing.JLabel jlbChangePassword;
    private javax.swing.JPasswordField jpfConfirmPassword;
    private javax.swing.JPasswordField jpfNewPassword;
    private javax.swing.JPasswordField jpfOldPassword;
    private javax.swing.JTextField jtfFullName;
    private javax.swing.JTextField jtfUserName;
    // End of variables declaration//GEN-END:variables
}
