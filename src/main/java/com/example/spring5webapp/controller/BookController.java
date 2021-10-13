package com.example.spring5webapp.controller;

import com.example.spring5webapp.model.BookDto;
import com.example.spring5webapp.repositories.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {

    private final BookRepository bookRepo;

    public BookController(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    @RequestMapping("/books")
    public String getBooks(Model model) {
        model.addAttribute("books", bookRepo.findAllBookData());
        return "bookView";
    }

    @GetMapping("/api/books")
    public ResponseEntity<List<BookDto>> apiGetBooks(){
        List<BookDto> dtoList = new ArrayList<>();
        List<Object[]> objList = bookRepo.findAllBookData();
        for (Object[] dto :objList) {
                BookDto bookDto = new BookDto();
                bookDto.setId(Long.decode(String.valueOf(dto[0])));
                bookDto.setTitle(String.valueOf(dto[1]));
                bookDto.setPublisher(String.valueOf(dto[2]));
                bookDto.setAuthor(String.valueOf(dto[3]));
                dtoList.add(bookDto);
        }
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }
}
