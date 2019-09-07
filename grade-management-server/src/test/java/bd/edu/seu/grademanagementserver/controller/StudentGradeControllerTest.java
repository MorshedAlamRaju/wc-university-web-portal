package bd.edu.seu.grademanagementserver.controller;

import bd.edu.seu.grademanagementserver.model.Course;
import bd.edu.seu.grademanagementserver.model.Grade;
import bd.edu.seu.grademanagementserver.model.Semester;
import bd.edu.seu.grademanagementserver.model.StudentGrade;
import bd.edu.seu.grademanagementserver.repository.StudentGradeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentGradeControllerTest {
    @Autowired
    private StudentGradeController studentGradeController;

    @Autowired
    private StudentGradeRepository studentGradeRepository;

    @Before
    public void delete(){
        studentGradeRepository.deleteAll();
    }


    @Test
    public void insertGradeTest(){
        Course algo = new Course("CSE2021", "Algorithm", "Core", 3.0);
        Grade grade = new Grade(1234l, 2019, Semester.FALL, 4.0, algo);
        ResponseEntity<StudentGrade> responseEntity = studentGradeController.save(grade);
        if(responseEntity.hasBody()){
            assertTrue(responseEntity.getBody().getGradeList().indexOf(grade) != -1);
        }
        else System.out.println(responseEntity.getStatusCodeValue());

    }


}
