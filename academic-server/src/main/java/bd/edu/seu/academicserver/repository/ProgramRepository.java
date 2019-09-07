package bd.edu.seu.academicserver.repository;

import bd.edu.seu.academicserver.model.Program;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramRepository extends MongoRepository<Program, Long> {
    Program findByName(String name);
}
