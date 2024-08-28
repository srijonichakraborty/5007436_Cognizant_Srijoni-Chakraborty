package com.code.api.controllers;

import com.code.api.entity.Book;
import com.code.api.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    public void setup() {
        bookRepository.deleteAll(); // Clean up the database before each test
    }

    @Test
    public void testGetAllBooks() throws Exception {
        // Arrange
        Book book1 = new Book("123", "Test Book 1", "Test Author", 9.99);
        Book book2 = new Book("124", "Test Book 2", "Test Author", 14.99);
        bookRepository.save(book1);
        bookRepository.save(book2);

        // Act and Assert
        mockMvc.perform(get("/api/books")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].isbn", is("123")))
                .andExpect(jsonPath("$[1].isbn", is("124")));
    }

    @Test
    public void testCreateBook() throws Exception {
        // Arrange
        String newBook = "{\"isbn\":\"125\",\"title\":\"New Book\",\"author\":\"New Author\",\"price\":19.99}";

        // Act and Assert
        mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newBook))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.isbn", is("125")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", is("New Book")));
    }

    @Test
    public void testDeleteBook() throws Exception {
        // Arrange
        Book book = new Book("126", "To Be Deleted", "Test Author", 9.99);
        book = bookRepository.save(book);

        // Act and Assert
        mockMvc.perform(delete("/api/books/" + book.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    // Add more tests for other endpoints (update, find by id, etc.) as needed
}
