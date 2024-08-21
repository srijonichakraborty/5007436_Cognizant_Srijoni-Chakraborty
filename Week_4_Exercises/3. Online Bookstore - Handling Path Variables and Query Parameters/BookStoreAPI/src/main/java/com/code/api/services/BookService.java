package com.code.api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.code.api.entity.Book;


@Service

public class BookService {


	private final List<Book> books = List.of(
	        new Book(1,"java", "Test", 950.00, "isbn-123456789"),
	        new Book(2,"Advance Java", "test1", 450.23, "isbn-895630")
	    );
    public List<Book> getAllBooks() {
        // Logic to fetch all books
        return List.of(
            new Book(1,"Merchant of Venice", "W. Shakespeare", 639.23, "ISBN123456"),
            new Book(2,"Harry Potter", "J.K Rowling", 1500.12, "ISBN789632")
        );
    }

    public Book getBookById(int id) {
        return books.stream()
            .filter(book -> book.getId()==id)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public List<Book> filterBooks(String title, String author) {
        return books.stream()
            .filter(book -> (title == null || book.getTitle().equalsIgnoreCase(title)) &&
                            (author == null || book.getAuthor().equalsIgnoreCase(author)))
            .collect(Collectors.toList());
    }
    
}
