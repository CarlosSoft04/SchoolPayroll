package Project_SpringRestFul.repository;

import Project_SpringRestFul.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

//Interface que extende JpaRepository que me fornece metodos prontos para fazer operacoes no banco de dados

//Coloquei essa classe dentro do meu repository por convencoes clean code

/**
 * Foi o segundo passo que fiz para a construcao do sistema, pois com ela, ja tenho o acesso dos metodos de acesso ao banco de dados, oque me permite nao criar
 * comandos sql nativos ou tecnicas manuais de manipulacao de dados
 */
public interface TeacherRepository extends JpaRepository<Teacher,Long> {
}
