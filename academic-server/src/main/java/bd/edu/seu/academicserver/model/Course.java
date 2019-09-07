package bd.edu.seu.academicserver.model;

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
    private double credits;
    private String type;
    private List<String> prerequisites;

    public Course(String code, String title, double credits, String type) {
        this.code = code;
        this.title = title;
        this.credits = credits;
        this.type = type;
        this.prerequisites = new ArrayList<>();
    }
}
