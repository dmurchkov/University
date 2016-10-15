package controller;

import model.Student;

import java.util.List;

public interface StudentDao {

    boolean add(Student student);

    List<Student> getAll();

    Student getById(int id);

    Student update(int studentId, Student newData);

    Student delete(int id);
}
