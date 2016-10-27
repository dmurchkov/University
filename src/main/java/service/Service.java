package service;

import model.*;

import javax.persistence.EntityManager;
import java.util.List;

public interface Service {

    List<Student> getAllStudents();

    List<Group> getAllGroups();

    List<Subject> getAllSubjects();

    List<Teacher> getAllTeachers();

    Student addStudent(Student student);

    Group addGroup(Group group);

    Subject addSubject(Subject subject);

    Teacher addTeacher(Teacher teacher);

    Student updateStudentInfo(Object id, Student newInfo);

    Group updateGroupInfo(Object id, Group newInfo);

    Subject updateSubjectInfo(Object id, Subject newInfo);

    Teacher updateTeacherInfo(Object id, Teacher newInfo);

    List<Teacher> getMinExperience();

    List<Teacher> getMaxExperience();

    List<Teacher> getTeachersByExperience(Object experience);

    List<Subject> getHumanitarian();

    List<Student> getStudentByGroupId(Object groupId);

    double getAvgMark(Object subjectId);

    Mark addMark(Student student, Subject subject, int mark);

}
