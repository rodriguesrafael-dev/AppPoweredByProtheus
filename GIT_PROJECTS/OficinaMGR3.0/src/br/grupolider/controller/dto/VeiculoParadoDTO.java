package br.grupolider.controller.dto;

import br.grupolider.model.dao.VeiculoParadoDAO;
import br.grupolider.model.vo.VeiculoParadoVO;
import java.util.List;

/**
 *
 * @author rodrigues rafael
 */
public class VeiculoParadoDTO {
    
    protected VeiculoParadoDAO vd = new VeiculoParadoDAO();
    
    public List<VeiculoParadoVO> getVeiculoParado() {
        return vd.getVeiculoParado();
    }
    
}
