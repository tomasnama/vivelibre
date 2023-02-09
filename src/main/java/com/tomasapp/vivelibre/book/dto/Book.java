package com.tomasapp.vivelibre.book.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@SuperBuilder
public class Book {
	
	private Integer id;
	private String title;
	private Long publicationTimestamp;
	private String summary;
	private Author author;
	
	public BookItemDate toBookItemDate() {
		return BookItemDate.builder()
				.id(this.id)
				.title(this.title)
				.author(this.author)
				.publicationTimestamp(this.publicationTimestamp)
				.summary(this.summary)
				.publicationDate(getPublicationDate())
				.bioSize(author.getBio().length())
				.build();
	}
	
	private String getPublicationDate() {
		Date date = Date.from(Instant.ofEpochMilli(publicationTimestamp));
		DateFormat formatter = new SimpleDateFormat("mm-dd-yyyy");
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		return formatter.format(date);
	}
	

}
