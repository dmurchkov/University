import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.*;
import org.junit.Test;
import utils.DatabaseUtils;

import java.io.InputStreamReader;
import java.sql.Connection;

public class StudentDaoTest {

    private static final Connection CONNECTION = DatabaseUtils.getDBConnection("test");
    private static ScriptRunner scriptRunner = new ScriptRunner(CONNECTION);

    @BeforeClass
    public static void initSchema() {
        scriptRunner.runScript(new InputStreamReader(StudentDaoTest.class.getResourceAsStream("/universitySchema.sql")));
    }

    @AfterClass
    public static void deleteDB() {

    }

    @Before
    public void createData() {
        scriptRunner.runScript(new InputStreamReader(StudentDaoTest.class.getResourceAsStream("/university_data.sql")));
    }

    @Test
    public void test() {
        System.out.println();
    }

    @After
    public void deleteData() {
        scriptRunner.runScript(new InputStreamReader(StudentDaoTest.class.getResourceAsStream("/drop_all_tables.sql")));
    }
}
