import controller.TeacherDao;
import controller.TeacherDaoImpl;
import model.Teacher;

public class TestAddTeacher {
    public static void main(String[] args) {
        TeacherDao teacherDao = new TeacherDaoImpl();
        Teacher teacher = teacherDao.add("Melnikov", 10, 5);
        System.out.println(teacher);
    }
}
