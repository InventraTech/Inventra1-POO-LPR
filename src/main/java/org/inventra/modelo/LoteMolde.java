package org.inventra.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LoteMolde {
    private int id_estoque;
    private int fk_produto;
    private String numero_lote; // Mapeado a partir de numero_l... VARCHAR(80)
    private int qtd_inicial;
    private int qtd_atual;
    private LocalDate dt_entrada;
    private LocalDate dt_valide; // Mapeado a partir de dt_validade DATE
    private BigDecimal valor_custo; // Mapeado do tipo NUMERIC(10,2)
    private String nota_fiscal; // Mapeado a partir de nota_fis... VARCHAR(255)

    // Construtor completo conforme seu padrão (sem id_estoque no construtor)
    public LoteMolde(int fk_produto, String numero_lote, int qtd_inicial, int qtd_atual,
                     LocalDate dt_entrada, LocalDate dt_valide, BigDecimal valor_custo, String nota_fiscal) {
        this.fk_produto = fk_produto;
        this.numero_lote = numero_lote;
        this.qtd_inicial = qtd_inicial;
        this.qtd_atual = qtd_atual;
        this.dt_entrada = dt_entrada;
        this.dt_valide = dt_valide;
        this.valor_custo = valor_custo;
        this.nota_fiscal = nota_fiscal;
        id_estoque++;
    }

    public int getId_estoque() {
        return id_estoque;
    }

    public void setId_estoque(int id_estoque) {
        this.id_estoque = id_estoque;
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

    public LocalDate getDt_valide() {
        return dt_valide;
    }

    public void setDt_valide(LocalDate dt_valide) {
        this.dt_valide = dt_valide;
    }

    public BigDecimal getValor_custo() {
        return valor_custo;
    }

    public void setValor_custo(BigDecimal valor_custo) {
        this.valor_custo = valor_custo;
    }

    public String getNota_fiscal() {
        return nota_fiscal;
    }

    public void setNota_fiscal(String nota_fiscal) {
        this.nota_fiscal = nota_fiscal;
    }
}