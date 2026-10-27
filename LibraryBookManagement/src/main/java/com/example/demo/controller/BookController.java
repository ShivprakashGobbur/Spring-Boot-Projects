package com.example.demo.controller;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.example.demo.model.Book;
import com.example.demo.service.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
public class BookController {
	 @Autowired
	    private BookService bookService;

	    // Add a book
	    @PostMapping
	    public ResponseEntity<Book> addBook(
	            @Valid @RequestBody Book book) {

	        Book savedBook = bookService.addBook(book);

	        return ResponseEntity.status(HttpStatus.CREATED)
	                .body(savedBook);
	    }

	    // Get all books
	    @GetMapping
	    public List<Book> getAllBooks() {
	        return bookService.getAllBooks();
	    }

	    // Get book by ID
	    @GetMapping("/{id}")
	    public Book getBookById(@PathVariable Long id) {
	        return bookService.getBookById(id);
	    }

	    // Update a book
	    @PutMapping("/{id}")
	    public Book updateBook(
	            @PathVariable Long id,
	            @Valid @RequestBody Book book) {

	        return bookService.updateBook(id, book);
	    }

	    // Delete a book
	    @DeleteMapping("/{id}")
	    public ResponseEntity<String> deleteBook(
	            @PathVariable Long id) {

	        return ResponseEntity.ok(
	                bookService.deleteBook(id));
	    }

	    // Search books by title
	    @GetMapping("/search")
	    public List<Book> searchByTitle(
	            @RequestParam String keyword) {

	        return bookService.searchByTitle(keyword);
	    }

	    // Search books by category
	    @GetMapping("/category")
	    public List<Book> getByCategory(
	            @RequestParam String category) {

	        return bookService.getByCategory(category);
	    }

	    // Find books within a maximum price
	    @GetMapping("/budget")
	    public List<Book> getBooksWithinBudget(
	            @RequestParam BigDecimal maxPrice) {

	        return bookService.getBooksWithinBudget(maxPrice);
	    }

	    // Update book availability
	    @PatchMapping("/{id}/availability")
	    public Book updateAvailability(
	            @PathVariable Long id,
	            @RequestParam Integer availableCopies) {

	        Book book = bookService.getBookById(id);

	        if (availableCopies == null || availableCopies < 0) {
	            throw new IllegalArgumentException(
	                    "Available copies cannot be negative");
	        }

	        book.setAvailableCopies(availableCopies);

	        return book;
	    }
}
