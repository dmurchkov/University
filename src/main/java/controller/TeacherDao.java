package controller;

import model.Teacher;

import java.util.List;

public interface TeacherDao {

    Teacher add(String name, int experience, int subjectId);

    List<Teacher> getAll();

    Teacher getById(int id);

    Teacher update(int id, String newName, int newExperience, int newGroupId);

    Teacher delete(int id);
}

