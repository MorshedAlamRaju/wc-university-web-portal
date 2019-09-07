package bd.edu.seu.logintokenserver.repository;


import bd.edu.seu.logintokenserver.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginTokenRepository extends MongoRepository<Employee, Long> {
}
