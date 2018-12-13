package Impl;

import dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Book;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    public BookDao bookDao;

    public Book findBookById(String isbn) {
        return bookDao.findBookById(isbn);
    }
}

