package jpa_intro.controller;

import jpa_intro.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class BookDaoImpl implements BookDao {
    private EntityManagerFactory factory;

    public BookDaoImpl(EntityManagerFactory factory) {
        this.factory = factory;
    }

    @Override
    public Book create(Book book) {
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.persist(book);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            manager.close();
        }
        return book;
    }

    @Override
    public boolean delete(Book book) {
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        Book toDelete = manager.find(Book.class, book.getId());
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
    public Book update(Book book) {
        return null;
    }

    @Override
    public Book findById(Object id) {
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            Book toReturn = manager.find(Book.class, id);
            return toReturn;
        } finally {
            manager.close();
        }
    }
}
