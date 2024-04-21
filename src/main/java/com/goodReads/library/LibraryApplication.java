package com.goodReads.library;

import com.goodReads.library.domain.Book;
import com.goodReads.library.domain.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class LibraryApplication implements CommandLineRunner {

	public static void main(String[] args) {
		// Run the Spring application
		SpringApplication.run(LibraryApplication.class, args);
	}

	@Autowired
	ApplicationContext context;// we use this to interact wih spring
	@Override
	public void run(String... args) throws Exception {
		// Creating a Book object b
		Book b = new Book();
		b.setId("1");
		b.setTitle("abc");
		b.setAuthor("xyz");
		b.setGenre(Genre.FICTION);;// change made from org code to remove err
		b.setRating(5.0);
		b.setCost(200.0);
		b.setReviewList(null);
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



	}
}
