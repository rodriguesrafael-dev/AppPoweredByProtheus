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
public class VeiculoParadoTCR extends DefaultTableCellRenderer {

    private final JLabel c;

    public VeiculoParadoTCR() {
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
        c.setForeground(new java.awt.Color(51, 51, 51));

        getColumnStatusColor(column, value);
        getRowStatusColor(table, row, column, isSelected);

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
        columnModel.getColumn(0).setPreferredWidth(1);     //column STATUS
        columnModel.getColumn(1).setPreferredWidth(30);    //column ORDEM			     
        columnModel.getColumn(2).setPreferredWidth(20);    //column FROTA 
        columnModel.getColumn(3).setPreferredWidth(350);   //column VEICULO
        columnModel.getColumn(4).setPreferredWidth(40);    //column PLACA
        columnModel.getColumn(5).setPreferredWidth(200);   //column FILIAL
        columnModel.getColumn(6).setPreferredWidth(50);    //column DATA
        columnModel.getColumn(7).setPreferredWidth(30);    //column HORA     
        return table;
    }

    //row background color
    private Color getRowColor(int row) {
        if (row % 2 == 0) {
            return new java.awt.Color(220, 220, 220);
        }
        return new java.awt.Color(205, 205, 205);
    }

    //row color
    private Color getColumnStatusColor(int column, Object value) {
        String str = (String) value;

        if (column == 0) {
            if (str.equalsIgnoreCase("1")) {
                c.setBackground(new java.awt.Color(0, 0, 255));//lime green
                c.setForeground(new java.awt.Color(0, 0, 255));//lime green
            } else if (str.equalsIgnoreCase("2")) {
                c.setBackground(new java.awt.Color(34, 139, 34));//dark red
                c.setForeground(new java.awt.Color(34, 139, 34));//dark red
            } else if (str.equalsIgnoreCase("3")) {
                c.setBackground(new java.awt.Color(255, 0, 0));//dark red
                c.setForeground(new java.awt.Color(255, 0, 0));//dark red
            } else if (str.equalsIgnoreCase("4")) {
                c.setBackground(new java.awt.Color(51, 51, 51));//dark red
                c.setForeground(new java.awt.Color(51, 51, 51));//dark red
            } else if (str.equalsIgnoreCase("")) {
                c.getBackground();
                c.getForeground();
            }

        }
        return null;
    }

    private JLabel getRowStatusColor(JTable table, int row, int column, boolean isSelected) {
        if (column != 0) {
            if (table.getValueAt(row, 0).toString().compareTo("1") == 0) {
                if (!isSelected) {
                    c.setForeground(new java.awt.Color(0, 0, 255)); //blue
                } else {
                    c.setBackground(new java.awt.Color(0, 102, 255)); //blue
                    c.setForeground(new java.awt.Color(255, 255, 255)); //white
                }

            } else if (table.getValueAt(row, 0).toString().compareTo("2") == 0) {
                if (!isSelected) {
                    c.setForeground(new java.awt.Color(34, 139, 34)); //green
                } else {
                    c.setBackground(new java.awt.Color(0, 102, 255)); //blue
                    c.setForeground(new java.awt.Color(255, 255, 255)); //white
                }

            } else if (table.getValueAt(row, 0).toString().compareTo("3") == 0) {
                if (!isSelected) {
                    c.setForeground(new java.awt.Color(255, 0, 0)); //red
                } else {
                    c.setBackground(new java.awt.Color(0, 102, 255)); //blue
                    c.setForeground(new java.awt.Color(255, 255, 255)); //white
                }

            } else if (table.getValueAt(row, 0).toString().compareTo("4") == 0) {
                if (!isSelected) {
                    c.setForeground(new java.awt.Color(51, 51, 51)); //dark grey
                } else {
                    c.setBackground(new java.awt.Color(0, 102, 255)); //blue
                    c.setForeground(new java.awt.Color(255, 255, 255)); //white
                }

            } else if (table.getValueAt(row, 0).toString().compareTo("") == 0) {
                if (!isSelected) {
                    c.getBackground();
                    c.getForeground();
                } else {
                    c.setBackground(new java.awt.Color(0, 102, 255));
                    c.setForeground(new java.awt.Color(255, 255, 255));
                }
            }
        }
        return c;
    }

    //column alignment
    private int getColumnTextAlignment(int column) {
        Border padding = BorderFactory.createEmptyBorder(0, 10, 0, 10);
        c.setBorder(BorderFactory.createCompoundBorder(getBorder(), padding));
        switch (column) {
            case 0:
                return SwingConstants.CENTER;
            case 1:
                return SwingConstants.RIGHT;
            case 2:
                return SwingConstants.RIGHT;
            case 3:
                return SwingConstants.LEFT;
            case 4:
                return SwingConstants.LEFT;
            case 5:
                return SwingConstants.LEFT;
            case 6:
                return SwingConstants.CENTER;
            case 7:
                return SwingConstants.CENTER;
            default:
                return SwingConstants.RIGHT;
        }

    }

}
