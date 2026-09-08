package org.inventra.dao;

import org.inventra.conexao.ConexaoBanco;
import org.inventra.modelo.FornecedorMolde;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FornecedorDAO {
    public void insertFornecedor(FornecedorMolde fornecedor){

        String sql = "INSERT INTO fornecedor (nome_juridico, cnpj, email, telefone, nota_vpq, fk_regiao) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexao = ConexaoBanco.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, fornecedor.getNome_juridico());
            stmt.setString(2, fornecedor.getCnpj());
            stmt.setString(3, fornecedor.getEmail());
            stmt.setString(4, fornecedor.getTelefone());
            stmt.setInt(5, fornecedor.getNota_vpq());
            stmt.setInt(6, fornecedor.getFk_regiao());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<FornecedorMolde> selectFornecedor(){
        List<FornecedorMolde> listaFornecedores = new ArrayList<>();

        String sql = "SELECT * FROM fornecedor ORDER BY id_fornecedor";

        try(Connection conexao = ConexaoBanco.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {

            while (rs.next()){
                int id = rs.getInt("id_fornecedor");
                String nome_juridico = rs.getString("nome_juridico");
                String cnpj = rs.getString("cnpj");
                String email = rs.getString("email");
                String telefone = rs.getString("telefone");
                int nota_vpq = rs.getInt("nota_vpq");
                int fk_regiao = rs.getInt("fk_regiao");

                FornecedorMolde novoFornecedor = new FornecedorMolde(id, nome_juridico, cnpj, email, telefone, nota_vpq, fk_regiao);
                listaFornecedores.add(novoFornecedor);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaFornecedores;
    }

    public void updateFornecedor(String NomeColuna, String novoValor, int id_fornecedor) {
        String sql = ("UPDATE fornecedor SET " + NomeColuna + " = ? WHERE id_fornecedor = ?");

        try (Connection conexao = ConexaoBanco.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            if (NomeColuna.equalsIgnoreCase("nome_juridico")) {
                stmt.setString(1, novoValor);
            }
            else if (NomeColuna.equalsIgnoreCase("cnpj")) {
                stmt.setString(1, novoValor);
            }
            else if (NomeColuna.equalsIgnoreCase("email")) {
                stmt.setString(1, novoValor);
            }
            else if (NomeColuna.equalsIgnoreCase("telefone")) {
                stmt.setString(1, novoValor);
            }
            else if (NomeColuna.equalsIgnoreCase("nota_vpq")) {
                stmt.setInt(1, Integer.parseInt(novoValor));
            }
            else if (NomeColuna.equalsIgnoreCase("fk_regiao")) {
                stmt.setInt(1, Integer.parseInt(novoValor));
            }

            //! Passa o segundo parâmetro, o ID
            stmt.setInt(2, id_fornecedor);

            stmt.executeUpdate();
            System.out.println("Fornecedor atualizado!");

        } catch (SQLException erro) {
            System.out.println("Erro ao se conectar no banco.");
            erro.printStackTrace();
        }
    }

    public void deleteFornecedor(int id_fornecedor){
        String sql = "DELETE FROM fornecedor WHERE id_fornecedor = ?";

        try (Connection conexao = ConexaoBanco.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setInt(1, id_fornecedor);

            int linhasExecutadas = stmt.executeUpdate();

            //O execute update guarda a quantidade de linhas afetadas.
            if(linhasExecutadas > 0){
                System.out.println("Alteração concluída com sucesso.");
            } else {
                System.out.println("Nenhum fornecedor com o id "+id_fornecedor+" foi encontrado");
            }

        } catch (SQLException erro) {
            System.out.println("Erro ao conectar com o banco.");
            erro.printStackTrace();
        }
    }
}

