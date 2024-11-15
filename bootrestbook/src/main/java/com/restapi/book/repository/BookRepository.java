package com.restapi.book.repository;

import org.springframework.data.repository.CrudRepository;

import com.restapi.book.entities.Book;

public interface BookRepository extends CrudRepository<Book, Integer> {

    public Book findById(int id);
    
}
