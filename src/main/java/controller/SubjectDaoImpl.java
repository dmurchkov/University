package controller;

import model.Student;
import model.Subject;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class SubjectDaoImpl implements SubjectDao {
    private static final Logger log = Logger.getLogger(StudentDao.class.getName());
    private EntityManagerFactory factory;

    public SubjectDaoImpl(EntityManagerFactory factory) {
        this.factory = factory;
    }

    @Override
    public Subject add(Subject subject) {
        log.info("addSubject");
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.persist(subject);
            transaction.commit();
            return subject;
        } catch (Exception e) {
            log.error(e);
            transaction.rollback();
        } finally {
            manager.close();
        }
        return subject;
    }

    @Override
    public List<Subject> getAll() {
        EntityManager manager = factory.createEntityManager();
        try {
            TypedQuery<Subject> subjects = manager.createQuery("FROM Subject", Subject.class);
            return subjects.getResultList();
        } finally {
            manager.close();
        }
    }

    @Override
    public Subject getById(Object id) {
        EntityManager manager = factory.createEntityManager();
        Subject toReturn;
        try {
            log.info("get subject by id");
            toReturn = manager.find(Subject.class, id);
        } finally {
            manager.close();
        }
        return toReturn;
    }

    @Override
    public Subject update(Object subjectId, Subject newData) {
        log.info("updating subject");
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            Subject toUpdate = manager.find(Subject.class, subjectId);
            transaction.begin();
            toUpdate.setName(newData.getName());
            toUpdate.setDescription(newData.getDescription());
            transaction.commit();
            return toUpdate;
        } catch (Exception e) {
            log.error(e);
            transaction.rollback();
        } finally {
            manager.close();
        }
        return null;
    }

    @Override
    public Subject delete(Object id) {
        log.info("delete subject");
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        Subject toDelete = null;
        try {
            toDelete = manager.find(Subject.class, id);
            transaction.begin();
            manager.remove(id);
            transaction.commit();
            return toDelete;
        } catch (Exception e) {
            log.error(e);
            transaction.rollback();
        } finally {
            manager.close();
        }
        return toDelete;
    }

    @Override
    public List<Subject> getHumanitarian() {
        EntityManager manager = factory.createEntityManager();
        try {
            TypedQuery<Subject> humanitarian = manager.createQuery("FROM Subject s WHERE s.description='humanitarian'", Subject.class);
            return humanitarian.getResultList();
        } finally {
            manager.close();
        }
    }
}
