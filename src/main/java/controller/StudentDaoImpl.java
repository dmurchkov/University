package controller;

import model.Student;
import org.apache.log4j.Logger;
import utils.DatabaseUtils;
import utils.PropertyLoader;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    private static final Logger log = Logger.getLogger(StudentDao.class.getName());

    public static final String ADD_STUDENT_QUERY = PropertyLoader.loadProperty("ADD_STUDENT_QUERY");
    public static final String GET_ALL_STUDENTS_QUERY = PropertyLoader.loadProperty("GET_ALL_STUDENTS_QUERY");
    public static final String GET_STUDENT_BY_ID_QUERY = PropertyLoader.loadProperty("GET_STUDENT_BY_ID_QUERY");
    public static final String UPDATE_STUDENT_QUERY = PropertyLoader.loadProperty("UPDATE_STUDENT_QUERY");
    public static final String DELETE_STUDENT_QUERY = PropertyLoader.loadProperty("DELETE_STUDENT_QUERY");

    @Override
    public boolean add(Student student) {
        log.info("add new student to DB");
        try (Connection connection = DatabaseUtils.getDBConnection("university")) {
            if (student == null || student.getClass() != Student.class) {
                log.info("add new student to DB -> FAIL (invalid parameter)");
                return false;
            }
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_STUDENT_QUERY);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getGroupId());
            preparedStatement.execute();
            log.info("add new student to DB -> success");
            return true;
        } catch (SQLException e) {
            log.error("add new student to DB -> FAIL");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Student> getAll() {
        log.info("return all students from DB");
        try (Connection connection = DatabaseUtils.getDBConnection("university")) {
            List<Student> toReturn = new ArrayList<>();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_STUDENTS_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Student tmp = new Student(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("group_id"));
                toReturn.add(tmp);
            }
            log.info("return all students from DB -> success");
            return toReturn;
        } catch (SQLException e) {
            log.error("return all students from DB -> FAIL");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Student getById(int id) {
        log.info("get student by id");
        Student toReturn = null;
        try (Connection connection = DatabaseUtils.getDBConnection("university")) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_STUDENT_BY_ID_QUERY);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                toReturn = new Student(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("group_id"));
            }
            log.info("get student by id -> success");
            return toReturn;
        } catch (SQLException e) {
            log.error("get student by id -> FAIL");
            e.printStackTrace();
        }
        return toReturn;
    }

    @Override
    public Student update(int studentId, Student newData) {
        log.info("update student " + studentId);
        if (newData == null || newData.getClass() != Student.class) {
            log.info("update student " + studentId + " -> FAIL (invalid parameter)");
            return null;
        }
        Student toReturn = null;
        try (Connection connection = DatabaseUtils.getDBConnection("university")) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STUDENT_QUERY);
            preparedStatement.setString(1, newData.getName());
            preparedStatement.setInt(2, newData.getGroupId());
            preparedStatement.setInt(3, studentId);
            preparedStatement.execute();
            toReturn = getById(studentId);
            log.info("update student " + studentId + " -> success");
            return toReturn;
        } catch (SQLException e) {
            log.error("update student " + studentId + " -> FAIL");
            e.printStackTrace();
        }
        return toReturn;
    }

    @Override
    public Student delete(int id) {
        log.info("delete student " + id);
        Student toReturn = null;
        try (Connection connection = DatabaseUtils.getDBConnection("university")) {
            toReturn = getById(id);
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STUDENT_QUERY);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            return toReturn;
        } catch (SQLException e) {
            log.error("delete student " + id + " -> FAIL");
            e.printStackTrace();
        }
        return toReturn;
    }
}
