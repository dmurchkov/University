package controller;

import model.Teacher;

import java.util.List;

public interface TeacherDao {

    Teacher add(Teacher teacher);

    List<Teacher> getAll();

    Teacher getById(Object id);

    Teacher update(Object teacherId, Teacher newData);

    Teacher delete(Object id);

    List<Teacher> withMinExperience();

    List<Teacher> withMaxExperience();

    List<Teacher> getByExperience(Object experience);

}

