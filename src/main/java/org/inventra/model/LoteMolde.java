package org.inventra.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LoteMolde {
    private int id_lote;
    private int fk_produto;
    private String numero_lote;
    private int qtd_inicial;
    private int qtd_atual;
    private LocalDate dt_entrada;
    private LocalDate dt_validade;
    private BigDecimal valor_compra;
    private String nota_fiscal;

    //VÁRIAVEL PARA SUBSTITUIR A FK
    private String produto;

    // Construtor completo conforme seu padrão (sem id_estoque no construtor)
    public LoteMolde(int fk_produto, String numero_lote, int qtd_inicial, int qtd_atual, LocalDate dt_entrada, LocalDate dt_valide, BigDecimal valor_custo, String nota_fiscal) {
        this.fk_produto = fk_produto;
        this.numero_lote = numero_lote;
        this.qtd_inicial = qtd_inicial;
        this.qtd_atual = qtd_atual;
        this.dt_entrada = dt_entrada;
        this.dt_validade = dt_valide;
        this.valor_compra = valor_custo;
        this.nota_fiscal = nota_fiscal;
    }

    public LoteMolde(int id_estoque, int fk_produto, String numero_lote, int qtd_inicial, int qtd_atual, LocalDate dt_entrada, LocalDate dt_valide, BigDecimal valor_compra, String nota_fiscal) {
        this.id_lote = id_estoque;
        this.fk_produto = fk_produto;
        this.numero_lote = numero_lote;
        this.qtd_inicial = qtd_inicial;
        this.qtd_atual = qtd_atual;
        this.dt_entrada = dt_entrada;
        this.dt_validade = dt_valide;
        this.valor_compra = valor_compra;
        this.nota_fiscal = nota_fiscal;
    }

    public LoteMolde(int id_estoque, String produto, String numero_lote, int qtd_inicial, int qtd_atual, LocalDate dt_entrada, LocalDate dt_valide, BigDecimal valor_compra, String nota_fiscal) {
        this.id_lote = id_estoque;
        this.produto = produto;
        this.numero_lote = numero_lote;
        this.qtd_inicial = qtd_inicial;
        this.qtd_atual = qtd_atual;
        this.dt_entrada = dt_entrada;
        this.dt_validade = dt_valide;
        this.valor_compra = valor_compra;
        this.nota_fiscal = nota_fiscal;
    }

    public int getId_lote() {
        return id_lote;
    }

    public void setId_lote(int id_lote) {
        this.id_lote = id_lote;
    }

    public int getFk_produto() {
        return fk_produto;
    }

    public void setFk_produto(int fk_produto) {
        this.fk_produto = fk_produto;
    }

    public String getNumero_lote() {
        return numero_lote;
    }

    public void setNumero_lote(String numero_lote) {
        this.numero_lote = numero_lote;
    }

    public int getQtd_inicial() {
        return qtd_inicial;
    }

    public void setQtd_inicial(int qtd_inicial) {
        this.qtd_inicial = qtd_inicial;
    }

    public int getQtd_atual() {
        return qtd_atual;
    }

    public void setQtd_atual(int qtd_atual) {
        this.qtd_atual = qtd_atual;
    }

    public LocalDate getDt_entrada() {
        return dt_entrada;
    }

    public void setDt_entrada(LocalDate dt_entrada) {
        this.dt_entrada = dt_entrada;
    }

    public LocalDate getDt_validade() {
        return dt_validade;
    }

    public void setDt_validade(LocalDate dt_validade) {
        this.dt_validade = dt_validade;
    }

    public BigDecimal getValor_compra() {
        return valor_compra;
    }

    public void setValor_compra(BigDecimal valor_compra) {
        this.valor_compra = valor_compra;
    }

    public String getNota_fiscal() {
        return nota_fiscal;
    }

    public void setNota_fiscal(String nota_fiscal) {
        this.nota_fiscal = nota_fiscal;
    }

    public String getProduto() {
        return produto;
    }
}