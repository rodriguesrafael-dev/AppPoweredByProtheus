
package br.grupolider.model.dao;

import br.grupolider.model.jdbc.GenericDAO;
import br.grupolider.model.vo.FuncionarioVO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rodrigues rafael
 */
public class FuncionarioDAO extends GenericDAO {
    
    protected FuncionarioVO f = new FuncionarioVO();
    
    public List<FuncionarioVO> searchFuncionario() {
        ArrayList<FuncionarioVO> list = new ArrayList<>();
        try {
            String select
                    = "SELECT "
                    + "TO_CHAR(st1.t1_codfunc), "
                    + "TO_CHAR(st1.t1_nome),"
                    + "TO_CHAR(st0.t0_nome) "
                    + "FROM "
                    + "PROTHEUS.st1010 st1 "
                    + "INNER JOIN "
                    + "PROTHEUS.st2010 st2 "
                    + "ON "
                    + "st1.t1_codfunc = st2.t2_codfunc "
                    + "INNER JOIN "
                    + "PROTHEUS.st0010 st0 "
                    + "ON "
                    + "st2.t2_especia = st0.t0_especia "
                    + "WHERE "
                    + "TO_CHAR(st1.t1_equipe) = '000001' "
                    + "ORDER BY "
                    + "st1.t1_codfunc";

            f = null;
            ps = getConnection().prepareStatement(select);
            rs = ps.executeQuery();

            while (rs.next()) {
                f = new FuncionarioVO();
                
                String cod = rs.getString("TO_CHAR(st1.t1_codfunc)").trim();
                String nome = rs.getString("TO_CHAR(st1.t1_nome)").trim();
                String especialidade = rs.getString("TO_CHAR(st0.t0_nome)").trim();
                
                f.setMatricula(cod);
                f.setNome(nome);
                f.setEspecialidade(especialidade);
                
                list.add(f);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    
     public FuncionarioVO returnCodFuncionario(String codfunc) {
        try {
            String select
                    = "SELECT "
                    + "TO_CHAR(st1.t1_codfunc), "
                    + "TO_CHAR(st1.t1_nome),"
                    + "TO_CHAR(st0.t0_nome) "
                    + "FROM "
                    + "PROTHEUS.st1010 st1 "
                    + "INNER JOIN "
                    + "PROTHEUS.st2010 st2 "
                    + "ON "
                    + "st1.t1_codfunc = st2.t2_codfunc "
                    + "INNER JOIN "
                    + "PROTHEUS.st0010 st0 "
                    + "ON "
                    + "st2.t2_especia = st0.t0_especia "
                    + "WHERE "
                    + "TO_CHAR(st1.t1_equipe) = '000001' "
                    + "ORDER BY "
                    + "st1.t1_codfunc";

            f = null;
            ps = getConnection().prepareStatement(select);
            rs = ps.executeQuery();

            while (rs.next()) {
                f = new FuncionarioVO();
                
                String cod = rs.getString("TO_CHAR(st1.t1_codfunc)").trim();
                String nome = rs.getString("TO_CHAR(st1.t1_nome)").trim();
                String especialidade = rs.getString("TO_CHAR(st0.t0_nome)").trim();
                
                f.setMatricula(cod);
                f.setNome(nome);
                f.setEspecialidade(especialidade);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return f;
    }
     
}
