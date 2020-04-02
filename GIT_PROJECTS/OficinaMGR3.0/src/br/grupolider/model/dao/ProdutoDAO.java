package br.grupolider.model.dao;

import br.grupolider.model.jdbc.GenericDAO;
import br.grupolider.model.vo.ProdutoVO;
import br.grupolider.util.VariablesConverter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rodrigues rafael
 */
public class ProdutoDAO extends GenericDAO {

    protected ProdutoVO p = new ProdutoVO();
    protected VariablesConverter vc = new VariablesConverter();
    
    public ProdutoVO searchProduto(String codigo) {
        try {
            String select
                    = "SELECT "
                    + "TO_CHAR(sb2.b2_cod), "
                    + "TO_CHAR(sb1.b1_desc), "
                    + "TO_CHAR(sb2.b2_qatu), "
                    + "TO_CHAR(sb1.b1_um), "
                    + "TO_CHAR(sb2.b2_qtsegum) "
                    + "FROM "
                    + "PROTHEUS.sb2010 sb2 "
                    + "INNER JOIN "
                    + "PROTHEUS.sb1010 sb1 "
                    + "ON "
                    + "sb1.b1_cod = sb2.b2_cod "
                    + "WHERE "
                    + "TO_CHAR(sb2.b2_cod) = '" + codigo + "'";

            p = null;
            ps = getConnection().prepareStatement(select);
            rs = ps.executeQuery();

            while (rs.next()) {
                p = new ProdutoVO();
                
                String cod = rs.getString("TO_CHAR(sb2.b2_cod)").trim();
                String desc = rs.getString("TO_CHAR(sb1.b1_desc)").trim();
                String saldo = rs.getString("TO_CHAR(sb2.b2_qatu)").trim();
                String un_med = rs.getString("TO_CHAR(sb1.b1_um)").trim();
                String embalagem = rs.getString("TO_CHAR(sb2.b2_qtsegum)").trim();

                p.setCodigo(cod);
                p.setDescricao(desc);
                p.setSaldo_atual(vc.formatDoubleToString(Double.parseDouble(saldo)));
                p.setUn_medida(un_med);
                p.setQuant_embalagem(embalagem);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return p;
    }

    public List<ProdutoVO> searchDescProduto(String descricao) {
        ArrayList<ProdutoVO> list = new ArrayList<>();
        try {
            String select
                    = "SELECT "
                    + "TO_CHAR(sb1.b1_cod), "
                    + "TO_CHAR(sb1.b1_desc), "
                    + "TO_CHAR(sb1.b1_um), "
                    + "TO_CHAR(sb2.b2_qatu) "
                    + "FROM "
                    + "PROTHEUS.sb2010 sb2 "
                    + "INNER JOIN "
                    + "PROTHEUS.sb1010 sb1 "
                    + "ON "
                    + "sb1.b1_cod = sb2.b2_cod "
                    + "WHERE "
                    + "TO_CHAR(sb1.b1_grupo) = '0091' "
                    + "AND "
                    + "TO_CHAR(sb2.b2_local) = '20' "
                    + "AND "
                    + "(TO_CHAR(sb1.b1_cod) LIKE '%" + descricao.replaceAll(" ", "%") + "%' "
                    + "OR TO_CHAR(sb1.b1_desc) LIKE '%" + descricao.replaceAll(" ", "%") + "%')";

            p = null;
            ps = getConnection().prepareStatement(select);
            rs = ps.executeQuery();

            while (rs.next()) {
                p = new ProdutoVO();
                
                String cod = rs.getString("TO_CHAR(sb1.b1_cod)").trim();
                String desc = rs.getString("TO_CHAR(sb1.b1_desc)").trim();
                String un_med = rs.getString("TO_CHAR(sb1.b1_um)").trim();
                String saldo = rs.getString("TO_CHAR(sb2.b2_qatu)").trim();

                p.setCodigo(cod);
                p.setDescricao(desc);
                p.setUn_medida(un_med);
                p.setSaldo_atual(vc.formatDoubleToString(Double.parseDouble(saldo)));
                
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public List<ProdutoVO> movimentacaoProduto(String codigo) {
        ArrayList<ProdutoVO> list = new ArrayList<>();
        try {
            String select
                    = "SELECT "
                    + "TO_CHAR(stj.tj_codbem), "
                    + "TO_CHAR(stl.tl_quantid), "
                    + "TO_CHAR(stl.tl_unidade), "
                    + "TO_CHAR(stl.tl_custo), "
                    + "TO_CHAR(stl.tl_ordem), "
                    + "TO_CHAR(stl.tl_dtinici) "
                    + "FROM "
                    + "PROTHEUS.stl010 stl "
                    + "INNER JOIN "
                    + "PROTHEUS.stj010 stj "
                    + "ON "
                    + "stl.tl_ordem = stj.tj_ordem "
                    + "WHERE "
                    + "stl.tl_dtinici >= '20190823' "
                    + "AND "
                    + "stj.tj_termino = 'S' "
                    + "AND "
                    + "TO_CHAR(stl.tl_codigo) = '" + codigo + "' "
                    + "ORDER BY "
                    + "stl.tl_dtinici DESC";

            p = null;
            ps = getConnection().prepareStatement(select);
            rs = ps.executeQuery();

            while (rs.next()) {
                p = new ProdutoVO();
                
                String codBem = rs.getString("TO_CHAR(stj.tj_codbem)").trim();
                String quant = rs.getString("TO_CHAR(stl.tl_quantid)").trim();
                String un_med = rs.getString("TO_CHAR(stl.tl_unidade)").trim();
                String custo = rs.getString("TO_CHAR(stl.tl_custo)").trim();
                String ordem_serv = rs.getString("TO_CHAR(stl.tl_ordem)").trim();
                String dataInicio = rs.getString("TO_CHAR(stl.tl_dtinici)").trim();
               
                p.setBem(codBem);
                p.setQuantidade(quant);
                p.setUn_medida(un_med);
                p.setCusto(custo);
                p.setOrdemServico(ordem_serv);
                p.setData(vc.formatData(dataInicio));
                
                list.add(p);
            }
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

}
