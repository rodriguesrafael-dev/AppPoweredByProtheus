package br.grupolider.model.vo;

/**
 *
 * @author rodrigues rafael
 */
public class ProdutoVO {

    private String codigo;
    private String descricao;
    private String un_medida;
    private String saldo_atual;
    private String quant_embalagem;
    private String Bem;
    private String quantidade;
    private String custo;
    private String OrdemServico;
    private String Data;

    public ProdutoVO() {
    }

    public ProdutoVO(String codigo, String descricao, String un_medida,
            String saldo_atual, String quant_embalagem, String Bem, String quantidade,
            String custo, String OrdemServico, String Data) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.un_medida = un_medida;
        this.saldo_atual = saldo_atual;
        this.quant_embalagem = quant_embalagem;
        this.Bem = Bem;
        this.quantidade = quantidade;
        this.custo = custo;
        this.OrdemServico = OrdemServico;
        this.Data = Data;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUn_medida() {
        return un_medida;
    }

    public void setUn_medida(String un_medida) {
        this.un_medida = un_medida;
    }

    public String getSaldo_atual() {
        return saldo_atual;
    }

    public void setSaldo_atual(String saldo_atual) {
        this.saldo_atual = saldo_atual;
    }

    public String getQuant_embalagem() {
        return quant_embalagem;
    }

    public void setQuant_embalagem(String quant_embalagem) {
        this.quant_embalagem = quant_embalagem;
    }

    public String getBem() {
        return Bem;
    }

    public void setBem(String Bem) {
        this.Bem = Bem;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getCusto() {
        return custo;
    }

    public void setCusto(String custo) {
        this.custo = custo;
    }

    public String getOrdemServico() {
        return OrdemServico;
    }

    public void setOrdemServico(String OrdemServico) {
        this.OrdemServico = OrdemServico;
    }

    public String getData() {
        return Data;
    }

    public void setData(String Data) {
        this.Data = Data;
    }

}
