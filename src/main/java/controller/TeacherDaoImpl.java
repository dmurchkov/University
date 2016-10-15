package controller;

import model.Student;
import model.Teacher;
import org.apache.log4j.Logger;
import utils.DatabaseUtils;
import utils.PropertyLoader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDaoImpl implements TeacherDao {

    private static final Logger log = Logger.getLogger(TeacherDaoImpl.class.getName());
    public static final String ADD_TEACHER_QUERY = PropertyLoader.loadProperty("ADD_TEACHER_QUERY");
    public static final String GET_LAST_TEACHER_ID_QUERY = PropertyLoader.loadProperty("GET_LAST_TEACHER_ID_QUERY");
    public static final String GET_ALL_TEACHERS_QUERY = PropertyLoader.loadProperty("GET_ALL_TEACHERS_QUERY");
    public static final String GET_TEACHER_BY_ID_QUERY = PropertyLoader.loadProperty("GET_TEACHER_BY_ID_QUERY");

    @Override
    public Teacher add(String name, int experience, int subjectId) {
        log.info("adding new teacher");
        Teacher toReturn = null;
        if (name == null || name.isEmpty() || experience < 0 || subjectId <= 0) {
            log.info("adding new teacher -> FAIL (invalid parameters)");
            return toReturn;
        }
        try (Connection connection = DatabaseUtils.getDBConnection("university")) {
            PreparedStatement add = connection.prepareStatement(ADD_TEACHER_QUERY);
            add.setString(1, name);
            add.setInt(2, experience);
            add.setInt(3, subjectId);
            add.execute();
            PreparedStatement getLastId = connection.prepareStatement(GET_LAST_TEACHER_ID_QUERY);
            ResultSet resultSet = getLastId.executeQuery();
            int id = 0;
            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }
            toReturn = new Teacher(id, name, experience, subjectId);
            log.info("adding new teacher -> success");
            return toReturn;
        } catch (SQLException e) {
            log.error("adding new teacher -> FAIL");
            e.printStackTrace();
        }
        return toReturn;
    }

    @Override
    public List<Teacher> getAll() {
        log.info("return all teachers from DB");
        try (Connection connection = DatabaseUtils.getDBConnection("university")) {
            List<Teacher> toReturn = new ArrayList<>();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_TEACHERS_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Teacher tmp = new Teacher(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("experience"),
                        resultSet.getInt("subject_id"));
                toReturn.add(tmp);
            }
            log.info("return all teachers from DB -> success");
            return toReturn;
        } catch (SQLException e) {
            log.error("return all teachers from DB -> FAIL");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Teacher getById(int id) {
        log.info("get teacher by id");
        Teacher toReturn = null;
        try (Connection connection = DatabaseUtils.getDBConnection("university")) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_TEACHER_BY_ID_QUERY);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                toReturn = new Teacher(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("experience"),
                        resultSet.getInt("subject_id"));
            }
            log.info("get teacher by id -> success");
            return toReturn;
        } catch (SQLException e) {
            log.error("get teacher by id -> FAIL");
            e.printStackTrace();
        }
        return toReturn;
    }

    @Override
    public Teacher update(int id, String newName, int newExperience, int newSubjectId) {
        log.info("update teacher " + id);
        if (newName == null || newName.isEmpty() || newExperience < 0 || newSubjectId <= 0) {
            log.info("update teacher " + id + " -> FAIL (invalid parameter)");
            return null;
        }
        Teacher toReturn = null;
        try (Connection connection = DatabaseUtils.getDBConnection("university")) {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("UPDATE teachers SET name='?', experience=?, subject_id=? WHERE id=?");
            preparedStatement.setString(1, newName);
            preparedStatement.setInt(2, newExperience);
            preparedStatement.setInt(3, newSubjectId);
            preparedStatement.setInt(4, id);
            preparedStatement.execute();
            toReturn = new Teacher(id, newName, newExperience, newSubjectId);
            log.info("update teacher " + id + " -> success");
            return toReturn;
        } catch (SQLException e) {
            log.error("update teacher " + id + " -> FAIL");
            e.printStackTrace();
        }
        return toReturn;
    }

    @Override
    public Teacher delete(int id) {
        return null;
    }
}
