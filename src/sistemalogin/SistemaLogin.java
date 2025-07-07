/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sistemalogin;

/**
 *
 * @author aluno
 */
public class SistemaLogin {
    public static void main(String[] args) {
        Conta conta1 = ContasFactory.criarConta("corrente");
        Conta conta2 = ContasFactory.criarConta("poupanca");

        conta1.depositar(650);
        conta2.depositar(375);

        conta1.sacar(150);
        conta2.sacar(210);

        System.out.println(conta1.getTipo() + " - Saldo: R$ " + conta1.getSaldo());
        System.out.println(conta2.getTipo() + " - Saldo: R$ " + conta2.getSaldo());
    }
}

