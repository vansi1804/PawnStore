/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to editTypeOfProduct this template
 */
package View.JTabbedPaneForm;

import Common.Default;
import Controller.ActivityHistoryController;
import Controller.ProductController;
import Controller.TypeOfProductController;
import Model.ActivityHistory;
import Model.Product;
import Model.TypeOfProduct;
import Support.CheckSupport;
import Support.ColorFormatSupport;
import Support.MessageSupport;
import Support.Support;
import View.HomePageJFrameForm;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author NVS
 */
@SuppressWarnings("ClassWithoutLogger")
public class ProductJPanelForm extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;

    public ProductJPanelForm() {
        initComponents();
        setTableFormat();
        setProductDefault(null);
    }

    public ProductJPanelForm(Product product) {
        initComponents();
        setTableFormat();
        setProductDefault(product);
    }

    private void setTableFormat() {
        ColorFormatSupport.FormatTableHeader(jtblTypeOfProduct);
        ColorFormatSupport.setDataTableCenter(jtblTypeOfProduct);
        ColorFormatSupport.FormatTableHeader(jtblProduct);
        ColorFormatSupport.setDataTableCenter(jtblProduct);
    }

    //=========================================================================\\
    private void setTypeOfProductDefault(TypeOfProduct typeOfProduct) {
        jbtnAddTypeOfProduct.setEnabled(false);
        jbtnEditTypeOfProduct.setEnabled(true);
        jlbInvalidTypeOfProductName.setText(null);
        if (typeOfProduct == null) {
            jbtnEditTypeOfProduct.setEnabled(false);
            jtfTypeOfProductId.setText(null);
            jtfTypeOfProductId.setEditable(true);
            jtfTypeOfProductName.setText(null);
            setTypeOfProductStatus(null);
            setTypeOfProductTable(TypeOfProductController.getCurrentInstance().findAllByStatus(getTypeOfProductStatus()));
        } else {
            jtfTypeOfProductId.setText(typeOfProduct.getId());
            jtfTypeOfProductId.setEditable(false);
            jtfTypeOfProductName.setText(typeOfProduct.getName());
            setTypeOfProductStatus(typeOfProduct.getDeleteFlag());
            setTypeOfProductTable(TypeOfProductController.getCurrentInstance().findAll());
            Support.setRowTableSelection(jtblTypeOfProduct, 1, typeOfProduct.getId());
        }
    }

    @SuppressWarnings({"NestedAssignment", "AssignmentToMethodParameter"})
    private void setTypeOfProductStatus(Boolean deleteFlag) {
        if (deleteFlag == null) {
            jrbServingTypeOfProductStatus.setSelected(true);
            jrbAllTypeOfProductStatus.setEnabled(true);
        } else {
            jrbStopServingTypeOfProductStatus.setSelected(deleteFlag);
            jrbServingTypeOfProductStatus.setSelected(!deleteFlag);
            jrbAllTypeOfProductStatus.setEnabled(false);
        }
    }

    private Boolean getTypeOfProductStatus() {
        return jrbAllTypeOfProductStatus.isSelected() ? null : jrbStopServingTypeOfProductStatus.isSelected();
    }

    private void setTypeOfProductTable(List<TypeOfProduct> typeOfProducts) {
        if (typeOfProducts == null) {
            return;
        }
        ColorFormatSupport.FormatTableHeader(jtblTypeOfProduct);
        ColorFormatSupport.setDataTableCenter(jtblTypeOfProduct);
        DefaultTableModel model = (DefaultTableModel) jtblTypeOfProduct.getModel();
        model.setRowCount(0);
        Object rowData[] = new Object[3];
        for (int i = 0; i < typeOfProducts.size(); i++) {
            rowData[0] = String.valueOf(i + 1);
            rowData[1] = typeOfProducts.get(i).getId();
            rowData[2] = typeOfProducts.get(i).getName();
            model.addRow(rowData);
        }
    }

    private TypeOfProduct getTypeOfProductFromForm() {
        boolean validInfo = true;
        if (CheckSupport.isNullOrBlank(jtfTypeOfProductName.getText())) {
            jlbInvalidTypeOfProductName.setText("Tên loại không được để trống");
            validInfo = false;
        }
        return validInfo ? new TypeOfProduct(jtfTypeOfProductId.getText(), jtfTypeOfProductName.getText(), getTypeOfProductStatus()) : null;
    }

    private void filterTypeOfProduct() {
        if (!jbtnAddTypeOfProduct.isEnabled() && !jbtnEditTypeOfProduct.isEnabled()) {
            setTypeOfProductTable(TypeOfProductController.getCurrentInstance()
                    .filterByKey(jtfTypeOfProductId.getText(), jtfTypeOfProductName.getText(), getTypeOfProductStatus()));
        }
    }

    //======================================================================================================\\
    private void setProductDefault(Product product) {
        jbtnAddProduct.setEnabled(false);
        jbtnEditProduct.setEnabled(true);
        jbtnPawn.setEnabled(false);
        jlbInvalidProductName.setText(null);
        jlbInvalidTypeOfProduct.setText(null);
        jlbInvalidPrductInfo.setText(null);
        if (product == null) {
            jtfProductID.setText(null);
            jtfProductID.setEditable(true);
            jtfProductName.setText(null);
            setCBTypeOfProduct(null);
            jtaProductInfo.setText(null);
            setProductStatus(null);
            jbtnEditProduct.setEnabled(false);
            setProductTable(ProductController.getCurrentInstance().findAllByStatus(getProductStatus()));
            setTypeOfProductDefault(null);
        } else {
            jtfProductID.setText(product.getId());
            jtfProductID.setEditable(false);
            jtfProductName.setText(product.getName());
            setCBTypeOfProduct(product.getTypeOfProduct());
            jtaProductInfo.setText(product.getInfo());
            setProductStatus(product.getStatus());
            Support.setRowTableSelection(jtblProduct, 1, product.getId());
            jbtnEditProduct.setEnabled(true);
            if (product.getTypeOfProduct().getDeleteFlag()
                    || product.getStatus().equals("Chưa chuộc")
                    || product.getStatus().equals("Cần thanh lý")) {

                jbtnPawn.setEnabled(false);
            } else {
                jbtnPawn.setEnabled(true);
            }
            setProductTable(ProductController.getCurrentInstance().findAllByStatus(getProductStatus()));
            Support.setRowTableSelection(jtblProduct, 1, product.getId());
            setTypeOfProductDefault(product.getTypeOfProduct());
        }
    }

    private void setCBTypeOfProduct(TypeOfProduct typeOfProduct) {
        jcbTypeOfProduct.removeAllItems();
        jcbTypeOfProduct.addItem("Tất cả");
        for (TypeOfProduct type : TypeOfProductController.getCurrentInstance().findAllByStatus(getTypeOfProductStatus())) {
            jcbTypeOfProduct.addItem(type.getName());
        }
        jcbTypeOfProduct.setSelectedItem(typeOfProduct == null ? "Tất cả" : typeOfProduct.getName());
    }

    private TypeOfProduct getCBTypeOfProduct() {
        return jcbTypeOfProduct.getSelectedItem().toString().equals("Tất cả") ? null
                : TypeOfProductController.getCurrentInstance().findOneByName(jcbTypeOfProduct.getSelectedItem().toString());
    }

    private void setProductStatus(String status) {
        if (CheckSupport.isNullOrBlank(status)) {
            jrbNewStaus.setEnabled(true);
            jrbNotRedeemedStatus.setEnabled(true);
            jrbRedeemedStatus.setEnabled(true);
            jrbNeedToLiquidateStatus.setEnabled(true);
            jrbLiquidatedStatus.setEnabled(true);
            jrbAllProductStatus.setEnabled(true);
            jrbNotRedeemedStatus.setSelected(true);
            return;
        } else {
            jrbNewStaus.setEnabled(false);
            jrbNotRedeemedStatus.setEnabled(false);
            jrbRedeemedStatus.setEnabled(false);
            jrbNeedToLiquidateStatus.setEnabled(false);
            jrbLiquidatedStatus.setEnabled(false);
            jrbAllProductStatus.setEnabled(false);
        }
        switch (status) {
            case "Mới" -> {
                jrbNewStaus.setEnabled(true);
                jrbNewStaus.setSelected(true);
            }
            case "Chưa chuộc" -> {
                jrbNotRedeemedStatus.setEnabled(true);
                jrbNotRedeemedStatus.setSelected(true);
            }
            case "Đã chuộc" -> {
                jrbRedeemedStatus.setEnabled(true);
                jrbRedeemedStatus.setSelected(true);
            }
            case "Cần thanh lý" -> {
                jrbNeedToLiquidateStatus.setEnabled(true);
                jrbNeedToLiquidateStatus.setSelected(true);
            }
            case "Đã thanh lý" -> {
                jrbLiquidatedStatus.setEnabled(true);
                jrbLiquidatedStatus.setSelected(true);
            }
        }
    }

    private String getProductStatus() {
        return jrbNotRedeemedStatus.isSelected() ? "Chưa chuộc"
                : jrbRedeemedStatus.isSelected() ? "Đã chuộc"
                : jrbNewStaus.isSelected() ? "Mới"
                : jrbNeedToLiquidateStatus.isSelected() ? "Cần thanh lý"
                : jrbLiquidatedStatus.isSelected() ? "Đã thanh lý"
                : null;
    }

    private void setProductTable(List<Product> products) {
        if (products == null) {
            return;
        }
        DefaultTableModel model = (DefaultTableModel) jtblProduct.getModel();
        model.setRowCount(0);
        Object rowData[] = new Object[5];
        for (int i = 0; i < products.size(); i++) {
            rowData[0] = String.valueOf(i + 1);
            rowData[1] = products.get(i).getId();
            rowData[2] = products.get(i).getName();
            rowData[3] = products.get(i).getTypeOfProduct().getName();
            rowData[4] = products.get(i).getInfo();
            model.addRow(rowData);
        }
    }

    private Product getProductFromForm() {
        boolean validInfo = true;
        if (CheckSupport.isNullOrBlank(jtfProductName.getText())) {
            jlbInvalidProductName.setText("Tên hàng hóa không để trống");
            validInfo = false;
        }
        TypeOfProduct typeOfProduct = getCBTypeOfProduct();
        if (typeOfProduct == null) {
            jlbInvalidTypeOfProduct.setText("Chọn loại hàng hóa");
            validInfo = false;
        }
        if (CheckSupport.isNullOrBlank(jtaProductInfo.getText())) {
            jlbInvalidPrductInfo.setText("Thông tin hàng hóa không để trống");
            validInfo = false;
        }
        return validInfo ? new Product(jtfProductID.getText(), typeOfProduct, jtfProductName.getText(),
                jtaProductInfo.getText(), getProductStatus()) : null;
    }

    private void filterProduct() {
        if (!jbtnPawn.isEnabled() && !jbtnAddProduct.isEnabled() && !jbtnEditProduct.isEnabled()) {
            setProductTable(ProductController.getCurrentInstance()
                    .filterByKey(jtfProductID.getText(), getCBTypeOfProduct(),
                            jtfProductName.getText(), jtaProductInfo.getText(), getProductStatus()));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jbtnDeleteTab = new javax.swing.JButton();
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
        jtaProductInfo = new javax.swing.JTextArea();
        jcbTypeOfProduct = new javax.swing.JComboBox<>();
        jbtnEditProduct = new javax.swing.JButton();
        jbtnAddProduct = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jbtnCreateNewProduct = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jrbNotRedeemedStatus = new javax.swing.JRadioButton();
        jrbRedeemedStatus = new javax.swing.JRadioButton();
        jrbLiquidatedStatus = new javax.swing.JRadioButton();
        jrbAllProductStatus = new javax.swing.JRadioButton();
        jrbNewStaus = new javax.swing.JRadioButton();
        jrbNeedToLiquidateStatus = new javax.swing.JRadioButton();
        jbtnPawn = new javax.swing.JButton();
        jlbInvalidProductName = new javax.swing.JLabel();
        jlbInvalidTypeOfProduct = new javax.swing.JLabel();
        jlbInvalidPrductInfo = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtblTypeOfProduct = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jtfTypeOfProductName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jtfTypeOfProductId = new javax.swing.JTextField();
        jbtnEditTypeOfProduct = new javax.swing.JButton();
        jbtnAddTypeOfProduct = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jbtnReloadAll = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jbtnCreateNewTypeOfProduct = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jrbStopServingTypeOfProductStatus = new javax.swing.JRadioButton();
        jrbServingTypeOfProductStatus = new javax.swing.JRadioButton();
        jrbAllTypeOfProductStatus = new javax.swing.JRadioButton();
        jlbInvalidTypeOfProductName = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblProduct = new javax.swing.JTable();

        jPanel1.setBackground(new java.awt.Color(51, 255, 255));

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setForeground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("HÀNG HÓA");

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

        jPanel3.setBackground(new java.awt.Color(51, 255, 255));
        jPanel3.setForeground(new java.awt.Color(0, 0, 0));

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Thông tin hàng hóa");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Tên hàng hóa");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Loại hàng hóa");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Thông tin chi tiết");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Mã hàng hóa");

        jtfProductName.setBackground(new java.awt.Color(255, 255, 255));
        jtfProductName.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jtfProductName.setForeground(new java.awt.Color(0, 0, 0));
        jtfProductName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jtProductInfoMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jtProductInfoMouseReleased(evt);
            }
        });

        jtfProductID.setBackground(new java.awt.Color(255, 255, 255));
        jtfProductID.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jtfProductID.setForeground(new java.awt.Color(0, 0, 0));
        jtfProductID.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jtProductInfoMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jtProductInfoMouseReleased(evt);
            }
        });

        jtaProductInfo.setBackground(new java.awt.Color(255, 255, 255));
        jtaProductInfo.setColumns(10);
        jtaProductInfo.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jtaProductInfo.setForeground(new java.awt.Color(0, 0, 0));
        jtaProductInfo.setRows(3);
        jtaProductInfo.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jtaProductInfo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jtProductInfoMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jtProductInfoMouseReleased(evt);
            }
        });
        jScrollPane3.setViewportView(jtaProductInfo);

        jcbTypeOfProduct.setBackground(new java.awt.Color(255, 255, 255));
        jcbTypeOfProduct.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jcbTypeOfProduct.setForeground(new java.awt.Color(0, 0, 0));
        jcbTypeOfProduct.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbTypeOfProductItemStateChanged(evt);
            }
        });
        jcbTypeOfProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jcbTypeOfProductMousePressed(evt);
            }
        });

        jbtnEditProduct.setBackground(new java.awt.Color(0, 255, 255));
        jbtnEditProduct.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jbtnEditProduct.setForeground(new java.awt.Color(0, 0, 0));
        jbtnEditProduct.setText("Sửa");
        jbtnEditProduct.setPreferredSize(new java.awt.Dimension(69, 27));
        jbtnEditProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEditProductActionPerformed(evt);
            }
        });

        jbtnAddProduct.setBackground(new java.awt.Color(0, 255, 255));
        jbtnAddProduct.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jbtnAddProduct.setForeground(new java.awt.Color(0, 0, 0));
        jbtnAddProduct.setText("Thêm");
        jbtnAddProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAddProductActionPerformed(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(0, 255, 255));

        jbtnCreateNewProduct.setBackground(new java.awt.Color(255, 255, 255));
        jbtnCreateNewProduct.setForeground(new java.awt.Color(0, 255, 255));
        jbtnCreateNewProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/addNew.png"))); // NOI18N
        jbtnCreateNewProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbtnCreateNewProductMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jbtnCreateNewProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jbtnCreateNewProduct, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jLabel10.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Trạng thái");

        jrbNotRedeemedStatus.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(jrbNotRedeemedStatus);
        jrbNotRedeemedStatus.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jrbNotRedeemedStatus.setForeground(new java.awt.Color(0, 0, 0));
        jrbNotRedeemedStatus.setText("Chưa chuộc");
        jrbNotRedeemedStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbProductStatusActionPerformed(evt);
            }
        });

        jrbRedeemedStatus.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(jrbRedeemedStatus);
        jrbRedeemedStatus.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jrbRedeemedStatus.setForeground(new java.awt.Color(0, 0, 0));
        jrbRedeemedStatus.setText("Đã chuộc");
        jrbRedeemedStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbProductStatusActionPerformed(evt);
            }
        });

        jrbLiquidatedStatus.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(jrbLiquidatedStatus);
        jrbLiquidatedStatus.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jrbLiquidatedStatus.setForeground(new java.awt.Color(0, 0, 0));
        jrbLiquidatedStatus.setText("Đã thanh lý");
        jrbLiquidatedStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbProductStatusActionPerformed(evt);
            }
        });

        jrbAllProductStatus.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(jrbAllProductStatus);
        jrbAllProductStatus.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jrbAllProductStatus.setForeground(new java.awt.Color(0, 0, 0));
        jrbAllProductStatus.setSelected(true);
        jrbAllProductStatus.setText("Tất cả");
        jrbAllProductStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbProductStatusActionPerformed(evt);
            }
        });

        jrbNewStaus.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(jrbNewStaus);
        jrbNewStaus.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jrbNewStaus.setForeground(new java.awt.Color(0, 0, 0));
        jrbNewStaus.setText("Mới");
        jrbNewStaus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbProductStatusActionPerformed(evt);
            }
        });

        jrbNeedToLiquidateStatus.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(jrbNeedToLiquidateStatus);
        jrbNeedToLiquidateStatus.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jrbNeedToLiquidateStatus.setForeground(new java.awt.Color(0, 0, 0));
        jrbNeedToLiquidateStatus.setText("Cần thanh lý");
        jrbNeedToLiquidateStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbProductStatusActionPerformed(evt);
            }
        });

        jbtnPawn.setBackground(new java.awt.Color(0, 255, 255));
        jbtnPawn.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jbtnPawn.setForeground(new java.awt.Color(0, 0, 0));
        jbtnPawn.setText("Cầm");
        jbtnPawn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnPawnActionPerformed(evt);
            }
        });

        jlbInvalidProductName.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        jlbInvalidProductName.setForeground(new java.awt.Color(255, 0, 0));
        jlbInvalidProductName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlbInvalidProductName.setText("Tên hàng hóa không được để trống");

        jlbInvalidTypeOfProduct.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        jlbInvalidTypeOfProduct.setForeground(new java.awt.Color(255, 0, 0));
        jlbInvalidTypeOfProduct.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlbInvalidTypeOfProduct.setText("Chọn loại hàng hóa");

        jlbInvalidPrductInfo.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        jlbInvalidPrductInfo.setForeground(new java.awt.Color(255, 0, 0));
        jlbInvalidPrductInfo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlbInvalidPrductInfo.setText("Thông tin không được để trống");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jbtnPawn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jrbNeedToLiquidateStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jrbNotRedeemedStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jrbRedeemedStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jrbLiquidatedStatus))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jrbAllProductStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jrbNewStaus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jlbInvalidProductName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtfProductID, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jtfProductName, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jcbTypeOfProduct, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane3)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jbtnAddProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jbtnEditProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jlbInvalidTypeOfProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlbInvalidPrductInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(315, 315, 315))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jtfProductID)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbInvalidProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbTypeOfProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbInvalidTypeOfProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlbInvalidPrductInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrbNotRedeemedStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jrbRedeemedStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jrbNewStaus, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrbLiquidatedStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jrbAllProductStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jrbNeedToLiquidateStatus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnAddProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnEditProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnPawn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));
        jPanel5.setForeground(new java.awt.Color(204, 204, 204));

        jtblTypeOfProduct.setBackground(new java.awt.Color(255, 255, 255));
        jtblTypeOfProduct.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
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
        jtblTypeOfProduct.setRowHeight(20);
        jtblTypeOfProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblTypeOfProductMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jtblTypeOfProduct);
        if (jtblTypeOfProduct.getColumnModel().getColumnCount() > 0) {
            jtblTypeOfProduct.getColumnModel().getColumn(0).setMinWidth(35);
            jtblTypeOfProduct.getColumnModel().getColumn(0).setPreferredWidth(35);
            jtblTypeOfProduct.getColumnModel().getColumn(0).setMaxWidth(35);
            jtblTypeOfProduct.getColumnModel().getColumn(1).setMinWidth(150);
            jtblTypeOfProduct.getColumnModel().getColumn(1).setPreferredWidth(150);
            jtblTypeOfProduct.getColumnModel().getColumn(1).setMaxWidth(150);
        }

        jLabel3.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Mã loại");

        jtfTypeOfProductName.setBackground(new java.awt.Color(255, 255, 255));
        jtfTypeOfProductName.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jtfTypeOfProductName.setForeground(new java.awt.Color(0, 0, 0));
        jtfTypeOfProductName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jtfInfoMousePressed(evt);
            }
        });
        jtfTypeOfProductName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfTypeOfProductKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Tên loại");

        jtfTypeOfProductId.setBackground(new java.awt.Color(255, 255, 255));
        jtfTypeOfProductId.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jtfTypeOfProductId.setForeground(new java.awt.Color(0, 0, 0));
        jtfTypeOfProductId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfTypeOfProductKeyReleased(evt);
            }
        });

        jbtnEditTypeOfProduct.setBackground(new java.awt.Color(0, 255, 255));
        jbtnEditTypeOfProduct.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jbtnEditTypeOfProduct.setForeground(new java.awt.Color(0, 0, 0));
        jbtnEditTypeOfProduct.setText("Sửa");
        jbtnEditTypeOfProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEditTypeOfProductActionPerformed(evt);
            }
        });

        jbtnAddTypeOfProduct.setBackground(new java.awt.Color(0, 255, 255));
        jbtnAddTypeOfProduct.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
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

        jbtnReloadAll.setBackground(new java.awt.Color(0, 255, 255));
        jbtnReloadAll.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jbtnReloadAll.setForeground(new java.awt.Color(0, 0, 0));
        jbtnReloadAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/reload.png"))); // NOI18N
        jbtnReloadAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnReloadAllActionPerformed(evt);
            }
        });

        jPanel9.setBackground(new java.awt.Color(0, 255, 255));

        jbtnCreateNewTypeOfProduct.setBackground(new java.awt.Color(255, 255, 255));
        jbtnCreateNewTypeOfProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/addNew.png"))); // NOI18N
        jbtnCreateNewTypeOfProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbtnCreateNewTypeOfProductMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jbtnCreateNewTypeOfProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jbtnCreateNewTypeOfProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel11.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Trạng thái");

        jrbStopServingTypeOfProductStatus.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup2.add(jrbStopServingTypeOfProductStatus);
        jrbStopServingTypeOfProductStatus.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jrbStopServingTypeOfProductStatus.setForeground(new java.awt.Color(0, 0, 0));
        jrbStopServingTypeOfProductStatus.setText("Ngưng phục vụ");
        jrbStopServingTypeOfProductStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbTypeOfProductStatusActionPerformed(evt);
            }
        });

        jrbServingTypeOfProductStatus.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup2.add(jrbServingTypeOfProductStatus);
        jrbServingTypeOfProductStatus.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jrbServingTypeOfProductStatus.setForeground(new java.awt.Color(0, 0, 0));
        jrbServingTypeOfProductStatus.setText("Phục vụ");
        jrbServingTypeOfProductStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbTypeOfProductStatusActionPerformed(evt);
            }
        });

        jrbAllTypeOfProductStatus.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup2.add(jrbAllTypeOfProductStatus);
        jrbAllTypeOfProductStatus.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jrbAllTypeOfProductStatus.setForeground(new java.awt.Color(0, 0, 0));
        jrbAllTypeOfProductStatus.setSelected(true);
        jrbAllTypeOfProductStatus.setText("Tất cả");
        jrbAllTypeOfProductStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbTypeOfProductStatusActionPerformed(evt);
            }
        });

        jlbInvalidTypeOfProductName.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        jlbInvalidTypeOfProductName.setForeground(new java.awt.Color(255, 0, 0));
        jlbInvalidTypeOfProductName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlbInvalidTypeOfProductName.setText("Tên loại không hợp lệ");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jlbInvalidTypeOfProductName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jrbAllTypeOfProductStatus, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jtfTypeOfProductId)
                                    .addComponent(jtfTypeOfProductName, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jrbStopServingTypeOfProductStatus, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jrbServingTypeOfProductStatus, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE))
                                .addGap(12, 12, 12)
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jbtnAddTypeOfProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jbtnEditTypeOfProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbtnReloadAll, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(10, 10, 10)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jtfTypeOfProductId, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfTypeOfProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addComponent(jlbInvalidTypeOfProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jrbStopServingTypeOfProductStatus))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jrbServingTypeOfProductStatus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jrbAllTypeOfProductStatus)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbtnAddTypeOfProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtnEditTypeOfProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbtnReloadAll, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));
        jPanel6.setForeground(new java.awt.Color(204, 204, 204));

        jtblProduct.setBackground(new java.awt.Color(255, 255, 255));
        jtblProduct.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jtblProduct.setForeground(new java.awt.Color(0, 0, 0));
        jtblProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã hàng hóa", "Tên hàng hóa", "Loại hàng", "Thông tin chi tiết"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblProduct.setRowHeight(20);
        jtblProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblProductMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtblProduct);
        if (jtblProduct.getColumnModel().getColumnCount() > 0) {
            jtblProduct.getColumnModel().getColumn(0).setMinWidth(35);
            jtblProduct.getColumnModel().getColumn(0).setPreferredWidth(35);
            jtblProduct.getColumnModel().getColumn(0).setMaxWidth(35);
            jtblProduct.getColumnModel().getColumn(1).setMinWidth(150);
            jtblProduct.getColumnModel().getColumn(1).setPreferredWidth(150);
            jtblProduct.getColumnModel().getColumn(1).setMaxWidth(150);
            jtblProduct.getColumnModel().getColumn(2).setMinWidth(200);
            jtblProduct.getColumnModel().getColumn(2).setPreferredWidth(200);
            jtblProduct.getColumnModel().getColumn(2).setMaxWidth(200);
            jtblProduct.getColumnModel().getColumn(3).setMinWidth(200);
            jtblProduct.getColumnModel().getColumn(3).setPreferredWidth(200);
            jtblProduct.getColumnModel().getColumn(3).setMaxWidth(200);
        }

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
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

    private void jtblTypeOfProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblTypeOfProductMouseClicked
        int row = jtblTypeOfProduct.getSelectedRow();
        if (evt.getClickCount() == 2 && row != -1) {
            JTable table = (JTable) evt.getSource();
            String id = (table.getModel().getValueAt(row, 1)).toString();
            TypeOfProduct typeOfProduct = TypeOfProductController.getCurrentInstance().findOneById(id);
            setTypeOfProductDefault(typeOfProduct);
        }
    }//GEN-LAST:event_jtblTypeOfProductMouseClicked

    private void jbtnReloadAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnReloadAllActionPerformed
        setTypeOfProductDefault(null);
        setProductDefault(null);
    }//GEN-LAST:event_jbtnReloadAllActionPerformed

    private void jbtnAddTypeOfProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAddTypeOfProductActionPerformed
        TypeOfProduct typeOfProduct = getTypeOfProductFromForm();
        if (typeOfProduct != null) {
            if (TypeOfProductController.getCurrentInstance().insert(typeOfProduct)) {
                MessageSupport.Message("Thông báo", "Thêm mới thành công");
                ActivityHistoryController.getCurrentInstance()
                        .insert(new ActivityHistory(Support.dateToString(LocalDateTime.now(), Default.DATE_TIME_FORMAT),
                                "Thêm mới", "Loại hàng hóa", typeOfProduct.toString()));
                setTypeOfProductDefault(null);
                setProductDefault(null);
            }
        }
    }//GEN-LAST:event_jbtnAddTypeOfProductActionPerformed

    private void jbtnEditTypeOfProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEditTypeOfProductActionPerformed
        TypeOfProduct typeOfProduct = getTypeOfProductFromForm();
        if (typeOfProduct != null) {
            if (TypeOfProductController.getCurrentInstance().update(typeOfProduct)) {
                MessageSupport.Message("Thông báo", "Cập nhật thông tin loại hàng hóa thành công");
                ActivityHistoryController.getCurrentInstance()
                        .insert(new ActivityHistory(Support.dateToString(LocalDateTime.now(), Default.DATE_TIME_FORMAT),
                                "Cập nhật", "Loại hàng hóa", typeOfProduct.toString()));
            }
            setTypeOfProductDefault(null);
            setProductDefault(null);
        }
    }//GEN-LAST:event_jbtnEditTypeOfProductActionPerformed

    private void jbtnAddProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAddProductActionPerformed
        Product product = getProductFromForm();
        if (product != null) {
            if (ProductController.getCurrentInstance().insert(product)) {
                MessageSupport.Message("Thông báo", "Thêm mới hàng hóa thành công");
                ActivityHistoryController.getCurrentInstance()
                        .insert(new ActivityHistory(Support.dateToString(LocalDateTime.now(), Default.DATE_TIME_FORMAT),
                                "Thêm mới", "Hàng hóa", product.toString()));
                setProductDefault(null);
            }
        }
    }//GEN-LAST:event_jbtnAddProductActionPerformed

    private void jbtnEditProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEditProductActionPerformed
        Product product = getProductFromForm();
        if (product != null) {
            if (ProductController.getCurrentInstance().update(product)) {
                MessageSupport.Message("Thông báo", "Cập nhật thông tin hàng hóa thành công");
                setProductDefault(null);
                ActivityHistoryController.getCurrentInstance()
                        .insert(new ActivityHistory(Support.dateToString(LocalDateTime.now(), Default.DATE_TIME_FORMAT),
                                "Cập nhật", "Hàng hóa", product.toString()));
            }
        }
    }//GEN-LAST:event_jbtnEditProductActionPerformed

    private void jtblProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblProductMouseClicked
        int row = jtblProduct.getSelectedRow();
        if (evt.getClickCount() == 2 && row != -1) {
            JTable table = (JTable) evt.getSource();
            String id = (table.getModel().getValueAt(row, 1)).toString();
            Product product = ProductController.getCurrentInstance().findOneById(id);
            setProductDefault(product);
            setTypeOfProductDefault(product.getTypeOfProduct());
        }
    }//GEN-LAST:event_jtblProductMouseClicked

    private void jrbProductStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbProductStatusActionPerformed
        filterProduct();
    }//GEN-LAST:event_jrbProductStatusActionPerformed

    private void jrbTypeOfProductStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbTypeOfProductStatusActionPerformed
        filterTypeOfProduct();
    }//GEN-LAST:event_jrbTypeOfProductStatusActionPerformed

    private void jbtnDeleteTabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDeleteTabActionPerformed
        HomePageJFrameForm.jtpHomePage.remove(HomePageJFrameForm.jtpHomePage.indexOfTab("Hàng hóa"));
    }//GEN-LAST:event_jbtnDeleteTabActionPerformed

    private void jbtnCreateNewTypeOfProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnCreateNewTypeOfProductMouseClicked
        setTypeOfProductDefault(null);
        jbtnAddTypeOfProduct.setEnabled(true);
        jtfTypeOfProductId.setText(TypeOfProductController.getCurrentInstance().createNewId());
        jtfTypeOfProductId.setEditable(false);
        setTypeOfProductStatus(Boolean.FALSE);
    }//GEN-LAST:event_jbtnCreateNewTypeOfProductMouseClicked

    private void jbtnPawnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnPawnActionPerformed
        Product product = getProductFromForm();
        if (product != null) {
            if (product.getTypeOfProduct().getDeleteFlag()) {
                MessageSupport.Message("Thông báo!", "Hàng hóa đã ngưng phục vụ");
            } else if (product.getStatus().equals("Chưa chuộc") || product.getStatus().equals("Cần thanh lý")) {
                MessageSupport.Message("Thông báo!", "Hàng hóa đang có hiệu lực ở một hợp đồng khác");
            } else {
                @SuppressWarnings("UnusedAssignment")
                JPanel jPanel = null;
                String title = "Hợp đồng";
                if (HomePageJFrameForm.jtpHomePage.indexOfTab(title) != -1) {
                    HomePageJFrameForm.jtpHomePage.remove(HomePageJFrameForm.jtpHomePage.indexOfTab(title));
                }
                jPanel = new PawnCouponJPanelForm(product);
                HomePageJFrameForm.jtpHomePage.addTab(title, jPanel);
                HomePageJFrameForm.jtpHomePage.setSelectedComponent(jPanel);
            }
        }
    }//GEN-LAST:event_jbtnPawnActionPerformed

    private void jtfInfoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtfInfoMousePressed
        if (evt.getSource().equals(jtfTypeOfProductName)) {
            jlbInvalidTypeOfProductName.setText(null);
        }
    }//GEN-LAST:event_jtfInfoMousePressed

    private void jtProductInfoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtProductInfoMousePressed
        if (evt.getSource().equals(jtfProductName)) {
            jlbInvalidProductName.setText(null);
        } else if (evt.getSource().equals(jcbTypeOfProduct)) {
            jlbInvalidTypeOfProduct.setText(null);
        } else if (evt.getSource().equals(jtaProductInfo)) {
            jlbInvalidPrductInfo.setText(null);
        }
    }//GEN-LAST:event_jtProductInfoMousePressed

    private void jtfTypeOfProductKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfTypeOfProductKeyReleased
        filterTypeOfProduct();
    }//GEN-LAST:event_jtfTypeOfProductKeyReleased

    private void jtProductInfoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtProductInfoMouseReleased
        filterProduct();
    }//GEN-LAST:event_jtProductInfoMouseReleased

    private void jcbTypeOfProductItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbTypeOfProductItemStateChanged
        filterProduct();
    }//GEN-LAST:event_jcbTypeOfProductItemStateChanged

    private void jbtnCreateNewProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnCreateNewProductMouseClicked
        setProductDefault(null);
        jbtnAddProduct.setEnabled(true);
        jtfProductID.setText(ProductController.getCurrentInstance().createNewId());
        jtfProductID.setEditable(false);
        setProductStatus("Mới");
        setProductTable(ProductController.getCurrentInstance().findAllByStatus(getProductStatus()));
    }//GEN-LAST:event_jbtnCreateNewProductMouseClicked

    private void jcbTypeOfProductMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcbTypeOfProductMousePressed
        jlbInvalidTypeOfProduct.setText(null);
    }//GEN-LAST:event_jcbTypeOfProductMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton jbtnAddProduct;
    private javax.swing.JButton jbtnAddTypeOfProduct;
    private javax.swing.JButton jbtnCreateNewProduct;
    private javax.swing.JButton jbtnCreateNewTypeOfProduct;
    private javax.swing.JButton jbtnDeleteTab;
    private javax.swing.JButton jbtnEditProduct;
    private javax.swing.JButton jbtnEditTypeOfProduct;
    private javax.swing.JButton jbtnPawn;
    private javax.swing.JButton jbtnReloadAll;
    private javax.swing.JComboBox<String> jcbTypeOfProduct;
    private javax.swing.JLabel jlbInvalidPrductInfo;
    private javax.swing.JLabel jlbInvalidProductName;
    private javax.swing.JLabel jlbInvalidTypeOfProduct;
    private javax.swing.JLabel jlbInvalidTypeOfProductName;
    private javax.swing.JRadioButton jrbAllProductStatus;
    private javax.swing.JRadioButton jrbAllTypeOfProductStatus;
    private javax.swing.JRadioButton jrbLiquidatedStatus;
    private javax.swing.JRadioButton jrbNeedToLiquidateStatus;
    private javax.swing.JRadioButton jrbNewStaus;
    private javax.swing.JRadioButton jrbNotRedeemedStatus;
    private javax.swing.JRadioButton jrbRedeemedStatus;
    private javax.swing.JRadioButton jrbServingTypeOfProductStatus;
    private javax.swing.JRadioButton jrbStopServingTypeOfProductStatus;
    private javax.swing.JTextArea jtaProductInfo;
    private javax.swing.JTable jtblProduct;
    private javax.swing.JTable jtblTypeOfProduct;
    private javax.swing.JTextField jtfProductID;
    private javax.swing.JTextField jtfProductName;
    private javax.swing.JTextField jtfTypeOfProductId;
    private javax.swing.JTextField jtfTypeOfProductName;
    // End of variables declaration//GEN-END:variables

}
