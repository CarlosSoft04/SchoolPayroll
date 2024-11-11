package Project_SpringRestFul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Por terceiro, parti para a classe principal do sistema, onde o projeto inteiro ira rodar.
 */

//Anotacao que iniciara um servlet e servira o servico
@SpringBootApplication
public class SchoolPayrollApplication {

	public static void main(String... args) {
		SpringApplication.run(SchoolPayrollApplication.class, args);
	}

}

/**
 * Para consultar as rotas e visualizar os dados use:
 *
 * curl -X POST localhost:8080/teachers -H 'Content-type:application/json' -d '{"nome": " ","sobrenome": " ", "disciplina": ""}'
 * curl -v localhost:8080/teachers
 * curl -X GET localhost:8080/teachers/{2} -H 'Content-type:application/json'
 * ...
 */
