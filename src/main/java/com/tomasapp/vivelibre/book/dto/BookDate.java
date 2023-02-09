package com.tomasapp.vivelibre.book.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookDate {
	
	private List<BookItemDate> books;

}
