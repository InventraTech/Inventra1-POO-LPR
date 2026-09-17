package org.inventra.dao;

import org.inventra.conexao.ConexaoBanco;
import org.inventra.model.ProdutoMolde;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    //! 1.CREATE
    public void inserirProduto(ProdutoMolde produto){

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

    public List<ProdutoMolde> listarProdutos(){
        List<ProdutoMolde> listaProdutos = new ArrayList<>();

        String sql = """
            SELECT p.id_produto, p.nome, m.nome AS nome_marca, c.nome AS nome_categoria, p.unidade_medida, p.estoque_min, p.estoque_max,
            	   f.nome_juridico AS nome_fornecedor, p.descricao, p.ativo
            FROM produto p
            JOIN marca m ON p.fk_marca = m.id_marca
            JOIN categoria c ON p.fk_categoria = c.id_categoria
            JOIN fornecedor f ON p.fk_fornecedor = f.id_fornecedor
            ORDER BY id_produto
        """;

        try (Connection conexao = ConexaoBanco.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()){

            while(rs.next()){
                int id = rs.getInt("id_produto");
                String nome = rs.getString("nome");
                String marca = rs.getString("nome_marca");
                String categoria = rs.getString("nome_categoria");
                String unidadeMedida = rs.getString("unidade_medida");
                int estoqueMin = rs.getInt("estoque_min");
                int estoqueMax = rs.getInt("estoque_max");
                String fornecedor = rs.getString("nome_fornecedor");
                String descricao = rs.getString("descricao");
                boolean ativo = rs.getBoolean("ativo");

                ProdutoMolde produto = new ProdutoMolde(id, nome, marca, categoria, unidadeMedida, estoqueMin, estoqueMax, fornecedor, descricao, ativo);
                listaProdutos.add(produto);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return listaProdutos;
    }

    //! 3.UPDATE
    public void atualizarProduto(String nomeColuna, String novoValor, int id_produto){

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
    public void deletarProduto(int id_produto){
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
