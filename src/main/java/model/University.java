package model;

import model.*;
import utils.DatabaseUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class University {
    private List<Student> students;
    private List<Group> groups;
    private List<Subject> subjects;
    private List<Teacher> teachers;
    private List<EducationProgramme> educationProgrammes;

    public University() throws ClassNotFoundException {
        students = setUpUniversity.getStudents();
        groups = setUpUniversity.getGroups();
        subjects = setUpUniversity.getSubjects();
        teachers = setUpUniversity.getTeachers();
        educationProgrammes = setUpUniversity.getEducationProgrammes();
    }

    public static class setUpUniversity {

        public static List<Student> getStudents() throws ClassNotFoundException {
            List<Student> students = null;
            try {
                students = new ArrayList<>();
                Connection connection = DatabaseUtils.getDBConnection("university");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM students;");
                while (resultSet.next()) {
                    Student tmp =
                            new Student(resultSet.getInt("id"),
                                    resultSet.getString("name"),
                                    resultSet.getInt("group_id"));
                    students.add(tmp);
                }
                return students;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return students;
        }

        public static List<Group> getGroups() throws ClassNotFoundException {
            List<Group> groups = null;
            try {
                groups = new ArrayList<>();
                Connection connection = DatabaseUtils.getDBConnection("university");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM groups;");
                while (resultSet.next()) {
                    Group tmp = new Group(resultSet.getInt("id"), resultSet.getString("name"));
                    groups.add(tmp);
                }
                return groups;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return groups;
        }

        public static List<Subject> getSubjects() throws ClassNotFoundException {
            List<Subject> subjects = null;
            try {
                subjects = new ArrayList<>();
                Connection connection = DatabaseUtils.getDBConnection("university");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM subjects;");
                while (resultSet.next()) {
                    Subject tmp =
                            new Subject(resultSet.getInt("id"),
                                    resultSet.getString("name"),
                                    resultSet.getString("description"));
                    subjects.add(tmp);
                }
                return subjects;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return subjects;
        }

        public static List<Teacher> getTeachers() throws ClassNotFoundException {
            List<Teacher> teachers = null;
            try {
                teachers = new ArrayList<>();
                Connection connection = DatabaseUtils.getDBConnection("university");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM teachers;");
                while (resultSet.next()) {
                    Teacher tmp =
                            new Teacher(resultSet.getInt("id"),
                                    resultSet.getString("name"),
                                    resultSet.getInt("experience"),
                                    resultSet.getInt("subject_id"));
                    teachers.add(tmp);
                }
                return teachers;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return teachers;
        }

        public static List<EducationProgramme> getEducationProgrammes() throws ClassNotFoundException {
            List<EducationProgramme> educationProgrammes = null;
            try {
                educationProgrammes = new ArrayList<>();
                Connection connection = DatabaseUtils.getDBConnection("university");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM education_programmes;");
                while (resultSet.next()) {
                    EducationProgramme tmp =
                            new EducationProgramme(resultSet.getInt("group_id"),
                                    resultSet.getInt("subject_id"));
                    educationProgrammes.add(tmp);
                }
                return educationProgrammes;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return educationProgrammes;
        }
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public List<EducationProgramme> getEducationProgrammes() {
        return educationProgrammes;
    }

    public boolean addStudent(Student student) throws ClassNotFoundException {
        if (student == null || student.getClass() != Student.class) return false;
        Connection connection = DatabaseUtils.getDBConnection("university");
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("INSERT INTO students(name, group_id) VALUES('%s','%d');",
                    student.getName(), student.getGroupId()));
            students.add(student);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addGroup(Group group) throws ClassNotFoundException {
        if (group == null || group.getClass() != Group.class) return false;
        Connection connection = DatabaseUtils.getDBConnection("university");
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("INSERT INTO groups(name) VALUES('%s');", group.getName()));
            groups.add(group);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addSubject(Subject subject) throws ClassNotFoundException {
        if (subject == null || subject.getClass() != Subject.class) return false;
        Connection connection = DatabaseUtils.getDBConnection("university");
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("INSERT INTO subjects(name, description) VALUES('%s','%s');",
                    subject.getName(), subject.getDescription()));
            subjects.add(subject);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addTeacher(Teacher teacher) throws ClassNotFoundException {
        if (teacher == null || teacher.getClass() != Teacher.class) return false;
        Connection connection = DatabaseUtils.getDBConnection("university");
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("INSERT INTO teachers(name, experience, subject_id) VALUES('%s','%d','%d');",
                    teacher.getName(), teacher.getExperience(), teacher.getSubjectId()));
            teachers.add(teacher);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean changeGroup(int studentId, int newGroupId) throws ClassNotFoundException {
        Student tmp = findStudentById(studentId);
        if (tmp == null || !groupIsPresent(newGroupId)) return false;
        Connection connection = DatabaseUtils.getDBConnection("university");
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("UPDATE students SET id=%d, name='%s', group_id=%d WHERE id=%d",
                    tmp.getId(), tmp.getName(), newGroupId, tmp.getId()));
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public boolean groupIsPresent(int id) {
        for (Group group : groups) {
            if (group.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public List<Student> getStudentsByGroupId(int groupId) {
        if (groupIsPresent(groupId)) {
            List<Student> toReturn = new ArrayList<>();
            for (Student student : students) {
                if (student.getGroupId() == groupId) {
                    toReturn.add(student);
                }
            }
            return toReturn;
        }
        return null;
    }

    @Override
    public String toString() {
        return "model.University{" +
                "students=" + students +
                ", groups=" + groups +
                ", subjects=" + subjects +
                ", teachers=" + teachers +
                ", educationProgrammes=" + educationProgrammes +
                '}';
    }
}
