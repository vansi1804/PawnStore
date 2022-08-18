/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to editTypeOfProduct this template
 */
package View.JTabbedPaneForm;

import Controller.ActivityHistoryController;
import Controller.ProductController;
import Controller.TypeOfProductController;
import Model.ActivityHistory;
import Model.Product;
import Model.StaticUser;
import Model.TypeOfProduct;
import Support.CheckSupport;
import Support.ColorFormatSupport;
import Support.MessageSupport;
import Support.Support;
import View.JHomePageForm;
import java.awt.Color;
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
public class JProductPanelForm extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;

    public JProductPanelForm() {
        initComponents();
        setTypeOfProductFindEvent();
        setFindProductEvent();
        setTypeOfProductDefault(null);
        setProductDefault(null);
    }

    JProductPanelForm(Product product) {
        initComponents();
        setTypeOfProductFindEvent();
        setFindProductEvent();
        setTypeOfProductDefault(null);
        setProductDefault(null);
        setProductDefault(product);
    }

    private void setTypeOfProductDefault(TypeOfProduct typeOfProduct) {
        if (typeOfProduct == null) {
            jbtnAddTypeOfProduct.setEnabled(true);
            jbtnEditTypeOfProduct.setEnabled(true);
            jtfTypeOfProductID.setText("");
            jtfTypeOfProductID.setEditable(true);
            jtfTypeOfProductName.setText("");
            setTypeOfProductStatus("");
            setTypeOfProductTable(TypeOfProductController.getCurrentInstance().findTypeOfProductByDeleteflagKey(getTypeOfProductStatus()));
            jbtnAddTypeOfProduct.setEnabled(false);
            jbtnEditTypeOfProduct.setEnabled(false);
        } else {
            jbtnAddTypeOfProduct.setEnabled(false);
            jbtnEditTypeOfProduct.setEnabled(true);
            jtfTypeOfProductID.setText(typeOfProduct.getId());
            jtfTypeOfProductID.setEditable(false);
            jtfTypeOfProductName.setText(typeOfProduct.getName());
            setTypeOfProductStatus(typeOfProduct.getDeleteflag() ? "1" : "0");
        }
    }

    private void setTypeOfProductStatus(String status) {
        if (CheckSupport.isBlank(status)) {
            jrbUnDelete.setEnabled(true);
            jrbUnDelete.setSelected(true);
            jrbDeleted.setEnabled(true);
            jrbAllDeleteflag.setEnabled(true);
        } else {
            switch (status) {
                case "0" -> {
                    jrbUnDelete.setEnabled(true);
                    jrbUnDelete.setSelected(true);
                    jrbDeleted.setEnabled(true);
                    jrbAllDeleteflag.setEnabled(false);
                }
                case "1" -> {
                    jrbUnDelete.setEnabled(true);
                    jrbDeleted.setEnabled(true);
                    jrbDeleted.setSelected(true);
                    jrbAllDeleteflag.setEnabled(false);
                }
            }
        }
    }

    private String getTypeOfProductStatus() {
        if (jrbUnDelete.isSelected()) {
            return "0";
        } else if (jrbDeleted.isSelected()) {
            return "1";
        } else {
            return null;
        }
    }

    private void setTypeOfProductTable(ArrayList<TypeOfProduct> typeOfProducts) {
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
        String id = jtfTypeOfProductID.getText();
        String name = jtfTypeOfProductName.getText();
        if (CheckSupport.isBlank(name)) {
            MessageSupport.ErrorMessage("Lỗi", "Tên loại không được để trống.");
            return null;
        }
        boolean deleteflag = getTypeOfProductStatus().equals("1");
        return new TypeOfProduct(id, name, deleteflag);
    }

    private void setTypeOfProductFindEvent() {
        jtfTypeOfProductID.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                findTypeOfProduct();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                findTypeOfProduct();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                findTypeOfProduct();
            }
        });
        jtfTypeOfProductName.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                findTypeOfProduct();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                findTypeOfProduct();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                findTypeOfProduct();
            }
        });
    }

    private void findTypeOfProduct() {
        if (!jbtnAddTypeOfProduct.isEnabled() && !jbtnEditTypeOfProduct.isEnabled()) {
            ArrayList<TypeOfProduct> results = TypeOfProductController.getCurrentInstance()
                    .findTypeOfProductByKey(jtfTypeOfProductID.getText(), jtfTypeOfProductName.getText(), getTypeOfProductStatus());
            setTypeOfProductTable(results);
        }
    }

    private void setProductDefault(Product product) {
        if (product == null) {
            jbtnAddProduct.setEnabled(true);
            jbtnEditProduct.setEnabled(true);
            jtfProductID.setText("");
            jtfProductID.setEditable(true);
            jtfProductName.setText("");
            setCBTypeOfProduct(TypeOfProductController.getCurrentInstance().getList());
            jtaInformation.setText("");
            setProductStatus("");
            setProductTable(ProductController.getCurrentInstance().findProductByStatusKey(getProductStatus()));
            jbtnAddProduct.setEnabled(false);
            jbtnEditProduct.setEnabled(false);
        } else {
            jbtnAddProduct.setEnabled(false);
            jbtnEditProduct.setEnabled(true);
            jtfProductID.setText(product.getId());
            jtfProductID.setEditable(false);
            jcbTypeOfProduct.setSelectedItem(product.getTypeOfProduct().getName());
            jtfProductName.setText(product.getName());
            jtaInformation.setText(product.getInfo());
            setProductStatus(product.getStatus());
        }
    }

    private void setCBTypeOfProduct(ArrayList<TypeOfProduct> typeOfProducts) {
        jcbTypeOfProduct.removeAllItems();
        jcbTypeOfProduct.addItem("Tất cả");
        for (TypeOfProduct typeOfProduct : typeOfProducts) {
            if (typeOfProduct.getDeleteflag()) {
                jcbTypeOfProduct.addItem(typeOfProduct.getName());
            } else {
                jcbTypeOfProduct.addItem(typeOfProduct.getName());
            }
        }
        jcbTypeOfProduct.setSelectedItem("Tất cả");
    }

    private TypeOfProduct getCBTypeOfProduct() {
        String typeOfProductName = jcbTypeOfProduct.getSelectedItem() == null ? null : jcbTypeOfProduct.getSelectedItem().toString();
        return TypeOfProductController.getCurrentInstance().getTypeOfProductByName(typeOfProductName);
    }

    private void setProductStatus(String status) {
        if (CheckSupport.isBlank(status)) {
            jrbNotRedeemedStatus.setEnabled(true);
            jrbNotRedeemedStatus.setSelected(true);
            jrbRedeemedStatus.setEnabled(true);
            jrbNeedToLiquidateStatus.setEnabled(true);
            jrbLiquidatedStatus.setEnabled(true);
            jrbNewStaus.setEnabled(true);
            jrbAllProductStatus.setEnabled(true);
        } else {
            switch (status) {
                case "Chưa chuộc" -> {
                    jrbNotRedeemedStatus.setEnabled(true);
                    jrbNotRedeemedStatus.setSelected(true);
                    jrbRedeemedStatus.setEnabled(false);
                    jrbNeedToLiquidateStatus.setEnabled(false);
                    jrbLiquidatedStatus.setEnabled(false);
                    jrbNewStaus.setEnabled(false);
                    jrbAllProductStatus.setEnabled(false);
                }
                case "Đã chuộc" -> {
                    jrbNotRedeemedStatus.setEnabled(false);
                    jrbRedeemedStatus.setEnabled(true);
                    jrbRedeemedStatus.setSelected(true);
                    jrbNeedToLiquidateStatus.setEnabled(false);
                    jrbLiquidatedStatus.setEnabled(false);
                    jrbNewStaus.setEnabled(false);
                    jrbAllProductStatus.setEnabled(false);
                }
                case "Cần thanh lý" -> {
                    jrbNotRedeemedStatus.setEnabled(false);
                    jrbRedeemedStatus.setEnabled(false);
                    jrbNeedToLiquidateStatus.setEnabled(true);
                    jrbNeedToLiquidateStatus.setSelected(true);
                    jrbLiquidatedStatus.setEnabled(false);
                    jrbNewStaus.setEnabled(false);
                    jrbAllProductStatus.setEnabled(false);
                }
                case "Đã thanh lý" -> {
                    jrbNotRedeemedStatus.setEnabled(false);
                    jrbRedeemedStatus.setEnabled(false);
                    jrbNeedToLiquidateStatus.setEnabled(false);
                    jrbLiquidatedStatus.setEnabled(true);
                    jrbLiquidatedStatus.setSelected(true);
                    jrbNewStaus.setEnabled(false);
                    jrbAllProductStatus.setEnabled(false);
                }
                case "Mới" -> {
                    jrbNotRedeemedStatus.setEnabled(false);
                    jrbRedeemedStatus.setEnabled(false);
                    jrbNeedToLiquidateStatus.setEnabled(false);
                    jrbLiquidatedStatus.setEnabled(false);
                    jrbNewStaus.setEnabled(true);
                    jrbNewStaus.setSelected(true);
                    jrbAllProductStatus.setEnabled(false);
                }
            }
        }
    }

    private String getProductStatus() {
        if (jrbNotRedeemedStatus.isSelected()) {
            return "Chưa chuộc";
        } else if (jrbRedeemedStatus.isSelected()) {
            return "Đã chuộc";
        } else if (jrbNeedToLiquidateStatus.isSelected()) {
            return "Cần thanh lý";
        } else if (jrbLiquidatedStatus.isSelected()) {
            return "Đã thanh lý";
        } else if (jrbNewStaus.isSelected()) {
            return "Mới";
        } else {
            return null;
        }
    }

    private void setProductTable(ArrayList<Product> products) {
        ColorFormatSupport.setDataTableCenter(jtblProduct);
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
        String id = jtfProductID.getText();
        ArrayList<TypeOfProduct> typeOfProducts = TypeOfProductController.getCurrentInstance()
                .findTypeOfProductByNameKey(TypeOfProductController.getCurrentInstance().getList(),
                        jcbTypeOfProduct.getSelectedItem().toString());
        if (typeOfProducts.isEmpty()) {
            MessageSupport.ErrorMessage("Lỗi", "Chọn loại hàng hóa.");
            return null;
        }
        TypeOfProduct typeOfProduct = typeOfProducts.get(typeOfProducts.size() - 1);

        String name = jtfProductName.getText();
        if (CheckSupport.isBlank(name)) {
            MessageSupport.ErrorMessage("Lỗi", "Tên sản hàng hóa không được để trống.");
            return null;
        }
        String infor = jtaInformation.getText();
        if (CheckSupport.isBlank(infor)) {
            MessageSupport.ErrorMessage("Lỗi", "Thông tin sản hàng hóa không được để trống.");
            return null;
        }
        String status = getProductStatus();
        return new Product(id, typeOfProduct, name, infor, status);
    }

    private void findProduct() {
        if (!jbtnAddProduct.isEnabled() && !jbtnEditProduct.isEnabled()) {
            ArrayList<Product> results = ProductController.getCurrentInstance()
                    .findProductByKey(jtfProductID.getText(), getCBTypeOfProduct(),
                            jtfProductName.getText(), jtaInformation.getText(), getProductStatus());
            setProductTable(results);
        }
    }

    private void setFindProductEvent() {
        jtfProductID.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                findProduct();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                findProduct();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                findProduct();
            }
        });
        jtfProductName.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                findProduct();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                findProduct();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                findProduct();
            }
        });
        jtaInformation.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                findProduct();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                findProduct();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                findProduct();
            }
        });
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
        jtaInformation = new javax.swing.JTextArea();
        jcbTypeOfProduct = new javax.swing.JComboBox<>();
        jbtnEditProduct = new javax.swing.JButton();
        jbtnAddProduct = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jlbAddNewProduct = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jrbNotRedeemedStatus = new javax.swing.JRadioButton();
        jrbRedeemedStatus = new javax.swing.JRadioButton();
        jrbLiquidatedStatus = new javax.swing.JRadioButton();
        jrbAllProductStatus = new javax.swing.JRadioButton();
        jrbNewStaus = new javax.swing.JRadioButton();
        jrbNeedToLiquidateStatus = new javax.swing.JRadioButton();
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
        jbtnReloadAll = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jlbAddNewTypeOfProduct = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jrbDeleted = new javax.swing.JRadioButton();
        jrbUnDelete = new javax.swing.JRadioButton();
        jrbAllDeleteflag = new javax.swing.JRadioButton();
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
                .addComponent(jbtnDeleteTab))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jbtnDeleteTab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(51, 255, 255));
        jPanel3.setForeground(new java.awt.Color(0, 0, 0));

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Thông tin hàng hóa");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 3, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Tên hàng hóa");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 3, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Loại hàng hóa");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 3, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Thông tin chi tiết");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 3, 16)); // NOI18N
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
        jtaInformation.setRows(3);
        jtaInformation.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane3.setViewportView(jtaInformation);

        jcbTypeOfProduct.setBackground(new java.awt.Color(255, 255, 255));
        jcbTypeOfProduct.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jcbTypeOfProduct.setForeground(new java.awt.Color(0, 0, 0));
        jcbTypeOfProduct.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbTypeOfProductItemStateChanged(evt);
            }
        });

        jbtnEditProduct.setBackground(new java.awt.Color(0, 255, 255));
        jbtnEditProduct.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jbtnEditProduct.setForeground(new java.awt.Color(0, 0, 0));
        jbtnEditProduct.setText("Sửa");
        jbtnEditProduct.setPreferredSize(new java.awt.Dimension(69, 27));
        jbtnEditProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEditProductActionPerformed(evt);
            }
        });

        jbtnAddProduct.setBackground(new java.awt.Color(0, 255, 255));
        jbtnAddProduct.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jbtnAddProduct.setForeground(new java.awt.Color(0, 0, 0));
        jbtnAddProduct.setText("Thêm");
        jbtnAddProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAddProductActionPerformed(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(0, 255, 255));

        jlbAddNewProduct.setBackground(new java.awt.Color(0, 255, 255));
        jlbAddNewProduct.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jlbAddNewProduct.setForeground(new java.awt.Color(0, 0, 0));
        jlbAddNewProduct.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbAddNewProduct.setText("+");
        jlbAddNewProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlbAddNewProductMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jlbAddNewProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jlbAddNewProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel10.setFont(new java.awt.Font("Times New Roman", 3, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Trạng thái");

        jrbNotRedeemedStatus.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(jrbNotRedeemedStatus);
        jrbNotRedeemedStatus.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jrbNotRedeemedStatus.setForeground(new java.awt.Color(0, 0, 0));
        jrbNotRedeemedStatus.setText("Chưa chuộc");
        jrbNotRedeemedStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbProductStatusActionPerformed(evt);
            }
        });

        jrbRedeemedStatus.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(jrbRedeemedStatus);
        jrbRedeemedStatus.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jrbRedeemedStatus.setForeground(new java.awt.Color(0, 0, 0));
        jrbRedeemedStatus.setText("Đã chuộc");
        jrbRedeemedStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbProductStatusActionPerformed(evt);
            }
        });

        jrbLiquidatedStatus.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(jrbLiquidatedStatus);
        jrbLiquidatedStatus.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jrbLiquidatedStatus.setForeground(new java.awt.Color(0, 0, 0));
        jrbLiquidatedStatus.setText("Đã thanh lý");
        jrbLiquidatedStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbProductStatusActionPerformed(evt);
            }
        });

        jrbAllProductStatus.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(jrbAllProductStatus);
        jrbAllProductStatus.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
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
        jrbNewStaus.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jrbNewStaus.setForeground(new java.awt.Color(0, 0, 0));
        jrbNewStaus.setText("Mới");
        jrbNewStaus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbProductStatusActionPerformed(evt);
            }
        });

        jrbNeedToLiquidateStatus.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(jrbNeedToLiquidateStatus);
        jrbNeedToLiquidateStatus.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jrbNeedToLiquidateStatus.setForeground(new java.awt.Color(0, 0, 0));
        jrbNeedToLiquidateStatus.setText("Cần thanh lý");
        jrbNeedToLiquidateStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbProductStatusActionPerformed(evt);
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
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jtfProductID)
                            .addComponent(jtfProductName)
                            .addComponent(jcbTypeOfProduct, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jbtnAddProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jbtnEditProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(364, 364, 364))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jrbNotRedeemedStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jrbNeedToLiquidateStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jrbRedeemedStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jrbLiquidatedStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jrbAllProductStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jrbNewStaus, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jtfProductID, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbTypeOfProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jrbNotRedeemedStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jrbRedeemedStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jrbNewStaus, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jrbLiquidatedStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jrbAllProductStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jrbNeedToLiquidateStatus)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnAddProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnEditProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        jLabel3.setFont(new java.awt.Font("Times New Roman", 3, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Mã loại");

        jtfTypeOfProductName.setBackground(new java.awt.Color(255, 255, 255));
        jtfTypeOfProductName.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jtfTypeOfProductName.setForeground(new java.awt.Color(0, 0, 0));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 3, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Tên loại");

        jtfTypeOfProductID.setBackground(new java.awt.Color(255, 255, 255));
        jtfTypeOfProductID.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jtfTypeOfProductID.setForeground(new java.awt.Color(0, 0, 0));

        jbtnEditTypeOfProduct.setBackground(new java.awt.Color(0, 255, 255));
        jbtnEditTypeOfProduct.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jbtnEditTypeOfProduct.setForeground(new java.awt.Color(0, 0, 0));
        jbtnEditTypeOfProduct.setText("Sửa");
        jbtnEditTypeOfProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEditTypeOfProductActionPerformed(evt);
            }
        });

        jbtnAddTypeOfProduct.setBackground(new java.awt.Color(0, 255, 255));
        jbtnAddTypeOfProduct.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
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
        jbtnReloadAll.setText("Tải lại");
        jbtnReloadAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnReloadAllActionPerformed(evt);
            }
        });

        jPanel9.setBackground(new java.awt.Color(0, 255, 255));

        jlbAddNewTypeOfProduct.setBackground(new java.awt.Color(0, 255, 255));
        jlbAddNewTypeOfProduct.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jlbAddNewTypeOfProduct.setForeground(new java.awt.Color(0, 0, 0));
        jlbAddNewTypeOfProduct.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbAddNewTypeOfProduct.setText("+");
        jlbAddNewTypeOfProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlbAddNewTypeOfProductMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jlbAddNewTypeOfProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jlbAddNewTypeOfProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel11.setFont(new java.awt.Font("Times New Roman", 3, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Trạng thái");

        jrbDeleted.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup2.add(jrbDeleted);
        jrbDeleted.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jrbDeleted.setForeground(new java.awt.Color(0, 0, 0));
        jrbDeleted.setText("Ngưng phục vụ");
        jrbDeleted.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbTypeOfProductStatusActionPerformed(evt);
            }
        });

        jrbUnDelete.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup2.add(jrbUnDelete);
        jrbUnDelete.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jrbUnDelete.setForeground(new java.awt.Color(0, 0, 0));
        jrbUnDelete.setText("Phục vụ");
        jrbUnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbTypeOfProductStatusActionPerformed(evt);
            }
        });

        jrbAllDeleteflag.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup2.add(jrbAllDeleteflag);
        jrbAllDeleteflag.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jrbAllDeleteflag.setForeground(new java.awt.Color(0, 0, 0));
        jrbAllDeleteflag.setSelected(true);
        jrbAllDeleteflag.setText("Tất cả");
        jrbAllDeleteflag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbTypeOfProductStatusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfTypeOfProductName)
                                    .addComponent(jtfTypeOfProductID)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jrbAllDeleteflag, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jrbDeleted, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jrbUnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jbtnAddTypeOfProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jbtnEditTypeOfProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbtnReloadAll, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jtfTypeOfProductID, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfTypeOfProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jrbDeleted))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jrbUnDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jrbAllDeleteflag))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnAddTypeOfProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnEditTypeOfProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbtnReloadAll, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
        }

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1222, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 808, Short.MAX_VALUE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void jlbAddNewTypeOfProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbAddNewTypeOfProductMouseClicked
        if (evt.getClickCount() == 2) {
            setTypeOfProductDefault(null);
            jbtnAddTypeOfProduct.setEnabled(true);
            jtfTypeOfProductID.setText(TypeOfProductController.getCurrentInstance().getNewID());
            jtfTypeOfProductID.setEditable(false);
            setTypeOfProductStatus("0");
        }
    }//GEN-LAST:event_jlbAddNewTypeOfProductMouseClicked

    private void jtblTypeOfProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblTypeOfProductMouseClicked
        int row = jtblTypeOfProduct.getSelectedRow();
        if (evt.getClickCount() == 2 && row != -1) {
            JTable table = (JTable) evt.getSource();
            String id = (table.getModel().getValueAt(row, 1)).toString();
            TypeOfProduct typeOfProduct = TypeOfProductController.getCurrentInstance().getTypeOfProductByID(id);
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
            TypeOfProduct existingTypeOfProduct = TypeOfProductController.getCurrentInstance().getTypeOfProductByID(typeOfProduct.getId());
            if (existingTypeOfProduct == null) {
                if (TypeOfProductController.getCurrentInstance().getTypeOfProductByName(typeOfProduct.getName()) == null) {
                    if (TypeOfProductController.getCurrentInstance().insert(typeOfProduct)) {
                        MessageSupport.Message("Thông báo", "Thêm mới thành công.");
                        ActivityHistoryController.getCurrentInstance().insert(
                                new ActivityHistory(Support.dateToString(new Date(), Support.getDateTimeFormat()),
                                        StaticUser.getCurrentInstanceUser(), "Thêm mới", "Loại hàng hóa", typeOfProduct.toString()));
                        setTypeOfProductDefault(null);
                        setCBTypeOfProduct(TypeOfProductController.getCurrentInstance()
                                .findTypeOfProductByDeleteFlag(TypeOfProductController.getCurrentInstance().getList(), false));
                    }
                } else {
                    MessageSupport.ErrorMessage("Lỗi", "Tên loại hàng tồn tại.");
                }
            } else {
                MessageSupport.ErrorMessage("Lỗi", "Mã loại hàng tồn tại.");
            }
        }
    }//GEN-LAST:event_jbtnAddTypeOfProductActionPerformed

    private void jbtnEditTypeOfProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEditTypeOfProductActionPerformed
        TypeOfProduct typeOfProduct = getTypeOfProductFromForm();
        if (typeOfProduct != null) {
            if (TypeOfProductController.getCurrentInstance().getTypeOfProductByName(typeOfProduct.getName()) != null) {
                if (TypeOfProductController.getCurrentInstance().update(typeOfProduct)) {
                    MessageSupport.Message("Thông báo", "Sửa thành công.");
                    ActivityHistoryController.getCurrentInstance().insert(
                            new ActivityHistory(Support.dateToString(new Date(), Support.getDateTimeFormat()),
                                    StaticUser.getCurrentInstanceUser(), "Sửa", "Loại hàng hóa", typeOfProduct.toString()));
                    setTypeOfProductDefault(null);
                    setCBTypeOfProduct(TypeOfProductController.getCurrentInstance()
                            .findTypeOfProductByDeleteFlag(TypeOfProductController.getCurrentInstance().getList(), false));
                }
            } else {
                MessageSupport.ErrorMessage("Lỗi", "Tên loại hàng tồn tại.");
            }

        }
    }//GEN-LAST:event_jbtnEditTypeOfProductActionPerformed

    private void jlbAddNewProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbAddNewProductMouseClicked
        if (evt.getClickCount() == 2) {
            setProductDefault(null);
            jbtnAddProduct.setEnabled(true);
            jtfProductID.setText(ProductController.getCurrentInstance().getNewID());
            jtfProductID.setEditable(false);
            setProductStatus("Mới");
        }
    }//GEN-LAST:event_jlbAddNewProductMouseClicked

    private void jbtnAddProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAddProductActionPerformed
        Product product = getProductFromForm();
        if (product != null) {
            if (!product.getTypeOfProduct().getDeleteflag()) {
                Product existingProduct = ProductController.getCurrentInstance().getProduct(product.getId());
                if (existingProduct == null) {
                    if (ProductController.getCurrentInstance().insert(product)) {
                        MessageSupport.Message("Thông báo", "Thêm mới thành công.");
                        ActivityHistoryController.getCurrentInstance().insert(
                                new ActivityHistory(Support.dateToString(new Date(), Support.getDateTimeFormat()),
                                        StaticUser.getCurrentInstanceUser(), "Thêm mới", "Hàng hóa", product.toString()));
                        setProductDefault(null);
                    }
                } else {
                    MessageSupport.ErrorMessage("Lỗi", "Mã hàng hóa tồn tại.");
                }
            } else {
                MessageSupport.ErrorMessage("Lỗi", "Loại hàng hóa đã ngưng phục vụ.");
            }
        }
    }//GEN-LAST:event_jbtnAddProductActionPerformed

    private void jbtnEditProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEditProductActionPerformed
        Product product = getProductFromForm();
        if (product != null) {
            if (!product.getTypeOfProduct().getDeleteflag()) {
                if (ProductController.getCurrentInstance().update(product)) {
                    MessageSupport.Message("Thông báo", "Sửa thành công.");
                    ActivityHistoryController.getCurrentInstance().insert(
                            new ActivityHistory(Support.dateToString(new Date(), Support.getDateTimeFormat()),
                                    StaticUser.getCurrentInstanceUser(), "Sửa", "Hàng hóa", product.toString()));
                    setProductDefault(null);
                }
            } else {
                MessageSupport.ErrorMessage("Lỗi", "Loại hàng hóa đã ngưng phục vụ.");
            }
        }
    }//GEN-LAST:event_jbtnEditProductActionPerformed

    private void jtblProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblProductMouseClicked
        int row = jtblProduct.getSelectedRow();
        if (evt.getClickCount() == 2 && row != -1) {
            JTable table = (JTable) evt.getSource();
            String id = (table.getModel().getValueAt(row, 1)).toString();
            Product product = ProductController.getCurrentInstance().getProduct(id);
            setProductDefault(product);
        }
    }//GEN-LAST:event_jtblProductMouseClicked

    private void jcbTypeOfProductItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbTypeOfProductItemStateChanged
        findProduct();
    }//GEN-LAST:event_jcbTypeOfProductItemStateChanged

    private void jrbProductStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbProductStatusActionPerformed
        findProduct();
    }//GEN-LAST:event_jrbProductStatusActionPerformed

    private void jrbTypeOfProductStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbTypeOfProductStatusActionPerformed
        findTypeOfProduct();
    }//GEN-LAST:event_jrbTypeOfProductStatusActionPerformed

    private void jbtnDeleteTabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDeleteTabActionPerformed
        JHomePageForm.jHomePageTabbedPane.remove(JHomePageForm.jHomePageTabbedPane.indexOfTab("Hàng hóa"));
    }//GEN-LAST:event_jbtnDeleteTabActionPerformed


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
    private javax.swing.JButton jbtnDeleteTab;
    private javax.swing.JButton jbtnEditProduct;
    private javax.swing.JButton jbtnEditTypeOfProduct;
    private javax.swing.JButton jbtnReloadAll;
    private javax.swing.JComboBox<String> jcbTypeOfProduct;
    private javax.swing.JLabel jlbAddNewProduct;
    private javax.swing.JLabel jlbAddNewTypeOfProduct;
    private javax.swing.JRadioButton jrbAllDeleteflag;
    private javax.swing.JRadioButton jrbAllProductStatus;
    private javax.swing.JRadioButton jrbDeleted;
    private javax.swing.JRadioButton jrbLiquidatedStatus;
    private javax.swing.JRadioButton jrbNeedToLiquidateStatus;
    private javax.swing.JRadioButton jrbNewStaus;
    private javax.swing.JRadioButton jrbNotRedeemedStatus;
    private javax.swing.JRadioButton jrbRedeemedStatus;
    private javax.swing.JRadioButton jrbUnDelete;
    private javax.swing.JTextArea jtaInformation;
    private javax.swing.JTable jtblProduct;
    private javax.swing.JTable jtblTypeOfProduct;
    private javax.swing.JTextField jtfProductID;
    private javax.swing.JTextField jtfProductName;
    private javax.swing.JTextField jtfTypeOfProductID;
    private javax.swing.JTextField jtfTypeOfProductName;
    // End of variables declaration//GEN-END:variables
}
