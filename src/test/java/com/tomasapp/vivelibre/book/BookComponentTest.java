package com.tomasapp.vivelibre.book;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tomasapp.vivelibre.book.component.BookComponent;
import com.tomasapp.vivelibre.book.dto.Book;
import com.tomasapp.vivelibre.book.dto.BookDate;

@SpringBootTest
public class BookComponentTest {
	
	@Autowired
	BookComponent bookComponent;
	
	 private static final String filePath = "books.json";
	
	@Test
	void filter() {
		List<Book> books = getBooks();
		assertTrue(books.size()==8);
		String filter = "Harry";
		BookDate bookDate = bookComponent.getBooks(filter, books).orElseThrow();
		assertTrue(bookDate.getBooks().size()==2);
	}

	private List<Book> getBooks() {
		Gson gson = new Gson();
		try (FileReader reader = new FileReader(ClassLoader.getSystemResource(filePath).getFile())) {
			Type listType = new TypeToken<List<Book>>(){}.getType();
			List<Book> books = gson.fromJson(reader, listType);
           return books;
       } catch (Exception ex) {
            ex.printStackTrace();
       }
		return null;
	}
	
	
	
	

}
