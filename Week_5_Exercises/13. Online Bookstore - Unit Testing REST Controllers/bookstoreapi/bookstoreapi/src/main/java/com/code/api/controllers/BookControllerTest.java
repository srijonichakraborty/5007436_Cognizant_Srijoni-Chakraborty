package com.code.api.controllers;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.code.api.dto.BookDTO;
import com.code.api.entity.Book;
import com.code.api.services.BookService;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new BookController(bookService)).build();
    }

    @Test
    void testGetBooks() throws Exception {
        BookDTO book1 = new BookDTO(1, "Title1", "Author1", "Publisher1", 2020);
        BookDTO book2 = new BookDTO(2, "Title2", "Author2", "Publisher2", 2021);
        List<BookDTO> books = Arrays.asList(book1, book2);

        when(bookService.getBooks()).thenReturn(books);

        mockMvc.perform(get("/api/books/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title", is("Title1")))
                .andExpect(jsonPath("$[1].title", is("Title2")));
    }

    @Test
    void testAddBook() throws Exception {
        Book book = new Book(1, "Goblet of Fire", "J.K. Rowling",600.00, "ISBN-12345678",0);

        when(bookService.addBook(any(Book.class))).thenReturn(book);

        mockMvc.perform(post("/api/books/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"Title1\", \"author\": \"Author1\", \"publisher\": \"Publisher1\", \"year\": 2020}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title", is("Title1")));
    }

    // Additional test cases...
}
