package br.grupolider.model.table;

import br.grupolider.model.vo.FuncionarioVO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author rodrigues rafael
 */
public class FuncionarioTM extends AbstractTableModel {

    private static final int MATRICULA = 0;
    private static final int NOME = 1;
    private static final int ESPECIALIDADE = 2;

    @SuppressWarnings("FieldMayBeFinal")
    private List<FuncionarioVO> linhas;

    private static final String[] colunas = new String[]{"MATRICULA", "NOME", "ESPECIALIDADE"};

    public FuncionarioTM() {
        this.linhas = new ArrayList<>();
    }

    public FuncionarioTM(ArrayList<FuncionarioVO> funcionario) {
        this.linhas = new ArrayList<>(funcionario);
    }

    @Override
    public int getRowCount() {
        return this.linhas.size();
    }

    @Override
    public int getColumnCount() {
        return FuncionarioTM.colunas.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return FuncionarioTM.colunas[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case MATRICULA:
                return String.class;
            case NOME:
                return String.class;
            case ESPECIALIDADE:
                return String.class;
            default:
                throw new ArrayIndexOutOfBoundsException("columnIndex out of bounds.");
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        FuncionarioVO funcionario = this.linhas.get(rowIndex);
        switch (columnIndex) {
            case MATRICULA:
                return funcionario.getMatricula();
            case NOME:
                return funcionario.getNome();
            case ESPECIALIDADE:
                return funcionario.getEspecialidade();
            default:
                throw new ArrayIndexOutOfBoundsException("columnIndex out of bounds.");
        }
    }

    public FuncionarioVO getValue(int rowIndex) {
        return this.linhas.get(rowIndex);
    }

    public int indexOf(FuncionarioVO funcionario) {
        return this.linhas.indexOf(funcionario);
    }

    public void addProduto(FuncionarioVO funcionario) {
        this.linhas.add(funcionario);
        this.fireTableRowsInserted(linhas.size() - 1, linhas.size() - 1);
    }

    //remove varias linhas ao mesmo tempo
    public void removeAll() {
        this.linhas.clear();
        fireTableDataChanged();
    }

}
