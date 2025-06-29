package br.com.university.sistemabancario.frontend;

import java.util.Observable;


public class ContaViewModel extends Observable {

    private double saldo;

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
        

        setChanged();       
        notifyObservers();  
    }
}