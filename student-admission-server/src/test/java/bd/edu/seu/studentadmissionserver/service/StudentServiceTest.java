package bd.edu.seu.studentadmissionserver.service;

import bd.edu.seu.studentadmissionserver.exception.ResourceAlreadyExistException;
import bd.edu.seu.studentadmissionserver.model.Address;
import bd.edu.seu.studentadmissionserver.model.BloodGroup;
import bd.edu.seu.studentadmissionserver.model.Gender;
import bd.edu.seu.studentadmissionserver.model.Student;
import bd.edu.seu.studentadmissionserver.repository.StudentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceTest {


    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;


    @Before
    public void deleteAll(){
        studentRepository.deleteAll();
    }


    @Test()
    public void saveStudentTest() throws ResourceAlreadyExistException{
        Address address = new Address(1212, "25/D", "Dhaka", "Bangladesh");
        Student student = new Student(313, 52, "Attentive Student", LocalDate.now(),
                address, "xyz@gmail.com", "01911111111", "BSc in CSE",
                Gender.MALE, BloodGroup.B_POSITIVE);
        try {
            Student student1 = studentService.save(student);
            assertEquals(student, student1);
        } catch (ResourceAlreadyExistException e){
            e.printStackTrace();
        }
    }

}
