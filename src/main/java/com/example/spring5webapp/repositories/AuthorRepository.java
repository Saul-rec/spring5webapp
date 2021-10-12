package com.example.spring5webapp.repositories;

import com.example.spring5webapp.model.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {

    @Query(value = "SELECT a.*,b.title FROM AUTHOR a" +
            " inner join author_book ab" +
            " on a.id = ab.author_id" +
            " inner join book b" +
            " on ab.book_id = b.id", nativeQuery = true)
    List<Object[]> findAllData();

}
