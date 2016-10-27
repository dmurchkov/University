package integration;

import model.*;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.log4j.Logger;
import org.junit.*;
import service.ServiceImpl;
import utils.DatabaseUtils;

import javax.persistence.*;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class JpaTest {

    private static final Connection CONNECTION = DatabaseUtils.getDBConnection("fake_db");
    private static ScriptRunner scriptRunner = new ScriptRunner(CONNECTION);
    private static EntityManagerFactory factory;
    private static ServiceImpl service;
    private List<Student> students;
    private List<Teacher> teachers;
    private List<Group> groups;
    private List<Subject> subjects;

    @Before
    public void createData() {
        scriptRunner.runScript(new InputStreamReader(JpaTest.class.getResourceAsStream("/create_university_jpa_db.sql")));
        factory = Persistence.createEntityManagerFactory("hibernate-unit");
        service = new ServiceImpl(factory);

        Group g1 = new Group("A1");
        Group g2 = new Group("A2");

        Subject s1 = new Subject("Maths", "tech");
        Subject s2 = new Subject("Programming", "tech");
        Subject s3 = new Subject("English", "humanitarian");

        g1.addToSubjectSet(s1);
        g1.addToSubjectSet(s2);
        g2.addToSubjectSet(s3);

        Group group1 = service.addGroup(g1);
        Group group2 = service.addGroup(g2);

        Subject subject1 = service.addSubject(s1);
        Subject subject2 = service.addSubject(s2);
        Subject subject3 = service.addSubject(s3);

        Teacher teacher1 = new Teacher("Teacher_1", 10, subject1.getId(), s1);
        Teacher teacher2 = new Teacher("Teacher_2", 5, subject2.getId(), s2);
        Teacher teacher3 = new Teacher("Teacher_3", 15, subject3.getId(), s3);

        Teacher tch1 = service.addTeacher(teacher1);
        Teacher tch2 = service.addTeacher(teacher2);
        Teacher tch3 = service.addTeacher(teacher3);

        Student student1 = new Student("Student_1", group1.getId(), g1);
        Student student2 = new Student("Student_2", group2.getId(), g2);

        Student st1 = service.addStudent(student1);
        Student st2 = service.addStudent(student2);

        service.addMark(student1, subject1, 5);
        service.addMark(student1, subject1, 4);
        service.addMark(student2, subject1, 4);

        students = new ArrayList<>();
        teachers = new ArrayList<>();
        subjects = new ArrayList<>();
        groups = new ArrayList<>();

        students.add(st1);
        students.add(st2);

        teachers.add(tch1);
        teachers.add(tch2);
        teachers.add(tch3);

        subjects.add(subject1);
        subjects.add(subject2);
        subjects.add(subject3);

        groups.add(group1);
        groups.add(group2);
    }

    @Test
    public void test_add_student() {
        Group testGroup = new Group("A3");
        service.addGroup(testGroup);
        service.addStudent(new Student("testStudent", testGroup.getId(), testGroup));
        int expectedSize = 3;
        Assert.assertTrue(service.getAllStudents().size() == expectedSize);
    }

    @Test
    public void test_add_teacher() {
        Subject testSubject = new Subject("testSubject", "tech");
        Teacher testTeacher = new Teacher("testTeacher", 25, testSubject.getId(), testSubject);
        service.addSubject(testSubject);
        service.addTeacher(testTeacher);
        int expectedSize = 4;
        Assert.assertTrue(service.getAllTeachers().size() == expectedSize);
    }

    @Test
    public void test_add_subject() {
        Subject testSubject = new Subject("testSubject", "tech");
        service.addSubject(testSubject);
        int expectedSize = 4;
        Assert.assertTrue(service.getAllSubjects().size() == expectedSize);
    }

    @Test
    public void test_add_group() {
        Group testGroup = new Group("testGroup");
        service.addGroup(testGroup);
        int expectedSize = 3;
        Assert.assertTrue(service.getAllGroups().size() == expectedSize);
    }

    @Test
    public void test_get_all_students() {
        Assert.assertEquals(service.getAllStudents(), students);
    }

    @Test
    public void test_get_all_groups() {
        Assert.assertEquals(service.getAllGroups(), groups);
    }

    @Test
    public void test_get_all_subjects() {
        Assert.assertEquals(service.getAllSubjects(), subjects);
    }

    @Test
    public void test_get_all_teachers() {
        Assert.assertEquals(service.getAllTeachers(), teachers);
    }

    @Test
    public void test_update_student_info() {
        Group group = new Group("testGroup");
        service.addGroup(group);
        Student student1 = new Student("testStudent_1", group.getId(), group);
        Student student2 = new Student("testStudent_2", group.getId(), group);
        Student newStudent = service.addStudent(student1);
        student2.setId(newStudent.getId());
        Assert.assertEquals(student2, service.updateStudentInfo(newStudent.getId(), student2));

    }

    @Test
    public void test_update_group_info() {
        Group group1 = new Group("testGroup1");
        Group group2 = new Group("testGroup2");
        Group newGroup = service.addGroup(group1);
        Assert.assertEquals(group2.getName(), service.updateGroupInfo(newGroup.getId(), group2).getName());
    }

    @Test
    public void test_update_teacher_info() {
        Subject subject = new Subject("French", "humanitarian");
        service.addSubject(subject);
        Teacher teacher1 = new Teacher("teacher_1", 10, subject.getId(), subject);
        Teacher teacher2 = new Teacher("teacher_2", 30, subject.getId(), subject);
        service.addTeacher(teacher1);
        teacher2.setId(teacher1.getId());
        Assert.assertEquals(teacher2, service.updateTeacherInfo(teacher1.getId(), teacher2));
    }

    @Test
    public void test_update_subject_info() {
        Subject subject1 = new Subject("s1", "description1");
        Subject subject2 = new Subject("s2", "description2");
        service.addSubject(subject1);
        subject2.setId(subject1.getId());
        Assert.assertEquals(subject2, service.updateSubjectInfo(subject1.getId(), subject2));
    }

    @Test
    public void test_get_min_experience() {
        Assert.assertTrue(service.getMinExperience().get(0).getExperience() == 5);
    }

    @Test
    public void test_get_max_experience() {
        Assert.assertTrue(service.getMaxExperience().get(0).getExperience() == 15);
    }

    @Test
    public void test_get_teacher_by_experience() {
        Assert.assertEquals(service.getTeachersByExperience(5).get(0).getName(), "Teacher_2");
    }

    @Test
    public void test_get_humanitarian() {
        List<Subject> expected = new ArrayList<>();
        expected.add(new Subject("English", "humanitarian"));
        Assert.assertEquals(expected.get(0).getName(), service.getHumanitarian().get(0).getName());
    }

    @Test
    public void test_get_students_by_group_id() {
        Group group = new Group("ACP");
        service.addGroup(group);
        List<Student> students = new ArrayList<>();
        Student student1 = new Student("name_1", group.getId(), group);
        Student student2 = new Student("name_2", group.getId(), group);
        service.addStudent(student1);
        service.addStudent(student2);
        students.add(student1);
        students.add(student2);
        Assert.assertEquals(students, service.getStudentByGroupId(group.getId()));
    }

    @Test
    public void test_get_avg_mark() {
        Subject subject = new Subject("C", "programming");
        Subject sub = service.addSubject(subject);

        Group group = new Group("ACP");
        service.addGroup(group);

        Student student1 = new Student("name_1", group.getId(), group);
        Student student2 = new Student("name_2", group.getId(), group);
        Student st1 = service.addStudent(student1);
        Student st2 = service.addStudent(student2);

        service.addMark(st1, sub, 5);
        service.addMark(st2, sub, 4);
        double expected = 4.5;
        Assert.assertTrue(expected == service.getAvgMark(sub));
    }

    @After
    public void deleteTables() {
        scriptRunner.runScript(new InputStreamReader(JpaTest.class.getResourceAsStream("/drop_university_jpa_query.sql")));
        students.clear();
        teachers.clear();
        groups.clear();
        subjects.clear();
    }

    @AfterClass
    public static void closeFactory() {
        factory.close();
    }
}
