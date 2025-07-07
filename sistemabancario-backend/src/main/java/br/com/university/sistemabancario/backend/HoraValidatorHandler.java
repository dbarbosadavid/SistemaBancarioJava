package br.com.university.sistemabancario.backend;

public class HoraValidatorHandler extends AbstractValidationHandler {
    @Override
    public boolean handle(ValidationRequest request) {
        System.out.println("Validando Hora...");

        if (request.getHora() == null || request.getHora().isEmpty()) {
            System.out.println("Erro: Hora inválida.");
            return false;
        }
        return handleNext(request);
    }
}