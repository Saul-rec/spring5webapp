package com.example.spring5webapp.model;

import java.io.Serializable;

public class BookDto implements Serializable {

    private Long id;
    private String title;
    private String publisher;
    private String author;

    public BookDto() {
        super();
    }

    public BookDto(Long id, String bookTitle, String bookPublisher, String authorFullName) {
        this.id = id;
        this.title = bookTitle;
        this.publisher = bookPublisher;
        this.author = authorFullName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
