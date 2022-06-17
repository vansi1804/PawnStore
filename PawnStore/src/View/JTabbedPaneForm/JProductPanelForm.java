/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to editTypeOfProduct this template
 */
package View.JTabbedPaneForm;

import Controller.ProductController;
import Model.Product;
import Model.TypeOfProduct;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import Support.*;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author NVS
 */
public class JProductPanelForm extends javax.swing.JPanel {

    ProductController _productController = new ProductController();
    public static String ID = null;

    public JProductPanelForm() {
        initComponents();
        loadTypeOfProduct();
        loadProduct(_productController.getProductList());
        loadCombobox();
        setProductDefault();
        setTypeOfProductDefault();
        Support.FormatTableHeader(jtblProduct);
        Support.FormatTableHeader(jtblTypeOfProduct);
    }

    public void setProductDefault() {
        jtfProductID.setEditable(true);
        jtfProductID.setText("");
        jtfProductName.setText("");
        jtaInformation.setText("");
        jrbAll.setSelected(true);
        jcbTypeOfProduct.setSelectedIndex(0);
    }

    public void setTypeOfProductDefault() {
        jtfTypeOfProductID.setEditable(true);
        jtfTypeOfProductID.setText("");
        jtfTypeOfProductName.setText("");
    }

    public void loadCombobox() {
        ArrayList<TypeOfProduct> _typeOfProductsList = _productController.getTypeOfProductList();
        ArrayList<String> _typeOfProductsNameList = new ArrayList<>();
        for (TypeOfProduct typeOfProduct : _typeOfProductsList) {
            _typeOfProductsNameList.add(typeOfProduct.getTypeOfProductName());
        }
        Support.loadCombobox(_typeOfProductsNameList, jcbTypeOfProduct);
    }

    public void loadProduct(ArrayList<Product> list) {
        DefaultTableModel model = (DefaultTableModel) jtblProduct.getModel();
        model.setRowCount(0);
        Object rowData[] = new Object[6];
        int STT = 1;
        for (int i = 0; i < list.size(); i++) {
            rowData[0] = String.valueOf(STT++);
            rowData[1] = list.get(i).getProductID();
            rowData[2] = list.get(i).getProductName();
            rowData[3] = list.get(i).getTypeOfProductName();
            rowData[4] = list.get(i).getInformation();
            rowData[5] = list.get(i).getStatus();
            model.addRow(rowData);
        }
        Support.setDataTableCenter(jtblProduct);
    }

    public void loadTypeOfProduct() {
        DefaultTableModel model = (DefaultTableModel) jtblTypeOfProduct.getModel();
        model.setRowCount(0);
        ArrayList<TypeOfProduct> _typeOfProductsList = _productController.getTypeOfProductList();
        Object rowData[] = new Object[3];
        int STT = 1;
        for (int i = 0; i < _typeOfProductsList.size(); i++) {
            rowData[0] = String.valueOf(STT++);
            rowData[1] = _typeOfProductsList.get(i).getTypeOfProductID();
            rowData[2] = _typeOfProductsList.get(i).getTypeOfProductName();
            model.addRow(rowData);
        }
        Support.setDataTableCenter(jtblTypeOfProduct);
    }

    public String getComboBoxValue() {
        return jcbTypeOfProduct.getSelectedItem().toString();
    }

    public void setStatus(String status) {
        if (CheckSupport.equals(status, "Chưa chuộc")) {
            jrbNotRedeemed.setSelected(true);
        } else if (CheckSupport.equals(status, "Đã chuộc")) {
            jrbRedeemed.setSelected(true);
        } else if (CheckSupport.equals(status, "Trễ")) {
            jrbLate.setSelected(true);
        } else if (CheckSupport.equals(status, "Cần thanh lý")) {
            jrbNeedToLiquidate.setSelected(true);
        } else if (CheckSupport.equals(status, "Đã thanh lý")) {
            jrbLiquidated.setSelected(true);
        } else {
            jrbAll.setSelected(true);
        }
    }

    public String getStatus() {
        if (jrbNotRedeemed.isSelected()) {
            return "Chưa chuộc";
        } else if (jrbRedeemed.isSelected()) {
            return "Đã chuộc";
        } else if (jrbLate.isSelected()) {
            return "Trễ";
        } else if (jrbNeedToLiquidate.isSelected()) {
            return "Cần thanh lý";
        } else if (jrbLiquidated.isSelected()) {
            return "Đã thanh lý";
        } else {
            return "";
        }
    }

    public TypeOfProduct getTypeOfProductFromForm() {
        return new TypeOfProduct(jtfTypeOfProductID.getText(), jtfTypeOfProductName.getText());
    }

    public Product getProductFromForm() {
        TypeOfProduct typeOfProduct;
        if (CheckSupport.equals(jcbTypeOfProduct.getSelectedItem().toString(), "Tất cả")) {
            typeOfProduct = new TypeOfProduct(" ", " ");
        } else {
            typeOfProduct = _productController.findTypeOfProduct("_name", getComboBoxValue());
        }
        return new Product(jtfProductID.getText(), jtfProductName.getText(), jtaInformation.getText(), getStatus(), typeOfProduct);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jtfProductName = new javax.swing.JTextField();
        jtfProductID = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtaInformation = new javax.swing.JTextArea();
        jcbTypeOfProduct = new javax.swing.JComboBox<>();
        jbtnAddNew = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jrbRedeemed = new javax.swing.JRadioButton();
        jrbLiquidated = new javax.swing.JRadioButton();
        jrbNotRedeemed = new javax.swing.JRadioButton();
        jrbNeedToLiquidate = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        jrbAll = new javax.swing.JRadioButton();
        jrbLate = new javax.swing.JRadioButton();
        jbtnEditProduct = new javax.swing.JButton();
        jbtnAddProduct = new javax.swing.JButton();
        jbtnFindProduct = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtblTypeOfProduct = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jtfTypeOfProductName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jtfTypeOfProductID = new javax.swing.JTextField();
        jbtnEditTypeOfProduct = new javax.swing.JButton();
        jbtnAddTypeOfProduct = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jbtnAddNew1 = new javax.swing.JButton();
        jbtnReloadAll = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblProduct = new javax.swing.JTable();

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setForeground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("HÀNG HÓA");

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

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));
        jPanel3.setForeground(new java.awt.Color(0, 0, 0));

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Thông tin hàng hóa");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Tên hàng hóa");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Loại hàng hóa");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Thông tin chi tiết");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Mã hàng hóa");

        jtfProductName.setBackground(new java.awt.Color(255, 255, 255));
        jtfProductName.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jtfProductName.setForeground(new java.awt.Color(0, 0, 0));

        jtfProductID.setBackground(new java.awt.Color(255, 255, 255));
        jtfProductID.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jtfProductID.setForeground(new java.awt.Color(0, 0, 0));

        jtaInformation.setBackground(new java.awt.Color(255, 255, 255));
        jtaInformation.setColumns(10);
        jtaInformation.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jtaInformation.setForeground(new java.awt.Color(0, 0, 0));
        jtaInformation.setRows(5);
        jtaInformation.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane3.setViewportView(jtaInformation);

        jcbTypeOfProduct.setBackground(new java.awt.Color(255, 255, 255));
        jcbTypeOfProduct.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jcbTypeOfProduct.setForeground(new java.awt.Color(0, 0, 0));
        jcbTypeOfProduct.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jbtnAddNew.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jbtnAddNew.setForeground(new java.awt.Color(0, 0, 0));
        jbtnAddNew.setText("Tạo mới");
        jbtnAddNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAddNewActionPerformed(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));

        jrbRedeemed.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(jrbRedeemed);
        jrbRedeemed.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        jrbRedeemed.setForeground(new java.awt.Color(0, 0, 0));
        jrbRedeemed.setText("Đã chuộc");

        jrbLiquidated.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(jrbLiquidated);
        jrbLiquidated.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        jrbLiquidated.setForeground(new java.awt.Color(0, 0, 0));
        jrbLiquidated.setText("Đã thanh lý");

        jrbNotRedeemed.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(jrbNotRedeemed);
        jrbNotRedeemed.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        jrbNotRedeemed.setForeground(new java.awt.Color(0, 0, 0));
        jrbNotRedeemed.setText("Chưa chuộc");

        jrbNeedToLiquidate.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(jrbNeedToLiquidate);
        jrbNeedToLiquidate.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        jrbNeedToLiquidate.setForeground(new java.awt.Color(0, 0, 0));
        jrbNeedToLiquidate.setText("Cần thanh lý");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Trạng thái");

        jrbAll.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(jrbAll);
        jrbAll.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        jrbAll.setForeground(new java.awt.Color(0, 0, 0));
        jrbAll.setText("Tất cả");

        jrbLate.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(jrbLate);
        jrbLate.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        jrbLate.setForeground(new java.awt.Color(0, 0, 0));
        jrbLate.setText("Trễ");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jrbNeedToLiquidate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jrbLiquidated, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jrbAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jrbLate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jrbRedeemed, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jrbNotRedeemed, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(132, 132, 132))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrbNotRedeemed)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrbRedeemed)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrbLate)
                .addGap(3, 3, 3)
                .addComponent(jrbNeedToLiquidate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrbLiquidated)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jrbAll))
        );

        jbtnEditProduct.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jbtnEditProduct.setForeground(new java.awt.Color(0, 0, 0));
        jbtnEditProduct.setText("Sửa");
        jbtnEditProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEditProductActionPerformed(evt);
            }
        });

        jbtnAddProduct.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jbtnAddProduct.setForeground(new java.awt.Color(0, 0, 0));
        jbtnAddProduct.setText("Thêm");
        jbtnAddProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAddProductActionPerformed(evt);
            }
        });

        jbtnFindProduct.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jbtnFindProduct.setForeground(new java.awt.Color(0, 0, 0));
        jbtnFindProduct.setText("Tìm");
        jbtnFindProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnFindProductActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfProductID))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jbtnAddProduct)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtnEditProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtnFindProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbTypeOfProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtnAddNew)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfProductID, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnAddNew, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbTypeOfProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane3)))
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnAddProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnEditProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnFindProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));
        jPanel5.setForeground(new java.awt.Color(204, 204, 204));

        jtblTypeOfProduct.setBackground(new java.awt.Color(255, 255, 255));
        jtblTypeOfProduct.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jtblTypeOfProduct.setForeground(new java.awt.Color(0, 0, 0));
        jtblTypeOfProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã loại", "Tên loại"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblTypeOfProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblTypeOfProductMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jtblTypeOfProduct);
        if (jtblTypeOfProduct.getColumnModel().getColumnCount() > 0) {
            jtblTypeOfProduct.getColumnModel().getColumn(0).setMinWidth(50);
            jtblTypeOfProduct.getColumnModel().getColumn(0).setPreferredWidth(50);
            jtblTypeOfProduct.getColumnModel().getColumn(0).setMaxWidth(50);
            jtblTypeOfProduct.getColumnModel().getColumn(1).setMinWidth(100);
            jtblTypeOfProduct.getColumnModel().getColumn(1).setPreferredWidth(100);
            jtblTypeOfProduct.getColumnModel().getColumn(1).setMaxWidth(100);
        }

        jLabel3.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Mã loại");

        jtfTypeOfProductName.setBackground(new java.awt.Color(255, 255, 255));
        jtfTypeOfProductName.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jtfTypeOfProductName.setForeground(new java.awt.Color(0, 0, 0));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Tên loại");

        jtfTypeOfProductID.setBackground(new java.awt.Color(255, 255, 255));
        jtfTypeOfProductID.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jtfTypeOfProductID.setForeground(new java.awt.Color(0, 0, 0));

        jbtnEditTypeOfProduct.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jbtnEditTypeOfProduct.setForeground(new java.awt.Color(0, 0, 0));
        jbtnEditTypeOfProduct.setText("Sửa");
        jbtnEditTypeOfProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEditTypeOfProductActionPerformed(evt);
            }
        });

        jbtnAddTypeOfProduct.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jbtnAddTypeOfProduct.setForeground(new java.awt.Color(0, 0, 0));
        jbtnAddTypeOfProduct.setText("Thêm");
        jbtnAddTypeOfProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAddTypeOfProductActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Loại hàng hóa");

        jbtnAddNew1.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jbtnAddNew1.setForeground(new java.awt.Color(0, 0, 0));
        jbtnAddNew1.setText("Tạo mới");
        jbtnAddNew1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAddNew1ActionPerformed(evt);
            }
        });

        jbtnReloadAll.setForeground(new java.awt.Color(0, 0, 0));
        jbtnReloadAll.setText("Tải lại");
        jbtnReloadAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnReloadAllActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jtfTypeOfProductName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                                    .addComponent(jtfTypeOfProductID, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(7, 7, 7)
                                .addComponent(jbtnAddNew1))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jbtnAddTypeOfProduct)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtnEditTypeOfProduct)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbtnReloadAll, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnReloadAll, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfTypeOfProductID, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtnAddNew1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfTypeOfProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbtnAddTypeOfProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtnEditTypeOfProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));
        jPanel6.setForeground(new java.awt.Color(204, 204, 204));

        jtblProduct.setBackground(new java.awt.Color(255, 255, 255));
        jtblProduct.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jtblProduct.setForeground(new java.awt.Color(0, 0, 0));
        jtblProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã hàng hóa", "Tên hàng hóa", "Loại hàng", "Thông tin chi tiết", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblProductMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtblProduct);
        if (jtblProduct.getColumnModel().getColumnCount() > 0) {
            jtblProduct.getColumnModel().getColumn(0).setMinWidth(50);
            jtblProduct.getColumnModel().getColumn(0).setPreferredWidth(50);
            jtblProduct.getColumnModel().getColumn(0).setMaxWidth(50);
            jtblProduct.getColumnModel().getColumn(1).setMinWidth(150);
            jtblProduct.getColumnModel().getColumn(1).setPreferredWidth(150);
            jtblProduct.getColumnModel().getColumn(1).setMaxWidth(150);
            jtblProduct.getColumnModel().getColumn(2).setMinWidth(200);
            jtblProduct.getColumnModel().getColumn(2).setPreferredWidth(200);
            jtblProduct.getColumnModel().getColumn(2).setMaxWidth(200);
            jtblProduct.getColumnModel().getColumn(3).setMinWidth(200);
            jtblProduct.getColumnModel().getColumn(3).setPreferredWidth(200);
            jtblProduct.getColumnModel().getColumn(3).setMaxWidth(200);
            jtblProduct.getColumnModel().getColumn(5).setMinWidth(200);
            jtblProduct.getColumnModel().getColumn(5).setPreferredWidth(200);
            jtblProduct.getColumnModel().getColumn(5).setMaxWidth(200);
        }

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void jtblTypeOfProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblTypeOfProductMouseClicked

        int row = jtblTypeOfProduct.getSelectedRow();
        if (evt.getClickCount() == 2 && row != -1) {
            JTable table = (JTable) evt.getSource();
            jtfTypeOfProductID.setText((table.getModel().getValueAt(row, 1)).toString());
            jtfTypeOfProductName.setText((table.getModel().getValueAt(row, 2)).toString());
        }
        ID = jtfTypeOfProductID.getText();
    }//GEN-LAST:event_jtblTypeOfProductMouseClicked

    private void jtblProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblProductMouseClicked

        int row = jtblProduct.getSelectedRow();
        if (evt.getClickCount() == 2 && row != -1) {
            jtfProductID.setEditable(false);
            JTable table = (JTable) evt.getSource();
            jtfProductID.setText((table.getModel().getValueAt(row, 1)).toString());
            jtfProductName.setText((table.getModel().getValueAt(row, 2)).toString());
            jcbTypeOfProduct.getModel().setSelectedItem((table.getModel().getValueAt(row, 3)).toString());
            jtaInformation.setText((table.getModel().getValueAt(row, 4)).toString());
            String status = (table.getModel().getValueAt(row, 5)).toString();
            setStatus(status);
        }
    }//GEN-LAST:event_jtblProductMouseClicked

    private void jbtnAddTypeOfProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAddTypeOfProductActionPerformed
        TypeOfProduct _typeOfProduct = getTypeOfProductFromForm();
        if (CheckSupport.isEmpty(_typeOfProduct.getTypeOfProductID())) {
            MessageSupport.ShowError(null, "Lỗi", "Mã loại hàng không được để trống.");
        } else {
            if (_productController.findTypeOfProduct("_id", _typeOfProduct.getTypeOfProductID()) != null) {
                MessageSupport.ShowError(null, "Lỗi", "Mã loại hàng đã tồn tại.");
            } else {
                if (CheckSupport.isEmpty(_typeOfProduct.getTypeOfProductName())) {
                    MessageSupport.ShowError(null, "Lỗi", "Tên hàng hóa không được để trống.");
                } else {
                    if (_productController.addTypeOfProduct(_typeOfProduct)) {
                        MessageSupport.Show(null, "Thông báo", "Thêm mới thành công..");
                        setTypeOfProductDefault();
                        loadTypeOfProduct();
                    }
                }
            }
        }
    }//GEN-LAST:event_jbtnAddTypeOfProductActionPerformed

    private void jbtnAddNew1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAddNew1ActionPerformed
        setTypeOfProductDefault();
    }//GEN-LAST:event_jbtnAddNew1ActionPerformed

    private void jbtnEditTypeOfProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEditTypeOfProductActionPerformed
        TypeOfProduct _typeOfProduct = getTypeOfProductFromForm();
        if (CheckSupport.isEmpty(_typeOfProduct.getTypeOfProductID())) {
            MessageSupport.ShowError(null, "Lỗi", "Mã loại hàng không được để trống.");
        } else {
            if (CheckSupport.isEmpty(_typeOfProduct.getTypeOfProductName())) {
                MessageSupport.ShowError(null, "Lỗi", "Tên loại hàng hóa không được để trống.");
            } else {
                if (ID == null) {
                    if (_productController.findTypeOfProduct("_id", _typeOfProduct.getTypeOfProductID()) == null) {
                        if (MessageSupport.Confirm(null, "Thông báo", "Mã loại hàng chưa tồn tại. Bạn có muốn thêm mới không?") == JOptionPane.YES_OPTION) {
                            if (_productController.addTypeOfProduct(_typeOfProduct)) {
                                MessageSupport.Show(null, "Thông báo", "Thêm mới thành công..");
                                setTypeOfProductDefault();
                                loadTypeOfProduct();
                            }
                        }
                    }
                } else {
                    if (!CheckSupport.equals(_typeOfProduct.getTypeOfProductID(), ID)
                            && _productController.findTypeOfProduct("_id", _typeOfProduct.getTypeOfProductID()) != null) {
                        MessageSupport.ShowError(null, "Lỗi", "Mã loại hàng hóa đã tồn tại.");
                    } else if (_productController.editTypeOfProduct(_typeOfProduct, ID)) {
                        MessageSupport.Show(null, "Thông báo", "Sửa thành công.");
                        setTypeOfProductDefault();
                        loadTypeOfProduct();
                        ID = null;
                    }
                }
            }
        }
    }//GEN-LAST:event_jbtnEditTypeOfProductActionPerformed

    private void jbtnAddNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAddNewActionPerformed
        setProductDefault();
        jrbNotRedeemed.setSelected(true);
    }//GEN-LAST:event_jbtnAddNewActionPerformed

    private void jbtnAddProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAddProductActionPerformed
        Product _product = getProductFromForm();
        if (CheckSupport.isEmpty(_product.getProductID())) {
            MessageSupport.ShowError(null, "Lỗi", "Tên hàng hóa không được để trống.");
        } else {
            if (_productController.findProductByID(_product.getProductID()) != null) {
                MessageSupport.ShowError(null, "Lỗi", "Mã hàng tồn tại.");
            } else {
                if (CheckSupport.isEmpty(_product.getTypeOfProductName())) {
                    MessageSupport.ShowError(null, "Lỗi", "Chọn loại hàng hóa.");
                } else {
                    if (_productController.addProduct(_product)) {
                        MessageSupport.Show(null, "Thông báo", "Thêm thành công.");
                        setProductDefault();
                        loadProduct(_productController.getProductList());
                    }
                }
            }
        }
    }//GEN-LAST:event_jbtnAddProductActionPerformed

    private void jbtnEditProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEditProductActionPerformed
        Product _product = getProductFromForm();
        if (CheckSupport.isEmpty(_product.getProductID())) {
            MessageSupport.ShowError(null, "Lỗi", "Tên hàng hóa không được để trống.");
        } else {
            if (CheckSupport.isEmpty(_product.getTypeOfProductName())) {
                MessageSupport.ShowError(null, "Lỗi", "Chọn loại hàng hóa.");
                
            } else if (!CheckSupport.equals(_productController.findProductByID(_product.getProductID()).getStatus(), getStatus())) {
                MessageSupport.ShowError(null, "Lỗi", "Không thể sửa trạng thái của sản phầm. Trạng thái của sản phẩm phụ thuộc vào trạng thái của hợp đồng");
                setStatus(_productController.findProductByID(_product.getProductID()).getStatus());
            } else if (_productController.editProduct(_product)) {
                MessageSupport.Show(null, "Thông báo", "Sửa thành công.");
                setProductDefault();
                loadProduct(_productController.getProductList());
            }
        }
    }//GEN-LAST:event_jbtnEditProductActionPerformed

    private void jbtnFindProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnFindProductActionPerformed
        if (jbtnFindProduct.getText() == "Tìm") {
            jbtnFindProduct.setText("Hủy");
            Product product = getProductFromForm();
            loadProduct(_productController.findProduct(product));
        } else {
            jbtnFindProduct.setText("Tìm");
            loadProduct(_productController.getProductList());
            setProductDefault();
        }
    }//GEN-LAST:event_jbtnFindProductActionPerformed

    private void jbtnReloadAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnReloadAllActionPerformed
        loadTypeOfProduct();
        loadProduct(_productController.getProductList());
        loadCombobox();
        setProductDefault();
        setTypeOfProductDefault();
        ID = null;
    }//GEN-LAST:event_jbtnReloadAllActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton jbtnAddNew;
    private javax.swing.JButton jbtnAddNew1;
    private javax.swing.JButton jbtnAddProduct;
    private javax.swing.JButton jbtnAddTypeOfProduct;
    private javax.swing.JButton jbtnEditProduct;
    private javax.swing.JButton jbtnEditTypeOfProduct;
    private javax.swing.JButton jbtnFindProduct;
    private javax.swing.JButton jbtnReloadAll;
    private javax.swing.JComboBox<String> jcbTypeOfProduct;
    private javax.swing.JRadioButton jrbAll;
    private javax.swing.JRadioButton jrbLate;
    private javax.swing.JRadioButton jrbLiquidated;
    private javax.swing.JRadioButton jrbNeedToLiquidate;
    private javax.swing.JRadioButton jrbNotRedeemed;
    private javax.swing.JRadioButton jrbRedeemed;
    private javax.swing.JTextArea jtaInformation;
    private javax.swing.JTable jtblProduct;
    private javax.swing.JTable jtblTypeOfProduct;
    private javax.swing.JTextField jtfProductID;
    private javax.swing.JTextField jtfProductName;
    private javax.swing.JTextField jtfTypeOfProductID;
    private javax.swing.JTextField jtfTypeOfProductName;
    // End of variables declaration//GEN-END:variables
}
