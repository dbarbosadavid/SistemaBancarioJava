package br.com.university.sistemabancario.backend;

public class SenhaValidatorHandler extends AbstractValidationHandler {
    @Override
    public boolean handle(ValidationRequest request) {
        System.out.println("Validando Formato da Senha...");

        if (request.getSenha() == null || request.getSenha().length() < 3) {
        System.out.println("Erro: A senha deve ter no mínimo 3 caracteres.");
        return false;
        }
        return handleNext(request);
    }
}