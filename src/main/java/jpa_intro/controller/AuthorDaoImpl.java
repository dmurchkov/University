package jpa_intro.controller;

import jpa_intro.model.Author;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class AuthorDaoImpl implements AuthorDao {

    private EntityManagerFactory factory;

    public AuthorDaoImpl(EntityManagerFactory factory) {
        this.factory = factory;
    }

    @Override
    public Author create(Author author) {
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.persist(author);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            manager.close();
        }
        return author;
    }

    @Override
    public boolean delete(Author author) {
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        Author toDelete = manager.find(Author.class, author.getId());
        try {
            transaction.begin();
            manager.remove(toDelete);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            return false;
        } finally {
            manager.close();
        }
        return true;
    }

    @Override
    public Author update(Author author) {
        return null;
    }

    @Override
    public Author findById(Object id) {
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            Author toReturn = manager.find(Author.class, id);
            return toReturn;
        } finally {
            manager.close();
        }
    }
}
