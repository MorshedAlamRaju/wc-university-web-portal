package bd.edu.seu.grademanagementserver.controller;

import bd.edu.seu.grademanagementserver.exception.ResourceAlreadyExistException;
import bd.edu.seu.grademanagementserver.exception.ResourceDoesNotExistException;
import bd.edu.seu.grademanagementserver.model.Grade;
import bd.edu.seu.grademanagementserver.model.StudentGrade;
import bd.edu.seu.grademanagementserver.service.StudentGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/grades")
public class StudentGradeController {
    @Autowired
    private StudentGradeService studentGradeService;



    @GetMapping(value = "")
    public ResponseEntity<List<StudentGrade>> findAll(){
        try{
            List<StudentGrade> studentGradeList = studentGradeService.findAll();
            return ResponseEntity.ok().body(studentGradeList);
        } catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<StudentGrade> findById(@PathVariable long id){
        try{
            StudentGrade studentGrade = studentGradeService.findById(id);
            return ResponseEntity.ok().body(studentGrade);
        } catch(ResourceDoesNotExistException e){
            return ResponseEntity.notFound().build();
        } catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value = "/cgpa/{cgpa}")
    public ResponseEntity<List<StudentGrade>> findByEarnedCgpa(@PathVariable  double cgpa){
        try{
            List<StudentGrade> studentGradeList = studentGradeService.findByEarnedCgpa(cgpa);
            return ResponseEntity.ok().body(studentGradeList);
        } catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value = "/{credits}")
    public ResponseEntity<List<StudentGrade>> findByEarnedCredits(@PathVariable double credits){
        try{
            List<StudentGrade> studentGradeList = studentGradeService.findByEarnedCredits(credits);
            return ResponseEntity.ok().body(studentGradeList);
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<StudentGrade> save(@RequestBody Grade grade){
        try{
            StudentGrade studentGrade = studentGradeService.save(grade);
            return ResponseEntity.ok().body(studentGrade);
        } catch (ResourceAlreadyExistException e){
            return ResponseEntity.badRequest().build();
        } catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<StudentGrade> updateGrade(@PathVariable long id, @RequestBody Grade grade){
        try{
            StudentGrade studentGrade = studentGradeService.update(id, grade);
            return ResponseEntity.ok().body(studentGrade);
        } catch(ResourceDoesNotExistException e){
            return ResponseEntity.badRequest().build();
        } catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

}
