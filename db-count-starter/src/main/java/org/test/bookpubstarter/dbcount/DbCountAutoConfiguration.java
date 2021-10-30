package org.test.bookpubstarter.dbcount;

import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;

@Configuration
public class DbCountAutoConfiguration {
    @Bean
    public DbCountRunner dbCountRunner (Collection<CrudRepository> repositories){
        return new DbCountRunner(repositories);
    }

}