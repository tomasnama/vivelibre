package com.tomasapp.vivelibre.book.component;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.tomasapp.vivelibre.book.dto.Book;
import com.tomasapp.vivelibre.book.dto.BookDate;
import com.tomasapp.vivelibre.book.dto.BookItemDate;

import lombok.extern.slf4j.Slf4j;

/**
 * @author tnavarro
 *
 */
@Component
@Slf4j
public class BookComponent {
	
	private Optional<BookDate> filter(String filter, List<Book> books) {
		//Escriba por pantalla los libros que no tengan fecha de publicación
		books.stream().filter(book->book.getPublicationTimestamp()==null)
					  .forEach(book-> {
						  System.out.println(book);
						  log.info(book.toString());
					  });
		
		/*Devuelva los libros que contengan la cadena de caracteres en el nombre, en el
		resumen y en la biografia del autor del libro. En caso de encontrar más de un
		libro en la lista devolver aquel más recientemente publicado.*/
		List<BookItemDate> result = books.stream().filter(book-> book.getSummary().contains(filter) 
								   ||book.getAuthor().getName().contains(filter)
								   ||book.getAuthor().getBio().contains(filter)).toList()
			 .stream().sorted(Comparator.comparing(Book::getPublicationTimestamp).reversed()).toList()
		/*Además se deberá devolver el libro con un campo date adicional que contenga el timestamp con el
		  siguiente formato de fecha: mm-dd-yyyy*/
			 .stream().map(Book::toBookItemDate).toList()
		/*Por último, la lista deberá quedar ordenada de la siguiente manera: Primero
		agrupada por fecha de publicación y luego ordenada por la biografia de autor
		más corta*/
			 .stream().sorted(getComparatorPublicationAndBioSize()).toList();
		return Optional.of(BookDate.builder().books(result).build());
	}
	
	private Comparator<BookItemDate> getComparatorPublicationAndBioSize() {
		return Comparator.comparing(BookItemDate::getPublicationTimestamp)
			     .thenComparing(BookItemDate::getBioSize);
	}
	
	public Optional<BookDate> getBooks(String filter, List<Book> books) {
		return filter(filter, books);
	}
	
	
}
