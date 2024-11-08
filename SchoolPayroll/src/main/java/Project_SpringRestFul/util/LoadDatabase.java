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

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(TeacherRepository teacherRepository, PedidoRepository pedidorepository) {

        return args -> {
            teacherRepository.save(new Teacher("Carlos","Rios","Programacao"));
            teacherRepository.save(new Teacher("Pedro","Silva","Maematica"));

            teacherRepository.findAll().forEach(teacher -> log.info("Pre carregado {}", teacher));

            pedidorepository.save(new Pedido("Ebook Pro", Status.COMPLETED));
            pedidorepository.save(new Pedido("Iphone 15" , Status.IN_PROGRESS));

            pedidorepository.findAll().forEach(pedido -> log.info("Pre carregado: {}", pedido));

        };

    }

}
