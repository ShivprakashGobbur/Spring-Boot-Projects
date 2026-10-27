package com.example.demo.service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.BookNotFoundException;
import com.example.demo.model.Book;

@Service
public class BookService {
	private final List<Book> books = new ArrayList<>();
    private Long nextId = 1L;

    // Add a book
    public Book addBook(Book book) {
        book.setId(nextId++);
        books.add(book);
        return book;
    }

    // Get all books
    public List<Book> getAllBooks() {
        return books;
    }

    // Get book by ID
    public Book getBookById(Long id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                    new BookNotFoundException(
                        "Book not found with ID: " + id));
    }

    // Update a book
    public Book updateBook(Long id, Book updatedBook) {
        Book existingBook = getBookById(id);

        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setIsbn(updatedBook.getIsbn());
        existingBook.setCategory(updatedBook.getCategory());
        existingBook.setPrice(updatedBook.getPrice());
        existingBook.setAvailableCopies(
                updatedBook.getAvailableCopies());
        existingBook.setPublisher(updatedBook.getPublisher());
        existingBook.setLanguage(updatedBook.getLanguage());

        return existingBook;
    }

    // Delete a book
    public String deleteBook(Long id) {
        Book book = getBookById(id);
        books.remove(book);
        return "Book deleted successfully with ID: " + id;
    }

    // Search books by title
    public List<Book> searchByTitle(String keyword) {
        return books.stream()
                .filter(book -> book.getTitle()
                        .toLowerCase()
                        .contains(keyword.toLowerCase()))
                .toList();
    }

    // Search books by category
    public List<Book> getByCategory(String category) {
        return books.stream()
                .filter(book -> book.getCategory()
                        .equalsIgnoreCase(category))
                .toList();
    }

    // Find books within a maximum price
    public List<Book> getBooksWithinBudget(BigDecimal maxPrice) {
        return books.stream()
                .filter(book -> book.getPrice()
                        .compareTo(maxPrice) <= 0)
                .toList();
    }
}
