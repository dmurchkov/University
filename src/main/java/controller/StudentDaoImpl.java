package controller;

import model.Student;
import org.apache.log4j.Logger;
import utils.DatabaseUtils;
import utils.PropertyLoader;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    private static final Logger log = Logger.getLogger(StudentDao.class.getName());
    private EntityManagerFactory factory;

    public StudentDaoImpl(EntityManagerFactory factory) {
        this.factory = factory;
    }

    @Override
    public Student add(Student student) {
        log.info("addStudent student");
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.persist(student);
            transaction.commit();
            return student;
        } catch (Exception e) {
            log.error(e);
            transaction.rollback();
        } finally {
            manager.close();
        }
        return student;
    }

    @Override
    public List<Student> getAll() {
        EntityManager manager = factory.createEntityManager();
        try {
            TypedQuery<Student> students = manager.createQuery("FROM Student", Student.class);
            return students.getResultList();
        } finally {
            manager.close();
        }
    }

    @Override
    public Student getById(Object id) {
        EntityManager manager = factory.createEntityManager();
        Student toReturn;
        try {
            log.info("get student by id");
            toReturn = manager.find(Student.class, id);
        } finally {

            manager.close();
        }
        return toReturn;
    }

    @Override
    public Student update(Object studentId, Student newData) {
        log.info("updating student");
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            Student toUpdate = manager.find(Student.class, studentId);
            transaction.begin();
            toUpdate.setName(newData.getName());
            toUpdate.setGroupId(newData.getGroupId());
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
    public Student delete(Object id) {
        log.info("delete student");
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        Student toDelete = null;
        try {
            toDelete = manager.find(Student.class, id);
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
    public List<Student> getByGroupId(Object groupId) {
        EntityManager manager = factory.createEntityManager();
        try {
            TypedQuery<Student> students =
                    manager.createQuery("FROM Student s WHERE s.groupId =:groupId", Student.class);
            students.setParameter("groupId", groupId);
            return students.getResultList();
        } finally {
            manager.close();
        }
    }
}
