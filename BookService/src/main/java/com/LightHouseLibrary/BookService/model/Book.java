package com.LightHouseLibrary.BookService.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Book {

	@Id
	@SequenceGenerator(
			name="book_sequence",
			sequenceName = "book_sequence",
			allocationSize = 1
			)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "book_sequence"
			)
	private long bookId;

	@Column(name = "book_name", nullable = false)
	private String name;
	private String author;

	private long pages;
	private String category;
	private int quantity;

}
