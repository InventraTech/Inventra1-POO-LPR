package org.inventra.dao;

import org.inventra.conexao.ConexaoBanco;
import org.inventra.modelo.LoteMolde;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class LoteDAO {

    // 1. CREATE
    public void insertLote(LoteMolde lote){
        String sql = "INSERT INTO lote (fk_produto, numero_lote, qtd_inicial, qtd_atual, dt_entrada, dt_validade, valor_custo, nota_fiscal) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try(Connection conexao = ConexaoBanco.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setInt(1, lote.getFk_produto());
            stmt.setString(2, lote.getNumero_lote());
            stmt.setInt(3, lote.getQtd_inicial());
            stmt.setInt(4, lote.getQtd_atual());
            stmt.setObject(5, lote.getDt_entrada());
            stmt.setObject(6, lote.getDt_valide());
            stmt.setBigDecimal(7, lote.getValor_custo()); // Utilização correta do BigDecimal
            stmt.setString(8, lote.getNota_fiscal());

            stmt.executeUpdate();
            System.out.println("Cadastro de Lote concluído.");

        } catch (SQLException erro) {
            System.out.println("Erro ao salvar lote.");
            erro.printStackTrace();
        }
    }

    // 2. READ
    public List<LoteMolde> selectLote(){
        List<LoteMolde> array = new ArrayList<>();
        String sql = "SELECT * FROM lote";

        try(Connection conexao = ConexaoBanco.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){

            while (rs.next()){
                int id = rs.getInt("id_estoque");
                int fk_produto = rs.getInt("fk_produto");
                String numero_lote = rs.getString("numero_lote");
                int qtd_inicial = rs.getInt("qtd_inicial");
                int qtd_atual = rs.getInt("qtd_atual");
                LocalDate dt_entrada = rs.getObject("dt_entrada", LocalDate.class);
                LocalDate dt_validade = rs.getObject("dt_validade", LocalDate.class);
                BigDecimal valor_custo = rs.getBigDecimal("valor_custo"); // Recuperação do BigDecimal
                String nota_fiscal = rs.getString("nota_fiscal");

                LoteMolde lote = new LoteMolde(fk_produto, numero_lote, qtd_inicial, qtd_atual, dt_entrada, dt_validade, valor_custo, nota_fiscal);
                array.add(lote);
            }

        } catch(SQLException erro){
            System.out.println("Erro ao consultar lotes.");
            erro.printStackTrace();
        }
        return array;
    }

    // 3. UPDATE
    public void updateLote(String NomeColuna, String novoValor, int id_estoque){
        String sql = ("UPDATE lote SET "+NomeColuna+" = ? WHERE id_estoque = ?");

        try (Connection conexao = ConexaoBanco.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            if(NomeColuna.equalsIgnoreCase("fk_produto")){
                stmt.setInt(1, Integer.parseInt(novoValor));
            }
            else if (NomeColuna.equalsIgnoreCase("numero_lote")){
                stmt.setString(1, novoValor);
            }
            else if (NomeColuna.equalsIgnoreCase("qtd_inicial")){
                stmt.setInt(1, Integer.parseInt(novoValor));
            }
            else if (NomeColuna.equalsIgnoreCase("qtd_atual")){
                stmt.setInt(1, Integer.parseInt(novoValor));
            }
            else if (NomeColuna.equalsIgnoreCase("dt_entrada")){
                LocalDate dt = LocalDate.parse(novoValor);
                stmt.setObject(1, dt);
            }
            else if (NomeColuna.equalsIgnoreCase("dt_validade")){
                LocalDate dt = LocalDate.parse(novoValor);
                stmt.setObject(1, dt);
            }
            else if (NomeColuna.equalsIgnoreCase("valor_custo")){
                // Conversão de String de entrada para BigDecimal
                stmt.setBigDecimal(1, new BigDecimal(novoValor));
            }
            else if (NomeColuna.equalsIgnoreCase("nota_fiscal")){
                stmt.setString(1, novoValor);
            }

            stmt.setInt(2, id_estoque);
            stmt.executeUpdate();
            System.out.println("Lote atualizado!");

        } catch (SQLException erro) {
            System.out.println("Erro ao se conectar no banco.");
            erro.printStackTrace();
        }
    }

    // 4. DELETE
    public void deleteLote(int id_estoque){
        String sql = "DELETE FROM lote WHERE id_estoque = ?";

        try (Connection conexao = ConexaoBanco.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setInt(1, id_estoque);
            int linhasExecutadas = stmt.executeUpdate();

            if(linhasExecutadas > 0){
                System.out.println("Alteração concluída com sucesso.");
            } else {
                System.out.println("Nenhum lote com o id "+id_estoque+" foi encontrado.");
            }

        } catch (SQLException erro) {
            System.out.println("Erro ao conectar com o banco.");
            erro.printStackTrace();
        }
    }
}