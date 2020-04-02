package br.grupolider.model.vo;

/**
 *
 * @author rodrigues raffael
 */
public class BemVO {

    private String cod_bem;
    private String desc_bem;
    private String filial;
    private String km;
    private String dt_ult_km;
    private String placa;
    private String ano_fab;
    private String chassi;
    private String renavam;
    private String desc_modelo;
    private String modelo;
    private String serie;
    private String fabricante;
    private String tipo_veiculo;

    public BemVO() {
    }

    public BemVO(String cod_bem, String desc_bem, String filial, String km,
            String dt_ult_km, String placa, String ano_fab, String chassi,
            String renavam, String desc_modelo, String modelo, String serie, String fabricante, String tipo_veiculo) {
        this.cod_bem = cod_bem;
        this.desc_bem = desc_bem;
        this.filial = filial;
        this.km = km;
        this.dt_ult_km = dt_ult_km;
        this.placa = placa;
        this.ano_fab = ano_fab;
        this.chassi = chassi;
        this.renavam = renavam;
        this.desc_modelo = desc_modelo;
        this.modelo = modelo;
        this.serie = serie;
        this.fabricante = fabricante;
        this.tipo_veiculo = tipo_veiculo;
    }

    public String getCod_bem() {
        return cod_bem;
    }

    public void setCod_bem(String cod_bem) {
        this.cod_bem = cod_bem;
    }

    public String getDesc_bem() {
        return desc_bem;
    }

    public void setDesc_bem(String desc_bem) {
        this.desc_bem = desc_bem;
    }

    public String getFilial() {
        return filial;
    }

    public void setFilial(String filial) {
        this.filial = filial;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }

    public String getDt_ult_km() {
        return dt_ult_km;
    }

    public void setDt_ult_km(String dt_ult_km) {
        this.dt_ult_km = dt_ult_km;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getAno_fab() {
        return ano_fab;
    }

    public void setAno_fab(String ano_fab) {
        this.ano_fab = ano_fab;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    public String getRenavam() {
        return renavam;
    }

    public void setRenavam(String renavam) {
        this.renavam = renavam;
    }

    public String getDesc_modelo() {
        return desc_modelo;
    }

    public void setDesc_modelo(String desc_modelo) {
        this.desc_modelo = desc_modelo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }
    
    public String getTipo_veiculo() {
        return tipo_veiculo;
    }
    
    public void setTipo_veiculo(String tipo_veiculo) {
        this.tipo_veiculo = tipo_veiculo;
    }

}
