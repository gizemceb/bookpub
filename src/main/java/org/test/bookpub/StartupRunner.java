package org.test.bookpub;

import org.test.bookpub.entity.Author;
import org.test.bookpub.entity.Book;
import org.test.bookpub.entity.Publisher;
import org.test.bookpub.repository.AuthorRepository;
import org.test.bookpub.repository.BookRepository;
import org.test.bookpub.repository.PublisherRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Order(Ordered.HIGHEST_PRECEDENCE - 15)
public class StartupRunner implements CommandLineRunner {
    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private DataSource ds;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private BookRepository bookRepository;

    @Autowired private AuthorRepository authorRepository;
    @Autowired private PublisherRepository publisherRepository;

    @Override
    public void run(String... args) throws Exception {
        Author author = new Author("Gizem", "Çebi");
        author = authorRepository.save(author);
        Publisher publisher = new Publisher(1L,"CEBI yayinlari");
        publisher = publisherRepository.save(publisher);
        Book book = new Book("61-61-61", "Çebi ailesi üyesi olmanın ayrıcalıkları", author, publisher );
        bookRepository.save(book);
        logger.info("Number of books:" + bookRepository.count());
    }

    /* @Scheduled(initialDelay = 1000, fixedRate = 10000)
    public void run() {
        logger.info("Number of books:" + bookRepository.count());
    } */
}
