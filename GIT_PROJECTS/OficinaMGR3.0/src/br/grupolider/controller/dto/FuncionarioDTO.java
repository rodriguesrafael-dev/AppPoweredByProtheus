
package br.grupolider.controller.dto;

import br.grupolider.model.dao.FuncionarioDAO;
import br.grupolider.model.vo.FuncionarioVO;
import java.util.List;

/**
 *
 * @author rodrigues rafael
 */
public class FuncionarioDTO {
    
    protected FuncionarioDAO fd = new FuncionarioDAO();
    
    public List<FuncionarioVO> searchFuncionario() {
        return fd.searchFuncionario();
    }
    
    public FuncionarioVO returnCodFuncionario(String codfunc) {
        return fd.returnCodFuncionario(codfunc);
    }
    
}
