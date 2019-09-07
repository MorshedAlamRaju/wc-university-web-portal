package bd.edu.seu.grademanagementserver.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "code")
public class Course {
    private String code;
    private String title;
    private String courseType;
    private double credits;
    private List<String> prerequisites;

    public Course(String code, String title, String courseType, double credits) {
        this.code = code;
        this.title = title;
        this.courseType = courseType;
        this.credits = credits;
        prerequisites = new ArrayList<>();
    }

}
