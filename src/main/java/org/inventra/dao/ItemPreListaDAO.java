package org.inventra.dao;

import org.inventra.conexao.ConexaoBanco;
import org.inventra.modelo.ItemPreListaMolde;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemPreListaDAO {
    //CREATE
    public void insertItemPreLista(ItemPreListaMolde ItemPreLista){

        String sql = "INSERT INTO item_pre_lista (fk_pre_lista, fk_fornecedor, fk_produto, qtd) VALUES(?, ?, ?, ?)";

        try(Connection conexao = ConexaoBanco.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setInt(1, ItemPreLista.getFk_preLista());
            stmt.setInt(2, ItemPreLista.getFk_fornecedor());
            stmt.setInt(3, ItemPreLista.getFk_produto());
            stmt.setInt(4, ItemPreLista.getQtd());

            stmt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //READ
    public List<ItemPreListaMolde> selectItemPreLista(){
        List<ItemPreListaMolde> listaItensPreLista = new ArrayList<>();

        String sql = "SELECT * FROM item_pre_lista";

        try (Connection conexao = ConexaoBanco.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()){

            while(rs.next()){

                int id_ItemPreLista = rs.getInt("id_item_lista");
                int fkPreLista = rs.getInt("fk_pre_lista");
                int fkFornecedor = rs.getInt("fk_fornecedor");
                int fkProduto = rs.getInt("fk_produto");
                int qtd = rs.getInt("qtd");

                ItemPreListaMolde novoItemPreLista = new ItemPreListaMolde(id_ItemPreLista, fkPreLista, fkFornecedor, fkProduto, qtd);
                listaItensPreLista.add(novoItemPreLista);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaItensPreLista;

    }

    public void updateItemPreLista(String NomeColuna, String novoValor, int id_item_prelista) {
        String sql = ("UPDATE item_pre_lista SET " + NomeColuna + " = ? WHERE id_item_lista = ?");

        try (Connection conexao = ConexaoBanco.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            if (NomeColuna.equalsIgnoreCase("fk_preLista")) {
                stmt.setInt(1, Integer.parseInt(novoValor));
            }
            else if (NomeColuna.equalsIgnoreCase("fk_fornecedor")) {
                stmt.setInt(1, Integer.parseInt(novoValor));
            }
            else if (NomeColuna.equalsIgnoreCase("fk_produto")) {
                stmt.setInt(1, Integer.parseInt(novoValor));
            }
            else if (NomeColuna.equalsIgnoreCase("qtd")) {
                stmt.setInt(1, Integer.parseInt(novoValor));
            }

            //! Passa o segundo parâmetro, o ID
            stmt.setInt(2, id_item_prelista);

            stmt.executeUpdate();
            System.out.println("Item da pré-lista atualizado!");

        } catch (SQLException erro) {
            System.out.println("Erro ao se conectar no banco.");
            erro.printStackTrace();
        }
    }

    //DELETE
    public void deleteItemPreLista(int id_itemPreLista){

        String sql = "DELETE FROM Item_pre_lista WHERE id_item_lista = ?";

        try(Connection conexao = ConexaoBanco.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setInt(1, id_itemPreLista);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
