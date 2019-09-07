package bd.edu.seu.studentadmissionserver.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentTest {

    @Test
    public void createStudentTest(){
        Address address = new Address(1212, "25/D", "Dhaka", "Bangladesh");
        Student student = new Student(123, 52, "Attentive Student", LocalDate.now(),
                address,
                "xyz@gmail.com", "01911111111", "BSc in CSE",
                Gender.MALE, BloodGroup.B_POSITIVE);
        System.out.println(student);
    }

}
