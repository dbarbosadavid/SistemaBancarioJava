/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.university.sistemabancario.backend;

/**
 *
 * @author aluno
 */
public class ContaProxy implements Conta {
    private Conta contaReal;
    private ValidadorUser validador;

    public ContaProxy(Conta contaReal, ValidadorUser validador) {
        this.contaReal = contaReal;
        this.validador = validador;
    }

    @Override
    public void depositar(double valor) {
        if (validador.validarUser()) {
            contaReal.depositar(valor);
        } else {
            System.out.println("Acesso negado. Usuario nao autenticado.");
        }
    }

    @Override
    public void sacar(double valor) {
        if (validador.validarUser()) {
            contaReal.sacar(valor);
        } else {
            System.out.println("Acesso negado. Usuario nao autenticado.");
        }
    }

    @Override
    public double getSaldo() {
        if (validador.validarUser()) {
            return contaReal.getSaldo();
        } else {
            System.out.println("Acesso negado. Usuario nao autenticado.");
            return 0.0;
        }
    }

    @Override
    public String getTipo() {
        if (validador.validarUser()) {
            return contaReal.getTipo();
        } else {
            System.out.println("Acesso negado. Usuario nao autenticado.");
            return "Desconhecido";
        }
    }
}