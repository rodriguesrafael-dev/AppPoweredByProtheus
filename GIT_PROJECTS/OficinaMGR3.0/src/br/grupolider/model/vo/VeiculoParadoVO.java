package br.grupolider.model.vo;

/**
 *
 * @author rodrigues rafael
 */
public class VeiculoParadoVO {

    private String ordem_servico;
    private String frota;
    private String desc_veiculo;
    private String placa;
    private String filial;
    private String hora;
    private String data;
    private String status;

    public VeiculoParadoVO() {
    }

    public VeiculoParadoVO(String ordem_servico, String frota, String desc_veiculo, 
            String placa, String filial, String hora, String data, String status) {
        this.ordem_servico = ordem_servico;
        this.frota = frota;
        this.desc_veiculo = desc_veiculo;
        this.placa = placa;
        this.filial = filial;
        this.hora = hora;
        this.data = data;
        this.status = status;
    }

    public String getOrdem_servico() {
        return ordem_servico;
    }

    public void setOrdem_servico(String ordem_servico) {
        this.ordem_servico = ordem_servico;
    }

    public String getFrota() {
        return frota;
    }

    public void setFrota(String frota) {
        this.frota = frota;
    }

    public String getDesc_veiculo() {
        return desc_veiculo;
    }

    public void setDesc_veiculo(String desc_veiculo) {
        this.desc_veiculo = desc_veiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getFilial() {
        return filial;
    }

    public void setFilial(String filial) {
        this.filial = filial;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
