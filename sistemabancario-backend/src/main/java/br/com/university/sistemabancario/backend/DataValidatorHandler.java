package br.com.university.sistemabancario.backend;

public class DataValidatorHandler extends AbstractValidationHandler {
    @Override
    public boolean handle(ValidationRequest request) {
        System.out.println("Validando Data...");
        // AQUI ESTÁ A CORREÇÃO: trocamos getNome() por getData()
        if (request.getData() == null || request.getData().isEmpty()) {
            System.out.println("Erro: Data inválida.");
            return false; // Interrompe a corrente
        }
        // No futuro, aqui poderia ter uma lógica para validar o formato da data
        return handleNext(request);
    }
}