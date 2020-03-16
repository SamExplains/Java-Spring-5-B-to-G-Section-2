package com.firstapp.demo.bootstrap;

import com.firstapp.demo.model.Author;
import com.firstapp.demo.model.Book;
import com.firstapp.demo.repositories.AuthorRepository;
import com.firstapp.demo.repositories.BookRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        // Will only create 4 objects, 2 Authors and 2 Books along with the Associations
        initData();
    }

    private void initData(){
        // Peanut
        Author peanut = new Author("Peanut", "Banda");
        Book ddd = new Book("Domain Driven Design", "1234", "Harper Collins");
        peanut.getBooks().add(ddd);
        ddd.getAuthors().add(peanut);

        // Save record
        authorRepository.save(peanut);
        bookRepository.save(ddd);

        // Lucas
        Author lucas = new Author("Lucas", "Po");
        Book noEJB = new Book("J2EE Development without EJB", "2344", "Worx");
        lucas.getBooks().add(noEJB);
        noEJB.getAuthors().add(lucas);

        // Save record
        authorRepository.save(lucas);
        bookRepository.save(noEJB);
    }

}
