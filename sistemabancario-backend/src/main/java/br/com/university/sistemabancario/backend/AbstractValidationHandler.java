package br.com.university.sistemabancario.backend;

public abstract class AbstractValidationHandler {
    private AbstractValidationHandler nextHandler;


    public AbstractValidationHandler setNext(AbstractValidationHandler handler) {
        this.nextHandler = handler;
        return handler; 
    }

    // O método que cada validador concreto irá implementar
    public abstract boolean handle(ValidationRequest request);

    // Método para passar para o próximo da corrente
    protected boolean handleNext(ValidationRequest request) {
        if (nextHandler == null) {
            return true; 
        }
        return nextHandler.handle(request);
    }
}