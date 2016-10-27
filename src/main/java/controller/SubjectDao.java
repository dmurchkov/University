package controller;

import model.Subject;

import java.util.List;

public interface SubjectDao {
    Subject add(Subject subject);

    List<Subject> getAll();

    Subject getById(Object id);

    Subject update(Object subjectId, Subject newData);

    Subject delete(Object id);

    List<Subject> getHumanitarian();
}
