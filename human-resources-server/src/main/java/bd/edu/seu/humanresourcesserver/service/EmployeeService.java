package bd.edu.seu.humanresourcesserver.service;

import bd.edu.seu.humanresourcesserver.exception.ResourceAlreadyExistException;
import bd.edu.seu.humanresourcesserver.exception.ResourceDoesNotExistException;
import bd.edu.seu.humanresourcesserver.model.Employee;
import bd.edu.seu.humanresourcesserver.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee findById(long id) throws ResourceDoesNotExistException {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if(optionalEmployee.isPresent())
            return optionalEmployee.get();
        else throw new ResourceDoesNotExistException(id + "");
    }

    public List<Employee> findAll(){
        List<Employee> employeeList = new ArrayList<>();
        employeeRepository.findAll().forEach(employeeList::add);
        return employeeList;
    }

    public Employee save(Employee employee) throws ResourceAlreadyExistException {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employee.getId());
        if(optionalEmployee.isPresent())
            throw new ResourceAlreadyExistException(employee.getId() + "");
        else return employeeRepository.save(employee);
    }

    public long delete(long id) throws ResourceDoesNotExistException {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if(optionalEmployee.isPresent()){
            employeeRepository.deleteById(id);
            return id;
        }
        else throw new  ResourceDoesNotExistException(id + "");
    }

    public Employee updateEmployee(long id, Employee employee) throws ResourceDoesNotExistException {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if(optionalEmployee.isPresent()){
            employee.setId(optionalEmployee.get().getId());
            return employeeRepository.save(employee);
        }
        else throw new ResourceDoesNotExistException(id + "");
    }

    public List<Employee> findByRole(String role){
        List<Employee> employeeList = employeeRepository.findByRole(role);
        return employeeList;
    }

}