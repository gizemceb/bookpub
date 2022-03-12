package org.test.bookpub;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.test.bookpub.entity.Book;
import org.test.bookpub.repository.BookRepository;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BookpubApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookPubApplicationTests {
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private BookRepository repository;

    @LocalServerPort
    private int port;

    private MockMvc mockMvc;
    private TestRestTemplate restTemplate = new TestRestTemplate();

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void contextLoads() {
        Assert.assertEquals(1, repository.count());
    }

    @Test
    public void webappBookIsbnApi() {
        Book book = restTemplate.getForObject("http://localhost:" + port + "/books/61-61-61", Book.class);
        Assert.assertNotNull(book);
        Assert.assertEquals("CEBI yayinlari", book.getPublisher().getName());
    }

    @Test
    public void webappPublisherApi() throws Exception {
        mockMvc.perform(get("/publishers/2")).andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.parseMediaType("application/hal+json")))
            .andExpect(content().string(containsString("CEBI yayinlari")))
            .andExpect(jsonPath("$.name").value("CEBI yayinlari"));
    }

}
