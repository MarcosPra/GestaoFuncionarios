package dao;
import dao.connection.ConexaoMySQL;
import model.Funcionario;
import model.Pessoa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

    public class FuncionarioDAO {
        public Boolean inserir(Funcionario funcionario) {
                try {
                    // Inserir na tabela pessoa
                    String sqlPessoa = "INSERT INTO pessoa (nome_completo, data_nascimento, documento, pais, estado, cidade) VALUES (?, ?, ?, ?, ?, ?)";
                    PreparedStatement preparacaoPessoa = ConexaoMySQL.get().prepareStatement(sqlPessoa, Statement.RETURN_GENERATED_KEYS);
                    preparacaoPessoa.setString(1, funcionario.getNomeCompleto());
                    preparacaoPessoa.setDate(2, java.sql.Date.valueOf(funcionario.getDataNascimento()));
                    preparacaoPessoa.setString(3, funcionario.getDocumento());
                    preparacaoPessoa.setString(4, funcionario.getPais());
                    preparacaoPessoa.setString(5, funcionario.getEstado());
                    preparacaoPessoa.setString(6, funcionario.getCidade());

                    int contLinhasAfetadasPessoa = preparacaoPessoa.executeUpdate();
                    ResultSet generatedKeys = preparacaoPessoa.getGeneratedKeys();
                    Long idPessoa = null;
                    if (generatedKeys.next()) {
                        idPessoa = generatedKeys.getLong(1);
                    }

                    if (idPessoa != null) {
                        // Inserir na tabela funcionario usando o ID da pessoa
                        String sqlFuncionario = "INSERT INTO funcionario (id_pessoa, cargo, salario) VALUES (?, ?, ?)";
                        PreparedStatement preparacaoFuncionario = ConexaoMySQL.get().prepareStatement(sqlFuncionario);
                        preparacaoFuncionario.setLong(1, idPessoa);
                        preparacaoFuncionario.setString(2, funcionario.getCargo());
                        preparacaoFuncionario.setDouble(3, funcionario.getSalario());

                        int contLinhasAfetadasFuncionario = preparacaoFuncionario.executeUpdate();

                        return contLinhasAfetadasFuncionario > 0;
                    } else {
                        // Tratar o caso em que não foi possível obter o ID da pessoa
                        return false;
                    }
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
