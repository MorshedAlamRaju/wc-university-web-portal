package bd.edu.seu.academicserver.controller;

import bd.edu.seu.academicserver.exception.ResourceAlreadyExistException;
import bd.edu.seu.academicserver.model.Course;
import bd.edu.seu.academicserver.model.Program;
import bd.edu.seu.academicserver.repository.ProgramRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProgramControllerTest {
    @Autowired
    private ProgramController programController;

    @Autowired
    private ProgramRepository programRepository;

    @Before
    public void delete(){
        programRepository.deleteAll();
    }


    @Test()
    public void createProgramTest(){
        Program bangla = new Program("Bangla", 120, 2.00);
        ResponseEntity<Program> responseEntity = programController.save(bangla);
        if(responseEntity.hasBody()){
            Program program = responseEntity.getBody();
            assertEquals(bangla, program);
        }
        else System.out.println(responseEntity.getStatusCodeValue());
    }

    @Test
    public void findProgramTest(){
        ResponseEntity<Program> responseEntity = programController.findByName("bangla");
        if(responseEntity.hasBody()){
            Program program = responseEntity.getBody();
            assertEquals("bangla", program.getName());
        } else System.out.println(responseEntity.getStatusCodeValue());
    }

    @Test
    public void addCourseTest(){
        Course course = new Course("GMR123", "Modern Bengali Grammer", 3.0, "Core");
        ResponseEntity<Program> responseEntity = programController.addCourse("bangla", course);
        if(responseEntity.hasBody()){
            Program program = responseEntity.getBody();
            assertTrue(program.getCourseList().contains(course) == true);
        }else System.out.println(responseEntity.getStatusCodeValue());
    }



}
