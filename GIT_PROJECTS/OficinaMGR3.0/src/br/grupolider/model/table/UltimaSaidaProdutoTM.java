package br.grupolider.model.table;

import br.grupolider.model.vo.ProdutoVO;
import br.grupolider.util.VariablesConverter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author rodrigues rafael
 */
public class UltimaSaidaProdutoTM extends AbstractTableModel {

    private static final int BEM = 0;
    private static final int QTD = 1;
    private static final int UNMED = 2;
    private static final int CUSTO = 3;
    private static final int OS = 4;
    private static final int DATA = 5;

    private final List<ProdutoVO> rows;

    VariablesConverter vc = new VariablesConverter();

    //sets the columns names    
    private static final String[] columns = new String[]{"BEM", "QTD", "UN.MED", "CUSTO", "O.S.", "DATA"};

    public UltimaSaidaProdutoTM() {
        this.rows = new ArrayList<>();
    }

    public UltimaSaidaProdutoTM(List<ProdutoVO> p) {
        this.rows = new ArrayList<>(p);
    }

    @Override
    public int getRowCount() {
        return this.rows.size();
    }

    @Override
    public int getColumnCount() {
        return UltimaSaidaProdutoTM.columns.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return UltimaSaidaProdutoTM.columns[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case BEM:
                return String.class;
            case QTD:
                return String.class;
            case UNMED:
                return String.class;
            case CUSTO:
                return String.class;
            case OS:
                return String.class;
            case DATA:
                return String.class;
            default:
                throw new ArrayIndexOutOfBoundsException("columnIndex out of bounds.");
        }

    }

    //returns the column value
    @Override
    @SuppressWarnings("static-access")
    public Object getValueAt(int rowIndex, int columnIndex) {
        ProdutoVO p = this.rows.get(rowIndex);
        switch (columnIndex) {
            case BEM:
                return p.getBem();
            case QTD:
                return p.getQuantidade();
            case UNMED:
                return p.getUn_medida();
            case CUSTO:
                return p.getCusto();
            case OS:
                return p.getOrdemServico();
            case DATA:
                return p.getData();
            default:
                throw new ArrayIndexOutOfBoundsException("columnIndex out of bounds.");
        }
        
    }

    //gets row value
    public Object getValue(int rowIndex) {
        return this.rows.get(rowIndex);
    }

    //gets row index
    public int indexOf(Object item) {
        return this.rows.indexOf(item);
    }

    //adds item into list
    public void addRow(ProdutoVO p) {
        this.rows.add(p);
        this.fireTableRowsInserted(rows.size() - 1, rows.size() - 1);
    }

    //removes item from row
    public void removeRowIndex(int rowIndex) {
        this.rows.remove(rowIndex);
        this.fireTableRowsDeleted(rowIndex, rowIndex);
    }

    //removes a lot of rows
    public void removeAllRow() {
        this.rows.clear();
        this.fireTableDataChanged();
    }

}
