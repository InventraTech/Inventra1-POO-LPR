package org.inventra.dao;

import org.inventra.conexao.ConexaoBanco;
import org.inventra.modelo.FuncionarioMolde;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class FuncionarioDAO {
    // 1.CREATE
    /**
     * A classe insertFuncionario serve para inserir novos dados na tabela funcionários
     * @author Jorge Llanos
     */
    public void insertFuncionario(FuncionarioMolde funcionario){

        //String sql: É o código SQL que será enviado para ser executado no banco.
        String sql = "INSERT INTO funcionario (nome, senha, fk_setor, email, telefone, cpf, dt_admissao, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";


        try(Connection conexao = ConexaoBanco.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getSenha());
            stmt.setInt(3, funcionario.getFk_setor());
            stmt.setString(4, funcionario.getEmail());
            stmt.setString(5, funcionario.getTelefone());
            stmt.setString(6, funcionario.getCpf() );
            stmt.setObject(7, funcionario.getDt_admissao());
            stmt.setString(8, funcionario.getStatus());

            stmt.executeUpdate();
            System.out.println("Cadastro concluída.");

        } catch (SQLException erro) {
            System.out.println("Erro ao salvar funcionário.");
            erro.printStackTrace();
        }
    }

    // 2.READ
    /**
     * O metodo SelectFuncionario exibe as colunas presentes no banco e seus valores
     */
    public List<FuncionarioMolde> selectFuncionario(){
        List<FuncionarioMolde> array = new ArrayList<>();

        String sql = "SELECT * FROM funcionario";

        try(Connection conexao = ConexaoBanco.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){

            while (rs.next()){

                int id = rs.getInt("id_funcionario");
                String nome = rs.getString("nome");
                String senha = rs.getString("senha");
                String email = rs.getString("email");
                String telefone = rs.getString("telefone");
                String cpf = rs.getString("cpf");
                LocalDate dt_admissao = rs.getObject("dt_admissao", LocalDate.class);
                String status = rs.getString("status");
                int fk_setor = rs.getInt("fk_setor");

                FuncionarioMolde funcionario = new FuncionarioMolde(nome, senha, email, telefone, cpf, dt_admissao, status, fk_setor);
                array.add(funcionario);
            }

        } catch(SQLException erro){
            System.out.println("Erro ao consultar funcionário.");
        }
        return array;
    }

    // 3. UPDATE
    /**
     * @author Jorge LLanos
     */
    public void updateFuncionario(String NomeColuna, String novoValor, int id_funcionario){

        String sql = ("UPDATE funcionario SET "+NomeColuna+" = ? WHERE id_funcionario = ?");

        try (Connection conexao = ConexaoBanco.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            if(NomeColuna.equalsIgnoreCase("nome")){
                stmt.setString(1, novoValor);
            }
            else if (NomeColuna.equalsIgnoreCase("Senha")){
                stmt.setString(1, novoValor);
            }
            else if (NomeColuna.equalsIgnoreCase("FK_setor")){
                stmt.setInt(1, Integer.parseInt(novoValor));
                //Transforma a STRING novoValor em um INT para o banco.
            }
            else if (NomeColuna.equalsIgnoreCase("Email")){
                stmt.setString(1, novoValor);
            }
            else if (NomeColuna.equalsIgnoreCase("Telefone")){
            stmt.setString(1, novoValor);
            }
            else if (NomeColuna.equalsIgnoreCase("CPF")){
                stmt.setString(1, novoValor);
            }
            else if (NomeColuna.equalsIgnoreCase("dt_admissao")){
                LocalDate dt_admissao = LocalDate.parse(novoValor); // Converte o texto para LocalDate
                stmt.setObject(1, dt_admissao);
            }
            //! Passa o segundo parâmetro, o ID
            stmt.setInt(2, id_funcionario);

            stmt.executeUpdate();
            System.out.println("Funcionário atualizado!");

        } catch (SQLException erro) {
            System.out.println("Erro ao se conectar no banco.");
            erro.printStackTrace();
        }
    }

    // 4.DELETE
    /**
     * Esse método deleta um funcionário com base em seu número de ID
     * @author Jorge Llanos
     */
    public void deleteFuncionario(int id_funcionario){

        String sql = "DELETE FROM funcionario WHERE id_funcionario = ?";

        try (Connection conexao = ConexaoBanco.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setInt(1, id_funcionario);

            int linhasExecutadas = stmt.executeUpdate();

            //O execute update guarda a quantidade de linhas afetadas.
            if(linhasExecutadas > 0){
                System.out.println("Alteração concluída com sucesso.");
            } else {
                System.out.println("Nenhum funcionário com o id "+id_funcionario+" foi encontrado");
            }

        } catch (SQLException erro) {
            System.out.println("Erro ao conectar com o banco.");
            erro.printStackTrace();
        }
    }
}
