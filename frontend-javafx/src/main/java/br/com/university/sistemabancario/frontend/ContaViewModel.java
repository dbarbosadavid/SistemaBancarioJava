package br.com.university.sistemabancario.frontend;

import java.util.Observable;

// Esta classe é o nosso "Model" do MVC. Ela estende Observable.
public class ContaViewModel extends Observable {

    private double saldo;

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
        
        // Etapa crucial do padrão:
        setChanged();       // 1. Marca que o objeto mudou.
        notifyObservers();  // 2. Notifica todos os observadores registrados.
    }
}