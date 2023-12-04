package controller;

import dao.FuncionarioDAO;
import model.Funcionario;

import java.time.LocalDate;
import java.util.ArrayList;

public class FuncionarioController {

    public String cadastrar(
            String nomeCompleto,
            LocalDate dataNascimento,
            String documento,
            String pais,
            String estado,
            String cidade,
            String cargo,
            Double salario
    ) {
        if (nomeCompleto == null || nomeCompleto.trim().equals("")) {
            return "[ERRO] Campo nome completo é obrigatório. Funcionário não cadastrado!";
        }

        Funcionario funcionario = new Funcionario(
                nomeCompleto,
                dataNascimento,
                documento,
                pais,
                estado,
                cidade,
                cargo,
                salario
        );

        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

        if (funcionarioDAO.inserir(funcionario)) {
            return "[OK] Funcionário cadastrado com sucesso!";
        } else {
            return "[ERRO] Erro desconhecido. Funcionário não cadastrado!";
        }
    }

    public String listar() {
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        ArrayList<Funcionario> lista = funcionarioDAO.selecionarTodos();

        StringBuilder conteudo = new StringBuilder();
        for (Funcionario funcionario : lista) {
            conteudo.append(funcionario).append("\n");
        }

        return conteudo.toString();
    }

    public String alterar(
            Long id,
            String nomeCompleto,
            LocalDate dataNascimento,
            String documento,
            String pais,
            String estado,
            String cidade,
            String cargo,
            Double salario
    ) {
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        if (id == null) {
            return "[ERRO] ID não pode ser nulo. Funcionário não alterado!";
        }
        if (funcionarioDAO.selecionarPorId(id) == null) {
            return "[ERRO] Não encontrado. Funcionário não alterado!";
        }
        if (nomeCompleto == null || nomeCompleto.trim().equals("")) {
            return "[ERRO] Campo nome completo é obrigatório. Funcionário não alterado!";
        }

        Funcionario funcionario = new Funcionario(
                nomeCompleto,
                dataNascimento,
                documento,
                pais,
                estado,
                cidade,
                cargo,
                salario
        );

        if (funcionarioDAO.atualizar(funcionario)) {
            return "[OK] Funcionário alterado com sucesso!";
        } else {
            return "[ERRO] Erro desconhecido. Funcionário não alterado!";
        }
    }

    public String excluir(Long id) {
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

        if (id == null) {
            return "[ERRO] ID não pode ser nulo. Funcionário não excluído!";
        }
        if (funcionarioDAO.selecionarPorId(id) == null) {
            return "[ERRO] Não encontrado. Funcionário não excluído!";
        }

        if (funcionarioDAO.deletar(id)) {
            return "[OK] Funcionário excluído com sucesso!";
        } else {
            return "[ERRO] Erro desconhecido. Funcionário não excluído!";
        }
    }
}
