package br.grupolider.model.dao;

import br.grupolider.model.jdbc.GenericDAO;
import br.grupolider.model.vo.BemVO;
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
public class BemDAO extends GenericDAO {

    protected BemVO b = new BemVO();
    protected VariablesConverter vc = new VariablesConverter();

    public BemVO searchBem(String bem) {
        try {
            String select
                    = "SELECT "
                    + "TO_CHAR(st9.t9_codbem), "
                    + "TO_CHAR(st9.t9_nome), "
                    + "TO_CHAR(shb.hb_nome), "
                    + "TO_CHAR(st9.t9_poscont), "
                    + "TO_CHAR(st9.t9_dtultac), "
                    + "TO_CHAR(st9.t9_placa), "
                    + "TO_CHAR(st9.t9_anofab), "
                    + "TO_CHAR(st9.t9_chassi), "
                    + "TO_CHAR(st9.t9_renavam), "
                    + "TO_CHAR(tqr.tqr_desmod), "
                    + "TO_CHAR(st9.t9_modelo), "
                    + "TO_CHAR(st9.t9_serie), "
                    + "TO_CHAR(st7.t7_nome), "
                    + "TO_CHAR(st6.t6_nome) "
                    + "FROM "
                    + "PROTHEUS.st9010 st9 "
                    + "INNER JOIN "
                    + "PROTHEUS.shb010 shb "
                    + "ON "
                    + "st9.t9_centrab = shb.hb_cod "
                    + "INNER JOIN "
                    + "PROTHEUS.tqr010 tqr "
                    + "ON "
                    + "st9.t9_tipmod = tqr.tqr_tipmod "                   
                    + "INNER JOIN "
                    + "PROTHEUS.st7010 st7 "
                    + "ON "
                    + "st9.t9_fabrica = st7.t7_fabrica "
                    + "INNER JOIN "
                    + "PROTHEUS.st6010 st6 "
                    + "ON "
                    + "st9.t9_codfami = st6.t6_codfami "                  
                    + "WHERE "
                    + "TO_CHAR(st9.t9_codbem) = '" + bem + "'";

            b = null;
            ps = getConnection().prepareStatement(select);
            rs = ps.executeQuery();

            while (rs.next()) {
                b = new BemVO();
                
                String cod_bem = rs.getString("TO_CHAR(st9.t9_codbem)").trim();
                String desc_bem = rs.getString("TO_CHAR(st9.t9_nome)").trim();
                String filial = rs.getString("TO_CHAR(shb.hb_nome)").trim();
                String km = rs.getString("TO_CHAR(st9.t9_poscont)").trim();
                String dt_ult_km = rs.getString("TO_CHAR(st9.t9_dtultac)").trim();
                String placa = rs.getString("TO_CHAR(st9.t9_placa)").trim();
                String ano_fab = rs.getString("TO_CHAR(st9.t9_anofab)").trim();
                String chassi = rs.getString("TO_CHAR(st9.t9_chassi)").trim();
                String renavam = rs.getString("TO_CHAR(st9.t9_renavam)").trim();
                String desc_modelo = rs.getString("TO_CHAR(tqr.tqr_desmod)").trim();
                String modelo = rs.getString("TO_CHAR(st9.t9_modelo)").trim();
                String serie = rs.getString("TO_CHAR(st9.t9_serie)").trim();
                String fabricante = rs.getString("TO_CHAR(st7.t7_nome)").trim();
                String tipo_veiculo = rs.getString("TO_CHAR(st6.t6_nome)").trim();
                
                b.setCod_bem(cod_bem);
                b.setDesc_bem(desc_bem);
                b.setFilial(filial);               
                b.setKm(vc.formatDoubleToString(Double.parseDouble(km)));                 
                b.setDt_ult_km(vc.formatData(dt_ult_km));                 
                b.setPlaca(placa);
                b.setAno_fab(ano_fab);
                b.setChassi(chassi);
                b.setRenavam(renavam);
                b.setDesc_modelo(desc_modelo);
                b.setModelo(modelo);
                b.setSerie(serie);
                b.setFabricante(fabricante);
                b.setTipo_veiculo(tipo_veiculo);
            }
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(BemDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(BemDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return b;
    }

    public List<BemVO> searchDescBem(String descricao) {
        ArrayList<BemVO> list = new ArrayList<>();
        try {
            String select
                    = "SELECT "
                    + "TO_CHAR(st9.t9_codbem), "
                    + "TO_CHAR(st9.t9_nome), "
                    + "TO_CHAR(shb.hb_nome), "
                    + "TO_CHAR(st9.t9_poscont), "
                    + "TO_CHAR(st9.t9_dtultac), "
                    + "TO_CHAR(st9.t9_placa), "
                    + "TO_CHAR(st9.t9_anofab), "
                    + "TO_CHAR(st9.t9_chassi), "
                    + "TO_CHAR(st9.t9_renavam), "
                    + "TO_CHAR(tqr.tqr_desmod), "
                    + "TO_CHAR(st9.t9_modelo), "
                    + "TO_CHAR(st9.t9_serie), "
                    + "TO_CHAR(st6.t6_nome) "
                    + "FROM "
                    + "PROTHEUS.st9010 st9 "
                    + "INNER JOIN "
                    + "PROTHEUS.shb010 shb "
                    + "ON "
                    + "st9.t9_centrab = shb.hb_cod "
                    + "INNER JOIN "
                    + "PROTHEUS.tqr010 tqr "
                    + "ON "
                    + "st9.t9_tipmod = tqr.tqr_tipmod "
                    + "INNER JOIN "
                    + "PROTHEUS.st6010 st6 "
                    + "ON "
                    + "st9.t9_codfami = st6.t6_codfami "
                    + "WHERE "
                    + "st9.R_E_C_D_E_L_ = '0' "
                    + "AND "
                    + "(TO_CHAR(st9.t9_placa) LIKE '%" + descricao.replaceAll(" ", "%") + "%'"
                    + "OR TO_CHAR(st9.t9_serie) LIKE '%" + descricao.replaceAll(" ", "%") + "%'"
                    + "OR TO_CHAR(st9.t9_renavam) LIKE '%" + descricao.replaceAll(" ", "%") + "%'"
                    + "OR TO_CHAR(st9.t9_nome) LIKE '%" + descricao.replaceAll(" ", "%") + "%')";

            b = null;
            ps = getConnection().prepareStatement(select);
            rs = ps.executeQuery();

            while (rs.next()) {
                b = new BemVO();
                
                String cod_bem = rs.getString("TO_CHAR(st9.t9_codbem)").trim();
                String desc_bem = rs.getString("TO_CHAR(st9.t9_nome)").trim();
                String filial = rs.getString("TO_CHAR(shb.hb_nome)").trim();
                String km = rs.getString("TO_CHAR(st9.t9_poscont)").trim();
                String dt_ult_km = rs.getString("TO_CHAR(st9.t9_dtultac)").trim();
                String placa = rs.getString("TO_CHAR(st9.t9_placa)").trim();
                String ano_fab = rs.getString("TO_CHAR(st9.t9_anofab)").trim();
                String chassi = rs.getString("TO_CHAR(st9.t9_chassi)").trim();
                String renavam = rs.getString("TO_CHAR(st9.t9_renavam)").trim();
                String desc_modelo = rs.getString("TO_CHAR(tqr.tqr_desmod)").trim();
                String modelo = rs.getString("TO_CHAR(st9.t9_modelo)").trim();
                String serie = rs.getString("TO_CHAR(st9.t9_serie)").trim();
                String tipo_veiculo = rs.getString("TO_CHAR(st6.t6_nome)").trim(); 
                
                b.setCod_bem(cod_bem);
                b.setDesc_bem(desc_bem);
                b.setFilial(filial);
                b.setKm(km);
                b.setDt_ult_km(dt_ult_km);
                b.setPlaca(placa);
                b.setAno_fab(ano_fab);
                b.setChassi(chassi);
                b.setRenavam(renavam);
                b.setDesc_modelo(desc_modelo);
                b.setModelo(modelo);
                b.setSerie(serie);
                b.setTipo_veiculo(tipo_veiculo);
                
                list.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BemDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(BemDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

}
