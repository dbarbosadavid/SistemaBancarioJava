package br.com.university.sistemabancario.backend;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("/api/contas") 
public class ContaController {


    @PostMapping("/depositar") 
    public OperacaoResponse depositar(@RequestBody OperacaoRequest request) {
        
        ValidadorUser validador = new ValidadorUser(); 


        Conta conta = new ContaProxy(
            ContasFactory.criarConta(request.getTipoConta()), //
            validador
        ); //

        conta.depositar(request.getValor()); 


        return new OperacaoResponse(
            "Depósito realizado com sucesso!",
            conta.getTipo(),
            conta.getSaldo()
        );
    }

    @PostMapping("/sacar") 
    public OperacaoResponse sacar(@RequestBody OperacaoRequest request) {
        ValidadorUser validador = new ValidadorUser(); //
        Conta conta = new ContaProxy(
            ContasFactory.criarConta(request.getTipoConta()), //
            validador
        ); //

        conta.sacar(request.getValor()); 

        return new OperacaoResponse(
            "Saque realizado com sucesso!",
            conta.getTipo(),
            conta.getSaldo()
        );
    }
}