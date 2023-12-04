import controller.ExemploController;
import model.*;
import model.enums.TipoMidia;
import test.ExemploTest;
import test.FuncionarioTest;

import java.time.LocalDate;
import java.util.Date;

public class Principal {
    public static void main(String[] args) {
        System.out.println("Protótipo de Aplicação para Gestão Hoteleira");

//        Pessoa p1 = new Pessoa(
//            "José Pereira da Silva",
//            LocalDate.of(1980, 12, 20),
//            "123-456",
//            "Brasil",
//            "SC",
//            "Tubarão"
//        );
//        System.out.println("Pessoa:");
//        System.out.println(p1);
//
//        Funcionario f1 = new Funcionario(
//            "Manoel da Silva",
//            LocalDate.of(1960, 01, 02),
//            "789-888",
//            "Brasil",
//            "SC",
//            "Jaguaruna",
//            "Serviços Gerais",
//            3000.0
//        );
//        System.out.println("Funcionário:");
//        System.out.println(f1);
//
//        Cliente c1 = new Cliente(
//                "Lionel Messi",
//                LocalDate.of(1970, 02, 10),
//                "454547848",
//                "Argentina",
//                "Abc",
//                "Buenos Aires",
//                true,
//                "Jogadô caro."
//        );
//        System.out.println("Cliente:");
//        System.out.println(c1);
//
//        Acomodacao a1 = new Acomodacao(
//            "Chalé Família",
//            400.0,
//            6,
//            "Isto é uma descrição"
//        );
//        System.out.println("Acomodação:");
//        System.out.println(a1);
//
//        MidiaAcomodacao ma1 = new MidiaAcomodacao(a1, TipoMidia.IMAGEM, "varanda.jpg");
//        System.out.println("Mídia acomodação:");
//        System.out.println(ma1);
//
//        Pacote pc1 = new Pacote("Combo Natal", a1, 4, 1500.0);
//        System.out.println("Pacote:");
//        System.out.println(pc1);
//
//        Reserva r1 = new Reserva(
//            a1,
//            c1,
//            2,
//            LocalDate.of(2023, 11, 14),
//            LocalDate.of(2023, 11, 16),
//            100.0
//        );
//        System.out.println("Reserva:");
//        System.out.println(r1);
//
//        Exemplo exemplo = new Exemplo("Isto é um texto", 99);
//        System.out.println("Exemplo:");
//        System.out.println(exemplo);

//        ExemploTest exemploTest = new ExemploTest();
//        System.out.println(exemploTest.testeCadastro(null, 587));
//        System.out.println(exemploTest.testeListagem());
//        System.out.println(exemploTest.testeAlteracao(6L, "", 99));
//        System.out.println(exemploTest.testeExclusao(null));

        FuncionarioTest test = new FuncionarioTest();
        System.out.println(FuncionarioTest.testeCadastro(null,
                "Nome Teste",
                LocalDate.of(1990, 1, 1),
                "123456789",
                "Brasil",
                "SP",
                "São Paulo",
                "Analista",
                5000.00));

        // Teste de Cadastro
//        String respostaCadastro = test.testeCadastro(
//                "Nome Teste",
//                LocalDate.of(1990, 1, 1),
//                "123456789",
//                "Brasil",
//                "SP",
//                "São Paulo",
//                "Analista",
//                5000.00
//        );
//        System.out.println("Cadastro: " + respostaCadastro);
//
//        // Teste de Listagem
//        String respostaListagem = test.testeListagem();
//        System.out.println("Listagem: " + respostaListagem);
//
//        // Teste de Alteração (Assumindo que existe um funcionário cadastrado)
//        String respostaAlteracao = test.testeAlteracao(
//                1L, // ID do funcionário a ser alterado
//                "Nome Alterado",
//                LocalDate.of(1985, 5, 10),
//                "987654321",
//                "Brasil",
//                "RJ",
//                "Rio de Janeiro",
//                "Gerente",
//                8000.00
//        );
//        System.out.println("Alteração: " + respostaAlteracao);
//
//        // Teste de Exclusão (Assumindo que existe um funcionário cadastrado)
//        String respostaExclusao = test.testeExclusao(1L); // ID do funcionário a ser excluído
//        System.out.println("Exclusão: " + respostaExclusao);
//    }
    }
}
