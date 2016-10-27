package controller;

import model.Student;

import java.util.List;

public interface StudentDao {

    Student add(Student student);

    List<Student> getAll();

    Student getById(Object id);

    Student update(Object studentId, Student newData);

    Student delete(Object id);

    List<Student> getByGroupId(Object groupId);
}
