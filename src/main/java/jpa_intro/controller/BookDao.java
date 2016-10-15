package jpa_intro.controller;

import jpa_intro.model.Book;

public interface BookDao {
    Book create(Book book);

    boolean delete(Book book);

    Book update(Book book);

    Book findById(Object id);
}
