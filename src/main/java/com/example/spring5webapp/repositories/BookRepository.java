package com.example.spring5webapp.repositories;

import com.example.spring5webapp.model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    @Query(value = "SELECT b.id,b.title, p.name as publisher, concat(a.firstname ,' ', a.lastname) as author  FROM BOOK b" +
            " inner join publisher p" +
            " on b.publisher_id = p.id" +
            " inner join author_book au" +
            " on b.id = au.book_id" +
            " inner join author a" +
            " on au.author_id = a.id", nativeQuery = true)
    List<Object[]> findAllBookData();

}