package bd.edu.seu.studentadmissionserver.repository;

import bd.edu.seu.studentadmissionserver.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends MongoRepository<Student, Long> {
    List<Student> findByBatch(int batch);
    List<Student> findByProgramName(String programName);
}
