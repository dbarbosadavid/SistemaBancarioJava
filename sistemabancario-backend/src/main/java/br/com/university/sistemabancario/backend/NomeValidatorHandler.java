package br.com.university.sistemabancario.backend;

public class NomeValidatorHandler extends AbstractValidationHandler {
    @Override
    public boolean handle(ValidationRequest request) {
        System.out.println("Validando Nome...");
        if (request.getNome() == null || request.getNome().isEmpty()) {
            System.out.println("Erro: Nome inválido.");
            return false;
        }
        return handleNext(request);
    }
}