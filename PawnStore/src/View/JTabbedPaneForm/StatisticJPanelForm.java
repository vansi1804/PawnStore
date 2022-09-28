/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.JTabbedPaneForm;

import Controller.StatisticController;
import Support.MessageSupport;
import Support.Support;
import View.HomePageJFrameForm;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author NVS
 */
@SuppressWarnings("ClassWithoutLogger")
public class StatisticJPanelForm extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;

    private static final Date nullDefaultDateFrom = Support.stringToDate("01/01/1000", Support.getDateFormat());
    private static final Date nullDefaultDateTo = Support.stringToDate("31/12/9999", Support.getDateFormat());
    private static final Date defaultDateFrom = Support.stringToDate(
            String.valueOf(Calendar.getInstance().getActualMinimum(Calendar.DATE))
            + "/" + String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1)
            + "/2022",
            Support.getDateFormat());
    private static final Date defaultDateTo = Support.stringToDate(
            String.valueOf(Calendar.getInstance().getActualMaximum(Calendar.DATE))
            + "/" + String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1)
            + "/2022",
            Support.getDateFormat());

    public StatisticJPanelForm() {
        initComponents();
        jdcDateFrom.setDate(defaultDateFrom);
        jdcDateTo.setDate(defaultDateTo);
        setPawnCouponStatistics();
        setCustomerStatistics();
        setTypeOfProductStatistics();
        setDateFindValueChage();
    }

    private void setPawnCouponStatistics() {
        @SuppressWarnings({"CollectionWithoutInitialCapacity", "UnusedAssignment"})
        ArrayList<String> results = new ArrayList<>();
        if (jdcDateFrom.getDate() == null || jdcDateTo.getDate() == null) {
            results = StatisticController.getCurrentInstance()
                    .getPawnCouponStatistic(nullDefaultDateFrom,nullDefaultDateTo);
        } else {
            results = StatisticController.getCurrentInstance().getPawnCouponStatistic(jdcDateFrom.getDate(), jdcDateTo.getDate());
        }
        jlb1_2.setText(results.get(0));
        jlb1_3.setText(results.get(1));
        jlb2_2.setText(results.get(2));
        jlb2_3.setText(results.get(3));
        jlb3_2.setText(results.get(4));
        jlb3_3.setText(results.get(5));
        jlb4_2.setText(results.get(6));
        jlb4_3.setText(results.get(7));
        jlb4_4.setText(results.get(8));
        jlb5_2.setText(results.get(9));

    }

    private void setCustomerStatistics() {
        @SuppressWarnings({"CollectionWithoutInitialCapacity", "UnusedAssignment"})
        ArrayList<String> results = new ArrayList<>();
        if (jdcDateFrom.getDate() == null || jdcDateTo.getDate() == null) {
            results = StatisticController.getCurrentInstance().getCustomerStatistic(nullDefaultDateFrom,nullDefaultDateTo);
        } else {
            results = StatisticController.getCurrentInstance()
                    .getCustomerStatistic(jdcDateFrom.getDate(), jdcDateTo.getDate());
        }
        jlb6_2.setText(results.get(0));
        jlb7_2.setText(results.get(1));
        jlb7_3.setText(results.get(2));
        jlb7_4.setText(results.get(3));
        jlb7_5.setText(results.get(4));
        jlb8_2.setText(results.get(5));
    }

    @SuppressWarnings("UnusedAssignment")
    private void setTypeOfProductStatistics() {
        @SuppressWarnings({"CollectionWithoutInitialCapacity", "UnusedAssignment"})
        ArrayList<String> results = new ArrayList<>();
        if (jdcDateFrom.getDate() == null || jdcDateTo.getDate() == null) {
            results = StatisticController.getCurrentInstance().getTypeOfProductStatistic(nullDefaultDateFrom,nullDefaultDateTo);
        } else {
            results = StatisticController.getCurrentInstance()
                    .getTypeOfProductStatistic(jdcDateFrom.getDate(), jdcDateTo.getDate());
        }
        jlb9_3.setText(results.get(0));
        jlb9_2.setText(results.get(1));
        jlb10_2.setText(results.get(2));
        jlb10_3.setText(results.get(3));
        jlb10_4.setText(results.get(4));
        jlb10_5.setText(results.get(5));
        jlb11_2.setText(results.get(6));

    }

    private void setDateFindValueChage() {
        jdcDateFrom.addPropertyChangeListener((PropertyChangeEvent evt) -> {
            if (jdcDateFrom.getDate() != null && jdcDateTo.getDate() != null) {
                if (jdcDateFrom.getDate().compareTo(jdcDateTo.getDate()) > 0) {
                    MessageSupport.Message("Lỗi", "Ngày tìm kiếm 1 không được sau ngày tìm kiếm 2.");
                } else {
                    setPawnCouponStatistics();
                    setCustomerStatistics();
                    setTypeOfProductStatistics();
                }
            }
        });

        jdcDateTo.addPropertyChangeListener((PropertyChangeEvent evt) -> {
            if (jdcDateFrom.getDate() != null && jdcDateTo.getDate() != null) {
                if (jdcDateTo.getDate().compareTo(jdcDateFrom.getDate()) < 0) {
                    MessageSupport.Message("Lỗi", "Ngày tìm kiếm 2 không được sau ngày tìm kiếm 1.");
                } else {
                    setPawnCouponStatistics();
                    setCustomerStatistics();
                    setTypeOfProductStatistics();
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel7 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jbtnDelete = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jlb1_1 = new javax.swing.JLabel();
        jlb1_2 = new javax.swing.JLabel();
        jlb1_3 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jlb2_2 = new javax.swing.JLabel();
        jlb2_1 = new javax.swing.JLabel();
        jlb2_3 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jlb3_1 = new javax.swing.JLabel();
        jlb3_3 = new javax.swing.JLabel();
        jlb3_2 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jlb4_1 = new javax.swing.JLabel();
        jlb4_2 = new javax.swing.JLabel();
        jlb4_3 = new javax.swing.JLabel();
        jlb4_4 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jlb5_1 = new javax.swing.JLabel();
        jlb5_2 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jlb6_1 = new javax.swing.JLabel();
        jlb6_2 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jlb8_1 = new javax.swing.JLabel();
        jlb8_2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jlb7_1 = new javax.swing.JLabel();
        jlb7_2 = new javax.swing.JLabel();
        jlb7_3 = new javax.swing.JLabel();
        jlb7_4 = new javax.swing.JLabel();
        jlb7_5 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jlb9_1 = new javax.swing.JLabel();
        jlb9_3 = new javax.swing.JLabel();
        jlb9_2 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jlb11_1 = new javax.swing.JLabel();
        jlb11_2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jlb10_1 = new javax.swing.JLabel();
        jlb10_2 = new javax.swing.JLabel();
        jlb10_3 = new javax.swing.JLabel();
        jlb10_4 = new javax.swing.JLabel();
        jlb10_5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jdcDateFrom = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jdcDateTo = new com.toedter.calendar.JDateChooser();
        jchbAll = new javax.swing.JCheckBox();
        jbtnReload = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel14.setText("jLabel14");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jButton1.setText("jButton1");

        setBackground(new java.awt.Color(0, 255, 255));

        jPanel1.setBackground(new java.awt.Color(0, 255, 255));

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("THỐNG KÊ");

        jbtnDelete.setFont(new java.awt.Font("Dialog", 1, 35)); // NOI18N
        jbtnDelete.setForeground(new java.awt.Color(255, 0, 0));
        jbtnDelete.setText("X");
        jbtnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnDelete)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbtnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(0, 255, 255));

        jPanel8.setBackground(new java.awt.Color(204, 204, 204));

        jLabel5.setBackground(new java.awt.Color(0, 255, 255));
        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Hợp Đồng");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
        );

        jPanel11.setBackground(new java.awt.Color(204, 204, 204));

        jLabel6.setBackground(new java.awt.Color(0, 255, 255));
        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Khách hàng");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
        );

        jPanel12.setBackground(new java.awt.Color(204, 204, 204));

        jLabel8.setBackground(new java.awt.Color(0, 255, 255));
        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Loại hàng hóa");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(0, 255, 255));

        jPanel13.setBackground(new java.awt.Color(0, 0, 204));

        jlb1_1.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jlb1_1.setForeground(new java.awt.Color(255, 255, 255));
        jlb1_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb1_1.setText("Mới");

        jlb1_2.setFont(new java.awt.Font("Times New Roman", 3, 20)); // NOI18N
        jlb1_2.setForeground(new java.awt.Color(255, 255, 255));
        jlb1_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb1_2.setText("Số lượng");

        jlb1_3.setFont(new java.awt.Font("Times New Roman", 3, 25)); // NOI18N
        jlb1_3.setForeground(new java.awt.Color(255, 255, 255));
        jlb1_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb1_3.setText("Giá cầm");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlb1_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jlb1_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jlb1_3, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addComponent(jlb1_1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlb1_2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlb1_3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel15.setBackground(new java.awt.Color(204, 0, 204));

        jlb2_2.setFont(new java.awt.Font("Times New Roman", 3, 20)); // NOI18N
        jlb2_2.setForeground(new java.awt.Color(255, 255, 255));
        jlb2_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb2_2.setText("Số lượng");

        jlb2_1.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jlb2_1.setForeground(new java.awt.Color(255, 255, 255));
        jlb2_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb2_1.setText("Chưa chuộc");

        jlb2_3.setFont(new java.awt.Font("Times New Roman", 3, 25)); // NOI18N
        jlb2_3.setForeground(new java.awt.Color(255, 255, 255));
        jlb2_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb2_3.setText("Giá cầm");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlb2_1, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
            .addComponent(jlb2_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jlb2_3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addComponent(jlb2_1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlb2_2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlb2_3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel16.setBackground(new java.awt.Color(255, 0, 0));

        jlb3_1.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jlb3_1.setForeground(new java.awt.Color(255, 255, 255));
        jlb3_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb3_1.setText("Đã chuộc");

        jlb3_3.setFont(new java.awt.Font("Times New Roman", 3, 25)); // NOI18N
        jlb3_3.setForeground(new java.awt.Color(255, 255, 255));
        jlb3_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb3_3.setText("Giá chuộc");

        jlb3_2.setFont(new java.awt.Font("Times New Roman", 3, 20)); // NOI18N
        jlb3_2.setForeground(new java.awt.Color(255, 255, 255));
        jlb3_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb3_2.setText("Số lượng");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlb3_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jlb3_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jlb3_3, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addComponent(jlb3_1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlb3_2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlb3_3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel17.setBackground(new java.awt.Color(204, 204, 0));

        jlb4_1.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jlb4_1.setForeground(new java.awt.Color(255, 255, 255));
        jlb4_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb4_1.setText("Đã thanh lý");

        jlb4_2.setFont(new java.awt.Font("Times New Roman", 3, 20)); // NOI18N
        jlb4_2.setForeground(new java.awt.Color(255, 255, 255));
        jlb4_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb4_2.setText("Số lượng");

        jlb4_3.setFont(new java.awt.Font("Times New Roman", 3, 25)); // NOI18N
        jlb4_3.setForeground(new java.awt.Color(255, 255, 255));
        jlb4_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb4_3.setText("Giá cầm");

        jlb4_4.setFont(new java.awt.Font("Times New Roman", 3, 25)); // NOI18N
        jlb4_4.setForeground(new java.awt.Color(255, 255, 255));
        jlb4_4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb4_4.setText("Giá TL");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlb4_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jlb4_3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlb4_4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jlb4_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addComponent(jlb4_1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlb4_2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlb4_3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlb4_4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel18.setBackground(new java.awt.Color(0, 204, 51));

        jlb5_1.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jlb5_1.setForeground(new java.awt.Color(255, 255, 255));
        jlb5_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb5_1.setText("Lãi");

        jlb5_2.setFont(new java.awt.Font("Times New Roman", 3, 30)); // NOI18N
        jlb5_2.setForeground(new java.awt.Color(255, 255, 255));
        jlb5_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb5_2.setText("Lãi");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlb5_2, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
            .addComponent(jlb5_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addComponent(jlb5_1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlb5_2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel19.setBackground(new java.awt.Color(0, 0, 204));

        jlb6_1.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jlb6_1.setForeground(new java.awt.Color(255, 255, 255));
        jlb6_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb6_1.setText("Mới");

        jlb6_2.setFont(new java.awt.Font("Times New Roman", 3, 25)); // NOI18N
        jlb6_2.setForeground(new java.awt.Color(255, 255, 255));
        jlb6_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb6_2.setText("Số lượng");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlb6_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jlb6_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addComponent(jlb6_1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlb6_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel20.setBackground(new java.awt.Color(204, 0, 0));

        jlb8_1.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jlb8_1.setForeground(new java.awt.Color(255, 255, 255));
        jlb8_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb8_1.setText("Ngưng phục vụ");

        jlb8_2.setFont(new java.awt.Font("Times New Roman", 3, 30)); // NOI18N
        jlb8_2.setForeground(new java.awt.Color(255, 255, 255));
        jlb8_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb8_2.setText("Số lượng");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlb8_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jlb8_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addComponent(jlb8_1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlb8_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        jPanel6.setBackground(new java.awt.Color(0, 204, 204));

        jlb7_1.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jlb7_1.setForeground(new java.awt.Color(255, 255, 255));
        jlb7_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb7_1.setText("Cầm nhiều");

        jlb7_2.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jlb7_2.setForeground(new java.awt.Color(255, 255, 255));
        jlb7_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb7_2.setText("Tên khách hàng");

        jlb7_3.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jlb7_3.setForeground(new java.awt.Color(255, 255, 255));
        jlb7_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb7_3.setText("Số lần cầm");

        jlb7_4.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        jlb7_4.setForeground(new java.awt.Color(255, 255, 255));
        jlb7_4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb7_4.setText("Tổng giá cầm");

        jlb7_5.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        jlb7_5.setForeground(new java.awt.Color(255, 255, 255));
        jlb7_5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb7_5.setText("Tổng lãi");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlb7_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jlb7_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jlb7_3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jlb7_4, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlb7_5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jlb7_1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlb7_2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlb7_3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlb7_4, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                    .addComponent(jlb7_5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel21.setBackground(new java.awt.Color(0, 0, 204));

        jlb9_1.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jlb9_1.setForeground(new java.awt.Color(255, 255, 255));
        jlb9_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb9_1.setText("Mới");

        jlb9_3.setFont(new java.awt.Font("Times New Roman", 3, 30)); // NOI18N
        jlb9_3.setForeground(new java.awt.Color(255, 255, 255));
        jlb9_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb9_3.setText("Số lượng");

        jlb9_2.setFont(new java.awt.Font("Times New Roman", 3, 30)); // NOI18N
        jlb9_2.setForeground(new java.awt.Color(255, 255, 255));
        jlb9_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb9_2.setText("Số lượng");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlb9_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jlb9_2, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
            .addComponent(jlb9_3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addComponent(jlb9_1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlb9_2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlb9_3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel22.setBackground(new java.awt.Color(204, 0, 0));

        jlb11_1.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jlb11_1.setForeground(new java.awt.Color(255, 255, 255));
        jlb11_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb11_1.setText("Ngưng phục vụ");

        jlb11_2.setFont(new java.awt.Font("Times New Roman", 3, 30)); // NOI18N
        jlb11_2.setForeground(new java.awt.Color(255, 255, 255));
        jlb11_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb11_2.setText("Số lượng");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlb11_1, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
            .addComponent(jlb11_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addComponent(jlb11_1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlb11_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(0, 204, 204));

        jlb10_1.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jlb10_1.setForeground(new java.awt.Color(255, 255, 255));
        jlb10_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb10_1.setText("Cầm nhiều");

        jlb10_2.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jlb10_2.setForeground(new java.awt.Color(255, 255, 255));
        jlb10_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb10_2.setText("Tên loại");

        jlb10_3.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jlb10_3.setForeground(new java.awt.Color(255, 255, 255));
        jlb10_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb10_3.setText("Số lần cầm");

        jlb10_4.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        jlb10_4.setForeground(new java.awt.Color(255, 255, 255));
        jlb10_4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb10_4.setText("Tổng giá cầm");

        jlb10_5.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        jlb10_5.setForeground(new java.awt.Color(255, 255, 255));
        jlb10_5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb10_5.setText("Tổng lãi");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlb10_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jlb10_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jlb10_3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jlb10_4, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlb10_5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jlb10_1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlb10_2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlb10_3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlb10_4, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                    .addComponent(jlb10_5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, 150, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Từ ngày");

        jdcDateFrom.setBackground(new java.awt.Color(0, 255, 255));
        jdcDateFrom.setForeground(new java.awt.Color(0, 0, 0));
        jdcDateFrom.setDateFormatString("dd/MM/yyyy");
        jdcDateFrom.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Đến ngày");

        jdcDateTo.setBackground(new java.awt.Color(0, 255, 255));
        jdcDateTo.setForeground(new java.awt.Color(0, 0, 0));
        jdcDateTo.setDateFormatString("dd/MM/yyyy");
        jdcDateTo.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N

        jchbAll.setBackground(new java.awt.Color(0, 255, 255));
        jchbAll.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jchbAll.setForeground(new java.awt.Color(0, 0, 0));
        jchbAll.setText("Tất cả");
        jchbAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jchbAllActionPerformed(evt);
            }
        });

        jbtnReload.setBackground(new java.awt.Color(0, 255, 255));
        jbtnReload.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jbtnReload.setForeground(new java.awt.Color(0, 0, 0));
        jbtnReload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/reload48x48.png"))); // NOI18N
        jbtnReload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnReloadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcDateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jdcDateTo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jchbAll)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnReload, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jdcDateTo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jchbAll, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jbtnReload, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jdcDateFrom, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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

    @SuppressWarnings({"deprecation"})
    private void jchbAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jchbAllActionPerformed
        if (!jchbAll.isSelected()) {
            jdcDateFrom.setEnabled(true);
            jdcDateFrom.setDate(defaultDateFrom);
            jdcDateTo.setEnabled(true);
            jdcDateTo.setDate(defaultDateTo);
            jlb1_1.setText("Mới");
            jlb6_1.setText("Mới");
            jlb9_1.setText("Mới");
        } else {
            jdcDateFrom.setEnabled(false);
            jdcDateFrom.setDate(null);
            jdcDateTo.setEnabled(false);
            jdcDateTo.setDate(null);
            jlb1_1.setText("Tổng");
            jlb6_1.setText("Tổng");
            jlb9_1.setText("Tổng");
        }
    }//GEN-LAST:event_jchbAllActionPerformed

    private void jbtnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDeleteActionPerformed
        HomePageJFrameForm.jHomePageTabbedPane.remove(HomePageJFrameForm.jHomePageTabbedPane.indexOfTab("Thống kê"));
    }//GEN-LAST:event_jbtnDeleteActionPerformed

    private void jbtnReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnReloadActionPerformed
        setPawnCouponStatistics();
        setCustomerStatistics();
        setTypeOfProductStatistics();
    }//GEN-LAST:event_jbtnReloadActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JButton jbtnDelete;
    private javax.swing.JButton jbtnReload;
    private javax.swing.JCheckBox jchbAll;
    private com.toedter.calendar.JDateChooser jdcDateFrom;
    private com.toedter.calendar.JDateChooser jdcDateTo;
    private javax.swing.JLabel jlb10_1;
    private javax.swing.JLabel jlb10_2;
    private javax.swing.JLabel jlb10_3;
    private javax.swing.JLabel jlb10_4;
    private javax.swing.JLabel jlb10_5;
    private javax.swing.JLabel jlb11_1;
    private javax.swing.JLabel jlb11_2;
    private javax.swing.JLabel jlb1_1;
    private javax.swing.JLabel jlb1_2;
    private javax.swing.JLabel jlb1_3;
    private javax.swing.JLabel jlb2_1;
    private javax.swing.JLabel jlb2_2;
    private javax.swing.JLabel jlb2_3;
    private javax.swing.JLabel jlb3_1;
    private javax.swing.JLabel jlb3_2;
    private javax.swing.JLabel jlb3_3;
    private javax.swing.JLabel jlb4_1;
    private javax.swing.JLabel jlb4_2;
    private javax.swing.JLabel jlb4_3;
    private javax.swing.JLabel jlb4_4;
    private javax.swing.JLabel jlb5_1;
    private javax.swing.JLabel jlb5_2;
    private javax.swing.JLabel jlb6_1;
    private javax.swing.JLabel jlb6_2;
    private javax.swing.JLabel jlb7_1;
    private javax.swing.JLabel jlb7_2;
    private javax.swing.JLabel jlb7_3;
    private javax.swing.JLabel jlb7_4;
    private javax.swing.JLabel jlb7_5;
    private javax.swing.JLabel jlb8_1;
    private javax.swing.JLabel jlb8_2;
    private javax.swing.JLabel jlb9_1;
    private javax.swing.JLabel jlb9_2;
    private javax.swing.JLabel jlb9_3;
    // End of variables declaration//GEN-END:variables
}
