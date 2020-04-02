package br.grupolider.model.table;

import br.grupolider.model.vo.ProdutoVO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author rodriguesrafael
 */
public class PesquisaProdutoTM extends AbstractTableModel {

    private static final int CODIGO = 0;
    private static final int DESCRICAO = 1;
    private static final int ESTOQUE = 2;
    private static final int UNIDADE = 3;

    @SuppressWarnings("FieldMayBeFinal")
    private List<ProdutoVO> linhas;

    private static final String[] colunas = new String[]{"CODIGO", "DESCRICAO", "ESTOQUE", "UN.MED."};

    public PesquisaProdutoTM() {
        this.linhas = new ArrayList<>();
    }

    public PesquisaProdutoTM(ArrayList<ProdutoVO> produto) {
        this.linhas = new ArrayList<>(produto);
    }

    @Override
    public int getRowCount() {
        return this.linhas.size();
    }

    @Override
    public int getColumnCount() {
        return PesquisaProdutoTM.colunas.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return PesquisaProdutoTM.colunas[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case CODIGO:
                return String.class;
            case DESCRICAO:
                return String.class;
            case ESTOQUE:
                return String.class;
            case UNIDADE:
                return String.class;
            default:
                throw new ArrayIndexOutOfBoundsException("columnIndex out of bounds.");
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ProdutoVO produto = this.linhas.get(rowIndex);
        switch (columnIndex) {
            case CODIGO:
                return produto.getCodigo();
            case DESCRICAO:
                return produto.getDescricao();
            case ESTOQUE:
                return produto.getSaldo_atual();
            case UNIDADE:
                return produto.getUn_medida();
            default:
                throw new ArrayIndexOutOfBoundsException("columnIndex out of bounds.");
        }
    }

    public ProdutoVO getValue(int rowIndex) {
        return this.linhas.get(rowIndex);
    }

    public int indexOf(ProdutoVO produto) {
        return this.linhas.indexOf(produto);
    }

    public void addProduto(ProdutoVO produto) {
        this.linhas.add(produto);
        this.fireTableRowsInserted(linhas.size() - 1, linhas.size() - 1);
    }

    //remove varias linhas ao mesmo tempo
    public void removeAll() {
        this.linhas.clear();
        fireTableDataChanged();
    }

}
