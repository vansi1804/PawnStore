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
import View.HomePageJFrameForm;
import static View.HomePageJFrameForm.jlblProfile;
import java.util.Arrays;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author NVS
 */
@SuppressWarnings("ClassWithoutLogger")
public class ProfileJPanelForm extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;

    public ProfileJPanelForm() {
        initComponents();
        jtfUsername.setEditable(false);
        setProfileDefault(false);
        jpfCurrentPassword.setEchoChar('*');
        jpfNewPassword.setEchoChar('*');
        jpfConfirmPassword.setEchoChar('*');
        setErrorCatchEvent();
    }

    private void setProfileDefault(boolean isChangingPassword) {
        jtfUsername.setText(StaticUser.getCurrentInstance().getUsername());
        jtfUsername.setEditable(false);
        jtfFullname.setText(StaticUser.getCurrentInstance().getFullname());
        jtfFullname.setEditable(!isChangingPassword);
        jlbInvalidFullname.setText(null);
        jpfCurrentPassword.setText(null);
        jpfNewPassword.setText(null);
        jpfNewPassword.setEditable(isChangingPassword);
        jlbInvalidNewPassword.setText(null);
        jpfConfirmPassword.setText(null);
        jpfConfirmPassword.setEditable(isChangingPassword);
        jlbInvalidConfirmPassword.setText(null);
        jlbChangePassword.setVisible(!isChangingPassword);
    }

    private void setErrorCatchEvent() {
        jtfFullname.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                jlbInvalidFullname.setText(!CheckSupport.isValidFullname(jtfFullname.getText()) ? "Họ tên không hợp lệ" : null);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                jlbInvalidFullname.setText(!CheckSupport.isValidFullname(jtfFullname.getText()) ? "Họ tên không hợp lệ" : null);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                jlbInvalidFullname.setText(!CheckSupport.isValidFullname(jtfFullname.getText()) ? "Họ tên không hợp lệ" : null);
            }
        }
        );
        jpfNewPassword.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                jlbInvalidNewPassword.setText((!jlbChangePassword.isVisible()
                        && CheckSupport.isNullOrBlank(String.valueOf(jpfNewPassword.getPassword()))
                        ? "Mật khẩu không được để trống" : null));
                jlbInvalidConfirmPassword.setText((!jlbChangePassword.isVisible()
                        && !Arrays.equals(jpfConfirmPassword.getPassword(), jpfNewPassword.getPassword()))
                        ? "Xác nhận mật khẩu không khớp" : null);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                jlbInvalidNewPassword.setText((!jlbChangePassword.isVisible()
                        && CheckSupport.isNullOrBlank(String.valueOf(jpfNewPassword.getPassword()))
                        ? "Mật khẩu không được để trống" : null));
                jlbInvalidConfirmPassword.setText((!jlbChangePassword.isVisible()
                        && !Arrays.equals(jpfConfirmPassword.getPassword(), jpfNewPassword.getPassword()))
                        ? "Xác nhận mật khẩu không khớp" : null);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                jlbInvalidNewPassword.setText((!jlbChangePassword.isVisible()
                        && CheckSupport.isNullOrBlank(String.valueOf(jpfNewPassword.getPassword()))
                        ? "Mật khẩu không được để trống" : null));
                jlbInvalidConfirmPassword.setText((!jlbChangePassword.isVisible()
                        && !Arrays.equals(jpfConfirmPassword.getPassword(), jpfNewPassword.getPassword()))
                        ? "Xác nhận mật khẩu không khớp" : null);
            }
        }
        );
        jpfConfirmPassword.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                jlbInvalidConfirmPassword.setText((!jlbChangePassword.isVisible()
                        && !Arrays.equals(jpfConfirmPassword.getPassword(), jpfNewPassword.getPassword()))
                        ? "Xác nhận mật khẩu không khớp" : null);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                jlbInvalidConfirmPassword.setText((!jlbChangePassword.isVisible()
                        && !Arrays.equals(jpfConfirmPassword.getPassword(), jpfNewPassword.getPassword()))
                        ? "Xác nhận mật khẩu không khớp" : null);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                jlbInvalidConfirmPassword.setText((!jlbChangePassword.isVisible()
                        && !Arrays.equals(jpfConfirmPassword.getPassword(), jpfNewPassword.getPassword()))
                        ? "Xác nhận mật khẩu không khớp" : null);
            }
        }
        );
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
        jpfCurrentPassword = new javax.swing.JPasswordField();
        jtfUsername = new javax.swing.JTextField();
        jtfFullname = new javax.swing.JTextField();
        jlbFullname = new javax.swing.JLabel();
        jlbUsername = new javax.swing.JLabel();
        jlbCurrentPassword = new javax.swing.JLabel();
        jlbConfirmPassword = new javax.swing.JLabel();
        jlbNewPassword = new javax.swing.JLabel();
        jpfNewPassword = new javax.swing.JPasswordField();
        jlbChangePassword = new javax.swing.JLabel();
        jlbInvalidFullname = new javax.swing.JLabel();
        jlbInvalidConfirmPassword = new javax.swing.JLabel();
        jlbInvalidNewPassword = new javax.swing.JLabel();

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
        jchbShowHirePassword.setFont(new java.awt.Font("Times New Roman", 3, 16)); // NOI18N
        jchbShowHirePassword.setForeground(new java.awt.Color(0, 0, 0));
        jchbShowHirePassword.setText("Hiện mật khẩu");
        jchbShowHirePassword.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jchbShowHirePasswordStateChanged(evt);
            }
        });
        jchbShowHirePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jchbShowHirePasswordActionPerformed(evt);
            }
        });

        jpfCurrentPassword.setBackground(new java.awt.Color(255, 255, 255));
        jpfCurrentPassword.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jpfCurrentPassword.setForeground(new java.awt.Color(0, 0, 0));

        jtfUsername.setBackground(new java.awt.Color(255, 255, 255));
        jtfUsername.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jtfUsername.setForeground(new java.awt.Color(0, 0, 0));

        jtfFullname.setBackground(new java.awt.Color(255, 255, 255));
        jtfFullname.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jtfFullname.setForeground(new java.awt.Color(0, 0, 0));

        jlbFullname.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jlbFullname.setForeground(new java.awt.Color(0, 0, 0));
        jlbFullname.setText("Họ và tên");

        jlbUsername.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jlbUsername.setForeground(new java.awt.Color(0, 0, 0));
        jlbUsername.setText("Tên đăng nhập");

        jlbCurrentPassword.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jlbCurrentPassword.setForeground(new java.awt.Color(0, 0, 0));
        jlbCurrentPassword.setText("Mật khẩu hiện tại");

        jlbConfirmPassword.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jlbConfirmPassword.setForeground(new java.awt.Color(0, 0, 0));
        jlbConfirmPassword.setText("Xác nhận mật khẩu");

        jlbNewPassword.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jlbNewPassword.setForeground(new java.awt.Color(0, 0, 0));
        jlbNewPassword.setText("Mật khẩu mới");

        jpfNewPassword.setBackground(new java.awt.Color(255, 255, 255));
        jpfNewPassword.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jpfNewPassword.setForeground(new java.awt.Color(0, 0, 0));

        jlbChangePassword.setFont(new java.awt.Font("Times New Roman", 3, 16)); // NOI18N
        jlbChangePassword.setForeground(new java.awt.Color(255, 0, 0));
        jlbChangePassword.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlbChangePassword.setText("Đổi mật khẩu");
        jlbChangePassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlbChangePasswordMouseClicked(evt);
            }
        });

        jlbInvalidFullname.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        jlbInvalidFullname.setForeground(new java.awt.Color(255, 0, 0));
        jlbInvalidFullname.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlbInvalidFullname.setText("Họ và tên không hợp lệ");

        jlbInvalidConfirmPassword.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        jlbInvalidConfirmPassword.setForeground(new java.awt.Color(255, 0, 0));
        jlbInvalidConfirmPassword.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlbInvalidConfirmPassword.setText("Xác nhận mật khẩu không khớp");

        jlbInvalidNewPassword.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        jlbInvalidNewPassword.setForeground(new java.awt.Color(255, 0, 0));
        jlbInvalidNewPassword.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlbInvalidNewPassword.setText("Mật khẩu không được trống");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(362, 362, 362)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jlbUsername, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlbCurrentPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlbNewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbFullname, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jpfNewPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                            .addComponent(jtfUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                            .addComponent(jpfCurrentPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                            .addComponent(jtfFullname, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                            .addComponent(jlbInvalidFullname, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                            .addComponent(jlbInvalidNewPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jlbInvalidConfirmPassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jlbConfirmPassword)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpfConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jbtnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jbtnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jlbChangePassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jchbShowHirePassword, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(420, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfFullname, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbFullname, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(jlbInvalidFullname, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbCurrentPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpfCurrentPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbNewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpfNewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(jlbInvalidNewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpfConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(jlbInvalidConfirmPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jchbShowHirePassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbChangePassword)
                .addGap(31, 31, 31)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
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

    private void jbtnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCancelActionPerformed
        setProfileDefault(false);
    }//GEN-LAST:event_jbtnCancelActionPerformed

    private void jbtnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSaveActionPerformed
        if (jlbChangePassword.isVisible()) { // update profile
            if (CheckSupport.isNullOrBlank(jlbInvalidFullname.getText())) {
                Account account = new Account(jtfUsername.getText(),
                        String.valueOf(jpfCurrentPassword.getPassword()), jtfFullname.getText(), false);
                if (AccountController.getCurrentInstance().updateProfile(account)) {
                    MessageSupport.Message("Thông báo", "Cập nhật thành công");
                    StaticUser.setCurrentInstance(account);
                    jlblProfile.setText(StaticUser.getCurrentInstance().getFullname()+"      ");
                    setProfileDefault(false);
                }
            }
        } else { // change password
            if (CheckSupport.isNullOrBlank(jlbInvalidNewPassword.getText()) && CheckSupport.isNullOrBlank(jlbInvalidConfirmPassword.getText())) {
                Account account = new Account(jtfUsername.getText(),
                        String.valueOf(jpfCurrentPassword.getPassword()), jtfFullname.getText(), false);
                if (AccountController.getCurrentInstance()
                        .changePassword(account, String.valueOf(jpfNewPassword.getPassword()))) {
                    MessageSupport.Message("Thông báo", "Cập nhật thành công");
                    setProfileDefault(false);
                }
            }
        }
    }//GEN-LAST:event_jbtnSaveActionPerformed

    private void jlbChangePasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbChangePasswordMouseClicked
        if (evt.getClickCount() == 2) {
            jlbChangePassword.setVisible(false);
            setProfileDefault(true);
        }
    }//GEN-LAST:event_jlbChangePasswordMouseClicked

    private void jbtnDeleteTabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDeleteTabActionPerformed
        HomePageJFrameForm.jHomePageTabbedPane.remove(HomePageJFrameForm.jHomePageTabbedPane.indexOfTab("Thông tin tài khoản"));
    }//GEN-LAST:event_jbtnDeleteTabActionPerformed

    private void jchbShowHirePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jchbShowHirePasswordActionPerformed

    }//GEN-LAST:event_jchbShowHirePasswordActionPerformed

    private void jchbShowHirePasswordStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jchbShowHirePasswordStateChanged
        Support.ShowHirePassword(jchbShowHirePassword, jpfCurrentPassword);
        Support.ShowHirePassword(jchbShowHirePassword, jpfNewPassword);
        Support.ShowHirePassword(jchbShowHirePassword, jpfConfirmPassword);
    }//GEN-LAST:event_jchbShowHirePasswordStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JButton jbtnCancel;
    private javax.swing.JButton jbtnDeleteTab;
    private javax.swing.JButton jbtnSave;
    private javax.swing.JCheckBox jchbShowHirePassword;
    private javax.swing.JLabel jlbChangePassword;
    private javax.swing.JLabel jlbConfirmPassword;
    private javax.swing.JLabel jlbCurrentPassword;
    private javax.swing.JLabel jlbFullname;
    private javax.swing.JLabel jlbInvalidConfirmPassword;
    private javax.swing.JLabel jlbInvalidFullname;
    private javax.swing.JLabel jlbInvalidNewPassword;
    private javax.swing.JLabel jlbNewPassword;
    private javax.swing.JLabel jlbUsername;
    private javax.swing.JPasswordField jpfConfirmPassword;
    private javax.swing.JPasswordField jpfCurrentPassword;
    private javax.swing.JPasswordField jpfNewPassword;
    private javax.swing.JTextField jtfFullname;
    private javax.swing.JTextField jtfUsername;
    // End of variables declaration//GEN-END:variables
}
