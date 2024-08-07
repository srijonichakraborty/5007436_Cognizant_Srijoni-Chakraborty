package com.library;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.library.service.BookService;

public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        BookService bookService = (BookService) context.getBean("bookService");
        bookService.manageBooks();
    }
}

//OUTPUT:
//	Managing books in the library...
//	Saving book to the library...
//	void com.library.service.BookService.manageBooks() executed in 25ms
