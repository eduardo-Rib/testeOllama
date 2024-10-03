package org.example;

public class RG {

    private String cpf;
    private String rg;
    private String dataExpedicao;
    private String nome;
    private String pai;
    private String mae;
    private String orgaoExpeditor;
    private String dataNascimento;
    private String naturalidade;

    public RG(String cpf, String rg, String dataExpedicao, String nome, String pai, String mae, String orgaoExpeditor, String dataNascimento, String naturalidade) {
        this.cpf = cpf;
        this.rg = rg;
        this.dataExpedicao = dataExpedicao;
        this.nome = nome;
        this.pai = pai;
        this.mae = mae;
        this.orgaoExpeditor = orgaoExpeditor;
        this.dataNascimento = dataNascimento;
        this.naturalidade = naturalidade;
    }

    public void imprimir() {
        System.out.println("----- Detalhes do RG -----");
        System.out.println("CPF: " + cpf);
        System.out.println("RG: " + rg);
        System.out.println("Data de Expedição: " + dataExpedicao);
        System.out.println("Nome: " + nome);
        System.out.println("Pai: " + pai);
        System.out.println("Mãe: " + mae);
        System.out.println("Órgão Expedidor: " + orgaoExpeditor);
        System.out.println("Data de Nascimento: " + dataNascimento);
        System.out.println("Naturalidade: " + naturalidade);
        System.out.println("--------------------------");
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getDataExpedicao() {
        return dataExpedicao;
    }

    public void setDataExpedicao(String dataExpedicao) {
        this.dataExpedicao = dataExpedicao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPai() {
        return pai;
    }

    public void setPai(String pai) {
        this.pai = pai;
    }

    public String getMae() {
        return mae;
    }

    public void setMae(String mae) {
        this.mae = mae;
    }

    public String getOrgaoExpeditor() {
        return orgaoExpeditor;
    }

    public void setOrgaoExpeditor(String orgaoExpeditor) {
        this.orgaoExpeditor = orgaoExpeditor;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }
}
