package org.test.bookpub;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.test.bookpubstarter.dbcount.EnableDbCounting;

@SpringBootApplication
@EnableScheduling
@EnableDbCounting
public class BookpubApplication {
	protected final Log logger = LogFactory.getLog(getClass());
	public static void main(String[] args) {
		SpringApplication.run(BookpubApplication.class, args);
	}

	@Bean
	public StartupRunner schedulerRunner() {
		return new StartupRunner();
	}

	/* @Bean
	public DbCountRunner dbCountRunner(Collection<CrudRepository> repositories) {
		return new DbCountRunner(repositories) {
			@Override
			public void run(String... args) throws Exception {
				logger.info("Manually Declared DbCountRunner");
			}
		};
	} */
}
