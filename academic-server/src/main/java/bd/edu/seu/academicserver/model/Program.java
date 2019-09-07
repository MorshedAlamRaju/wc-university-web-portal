package bd.edu.seu.academicserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "name")
public class Program {
    private String name;
    private double credits_required;
    private double cgpa_required;
    List<Course> courseList;

    public Program(String name, double credits_required, double cgpa_required) {
        this.name = name;
        this.credits_required = credits_required;
        this.cgpa_required = cgpa_required;
        this.courseList = new ArrayList<>();
    }
}
