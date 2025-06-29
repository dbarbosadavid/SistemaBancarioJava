package br.com.university.sistemabancario.frontend;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Observable;
import java.util.Observer;

// O Controller agora implementa Observer para "observar" o Model
public class MainViewController implements Observer {

    @FXML private ComboBox<String> tipoContaComboBox;
    @FXML private TextField valorTextField;
    @FXML private Label saldoLabel;

    private final ApiClient apiClient = new ApiClient();
    // O Controller agora tem uma instância do nosso ViewModel
    private final ContaViewModel viewModel = new ContaViewModel();

    @FXML
    public void initialize() {
        tipoContaComboBox.getItems().addAll("corrente", "poupanca", "salario");
        tipoContaComboBox.setValue("corrente");

        // O Controller se registra como um observador do ViewModel
        viewModel.addObserver(this);
    }

    @FXML
    private void handleDepositarAction() {
        try {
            OperacaoRequest request = new OperacaoRequest();
            request.setTipoConta(tipoContaComboBox.getValue());
            request.setValor(Double.parseDouble(valorTextField.getText()));

            OperacaoResponse response = apiClient.depositar(request);

            // O Controller agora só atualiza o modelo.
            // Ele não toca mais diretamente no label.
            viewModel.setSaldo(response.getNovoSaldo());

        } catch (NumberFormatException e) {
            exibirAlerta("Erro de Formato", "Por favor, digite um valor numérico válido.");
        } catch (Exception e) {
            exibirAlerta("Erro de Comunicação", "Não foi possível conectar ao servidor: " + e.getMessage());
        }
    }

    @FXML
    private void handleSacarAction() {
        // Implementação do saque segue o mesmo padrão
        try {
            OperacaoRequest request = new OperacaoRequest();
            request.setTipoConta(tipoContaComboBox.getValue());
            request.setValor(Double.parseDouble(valorTextField.getText()));

            OperacaoResponse response = apiClient.sacar(request);
            viewModel.setSaldo(response.getNovoSaldo());

        } catch (NumberFormatException e) {
            exibirAlerta("Erro de Formato", "Por favor, digite um valor numérico válido.");
        } catch (Exception e) {
            exibirAlerta("Erro de Comunicação", "Não foi possível conectar ao servidor: " + e.getMessage());
        }
    }

    // Este método é exigido pela interface Observer.
    // Ele é chamado automaticamente quando o viewModel.notifyObservers() é executado.
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof ContaViewModel) {
            // Pega o novo saldo do modelo e ATUALIZA A VIEW AQUI!
            double novoSaldo = ((ContaViewModel) o).getSaldo();
            // Platform.runLater garante que a atualização da UI ocorra na thread correta
            Platform.runLater(() -> {
                saldoLabel.setText("R$ " + String.format("%.2f", novoSaldo));
            });
        }
    }

    private void exibirAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}