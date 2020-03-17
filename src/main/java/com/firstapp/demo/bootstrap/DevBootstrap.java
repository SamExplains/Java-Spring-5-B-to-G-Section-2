package com.firstapp.demo.bootstrap;

import com.firstapp.demo.model.Author;
import com.firstapp.demo.model.Book;
import com.firstapp.demo.model.Publisher;
import com.firstapp.demo.repositories.AuthorRepository;
import com.firstapp.demo.repositories.BookRepository;
import com.firstapp.demo.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        // Will only create 4 objects, 2 Authors and 2 Books along with the Associations
        initData();
    }

    private void initData(){
        // Peanut
        Publisher publisher = new Publisher();
        publisher.setName("Harper Collins");
        publisherRepository.save(publisher);

        Author peanut = new Author("Peanut", "Banda");
        Book ddd = new Book("Domain Driven Design", "1234", publisher);
        peanut.getBooks().add(ddd);
        ddd.getAuthors().add(peanut);

        // Save record
        authorRepository.save(peanut);
        bookRepository.save(ddd);

        // Lucas
        Author lucas = new Author("Lucas", "Po");
        Book noEJB = new Book("J2EE Development without EJB", "2344", publisher);
        lucas.getBooks().add(noEJB);
        noEJB.getAuthors().add(lucas);

        // Save record
        authorRepository.save(lucas);
        bookRepository.save(noEJB);
    }

}
