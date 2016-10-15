package jpa_intro.controller;

import jpa_intro.model.Author;

public interface AuthorDao {
    Author create(Author author);

    boolean delete(Author author);

    Author update(Author author);

    Author findById(Object id);
}
