/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Support;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.text.JTextComponent;

/**
 *
 * @author NVS
 */
@SuppressWarnings({"ClassWithoutLogger", "UtilityClassWithoutPrivateConstructor"})
public class ColorFormatSupport {

    public static void FormatTableHeader(JTable table) {
        JTableHeader jTableHeader = table.getTableHeader();
        jTableHeader.setBackground(Color.WHITE);
        jTableHeader.setForeground(Color.BLACK);
        jTableHeader.setFont(new Font("Times New Roman", Font.BOLD, 16));
    }

    public static void setDataTableCenter(JTable table) {
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        TableModel tableModel = table.getModel();
        for (int columnIndex = 0; columnIndex < tableModel.getColumnCount(); columnIndex++) {
            table.getColumnModel().getColumn(columnIndex).setCellRenderer(renderer);
        }
    }

    public static void setColorFormatEnable(JDateChooser jdaChooser, boolean isEnable, Color backgroundColor, Color foregrountColor) {
        ((JTextComponent) jdaChooser.getDateEditor().getUiComponent()).setEditable(isEnable);
        jdaChooser.getDateEditor().getUiComponent().setBackground(backgroundColor);
        jdaChooser.getDateEditor().getUiComponent().setForeground(foregrountColor);
    }

    public static void setColorFormatEnable(JComboBox<String> jcomboBox, boolean isEnable, Color backgroundColor, Color foregrountColor) {
        jcomboBox.getEditor().getEditorComponent().setEnabled(isEnable);
        jcomboBox.getEditor().getEditorComponent().setBackground(backgroundColor);        
        jcomboBox.getEditor().getEditorComponent().setForeground(foregrountColor);
    }
}
