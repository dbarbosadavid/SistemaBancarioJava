package br.com.university.sistemabancario.backend;

public abstract class AbstractValidationHandler {
    private AbstractValidationHandler nextHandler;

    // Método para construir a "corrente"
    public AbstractValidationHandler setNext(AbstractValidationHandler handler) {
        this.nextHandler = handler;
        return handler; // Retorna o próximo para facilitar o encadeamento
    }

    // O método que cada validador concreto irá implementar
    public abstract boolean handle(ValidationRequest request);

    // Método para passar para o próximo da corrente
    protected boolean handleNext(ValidationRequest request) {
        if (nextHandler == null) {
            return true; // Fim da corrente, tudo foi validado com sucesso
        }
        return nextHandler.handle(request);
    }
}