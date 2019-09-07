package bd.edu.seu.academicserver.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProgramTest {
    @Test
    public void createProgramTest(){
        Course course = new Course("CSE1011", "Computer fundamentals", 3.0, "Core");
        Program program = new Program("CSE", 144.0, 2.50, Arrays.asList(course));
        System.out.println(program);
    }
}
