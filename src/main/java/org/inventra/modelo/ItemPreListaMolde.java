package org.inventra.modelo;

public class ItemPreListaMolde {
    private int id_item_prelista;
    private int fk_preLista;
    private int fk_fornecedor;
    private int fk_produto;
    private int qtd;

    public ItemPreListaMolde(int id_item_prelista, int fk_preLista, int fk_fornecedor, int fk_produto, int qtd) {
        this.id_item_prelista = id_item_prelista;
        this.fk_preLista = fk_preLista;
        this.fk_fornecedor = fk_fornecedor;
        this.fk_produto = fk_produto;
        this.qtd = qtd;
    }

    public ItemPreListaMolde(int fk_preLista, int fk_fornecedor, int fk_produto, int qtd) {
        this.fk_preLista = fk_preLista;
        this.fk_fornecedor = fk_fornecedor;
        this.fk_produto = fk_produto;
        this.qtd = qtd;
    }

    public int getId_item_prelista() {
        return id_item_prelista;
    }

    public int getFk_preLista() {
        return fk_preLista;
    }

    public void setFk_preLista(int fk_preLista) {
        this.fk_preLista = fk_preLista;
    }

    public int getFk_fornecedor() {
        return fk_fornecedor;
    }

    public void setFk_fornecedor(int fk_fornecedor) {
        this.fk_fornecedor = fk_fornecedor;
    }

    public int getFk_produto() {
        return fk_produto;
    }

    public void setFk_produto(int fk_produto) {
        this.fk_produto = fk_produto;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }
}
