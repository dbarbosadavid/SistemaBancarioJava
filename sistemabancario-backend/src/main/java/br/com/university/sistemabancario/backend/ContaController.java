package br.com.university.sistemabancario.backend;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Anotação que define esta classe como um Controller de API REST
@RequestMapping("/api/contas") // Todas as requisições para este controller devem começar com /api/contas
public class ContaController {

    /**
     * NOTA IMPORTANTE PARA O EXERCÍCIO:
     * Para simplificar, este exemplo cria uma nova conta a cada requisição.
     * Em um sistema real, você gerenciaria as contas existentes (buscando de um banco de dados
     * ou de uma estrutura em memória, como um Map, para que o saldo persista entre as chamadas).
     */

    @PostMapping("/depositar") // Responde a requisições POST para /api/contas/depositar
    public OperacaoResponse depositar(@RequestBody OperacaoRequest request) {
        // O @RequestBody converte automaticamente o JSON recebido para o objeto OperacaoRequest
        ValidadorUser validador = new ValidadorUser(); // Instancia o validador

        // Usa a fábrica para criar a conta e a envolve no Proxy
        Conta conta = new ContaProxy(
            ContasFactory.criarConta(request.getTipoConta()), //
            validador
        ); //

        conta.depositar(request.getValor()); // Executa a operação

        // Cria e retorna o objeto de resposta. O Spring o converterá para JSON.
        return new OperacaoResponse(
            "Depósito realizado com sucesso!",
            conta.getTipo(),
            conta.getSaldo()
        );
    }

    @PostMapping("/sacar") // Responde a requisições POST para /api/contas/sacar
    public OperacaoResponse sacar(@RequestBody OperacaoRequest request) {
        ValidadorUser validador = new ValidadorUser(); //
        Conta conta = new ContaProxy(
            ContasFactory.criarConta(request.getTipoConta()), //
            validador
        ); //

        conta.sacar(request.getValor()); // Executa a operação

        return new OperacaoResponse(
            "Saque realizado com sucesso!",
            conta.getTipo(),
            conta.getSaldo()
        );
    }
}