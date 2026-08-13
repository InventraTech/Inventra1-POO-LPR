package org.inventra.dao;

import org.inventra.conexao.ConexaoBanco;
import org.inventra.modelo.RequisicaoMolde;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RequisicaoDAO {

    // 1. CREATE
    public void insertRequisicao(RequisicaoMolde requisicao){
        String sql = "INSERT INTO requisicoes (id_tipoRequisicao, quantidade_produto, motivo, status, data, hora, fk_funcionario_cadastro, fk_funcionario_aprovador, fk_produto) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try(Connection conexao = ConexaoBanco.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setInt(1, requisicao.getIdTipoRequisicao());
            stmt.setInt(2, requisicao.getQuantidadeProduto());
            stmt.setString(3, requisicao.getMotivo());
            stmt.setString(4, requisicao.getStatus());

            // Separa o LocalDateTime em LocalDate e LocalTime para salvar nas colunas separadas
            stmt.setObject(5, requisicao.getDataHora().toLocalDate());
            stmt.setObject(6, requisicao.getDataHora().toLocalTime());

            stmt.setInt(7, requisicao.getFkFuncionarioSolicitante());
            stmt.setInt(8, requisicao.getFkFuncionarioAprovador());
            stmt.setInt(9, requisicao.getFkProduto());

            stmt.executeUpdate();
            System.out.println("Requisição cadastrada com sucesso.");

        } catch (SQLException erro) {
            System.out.println("Erro ao salvar requisição.");
            erro.printStackTrace();
        }
    }

    // 2. READ
    public List<RequisicaoMolde> selectRequisicao(){
        List<RequisicaoMolde> array = new ArrayList<>();
        String sql = "SELECT * FROM requisicoes";

        try(Connection conexao = ConexaoBanco.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){

            while (rs.next()){
                int id = rs.getInt("id_requisicao");
                int id_tipo = rs.getInt("id_tipoRequisicao");
                int qtd = rs.getInt("quantidade_produto");
                String motivo = rs.getString("motivo");
                String status = rs.getString("status");

                // Lê a data e a hora individualmente e as combina em um LocalDateTime
                LocalDate data = rs.getObject("data", LocalDate.class);
                LocalTime hora = rs.getObject("hora", LocalTime.class);
                LocalDateTime dataHora = LocalDateTime.of(data, hora);

                int fk_func_cad = rs.getInt("fk_funcionario_cadastro");
                int fk_func_aprov = rs.getInt("fk_funcionario_aprovador");
                int fk_prod = rs.getInt("fk_produto");

                RequisicaoMolde requisicao = new RequisicaoMolde(id_tipo, qtd, motivo, status, dataHora, fk_func_cad, fk_func_aprov, fk_prod);
                array.add(requisicao);
            }

        } catch(SQLException erro){
            System.out.println("Erro ao consultar requisições.");
            erro.printStackTrace();
        }
        return array;
    }

    // 3. UPDATE
    public void updateRequisicao(String nomeColuna, String novoValor, int id_requisicao){
        String sql = "UPDATE requisicoes SET " + nomeColuna + " = ? WHERE id_requisicao = ?";

        try (Connection conexao = ConexaoBanco.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            if(nomeColuna.equalsIgnoreCase("id_tipoRequisicao")){
                stmt.setInt(1, Integer.parseInt(novoValor));
            }
            else if (nomeColuna.equalsIgnoreCase("quantidade_produto")){
                stmt.setInt(1, Integer.parseInt(novoValor));
            }
            else if (nomeColuna.equalsIgnoreCase("motivo")){
                stmt.setString(1, novoValor);
            }
            else if (nomeColuna.equalsIgnoreCase("status")){
                stmt.setString(1, novoValor);
            }
            else if (nomeColuna.equalsIgnoreCase("data")){
                LocalDate data = LocalDate.parse(novoValor);
                stmt.setObject(1, data);
            }
            else if (nomeColuna.equalsIgnoreCase("hora")){
                LocalTime hora = LocalTime.parse(novoValor);
                stmt.setObject(1, hora);
            }
            else if (nomeColuna.equalsIgnoreCase("fk_funcionario_cadastro")){
                stmt.setInt(1, Integer.parseInt(novoValor));
            }
            else if (nomeColuna.equalsIgnoreCase("fk_funcionario_aprovador")){
                stmt.setInt(1, Integer.parseInt(novoValor));
            }
            else if (nomeColuna.equalsIgnoreCase("fk_produto")){
                stmt.setInt(1, Integer.parseInt(novoValor));
            }

            stmt.setInt(2, id_requisicao);
            stmt.executeUpdate();
            System.out.println("Requisição atualizada!");

        } catch (SQLException erro) {
            System.out.println("Erro ao se conectar no banco.");
            erro.printStackTrace();
        }
    }

    // 4. DELETE
    public void deleteRequisicao(int id_requisicao){
        String sql = "DELETE FROM requisicoes WHERE id_requisicao = ?";

        try (Connection conexao = ConexaoBanco.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setInt(1, id_requisicao);
            int linhasExecutadas = stmt.executeUpdate();

            if(linhasExecutadas > 0){
                System.out.println("Alteração concluída com sucesso.");
            } else {
                System.out.println("Nenhuma requisição com o id " + id_requisicao + " foi encontrada.");
            }

        } catch (SQLException erro) {
            System.out.println("Erro ao conectar com o banco.");
            erro.printStackTrace();
        }
    }
}