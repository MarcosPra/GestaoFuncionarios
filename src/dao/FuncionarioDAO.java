package dao;
import dao.connection.ConexaoMySQL;
import model.Funcionario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

    public class FuncionarioDAO {
        public Boolean inserir(Funcionario funcionario) {
            try {
                String sql = "INSERT INTO funcionario (id_pessoa, cargo, salario) VALUES (?, ?, ?)";
                PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                preparacao.setLong(1, funcionario.getId());
                preparacao.setString(2, funcionario.getCargo());
                preparacao.setDouble(3, funcionario.getSalario());

                int contLinhasAfetadas = preparacao.executeUpdate();
                ResultSet generatedKeys = preparacao.getGeneratedKeys();

                if (generatedKeys.next()) {
                    funcionario.setId(generatedKeys.getLong(1));
                }

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
