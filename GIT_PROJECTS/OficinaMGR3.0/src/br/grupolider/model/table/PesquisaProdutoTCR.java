package br.grupolider.model.table;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author rodrigues rafael
 */
public class PesquisaProdutoTCR extends DefaultTableCellRenderer {

    private final JLabel c;

    public PesquisaProdutoTCR() {
        c = new JLabel();
        c.setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(
            JTable table,
            Object value,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column) {

        getFormat(table);

        c.setFont(new Font("Arial", Font.BOLD, 12));
        c.setText(String.valueOf(value).toUpperCase());
        c.setHorizontalAlignment(getColumnTextAlignment(column));
        c.setBackground(getRowColor(row));

        getStock(table, row, isSelected);

        return c;
    }

    public JTable getFormat(JTable table) {
        getHeaderDefault(table);
        getColumnModel(table);
        table.getParent().setBackground(new java.awt.Color(214, 217, 223));
        return table;
    }

    //table header default
    private JTable getHeaderDefault(JTable table) {
        JTableHeader tableHeader;
        tableHeader = table.getTableHeader();

        tableHeader.setForeground(new java.awt.Color(51, 51, 51));
        tableHeader.setFont(new Font("Segoe UI Semibold", Font.BOLD, 12));
        tableHeader.setReorderingAllowed(false);
        tableHeader.setResizingAllowed(false);

        DefaultTableCellRenderer defaultTCR;
        defaultTCR = (DefaultTableCellRenderer) tableHeader.getDefaultRenderer();
        defaultTCR.setHorizontalAlignment(SwingConstants.CENTER);

        return table;
    }

    //column model size
    private JTable getColumnModel(JTable table) {
        TableColumnModel columnModel;
        columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(30);   //column CODIGO			     
        columnModel.getColumn(1).setPreferredWidth(200);  //column DESCRICAO 
        columnModel.getColumn(2).setPreferredWidth(5);    //column ESTOQUE
        columnModel.getColumn(3).setPreferredWidth(5);    //column UNID
        return table;
    }

    private JLabel getStock(JTable table, int row, boolean isSelected) {
        if (table.getValueAt(row, 2).toString().compareTo("0") == 0) {
            if (!isSelected) {
                c.setBackground(new java.awt.Color(255, 120, 120));
                c.setForeground(new java.awt.Color(51, 51, 51));
            } else {
                c.setBackground(new java.awt.Color(51, 51, 51));
                c.setForeground(new java.awt.Color(255, 255, 255));
            }
            
        } else {
            if (!isSelected) {
                c.setBackground(new java.awt.Color(155, 253, 155));
                c.setForeground(new java.awt.Color(51, 51, 51));
            } else {
                c.setBackground(new java.awt.Color(51, 51, 51));
                c.setForeground(new java.awt.Color(255, 255, 255));
            }
            
        }
        return c;
    }

    //row color
    private Color getRowColor(int row) {
        if (row % 2 == 0) {
            return new java.awt.Color(220, 220, 220);
        }
        return new java.awt.Color(205, 205, 205);
    }

    //column alignment
    private int getColumnTextAlignment(int column) {
        Border padding = BorderFactory.createEmptyBorder(0, 10, 0, 10);
        c.setBorder(BorderFactory.createCompoundBorder(getBorder(), padding));
        switch (column) {
            case 0:
                return SwingConstants.RIGHT;
            case 1:
                return SwingConstants.LEFT;
            case 2:
                return SwingConstants.RIGHT;
            case 3:
                return SwingConstants.LEFT;
            default:
                return SwingConstants.RIGHT;
        }

    }

}
