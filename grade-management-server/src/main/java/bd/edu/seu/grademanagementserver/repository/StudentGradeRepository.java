package bd.edu.seu.grademanagementserver.repository;

import bd.edu.seu.grademanagementserver.model.StudentGrade;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentGradeRepository extends MongoRepository<StudentGrade, Long> {
    List<StudentGrade> findByEarnedCredits(double credits);
    List<StudentGrade> findByEarnedCgpa(double credits);
}
