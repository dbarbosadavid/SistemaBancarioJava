package br.com.university.sistemabancario.backend;

public class CodigoValidatorHandler extends AbstractValidationHandler {
    @Override
    public boolean handle(ValidationRequest request) {
        System.out.println("Validando Codigo...");
        // CORREÇÃO: trocado getNome() por getCodigo()
        if (request.getCodigo() == null || request.getCodigo().isEmpty()) {
            System.out.println("Erro: Codigo inválido.");
            return false;
        }
        return handleNext(request);
    }
}