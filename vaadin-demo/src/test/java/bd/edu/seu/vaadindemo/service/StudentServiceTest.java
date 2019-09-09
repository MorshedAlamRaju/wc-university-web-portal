package bd.edu.seu.vaadindemo.service;

import bd.edu.seu.vaadindemo.model.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceTest {
    @Autowired
    private StudentService studentService;

    @Test
    public  void saveStudentTest(){
        Student student = new Student(1l, "Lana Rose", LocalDate.now());
        Student lana = studentService.save(student);
        assertEquals(student, lana);
    }

    @Test
    public void findByIdTest(){
        Student student = new Student(1l, "Lana Rose", LocalDate.now());
        Student lana = studentService.findById(1);
        assertEquals(student, lana);
    }

    @Test
    public  void updateStudentInfoTest(){
        Student lana = new Student(1l, "Lana Rose", LocalDate.of(1987, 1, 3));
        Student student = studentService.update(1, lana);
        assertTrue(lana.getDob() == student.getDob());
    }


}
