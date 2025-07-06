/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemalogin;

/**
 *
 * @author aluno
 */
public class ContaSalario implements Conta {
    private double saldo;

    @Override
    public void depositar(double valor) {
        saldo += valor;
    }

    @Override
    public void sacar(double valor) {
        // Saques permitidos até o saldo disponível
        if (saldo >= valor) {
            saldo -= valor;
        } else {
            System.out.println("Saldo insuficiente na Conta Salário.");
        }
    }

    @Override
    public double getSaldo() {
        return saldo;
    }

    @Override
    public String getTipo() {
        return "Conta Salário";
    }
}

