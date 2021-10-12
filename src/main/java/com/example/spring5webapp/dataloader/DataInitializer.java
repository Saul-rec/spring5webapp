package com.example.spring5webapp.dataloader;

import com.example.spring5webapp.model.Author;
import com.example.spring5webapp.model.Book;
import com.example.spring5webapp.model.Publisher;
import com.example.spring5webapp.repositories.AuthorRepository;
import com.example.spring5webapp.repositories.BookRepository;
import com.example.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private final AuthorRepository authorRepo;
    private final BookRepository bookRepo;
    private final PublisherRepository publisherRepo;

    public DataInitializer(AuthorRepository authorRepo, BookRepository bookRepo, PublisherRepository publisherRepo) {
        this.authorRepo = authorRepo;
        this.bookRepo = bookRepo;
        this.publisherRepo = publisherRepo;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }

    private void initData() {
        Publisher pub1 = new Publisher("Harper Collins","San Francisco");
        Publisher pub2 = new Publisher("Worx", "New York");
        List<Publisher> pubList = new ArrayList<>();
        pubList.add(pub1);
        pubList.add(pub2);
        publisherRepo.saveAll(pubList);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "1234", pub1);
        Book finesse = new Book("The Art of Academic Finesse","3256",pub1);
        List<Book> books = new ArrayList<>();
        books.add(ddd);
        books.add(finesse);
        eric.getBooks().addAll(books);

        ddd.getAuthors().add(eric);
        finesse.getAuthors().add(eric);

        authorRepo.save(eric);
        bookRepo.saveAll(books);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development Without EJB", "23444", pub2);
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        authorRepo.save(rod);
        bookRepo.save(noEJB);
    }
}
