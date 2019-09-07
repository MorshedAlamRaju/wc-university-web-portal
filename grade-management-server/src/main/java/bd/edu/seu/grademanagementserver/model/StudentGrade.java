package bd.edu.seu.grademanagementserver.model;


import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class StudentGrade {
    private long id;
    private double attemptedCredits;
    private double earnedCredits;
    private double earnedCgpa;
    private List<Grade> gradeList;

    public StudentGrade(long id, double attemptedCredits, double earnedCredits, double earnedCgpa) {
        this.id = id;
        this.attemptedCredits = attemptedCredits;
        this.earnedCredits = earnedCredits;
        this.earnedCgpa = earnedCgpa;
        gradeList = new ArrayList<>();
    }
}
