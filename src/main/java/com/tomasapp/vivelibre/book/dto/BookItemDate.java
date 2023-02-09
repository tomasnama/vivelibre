package com.tomasapp.vivelibre.book.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class BookItemDate extends Book {

	private String publicationDate;
	private Integer bioSize;
	
}
