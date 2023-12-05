import controller.ExemploController;
import controller.FuncionarioController;
import dao.FuncionarioDAO;
import model.*;
import model.enums.TipoMidia;
import test.ExemploTest;
import test.FuncionarioTest;

import java.time.LocalDate;
import java.util.Date;

public class Principal {
    public static void main(String[] args) {
        System.out.println("Protótipo de Aplicação para Gestão Hoteleira");

        System.out.println(FuncionarioTest.testeCadastro(
                "Manoel da Silva",
                LocalDate.of(1960, 01, 02),
                "789-888",
                "Brasil",
                "SC",
                "Jaguaruna",
                "Serviços Gerais",
                3000.0
        ));
        System.out.println(FuncionarioTest.testeCadastro(
                "Manoel do balucubaco",
                LocalDate.of(1970, 02, 20),
                "789-888",
                "Brasil",
                "SP",
                "São Paulo",
                "Gestao",
                25000.0
        ));
    }
}