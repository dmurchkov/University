package jpa_intro.test;

import jpa_intro.controller.AuthorDao;
import jpa_intro.controller.AuthorDaoImpl;
import jpa_intro.model.Author;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

public class InsertAuthors {
    public static void main(String[] args) {
        Author author = new Author("authorName", 3000, new Date());
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("hibernate-unit");
        AuthorDao authorDao = new AuthorDaoImpl(factory);
        authorDao.create(author);
    }
}
