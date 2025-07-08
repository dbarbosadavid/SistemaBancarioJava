package br.com.university.sistemabancario.frontend;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collectors;

public class MainViewController implements Observer {

    // --- Componentes da Tela (injetados pelo FXML) ---
    @FXML private TextField usuarioTextField;
    @FXML private PasswordField senhaPasswordField;
    @FXML private Label statusLoginLabel;
    @FXML private VBox painelOperacoes; 
    @FXML private ComboBox<String> tipoContaComboBox;
    @FXML private TextField valorTextField;
    @FXML private Label saldoLabel;
    @FXML private TextField contaDestinoTextField;
    @FXML private TextArea extratoTextArea;
    @FXML private TextField codigoTextField;

    // --- Variáveis de Lógica e Estado ---
    private final ApiClient apiClient = new ApiClient();
    private final ContaViewModel viewModel = new ContaViewModel();
    private Usuario usuarioLogado; // Guarda os dados do usuário após o login

    /**
     * Este método é chamado automaticamente pelo JavaFX depois que a tela é carregada.
     */
    @FXML
    public void initialize() {
        viewModel.addObserver(this);
        // Inicia a tela com o painel de operações invisível e desabilitado.
        painelOperacoes.setVisible(false);
        painelOperacoes.setManaged(false);
    }

    /**
     * Chamado quando o botão de Login é clicado.
     */
@FXML
    private void handleLoginAction() {
    // Pega os dados de TODOS os campos
    String login = usuarioTextField.getText();
    String senha = senhaPasswordField.getText();
    String codigo = codigoTextField.getText();

    DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter formatadorHora = DateTimeFormatter.ofPattern("HH:mm");
    String dataAtual = LocalDateTime.now().format(formatadorData);
    String horaAtual = LocalDateTime.now().format(formatadorHora);

    // Cria o objeto de requisição completo
    LoginRequest request = new LoginRequest();
    request.setLogin(login);
    request.setSenha(senha);
    request.setData(dataAtual);   
    request.setHora(horaAtual);   
    request.setCodigo(codigo);

    // O resto do método continua igual...
    try {
        this.usuarioLogado = apiClient.login(request);
        statusLoginLabel.setText("Login bem-sucedido! Bem-vindo, " + usuarioLogado.getLogin() + "!");
        statusLoginLabel.setTextFill(Color.GREEN);
        setOperacoesHabilitadas(true);
        popularContasComboBox();
    } catch (Exception e) {
        exibirAlerta("Falha no Login", "Não foi possível autenticar: " + e.getMessage());
        statusLoginLabel.setText("Falha no login.");
        statusLoginLabel.setTextFill(Color.RED);
    }
}

    /**
     * Chamado quando o botão de Depositar é clicado.
     */
    @FXML
    private void handleDepositarAction() {
        try {
            OperacaoRequest request = criarOperacaoRequest();
            if (request == null) return;

            // Chama a API e recebe a resposta da operação
            OperacaoResponse response = apiClient.depositar(request);

            // Atualiza o nosso modelo de dados com o novo saldo
            viewModel.setSaldo(response.getNovoSaldo());
            exibirAlerta("Sucesso", response.getMensagem());

        } catch (Exception e) {
            exibirAlerta("Erro de Depósito", "Não foi possível realizar o depósito: " + e.getMessage());
        }
    }

    /**
     * Chamado quando o botão de Sacar é clicado.
     */
    @FXML
    private void handleSacarAction() {
        try {
            OperacaoRequest request = criarOperacaoRequest();
            if (request == null) return;

            // Chama a API e recebe a resposta da operação
            OperacaoResponse response = apiClient.sacar(request);
            
            // Atualiza o nosso modelo de dados com o novo saldo
            viewModel.setSaldo(response.getNovoSaldo());
            exibirAlerta("Sucesso", response.getMensagem());

        } catch (Exception e) {
            exibirAlerta("Erro de Saque", "Não foi possível realizar o saque: " + e.getMessage());
        }
    }

    // Dentro da classe MainViewController

@FXML
private void handleTransferirAction() {
    if (usuarioLogado == null) {
        exibirAlerta("Erro", "Você precisa fazer login primeiro!");
        return;
    }

    try {
        // Pega os dados da tela
        String tipoContaOrigem = tipoContaComboBox.getValue();
        Long idContaOrigem = encontrarIdDaContaPeloTipo(tipoContaOrigem);
        Long idContaDestino = Long.parseLong(contaDestinoTextField.getText());
        double valor = Double.parseDouble(valorTextField.getText());

        // Validações
        if (idContaOrigem.equals(idContaDestino)) {
            exibirAlerta("Erro de Transferência", "A conta de origem e destino não podem ser a mesma.");
            return;
        }

        // Cria o objeto de requisição
        TransferenciaRequest request = new TransferenciaRequest();
        request.setIdContaOrigem(idContaOrigem);
        request.setIdContaDestino(idContaDestino);
        request.setValor(valor);

        // Chama a API
        try{
            apiClient.transferir(request);


            exibirAlerta("Sucesso", "Transferência realizada com sucesso!");

        }catch (Exception e){
            exibirAlerta("Saldo Insuficiente", "Saldo Insuficiente!!!");
        }
        
        


        valorTextField.clear();
        contaDestinoTextField.clear();

    } catch (NumberFormatException e) {
        exibirAlerta("Erro de Formato", "Valor e ID da conta de destino devem ser numéricos.");
    } catch (Exception e) {
        exibirAlerta("Erro de Transferência", "Não foi possível realizar a transferência: " + e.getMessage());
    }
}

    /**
     * Este método da interface Observer é chamado sempre que o viewModel muda.
     */
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof ContaViewModel) {
            ContaViewModel model = (ContaViewModel) o;
            Platform.runLater(() -> {
                saldoLabel.setText("R$ " + String.format("%.2f", model.getSaldo()));
            });
        }
    }

    @FXML
private void handleExtratoAction() {
    try {
        String tipoSelecionado = tipoContaComboBox.getValue();
        Long idDaConta = encontrarIdDaContaPeloTipo(tipoSelecionado);
        if (idDaConta == null) {
            exibirAlerta("Erro", "Selecione uma conta para ver o extrato.");
            return;
        }

        // Chama a API para buscar a lista de movimentos
        List<Movimento> movimentos = apiClient.getExtrato(idDaConta);

        // Limpa a área de texto e formata o extrato
        extratoTextArea.clear();
        if (movimentos.isEmpty()) {
            extratoTextArea.setText("Nenhum movimento encontrado para esta conta.");
        } else {
            StringBuilder extratoFormatado = new StringBuilder();
            for (Movimento mov : movimentos) {
                extratoFormatado.append("Data: ").append(mov.getData().toLocalDate());
                extratoFormatado.append(" | Tipo: ").append(mov.getTipo());
                extratoFormatado.append(" | Valor: R$ ").append(String.format("%.2f", mov.getValor()));
                extratoFormatado.append("\n"); // Nova linha
            }
            extratoTextArea.setText(extratoFormatado.toString());
        }

    } catch (Exception e) {
        exibirAlerta("Erro ao Emitir Extrato", e.getMessage());
    }
}
    

    private OperacaoRequest criarOperacaoRequest() {
        String tipoSelecionado = tipoContaComboBox.getValue();
        if (tipoSelecionado == null || tipoSelecionado.isEmpty()) {
            exibirAlerta("Erro de Operação", "Por favor, selecione um tipo de conta.");
            return null;
        }

        Long idDaConta = encontrarIdDaContaPeloTipo(tipoSelecionado);
        if (idDaConta == null) {
            exibirAlerta("Erro Interno", "Não foi possível encontrar o ID para a conta selecionada.");
            return null;
        }

        try {
            double valor = Double.parseDouble(valorTextField.getText());
            OperacaoRequest request = new OperacaoRequest();
            request.setContaId(idDaConta);
            request.setValor(valor);
            return request;
        } catch (NumberFormatException e) {
            exibirAlerta("Erro de Formato", "Por favor, digite um valor numérico válido.");
            return null;
        }
    }

    private Long encontrarIdDaContaPeloTipo(String tipoConta) {
        if (usuarioLogado == null || usuarioLogado.getContas() == null) {
            return null;
        }
        for (Conta conta : usuarioLogado.getContas()) {
            // Usamos o getTipo() que vem do DTO Conta do front-end
            if (conta.getTipo().equals(tipoConta)) {
                return conta.getId();
            }
        }
        return null;
    }

    private void popularContasComboBox() {
        if (usuarioLogado != null && usuarioLogado.getContas() != null) {
            tipoContaComboBox.getItems().clear();
            tipoContaComboBox.getItems().addAll(
                usuarioLogado.getContas().stream()
                             .map(Conta::getTipo) // Mapeia cada objeto Conta para sua String de tipo
                             .collect(Collectors.toList())
            );
            if (!tipoContaComboBox.getItems().isEmpty()) {
                tipoContaComboBox.getSelectionModel().selectFirst();
            }
        }
    }
    
    private void exibirAlerta(String titulo, String mensagem) {

        Alert.AlertType tipoAlerta = titulo.toLowerCase().contains("sucesso") ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR;
        Alert alert = new Alert(tipoAlerta);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    /**
     * Habilita ou desabilita todos os controles da área de operações,
     * mostrando ou escondendo o painel que os contém.
     * @param habilitar 'true' para mostrar e habilitar, 'false' para esconder e desabilitar.
     */
    private void setOperacoesHabilitadas(boolean habilitar) {
        // A verificação '!= null' é uma segurança extra.
        if (painelOperacoes != null) {
            painelOperacoes.setVisible(habilitar);
            painelOperacoes.setManaged(habilitar); // 'setManaged' remove o espaço em branco quando está invisível
        }
    }
}