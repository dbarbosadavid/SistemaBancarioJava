package br.com.university.sistemabancario.backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication; // Import necessário para o SpringApplication.run
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class SistemabancarioBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SistemabancarioBackendApplication.class, args);
    }

@Bean
public CommandLineRunner initialData(UsuarioRepository usuarioRepository, ContaRepository contaRepository) {
    return args -> {
        if (usuarioRepository.findByLogin("bruno") == null) {
            Usuario novoUsuario = new Usuario();
            novoUsuario.setLogin("bruno");
            novoUsuario.setSenha("123");
            novoUsuario.setContas(new java.util.ArrayList<>());


            ContaCorrente contaC = new ContaCorrente();
            contaC.setUsuario(novoUsuario);
            contaC.setSaldo(1000.0); 
            novoUsuario.getContas().add(contaC);


            ContaPoupanca contaP = new ContaPoupanca();
            contaP.setUsuario(novoUsuario);
            contaP.setSaldo(500.0); 
            novoUsuario.getContas().add(contaP);

            usuarioRepository.save(novoUsuario);

            System.out.println("Usuário 'bruno' e suas contas foram criados com sucesso.");
        }
      
        if (usuarioRepository.findByLogin("david") == null) {
            Usuario novoUsuario = new Usuario();
            novoUsuario.setLogin("david");
            novoUsuario.setSenha("123");
            novoUsuario.setContas(new java.util.ArrayList<>());


            ContaCorrente contaC = new ContaCorrente();
            contaC.setUsuario(novoUsuario);
            contaC.setSaldo(2000.0); 
            novoUsuario.getContas().add(contaC);


            ContaPoupanca contaP = new ContaPoupanca();
            contaP.setUsuario(novoUsuario);
            contaP.setSaldo(1500.0); 
            novoUsuario.getContas().add(contaP);

            usuarioRepository.save(novoUsuario);

            System.out.println("Usuário 'David' e suas contas foram criados com sucesso.");
        }
       
        if (usuarioRepository.findByLogin("igor") == null) {
            Usuario novoUsuario = new Usuario();
            novoUsuario.setLogin("igor");
            novoUsuario.setSenha("123");
            novoUsuario.setContas(new java.util.ArrayList<>());


            ContaSalario contaS = new ContaSalario();
            contaS.setUsuario(novoUsuario);
            contaS.setSaldo(2000.0); 
            novoUsuario.getContas().add(contaS);


            usuarioRepository.save(novoUsuario);

            System.out.println("Usuário 'Igor' e suaa conta salario foi criada com sucesso.");
        }
    };
}
}