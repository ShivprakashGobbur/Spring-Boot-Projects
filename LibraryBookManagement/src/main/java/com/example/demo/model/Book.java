package com.example.demo.model;
import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
	 private Long id;

	    @NotBlank(message = "Title is required")
	    @Size(min = 2, max = 100,
	          message = "Title must contain 2 to 100 characters")
	    private String title;

	    @NotBlank(message = "Author is required")
	    private String author;

	    @NotBlank(message = "ISBN is required")
	    private String isbn;

	    @NotBlank(message = "Category is required")
	    private String category;

	    @NotNull(message = "Price is required")
	    @DecimalMin(value = "0.0",
	                message = "Price cannot be negative")
	    private BigDecimal price;

	    @NotNull(message = "Available copies are required")
	    @Min(value = 0,
	         message = "Available copies cannot be negative")
	    private Integer availableCopies;

	    
	    @NotBlank(message = "Publisher is required")
	    private String publisher;

	    
	    @NotBlank(message = "Language is required")
	    private String language;
}
