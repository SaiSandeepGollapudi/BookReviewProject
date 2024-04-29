package com.goodReads.library;

import com.goodReads.library.Repositry.BookRepository;
import com.goodReads.library.Repositry.UserRepository;
import com.goodReads.library.domain.Book;
import com.goodReads.library.domain.Genre;
import com.goodReads.library.domain.Review;
import com.goodReads.library.service.impl.BookCascadeSampleImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class LibraryApplication implements CommandLineRunner {

	public static void main(String[] args) {
		// Run the Spring application
		SpringApplication.run(LibraryApplication.class, args);
	}
	@Autowired
	ApplicationContext context;// we use this to interact wih spring

	@Autowired
	BookRepository bookRepository;// id we auto wire the interface the implementation part is taken care by spring

	@Autowired
	UserRepository userRepository;
//	@Autowired
//	DBConfiguration dBConfiguration;
@Autowired
BookCascadeSampleImpl bookCascadeSample;

	@Override
	public void run(String... args) throws Exception {


		bookCascadeSample.testCascade(3);


		Book book=new Book();
		book.setTitle("testCascade");
		book.setAuthor("JK Rowling");
		book.setGenre(Genre.FANTASY);
		book.setRating(5.0);
		book.setCost(500.0);
		book.setYear(2000);
		List<Review> reviews=new ArrayList<>();
		reviews.add(new Review());

		bookRepository.save(book);

		List<Book> books =bookRepository.findAll();

		//books=bookRepository.findByAuthor("JK Rowling");

		books=bookRepository.findByTitleLike("testCascade");

		if(!CollectionUtils.isEmpty(books)){
			Book b=books.get(0);
			bookCascadeSample.testCascade(b.getId());

		}

//
//		Book book= new Book();// every time we run the app a new book will get added in db.  It's a common practice to include such code in the run method of a Spring Boot
//		// application's main class to initialize sample data or perform startup tasks when the application starts.
//		book.setTitle("Harry potter 1");
//		book.setAuthor("JK Rowling");
//		book.setGenre(Genre.FANTASY);
//		book.setCost(500.0);
//		book.setYear(2000);
//
//bookRepository.save(book);
//		this code snippet serves as an example of how to perform basic database operations using Spring Data JPA and demonstrates the usage of various query methods
//		provided by the BookRepository.
		//List<Book> books=bookRepository.findAll();

		books=bookRepository.findByAuthor("JK Rowling");

//		books=bookRepository.findByTitleLike("Harry%");


		books=bookRepository.findByAuthorAndGenre("JK Rowling", Genre.valueOf("FANTASY"));

		books=bookRepository.findByAuthorAndYear("JK Rowling",1999);
		for(Book book1:books){
			System.out.println(book1);
		}

	}
}
		/***
		 *
		 * Steps for hibernate:
		 * 1. add the spring data jpa maven dependency.
		 * 2. add the configurations of url,username,password in application.properties.
		 * 3. Annotate you domain with @Table and @Entity annotation.
		 * 4. Add an iD to the domain with desired ID generation type.
		 * 5. Create repository for each domain and let it extend the JPARepository.
		 * 6. write your custom queries to the interface.
		 * 7. Start using the repository for DB related task in the service layer.
		 *


		// Creating a Book object b
		Book b = new Book();
		b.setId("1");
		b.setTitle("abc");
		b.setAuthor("xyz");
		b.setGenre(Genre.FICTION);;// change made from org code to remove err
		b.setRating(5.0);
		b.setCost(200.0);
		//b.setReviewList(null);
		b.setYear(2019);

		// Creating another Book object b1 with identical properties
		Book b1 = new Book();
		b1.setId("1");
		b1.setTitle("abc");
		b1.setAuthor("xyz");
		b1.setGenre(Genre.FICTION);// change made from org code to remove err
		b1.setRating(5.0);
		b1.setCost(200.0);
		b1.setReviewList(null);
		b1.setYear(2019);

		// Creating a HashSet to store Book objects
		Set<Book> bookSet = new HashSet<>();

		// Adding Book objects to the HashSet
		bookSet.add(b); // Adding first Book object b
		bookSet.add(b1); // Adding second Book object b1
		bookSet.add(b); // Adding first Book object b again

		// Printing the size of the HashSet
		System.out.println(bookSet.size()); // Output: 2 (Expected)he output should be 3. This is because even though b and b1 have the same properties, they are separate instances of
		// the Book class. Therefore, both are added to the HashSet. When you attempt to add b again, it's not considered a duplicate due to the nature of HashSet,
		// which doesn't allow duplicate elements based on reference equality. So, the size of the HashSet remains 2

		 * */

