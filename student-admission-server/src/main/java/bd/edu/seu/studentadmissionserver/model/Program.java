package bd.edu.seu.studentadmissionserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Program {
    private String name;
    private DegreeType degreeType;
    private int minimun_required_credits;
    private double minimum_required_cgpa;
}
