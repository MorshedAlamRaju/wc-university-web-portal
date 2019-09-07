package bd.edu.seu.studentadmissionserver.controller;


import bd.edu.seu.studentadmissionserver.exception.ResourceDoesNotExistException;
import bd.edu.seu.studentadmissionserver.model.Student;
import bd.edu.seu.studentadmissionserver.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/students")
public class StudentController {

    @Autowired
    public StudentService studentService;

    @GetMapping(value = "")
    public ResponseEntity<List<Student>> findAll(){
        try{
            List<Student> studentList = new ArrayList<>();
            studentList = studentService.findAll();
            return ResponseEntity.ok().body(studentList);
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Student> findById(@PathVariable long id){
        try{
            Student student = studentService.findById(id);
            return ResponseEntity.ok().body(student);
        } catch (ResourceDoesNotExistException e){
            return ResponseEntity.notFound().build();
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

    @PostMapping(value = "")
    public ResponseEntity<Student> save(@RequestBody Student student){
        try{
            Student student1 = studentService.save(student);
            return ResponseEntity.status(HttpStatus.CREATED).body(student1);
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> delete(@PathVariable long id){
        try{
            long deleted = studentService.delete(id);
            return ResponseEntity.ok().body(deleted);
        } catch (ResourceDoesNotExistException e){
            return ResponseEntity.notFound().build();
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value = "/program/{programName}")
    public ResponseEntity<List<Student>> findByProgramName(@PathVariable String programName){
        try{
            List<Student> studentList = studentService.findByProgramName(programName);
            return ResponseEntity.ok().body(studentList);
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value = "/batch/{batch}")
    public ResponseEntity<List<Student>> findByBatch(@PathVariable int batch){
        try{
            List<Student> studentList = studentService.findByBatch(batch);
            return ResponseEntity.ok(studentList);
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable long id, @RequestBody Student student){
        try{
            Student student1 = studentService.updateStudent(id, student);
            return ResponseEntity.ok().body(student1);
        } catch (ResourceDoesNotExistException e){
            return  ResponseEntity.notFound().build();
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

}
