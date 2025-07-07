package br.com.university.sistemabancario.backend;

public class DataValidatorHandler extends AbstractValidationHandler {
    @Override
    public boolean handle(ValidationRequest request) {
        System.out.println("Validando Data...");

        if (request.getData() == null || request.getData().isEmpty()) {
            System.out.println("Erro: Data inválida.");
            return false; 
        }

        return handleNext(request);
    }
}