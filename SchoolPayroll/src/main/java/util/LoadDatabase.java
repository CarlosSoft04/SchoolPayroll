package util;

import model.Teacher;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import repository.TeacherRepository;

import java.util.logging.Logger;

@Configuration
public class LoadDatabase {

    private static final Logger log = (Logger) LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    private CommandLineRunner initDatabase(TeacherRepository repository) {

        return args -> {
            log.info("Pré carregamento: " + repository.save(new Teacher("Carlos","Professor")));
            log.info("Pré carregamento: " + repository.save(new Teacher("Julia","Professora")));
        };

    }

}
