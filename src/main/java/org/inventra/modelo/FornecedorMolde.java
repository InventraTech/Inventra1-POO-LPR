package org.inventra.modelo;

public class FornecedorMolde {
    private int id_fornecedor;
    private String nome_juridico;
    private String cnpj;
    private String email;
    private String telefone;
    private int nota_vpq;
    private int fk_regiao;

    public FornecedorMolde(int id_fornecedor, String nome_juridico, String cnpj, String email, String telefone, int nota_vpq, int fk_regiao) {
        this.id_fornecedor = id_fornecedor;
        this.nome_juridico = nome_juridico;
        this.cnpj = cnpj;
        this.email = email;
        this.telefone = telefone;
        this.nota_vpq = nota_vpq;
        this.fk_regiao = fk_regiao;
    }

    public FornecedorMolde(String nome_juridico, String cnpj, String email, String telefone, int nota_vpq, int fk_regiao) {
        this.nome_juridico = nome_juridico;
        this.cnpj = cnpj;
        this.email = email;
        this.telefone = telefone;
        this.nota_vpq = nota_vpq;
        this.fk_regiao = fk_regiao;
    }

    public int getId_fornecedor() {
        return id_fornecedor;
    }

    public String getNome_juridico() {
        return nome_juridico;
    }

    public void setNome_juridico(String nome_juridico) {
        this.nome_juridico = nome_juridico;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getNota_vpq() {
        return nota_vpq;
    }

    public void setNota_vpq(int nota_vpq) {
        this.nota_vpq = nota_vpq;
    }

    public int getFk_regiao() {
        return fk_regiao;
    }

    public void setFk_regiao(int fk_regiao) {
        this.fk_regiao = fk_regiao;
    }
}

