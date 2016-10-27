package controller;

import model.Student;
import model.Teacher;
import org.apache.log4j.Logger;

import javax.persistence.*;
import java.util.List;


public class TeacherDaoImpl implements TeacherDao {

    private static final Logger log = Logger.getLogger(StudentDao.class.getName());
    private EntityManagerFactory factory;

    public TeacherDaoImpl(EntityManagerFactory factory) {
        this.factory = factory;
    }

    @Override
    public Teacher add(Teacher teacher) {
        log.info("addTeacher");
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.persist(teacher);
            transaction.commit();
            return teacher;
        } catch (Exception e) {
            log.error(e);
            transaction.rollback();
        } finally {
            manager.close();
        }
        return teacher;
    }

    @Override
    public List<Teacher> getAll() {
        EntityManager manager = factory.createEntityManager();
        try {
            TypedQuery<Teacher> teachers = manager.createQuery("FROM Teacher", Teacher.class);
            return teachers.getResultList();
        } finally {
            manager.close();
        }
    }

    @Override
    public Teacher getById(Object id) {
        EntityManager manager = factory.createEntityManager();
        Teacher toReturn;
        try {
            log.info("get teacher by id");
            toReturn = manager.find(Teacher.class, id);
        } finally {
            manager.close();
        }
        return toReturn;
    }

    @Override
    public Teacher update(Object teacherId, Teacher newData) {
        log.info("updating teacher");
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            Teacher toUpdate = manager.find(Teacher.class, teacherId);
            transaction.begin();
            toUpdate.setName(newData.getName());
            toUpdate.setExperience(newData.getExperience());
            toUpdate.setSubjectId(newData.getSubjectId());
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
    public Teacher delete(Object id) {
        log.info("delete teacher");
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        Teacher toDelete = null;
        try {
            toDelete = manager.find(Teacher.class, id);
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
    public List<Teacher> withMinExperience() {
        EntityManager manager = factory.createEntityManager();
        try {
            TypedQuery<Teacher> query = manager.createQuery("FROM Teacher t WHERE t.experience=(SELECT MIN(t.experience) FROM Teacher t)", Teacher.class);
            return query.getResultList();
        } finally {
            manager.close();
        }
    }

    @Override
    public List<Teacher> withMaxExperience() {
        EntityManager manager = factory.createEntityManager();
        try {
            TypedQuery<Teacher> query = manager.createQuery("FROM Teacher t WHERE t.experience=(SELECT MAX(t.experience) FROM Teacher t)", Teacher.class);
            return query.getResultList();
        } finally {
            manager.close();
        }
    }

    @Override
    public List<Teacher> getByExperience(Object experience) {
        EntityManager manager = factory.createEntityManager();
        try {
            TypedQuery<Teacher> query = manager.createQuery("FROM Teacher t where t.experience=:experience)", Teacher.class);
            query.setParameter("experience", experience);
            return query.getResultList();
        } finally {
            manager.close();
        }
    }
}
