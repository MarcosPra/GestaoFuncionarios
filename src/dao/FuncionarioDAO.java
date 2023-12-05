package dao;
import dao.connection.ConexaoMySQL;
import model.Funcionario;
import model.Pessoa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.time.LocalDate;

public class FuncionarioDAO {
    // ... outros métodos

    public Boolean inserir(Funcionario funcionario) {
        try {
            // Passo 1: Obter o maior ID da tabela pessoa
            String consultaMaxId = "SELECT MAX(id) AS max_id FROM pessoa";
            Statement stmtMaxId = ConexaoMySQL.get().createStatement();
            ResultSet resultadoMaxId = stmtMaxId.executeQuery(consultaMaxId);

            long novoIdPessoa = 1; // Valor padrão se a tabela estiver vazia
            if (resultadoMaxId.next()) {
                novoIdPessoa = resultadoMaxId.getLong("max_id") + 1;
            }

            // Passo 2: Inserir na tabela pessoa
            String sqlPessoa = "INSERT INTO pessoa (id, nome_completo, data_nascimento, documento, pais, estado, cidade) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparacaoPessoa = ConexaoMySQL.get().prepareStatement(sqlPessoa);
            preparacaoPessoa.setLong(1, novoIdPessoa);
            preparacaoPessoa.setString(2, funcionario.getNomeCompleto());
           preparacaoPessoa.setDate(3, funcionario.getDataNascimento());
            preparacaoPessoa.setString(4, funcionario.getDocumento());
            preparacaoPessoa.setString(5, funcionario.getPais());
            preparacaoPessoa.setString(6, funcionario.getEstado());
            preparacaoPessoa.setString(7, funcionario.getCidade());

            preparacaoPessoa.executeUpdate();

            // Passo 3: Inserir na tabela funcionario
            String sqlFuncionario = "INSERT INTO funcionario (id_pessoa, cargo, salario) VALUES (?, ?, ?)";
            PreparedStatement preparacaoFuncionario = ConexaoMySQL.get().prepareStatement(sqlFuncionario);
            preparacaoFuncionario.setLong(1, novoIdPessoa);
            preparacaoFuncionario.setString(2, funcionario.getCargo());
            preparacaoFuncionario.setDouble(3, funcionario.getSalario());

            int contLinhasAfetadas = preparacaoFuncionario.executeUpdate();

            return contLinhasAfetadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Funcionario> selecionarTodos() {
            try {
                String sql = "SELECT * FROM funcionario ORDER BY id";
                Statement stmt = ConexaoMySQL.get().createStatement();
                ResultSet resultado = stmt.executeQuery(sql);

                ArrayList<Funcionario> listaFuncionarios = new ArrayList<>();
                while (resultado.next()) {
                    Funcionario funcionario = new Funcionario(
                            resultado.getString("nomeCompleto"),
                            resultado.getDate("dataNascimento").toLocalDate(),
                            resultado.getString("documento"),
                            resultado.getString("pais"),
                            resultado.getString("estado"),
                            resultado.getString("cidade"),
                            resultado.getString("cargo"),
                            resultado.getDouble("salario")
                    );
                    funcionario.setId(resultado.getLong("id"));
                    listaFuncionarios.add(funcionario);
                }

                return listaFuncionarios;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }

        public Boolean atualizar(Funcionario funcionario) {
            try {
                String sql = "UPDATE funcionario SET id_pessoa = ?, cargo = ?, salario = ? WHERE id = ?";
                PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
                preparacao.setLong(1, funcionario.getId());
                preparacao.setString(2, funcionario.getCargo());
                preparacao.setDouble(3, funcionario.getSalario());
                preparacao.setLong(4, funcionario.getId());

                int contLinhasAfetadas = preparacao.executeUpdate();
                return contLinhasAfetadas > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        public Boolean deletar(Long id) {
            try {
                String sql = "DELETE FROM funcionario WHERE id = ?";
                PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
                preparacao.setLong(1, id);

                int contLinhasAfetadas = preparacao.executeUpdate();
                return contLinhasAfetadas > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        public Funcionario selecionarPorId(Long id) {
            try {
                String sql = "SELECT * FROM funcionario WHERE id = ?";
                PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
                preparacao.setLong(1, id);

                ResultSet resultado = preparacao.executeQuery();
                if (resultado.next()) {
                    Funcionario funcionario = new Funcionario(
                            resultado.getString("nomeCompleto"),
                            resultado.getDate("dataNascimento").toLocalDate(),
                            resultado.getString("documento"),
                            resultado.getString("pais"),
                            resultado.getString("estado"),
                            resultado.getString("cidade"),
                            resultado.getString("cargo"),
                            resultado.getDouble("salario")
                    );
                    funcionario.setId(resultado.getLong("id"));
                    return funcionario;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
