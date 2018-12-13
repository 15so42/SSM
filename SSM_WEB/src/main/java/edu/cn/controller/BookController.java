package edu.cn.controller;

import Impl.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.Book;

@Controller
public class BookController {
    @Autowired
    public BookService bookServiceImpl;

    @RequestMapping(value="/book/{isbn}")
    @ResponseBody
    public Book getBookByIsbn(@PathVariable("isbn") String isbn){
        return bookServiceImpl.findBookById(isbn);
    }

}
