/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.JTabbedPaneForm;

import Common.Default;
import Controller.AccountController;
import Controller.ActivityHistoryController;
import Model.Account;
import Model.ActivityHistory;
import Model.StaticUser;
import Support.CheckSupport;
import Support.ColorFormatSupport;
import Support.MessageSupport;
import Support.Support;
import View.HomePageJFrameForm;
import java.beans.PropertyChangeEvent;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author NVS
 */
@SuppressWarnings("ClassWithoutLogger")
public class AccountJPanelForm extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;

    public AccountJPanelForm() {
        initComponents();
        setAccountDefault(null);
        setActivityHistoryFilterEvent();
    }

    private void setAccountDefault(Account account) {
        jbtnAdd.setEnabled(true);
        jlbInvalidUsername.setText(null);
        jlbInvalidFullname.setText(null);
        jlbInvalidPasword.setText(null);
        jchbShowHirePassword.setSelected(false);
        if (account == null) {
            jtfUsername.setText(null);
            jtfUsername.setEditable(true);
            jtfFullname.setText(null);
            jtfFullname.setEditable(true);
            jpfPassword.setText(null);
            jbtnLockOrUnlock.setEnabled(false);
            jbtnLockOrUnlock.setText("Khóa");
            jbtnResetPassword.setEnabled(false);
            setAccountStatus(null);
            setAccountTable(AccountController.getCurrentInstance().findAllByStatus(getAccountStatus()));
        } else {
            jtfUsername.setText(account.getUsername());
            jtfUsername.setEditable(false);
            jtfFullname.setText(account.getFullname());
            jtfFullname.setEditable(false);
            jpfPassword.setText(null);
            jbtnAdd.setEnabled(false);
            jbtnLockOrUnlock.setEnabled(true);
            jbtnLockOrUnlock.setText(account.getDeleteFlag() ? "Mở khóa" : "Khóa");
            jbtnResetPassword.setEnabled(true);
        }
        setActivityHistoryDefault(account);
    }

    private void setAccountStatus(Boolean isDeleteFlag) {
        if (isDeleteFlag == null) {
            jrbAll.setEnabled(true);
            jrbActive.setSelected(true);
        } else {
            jrbAll.setEnabled(false);
            jrbActive.setSelected(!isDeleteFlag);
            jrbLocked.setSelected(isDeleteFlag);
        }

    }

    private Boolean getAccountStatus() {
        return jrbAll.isSelected() ? null : jrbLocked.isSelected();
    }

    private void setAccountTable(List<Account> accounts) {
        ColorFormatSupport.FormatTableHeader(jtblAccount);
        ColorFormatSupport.setDataTableCenter(jtblAccount);
        DefaultTableModel model = (DefaultTableModel) jtblAccount.getModel();
        model.setRowCount(0);
        Object rowData[] = new Object[3];
        for (int i = 0; i < accounts.size(); i++) {
            rowData[0] = String.valueOf(i + 1);
            rowData[1] = accounts.get(i).getUsername();
            rowData[2] = accounts.get(i).getFullname();
            model.addRow(rowData);
        }
    }

    private Account getAccountFromForm() {
        boolean validInfo = true;
        if (!CheckSupport.isValidUsername(jtfUsername.getText())) {
            jlbInvalidUsername.setText("Tên đăng nhập chỉ chứa ký tự chữ và ký tự số");
            validInfo = false;
        }
        if (!CheckSupport.isValidFullname(jtfFullname.getText())) {
            jlbInvalidFullname.setText("Họ và tên không hợp lệ");
            validInfo = false;
        }
        if (CheckSupport.isNullOrBlank(String.valueOf(jpfPassword.getPassword()))) {
            jlbInvalidPasword.setText("Nhập mật khẩu của quản trị viên");
            validInfo = false;
        }
        return validInfo ? new Account(jtfUsername.getText(), null, jtfFullname.getText(), false) : null;
    }

    private void setActivityHistoryDefault(Account account) {
        jdcFromDate.setDate(null);
        jdcToDate.setDate(null);
        jtfUsernameKey.setText(account == null ? null : account.getUsername());
        jtfUsernameKey.setEditable(account == null);
        jtfObjectnameKey.setText(null);
        jtfActivityKey.setText(null);
        jtfInforKey.setText(null);
        if (account == null) {
            setActivityHistoryTable(ActivityHistoryController.getCurrentInstance().getList());
        }
    }

    @SuppressWarnings("AssignmentToMethodParameter")
    private void setActivityHistoryTable(List<ActivityHistory> activityHistorys) {
        if (activityHistorys == null) {
            return;
        }
        ColorFormatSupport.FormatTableHeader(jtblActivityHistory);
        ColorFormatSupport.setDataTableCenter(jtblActivityHistory);
        DefaultTableModel model = (DefaultTableModel) jtblActivityHistory.getModel();
        model.setRowCount(0);
        Object rowData[] = new Object[6];
        for (int i = 0; i <= activityHistorys.size() - 1; i++) {
            rowData[0] = String.valueOf(i + 1);
            rowData[1] = activityHistorys.get(activityHistorys.size() - 1 - i).getTime();
            rowData[2] = activityHistorys.get(activityHistorys.size() - 1 - i).getAccount().getUsername();
            rowData[3] = activityHistorys.get(activityHistorys.size() - 1 - i).getActivity();
            rowData[4] = activityHistorys.get(activityHistorys.size() - 1 - i).getObjectname();
            rowData[5] = activityHistorys.get(activityHistorys.size() - 1 - i).getInfor();
            model.addRow(rowData);
        }
    }

    private void setActivityHistoryFilterEvent() {
        jdcFromDate.addPropertyChangeListener((PropertyChangeEvent evt) -> {
            if (jdcFromDate.getDate() != null && jdcToDate.getDate() != null) {
                if (jdcFromDate.getDate().compareTo(jdcToDate.getDate()) > 0) {
                    jdcFromDate.setDate(jdcToDate.getDate());
                }
            }
            filterActivityHistory();
        });
        jdcToDate.addPropertyChangeListener((PropertyChangeEvent evt) -> {
            if (jdcFromDate.getDate() != null && jdcToDate.getDate() != null) {
                if (jdcToDate.getDate().compareTo(jdcFromDate.getDate()) < 0) {
                    jdcToDate.setDate(jdcFromDate.getDate());
                }
            }
            filterActivityHistory();
        });
        jtfUsernameKey.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterActivityHistory();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterActivityHistory();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterActivityHistory();
            }
        }
        );
        jtfActivityKey.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterActivityHistory();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterActivityHistory();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterActivityHistory();
            }
        }
        );
        jtfObjectnameKey.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterActivityHistory();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterActivityHistory();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterActivityHistory();
            }
        }
        );
        jtfInforKey.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterActivityHistory();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterActivityHistory();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterActivityHistory();
            }
        }
        );
    }

    private void filterActivityHistory() {
        setActivityHistoryTable(ActivityHistoryController.getCurrentInstance()
                .filterByKey(Support.dateToString(jdcFromDate.getDate(), Default.DATE_FORMAT),
                        Support.dateToString(jdcToDate.getDate(), Default.DATE_FORMAT),
                        jtfUsernameKey.getText(), jtfActivityKey.getText(), jtfObjectnameKey.getText(), jtfInforKey.getText()));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel6 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jbtnDeleteTab = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblAccount = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jrbLocked = new javax.swing.JRadioButton();
        jrbActive = new javax.swing.JRadioButton();
        jrbAll = new javax.swing.JRadioButton();
        jbtnReload = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jtfUsername = new javax.swing.JTextField();
        jpfPassword = new javax.swing.JPasswordField();
        jchbShowHirePassword = new javax.swing.JCheckBox();
        jbtnAdd = new javax.swing.JButton();
        jbtnLockOrUnlock = new javax.swing.JButton();
        jbtnResetPassword = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jtfFullname = new javax.swing.JTextField();
        jlbInvalidFullname = new javax.swing.JLabel();
        jlbInvalidUsername = new javax.swing.JLabel();
        jlbInvalidPasword = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jdcFromDate = new com.toedter.calendar.JDateChooser();
        jdcToDate = new com.toedter.calendar.JDateChooser();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jtfUsernameKey = new javax.swing.JTextField();
        jtfActivityKey = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jtfObjectnameKey = new javax.swing.JTextField();
        jtfInforKey = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtblActivityHistory = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(0, 255, 255));

        jPanel4.setBackground(new java.awt.Color(0, 255, 255));

        jPanel5.setBackground(new java.awt.Color(153, 153, 153));

        jLabel6.setBackground(new java.awt.Color(51, 255, 255));
        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("TÀI KHOẢN");

        jbtnDeleteTab.setFont(new java.awt.Font("Dialog", 1, 35)); // NOI18N
        jbtnDeleteTab.setForeground(new java.awt.Color(255, 0, 0));
        jbtnDeleteTab.setText("X");
        jbtnDeleteTab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnDeleteTabActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnDeleteTab)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbtnDeleteTab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(0, 255, 255));
        jPanel9.setForeground(new java.awt.Color(51, 51, 51));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jtblAccount.setBackground(new java.awt.Color(255, 255, 255));
        jtblAccount.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jtblAccount.setForeground(new java.awt.Color(0, 0, 0));
        jtblAccount.setModel(new javax.swing.table.DefaultTableModel(
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
        jtblAccount.setRowHeight(20);
        jtblAccount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblAccountMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtblAccount);
        if (jtblAccount.getColumnModel().getColumnCount() > 0) {
            jtblAccount.getColumnModel().getColumn(0).setPreferredWidth(35);
            jtblAccount.getColumnModel().getColumn(1).setMinWidth(150);
            jtblAccount.getColumnModel().getColumn(1).setPreferredWidth(150);
        }

        jLabel10.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Trạng thái :");

        jrbLocked.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(jrbLocked);
        jrbLocked.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jrbLocked.setForeground(new java.awt.Color(0, 0, 0));
        jrbLocked.setText("Đã khóa");
        jrbLocked.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbAccountStatusActionPerformed(evt);
            }
        });

        jrbActive.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(jrbActive);
        jrbActive.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jrbActive.setForeground(new java.awt.Color(0, 0, 0));
        jrbActive.setSelected(true);
        jrbActive.setText("Đang hoạt động");
        jrbActive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbAccountStatusActionPerformed(evt);
            }
        });

        jrbAll.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(jrbAll);
        jrbAll.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jrbAll.setForeground(new java.awt.Color(0, 0, 0));
        jrbAll.setText("Tất cả");
        jrbAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbAccountStatusActionPerformed(evt);
            }
        });

        jbtnReload.setBackground(new java.awt.Color(0, 255, 255));
        jbtnReload.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jbtnReload.setForeground(new java.awt.Color(51, 51, 51));
        jbtnReload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/reload.png"))); // NOI18N
        jbtnReload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnReloadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jrbLocked)
                        .addGap(18, 18, 18)
                        .addComponent(jrbActive, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jrbAll)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtnReload, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtnReload, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jrbActive, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jrbAll, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jrbLocked, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Tên đăng nhập");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Mật khẩu quản trị viên");

        jtfUsername.setBackground(new java.awt.Color(255, 255, 255));
        jtfUsername.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jtfUsername.setForeground(new java.awt.Color(0, 0, 0));
        jtfUsername.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jtfInfoMousePressed(evt);
            }
        });

        jpfPassword.setBackground(new java.awt.Color(255, 255, 255));
        jpfPassword.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jpfPassword.setForeground(new java.awt.Color(0, 0, 0));
        jpfPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jtfInfoMousePressed(evt);
            }
        });

        jchbShowHirePassword.setBackground(new java.awt.Color(204, 204, 204));
        jchbShowHirePassword.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jchbShowHirePassword.setForeground(new java.awt.Color(0, 0, 0));
        jchbShowHirePassword.setText("Hiện mật khẩu");
        jchbShowHirePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jchbShowHirePasswordActionPerformed(evt);
            }
        });

        jbtnAdd.setBackground(new java.awt.Color(0, 255, 255));
        jbtnAdd.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jbtnAdd.setForeground(new java.awt.Color(51, 51, 51));
        jbtnAdd.setText("Thêm");
        jbtnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAddActionPerformed(evt);
            }
        });

        jbtnLockOrUnlock.setBackground(new java.awt.Color(0, 255, 255));
        jbtnLockOrUnlock.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jbtnLockOrUnlock.setForeground(new java.awt.Color(51, 51, 51));
        jbtnLockOrUnlock.setText("Khóa");
        jbtnLockOrUnlock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnLockOrUnlockActionPerformed(evt);
            }
        });

        jbtnResetPassword.setBackground(new java.awt.Color(0, 255, 255));
        jbtnResetPassword.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jbtnResetPassword.setForeground(new java.awt.Color(51, 51, 51));
        jbtnResetPassword.setText("Đặt lại mật khẩu");
        jbtnResetPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnResetPasswordActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Họ và tên");

        jtfFullname.setBackground(new java.awt.Color(255, 255, 255));
        jtfFullname.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jtfFullname.setForeground(new java.awt.Color(0, 0, 0));
        jtfFullname.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jtfInfoMousePressed(evt);
            }
        });

        jlbInvalidFullname.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        jlbInvalidFullname.setForeground(new java.awt.Color(255, 0, 0));
        jlbInvalidFullname.setText("Họ và tên không hợp lệ");

        jlbInvalidUsername.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        jlbInvalidUsername.setForeground(new java.awt.Color(255, 0, 0));
        jlbInvalidUsername.setText("Tên đăng nhập không hợp lệ");

        jlbInvalidPasword.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        jlbInvalidPasword.setForeground(new java.awt.Color(255, 0, 0));
        jlbInvalidPasword.setText("Nhập mật khẩu để xác nhận");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtnResetPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtfUsername)
                    .addComponent(jlbInvalidUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtfFullname)
                    .addComponent(jlbInvalidFullname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpfPassword)
                    .addComponent(jlbInvalidPasword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jbtnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jchbShowHirePassword)
                            .addComponent(jbtnLockOrUnlock, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtfUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbInvalidUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfFullname, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbInvalidFullname, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbInvalidPasword, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jchbShowHirePassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnLockOrUnlock, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnResetPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Lịch sử hoạt động");

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 51, 51));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Từ ngày");

        jdcFromDate.setDateFormatString("dd/MM/yyyy");
        jdcFromDate.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jdcFromDate.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdcDatePropertyChange(evt);
            }
        });

        jdcToDate.setDateFormatString("dd/MM/yyyy");
        jdcToDate.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jdcToDate.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdcDatePropertyChange(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("~");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Tên đăng nhập");

        jtfUsernameKey.setBackground(new java.awt.Color(255, 255, 255));
        jtfUsernameKey.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jtfUsernameKey.setForeground(new java.awt.Color(0, 0, 0));
        jtfUsernameKey.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfActivityHistoryKeyReleased(evt);
            }
        });

        jtfActivityKey.setBackground(new java.awt.Color(255, 255, 255));
        jtfActivityKey.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jtfActivityKey.setForeground(new java.awt.Color(0, 0, 0));
        jtfActivityKey.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfActivityHistoryKeyReleased(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Hoạt động");

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 51, 51));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Đối tượng");

        jtfObjectnameKey.setBackground(new java.awt.Color(255, 255, 255));
        jtfObjectnameKey.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jtfObjectnameKey.setForeground(new java.awt.Color(0, 0, 0));
        jtfObjectnameKey.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfActivityHistoryKeyReleased(evt);
            }
        });

        jtfInforKey.setBackground(new java.awt.Color(255, 255, 255));
        jtfInforKey.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jtfInforKey.setForeground(new java.awt.Color(0, 0, 0));
        jtfInforKey.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfActivityHistoryKeyReleased(evt);
            }
        });

        jtblActivityHistory.setBackground(new java.awt.Color(255, 255, 255));
        jtblActivityHistory.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jtblActivityHistory.setForeground(new java.awt.Color(0, 0, 0));
        jtblActivityHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Ngày", "Tên đăng nhập", "Hoạt động", "Đối tượng", "Thông tin"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblActivityHistory.setRowHeight(20);
        jScrollPane2.setViewportView(jtblActivityHistory);
        if (jtblActivityHistory.getColumnModel().getColumnCount() > 0) {
            jtblActivityHistory.getColumnModel().getColumn(0).setMinWidth(35);
            jtblActivityHistory.getColumnModel().getColumn(0).setPreferredWidth(35);
            jtblActivityHistory.getColumnModel().getColumn(0).setMaxWidth(35);
            jtblActivityHistory.getColumnModel().getColumn(1).setMinWidth(140);
            jtblActivityHistory.getColumnModel().getColumn(1).setPreferredWidth(140);
            jtblActivityHistory.getColumnModel().getColumn(1).setMaxWidth(140);
            jtblActivityHistory.getColumnModel().getColumn(2).setMinWidth(50);
            jtblActivityHistory.getColumnModel().getColumn(2).setMaxWidth(200);
            jtblActivityHistory.getColumnModel().getColumn(3).setMinWidth(50);
            jtblActivityHistory.getColumnModel().getColumn(3).setMaxWidth(100);
            jtblActivityHistory.getColumnModel().getColumn(4).setMinWidth(50);
            jtblActivityHistory.getColumnModel().getColumn(4).setMaxWidth(100);
        }

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 51, 51));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Thông tin");

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(51, 51, 51));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Đến ngày");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jdcFromDate, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jdcToDate, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtfUsernameKey)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfActivityKey, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfObjectnameKey, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfInforKey)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(2, 2, 2))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(6, 6, 6)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jdcToDate, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jdcFromDate, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jtfUsernameKey, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jtfActivityKey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jtfObjectnameKey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jtfInforKey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void jbtnReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnReloadActionPerformed
        setAccountDefault(null);
    }//GEN-LAST:event_jbtnReloadActionPerformed

    private void jbtnLockOrUnlockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnLockOrUnlockActionPerformed
        Account account = getAccountFromForm();
        if (account != null) {
            if (StaticUser.getCurrentInstance().getUsername().equals(jtfUsername.getText())) {
                MessageSupport.Message("Thông báo", "Tài khoản của quản trị viên là bất tử, không thể khóa");
            } else {
                if (AccountController.getCurrentInstance()
                        .lockOrUnlock(account, String.valueOf(jpfPassword.getPassword()))) {
                    if (jbtnLockOrUnlock.getText().equals("Khóa")) {
                        MessageSupport.Message("Thông báo", "Tài khoản đã bị khóa");
                    } else {
                        MessageSupport.Message("Thông báo", "Tài khoản đã được khôi phục");
                    }
                    setAccountDefault(null);
                    ActivityHistoryController.getCurrentInstance()
                            .insert(new ActivityHistory(Support.dateToString(LocalDateTime.now(), Default.DATE_TIME_FORMAT),
                                    "Đặt lại mật khẩu", "Tài khoản", account.toString()));
                }
            }
        }
    }//GEN-LAST:event_jbtnLockOrUnlockActionPerformed

    private void jtblAccountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblAccountMouseClicked
        int row = jtblAccount.getSelectedRow();
        if (evt.getClickCount() == 2 && row != -1) {
            JTable table = (JTable) evt.getSource();
            String username = (table.getModel().getValueAt(row, 1)).toString();
            Account account = AccountController.getCurrentInstance().findOneByUsername(username);
            setAccountDefault(account);
        }
    }//GEN-LAST:event_jtblAccountMouseClicked

    private void jbtnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAddActionPerformed
        Account account = getAccountFromForm();
        if (account != null) {
            if (AccountController.getCurrentInstance().insert(account, String.valueOf(jpfPassword.getPassword()))) {
                MessageSupport.Message("Thông báo", "Tạo tài khoản thành công. Mật khẩu mặc định là 1");
                setAccountDefault(null);
                ActivityHistoryController.getCurrentInstance()
                        .insert(new ActivityHistory(Support.dateToString(LocalDateTime.now(), Default.DATE_TIME_FORMAT),
                                "Thêm mới", "Tài khoản", account.toString()));
            }
        }
    }//GEN-LAST:event_jbtnAddActionPerformed

    private void jrbAccountStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbAccountStatusActionPerformed
        setAccountTable(AccountController.getCurrentInstance().findAllByStatus(getAccountStatus()));
    }//GEN-LAST:event_jrbAccountStatusActionPerformed

    private void jbtnDeleteTabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDeleteTabActionPerformed
        HomePageJFrameForm.jHomePageTabbedPane.remove(HomePageJFrameForm.jHomePageTabbedPane.indexOfTab("Tài khoản"));
    }//GEN-LAST:event_jbtnDeleteTabActionPerformed

    private void jbtnResetPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnResetPasswordActionPerformed
        Account account = getAccountFromForm();
        if (account != null) {
            if (AccountController.getCurrentInstance().resetPassword(account, String.valueOf(jpfPassword.getPassword()))) {
                MessageSupport.Message("Thông báo", "Mật khẩu đã được đặt mặc định là 1");
                setAccountDefault(null);
                ActivityHistoryController.getCurrentInstance()
                        .insert(new ActivityHistory(Support.dateToString(LocalDateTime.now(), Default.DATE_TIME_FORMAT),
                                "Đặt lại mật khẩu", "Tài khoản", account.toString()));
            }
        }
    }//GEN-LAST:event_jbtnResetPasswordActionPerformed

    private void jtfInfoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtfInfoMousePressed
        if (evt.getSource().equals(jtfUsername)) {
            jlbInvalidUsername.setText(null);
        } else if (evt.getSource().equals(jtfFullname)) {
            jlbInvalidFullname.setText(null);
        } else if (evt.getSource().equals(jpfPassword)) {
            jlbInvalidPasword.setText(null);
        }
    }//GEN-LAST:event_jtfInfoMousePressed

    private void jtfActivityHistoryKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfActivityHistoryKeyReleased
        filterActivityHistory();
    }//GEN-LAST:event_jtfActivityHistoryKeyReleased

    private void jdcDatePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdcDatePropertyChange
        if (jdcFromDate.getDate() != null && jdcToDate.getDate() != null) {
            if (jdcFromDate.getDate().compareTo(jdcToDate.getDate()) > 0) {
                jdcFromDate.setDate(jdcToDate.getDate());
            }
        }
        if (jdcFromDate.getDate() != null && jdcToDate.getDate() != null) {
            if (jdcToDate.getDate().compareTo(jdcFromDate.getDate()) < 0) {
                jdcToDate.setDate(jdcFromDate.getDate());
            }
        }
        filterActivityHistory();
    }//GEN-LAST:event_jdcDatePropertyChange


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbtnAdd;
    private javax.swing.JButton jbtnDeleteTab;
    private javax.swing.JButton jbtnLockOrUnlock;
    private javax.swing.JButton jbtnReload;
    private javax.swing.JButton jbtnResetPassword;
    private javax.swing.JCheckBox jchbShowHirePassword;
    private com.toedter.calendar.JDateChooser jdcFromDate;
    private com.toedter.calendar.JDateChooser jdcToDate;
    private javax.swing.JLabel jlbInvalidFullname;
    private javax.swing.JLabel jlbInvalidPasword;
    private javax.swing.JLabel jlbInvalidUsername;
    private javax.swing.JPasswordField jpfPassword;
    private javax.swing.JRadioButton jrbActive;
    private javax.swing.JRadioButton jrbAll;
    private javax.swing.JRadioButton jrbLocked;
    private javax.swing.JTable jtblAccount;
    private javax.swing.JTable jtblActivityHistory;
    private javax.swing.JTextField jtfActivityKey;
    private javax.swing.JTextField jtfFullname;
    private javax.swing.JTextField jtfInforKey;
    private javax.swing.JTextField jtfObjectnameKey;
    private javax.swing.JTextField jtfUsername;
    private javax.swing.JTextField jtfUsernameKey;
    // End of variables declaration//GEN-END:variables
}
