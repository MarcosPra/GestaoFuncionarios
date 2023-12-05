import controller.ExemploController;
import model.*;
import model.enums.TipoMidia;
import test.ExemploTest;
import test.FuncionarioTest;

import javax.swing.*;
import java.time.LocalDate;
import java.util.Date;

public class Principal {
    public static void main(String[] args) {
        System.out.println("Protótipo de Aplicação para Gestão Hoteleira");


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

    }
}
