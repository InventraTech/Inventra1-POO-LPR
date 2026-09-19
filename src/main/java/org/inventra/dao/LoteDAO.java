package org.inventra.dao;

import org.inventra.conexao.ConexaoBanco;
import org.inventra.model.LoteMolde;
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
    public void inserirLote(LoteMolde lote){
        String sql = "INSERT INTO lote (fk_produto, numero_lote, qtd_inicial, qtd_atual, dt_entrada, dt_validade, valor_compra, nota_fiscal) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try(Connection conexao = ConexaoBanco.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setInt(1, lote.getFk_produto());
            stmt.setString(2, lote.getNumero_lote());
            stmt.setInt(3, lote.getQtd_inicial());
            stmt.setInt(4, lote.getQtd_atual());
            stmt.setObject(5, lote.getDt_entrada());
            stmt.setObject(6, lote.getDt_validade());
            stmt.setBigDecimal(7, lote.getValor_compra()); // Utilização correta do BigDecimal
            stmt.setString(8, lote.getNota_fiscal());

            stmt.executeUpdate();
            System.out.println("Cadastro de Lote concluído.");

        } catch (SQLException erro) {
            System.out.println("Erro ao salvar lote.");
            erro.printStackTrace();
        }
    }

    // 2. READ
    public List<LoteMolde> listarLote(){
        List<LoteMolde> array = new ArrayList<>();
        String sql = """
            SELECT l.id_lote, p.nome AS nome_produto, l.numero_lote, l.qtd_inicial, l.qtd_atual, l.dt_entrada, l.dt_validade, l.valor_compra, l.nota_fiscal
            FROM lote l
            JOIN produto p ON l.fk_produto = p.id_produto
            ORDER BY id_lote
        """;

        try(Connection conexao = ConexaoBanco.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){

            while (rs.next()){
                int id = rs.getInt("id_lote");
                String produto = rs.getString("nome_produto");
                String numero_lote = rs.getString("numero_lote");
                int qtd_inicial = rs.getInt("qtd_inicial");
                int qtd_atual = rs.getInt("qtd_atual");
                LocalDate dt_entrada = rs.getObject("dt_entrada", LocalDate.class);
                LocalDate dt_validade = rs.getObject("dt_validade", LocalDate.class);
                BigDecimal valor_custo = rs.getBigDecimal("valor_compra");
                String nota_fiscal = rs.getString("nota_fiscal");

                LoteMolde lote = new LoteMolde(id, produto, numero_lote, qtd_inicial, qtd_atual, dt_entrada, dt_validade, valor_custo, nota_fiscal);
                array.add(lote);
            }

        } catch(SQLException erro){
            System.out.println("Erro ao consultar lotes.");
            erro.printStackTrace();
        }
        return array;
    }

    // 3. UPDATE
    public void atualizarLote(String NomeColuna, String novoValor, int id_lote){
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
            else if (NomeColuna.equalsIgnoreCase("valor_compra")){
                BigDecimal valorBigDecimal = new BigDecimal(novoValor);
                stmt.setBigDecimal(1, valorBigDecimal);
            }
            else if (NomeColuna.equalsIgnoreCase("nota_fiscal")){
                stmt.setString(1, novoValor);
            }

            stmt.setInt(2, id_lote);
            stmt.executeUpdate();
            System.out.println("Lote atualizado!");

        } catch (SQLException erro) {
            System.out.println("Erro ao se conectar no banco.");
            erro.printStackTrace();
        }
    }

    // 4. DELETE
    public void deletarLote(int id_lote){
        String sql = "DELETE FROM lote WHERE id_lote = ?";

        try (Connection conexao = ConexaoBanco.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setInt(1, id_lote);
            int linhasExecutadas = stmt.executeUpdate();

            if(linhasExecutadas > 0){
                System.out.println("Alteração concluída com sucesso.");
            } else {
                System.out.println("Nenhum lote com o id "+id_lote+" foi encontrado.");
            }

        } catch (SQLException erro) {
            System.out.println("Erro ao conectar com o banco.");
            erro.printStackTrace();
        }
    }
}