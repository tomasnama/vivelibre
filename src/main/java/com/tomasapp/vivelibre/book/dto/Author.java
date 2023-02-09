package com.tomasapp.vivelibre.book.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Author {
	
	private String name;
	private String bio;
	
}
