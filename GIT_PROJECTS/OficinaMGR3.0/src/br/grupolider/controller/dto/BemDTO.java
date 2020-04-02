package br.grupolider.controller.dto;

import br.grupolider.model.dao.BemDAO;
import br.grupolider.model.vo.BemVO;
import java.util.List;

/**
 *
 * @author rodrigues raffael
 */
public class BemDTO {

    protected BemDAO bd = new BemDAO();

    public BemVO searchBem(String bem) {
        return bd.searchBem(bem);
    }

    public List<BemVO> searchDescBem(String descricao) {
        return bd.searchDescBem(descricao);
    }
    
}
