package bd.edu.seu.grademanagementserver.model;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "course")
public class Grade {
    private long studentId;
    private int year;
    private Semester semester;
    private double points;
    @ToString.Exclude
    private Course course;

    @ToString.Include
    public String CourseCode(){
        return course.getCode();
    }

}
