package br.grupolider.model.dao;

import br.grupolider.model.jdbc.GenericDAO;
import br.grupolider.model.vo.VeiculoParadoVO;
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
public class VeiculoParadoDAO extends GenericDAO {

    protected VeiculoParadoVO vp = new VeiculoParadoVO();
    protected VariablesConverter vc = new VariablesConverter();

    public List<VeiculoParadoVO> getVeiculoParado() {
        ArrayList<VeiculoParadoVO> list = new ArrayList<>();
        try {
            String select
                    = "SELECT "
                    + "TO_CHAR(stj.tj_ordem), "
                    + "TO_CHAR(stj.tj_codbem), "
                    + "TO_CHAR(st9.t9_nome), "
                    + "TO_CHAR(st9.t9_placa), "
                    + "TO_CHAR(stj.tj_dtprini), "
                    + "TO_CHAR(stj.tj_hoprini), "
                    + "TO_CHAR(shb.hb_nome), "
                    + "TO_CHAR(stj.tj_yparado), "
                    + "TO_CHAR(stj.tj_situaca), "
                    + "TO_CHAR(stj.tj_termino), "
                    + "TO_CHAR(stj.tj_ymotivo) "
                    + "FROM "
                    + "PROTHEUS.stj010 stj "
                    + "INNER JOIN "
                    + "PROTHEUS.st9010 st9 "
                    + "ON "
                    + "stj.tj_codbem = st9.t9_codbem "
                    + "INNER JOIN "
                    + "PROTHEUS.shb010 shb "
                    + "ON "
                    + "st9.t9_centrab = shb.hb_cod "
                    + "INNER JOIN "
                    + "PROTHEUS.st6010 st6 "
                    + "ON "
                    + "st6.t6_codfami = st9.t9_codfami "
                    + "WHERE "
                    + "stj.tj_dtprini >= '20200101' "
                    + "AND "
                    + "stj.tj_yparado = '1' "
                    + "AND "
                    + "stj.tj_situaca = 'L' "
                    + "AND "
                    + "stj.tj_termino = 'N' "
                    + "AND "
                    + "st6.t6_nome "
                    + "IN "
                    + "('TRUCK', 'CAMINHAO', 'CARRETA', 'COLETOR DE LIXO (TOCO)', 'TRUCK CARGA SECA', "
                    + "'TRUCK TERMICO', 'BAU SEMI-REBOQUE TERMICO', "
                    + "'CAVALINHO TOCO', 'VEICULOS LEVES', 'MUCK TOCO', 'VAN', "
                    + "'VEICULO DE TRACAO', 'AUTOMOVEIS', 'SEMI - REBOQUE', "
                    + "'TOCO BAU CARGA SECA', 'TOCO BAU TERMICO', 'BASCULANTE TRUCK', "
                    + "'TOCO CARROCERIA', 'BAU SEMI-REBOQUE CARGA SECA', "
                    + "'CAVALINHO TRUCK', 'MUCK TRUCK', 'BAU 3/4 TERMICO', 'BAU 3/4 CARGA SECA', "
                    + "'BAU 3/4 CARROCERIA', 'FROTA DE TERCEIROS', "
                    + "'CARROCERIA 3/4', 'VEICULO DE TERCEIROS', 'C. ABERTA') "
                    + "ORDER BY "
                    + "stj.tj_dtprini DESC";

            vp = null;
            ps = getConnection().prepareStatement(select);
            rs = ps.executeQuery();

            while (rs.next()) {
                vp = new VeiculoParadoVO();
                String ordem = rs.getString("TO_CHAR(stj.tj_ordem)").trim();
                String frota = rs.getString("TO_CHAR(stj.tj_codbem)").trim();
                String desc = rs.getString("TO_CHAR(st9.t9_nome)").trim();
                String placa = rs.getString("TO_CHAR(st9.t9_placa)").trim();
                String filial = rs.getString("TO_CHAR(shb.hb_nome)").trim();
                String data = rs.getString("TO_CHAR(stj.tj_dtprini)").trim();
                String hora = rs.getString("TO_CHAR(stj.tj_hoprini)").trim();
                String status = rs.getString("TO_CHAR(stj.tj_ymotivo)").trim();
                
                vp.setOrdem_servico(ordem);
                vp.setFrota(frota);
                vp.setDesc_veiculo(desc);
                vp.setPlaca(placa);
                vp.setFilial(filial);
                vp.setData(vc.formatData(data));
                vp.setHora(hora);
                vp.setStatus(status);
                
                list.add(vp);
            }
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(VeiculoParadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(VeiculoParadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

}
