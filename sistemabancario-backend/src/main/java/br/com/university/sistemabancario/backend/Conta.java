/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.university.sistemabancario.backend;

/**
 *
 * @author aluno
 */
public interface Conta {
    void depositar(double valor);
    void sacar(double valor);
    double getSaldo();
    String getTipo();
}
