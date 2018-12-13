package dao;


import pojo.Book;

public interface BookDao {
    public Book findBookById(String isbn);
}
