package br.com.university.sistemabancario.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@RestController
@RequestMapping("/api/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

@PostMapping("/login")
public ResponseEntity<Usuario> login(@RequestBody LoginRequest loginRequest) {
    // Chama o novo método login no serviço, passando todos os 5 parâmetros
    Usuario usuarioAutenticado = contaService.login(
        loginRequest.getLogin(),
        loginRequest.getSenha(),
        loginRequest.getData(),
        loginRequest.getHora(),
        loginRequest.getCodigo()
    );

    if (usuarioAutenticado != null) {
        // Se a validação e a autenticação passarem, retorna 200 OK
        return ResponseEntity.ok(usuarioAutenticado);
    } else {
        // Se falhar, retorna 401 Unauthorized
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}

    @PostMapping("/depositar")
    // O request agora só precisa do ID da conta e do valor
    public ResponseEntity<OperacaoResponse> depositar(@RequestBody OperacaoRequest request) {
        Conta contaAtualizada = contaService.realizarDeposito(request.getContaId(), request.getValor());
        return ResponseEntity.ok(new OperacaoResponse("Depósito realizado com sucesso!", contaAtualizada.getTipo(), contaAtualizada.getSaldo()));
    }

    @PostMapping("/sacar")
    // O request agora só precisa do ID da conta e do valor
    public ResponseEntity<OperacaoResponse> sacar(@RequestBody OperacaoRequest request) {
        Conta contaAtualizada = contaService.realizarSaque(request.getContaId(), request.getValor());
        return ResponseEntity.ok(new OperacaoResponse("Saque realizado com sucesso!", contaAtualizada.getTipo(), contaAtualizada.getSaldo()));
    }

 // O método agora fica mais limpo e delega o tratamento de erro
@PostMapping("/transferir")
public ResponseEntity<Void> transferir(@RequestBody TransferenciaRequest request) {
    contaService.realizarTransferencia(
        request.getIdContaOrigem(), 
        request.getIdContaDestino(), 
        request.getValor()
    );
    // Se nenhuma exceção for lançada, retorna 200 OK
    return ResponseEntity.ok().build();
}

    @GetMapping("/{contaId}/extrato")
    public ResponseEntity<List<Movimento>> getExtrato(@PathVariable Long contaId) {
        List<Movimento> extrato = contaService.getExtrato(contaId);
        return ResponseEntity.ok(extrato);
    }
}