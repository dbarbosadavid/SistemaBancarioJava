/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemalogin;

/**
 *
 * @author aluno
 */
public class ContasFactory {
    public static Conta criarConta(String tipo) {
        switch (tipo.toLowerCase()) {
            case "corrente":
                return new ContaCorrente();
            case "poupanca":
                return new ContaPoupanca();
            case "salario":
                return new ContaSalario();
            default:
                throw new IllegalArgumentException("Tipo de conta desconhecido: " + tipo);
        }
    }
}
