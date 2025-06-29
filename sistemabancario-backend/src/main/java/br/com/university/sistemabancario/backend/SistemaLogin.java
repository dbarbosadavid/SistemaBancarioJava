/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package br.com.university.sistemabancario.backend;

/**
 *
 * @author aluno
 */
public class SistemaLogin {
    public static void main(String[] args) {
        ValidadorUser validadorUser = new ValidadorUser();


        Conta conta1 = new ContaProxy(ContasFactory.criarConta("corrente"), validadorUser);
        Conta conta2 = new ContaProxy(ContasFactory.criarConta("poupanca"), validadorUser);
        Conta conta3 = new ContaProxy(ContasFactory.criarConta("salario"), validadorUser);


        System.out.println("--- Operacoes na Conta Corrente ---");
        conta1.depositar(650);
        conta1.sacar(150);
        System.out.println(conta1.getTipo() + " - Saldo: R$ " + conta1.getSaldo());

        System.out.println("\n--- Operacoes na Conta Poupanca ---");
        conta2.depositar(375);
        conta2.sacar(210);
        System.out.println(conta2.getTipo() + " - Saldo: R$ " + conta2.getSaldo());

        System.out.println("\n--- Operacoes na Conta Salario ---");
        conta3.depositar(1000);
        conta3.sacar(300);
        System.out.println(conta3.getTipo() + " - Saldo: R$ " + conta3.getSaldo());
    }
}