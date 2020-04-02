package br.grupolider.model.vo;

/**
 *
 * @author rodrigues rafael
 */
public class FuncionarioVO {

    private String matricula;
    private String nome;
    private String especialidade;

    public FuncionarioVO() {
    }

    public FuncionarioVO(String matricula, String nome, String especialidade) {
        this.matricula = matricula;
        this.nome = nome;
        this.especialidade = especialidade;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

}
