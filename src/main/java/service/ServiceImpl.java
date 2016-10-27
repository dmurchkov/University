package service;

import controller.*;
import model.*;
import utils.DatabaseUtils;

import javax.persistence.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ServiceImpl implements Service {
    private EntityManagerFactory factory;
    private StudentDao studentDao;
    private TeacherDao teacherDao;
    private SubjectDao subjectDao;
    private GroupDao groupDao;

    public ServiceImpl(EntityManagerFactory factory) {
        this.factory = factory;
        studentDao = new StudentDaoImpl(factory);
        teacherDao = new TeacherDaoImpl(factory);
        subjectDao = new SubjectDaoImpl(factory);
        groupDao = new GroupDaoImpl(factory);
    }

    //adding

    @Override
    public Student addStudent(Student student) {
        return studentDao.add(student);
    }

    @Override
    public Group addGroup(Group group) {
        return groupDao.add(group);
    }

    @Override
    public Subject addSubject(Subject subject) {
        return subjectDao.add(subject);
    }

    @Override
    public Teacher addTeacher(Teacher teacher) {
        return teacherDao.add(teacher);
    }

    //get all

    @Override
    public List<Student> getAllStudents() {
        return studentDao.getAll();
    }

    @Override
    public List<Group> getAllGroups() {
        return groupDao.getAll();
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subjectDao.getAll();
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherDao.getAll();
    }

    //updating

    @Override
    public Student updateStudentInfo(Object id, Student newInfo) {
        return studentDao.update(id, newInfo);
    }

    @Override
    public Group updateGroupInfo(Object id, Group newInfo) {
        return groupDao.update(id, newInfo);
    }

    @Override
    public Subject updateSubjectInfo(Object id, Subject newInfo) {
        return subjectDao.update(id, newInfo);
    }

    @Override
    public Teacher updateTeacherInfo(Object id, Teacher newInfo) {
        return teacherDao.update(id, newInfo);
    }


    @Override
    public List<Teacher> getMinExperience() {
        return teacherDao.withMinExperience();
    }

    @Override
    public List<Teacher> getMaxExperience() {
        return teacherDao.withMaxExperience();
    }

    @Override
    public List<Teacher> getTeachersByExperience(Object experience) {
        return teacherDao.getByExperience(experience);
    }

    @Override
    public List<Subject> getHumanitarian() {
        return subjectDao.getHumanitarian();
    }

    @Override
    public List<Student> getStudentByGroupId(Object groupId) {
        return studentDao.getByGroupId(groupId);
    }

    @Override
    public double getAvgMark(Object subjectId) {
        EntityManager manager = factory.createEntityManager();
        try {
            //SELECT avg(m.mark) FROM Mark m WHERE m.subject.id=:id
            Query query = manager.createQuery("SELECT avg(m.mark) FROM Mark m WHERE m.subject=:subject");
            query.setParameter("subject", subjectId);
            double toReturn = (double) query.getSingleResult();
            return toReturn;
        } finally {
            manager.close();
        }
    }

    @Override
    public Mark addMark(Student student, Subject subject, int mark) {
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        Mark toReturn = null;
        try {
            toReturn = new Mark(mark, student, subject);
            transaction.begin();
            manager.persist(toReturn);
            transaction.commit();
            return toReturn;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            manager.close();
        }
        return toReturn;
    }
}
