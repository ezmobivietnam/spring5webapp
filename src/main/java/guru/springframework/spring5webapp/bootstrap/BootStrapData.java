package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository,
                         PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher publisher = new Publisher("Pub name", "Stress", "city", "state", "zip");

        Author eric = new Author("Eric", "Evan");
        Book ddd = new Book("Domain Driven Design", "123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(publisher);
        publisher.getBooks().add(ddd);

        authorRepository.save(eric);
        publisherRepository.save(publisher);
        bookRepository.save(ddd);
        //
        Author rod = new Author("Rod", "Johnson");
        Book noJ2EE = new Book("J2EE Develoment without EJB", "234567");
        rod.getBooks().add(noJ2EE);
        noJ2EE.getAuthors().add(rod);
        publisher.getBooks().add(noJ2EE);
        noJ2EE.setPublisher(publisher);

        authorRepository.save(rod);
        publisherRepository.save(publisher);
        bookRepository.save(noJ2EE);
        //
        System.out.println("Started in Bootstrap");
        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Number of publisher: " + publisherRepository.count());
        System.out.println("Number of books published: " + publisher.getBooks());
    }
}
