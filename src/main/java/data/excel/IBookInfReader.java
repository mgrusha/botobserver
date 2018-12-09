package data.excel;

import data.entities.Book;

import java.util.List;

public interface IBookInfReader {
    public List<Book> getAllBooks();

    public Book getBook(String bookName);
}
