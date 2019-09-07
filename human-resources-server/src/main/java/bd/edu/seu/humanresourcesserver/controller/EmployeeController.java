package bd.edu.seu.humanresourcesserver.controller;


import bd.edu.seu.humanresourcesserver.exception.ResourceDoesNotExistException;
import bd.edu.seu.humanresourcesserver.model.Employee;
import bd.edu.seu.humanresourcesserver.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/employees")
public class EmployeeController {

    @Autowired
    public EmployeeService employeeService;

    @GetMapping(value = "")
    public ResponseEntity<List<Employee>> findAll(){
        try{
            List<Employee> employeeList = new ArrayList<>();
            employeeList = employeeService.findAll();
            return ResponseEntity.ok().body(employeeList);
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Employee> findById(@PathVariable long id){
        try{
            Employee employee = employeeService.findById(id);
            return ResponseEntity.ok().body(employee);
        } catch (ResourceDoesNotExistException e){
            return ResponseEntity.notFound().build();
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

    @PostMapping(value = "")
    public ResponseEntity<Employee> save(@RequestBody Employee employee){
        try{
            Employee employee1 = employeeService.save(employee);
            return ResponseEntity.status(HttpStatus.CREATED).body(employee1);
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> delete(@PathVariable long id){
        try{
            long deleted = employeeService.delete(id);
            return ResponseEntity.ok().body(deleted);
        } catch (ResourceDoesNotExistException e){
            return ResponseEntity.notFound().build();
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value = "/roles/role}")
    public ResponseEntity<List<Employee>> findByProgramName(@PathVariable String programName){
        try{
            List<Employee> employeeList = employeeService.findByRole(programName);
            return ResponseEntity.ok().body(employeeList);
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }


    @PostMapping(value = "/{id}}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id, @RequestBody Employee employee){
        try{
            Employee employee1 = employeeService.updateEmployee(id, employee);
            return ResponseEntity.ok().body(employee1);
        } catch (ResourceDoesNotExistException e){
            return  ResponseEntity.notFound().build();
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

}