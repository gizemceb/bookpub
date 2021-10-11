package com.test.bookpub.controllers;

import com.test.bookpub.entity.Book;
import com.test.bookpub.entity.Isbn;
import com.test.bookpub.entity.Reviewer;
import com.test.bookpub.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyEditorSupport;
import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // books/{isbn}
    @RequestMapping(value = "/{isbn:.+}", method = RequestMethod.GET)
    public Book getBook(@PathVariable Isbn isbn) {
        return bookRepository.findBookByIsbn(isbn.getIsbn());
    }

    // books/reviewers
    @RequestMapping(value = "/{isbn}/reviewers", method = RequestMethod.GET)
    public List<Reviewer> getReviewers(@PathVariable("isbn") Book book) {
        return book.getReviewers();
    }

    @RequestMapping(value = "/session", method = RequestMethod.GET)
    public String getSessionId(HttpServletRequest request) {
        return request.getSession().getId();
    }

    public class IsbnEditor extends PropertyEditorSupport {
        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            if (StringUtils.hasText(text)) {
                setValue(new Isbn(text.trim()));
            } else {
                setValue(null);
            }
        }

        @Override
        public String getAsText() {
            Isbn isbn = (Isbn) getValue();
            if (isbn != null) {
                return isbn.getIsbn();
            } else {
                return "";
            }
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Isbn.class, new IsbnEditor());
    }
}
