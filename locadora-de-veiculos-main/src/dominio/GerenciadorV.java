package src.dominio;

import db.VeiculoDb;
import dominio.Veiculo;
import java.util.List;
import java.util.Scanner;

public class GerenciadorV {


    public void gerenciarVeiculos(VeiculoDb veiculoDb, Scanner scanner) {
        boolean continuar = true;

        while (continuar) {
            System.out.println("""
                Gerenciamento de Veículos:
                1 - Cadastrar Veículo
                2 - Alterar Veículo
                3 - Buscar Veículo por Placa
                4 - Deletar Veículo
                5 - Voltar ao menu principal
                Opção: """);

            int opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1 -> cadastrarVeiculo(veiculoDb, scanner);
                case 2 -> alterarVeiculo(veiculoDb, scanner);
                case 3 -> buscarVeiculoPorPlaca(veiculoDb, scanner);
                case 4 -> deletarVeiculo(veiculoDb, scanner);
                case 5 -> continuar = false;
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }


    private void cadastrarVeiculo(VeiculoDb veiculoDb, Scanner scanner) {
        System.out.print("Digite a placa do veículo: ");
        String placa = scanner.nextLine();
        System.out.print("Digite o modelo do veículo: ");
        String modelo = scanner.nextLine();
        scanner.nextLine();

        Veiculo veiculo = new Veiculo(placa, modelo);
        boolean sucesso = veiculoDb.cadastrar(veiculo);
        System.out.println(sucesso ? "Veículo cadastrado com sucesso!" : "Erro: Veículo já cadastrado.");
    }


    private void alterarVeiculo(VeiculoDb veiculoDb, Scanner scanner) {
        System.out.print("Digite a placa do veículo a ser alterado: ");
        String placa = scanner.nextLine();

        Veiculo veiculo = veiculoDb.buscar(placa);
        if (veiculo == null) {
            System.out.println("Veículo não encontrado.");
            return;
        }

        System.out.print("Digite o novo modelo do veículo: ");
        String novoModelo = scanner.nextLine();
        scanner.nextLine();

        veiculo.setModelo(novoModelo);

        boolean sucesso = veiculoDb.alterar(veiculo);
        System.out.println(sucesso ? "Veículo alterado com sucesso!" : "Erro ao alterar o veículo.");
    }


    private void buscarVeiculoPorPlaca(VeiculoDb veiculoDb, Scanner scanner) {
        System.out.print("Digite a placa do veículo: ");
        String placa = scanner.nextLine();

        Veiculo veiculo = veiculoDb.buscar(placa);
        if (veiculo != null) {
            System.out.println("Veículo encontrado: ");
            System.out.println("Placa: " + veiculo.getPlaca());
            System.out.println("Modelo: " + veiculo.getModelo());
        } else {
            System.out.println("Veículo não encontrado.");
        }
    }

    private void deletarVeiculo(VeiculoDb veiculoDb, Scanner scanner) {
        System.out.print("Digite a placa do veículo a ser deletado: ");
        String placa = scanner.nextLine();

        boolean sucesso = veiculoDb.deletar(placa);
        System.out.println(sucesso ? "Veículo deletado com sucesso." : "Erro: Veículo não encontrado.");
    }
}
