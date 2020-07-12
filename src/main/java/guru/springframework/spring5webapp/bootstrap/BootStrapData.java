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
        System.out.println("### Started in Bootstrap");

        Publisher publisher = new Publisher("Pub name", "Stress", "city", "state", "zip");
        publisherRepository.save(publisher);
        //
        Author eric = new Author("Eric", "Evan");
        Book ddd = new Book("Domain Driven Design", "123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(publisher);
        publisher.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(publisher);
        //
        Author rod = new Author("Rod", "Johnson");
        Book noJ2EE = new Book("J2EE Develoment without EJB", "234567");
        rod.getBooks().add(noJ2EE);
        noJ2EE.getAuthors().add(rod);
        noJ2EE.setPublisher(publisher);
        publisher.getBooks().add(noJ2EE);

        authorRepository.save(rod);
        bookRepository.save(noJ2EE);
        publisherRepository.save(publisher);
        //
        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Number of publisher: " + publisherRepository.count());
        System.out.println("Number of books published: " + publisher.getBooks());
    }
}
