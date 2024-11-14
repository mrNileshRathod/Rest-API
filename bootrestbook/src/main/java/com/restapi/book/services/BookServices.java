package com.restapi.book.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.book.entities.Book;
import com.restapi.book.repository.BookRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BookServices{

	@Autowired
    private BookRepository bookRepository;

    // private final BookRepository bookRepository;

    // private static List<Book> bookList = new ArrayList<>();

    // static{
    //     bookList.add(new Book(100, "Spring", "Tom"));
    //     bookList.add(new Book(101, "RestAPI", "Bob"));
    //     bookList.add(new Book(102, "SpringBoot", "Alice"));
    // }

    public List<Book> getAllBooks() {
        List<Book> list = (List<Book>) this.bookRepository.findAll();
        return list;
    }

    public Book getBookByID(int id) {
        Book book = null;
        try {
            book = this.bookRepository.findById(id);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return book;
    }

    public Book addBook(Book book) {
        bookRepository.save(book);
        return book;
    }

    public void deleteBook(int id) {
        // bookList = bookList.stream().filter(e->e.getId() != id).collect(Collectors.toList());
        bookRepository.deleteById(id);
    }

    public void updateBook(Book book, int id) {
        // bookList = bookList.stream().map(b-> {
        //     if(b.getId() == id) {
        //         b.setTitle(book.getTitle());
        //         b.setAuthor(book.getAuthor());
        //     }
        //     return b;   
        // }).collect(Collectors.toList());
        book.setId(id);
        bookRepository.save(book);
    }
    
}
