package bd.edu.seu.academicserver.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseTest {
    @Test
    public void createCourseTest(){
        Course course = new Course("ETE2021", "Communication theory", 3.0, "Core");
        System.out.println(course);
    }
}
