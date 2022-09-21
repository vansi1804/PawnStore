/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.JTabbedPaneForm;

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
import java.util.ArrayList;
import java.util.Date;
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
    private static boolean ActivityHistoryFindAble;

    public AccountJPanelForm() {
        initComponents();
        setAccountDefault(null);
        setActivityHistoryFindEvent();
    }

    private void setAccountDefault(Account account) {
        if (account == null) {
            jbtnAdd.setEnabled(true);
            jbtnLockOrUnlock.setText("Khóa");
            jbtnLockOrUnlock.setEnabled(false);
            jtfFullname.setText("");
            jtfFullname.setEditable(true);
            jtfusername.setText("");
            jtfusername.setEditable(true);
            jpfPassword.setText("");
            jpfConfirmPassword.setText("");
            jlbResetPassword.setVisible(false);
            setAccountStatus("");
            setAccountTable(AccountController.getCurrentInstance().getList());
        } else {
            jbtnAdd.setEnabled(false);
            jbtnLockOrUnlock.setEnabled(true);
            if (!account.getDeleteflag()) {
                jbtnLockOrUnlock.setText("Khóa");
            } else {
                jbtnLockOrUnlock.setText("Mở khóa");
            }
            jtfFullname.setText(account.getFullname());
            jtfFullname.setEditable(false);
            jtfusername.setText(account.getUsername());
            jtfusername.setEditable(false);
            jpfPassword.setText("");
            jpfConfirmPassword.setText("");
            jlbResetPassword.setVisible(true);
        }
        setActivityHistoryDefault(account);
    }

    private void setAccountStatus(String status) {
        if (CheckSupport.isBlank(status)) {
            jrbUnlocked.setEnabled(true);
            jrbUnlocked.setSelected(true);
            jrbLocked.setEnabled(true);
            jrbAll.setEnabled(true);
        } else {
            switch (status) {
                case "0" -> {
                    jrbUnlocked.setEnabled(true);
                    jrbUnlocked.setSelected(true);
                    jrbLocked.setEnabled(true);
                    jrbAll.setEnabled(false);
                }
                case "1" -> {
                    jrbUnlocked.setEnabled(true);
                    jrbLocked.setEnabled(true);
                    jrbLocked.setSelected(true);
                    jrbAll.setEnabled(false);
                }
            }
        }
    }

    private String getAccountStatus() {
        if (jrbUnlocked.isSelected()) {
            return "0";
        } else if (jrbLocked.isSelected()) {
            return "1";
        } else {
            setAccountTable(AccountController.getCurrentInstance().getList());
            return null;
        }
    }

    private void setAccountTable(ArrayList<Account> accounts) {
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
        String username = jtfusername.getText();
        if (CheckSupport.isBlank(username)) {
            MessageSupport.ErrorMessage("Lỗi", "Tên đăng nhập không được để trống hoặc chứa ký tự trắng.");
            return null;
        }
        boolean deleteflag = false;
        return new Account(username, "1", fullname, deleteflag);
    }

    private void findAccount() {
        if (CheckSupport.isBlank(getAccountStatus())) {
            setAccountTable(AccountController.getCurrentInstance().getList());
        } else if (getAccountStatus().equals("0")) {
            setAccountTable(AccountController.getCurrentInstance().findAccountByDeleteFlag(false));
        } else if (getAccountStatus().equals("1")) {
            setAccountTable(AccountController.getCurrentInstance().findAccountByDeleteFlag(true));
        }
    }

    private void setActivityHistoryDefault(Account account) {
        ActivityHistoryFindAble = false;
        jDCFromDate.setDate(null);
        jDCToDate.setDate(null);
        jtfUsernameKey.setText("");
        jtfActivityKey.setText("");
        jtfObjectnameKey.setText("");
        jtfInforKey.setText("");
        setActivityHistoryTable(ActivityHistoryController.getCurrentInstance().getList());
        ActivityHistoryFindAble = true;
        if (account != null) {
            jtfUsernameKey.setText(account.getUsername());
        }
    }

    @SuppressWarnings("AssignmentToMethodParameter")
    private void setActivityHistoryTable(ArrayList<ActivityHistory> activityHistorys) {
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

    private void findActivityHistory() {
        if (ActivityHistoryFindAble) {
            @SuppressWarnings({"UnusedAssignment", "CollectionWithoutInitialCapacity"})
            ArrayList<ActivityHistory> results = new ArrayList<>();
            results = ActivityHistoryController.getCurrentInstance()
                    .findActivityHistoryByKey(
                            Support.dateToString(jDCFromDate.getDate(), Support.getDateFormat()),
                            Support.dateToString(jDCToDate.getDate(), Support.getDateFormat()),
                            jtfUsernameKey.getText(),
                            jtfActivityKey.getText(),
                            jtfObjectnameKey.getText(),
                            jtfInforKey.getText());
            setActivityHistoryTable(results);
        }
    }

    private void setActivityHistoryFindEvent() {
        jDCToDate.setDate(null);
        jDCFromDate.addPropertyChangeListener((PropertyChangeEvent evt) -> {
            if (jDCFromDate.getDate() != null && jDCToDate.getDate() != null) {
                if (jDCFromDate.getDate().compareTo(jDCToDate.getDate()) > 0) {
                    jDCFromDate.setDate(jDCToDate.getDate());
                }
            }
            findActivityHistory();
        });
        jDCToDate.addPropertyChangeListener((PropertyChangeEvent evt) -> {
            if (jDCFromDate.getDate() != null && jDCToDate.getDate() != null) {
                if (jDCToDate.getDate().compareTo(jDCFromDate.getDate()) < 0) {
                    jDCToDate.setDate(jDCFromDate.getDate());
                }
            }
            findActivityHistory();
        });
        jtfUsernameKey.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                findActivityHistory();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                findActivityHistory();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                findActivityHistory();
            }
        }
        );
        jtfActivityKey.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                findActivityHistory();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                findActivityHistory();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                findActivityHistory();
            }
        }
        );
        jtfObjectnameKey.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                findActivityHistory();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                findActivityHistory();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                findActivityHistory();
            }
        }
        );
        jtfInforKey.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                findActivityHistory();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                findActivityHistory();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                findActivityHistory();
            }
        }
        );
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jtfUserFullName = new javax.swing.JTextField();
        jtfUserName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        buttonGroup1 = new javax.swing.ButtonGroup();
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
        jrbUnlocked = new javax.swing.JRadioButton();
        jrbAll = new javax.swing.JRadioButton();
        jbtnReload = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jtfFullname = new javax.swing.JTextField();
        jtfusername = new javax.swing.JTextField();
        jpfPassword = new javax.swing.JPasswordField();
        jchbShowHirePassword = new javax.swing.JCheckBox();
        jpfConfirmPassword = new javax.swing.JPasswordField();
        jlbResetPassword = new javax.swing.JLabel();
        jbtnAdd = new javax.swing.JButton();
        jbtnLockOrUnlock = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jDCFromDate = new com.toedter.calendar.JDateChooser();
        jDCToDate = new com.toedter.calendar.JDateChooser();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jtfUsernameKey = new javax.swing.JTextField();
        jtfActivityKey = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jtfObjectnameKey = new javax.swing.JTextField();
        jtfInforKey = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtblActivityHistory = new javax.swing.JTable();

        jLabel1.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel1.setText("Họ và tên");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel2.setText("Tên đăng nhập");

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
        jtblAccount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblAccountMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtblAccount);
        if (jtblAccount.getColumnModel().getColumnCount() > 0) {
            jtblAccount.getColumnModel().getColumn(0).setMinWidth(50);
            jtblAccount.getColumnModel().getColumn(0).setPreferredWidth(50);
            jtblAccount.getColumnModel().getColumn(0).setMaxWidth(50);
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

        jrbUnlocked.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(jrbUnlocked);
        jrbUnlocked.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jrbUnlocked.setForeground(new java.awt.Color(0, 0, 0));
        jrbUnlocked.setSelected(true);
        jrbUnlocked.setText("Đang hoạt động");
        jrbUnlocked.addActionListener(new java.awt.event.ActionListener() {
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jrbLocked)
                        .addGap(13, 13, 13)
                        .addComponent(jrbUnlocked, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jrbAll)
                        .addGap(0, 183, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbtnReload, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jrbLocked, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jrbUnlocked, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jrbAll, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbtnReload, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Họ và tên");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Tên đăng nhập");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Mật khẩu quản trị viên");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Xác nhận mật khẩu");

        jtfFullname.setBackground(new java.awt.Color(255, 255, 255));
        jtfFullname.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jtfFullname.setForeground(new java.awt.Color(0, 0, 0));

        jtfusername.setBackground(new java.awt.Color(255, 255, 255));
        jtfusername.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jtfusername.setForeground(new java.awt.Color(0, 0, 0));

        jpfPassword.setBackground(new java.awt.Color(255, 255, 255));
        jpfPassword.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jpfPassword.setForeground(new java.awt.Color(0, 0, 0));

        jchbShowHirePassword.setBackground(new java.awt.Color(204, 204, 204));
        jchbShowHirePassword.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jchbShowHirePassword.setForeground(new java.awt.Color(0, 0, 0));
        jchbShowHirePassword.setText("Hiện mật khẩu");
        jchbShowHirePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jchbShowHirePasswordActionPerformed(evt);
            }
        });

        jpfConfirmPassword.setBackground(new java.awt.Color(255, 255, 255));
        jpfConfirmPassword.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jpfConfirmPassword.setForeground(new java.awt.Color(0, 0, 0));

        jlbResetPassword.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jlbResetPassword.setForeground(new java.awt.Color(255, 0, 0));
        jlbResetPassword.setText("Đặt lại mật khẩu");
        jlbResetPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlbResetPasswordMouseClicked(evt);
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jtfusername, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpfPassword, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jchbShowHirePassword)
                    .addComponent(jpfConfirmPassword, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlbResetPassword)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jbtnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbtnLockOrUnlock, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jtfFullname))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jtfFullname, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfusername, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jchbShowHirePassword)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpfConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlbResetPassword)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbtnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtnLockOrUnlock, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Lịch sử hoạt động");

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 51, 51));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Ngày :");

        jDCFromDate.setDateFormatString("dd/MM/yyyy");
        jDCFromDate.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N

        jDCToDate.setDateFormatString("dd/MM/yyyy");
        jDCToDate.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N

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

        jtfActivityKey.setBackground(new java.awt.Color(255, 255, 255));
        jtfActivityKey.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jtfActivityKey.setForeground(new java.awt.Color(0, 0, 0));

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

        jtfInforKey.setBackground(new java.awt.Color(255, 255, 255));
        jtfInforKey.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jtfInforKey.setForeground(new java.awt.Color(0, 0, 0));

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Thông tin");

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
        jScrollPane2.setViewportView(jtblActivityHistory);
        if (jtblActivityHistory.getColumnModel().getColumnCount() > 0) {
            jtblActivityHistory.getColumnModel().getColumn(0).setMinWidth(40);
            jtblActivityHistory.getColumnModel().getColumn(0).setPreferredWidth(40);
            jtblActivityHistory.getColumnModel().getColumn(0).setMaxWidth(40);
            jtblActivityHistory.getColumnModel().getColumn(1).setMinWidth(140);
            jtblActivityHistory.getColumnModel().getColumn(1).setPreferredWidth(140);
            jtblActivityHistory.getColumnModel().getColumn(1).setMaxWidth(140);
            jtblActivityHistory.getColumnModel().getColumn(2).setMinWidth(150);
            jtblActivityHistory.getColumnModel().getColumn(2).setPreferredWidth(150);
            jtblActivityHistory.getColumnModel().getColumn(2).setMaxWidth(200);
            jtblActivityHistory.getColumnModel().getColumn(3).setPreferredWidth(100);
            jtblActivityHistory.getColumnModel().getColumn(3).setMaxWidth(100);
            jtblActivityHistory.getColumnModel().getColumn(4).setPreferredWidth(100);
            jtblActivityHistory.getColumnModel().getColumn(4).setMaxWidth(100);
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDCFromDate, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDCToDate, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtfInforKey))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jDCToDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel17)
                                .addComponent(jDCFromDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel13)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel16)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jtfInforKey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfUsernameKey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfActivityKey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfObjectnameKey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
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

    private void jlbResetPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbResetPasswordMouseClicked
        if (!StaticUser.getCurrentInstanceUser().getUsername().equals(jtfusername.getText())) {
            if (String.valueOf(jpfPassword.getPassword()).equals(StaticUser.getCurrentInstanceUser().getPassword())) {
                if (String.valueOf(jpfPassword.getPassword()).equals(String.valueOf(jpfConfirmPassword.getPassword()))) {
                    Account existingAccount = AccountController.getCurrentInstance().getAccount(jtfusername.getText());
                    existingAccount.setPassword("1");
                    if (AccountController.getCurrentInstance().update(existingAccount)) {
                        MessageSupport.Message("Thông báo", "Mật khẩu đã được đặt lại mặc định là 1.");
                        ActivityHistoryController.getCurrentInstance().insert(
                                new ActivityHistory(Support.dateToString(new Date(), Support.getDateTimeFormat()),
                                        StaticUser.getCurrentInstanceUser(), "Đặt lại mật khẩu", "Tài khoản", existingAccount.toString()));
                        setAccountDefault(null);
                    }
                } else {
                    MessageSupport.ErrorMessage("Lỗi", "Xác nhận mật khẩu không đúng.");
                }
            } else {
                MessageSupport.ErrorMessage("Lỗi", "Mật khẩu của quản trị viên không đúng.");
            }
        } else {
            MessageSupport.Message("Thông báo", "Mật khẩu mặc định của quản trị viên là admin. Hãy vào mục thông tin tài khoản để đổi mật khẩu.");

        }
    }//GEN-LAST:event_jlbResetPasswordMouseClicked

    private void jbtnReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnReloadActionPerformed
        setAccountDefault(null);
    }//GEN-LAST:event_jbtnReloadActionPerformed

    private void jbtnLockOrUnlockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnLockOrUnlockActionPerformed
        if (jtfusername.getText().equals("admin")) {
            MessageSupport.Message("Thông báo", "Tài khoản của quản trị viên là bất tử, không thể khóa.");
            return;
        }
        if (String.valueOf(jpfPassword.getPassword()).equals(StaticUser.getCurrentInstanceUser().getPassword())) {
            if (String.valueOf(jpfPassword.getPassword()).equals(String.valueOf(jpfConfirmPassword.getPassword()))) {
                if (jbtnLockOrUnlock.getText().equals("Khóa")) {
                    Account existingAccount = AccountController.getCurrentInstance().getAccount(jtfusername.getText());
                    existingAccount.setDeleteflag(true);
                    if (AccountController.getCurrentInstance().update(existingAccount)) {
                        MessageSupport.Message("Thông báo", "Tài khoản đã khóa.");
                        ActivityHistoryController.getCurrentInstance().insert(
                                new ActivityHistory(Support.dateToString(new Date(), Support.getDateTimeFormat()),
                                        StaticUser.getCurrentInstanceUser(), "Khóa", "Tài khoản", existingAccount.toString()));
                        setAccountDefault(null);
                    }
                } else {
                    Account existingAccount = AccountController.getCurrentInstance().getAccount(jtfusername.getText());
                    Account account = new Account(existingAccount);
                    existingAccount.setDeleteflag(false);
                    if (AccountController.getCurrentInstance().update(existingAccount)) {
                        MessageSupport.Message("Thông báo", "Tài khoản đã được mở khóa.");
                        ActivityHistoryController.getCurrentInstance().insert(
                                new ActivityHistory(Support.dateToString(new Date(), Support.getDateTimeFormat()),
                                        StaticUser.getCurrentInstanceUser(), "Mở khóa", "Tài khoản", account.toString()));
                        setAccountDefault(null);
                    }
                }
            } else {
                MessageSupport.ErrorMessage("Lỗi", "Xác nhận mật khẩu không đúng.");
            }
        } else {
            MessageSupport.ErrorMessage("Lỗi", "Mật khẩu của quản trị viên không đúng.");
        }
    }//GEN-LAST:event_jbtnLockOrUnlockActionPerformed

    private void jtblAccountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblAccountMouseClicked
        int row = jtblAccount.getSelectedRow();
        if (evt.getClickCount() == 2 && row != -1) {
            JTable table = (JTable) evt.getSource();
            String username = (table.getModel().getValueAt(row, 1)).toString();
            Account account = AccountController.getCurrentInstance().getAccount(username);
            setAccountDefault(account);
        }
    }//GEN-LAST:event_jtblAccountMouseClicked

    private void jbtnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAddActionPerformed

        Account account = getAccountFromForm();
        if (account != null) {
            Account existingAccount = AccountController.getCurrentInstance().getAccount(account.getUsername());
            if (existingAccount == null) {
                if (String.valueOf(jpfPassword.getPassword()).equals(StaticUser.getCurrentInstanceUser().getPassword())) {
                    if (String.valueOf(jpfPassword.getPassword()).equals(String.valueOf(jpfConfirmPassword.getPassword()))) {
                        if (AccountController.getCurrentInstance().insert(account)) {
                            MessageSupport.Message("Thông báo", "Thêm thành công. Mật khẩu mặc định là 1.");
                            ActivityHistoryController.getCurrentInstance().insert(
                                    new ActivityHistory(Support.dateToString(new Date(), Support.getDateTimeFormat()),
                                            StaticUser.getCurrentInstanceUser(), "Thêm mới", "Tài khoản", account.toString()));
                            setAccountDefault(null);
                        } else {
                            MessageSupport.Message("Thông báo", "Thêm thất bại.");
                            setAccountDefault(null);
                        }
                    } else {
                        MessageSupport.ErrorMessage("Lỗi", "Xác nhận mật khẩu không đúng.");
                    }
                } else {
                    MessageSupport.ErrorMessage("Lỗi", "Mật khẩu của quản trị viên không đúng.");
                }
            } else {
                MessageSupport.ErrorMessage("Lỗi", "Tên đăng nhập tồn tại.");
            }
        }
    }//GEN-LAST:event_jbtnAddActionPerformed

    private void jrbAccountStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbAccountStatusActionPerformed
        findAccount();
    }//GEN-LAST:event_jrbAccountStatusActionPerformed

    private void jbtnDeleteTabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDeleteTabActionPerformed
        HomePageJFrameForm.jHomePageTabbedPane.remove(HomePageJFrameForm.jHomePageTabbedPane.indexOfTab("Tài khoản"));
    }//GEN-LAST:event_jbtnDeleteTabActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JDateChooser jDCFromDate;
    private com.toedter.calendar.JDateChooser jDCToDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbtnAdd;
    private javax.swing.JButton jbtnDeleteTab;
    private javax.swing.JButton jbtnLockOrUnlock;
    private javax.swing.JButton jbtnReload;
    private javax.swing.JCheckBox jchbShowHirePassword;
    private javax.swing.JLabel jlbResetPassword;
    private javax.swing.JPasswordField jpfConfirmPassword;
    private javax.swing.JPasswordField jpfPassword;
    private javax.swing.JRadioButton jrbAll;
    private javax.swing.JRadioButton jrbLocked;
    private javax.swing.JRadioButton jrbUnlocked;
    private javax.swing.JTable jtblAccount;
    private javax.swing.JTable jtblActivityHistory;
    private javax.swing.JTextField jtfActivityKey;
    private javax.swing.JTextField jtfFullname;
    private javax.swing.JTextField jtfInforKey;
    private javax.swing.JTextField jtfObjectnameKey;
    private javax.swing.JTextField jtfUserFullName;
    private javax.swing.JTextField jtfUserName;
    private javax.swing.JTextField jtfUsernameKey;
    private javax.swing.JTextField jtfusername;
    // End of variables declaration//GEN-END:variables
}
