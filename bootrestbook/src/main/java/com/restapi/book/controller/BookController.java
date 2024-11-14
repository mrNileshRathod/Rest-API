package com.restapi.book.controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.restapi.book.entities.Book;
import com.restapi.book.services.BookServices;

@RestController
@RequestMapping("rest/api/v1")
public class BookController {
    
	@Autowired
    private BookServices bookServices;

    @GetMapping("/books")
    public ResponseEntity<List<?>> getBooks(){
        List<Book> list = this.bookServices.getAllBooks();

        if(list.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(list);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<?> getBook(@PathVariable("id") int id) {
        Book book = this.bookServices.getBookByID(id);

        if(Objects.isNull(book)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.of(Optional.of(book));
    }

    @PostMapping("/books")
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        Book b = null;

        try {
            b = this.bookServices.addBook(book);
            System.out.println(b);
            return ResponseEntity.of(Optional.of(b));
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } 
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") int id) {
        try {
            this.bookServices.deleteBook(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("books/{id}")
    public ResponseEntity<?> updateBook(@RequestBody Book book, @PathVariable("id") int id) {
        try {
            this.bookServices.updateBook(book, id);
            return ResponseEntity.ok().body(book);
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
