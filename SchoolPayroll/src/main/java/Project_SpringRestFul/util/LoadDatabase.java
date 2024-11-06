package Project_SpringRestFul.util;

import Project_SpringRestFul.model.Teacher;
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
    CommandLineRunner initDatabase(TeacherRepository repository) {

        return args -> {
            log.info("Pré carregamento: {}", repository.save(new Teacher("Carlos", "Professor")));
            log.info("Pré carregamento: {}", repository.save(new Teacher("Julia", "Professora")));
        };

    }

}
