package bd.edu.seu.studentadmissionserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "studentId")
public class Student {
    @Id
    private long studentId;
    private int batch;
    private String name;
    private LocalDate dateOfBirth;
    private Address address;
    private String email;
    private String phone;
    private String programName;
    private Gender gender;
    private BloodGroup bloodGroup;

    public Student(long studentId, int batch, String name, LocalDate dateOfBirth, Address address, String programName, Gender gender) {
        this.studentId = studentId;
        this.batch = batch;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.programName = programName;
        this.gender = gender;
    }
}
