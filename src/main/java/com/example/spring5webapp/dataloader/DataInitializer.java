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
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        authorRepo.save(eric);
        bookRepo.save(ddd);

        Author eric2 = new Author("Eric", "Evans");
        Book dd = new Book("The Art of Academic Finesse", "3452", pub1);
        eric2.getBooks().add(dd);
        dd.getAuthors().add(eric2);
        authorRepo.save(eric2);
        bookRepo.save(dd);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development Without EJB", "23444", pub2);
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        authorRepo.save(rod);
        bookRepo.save(noEJB);


    }
}
