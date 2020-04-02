package br.grupolider.model.table;

import br.grupolider.model.vo.BemVO;
import br.grupolider.model.vo.ProdutoVO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author rodrigues rafael
 */
public class PesquisaBemTM extends AbstractTableModel {

    private static final int BEM = 0;
    private static final int DESCRICAO = 1;
    private static final int PLACA = 2;
    private static final int SERIE = 3;
    private static final int RENAVAM = 4;

    @SuppressWarnings("FieldMayBeFinal")
    private List<BemVO> linhas;

    private static final String[] colunas = new String[]{"BEM", "DESCRICAO", "PLACA", "SERIE", "RENAVAM"};

    public PesquisaBemTM() {
        this.linhas = new ArrayList<>();
    }

    public PesquisaBemTM(ArrayList<BemVO> bem) {
        this.linhas = new ArrayList<>(bem);
    }

    @Override
    public int getRowCount() {
        return this.linhas.size();
    }

    @Override
    public int getColumnCount() {
        return PesquisaBemTM.colunas.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return PesquisaBemTM.colunas[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case BEM:
                return String.class;
            case DESCRICAO:
                return String.class;
            case PLACA:
                return String.class;
            case SERIE:
                return String.class;
            case RENAVAM:
                return String.class;
            default:
                throw new ArrayIndexOutOfBoundsException("columnIndex out of bounds.");
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        BemVO bem = this.linhas.get(rowIndex);
        switch (columnIndex) {
            case BEM:
                return bem.getCod_bem();
            case DESCRICAO:
                return bem.getDesc_bem();
            case PLACA:
                return bem.getPlaca();
            case SERIE:
                return bem.getSerie();
            case RENAVAM:
                return bem.getRenavam();
            default:
                throw new ArrayIndexOutOfBoundsException("columnIndex out of bounds.");
        }
    }

    public BemVO getValue(int rowIndex) {
        return this.linhas.get(rowIndex);
    }

    public int indexOf(ProdutoVO produto) {
        return this.linhas.indexOf(produto);
    }

    public void addProduto(BemVO bem) {
        this.linhas.add(bem);
        this.fireTableRowsInserted(linhas.size() - 1, linhas.size() - 1);
    }

    //remove varias linhas ao mesmo tempo
    public void removeAll() {
        this.linhas.clear();
        fireTableDataChanged();
    }

}
