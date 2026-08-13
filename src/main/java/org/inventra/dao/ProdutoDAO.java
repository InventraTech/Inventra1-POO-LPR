package org.inventra.dao;

import org.inventra.conexao.ConexaoBanco;
import org.inventra.modelo.ProdutoMolde;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProdutoDAO {
    //! 1.CREATE
    public void insertProduto(ProdutoMolde produto){

        String sql = "INSERT INTO produto (nome, fk_marca, fk_categoria, unidade_medida, estoque_min, estoque_max, fk_fornecedor, descricao, ativo) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try(Connection conexao = ConexaoBanco.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getFk_marca());
            stmt.setInt(3, produto.getFk_categoria());
            stmt.setString(4, produto.getUnidadeMedida());
            stmt.setInt(5, produto.getEstoqueMin());
            stmt.setInt(6, produto.getEstoqueMax());
            stmt.setInt(7, produto.getFk_fornecedor());
            stmt.setString(8, produto.getDescricao());
            stmt.setBoolean(9, produto.isAtivo());

            stmt.executeUpdate();
            System.out.println("Produto cadastrado");

        } catch (SQLException erro) {
            System.out.println("Erro ao cadastrar o produto");
            erro.printStackTrace();
        }
    }

    //2 SELECT

    public void selectProduto(){

    }

    //! 3.UPDATE
    public void updateProduto(String nomeColuna, String novoValor, int id_produto){

        String sql = ("UPDATE produto SET "+nomeColuna+" = ? WHERE id_produto = ?");

        try(Connection conexao = ConexaoBanco.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql)){

            if(nomeColuna.equalsIgnoreCase("nome")){
                stmt.setString(1, novoValor);
            }
            else if (nomeColuna.equalsIgnoreCase("FK_marca")){
                stmt.setInt(1, Integer.parseInt(novoValor));
            }
            else if (nomeColuna.equalsIgnoreCase("FK_categoria")){
                stmt.setInt(1, Integer.parseInt(novoValor));
            }
            else if (nomeColuna.equalsIgnoreCase("Unidade_medida")){
                stmt.setString(1, novoValor);
            }
            else if (nomeColuna.equalsIgnoreCase("estoque_min")){
                stmt.setInt(1, Integer.parseInt(novoValor));
            }
            else if (nomeColuna.equalsIgnoreCase("estoque_max")){
                stmt.setInt(1, Integer.parseInt(novoValor));
            }
            else if (nomeColuna.equalsIgnoreCase("fk_fornecedor")){
                stmt.setInt(1, Integer.parseInt(novoValor));
            }
            else if(nomeColuna.equalsIgnoreCase("descricao")){
                stmt.setString(1, novoValor);
            }

            stmt.setInt(2, id_produto);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    //! 4.DELETE
    public void deleteProduto(int id_produto){
        String sql = "DELETE FROM produto WHERE id_produto = ?";

        try(Connection conexao = ConexaoBanco.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setInt(1, id_produto);

            int linhasExecutadas = stmt.executeUpdate();

            if(linhasExecutadas > 0){
                System.out.println("Alteração concluída com sucesso.");
            } else {
                System.out.println("Nenhuma linha foi encontrada com o id "+id_produto);
            }

        } catch (SQLException erro) {
            System.out.println("Erro ao conectar o banco");
            erro.printStackTrace();
        }
    }
}
