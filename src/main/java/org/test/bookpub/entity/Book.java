package org.test.bookpub.entity;

import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    private String isbn;
    private String title;
    private String description;

    @ManyToOne
    @JsonIgnore
    private Author author;

    @ManyToOne
    private Publisher publisher;

    @ManyToMany
    private List<Reviewer> reviewers;

    protected  Book() { }

    public Book(String isbn, String title, Author author, Publisher publisher) {
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.title = title;
    }
    public Long getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Author getAuthor() {
        return author;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public List<Reviewer> getReviewers() {
        return reviewers;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public void setReviewers(List<Reviewer> reviewers) {
        this.reviewers = reviewers;
    }

}
