package bd.edu.seu.humanresourcesserver.repository;

import bd.edu.seu.humanresourcesserver.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, Long> {
    List<Employee> findByRole(String role);
    List<Employee> findBySalary(double salary);
}
