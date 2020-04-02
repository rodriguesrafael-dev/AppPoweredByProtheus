package br.grupolider.model.table;

import br.grupolider.model.vo.VeiculoParadoVO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author rodrigues rafael
 */
public class VeiculoParadoTM extends AbstractTableModel {

    private static final int STATUS = 0;
    private static final int ORDEM = 1;
    private static final int FROTA = 2;
    private static final int VEICULO = 3;
    private static final int PLACA = 4;
    private static final int FILIAL = 5;
    private static final int DATA = 6;
    private static final int HORA = 7;
    
    private final List<VeiculoParadoVO> rows;

    //sets the columns names    
    private static final String[] columns = new String[]{"S","O.S.", "FROTA", "VEICULO", "PLACA", "FILIAL", "DATA INICIO", "HORA"};

    public VeiculoParadoTM() {
        this.rows = new ArrayList<>();
    }

    public VeiculoParadoTM(List<VeiculoParadoVO> v) {
        this.rows = new ArrayList<>(v);
    }

    @Override
    public int getRowCount() {
        return this.rows.size();
    }

    @Override
    public int getColumnCount() {
        return VeiculoParadoTM.columns.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return VeiculoParadoTM.columns[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case STATUS:
                return String.class;
            case ORDEM:
                return String.class;
            case FROTA:
                return String.class;
            case VEICULO:
                return String.class;
            case PLACA:
                return String.class;
            case FILIAL:
                return String.class;
            case DATA:
                return String.class;
            case HORA:
                return String.class;
            default:
                throw new ArrayIndexOutOfBoundsException("columnIndex out of bounds.");
        }

    }

    //returns the column value
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        VeiculoParadoVO v = this.rows.get(rowIndex);
        switch (columnIndex) {
            case STATUS:
                return v.getStatus();
            case ORDEM:
                return v.getOrdem_servico();
            case FROTA:
                return v.getFrota();
            case VEICULO:
                return v.getDesc_veiculo();
            case PLACA:
                return v.getPlaca();
            case FILIAL:
                return v.getFilial();
            case DATA:
                return v.getData();
            case HORA:
                return v.getHora();
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
    public void addRow(VeiculoParadoVO v) {
        this.rows.add(v);
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
