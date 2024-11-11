package Project_SpringRestFul.util;

import Project_SpringRestFul.model.Pedido;
import Project_SpringRestFul.model.Status;
import Project_SpringRestFul.model.Teacher;
import Project_SpringRestFul.repository.PedidoRepository;
import Project_SpringRestFul.repository.TeacherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * No meu quarto passo, decidi por criar essa classe de pré carregamento de alguns dados para eu visualizar no console apos rodar a aplicacao.
 * Classe que eh responsavel por preencher automaticamente o meu banco de dados toda vez que a aplicacao for iniciada
 */

//Essa anotacao gerencia dependencias importantes para o meu projeto, alem de indicar que a clsse contem Beans e configuracoes de componentes Spring
@Configuration
public class LoadDatabase {

    //Loger para mostrar dados no console
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    /**
     * A anotacao @Bean, diz ao Spring que esse metodo deve ser tratado como um bean(ser tratado e gerenciado) apos a inicializacao da aplicacao
     * @param teacherRepository objeto passado como parametro para obter os metodos de acesso ao banco de dados da entity-Teacher
     * @param pedidorepository objeto passado como parametro para obter os metodos de acessoa o banco de dados da entity-Pedido
     * CommandLineRunner diz que assim que se iniciar a aplicacao, o Spring deve executar este metodo.
     * @return
     */
    @Bean
    CommandLineRunner initDatabase(TeacherRepository teacherRepository, PedidoRepository pedidorepository) {

        return args -> {
            /**
             * Aqui serao criados dois objetos e serao salvos no banco de dados atraves do metodo save da interface repository
             */
            teacherRepository.save(new Teacher("Carlos","Rios","Programacao"));
            teacherRepository.save(new Teacher("Pedro","Silva","Maematica"));

            //forEach para imprimir a lista no console a cada registro
            teacherRepository.findAll().forEach(teacher -> log.info("Pre carregado {}", teacher));

            pedidorepository.save(new Pedido("Ebook Pro", Status.COMPLETED));
            pedidorepository.save(new Pedido("Iphone 15" , Status.IN_PROGRESS));
            //forEach para imprimir a lista no console a cada registro
            pedidorepository.findAll().forEach(pedido -> log.info("Pre carregado: {}", pedido));

        };

    }

}
