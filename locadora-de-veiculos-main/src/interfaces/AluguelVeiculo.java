package src.interfaces;

import src.dominio.Cliente;
import src.dominio.Veiculo;


public interface AluguelVeiculo<T extends Cliente, V extends Veiculo> {


    void alugarVeiculo(T cliente, V veiculo, String localRetirada, long dataInicio);


    void devolverVeiculo(T cliente, V veiculo, String localDevolucao, long dataFim);
}

