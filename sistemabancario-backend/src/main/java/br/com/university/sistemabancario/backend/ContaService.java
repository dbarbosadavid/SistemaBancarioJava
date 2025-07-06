package br.com.university.sistemabancario.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ContaService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ContaRepository contaRepository; // <-- Agora só um repositório de conta

    @Autowired
    private MovimentoRepository movimentoRepository;

    public Usuario login(String login, String senha) {
        // ... método de login não muda ...
        Usuario usuario = usuarioRepository.findByLogin(login);
        if (usuario != null && usuario.getSenha().equals(senha)) {
            return usuario;
        }
        return null;
    }

    @Transactional
    public void realizarTransferencia(Long idContaOrigem, Long idContaDestino, double valor) {
        // 1. Busca as contas de origem e destino no banco de dados.
        //    O .orElseThrow garante que a operação falhe se uma das contas não for encontrada.
        Conta contaOrigem = contaRepository.findById(idContaOrigem)
                .orElseThrow(() -> new RuntimeException("Conta de origem não encontrada!"));

        Conta contaDestino = contaRepository.findById(idContaDestino)
                .orElseThrow(() -> new RuntimeException("Conta de destino não encontrada!"));

        // 2. Executa a lógica de negócio (o método sacar já verifica o saldo)
        contaOrigem.sacar(valor);
        contaDestino.depositar(valor);

        // 3. Cria os registros de movimento para o extrato
        Movimento movimentoSaida = new Movimento();
        movimentoSaida.setTipo("TRANSFERENCIA_ENVIADA");
        movimentoSaida.setValor(valor * -1); // Registra como um valor negativo
        movimentoSaida.setData(LocalDateTime.now());
        movimentoSaida.setConta(contaOrigem);

        Movimento movimentoEntrada = new Movimento();
        movimentoEntrada.setTipo("TRANSFERENCIA_RECEBIDA");
        movimentoEntrada.setValor(valor);
        movimentoEntrada.setData(LocalDateTime.now());
        movimentoEntrada.setConta(contaDestino);

        // 4. Salva tudo no banco de dados.
        //    Graças ao @Transactional, se qualquer um desses passos falhar,
        //    todos os anteriores serão desfeitos automaticamente.
        contaRepository.save(contaOrigem);
        contaRepository.save(contaDestino);
        movimentoRepository.save(movimentoSaida);
        movimentoRepository.save(movimentoEntrada);
    }

    // A anotação @Transactional garante que todas as operações de banco de dados
    // neste método aconteçam juntas. Se uma falhar, todas são desfeitas.
    @Transactional
    public Conta realizarDeposito(Long contaId, double valor) {
        Conta conta = contaRepository.findById(contaId)
                .orElseThrow(() -> new RuntimeException("Conta com ID " + contaId + " não encontrada"));

        conta.depositar(valor);

        Movimento movimento = new Movimento();
        movimento.setTipo("DEPOSITO");
        movimento.setValor(valor);
        movimento.setData(LocalDateTime.now());
        movimento.setConta(conta);

        // O movimento é salvo em cascata quando salvamos a conta, por causa da configuração na entidade Conta.
        // Mas salvá-lo explicitamente não causa problema.
        movimentoRepository.save(movimento);

        return contaRepository.save(conta);
    }

    @Transactional
    public Conta realizarSaque(Long contaId, double valor) {
        Conta conta = contaRepository.findById(contaId)
                .orElseThrow(() -> new RuntimeException("Conta com ID " + contaId + " não encontrada"));

        // Guarda o saldo antes para verificar se o saque foi efetivado
        double saldoAntes = conta.getSaldo();
        conta.sacar(valor);

        // Só registra o movimento se o saldo mudou (saque foi bem-sucedido)
        if (conta.getSaldo() != saldoAntes) {
            Movimento movimento = new Movimento();
            movimento.setTipo("SAQUE");
            movimento.setValor(valor);
            movimento.setData(LocalDateTime.now());
            movimento.setConta(conta);
            movimentoRepository.save(movimento);
        }

        return contaRepository.save(conta);
    }
    
        public List<Movimento> getExtrato(Long contaId) {
        // Busca a conta pelo ID. Se não encontrar, lança um erro.
        Conta conta = contaRepository.findById(contaId)
                .orElseThrow(() -> new RuntimeException("Conta com ID " + contaId + " não encontrada"));

        // Retorna a lista de movimentos que já está associada à conta.
        // O FetchType.EAGER que configuramos antes ajuda aqui.
        return conta.getMovimentos();
    }
}