
package br.grupolider.controller.dto;

import br.grupolider.model.dao.ProdutoDAO;
import br.grupolider.model.vo.ProdutoVO;
import java.util.List;

/**
 *
 * @author rodrigues rafael
 */
public class ProdutoDTO {
    
    protected ProdutoDAO pd = new ProdutoDAO();
    
    public ProdutoVO searchProduto(String codigo) {
        return pd.searchProduto(codigo);
    }
    
    public List<ProdutoVO> searchDescProduto(String descricao) {
        return pd.searchDescProduto(descricao);
    }
    
    public List<ProdutoVO> movimentacaoProduto(String codigo) {
        return pd.movimentacaoProduto(codigo);
    }
    
}
